package com.myproject.repository;

import com.myproject.domain.alipay.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shuyi
 * @date 2020/8/11
 */
public interface AliPayRepository extends JpaRepository<AlipayConfig, Long> {

}
