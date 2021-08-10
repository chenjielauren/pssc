package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.SdOrder;
import com.saas.pssc.mapper.SdOrderMapper;
import com.saas.pssc.service.ISdOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单信息Service业务层处理
 * 
 * @author admin
 * @date 2021-07-22
 */
@Service
public class SdOrderServiceImpl implements ISdOrderService 
{
    @Autowired
    private SdOrderMapper sdOrderMapper;

    /**
     * 查询订单信息
     * 
     * @param id 订单信息ID
     * @return 订单信息
     */
    @Override
    public SdOrder selectSdOrderById(String id)
    {
        return sdOrderMapper.selectSdOrderById(id);
    }

    /**
     * 查询订单信息列表
     * 
     * @param sdOrder 订单信息
     * @return 订单信息
     */
    @Override
    @DataScope(userAlias = "su")
    public List<SdOrder> selectSdOrderList(SdOrder sdOrder)
    {
        return sdOrderMapper.selectSdOrderList(sdOrder);
    }

    /**
     * 新增订单信息
     * 
     * @param sdOrder 订单信息
     * @return 结果
     */
    @Override
    public int insertSdOrder(SdOrder sdOrder)
    {
        sdOrder.setCreateTime(DateUtils.getNowDate());
        return sdOrderMapper.insertSdOrder(sdOrder);
    }

    /**
     * 修改订单信息
     * 
     * @param sdOrder 订单信息
     * @return 结果
     */
    @Override
    public int updateSdOrder(SdOrder sdOrder)
    {
        sdOrder.setUpdateTime(DateUtils.getNowDate());
        return sdOrderMapper.updateSdOrder(sdOrder);
    }

    /**
     * 删除订单信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSdOrderByIds(String ids)
    {
        return sdOrderMapper.updateSdOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息信息
     * 
     * @param id 订单信息ID
     * @return 结果
     */
    @Override
    public int deleteSdOrderById(String id)
    {
        return sdOrderMapper.updateSdOrderById(id);
    }
}
