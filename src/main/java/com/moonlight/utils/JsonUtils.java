package com.moonlight.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonlight.collections.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class JsonUtils {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T jsonToJava(JsonNode jsonNode, Class<T> tClass) {
        T object = null;
        try {
            object = OBJECT_MAPPER.treeToValue(jsonNode, tClass);
        } catch (Exception e) {
            log.info("Method[jsonToJava] Error converting json to java Object value! : {}", e);
        }
        return object;
    }

    public static JsonNode javaToJson(Object object) {
        JsonNode jsonNode = null;
        try {
            jsonNode = OBJECT_MAPPER.convertValue(object, JsonNode.class);
        }catch (Exception e) {
            log.info("Method[javaToJson] Error converting java object to jsonNode : {}", e);
        }
        return jsonNode;
    }

    public static String javaToJsonString(Object object) {
        String jsonString = null;
        try {
            jsonString = OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.info("Method[javaToJsonString] Error converting java object to jsonString : {}", e);
        }
        return jsonString;
    }

    public static <T> T jsonStringToJava(String jsonString, Class<T> tClass) {
        T object = null;
        try {
            object = OBJECT_MAPPER.readValue(jsonString, tClass);
        } catch (Exception e) {
            log.info("Method[jsonStringToJava] Error converting jsonString to Java Object : {}", e);
        }
        return object;
    }

    public static String javaListToJsonArray(List<Object> tList) {
        String jsonStr = null;
        try {
            jsonStr = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(tList);
        } catch (Exception e) {

        }
        return jsonStr;
    }
    public static void main(String[] args) {
        Address address = new Address("add1", "city1", "state1", 123445);
        List<Address> addressList = new ArrayList<>();

        addressList.add(address);
        addressList.add(new Address("add2", "city2", "state2", 24465));
        System.out.println(javaListToJsonArray(Collections.singletonList(addressList)));
    }
}
