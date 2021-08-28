package com.saas.pssc.service;

import java.util.List;

import com.saas.pssc.domain.SdDelivery;

/**
 * 发货信息Service接口
 * 
 * @author admin
 * @date 2021-08-01
 */
public interface ISdDeliveryService 
{
    /**
     * 查询发货信息
     * 
     * @param id 发货信息ID
     * @return 发货信息
     */
    public SdDelivery selectSdDeliveryById(String id);

    /**
     * 查询发货信息列表
     * 
     * @param sdDelivery 发货信息
     * @return 发货信息集合
     */
    public List<SdDelivery> selectSdDeliveryList(SdDelivery sdDelivery);

    /**
     * 新增发货信息
     * 
     * @param sdDelivery 发货信息
     * @return 结果
     */
    public int insertSdDelivery(SdDelivery sdDelivery);

    /**
     * 修改发货信息
     * 
     * @param sdDelivery 发货信息
     * @return 结果
     */
    public int updateSdDelivery(SdDelivery sdDelivery);

    /**
     * 批量删除发货信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSdDeliveryByIds(String ids);

    /**
     * 删除发货信息信息
     * 
     * @param id 发货信息ID
     * @return 结果
     */
    public int deleteSdDeliveryById(String id);

	public void batchSdDeliveryDetail(SdDelivery sdDelivery);

}
