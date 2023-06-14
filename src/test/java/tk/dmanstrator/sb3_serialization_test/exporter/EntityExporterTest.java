package tk.dmanstrator.sb3_serialization_test.exporter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tk.dmanstrator.sb3_serialization_test.entity.CustomObject;

import java.io.IOException;
import java.util.Arrays;

class EntityExporterTest {

    private static final String EMPTY_JSON = "{ }";

    @Test
    void testExportCustomObject() throws IOException {
        final CustomObject customObject = new CustomObject("hello", Arrays.asList("wor", "ld"));
        final String json = EntityExporter.exportCustomObject(customObject);
        Assertions.assertThat(json)
                .isNotEmpty()
                .isNotEqualTo(EMPTY_JSON);
    }

}