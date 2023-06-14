package tk.dmanstrator.sb3_serialization_test.serialization.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import tk.dmanstrator.sb3_serialization_test.serialization.SerializationHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractJsonSerializationHandler implements SerializationHandler {

    protected static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL =
            ThreadLocal.withInitial(AbstractJsonSerializationHandler::newObjectMapper);

    private final Map<Class<?>, ObjectReader> readers = new ConcurrentHashMap<>();
    private final Map<Class<?>, ObjectWriter> writers = new ConcurrentHashMap<>();

    private final boolean doPrettyPrint;

    protected AbstractJsonSerializationHandler(final boolean doPrettyPrint) {
        this.doPrettyPrint = doPrettyPrint;
    }

    @Override
    public <T extends Serializable> void writeObject(final T object, final OutputStream os) throws IOException {
        ObjectWriter writer = getWriter(object.getClass());

        if (doPrettyPrint) {
            writer = writer.withDefaultPrettyPrinter();
        }

        writer.writeValue(os, object);
    }

    @Override
    public <T extends Serializable> String writeObjectAsString(final T object) throws IOException {
        ObjectWriter writer = getWriter(object.getClass());

        if (doPrettyPrint) {
            writer = writer.withDefaultPrettyPrinter();
        }

        return writer.writeValueAsString(object);
    }

    @Override
    public <T extends Serializable> T readObject(final Class<T> clazz, final InputStream is) throws IOException {
        return getReader(clazz).readValue(is);
    }

    @Override
    public <T extends Serializable> T readObjectFromString(final Class<T> clazz, final String input)
            throws IOException {
        return getReader(clazz).readValue(input);
    }

    private static ObjectMapper newObjectMapper() {
        final PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
                .builder()
                .allowIfBaseType(List.class)
                .allowIfBaseType(Map.class)
                .allowIfBaseType(Set.class)
                .allowIfSubType(List.class)
                .allowIfSubType(Map.class)
                .build();
        return JsonMapper.builder()
                .enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)
                .activateDefaultTyping(ptv)
                .addModule(new JavaTimeModule())
                .addModule(new Jdk8Module())
                .visibility(PropertyAccessor.ALL, Visibility.NONE)
                .visibility(PropertyAccessor.FIELD, Visibility.ANY)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .serializationInclusion(Include.NON_NULL)
                .build();
    }

    protected abstract ObjectMapper createObjectMapper();

    private ObjectReader getReader(final Class<?> clazz) {
        return readers.computeIfAbsent(clazz, c -> OBJECT_MAPPER_THREAD_LOCAL.get().readerFor(c));
    }

    private ObjectWriter getWriter(final Class<?> clazz) {
        return writers.computeIfAbsent(clazz, c -> OBJECT_MAPPER_THREAD_LOCAL.get().writerFor(c));
    }

}
