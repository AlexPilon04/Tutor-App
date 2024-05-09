package persistence;


import org.json.JSONObject;

//Code influenced by the JsonSerizalizationDemo https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar//Code
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}