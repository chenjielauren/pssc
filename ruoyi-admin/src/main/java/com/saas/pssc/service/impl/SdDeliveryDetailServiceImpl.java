package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.SdDeliveryDetailMapper;
import com.saas.pssc.domain.SdDeliveryDetail;
import com.saas.pssc.service.ISdDeliveryDetailService;
import com.saas.common.core.text.Convert;

/**
 * 发货单标注明细Service业务层处理
 * 
 * @author admin
 * @date 2021-08-18
 */
@Service
public class SdDeliveryDetailServiceImpl implements ISdDeliveryDetailService 
{
    @Autowired
    private SdDeliveryDetailMapper sdDeliveryDetailMapper;

    /**
     * 查询发货单标注明细
     * 
     * @param id 发货单标注明细ID
     * @return 发货单标注明细
     */
    @Override
    public SdDeliveryDetail selectSdDeliveryDetailById(String id)
    {
        return sdDeliveryDetailMapper.selectSdDeliveryDetailById(id);
    }

    /**
     * 查询发货单标注明细列表
     * 
     * @param sdDeliveryDetail 发货单标注明细
     * @return 发货单标注明细
     */
    @Override
    public List<SdDeliveryDetail> selectSdDeliveryDetailList(SdDeliveryDetail sdDeliveryDetail)
    {
        return sdDeliveryDetailMapper.selectSdDeliveryDetailList(sdDeliveryDetail);
    }

    /**
     * 新增发货单标注明细
     * 
     * @param sdDeliveryDetail 发货单标注明细
     * @return 结果
     */
    @Override
    public int insertSdDeliveryDetail(SdDeliveryDetail sdDeliveryDetail)
    {
        sdDeliveryDetail.setCreateTime(DateUtils.getNowDate());
        return sdDeliveryDetailMapper.insertSdDeliveryDetail(sdDeliveryDetail);
    }

    /**
     * 修改发货单标注明细
     * 
     * @param sdDeliveryDetail 发货单标注明细
     * @return 结果
     */
    @Override
    public int updateSdDeliveryDetail(SdDeliveryDetail sdDeliveryDetail)
    {
        sdDeliveryDetail.setUpdateTime(DateUtils.getNowDate());
        return sdDeliveryDetailMapper.updateSdDeliveryDetail(sdDeliveryDetail);
    }

    /**
     * 删除发货单标注明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSdDeliveryDetailByIds(String ids)
    {
        return sdDeliveryDetailMapper.deleteSdDeliveryDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发货单标注明细信息
     * 
     * @param id 发货单标注明细ID
     * @return 结果
     */
    @Override
    public int deleteSdDeliveryDetailById(String id)
    {
        return sdDeliveryDetailMapper.deleteSdDeliveryDetailById(id);
    }
}
