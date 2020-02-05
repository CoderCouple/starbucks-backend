package com.starbucks.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.starbucks.constant.Constants.DATE_FORMATTER;

public class Utils {
    public static Timestamp getUTCTimestamp(final String timeString) {
        return StringUtils.isBlank(timeString) ? null : Timestamp.from(LocalDateTime.parse(timeString, DATE_FORMATTER)
                .atOffset(ZoneOffset.UTC).toInstant());
    }
}
