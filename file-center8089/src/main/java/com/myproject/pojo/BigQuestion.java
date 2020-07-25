package com.myproject.pojo;

import lombok.Data;

import java.util.List;

/**
 * 试卷题目
 * @author shuyi
 * @date 2020/7/24
 */
@Data
public class BigQuestion {
    private String  bigquestionname;
    List<Exampaperquestion> questions;
}
