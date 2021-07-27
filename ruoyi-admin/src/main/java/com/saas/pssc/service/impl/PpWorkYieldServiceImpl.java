package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.PpWorkYield;
import com.saas.pssc.mapper.PpWorkYieldMapper;
import com.saas.pssc.service.IPpWorkYieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工序成品率Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class PpWorkYieldServiceImpl implements IPpWorkYieldService 
{
    @Autowired
    private PpWorkYieldMapper ppWorkYieldMapper;

    /**
     * 查询工序成品率
     * 
     * @param id 工序成品率ID
     * @return 工序成品率
     */
    @Override
    public PpWorkYield selectPpWorkYieldById(Long id)
    {
        return ppWorkYieldMapper.selectPpWorkYieldById(id);
    }

    /**
     * 查询工序成品率列表
     * 
     * @param ppWorkYield 工序成品率
     * @return 工序成品率
     */
    @Override
    @DataScope(userAlias = "su")
    public List<PpWorkYield> selectPpWorkYieldList(PpWorkYield ppWorkYield)
    {
        return ppWorkYieldMapper.selectPpWorkYieldList(ppWorkYield);
    }

    /**
     * 新增工序成品率
     * 
     * @param ppWorkYield 工序成品率
     * @return 结果
     */
    @Override
    public int insertPpWorkYield(PpWorkYield ppWorkYield)
    {
        ppWorkYield.setCreateTime(DateUtils.getNowDate());
        return ppWorkYieldMapper.insertPpWorkYield(ppWorkYield);
    }

    /**
     * 修改工序成品率
     * 
     * @param ppWorkYield 工序成品率
     * @return 结果
     */
    @Override
    public int updatePpWorkYield(PpWorkYield ppWorkYield)
    {
        ppWorkYield.setUpdateTime(DateUtils.getNowDate());
        return ppWorkYieldMapper.updatePpWorkYield(ppWorkYield);
    }

    /**
     * 失效工序成品率对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePpWorkYieldByIds(String ids)
    {
        return ppWorkYieldMapper.updatePpWorkYieldByIds(Convert.toStrArray(ids));
    }

    /**
     * 失效工序成品率信息
     * 
     * @param id 工序成品率ID
     * @return 结果
     */
    @Override
    public int deletePpWorkYieldById(Long id)
    {
        return ppWorkYieldMapper.updatePpWorkYieldById(id);
    }
}
