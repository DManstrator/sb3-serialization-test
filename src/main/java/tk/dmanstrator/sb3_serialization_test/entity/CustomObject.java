package tk.dmanstrator.sb3_serialization_test.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private final String hello;
    private final List<String> world;

    // Default Constructor needed for (de)serialization.
    public CustomObject() {
        this("", new ArrayList<>());
    }

    public CustomObject(String hello, List<String> world) {
        this.hello = hello;
        this.world = world;
    }

    public String getHello() {
        return hello;
    }

    public List<String> getWorld() {
        return world;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CustomObject) obj;
        return Objects.equals(this.hello, that.hello) &&
                Objects.equals(this.world, that.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hello, world);
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "hello='" + hello + '\'' +
                ", world=" + world +
                '}';
    }

}
