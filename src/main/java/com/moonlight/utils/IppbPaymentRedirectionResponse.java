package com.moonlight.utils;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IppbPaymentRedirectionResponse {
    private String redirectionUrl;
    private String status;
    private String errorMessage;
    public IppbPaymentRedirectionResponse() {}

    public IppbPaymentRedirectionResponse(String jsonString) {
        this.redirectionUrl = extractValue(jsonString, "redirectionUrl");
        this.status = extractValue(jsonString, "status");
        if (jsonString.contains("errorMessage")) {
            this.errorMessage = extractValue(jsonString, errorMessage);
        }
    }

    private String extractValue(String jsonString, String key) {
        String keyWithQuotes = "\""+key+"\":\"";
        int startIndex = jsonString.indexOf(keyWithQuotes) + keyWithQuotes.length();
        int endIndex = jsonString.indexOf("\"", startIndex + 1);
        return jsonString.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {
        String str = "{\n  \"redirectionUrl\" : \"https://103.108.118.20/IPPBMicroATMServerUAT/resources/Appzillon?custId=5000003358&sessionID=20210720154723849&langId=en&uuid=500000335816267762438494610\",\n  \"status\" : \"success\"\n}";
        str = str.replaceAll("[\\n\\s]", "");
        System.out.println(str);
        IppbPaymentRedirectionResponse response = new IppbPaymentRedirectionResponse(str);
        System.out.println(response);
    }
}

