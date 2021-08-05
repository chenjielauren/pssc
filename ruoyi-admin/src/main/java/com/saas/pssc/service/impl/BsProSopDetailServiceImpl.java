package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsProSopDetailMapper;
import com.saas.pssc.domain.BsProSopDetail;
import com.saas.pssc.service.IBsProSopDetailService;
import com.saas.common.core.text.Convert;

/**
 * 成品检验标准明细Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class BsProSopDetailServiceImpl implements IBsProSopDetailService 
{
    @Autowired
    private BsProSopDetailMapper bsProSopDetailMapper;

    /**
     * 查询成品检验标准明细
     * 
     * @param id 成品检验标准明细ID
     * @return 成品检验标准明细
     */
    @Override
    public BsProSopDetail selectBsProSopDetailById(String id)
    {
        return bsProSopDetailMapper.selectBsProSopDetailById(id);
    }

    /**
     * 查询成品检验标准明细列表
     * 
     * @param bsProSopDetail 成品检验标准明细
     * @return 成品检验标准明细
     */
    @Override
    public List<BsProSopDetail> selectBsProSopDetailList(BsProSopDetail bsProSopDetail)
    {
        return bsProSopDetailMapper.selectBsProSopDetailList(bsProSopDetail);
    }

    /**
     * 新增成品检验标准明细
     * 
     * @param bsProSopDetail 成品检验标准明细
     * @return 结果
     */
    @Override
    public int insertBsProSopDetail(BsProSopDetail bsProSopDetail)
    {
        bsProSopDetail.setCreateTime(DateUtils.getNowDate());
        return bsProSopDetailMapper.insertBsProSopDetail(bsProSopDetail);
    }

    /**
     * 修改成品检验标准明细
     * 
     * @param bsProSopDetail 成品检验标准明细
     * @return 结果
     */
    @Override
    public int updateBsProSopDetail(BsProSopDetail bsProSopDetail)
    {
        bsProSopDetail.setUpdateTime(DateUtils.getNowDate());
        return bsProSopDetailMapper.updateBsProSopDetail(bsProSopDetail);
    }

    /**
     * 删除成品检验标准明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsProSopDetailByIds(String ids)
    {
        return bsProSopDetailMapper.deleteBsProSopDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成品检验标准明细信息
     * 
     * @param id 成品检验标准明细ID
     * @return 结果
     */
    @Override
    public int deleteBsProSopDetailById(String id)
    {
        return bsProSopDetailMapper.deleteBsProSopDetailById(id);
    }
}
