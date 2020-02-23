package com.starbucks.localization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;
import java.util.ResourceBundle;

public class Localization {
    // Supported language
    private static final String EN = "en";

    // Supported Country
    private static final String US = "US";

    private static final Logger LOGGER = LoggerFactory.getLogger(Localization.class);
    private static final String RESOURCE_BUNDLE_NAME = "strings";

    // Default Locale
    public static final Locale DEFAULT_LOCALE = new Locale(EN, US);

    public static String getLocalizedMsg(final String key, final String lang, final Object... msgVariables) {
        try {
            final Locale currentLocale = getCurrentLocale(Collections.singletonList(new LanguageRange(lang)));
            final ResourceBundle localizedStrings = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, currentLocale);
            final String localizedMsg = localizedStrings.getString(key);
            final MessageFormat formatter = new MessageFormat(localizedMsg, currentLocale);
            final String result = formatter.format(msgVariables);
            LOGGER.info("key", key, "lang", lang, "result", result, " Localized message for given key and lang");
            return result;
        } catch (final Exception ex) {
            LOGGER.error("key:" + key + " lang:" + lang + " StarbucksError finding localized string for given key and lang", ex);
            return null;
        }
    }

    public static Locale getCurrentLocale(final List<LanguageRange> priorityList) {
        Locale locale = null;
        if (null != priorityList && !priorityList.isEmpty()) {
            // the best matching Locale instance chosen based on priority or weight, or null if nothing matches.
            locale = Locale.lookup(priorityList, Localization.getSupportedLocales());
        }
        if (null == locale) {
            locale = DEFAULT_LOCALE;
        }
        return locale;
    }

    private static List<Locale> getSupportedLocales() {
        // Expand the list when we want to support more locales in the future
        return Collections.singletonList(DEFAULT_LOCALE);
    }
}
