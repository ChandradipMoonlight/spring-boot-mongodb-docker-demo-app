package com.moonlight.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HDFCQuestions {
    public String QuestionId;
    public String QuestionText;
    public List<Options> Options;

    @ToString
    public static class Options {
        public String OptionId;
        public String OptionText;
        public Options(final String optionId, final String optionText){
            this.OptionId =optionId;
            this.OptionText = optionText;

        }
        public Options() {}
    }
}
