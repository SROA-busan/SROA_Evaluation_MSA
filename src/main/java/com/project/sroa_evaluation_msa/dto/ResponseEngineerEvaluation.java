package com.project.sroa_evaluation_msa.dto;

import lombok.Getter;

@Getter
public class ResponseEngineerEvaluation {
    private String classfyName;
    private String content;
    private Integer score;
    private String writeDate;

    public ResponseEngineerEvaluation(String classfyName, String content, Integer score, String writeDate) {
        this.classfyName = classfyName;
        this.content = content;
        this.score = score;
        this.writeDate = writeDate;
    }
}
