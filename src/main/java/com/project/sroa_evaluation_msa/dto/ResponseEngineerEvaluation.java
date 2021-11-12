package com.project.sroa_evaluation_msa.dto;

import lombok.Getter;

@Getter
public class ResponseEngineerEvaluation {
    private String classifyName;
    private String content;
    private Integer score;
    private String writeDate;

    public ResponseEngineerEvaluation(String classifyName, String content, Integer score, String writeDate) {
        this.classifyName = classifyName;
        this.content = content;
        this.score = score;
        this.writeDate = writeDate;
    }
}
