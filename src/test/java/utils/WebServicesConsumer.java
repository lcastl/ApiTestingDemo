package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.junit.Assert;
import java.util.Collection;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class WebServicesConsumer {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request = given();
    public String endpoint;
    private JsonObject requestBody = new JsonObject();
    private JsonArray requestBodyArray = new JsonArray();
    LogMessages logger = new LogMessages();
    public void setEndpoint(String proEndPoint) {
        this.endpoint = proEndPoint;
        logger.setEndPoint(endpoint);
    }

    public void setParams(String tag, Object value) {
        request.param(tag, value);
    }

    public void setParamsWithPost(String tag, Object value) {
        request.queryParam(tag, value);
    }

    public void setParamsWhenBody(String tag, Object value) {
        request.queryParam(tag, value);
    }

    public void setHeader(String tag, Object value) {
        request.header(tag, value);
        logger.setHeaders(tag, value.toString());
    }

    public void consumeRestGet() {
        response = request.when().get(endpoint);
        logger.setRequestGet();
    }

    public void consumeRestPost() {
        if (requestBody.size() != 0) {
            request.body(requestBody.toString());
            System.out.println(requestBody.toString());
        } else if (requestBodyArray.size() != 0) {
            request.body(requestBodyArray.toString());
            System.out.println(requestBodyArray.toString());
        }
        logger.setBody(requestBody.toString());
        response = request.when().post(endpoint);
        logger.setRequestPost();
    }

    public void consumeFreeRestPost(String body) {
        request.body(body);
        System.out.println(body);
        response = request.when().post(endpoint);
    }

    public void consumeRestUpdate() {
        if (requestBody.size() != 0) {
            request.body(requestBody.toString());
            System.out.println(requestBody.toString());
        } else if (requestBodyArray.size() != 0) {
            request.body(requestBodyArray.toString());
            System.out.println(requestBodyArray.toString());
        }
        response = request.when().patch(endpoint);
    }

    public void consumeRestPostUsingJson(String body) {
        request.body(body);
        System.out.println(body);
        response = request.when().post(endpoint);
    }

    public void obtainResponse() {
        //response.prettyPrint();
        json = response.then();
        logger.setResponse(response.asString());
    }

    public void setBodyToPostString(String tag, Object value) {
        switch (value.getClass().toString()) {
            case "class java.lang.String":
                requestBody.addProperty(tag, String.valueOf(value));
                break;
            case "class java.lang.Integer":
                requestBody.addProperty(tag, (Integer) value);
                break;
            case "class com.google.gson.JsonArray":
                if (tag.isEmpty() || tag == null) {
                    requestBodyArray = (JsonArray) value;
                } else {
                    requestBody.add(tag, (JsonArray) value);
                }
                break;
        }
    }

    public void setBodyToPostObject(String tag, JsonObject value) {
        requestBody.add(tag, value);
    }

    public void setBodyToPostObject(String tag, JsonArray value) {
        requestBody.add(tag, value);
    }

    public void setFormatDataToPostString(String tag, String value) {
        request.multiPart(tag, value);
    }

    public JsonArray setJsonArray(JsonArray object, JsonObject value) {
        try {
            object.add(value);
            return object;
        } catch (NullPointerException E) {
            JsonArray array = new JsonArray();
            array.add(value);
            return array;
        }
    }

    public void responseStatusShouldBe(int status) {
        try {
            json.assertThat().statusCode(status);
            logger.setStatus(status);
        } catch (Exception e) {
            logger.setStatus(status, "ERROR");
            Assert.fail("the status response wasn't expected " + status);
        }
    }

    public void singleEntryContains(Map.Entry<String, String> field) {
        if (StringUtils.isNumeric(field.getValue())) {
            json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
        } else if (field.getValue().contains("null")) {
            json.body(field.getKey(), equalTo((Collection<Matcher<? super Object>>) null));
        } else if (field.getValue().contains("true") || field.getValue().contains("false")) {
            json.body(field.getKey(), equalTo(Boolean.valueOf(field.getValue())));
        } else {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    public void singleEntryContainsString(Map.Entry<String, String> field) {
       json.body(field.getKey(), equalTo(field.getValue()));
    }
    public void arrayEntryContainsDouble(Map.Entry<String, String> field) {
        json.body(field.getKey(), hasItem(Double.parseDouble(field.getValue())));
    }
    public void singleEntryMayContains(Map.Entry<String, String> field) {
        json.body(field.getKey(), containsString(field.getValue()));
    }

    public void arrayEntryNoContains(Map.Entry<String, String> field) {
        json.body(field.getKey(), not(hasItem(field.getValue())));
    }

    public void arrayEntryCount(Map.Entry<String, String> field) {
        json.body(field.getKey() + ".size()", equalTo(Integer.parseInt(field.getValue())));
    }
}
