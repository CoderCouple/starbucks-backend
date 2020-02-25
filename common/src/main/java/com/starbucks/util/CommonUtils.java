package com.starbucks.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import static com.starbucks.constant.Constants.DATE_FORMATTER;

public class CommonUtils {
    public static Timestamp getUTCTimestamp(final String timeString) {
        return StringUtils.isBlank(timeString) ? null : Timestamp.from(LocalDateTime.parse(timeString, DATE_FORMATTER)
                .atOffset(ZoneOffset.UTC).toInstant());
    }

    public static String getUTCDateTimeString(final Timestamp timestamp) {
        return Objects.isNull(timestamp) ? null : getUTCDateTimeString(timestamp.getTime());
    }

    public static String getUTCDateTimeString(final long timeInMillis) {
        return timeInMillis < 0 ? null : LocalDateTime.ofEpochSecond(timeInMillis / 1000, 0, ZoneOffset.UTC)
                .format(DATE_FORMATTER);
    }
}
