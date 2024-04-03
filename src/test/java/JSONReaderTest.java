import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertThrows;

public class JSONReaderTest {

    private final String projectRoot = System.getProperty("user.dir");
    private final JSONReader jsonReader = new JSONReader();

    @Test
    public void whenNoFieldResource_thenTrue() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONNoFieldResource.json")));
        Assert.assertTrue(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenFieldResourceValueIsNull_thenTrue() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONFieldResourceNullValue.json")));
        Assert.assertTrue(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenFieldResourceValueIsEmpty_thenTrue() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONFieldResourceEmptyValue.json")));
        Assert.assertTrue(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenFieldResourceOneAsteriskValue_thenFalse() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONFieldResourceOneAsteriskValue.json")));
        Assert.assertFalse(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenFieldResourceMoreThanOneAsteriskValue_thenTrue() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONFieldResourceMoreThanOneAsteriskValue.json")));
        Assert.assertTrue(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenFieldResourceZeroAsterisksValue_thenTrue() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONFieldResourceZeroAsterisksValue.json")));
        Assert.assertTrue(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenEmptyJSON_thenTrue() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONEmptyJSON.json")));
        Assert.assertTrue(jsonReader.isSingleAsterisk(jsonObject));
    }

    @Test
    public void whenEmptyFile_thenThrowsException() {
        assertThrows(org.json.JSONException.class, () -> {
            new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONEmptyFile.json")));
        });
    }

    @Test
    public void whenFileNotFound_thenThrowsException() {
        assertThrows(java.io.FileNotFoundException.class, () -> {
            new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONFileNotFound.json")));
        });
    }

    @Test
    public void whenWrongFormatOfJSON_thenThrowsException() {
        assertThrows(org.json.JSONException.class, () -> {
            new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/test/resources/JSONWrongFormatOfJSON.json")));
        });
    }

    @Test
    public void whenNullArgumentInIsSingleAsterisk_thenThrowsException() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            Assert.assertTrue(jsonReader.isSingleAsterisk(null));
        });
    }
}
