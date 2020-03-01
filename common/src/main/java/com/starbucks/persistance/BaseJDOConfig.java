package com.starbucks.persistance;


import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Strings;
import com.starbucks.config.SharedConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Properties;

public class BaseJDOConfig extends Properties implements JDOConfig  {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseJDOConfig.class);

    public static final Integer IDLE_TIMEOUT_DEFAULT = 240000;
    public static final String PREP_STMT_CACHE_DEFAULT = "true";
    public static final Integer PREP_STMT_CACHE_SIZE_DEFAULT = 250;
    public static final Integer PREP_STMT_CACHE_SQL_LIMIT_DEFAULT = 2048;
    public static final Integer MAX_POOL_SIZE = 5;
    public static final Integer MIN_IDLE_POOL = 1;
    public static final Integer LEAK_DETECTION_THRESHOLD = 30000;
    public static final String MYSQL_USE_CONFIG = "maxPerformance";
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final Integer WAIT_TIMEOUT_DEFAULT = 5000;


    protected HikariDataSource hikariDS;
    private final SharedConfig config;
    private final String groupName;
    private final String configKeyGroup;
    private String type;
    private String name;
    private String user;
    private String autoCreate;
    private String urlOptionalParams = "";
    private String host = "";
    private String port = "";
    private String env = "";
    private String transactionPoolName;

    private final MetricRegistry metricRegistry;
    private final boolean readOnly;

    /*
     * Static Creation Helpers
     */
    static BaseJDOConfig createTestJDOConfig(final SharedConfig config, final String configKeyGroup) {
        return new BaseJDOConfig(config, configKeyGroup);
    }

    /*
     * Constructors
     */
    private BaseJDOConfig(final SharedConfig config, final String configKeyGroup) {
        this.config = config;
        this.configKeyGroup = configKeyGroup;
        groupName = config.getGroupName();
        metricRegistry = null;
        readOnly = true;
        env = SharedConfig.Env.DEV.getName();

    }

    public BaseJDOConfig(final SharedConfig config, final MetricRegistry metricRegistry,
                         final boolean readOnly, final String configKeyGroup) {
        this(config, metricRegistry, readOnly, configKeyGroup, null);
    }

    public BaseJDOConfig(final SharedConfig config, final MetricRegistry metricRegistry,
                         final boolean readOnly, final String configKeyGroup, final String transactionPoolName) {
        this.config = config;
        this.metricRegistry = metricRegistry;
        this.readOnly = readOnly;
        this.groupName = config.getGroupName();
        this.transactionPoolName = transactionPoolName;
        this.configKeyGroup = configKeyGroup;
        this.env = config.getEnv();

        // Setup
        initialize();
        setupPassword();
        setupAutoCreate();
        setJDBC();

        // Pool Type Setup
        final String poolType = Objects.requireNonNull(config.getString(getConfigKey("connection_pool_type")));
        if ("HikariCP".equals(poolType)) {
            setHikariPool();
        } else {
            throw new RuntimeException("JDOConfig Initialization Failure: Pool not recognized");
        }
    }

    // No need for default constructor because it's not used and causes final fields assigned multiple times.
    // E.g. this.groupName is null if you use default constructor
    private void initialize() {

        // common datanucleus settings
        setProperty("datanucleus.cache.level2.mode", "none");
        setProperty("datanucleus.DetachAllOnCommit", "true");
        setProperty("datanucleus.identifier.case", "MixedCase");
        setProperty("datanucleus.rdbms.initializeColumnInfo", "None");
        setProperty("datanucleus.rdbms.statementBatchLimit", "100");
        setProperty("datanucleus.validateColumns", "false");
        setProperty("datanucleus.validateConstraints", "true");
        setProperty("datanucleus.validateTables", "false");
        setProperty("datanucleus.validation.mode", "auto");
        setProperty("datanucleus.enableStatistics", "true");
        setProperty("datanucleus.query.jdoql.allowAll", "true");
        setProperty("datanucleus.query.sql.allowAll", "true");
        setProperty("datanucleus.query.jdoql.dropDistinctFromImplicitJoin", "true");


        setProperty(PersistenceDirector.JDO_READ_ONLY_OPTION_NAME, String.valueOf(isReadOnly())); // read and write timeout
        setProperty("datanucleus.maxFetchDepth", "3");

        setProperty("datanucleus.datastoreReadTimeout", String.valueOf(config.getInteger(getConfigKey("read_timeout"))));
        setProperty("datanucleus.datastoreWriteTimeout", String.valueOf(config.getInteger(getConfigKey("write_timeout"))));
    }

    public HikariDataSource getHikariDS() {
        return hikariDS;
    }

    public String getDBConnectionUrl() {
        String url;
        switch (getType()) {
            case "h2":
            case "hsqldb":
                url = "jdbc:" + getType() + ":mem:" + getName();
                break;

            default:
                url = "jdbc:" + getType() + "://" + getHost() + ":" + getPort() + "/" + getName();
                break;
        }
        if (!Strings.isNullOrEmpty(urlOptionalParams)) {
            url += urlOptionalParams;
        }
        return url;
    }

    public String getType() {
        return type;
    }

    public String getUrlOptionalParams() {
        return urlOptionalParams;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        String key = config.getString(getConfigKey("password_key"));
        LOGGER.warn("Using key={}", key);
        if (StringUtils.isBlank(key)) {
            LOGGER.info("No db password key set, skipping KMS");
            return config.getString(getConfigKey("access"));
        } else {
            return config.getString(getConfigKey("secret"));
        }
    }

    public String getAutoCreate() {
        return autoCreate;
    }

    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    protected String getConfigKey(final String key) {
        String result = "jdo." + this.groupName;
        if (this.configKeyGroup != null) {
            result = result + "." + this.configKeyGroup;
        }

        result = result + "." + key;
        return result;
    }

    private String getSystem(final String propertyKey, final String defaultValue) {
        final String system = System.getProperty(propertyKey);
        if (Strings.isNullOrEmpty(system)) {
            return defaultValue;
        } else {
            return system;
        }
    }

    private void setupAutoCreate() {
        this.autoCreate = String.valueOf(config.getBoolean(getConfigKey("auto_create")));
        setProperty("datanucleus.autoCreateColumns", autoCreate);
        setProperty("datanucleus.autoCreateConstraints", autoCreate);
        setProperty("datanucleus.autoCreateSchema", autoCreate);
        setProperty("datanucleus.autoCreateTables", autoCreate);
        setProperty("datanucleus.rdbms.CheckExistTablesOrViews", autoCreate);
    }

    private HikariConfig getHikariConfig() {
        final HikariConfig hikariConfig = new HikariConfig();
        final String driverClass = config.getStringOrDefault(getConfigKey("driver_name"), MYSQL_DRIVER);
        hikariConfig.setJdbcUrl(getDBConnectionUrl());
        hikariConfig.setDriverClassName(driverClass);
        hikariConfig.setUsername(getUser());
        hikariConfig.setPassword(getPassword());
        hikariConfig.setAutoCommit(false);
        hikariConfig.setTransactionIsolation("TRANSACTION_READ_COMMITTED");

        if (MYSQL_DRIVER.equals(driverClass)) {
            hikariConfig.addDataSourceProperty("cachePrepStmts", PREP_STMT_CACHE_DEFAULT);
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", config.getIntegerOrDefault(
                    getConfigKey("prep_stmt_cache_size"), PREP_STMT_CACHE_SIZE_DEFAULT));

            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", config.getIntegerOrDefault(
                    getConfigKey("prep_stmt_cache_sql_limit"), PREP_STMT_CACHE_SQL_LIMIT_DEFAULT));

            // Removes unnecessary no-ops
            final String useConfigs = config.getStringOrDefault(getConfigKey("use_configs"), MYSQL_USE_CONFIG);
            LOGGER.info("Using JDBC UseConfigs: " + useConfigs);
            hikariConfig.addDataSourceProperty("useConfigs", useConfigs);
            hikariConfig.addDataSourceProperty("useLocalTransactionState", "true");
        }
        hikariConfig.setReadOnly(isReadOnly());

        hikariConfig.setPoolName(config.getString(getConfigKey("pool_instance_name")));
        hikariConfig.setMetricRegistry(metricRegistry);

        // TODO DEFAULTS

        int maxPoolSize = config.getIntegerOrDefault(
                getConfigKey("max_pool_size"), MAX_POOL_SIZE);
        hikariConfig.setMaximumPoolSize(maxPoolSize);

        int minIdle = config.getIntegerOrDefault(
                getConfigKey("min_idle"), MIN_IDLE_POOL);
        hikariConfig.setMinimumIdle(minIdle);

        // 30 seconds less than ANY database connection timeout
        long maxLifetime = config.getIntegerOrDefault(
                getConfigKey("max_lifetime"), IDLE_TIMEOUT_DEFAULT);
        hikariConfig.setMaxLifetime(maxLifetime);

        // 30 seconds less than ANY connection timeout
        long idleTimeout = config.getIntegerOrDefault(
                getConfigKey("idle_timeout"), IDLE_TIMEOUT_DEFAULT);
        hikariConfig.setIdleTimeout(idleTimeout);

        long connectionTimeout = config.getIntegerOrDefault(
                getConfigKey("max_wait"), WAIT_TIMEOUT_DEFAULT);
        hikariConfig.setConnectionTimeout(connectionTimeout);

        long leakDetectionThreshold = config.getIntegerOrDefault(
                getConfigKey("leak_detection_threshold"), LEAK_DETECTION_THRESHOLD);
        hikariConfig.setLeakDetectionThreshold(leakDetectionThreshold);
        return hikariConfig;
    }

    private void setHikariPool() {
        final HikariConfig hikariConfig = getHikariConfig();
        hikariDS = new HikariDataSource(hikariConfig);
        setProperty("javax.jdo.option.ConnectionDriverName", config.getString(getConfigKey("driver_name")));

        // transactionPoolName is specified in WriteJDOConfig
        if (transactionPoolName != null) {
            // Transactional datasource
            hikariConfig.setMaximumPoolSize(hikariConfig.getMaximumPoolSize() * 3);
            hikariConfig.setMinimumIdle(hikariConfig.getMaximumPoolSize());
            hikariConfig.setPoolName(transactionPoolName);
            final HikariDataSource transactionalDS = new HikariDataSource(hikariConfig);

            this.put("datanucleus.ConnectionFactory", transactionalDS);
            this.put("datanucleus.ConnectionFactory2", hikariDS);
        } else {
            this.put("datanucleus.ConnectionFactory", hikariDS);
        }
    }

    private void setJDBC() {
        this.host = getSystem("dbHost", config.getString(getConfigKey("host")));
        this.port = getSystem("dbPort", String.valueOf(config.getInteger(getConfigKey("port"))));
        this.type = Objects.requireNonNull(config.getString(getConfigKey("type")), "type");
        this.name = Objects.requireNonNull(getSystem("dbName", config.getString(getConfigKey("dbname"))), "name");
        this.user = Objects.requireNonNull(getSystem("dbUsername", config.getString(getConfigKey("username"))), "user");
        this.urlOptionalParams = config.getString(getConfigKey("url_optional_params"));
        LOGGER.info("JDBC Url (" + getGroupName() + "): " + getDBConnectionUrl());
    }

    private void setupPassword() {
        setProperty("javax.jdo.option.ConnectionPassword", getPassword());
    }
}
