package guru.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonContentTest {
    private final ClassLoader cl = JsonContentTest.class.getClassLoader();


    @Test
    public void checkJsonContent() throws IOException {
        try (InputStream stream = cl.getResourceAsStream("sample.json")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(stream, Map.class);
            List<Map<String, Object>> products = (List<Map<String, Object>>) map.get("products");
            Map<String, Object> product = products.stream()
                    .filter(p -> p.get("id").equals(48))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Product with id 48 not found"));
            List<String> categories = (List<String>) product.get("categories");
            assertEquals(List.of("Кофеварки", "Кухонная техника", "Техника"), categories);
        }
    }
}
