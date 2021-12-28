package service;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import util.ReadJson;

import java.io.IOException;
import java.util.*;

public class CredentialService {

    public Map<String, List<List<Object>>> returnDifferenceOfCredentialsMap(String path1, String path2) throws IOException {
        Map<String, List<Object>> map1 = ReadJson.readJsonLikeAMap(path1);
        Map<String, List<Object>> map2 = ReadJson.readJsonLikeAMap(path2);
        Map<String, List<Object>> sumMap = new HashMap<>(map1);
        map2.entrySet().stream().forEach(entry -> {
            sumMap.put(entry.getKey(), entry.getValue());
        });
        Set resultSet = Sets.symmetricDifference(map1.keySet(), map2.keySet());

        MapDifference mapDifference = Maps.difference(map1,map2);
        Map midResult = mapDifference.entriesDiffering();
        Map result = new HashMap();
        Set keys = midResult.keySet();
        for (Object key : keys) {
            List<Object> intersection = new ArrayList<>(map1.get(key));
            intersection.retainAll(map2.get(key));
            List<Object> differenceList = new ArrayList<>(map1.get(key));
            differenceList.removeAll(intersection);
            result.put(key,differenceList);
        }
        for (Object key : resultSet) {
            if (map1.containsKey(key)) {
                result.put(key, map1.get(key));
            } else {
                result.put(key, map2.get(key));
            }
        }

        return result;
    }


    public int returnNumberOfDifferenceOfCredentialsMap(String path1, String path2) throws IOException {
        return returnDifferenceOfCredentialsMap(path1, path2).values()
                .stream().mapToInt(List::size).sum();
    }

}
