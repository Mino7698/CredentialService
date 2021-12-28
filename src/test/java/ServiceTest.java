import com.fasterxml.jackson.databind.ObjectMapper;
import model.Profession;
import model.ProfessionWithListOfObjects;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.CredentialService;


@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    CredentialService service = new CredentialService();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void ServiceReturnDifferenceTest() throws IOException {
        String path1 = "src/test/java/resources/Сredentials.json";
        String path2 = "src/test/java/resources/AnotherСredentials.json";
        String ResultOfDifferencesOfCredentials = "src/test/java/resources/ResultDifeerencesOfСredentials.json";

        Profession resultDifferencesOfPrudentials
                = new ObjectMapper().readValue(new File(ResultOfDifferencesOfCredentials), Profession.class);

        Assert.assertEquals(resultDifferencesOfPrudentials.getCredentials(),service.returnDifference(path1,path2));
    }

    @Test
    public void ServiceTestReturnDifferencesOfCredentialsList() throws IOException {
        String path2 = "src/test/java/resources/AnotherСredentials.json";
        String path3 = "src/test/java/resources/CorruptedСredentials.json";
        String pathResultDifferencesOfCorruptedCredentials = "src/test/java/resources/ResultDifferencesOfCorruptedCredentials.json";

        ProfessionWithListOfObjects resultDifferencesOfCorruptedCredentials
                = new ObjectMapper().readValue(new File(pathResultDifferencesOfCorruptedCredentials),  ProfessionWithListOfObjects.class);

        Assert.assertEquals(resultDifferencesOfCorruptedCredentials.getCredentials(), service.returnDifferenceOfCorruptedCredentialsList(path2,path3));
    }

    @Test
    public void ServiceTestReturnNumberOfDifference() throws IOException {
        String path1 = "src/test/java/resources/Сredentials.json";
        String path2 = "src/test/java/resources/AnotherСredentials.json";

        Assert.assertEquals(3, service.returnNumberOfDifference(path1,path2));
    }

    @Test
    public void ServiceTestReturnOfDifferencesOfCredentialsList() throws IOException {
        String path2 = "src/test/java/resources/AnotherСredentials.json";
        String path3 = "src/test/java/resources/CorruptedСredentials.json";

        Assert.assertEquals(3, service.returnNumberOfDifferenceOfCorruptedCredentialsList(path2,path3));
    }

}

