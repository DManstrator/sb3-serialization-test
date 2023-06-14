package tk.dmanstrator.sb3_serialization_test.entity;

import java.io.Serializable;
import java.util.List;

public record CustomObject(String hello, List<String> world) implements Serializable {}
