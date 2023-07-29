package com.moonlight.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

    }

}
