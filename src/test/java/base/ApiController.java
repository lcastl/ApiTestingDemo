package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import utils.LogMessages;
import utils.WebServicesConsumer;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ApiController extends WebServicesConsumer {
    String ERROR_JSON_PATH = "Error Json doesn't match, value expected was %s but the obtained was %s";

    public void singleEntryMapString() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        try (FileReader reader = new FileReader("src\\test\\resources\\validate.json")){
            map = mapper.readValue(reader , Map.class);

        } catch (Exception e) {
            e.printStackTrace();
        }/*
        Map<String, String> filteredMap =
                map.entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().startsWith("s"))
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue)
                        );
        System.out.println(filteredMap);
        */
        for (Map.Entry<String, String> field : map.entrySet()) {
            try {
                singleEntryContainsString(field);
            } catch (AssertionError e) {
                callError(field.getKey(), e.getMessage());
            }
        }
    }

    public void callError(String key, String message) {
        LogMessages.setError(message);
        Assert.fail(String.format(ERROR_JSON_PATH,key,message));
    }
}
