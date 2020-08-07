package com.myproject.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 *
 * @author Hawk
 * @date 2020/4/15
 */
public class ExceptionUtil {

    /**
     * 获取异常详情
     *
     * @param throwable
     * @return
     */
    public static String getExceptionDetail(Throwable throwable) {
        StringWriter result = new StringWriter();
        PrintWriter pw = new PrintWriter(result);
        try {
            throwable.printStackTrace(pw);
        } finally {
            pw.close();
        }
        return result.toString();
    }

}
