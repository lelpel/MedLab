package ua.lelpel.medlab.db;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;

import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by bruce on 28.11.2017.
 */
public class LocalDateConverter {
    private static SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");

    private LocalDateConverter() {
        throw new AssertionError();
    }

    @TypeConverter
    public static LocalDate fromString(@Nullable String s) {
       return LocalDate.parse(s);
    }

    @TypeConverter
    public static String localDateToString(@Nullable org.joda.time.LocalDate localDate) {
        return localDate.toString();
    }
}