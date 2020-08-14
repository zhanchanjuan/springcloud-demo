package com.myproject.service.impl;

import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.myproject.common.exception.ProjectException;
import com.myproject.config.AliPaySystemConfig;
import com.myproject.domain.alipay.AlipayConfig;
import com.myproject.repository.AliPayRepository;
import com.myproject.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author shuyi
 * @date 2020/8/12
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "payment-center:alipay")
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AliPayRepository aliPayRepository;

    @Autowired
    private AliPaySystemConfig aliPaySystemConfig;


    /**
     * 添加支付宝支付 配置
     * @param config 配置类
     * @return 返回配置结果
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
    public AlipayConfig addConfig(AlipayConfig config) {
        Date current =new Date();
        config.setCreateTime(current);
        config.setUpdateTime(current);
        return  aliPayRepository.save(config);
    }

    /**
     * 删除支付宝配置信息
     * @param id 配置id
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
    @Modifying
    @CacheEvict(key="'config:'+#id")
    public void deleteConfig(Long id) {
        Optional<AlipayConfig> optional=aliPayRepository.findById(id);
        if(!optional.isPresent()){
            throw new ProjectException("未查询到相关配置信息");
        }
        aliPayRepository.deleteById(id);
    }

    /**
     * 修改支付宝配置信息
     * @param alipayConfig 支付宝配置
     * @return 配置修改结果
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
    @Modifying
    @CachePut(key = "'config:'+#alipayConfig.getId()")
    public AlipayConfig updateConfig(AlipayConfig alipayConfig) {
        alipayConfig.setUpdateTime(new Date());
        return aliPayRepository.save(alipayConfig);
    }

    /**
     * 通过id查询配置信息
     * @param id 配置id
     * @return 配置信息
     */
    @Override
    @Cacheable("'config:'+id")
    public AlipayConfig findConfigById(Long id) {
        Optional<AlipayConfig> optional=aliPayRepository.findById(id);
        if(!optional.isPresent()){
            throw new ProjectException("未查询到相关配置");
        }
        return optional.get();
    }

    /**
     * 查询默认的配置
     * @return
     */
    @Override
    @Cacheable(key = "'config:'+#root.target.getDefaultConfigId()")
    public AlipayConfig getDefaultConfig() {
        return findConfigById(getDefaultConfigId());
    }

    /**
     * 获取支付宝配置列表信息
     * @param config
     * @return
     */
    @Override
    public List<AlipayConfig> getConfigList(AlipayConfig config) {
        if(null==config){
            config=new AlipayConfig();
        }

        return null;
    }

    @Override
    public String getPcPayUrl(AlipayConfig alipay, AlipayTradePagePayModel dto) {
        return null;
    }

    @Override
    public String getWapPayUrl(AlipayConfig alipay, AlipayTradeWapPayModel wapPayDto) {
        return null;
    }

    @Override
    public String getAppOrderInfo(AlipayConfig alipay, AlipayTradePagePayModel dto) {
        return null;
    }

    @Override
    public void closeTrade(AlipayConfig alipayConfig, String outTradeNo) {

    }

    @Override
    public void refund(AlipayConfig alipayConfig, String outTradeNo, Double refundAmount) {

    }

    @Override
    public void execNotify(HttpServletRequest request, AlipayConfig alipayConfig) {

    }

    @Override
    public String execPaySuccessReturn(HttpServletRequest request, AlipayConfig alipayConfig) {
        return null;
    }

    /**
     * 公共方法
     */
     public Long getDefaultConfigId(){
         return aliPaySystemConfig.getDefaultConfigId();
     }

}
