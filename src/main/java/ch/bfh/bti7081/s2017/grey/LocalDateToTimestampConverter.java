package ch.bfh.bti7081.s2017.grey;

import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.v7.data.util.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Created by Nic on 21.05.17.
 */
public class TimestampToLocalDateConverter implements com.vaadin.data.Converter<LocalDate, Timestamp> {

    @Override
    public Result<Timestamp> convertToModel(LocalDate localDate, ValueContext valueContext) {
        try {
            return Result.ok(Timestamp.valueOf(localDate.atStartOfDay()));
        } catch (IllegalArgumentException e) {
            return Result.error("Could not convert");
        }
    }

    @Override
    public LocalDate convertToPresentation(Timestamp timestamp, ValueContext valueContext) {
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
