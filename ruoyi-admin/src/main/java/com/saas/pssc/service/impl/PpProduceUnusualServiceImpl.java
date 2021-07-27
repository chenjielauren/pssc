package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.PpProduceUnusual;
import com.saas.pssc.mapper.PpProduceUnusualMapper;
import com.saas.pssc.service.IPpProduceUnusualService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生产异常记录Service业务层处理
 * 
 * @author admin
 * @date 2021-07-23
 */
@Service
public class PpProduceUnusualServiceImpl implements IPpProduceUnusualService 
{
    @Autowired
    private PpProduceUnusualMapper ppProduceUnusualMapper;

    /**
     * 查询生产异常记录
     * 
     * @param id 生产异常记录ID
     * @return 生产异常记录
     */
    @Override
    public PpProduceUnusual selectPpProduceUnusualById(Long id)
    {
        return ppProduceUnusualMapper.selectPpProduceUnusualById(id);
    }

    /**
     * 查询生产异常记录列表
     * 
     * @param ppProduceUnusual 生产异常记录
     * @return 生产异常记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<PpProduceUnusual> selectPpProduceUnusualList(PpProduceUnusual ppProduceUnusual)
    {
        return ppProduceUnusualMapper.selectPpProduceUnusualList(ppProduceUnusual);
    }

    /**
     * 新增生产异常记录
     * 
     * @param ppProduceUnusual 生产异常记录
     * @return 结果
     */
    @Override
    public int insertPpProduceUnusual(PpProduceUnusual ppProduceUnusual)
    {
        ppProduceUnusual.setCreateTime(DateUtils.getNowDate());
        return ppProduceUnusualMapper.insertPpProduceUnusual(ppProduceUnusual);
    }

    /**
     * 修改生产异常记录
     * 
     * @param ppProduceUnusual 生产异常记录
     * @return 结果
     */
    @Override
    public int updatePpProduceUnusual(PpProduceUnusual ppProduceUnusual)
    {
        ppProduceUnusual.setUpdateTime(DateUtils.getNowDate());
        return ppProduceUnusualMapper.updatePpProduceUnusual(ppProduceUnusual);
    }
    

    /**
     * 删除生产异常记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePpProduceUnusualByIds(String ids)
    {
        return ppProduceUnusualMapper.updatePpProduceUnusualByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产异常记录信息
     * 
     * @param id 生产异常记录ID
     * @return 结果
     */
    @Override
    public int deletePpProduceUnusualById(Long id)
    {
        return ppProduceUnusualMapper.updatePpProduceUnusualById(id);
    }
}
