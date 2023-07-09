package com.moonlight.utils;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    private static List<Object> getExtraMedicalList(List<HDFCQuestions> hdfcQuestionList) {
        List<Object> extraMedicalList = new ArrayList<>();
        List<HDFCQuestions.Options> options = new ArrayList<>();
        Map<String, Object> idOneObj = new HashMap<>();
        hdfcQuestionList.forEach(question -> {
            Map<String, Object> questionMap = new HashMap<>();
            if (question.getQuestionId().startsWith("HHOS")) {
                options.add(new HDFCQuestions.Options(question.getQuestionId().replace("HHOS", ""), question.getQuestionText()));
            } else if (question.getQuestionId().equalsIgnoreCase("1")) {
                idOneObj.put("QuestionId", question.QuestionId);
                idOneObj.put("QuestionText", question.getQuestionText());
            } else {
                questionMap.put("QuestionId", question.QuestionId);
                questionMap.put("QuestionText", question.getQuestionText());
                questionMap.put("Options", question.getOptions());
                extraMedicalList.add(questionMap);
            }

        });
        idOneObj.put("Options", options);
        extraMedicalList.add(idOneObj);
        return extraMedicalList;
    }

    private static List<Object> getExtraMedicalQuestionsListForFamilyFloter(List<HDFCQuestions> hdfcQuestionList){
        List<Object> extraMedicalList = new ArrayList<>();
        List<HDFCQuestions.Options> options = new ArrayList<>();
        Map<String, Object> mapObjectForIdOne = new HashMap<>();
        hdfcQuestionList.forEach(question -> {
            Map<String, Object> questionMap = new HashMap<>();
            if (question.getQuestionId().startsWith("HHOS")) {
                options.add(new HDFCQuestions.Options(getId(question.getQuestionId().replace("HHOS", ""), 52), question.getQuestionText()));
            } else if (question.getQuestionId().equalsIgnoreCase("1")) {
                mapObjectForIdOne.put("QuestionId", getId(question.QuestionId, 13));
                mapObjectForIdOne.put("QuestionText", question.getQuestionText());
            } else {
                questionMap.put("QuestionId", getId(question.QuestionId, 13));
                questionMap.put("QuestionText", question.getQuestionText());
                List<HDFCQuestions.Options> optionList = question.Options.stream().map(option -> {
                    HDFCQuestions.Options options1 = new HDFCQuestions.Options(getId(option.OptionId, 52), option.OptionText);
                    return options1;
                }).collect(Collectors.toList());
                questionMap.put("Options", optionList);
                extraMedicalList.add(questionMap);
            }
        });
        mapObjectForIdOne.put("Options", options);
        extraMedicalList.add(mapObjectForIdOne);
        return extraMedicalList;
    }

    private static String getId(String strId, Integer incrementedValue) {
        Integer intId = Integer.valueOf(strId) + incrementedValue;
        return String.valueOf(intId);
    }


    private static String getJsonStr() {
        String jsonStr = "{\n" +
                "  \"hdfcQuestions\" : [\n" +
                "  {\n" +
                "    \"QuestionId\": 3,\n" +
                "    \"QuestionText\": \"Is the insured member on any regular medication?\",\n" +
                "    \"Options\": [{\n" +
                "      \"OptionId\" : \"19\",\n" +
                "      \"OptionText\" : \"ot_1\"\n" +
                "    },\n" +
                "      {\n" +
                "        \"OptionId\" : \"19\",\n" +
                "      \"OptionText\" : \"ot_2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS4\",\n" +
                "    \"QuestionText\": \"Chest Pain/ Heart Attack or any other Heart Disease/ Problem\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS5\",\n" +
                "    \"QuestionText\": \"Liver or Gall Bladder ailment/Jaundice/Hepatitis B or C\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": 5,\n" +
                "    \"QuestionText\": \"Is there any hospitalisation history of the insured member?\",\n" +
                "    \"Options\": [{\n" +
                "      \"OptionId\" : \"23\",\n" +
                "      \"OptionText\" : \"ot_1\"\n" +
                "    },\n" +
                "      {\n" +
                "        \"OptionId\" : \"23\",\n" +
                "      \"OptionText\" : \"ot_2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS6\",\n" +
                "    \"QuestionText\": \"Kidney ailment or Diseases of Reproductive organs\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS7\",\n" +
                "    \"QuestionText\": \"Tuberculosis/ Asthma or any other Lung disorder\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS8\",\n" +
                "    \"QuestionText\": \"Ulcer (Stomach/ Duodenal), or any ailment of Digestive System\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS9\",\n" +
                "    \"QuestionText\": \"Any Blood disorder (example Anaemia, Haemophilia, Thalassaemia) or any genetic disorder\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": 4,\n" +
                "    \"QuestionText\": \"Has the insured member undergone any tests or medical investigation?\",\n" +
                "    \"Options\": [{\n" +
                "      \"OptionId\" : \"21\",\n" +
                "      \"OptionText\" : \"ot_1\"\n" +
                "    },\n" +
                "      {\n" +
                "        \"OptionId\" : \"21\",\n" +
                "      \"OptionText\" : \"ot_2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS15\",\n" +
                "    \"QuestionText\": \"Arthritis, Spondylitis, Fracture or any other disorder of Muscle Bone/ Joint/ Ligament/ Cartilage\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": 1,\n" +
                "    \"QuestionText\": \"Does the insured member have any ailment/disability/deformity, due to accident or congenital disease ?\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS16\",\n" +
                "    \"QuestionText\": \"Any other disease/condition not mentioned above\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": 6,\n" +
                "    \"QuestionText\": \"Is the insured member expecting a baby/pregnant (only for females)\",\n" +
                "    \"Options\": [{\n" +
                "      \"OptionId\" : \"25\",\n" +
                "      \"OptionText\" : \"ot_1\"\n" +
                "    },\n" +
                "      {\n" +
                "        \"OptionId\" : \"25\",\n" +
                "      \"OptionText\" : \"ot_2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": 2,\n" +
                "    \"QuestionText\": \"Is there any surgery planned for the insured member ?\",\n" +
                "    \"Options\": [{\n" +
                "      \"OptionId\" : \"17\",\n" +
                "      \"OptionText\" : \"ot_1\"\n" +
                "    },\n" +
                "      {\n" +
                "        \"OptionId\" : \"17\",\n" +
                "      \"OptionText\" : \"ot_2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS1\",\n" +
                "    \"QuestionText\": \"Hypertension/ High blood pressure\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS3\",\n" +
                "    \"QuestionText\": \"Diabetes/ High blood sugar/Sugar in urine\",\n" +
                "    \"Options\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"QuestionId\": \"HHOS3\",\n" +
                "    \"QuestionText\": \"Cancer, Tumour\\n\"}]\n" +
                "}";
        return jsonStr;
    }
//    public static void main(String[] args) {
//        MyClass myClass = JsonUtils.jsonStringToJava(getJsonStr(), MyClass.class);
//        List<Object> extraMedicalList = getExtraMedicalQuestionsListForFamilyFloter(myClass.hdfcQuestions);
//        System.out.println(JsonUtils.javaListToJsonArray(extraMedicalList));
        double pi = Math.PI;
        String para = "India is my country, and I love to my country";
//        System.out.println(countWords(para));
//    }

    public static int countWords(String str) {
        return str.split(" ").length;
    }

    public static Map<Character, Integer> getDuplicateCharsAndTheirCount(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0; i<str.length(); i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key)+1);
            } else {
                map.put(key, 1);
            }
        }
        return map;
    }

    public static void printResultByJava_7() {
        String str = "nivruttee";
        for (Map.Entry<Character, Integer> entry : getDuplicateCharsAndTheirCount(str).entrySet()) {
            if (entry.getValue()>1) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
    }
    public static void printResultByJava_8() {
        String str = "nivruttee";
        getDuplicateCharsAndTheirCount(str).forEach((k, v) -> {
            if (v>1) {
                System.out.println(k + " = " + v);
            }
        });
    }

    public static void main(String[] args) {

    }

}
