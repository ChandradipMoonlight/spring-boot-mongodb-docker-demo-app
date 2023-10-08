package com.moonlight.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import springfox.documentation.spring.web.json.Json;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Slf4j
public class Test {


    private static String getJsonStr() {
        String jsonStr = "{\n" +
                "  \"verticalCode\": \"Property\",\n" +
                "  \"insuranceName\": \"TAGIC\",\n" +
                "  \"customerName\": \"abc pqr\",\n" +
                "  \"insurerCode\": \"TATA\",\n" +
                "  \"planName\": \"ANTYODAYA SHRAMIK SURAKSHA YOJANA\",\n" +
                "  \"insurerName\": \"TATA\",\n" +
                "  \"partnerId\": \"5ff55f0706cf200001455fa5\",\n" +
                "  \"tenantUniqueCode\": \"1069712\",\n" +
                "  \"uniquePaymentId\": \"AH1HXASB5DS\",\n" +
                "  \"requestId\": \"AH1HXAQP2QB\",\n" +
                "  \"totalAmount\": \"499\",\n" +
                "  \"baseAmount\": \"423\",\n" +
                "  \"gst\": \"76\",\n" +
                "  \"broker\": \"ippb\",\n" +
                "  \"tenant\": \"ippb\",\n" +
                "  \"autoDebitReqd\": \"true\",\n" +
                "  \"autoDebitFixedAmount\": \"499\",\n" +
                "  \"autoDebitStartDate\": \"23-Jul-2024\",\n" +
                "  \"autoDebitEndDate\": \"22-Jul-2025\"\n" +
                "}";
        return jsonStr;
    }




    public static boolean isPerfectNumberByJava8(long dividend) {
//        return LongStream.rangeClosed(2, number/2)
//                .filter(num -> number % num == 0)
//                .sum()==number-1;
        return LongStream.rangeClosed(2, (long) Math.sqrt(dividend))
                .reduce(1, (sum, divisor) -> dividend % divisor == 0 ? sum + divisor + dividend/divisor : sum) == dividend;
    }

    public static List<Long> getListOfPerfectNumberFrom1ToN(long n) {
        if (n<6) {
            return Collections.emptyList();
        }
        return LongStream.rangeClosed(6, n)
                .parallel()
                .filter(number -> isPerfectNumberByJava8(number))
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean isPerfectNumberByJava7(long number) {
        System.out.println("using java 7");
        boolean check = false;
        int sum = 1;
        for (int i=2; i<=number/2; i++) {
            if (number%i == 0) {
                sum += i;
            }
        }
        if (sum == number) {
            check = true;
        }
        return check;
    }


    public static void main(String[] args) {
        print().subscribe();
    }
    private static String extractValue(String jsonString, String key) {
        String keyWithQuotes = key;
        int startIndex = jsonString.indexOf(keyWithQuotes) + keyWithQuotes.length() + 1;
        int endIndex = jsonString.indexOf("}", startIndex + 1);
        return jsonString.substring(startIndex, endIndex-1);
    }

    public static void getResponse() {
        String rowJson = "{\"Errors\":[{\"ErrorCode\":\"BPT33204\",\"ErrorMessage\":\"{\"Data\":{\"TransactionStatus\":{\"IsOverriden\":\"false\",\"ReplyCode\":\"90\",\"SpReturnValue\":\"100\",\"ReplyText\":\" Server Rejected Request. \",\"Memo\":\"\",\"ExternalReferenceNo\":\"9ADjc\",\"IsServiceChargeApplied\":\"false\",\"ExtendedReply\":{\"Messages\":[{\"Code\":{\"Mutable\":false,\"EnumValue\":\"86\",\"Value\":\"86\"},\"Message\":\" Insufficient Balance. \"}]},\"FCYHangeHandlingApplied\":false}}}\"}]}";
       rowJson = rowJson.replace("\\","");
//       rowJson = rowJson.replace("\"{","{");
        rowJson = rowJson.replace("}\"","}");
        rowJson = rowJson.replace("\"{","{");
        System.out.println("Response : "+rowJson);
//        JsonNode jsonNode = JsonUtils.javaToJson(rowJson);
        JsonNode reponse = JsonUtils.jsonStringToJava(rowJson, JsonNode.class);
        System.out.println(reponse);

    }

    public static Flux<String> getData() {
        return Flux.just("Dipak", "swapnil");
    }

    public static Flux<Integer> print() {
        Flux<Integer> lengths = getData().map(data -> {
           log.info("data : {}", data);
            return data.length();
        });
        return lengths;

    }
}
