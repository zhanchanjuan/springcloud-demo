package com.myproject.util;


import com.myproject.domain.CommonPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *分页类
 */
public class PageUtil {

    /**
     * 获取pageable
     * @param commonPageReq
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Pageable getPageable(CommonPage<T> commonPageReq, Class<T> clazz) {
        if (null == commonPageReq.getData()) {
            try {
                commonPageReq.setData(clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Pageable pageable = null;
        if (StringUtils.isNotBlank(commonPageReq.getSortName())) {
            Sort sort = null;
            if (commonPageReq.getSortType().toUpperCase().equals(Sort.DEFAULT_DIRECTION.toString())) {
                sort = Sort.by(commonPageReq.getSortName());
            } else {
                sort = Sort.by(Sort.Direction.DESC, commonPageReq.getSortName());
            }
            pageable = PageRequest.of(commonPageReq.getPageNo(), commonPageReq.getPageSize(), sort);
        } else {
            pageable = PageRequest.of(commonPageReq.getPageNo(), commonPageReq.getPageSize());
        }
        return pageable;
    }

}
