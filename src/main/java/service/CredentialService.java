package service;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import model.ProfCredentials;
import util.ReadJson;

import java.io.IOException;
import java.util.*;

public class CredentialService {

    public Map returnDifferenceOfCredentialsMap (String path1, String path2) throws IOException {
        Map map1 = ReadJson.readJsonLikeAMap(path1);
        Map map2 = ReadJson.readJsonLikeAMap(path2);
        MapDifference mapDifference = Maps.difference(map1,map2);
        Map midResult = mapDifference.entriesDiffering();
        Map result = new HashMap();
        Set keys = midResult.keySet();
        for (Object key : keys) {
            List<Object> intersection = new ArrayList<>((List<Object>) map1.get(key));
            intersection.retainAll((List<Object>) map2.get(key));
            List<Object> differenceList = new ArrayList<>((List<Object>) map1.get(key));
            differenceList.removeAll(intersection);
            result.put(key,differenceList);
        }
        return result;
    }

    public int returnNumberOfDifferenceOfCredentialsMap (String path1, String path2) throws IOException {
        Map map = returnDifferenceOfCredentialsMap(path1,path2);
        Set keys = map.keySet();
        List<Object> differenceList = new ArrayList<>();
        for (Object key : keys) {
            differenceList.addAll((Collection<?>) map.get(key));
        }
        return differenceList.size();
    }

}
