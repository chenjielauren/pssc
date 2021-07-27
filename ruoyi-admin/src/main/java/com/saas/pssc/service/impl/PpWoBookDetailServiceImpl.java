package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.PpWoBookDetailMapper;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.service.IPpWoBookDetailService;
import com.saas.common.core.text.Convert;

/**
 * 工单报工记录明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-24
 */
@Service
public class PpWoBookDetailServiceImpl implements IPpWoBookDetailService 
{
    @Autowired
    private PpWoBookDetailMapper ppWoBookDetailMapper;

    /**
     * 查询工单报工记录明细
     * 
     * @param id 工单报工记录明细ID
     * @return 工单报工记录明细
     */
    @Override
    public PpWoBookDetail selectPpWoBookDetailById(Long id)
    {
        return ppWoBookDetailMapper.selectPpWoBookDetailById(id);
    }

    /**
     * 查询工单报工记录明细列表
     * 
     * @param ppWoBookDetail 工单报工记录明细
     * @return 工单报工记录明细
     */
    @Override
    public List<PpWoBookDetail> selectPpWoBookDetailList(PpWoBookDetail ppWoBookDetail)
    {
        return ppWoBookDetailMapper.selectPpWoBookDetailList(ppWoBookDetail);
    }

    /**
     * 新增工单报工记录明细
     * 
     * @param ppWoBookDetail 工单报工记录明细
     * @return 结果
     */
    @Override
    public int insertPpWoBookDetail(PpWoBookDetail ppWoBookDetail)
    {
        ppWoBookDetail.setCreateTime(DateUtils.getNowDate());
        return ppWoBookDetailMapper.insertPpWoBookDetail(ppWoBookDetail);
    }

    /**
     * 修改工单报工记录明细
     * 
     * @param ppWoBookDetail 工单报工记录明细
     * @return 结果
     */
    @Override
    public int updatePpWoBookDetail(PpWoBookDetail ppWoBookDetail)
    {
        ppWoBookDetail.setUpdateTime(DateUtils.getNowDate());
        return ppWoBookDetailMapper.updatePpWoBookDetail(ppWoBookDetail);
    }

    /**
     * 删除工单报工记录明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookDetailByIds(String ids)
    {
        return ppWoBookDetailMapper.deletePpWoBookDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单报工记录明细信息
     * 
     * @param id 工单报工记录明细ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookDetailById(Long id)
    {
        return ppWoBookDetailMapper.deletePpWoBookDetailById(id);
    }
}