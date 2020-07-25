package com.myproject.pojo;


import lombok.Data;

import java.util.List;

/**
 * @author shuyi
 * @date 2020/7/24
 */
@Data
public class Exampaperquestion {
    private String questionsequence;
    private String questioncontent;

    List<Exampaperoption> options;


}
