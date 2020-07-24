package com.myproject.pojo;


import lombok.Data;

import java.util.List;

/**
 * PDF数据源
 * @author shuyi
 * @date 2020/7/24
 */
@Data
public class Exampaper {
    private String title;
    List<BigQuestion> bigQuestions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BigQuestion> getBigQuestions() {
        return bigQuestions;
    }

    public void setBigQuestions(List<BigQuestion> bigQuestions) {
        this.bigQuestions = bigQuestions;
    }

//
//    public List<Bigquestion> BigQuestions() {
//        return List<Bigquestion>;
//    }
}
