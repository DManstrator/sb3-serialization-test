package tk.dmanstrator.sb3_serialization_test.exporter;

import tk.dmanstrator.sb3_serialization_test.entity.CustomObject;
import tk.dmanstrator.sb3_serialization_test.serialization.SerializationHandlerFactory;

import java.io.IOException;

/**
 * Class responsible for exporting entities.
 */
public class EntityExporter {

    private EntityExporter() {
        // hide default constructor
    }

    public static String exportCustomObject(final CustomObject customObject) throws IOException {
        return SerializationHandlerFactory.getDefaultHandlerWithPrettyPrint()
                .writeObjectAsString(customObject);
    }

}
