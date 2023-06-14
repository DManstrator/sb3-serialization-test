package tk.dmanstrator.sb3_serialization_test.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public interface SerializationHandler {

    boolean isBinary();

    String getFileNameExtension();

    <T extends Serializable> void writeObject(final T object, final OutputStream os) throws IOException;

    <T extends Serializable> String writeObjectAsString(final T object) throws IOException;

    <T extends Serializable> T readObject(final Class<T> clazz, final InputStream is) throws IOException;

    <T extends Serializable> T readObjectFromString(final Class<T> clazz, final String input) throws IOException;

}
