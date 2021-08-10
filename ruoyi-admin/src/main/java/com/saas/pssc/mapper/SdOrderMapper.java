package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.SdOrder;

/**
 * 订单信息Mapper接口
 * 
 * @author admin
 * @date 2021-07-22
 */
public interface SdOrderMapper 
{
    /**
     * 查询订单信息
     * 
     * @param id 订单信息ID
     * @return 订单信息
     */
    public SdOrder selectSdOrderById(String id);

    /**
     * 查询订单信息列表
     * 
     * @param sdOrder 订单信息
     * @return 订单信息集合
     */
    public List<SdOrder> selectSdOrderList(SdOrder sdOrder);

    /**
     * 新增订单信息
     * 
     * @param sdOrder 订单信息
     * @return 结果
     */
    public int insertSdOrder(SdOrder sdOrder);

    /**
     * 修改订单信息
     * 
     * @param sdOrder 订单信息
     * @return 结果
     */
    public int updateSdOrder(SdOrder sdOrder);

     /**
     * 失效订单信息
     * 
     * @param id 订单信息ID
     * @return 结果
     */
    public int updateSdOrderById(String id);

    /**
     * 批量失效订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateSdOrderByIds(String[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单信息ID
     * @return 结果
     */
    public int deleteSdOrderById(String id);

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSdOrderByIds(String[] ids);
}
