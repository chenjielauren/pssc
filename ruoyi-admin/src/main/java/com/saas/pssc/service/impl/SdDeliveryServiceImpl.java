package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.mapper.SdDeliveryMapper;
import com.saas.pssc.service.ISdDeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发货信息Service业务层处理
 * 
 * @author admin
 * @date 2021-08-01
 */
@Service
public class SdDeliveryServiceImpl implements ISdDeliveryService 
{
    @Autowired
    private SdDeliveryMapper sdDeliveryMapper;

    /**
     * 查询发货信息
     * 
     * @param id 发货信息ID
     * @return 发货信息
     */
    @Override
    public SdDelivery selectSdDeliveryById(Long id)
    {
        return sdDeliveryMapper.selectSdDeliveryById(id);
    }

    /**
     * 查询发货信息列表
     * 
     * @param sdDelivery 发货信息
     * @return 发货信息
     */
    @Override
    @DataScope(userAlias = "su")
    public List<SdDelivery> selectSdDeliveryList(SdDelivery sdDelivery)
    {
        return sdDeliveryMapper.selectSdDeliveryList(sdDelivery);
    }

    /**
     * 新增发货信息
     * 
     * @param sdDelivery 发货信息
     * @return 结果
     */
    @Override
    public int insertSdDelivery(SdDelivery sdDelivery)
    {
        sdDelivery.setCreateTime(DateUtils.getNowDate());
        return sdDeliveryMapper.insertSdDelivery(sdDelivery);
    }

    /**
     * 修改发货信息
     * 
     * @param sdDelivery 发货信息
     * @return 结果
     */
    @Override
    public int updateSdDelivery(SdDelivery sdDelivery)
    {
        sdDelivery.setUpdateTime(DateUtils.getNowDate());
        return sdDeliveryMapper.updateSdDelivery(sdDelivery);
    }

    /**
     * 删除发货信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSdDeliveryByIds(String ids)
    {
        return sdDeliveryMapper.updateSdDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发货信息信息
     * 
     * @param id 发货信息ID
     * @return 结果
     */
    @Override
    public int deleteSdDeliveryById(Long id)
    {
        return sdDeliveryMapper.updateSdDeliveryById(id);
    }
}