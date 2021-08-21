package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.SdDeliveryDetail;

/**
 * 发货单标注明细Service接口
 * 
 * @author admin
 * @date 2021-08-18
 */
public interface ISdDeliveryDetailService 
{
    /**
     * 查询发货单标注明细
     * 
     * @param id 发货单标注明细ID
     * @return 发货单标注明细
     */
    public SdDeliveryDetail selectSdDeliveryDetailById(String id);

    /**
     * 查询发货单标注明细列表
     * 
     * @param sdDeliveryDetail 发货单标注明细
     * @return 发货单标注明细集合
     */
    public List<SdDeliveryDetail> selectSdDeliveryDetailList(SdDeliveryDetail sdDeliveryDetail);

    /**
     * 新增发货单标注明细
     * 
     * @param sdDeliveryDetail 发货单标注明细
     * @return 结果
     */
    public int insertSdDeliveryDetail(SdDeliveryDetail sdDeliveryDetail);

    /**
     * 修改发货单标注明细
     * 
     * @param sdDeliveryDetail 发货单标注明细
     * @return 结果
     */
    public int updateSdDeliveryDetail(SdDeliveryDetail sdDeliveryDetail);

    /**
     * 批量删除发货单标注明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSdDeliveryDetailByIds(String ids);

    /**
     * 删除发货单标注明细信息
     * 
     * @param id 发货单标注明细ID
     * @return 结果
     */
    public int deleteSdDeliveryDetailById(String id);
}
