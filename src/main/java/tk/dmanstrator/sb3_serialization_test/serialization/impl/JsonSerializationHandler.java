package tk.dmanstrator.sb3_serialization_test.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializationHandler extends AbstractJsonSerializationHandler {

    /**
     * Creates a new JSON Serializer. Won't apply pretty-printing when writing objects.
     */
    public JsonSerializationHandler() {
        this(false);
    }

    /**
     * Creates a new JSON Serializer.
     *
     * @param doPrettyPrint Flag to tell whether pretty-printing should be performed when writing objects or not.
     */
    public JsonSerializationHandler(final boolean doPrettyPrint) {
        super(doPrettyPrint);
    }

    @Override
    public boolean isBinary() {
        return false;
    }

    @Override
    public String getFileNameExtension() {
        return ".json";
    }

    public static ObjectMapper getStandardObjectMapper() {
        return OBJECT_MAPPER_THREAD_LOCAL.get();
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

}
