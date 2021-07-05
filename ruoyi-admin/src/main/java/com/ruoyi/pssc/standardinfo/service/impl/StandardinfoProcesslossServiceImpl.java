package com.ruoyi.pssc.standardinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pssc.standardinfo.mapper.StandardinfoProcesslossMapper;
import com.ruoyi.pssc.standardinfo.domain.StandardinfoProcessloss;
import com.ruoyi.pssc.standardinfo.service.IStandardinfoProcesslossService;
import com.ruoyi.common.core.text.Convert;

/**
 * 工序损耗标准Service业务层处理
 * 
 * @author lauren
 * @date 2021-07-05
 */
@Service
public class StandardinfoProcesslossServiceImpl implements IStandardinfoProcesslossService 
{
    @Autowired
    private StandardinfoProcesslossMapper standardinfoProcesslossMapper;

    /**
     * 查询工序损耗标准
     * 
     * @param processlossId 工序损耗标准ID
     * @return 工序损耗标准
     */
    @Override
    public StandardinfoProcessloss selectStandardinfoProcesslossById(Long processlossId)
    {
        return standardinfoProcesslossMapper.selectStandardinfoProcesslossById(processlossId);
    }

    /**
     * 查询工序损耗标准列表
     * 
     * @param standardinfoProcessloss 工序损耗标准
     * @return 工序损耗标准
     */
    @Override
    public List<StandardinfoProcessloss> selectStandardinfoProcesslossList(StandardinfoProcessloss standardinfoProcessloss)
    {
        return standardinfoProcesslossMapper.selectStandardinfoProcesslossList(standardinfoProcessloss);
    }

    /**
     * 新增工序损耗标准
     * 
     * @param standardinfoProcessloss 工序损耗标准
     * @return 结果
     */
    @Override
    public int insertStandardinfoProcessloss(StandardinfoProcessloss standardinfoProcessloss)
    {
        standardinfoProcessloss.setCreateTime(DateUtils.getNowDate());
        return standardinfoProcesslossMapper.insertStandardinfoProcessloss(standardinfoProcessloss);
    }

    /**
     * 修改工序损耗标准
     * 
     * @param standardinfoProcessloss 工序损耗标准
     * @return 结果
     */
    @Override
    public int updateStandardinfoProcessloss(StandardinfoProcessloss standardinfoProcessloss)
    {
        standardinfoProcessloss.setUpdateTime(DateUtils.getNowDate());
        return standardinfoProcesslossMapper.updateStandardinfoProcessloss(standardinfoProcessloss);
    }

    /**
     * 删除工序损耗标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoProcesslossByIds(String ids)
    {
        return standardinfoProcesslossMapper.deleteStandardinfoProcesslossByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工序损耗标准信息
     * 
     * @param processlossId 工序损耗标准ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoProcesslossById(Long processlossId)
    {
        return standardinfoProcesslossMapper.deleteStandardinfoProcesslossById(processlossId);
    }
}
