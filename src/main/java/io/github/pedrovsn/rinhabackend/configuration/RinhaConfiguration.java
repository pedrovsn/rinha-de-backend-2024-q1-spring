package io.github.pedrovsn.rinhabackend.configuration;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import java.time.format.*;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.*;

@Configuration
public class RinhaConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME))
                .featuresToDisable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
    }
}
