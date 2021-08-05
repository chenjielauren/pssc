package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsMatSopDetailMapper;
import com.saas.pssc.domain.BsMatSopDetail;
import com.saas.pssc.service.IBsMatSopDetailService;
import com.saas.common.core.text.Convert;

/**
 * 材料检验标准明细Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class BsMatSopDetailServiceImpl implements IBsMatSopDetailService 
{
    @Autowired
    private BsMatSopDetailMapper bsMatSopDetailMapper;

    /**
     * 查询材料检验标准明细
     * 
     * @param id 材料检验标准明细ID
     * @return 材料检验标准明细
     */
    @Override
    public BsMatSopDetail selectBsMatSopDetailById(String id)
    {
        return bsMatSopDetailMapper.selectBsMatSopDetailById(id);
    }

    /**
     * 查询材料检验标准明细列表
     * 
     * @param bsMatSopDetail 材料检验标准明细
     * @return 材料检验标准明细
     */
    @Override
    public List<BsMatSopDetail> selectBsMatSopDetailList(BsMatSopDetail bsMatSopDetail)
    {
        return bsMatSopDetailMapper.selectBsMatSopDetailList(bsMatSopDetail);
    }

    /**
     * 新增材料检验标准明细
     * 
     * @param bsMatSopDetail 材料检验标准明细
     * @return 结果
     */
    @Override
    public int insertBsMatSopDetail(BsMatSopDetail bsMatSopDetail)
    {
        bsMatSopDetail.setCreateTime(DateUtils.getNowDate());
        return bsMatSopDetailMapper.insertBsMatSopDetail(bsMatSopDetail);
    }

    /**
     * 修改材料检验标准明细
     * 
     * @param bsMatSopDetail 材料检验标准明细
     * @return 结果
     */
    @Override
    public int updateBsMatSopDetail(BsMatSopDetail bsMatSopDetail)
    {
        bsMatSopDetail.setUpdateTime(DateUtils.getNowDate());
        return bsMatSopDetailMapper.updateBsMatSopDetail(bsMatSopDetail);
    }

    /**
     * 删除材料检验标准明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsMatSopDetailByIds(String ids)
    {
        return bsMatSopDetailMapper.deleteBsMatSopDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除材料检验标准明细信息
     * 
     * @param id 材料检验标准明细ID
     * @return 结果
     */
    @Override
    public int deleteBsMatSopDetailById(String id)
    {
        return bsMatSopDetailMapper.deleteBsMatSopDetailById(id);
    }
}
