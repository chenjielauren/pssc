package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.domain.SdDeliveryDetail;

/**
 * 发货信息Mapper接口
 * 
 * @author admin
 * @date 2021-08-01
 */
public interface SdDeliveryMapper 
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
     * 删除发货信息
     * 
     * @param id 发货信息ID
     * @return 结果
     */
    public int deleteSdDeliveryById(String id);

    /**
     * 批量删除发货信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSdDeliveryByIds(String[] ids);

    /**
     * 批量失效发货信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int updateSdDeliveryByIds(String[] strArray);

    /**
     * 失效发货信息
     * 
     * @param id 发货信息ID
     * @return 结果
     */
	public int updateSdDeliveryById(String id);

	public void batchSdDeliveryDetail(List<SdDeliveryDetail> sdDeliveryDetails);

	public void deleteSdDeliveryDetailByMainId(String id);
}
