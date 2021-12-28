package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadJson {
    private ReadJson() {}
    public static Map<String, List<Object>> readJsonLikeAMap(String path) throws IOException {
        return (Map<String, List<Object>>) new ObjectMapper().readValue(new File(path), Map.class);
    }

}
