package io.ac.starter.util;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.io.Writer;
import java.util.Set;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
public class DefaultObjectMapper  extends ObjectMapper {

    public DefaultObjectMapper(String... basePackages){


        Reflections reflections = new Reflections(
                        new ConfigurationBuilder()
                        .forPackages(basePackages)
                        .addScanners(new SubTypesScanner())
                       );
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(JsonTypeName.class);
        super.registerSubtypes(classSet);
    }

    @Override
    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeValue(Writer w, Object value) {
        try {
            super._configAndWriteValue(_jsonFactory.createGenerator(w), value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public <T> T readValue(String content, Class<T> valueType) {
        try {
            return super.readValue(content, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return super.readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
