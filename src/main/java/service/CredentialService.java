package service;

import model.ProfCredentials;
import util.ReadJson;

import java.io.IOException;
import java.util.*;

public class CredentialService {
    public List<ProfCredentials> returnDifference(String path1, String path2) throws IOException {
        List<ProfCredentials> list1 = ReadJson.readJsonInClassWithCredentials(path1).getCredentials();
        List<ProfCredentials> list2 = ReadJson.readJsonInClassWithCredentials(path2).getCredentials();
        List<ProfCredentials> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        List<ProfCredentials> result = new ArrayList<>(list1);
        result.removeAll(intersection);
        return result;
    }

    public List<Object> returnDifferenceOfCorruptedCredentialsList (String path1, String path2) throws IOException {
        List<Object> list1 = ReadJson.readJsonInClass(path1).getCredentials();
        List<Object> list2 = ReadJson.readJsonInClass(path2).getCredentials();
        List<Object> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        List<Object> result = new ArrayList<>(list1);
        result.removeAll(intersection);
        return result;
    }

    public int returnNumberOfDifference(String path1, String path2) throws IOException {
        return returnDifference(path1,path2).size();
    }

    public int returnNumberOfDifferenceOfCorruptedCredentialsList (String path1, String path2) throws IOException {
        return returnDifferenceOfCorruptedCredentialsList(path1,path2).size();
    }
}
