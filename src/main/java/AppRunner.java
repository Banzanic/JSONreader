import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class AppRunner {
    public static void main(String[] args) throws FileNotFoundException {
        String projectRoot = System.getProperty("user.dir");
        JSONReader jsonReader = new JSONReader();
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(projectRoot + "/src/main/resources/JSONExample.json")));
        System.out.println(jsonReader.isSingleAsterisk(jsonObject));
    }
}
