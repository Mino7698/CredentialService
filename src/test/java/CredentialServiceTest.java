import com.fasterxml.jackson.databind.ObjectMapper;
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
    String path1 = "src/test/resources/SourceCredentialsMap.json";
    String path2 = "src/test/resources/ChangedCredentialsMap.json";

    @Test
    public void ServiceTestReturnDifferencesOfCredentialsMap() throws IOException {
        String pathResultDifferencesOfCorruptedCredentials = "src/test/resources/ResultDifferencesCredentialsMap.json";

        Map resultDifferencesOfCredentialsMap
                = new ObjectMapper().readValue(new File(pathResultDifferencesOfCorruptedCredentials), Map.class);

        Assert.assertEquals(resultDifferencesOfCredentialsMap, service.returnDifferenceOfCredentialsMap(path1, path2));
    }

    @Test
    public void ServiceTestReturnNumberOfDifferenceOfCredentialsMap() throws IOException {
        Assert.assertEquals(4, service.returnNumberOfDifferenceOfCredentialsMap(path1, path2));
    }

}

