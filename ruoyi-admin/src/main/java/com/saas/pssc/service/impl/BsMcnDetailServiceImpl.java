package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsMcnDetailMapper;
import com.saas.pssc.domain.BsMcnDetail;
import com.saas.pssc.service.IBsMcnDetailService;
import com.saas.common.core.text.Convert;

/**
 * 4M变更单明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-19
 */
@Service
public class BsMcnDetailServiceImpl implements IBsMcnDetailService 
{
    @Autowired
    private BsMcnDetailMapper bsMcnDetailMapper;

    /**
     * 查询4M变更单明细
     * 
     * @param id 4M变更单明细ID
     * @return 4M变更单明细
     */
    @Override
    public BsMcnDetail selectBsMcnDetailById(String id)
    {
        return bsMcnDetailMapper.selectBsMcnDetailById(id);
    }

    /**
     * 查询4M变更单明细列表
     * 
     * @param bsMcnDetail 4M变更单明细
     * @return 4M变更单明细
     */
    @Override
    public List<BsMcnDetail> selectBsMcnDetailList(BsMcnDetail bsMcnDetail)
    {
        return bsMcnDetailMapper.selectBsMcnDetailList(bsMcnDetail);
    }

    /**
     * 新增4M变更单明细
     * 
     * @param bsMcnDetail 4M变更单明细
     * @return 结果
     */
    @Override
    public int insertBsMcnDetail(BsMcnDetail bsMcnDetail)
    {
        bsMcnDetail.setCreateTime(DateUtils.getNowDate());
        return bsMcnDetailMapper.insertBsMcnDetail(bsMcnDetail);
    }

    /**
     * 修改4M变更单明细
     * 
     * @param bsMcnDetail 4M变更单明细
     * @return 结果
     */
    @Override
    public int updateBsMcnDetail(BsMcnDetail bsMcnDetail)
    {
        bsMcnDetail.setUpdateTime(DateUtils.getNowDate());
        return bsMcnDetailMapper.updateBsMcnDetail(bsMcnDetail);
    }

    /**
     * 删除4M变更单明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsMcnDetailByIds(String ids)
    {
        return bsMcnDetailMapper.deleteBsMcnDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除4M变更单明细信息
     * 
     * @param id 4M变更单明细ID
     * @return 结果
     */
    @Override
    public int deleteBsMcnDetailById(String id)
    {
        return bsMcnDetailMapper.deleteBsMcnDetailById(id);
    }
}
