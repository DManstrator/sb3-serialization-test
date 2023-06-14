package tk.dmanstrator.sb3_serialization_test.serialization;

import tk.dmanstrator.sb3_serialization_test.serialization.impl.JsonSerializationHandler;

public final class SerializationHandlerFactory {

    private static final SerializationHandler DEFAULT_HANDLER = new JsonSerializationHandler(false);
    private static final SerializationHandler DEFAULT_HANDLER_WITH_PRETTY_PRINT = new JsonSerializationHandler(true);

    private SerializationHandlerFactory() {}

    public static SerializationHandler getDefaultHandler() {
        return DEFAULT_HANDLER;
    }

    public static SerializationHandler getDefaultHandlerWithPrettyPrint() {
        return DEFAULT_HANDLER_WITH_PRETTY_PRINT;
    }

}
