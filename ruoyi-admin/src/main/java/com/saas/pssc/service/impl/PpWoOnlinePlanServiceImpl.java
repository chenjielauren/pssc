package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.PpWoOnlinePlan;
import com.saas.pssc.mapper.PpWoOnlinePlanMapper;
import com.saas.pssc.service.IPpWoOnlinePlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工单上线计划Service业务层处理
 * 
 * @author admin
 * @date 2021-07-23
 */
@Service
public class PpWoOnlinePlanServiceImpl implements IPpWoOnlinePlanService 
{
    @Autowired
    private PpWoOnlinePlanMapper ppWoOnlinePlanMapper;

    /**
     * 查询工单上线计划
     * 
     * @param id 工单上线计划ID
     * @return 工单上线计划
     */
    @Override
    public PpWoOnlinePlan selectPpWoOnlinePlanById(Long id)
    {
        return ppWoOnlinePlanMapper.selectPpWoOnlinePlanById(id);
    }

    /**
     * 查询工单上线计划列表
     * 
     * @param ppWoOnlinePlan 工单上线计划
     * @return 工单上线计划
     */
    @Override
    @DataScope(userAlias = "su")
    public List<PpWoOnlinePlan> selectPpWoOnlinePlanList(PpWoOnlinePlan ppWoOnlinePlan)
    {
        return ppWoOnlinePlanMapper.selectPpWoOnlinePlanList(ppWoOnlinePlan);
    }

    /**
     * 新增工单上线计划
     * 
     * @param ppWoOnlinePlan 工单上线计划
     * @return 结果
     */
    @Override
    public int insertPpWoOnlinePlan(PpWoOnlinePlan ppWoOnlinePlan)
    {
        ppWoOnlinePlan.setCreateTime(DateUtils.getNowDate());
        return ppWoOnlinePlanMapper.insertPpWoOnlinePlan(ppWoOnlinePlan);
    }

    /**
     * 修改工单上线计划
     * 
     * @param ppWoOnlinePlan 工单上线计划
     * @return 结果
     */
    @Override
    public int updatePpWoOnlinePlan(PpWoOnlinePlan ppWoOnlinePlan)
    {
        ppWoOnlinePlan.setUpdateTime(DateUtils.getNowDate());
        return ppWoOnlinePlanMapper.updatePpWoOnlinePlan(ppWoOnlinePlan);
    }

    /**
     * 删除工单上线计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePpWoOnlinePlanByIds(String ids)
    {
        return ppWoOnlinePlanMapper.updatePpWoOnlinePlanByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单上线计划信息
     * 
     * @param id 工单上线计划ID
     * @return 结果
     */
    @Override
    public int deletePpWoOnlinePlanById(Long id)
    {
        return ppWoOnlinePlanMapper.updatePpWoOnlinePlanById(id);
    }
}