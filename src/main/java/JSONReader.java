import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    public List<String> getValuesInObject(JSONObject jsonObject, String key) {
        List<String> accumulatedValues = new ArrayList<>();
        for (String currentKey : jsonObject.keySet()) {
            Object value = jsonObject.get(currentKey);
            if (currentKey.equals(key)) {
                accumulatedValues.add(value.toString());
            }

            if (value instanceof JSONObject) {
                accumulatedValues.addAll(getValuesInObject((JSONObject) value, key));
            } else if (value instanceof JSONArray) {
                accumulatedValues.addAll(getValuesInArray((JSONArray) value, key));
            }
        }

        return accumulatedValues;
    }

    public List<String> getValuesInArray(JSONArray jsonArray, String key) {
        List<String> accumulatedValues = new ArrayList<>();
        for (Object obj : jsonArray) {
            if (obj instanceof JSONArray) {
                accumulatedValues.addAll(getValuesInArray((JSONArray) obj, key));
            } else if (obj instanceof JSONObject) {
                accumulatedValues.addAll(getValuesInObject((JSONObject) obj, key));
            }
        }

        return accumulatedValues;
    }

    public boolean isSingleAsterisk(JSONObject jsonObject) {
        //I am not sure if this section should throw an exception(since that would not be a AWS::IAM::Role::Policy) or simply true
        if (!jsonObject.has("PolicyDocument")) {
            return true;
        }
        if (!jsonObject.getJSONObject("PolicyDocument").has("Statement")) {
            return true;
        }
        JSONArray statements = jsonObject.getJSONObject("PolicyDocument").getJSONArray("Statement");
        for (Object statement : statements) {
            if (getValuesInObject((JSONObject) statement, "Resource").toString().equals("[*]")) {
                return false;
            }
        }
        return true;
    }
}
