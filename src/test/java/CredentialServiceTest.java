import com.fasterxml.jackson.databind.ObjectMapper;
import model.Profession;
import model.ProfessionWithListOfObjects;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.CredentialService;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class CredentialServiceTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    CredentialService service = new CredentialService();
    String path1 = "src/test/resources/Сredentials.json";
    String path2 = "src/test/resources/AnotherСredentials.json";
    String path3 = "src/test/resources/CorruptedСredentials.json";
    String path5 = "src/test/resources/SourceCredentialsMap.json";
    String path6 = "src/test/resources/ChangedCredentialsMap.json";

    @Test
    public void ServiceReturnDifferenceTest() throws IOException {
        String ResultOfDifferencesOfCredentials = "src/test/resources/ResultDifferencesOfСredentials.json";

        Profession resultDifferencesOfPrudentials
                = new ObjectMapper().readValue(new File(ResultOfDifferencesOfCredentials), Profession.class);

        Assert.assertEquals(resultDifferencesOfPrudentials.getCredentials(), service.returnDifference(path1, path2));
    }

    @Test
    public void ServiceTestReturnDifferencesOfCredentialsList() throws IOException {
        String pathResultDifferencesOfCorruptedCredentials = "src/test/resources/ResultDifferencesOfCorruptedCredentials.json";

        ProfessionWithListOfObjects resultDifferencesOfCorruptedCredentials
                = new ObjectMapper().readValue(new File(pathResultDifferencesOfCorruptedCredentials), ProfessionWithListOfObjects.class);

        Assert.assertEquals(resultDifferencesOfCorruptedCredentials.getCredentials(), service.returnDifferenceOfCorruptedCredentialsList(path2, path3));
    }

    @Test
    public void ServiceTestReturnDifferencesOfCredentialsMap() throws IOException {
        String pathResultDifferencesOfCorruptedCredentials = "src/test/resources/ResultDifferencesCredentialsMap.json";

        Map resultDifferencesOfCredentialsMap
                = new ObjectMapper().readValue(new File(pathResultDifferencesOfCorruptedCredentials), Map.class);

        Assert.assertEquals(resultDifferencesOfCredentialsMap, service.returnDifferenceOfCredentialsMap(path5, path6));
    }

    @Test
    public void ServiceTestReturnNumberOfDifference() throws IOException {
        Assert.assertEquals(3, service.returnNumberOfDifference(path1, path2));
    }

    @Test
    public void ServiceTestReturnOfDifferencesOfCredentialsList() throws IOException {
        Assert.assertEquals(3, service.returnNumberOfDifferenceOfCorruptedCredentialsList(path2, path3));
    }

    @Test
    public void ServiceTestReturnNumberOfDifferenceOfCredentialsMap() throws IOException {
        Assert.assertEquals(5, service.returnNumberOfDifferenceOfCredentialsMap(path5, path6));
    }

}

