package service;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import util.ReadJson;

import java.io.IOException;
import java.util.*;

public class CredentialService {

    public Map<String, List<Object>> returnDifferenceOfCredentialsMap(String path1, String path2) throws IOException {
        Map<String, List<Object>> map1 = ReadJson.readJsonLikeAMap(path1);
        Map<String, List<Object>> map2 = ReadJson.readJsonLikeAMap(path2);
        Set<String> resultSet = Sets.symmetricDifference(map1.keySet(), map2.keySet());

        MapDifference<String, List<Object>> mapDifference = Maps.difference(map1, map2);
        Map<String, MapDifference.ValueDifference<List<Object>>> midResult = mapDifference.entriesDiffering();
        Map<String,List<Object>> result = new HashMap<>();
        Set<String> keys = midResult.keySet();
        for (String key : keys) {
            List<Object> intersection = new ArrayList<>(map1.get(key));
            intersection.retainAll(map2.get(key));
            List<Object> differenceList = new ArrayList<>(map1.get(key));
            differenceList.removeAll(intersection);
            result.put(key, differenceList);
        }
        for (String key : resultSet) {
            if (map1.containsKey(key)) {
                if (!map1.get(key).isEmpty()) {
                    if (((LinkedHashMap) map1.get(key).get(0)).size() != 0) {
                        result.put(key, map1.get(key));
                    }
                }
            } else {
                if (!map2.get(key).isEmpty()) {
                    if (!map2.get(key).isEmpty()) {
                        if (((LinkedHashMap) map2.get(key).get(0)).size() != 0) {
                            result.put(key, map2.get(key));
                        }
                    }
                }
            }
        }

        return result;
    }


    public int returnNumberOfDifferenceOfCredentialsMap(String path1, String path2) throws IOException {
        return returnDifferenceOfCredentialsMap(path1, path2).values().stream().mapToInt(List::size).sum();
    }

}
