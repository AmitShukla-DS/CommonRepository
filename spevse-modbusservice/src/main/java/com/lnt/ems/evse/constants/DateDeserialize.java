/**
 *
 */
package com.lnt.ems.evse.constants;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 503105802
 *
 */
public class DateDeserialize extends StdDeserializer<Date> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public DateDeserialize() {
        this(null);
    }

    public DateDeserialize(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser,
                            DeserializationContext deserializationContext) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date = jsonParser.getText();
        try {
            return format.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}