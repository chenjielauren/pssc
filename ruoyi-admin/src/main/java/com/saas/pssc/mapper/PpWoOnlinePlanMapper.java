package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.PpWoOnlinePlan;

/**
 * 工单上线计划Mapper接口
 * 
 * @author admin
 * @date 2021-07-23
 */
public interface PpWoOnlinePlanMapper 
{
    /**
     * 查询工单上线计划
     * 
     * @param id 工单上线计划ID
     * @return 工单上线计划
     */
    public PpWoOnlinePlan selectPpWoOnlinePlanById(Long id);

    /**
     * 查询工单上线计划列表
     * 
     * @param ppWoOnlinePlan 工单上线计划
     * @return 工单上线计划集合
     */
    public List<PpWoOnlinePlan> selectPpWoOnlinePlanList(PpWoOnlinePlan ppWoOnlinePlan);

    /**
     * 新增工单上线计划
     * 
     * @param ppWoOnlinePlan 工单上线计划
     * @return 结果
     */
    public int insertPpWoOnlinePlan(PpWoOnlinePlan ppWoOnlinePlan);

    /**
     * 修改工单上线计划
     * 
     * @param ppWoOnlinePlan 工单上线计划
     * @return 结果
     */
    public int updatePpWoOnlinePlan(PpWoOnlinePlan ppWoOnlinePlan);

    /**
     * 失效工单上线计划
     * 
     * @param id 工单上线计划ID
     * @return 结果
     */
    public int updatePpWoOnlinePlanById(Long id);

    /**
     * 批量失效工单上线计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updatePpWoOnlinePlanByIds(String[] ids);

    /**
     * 删除工单上线计划
     * 
     * @param id 工单上线计划ID
     * @return 结果
     */
    public int deletePpWoOnlinePlanById(Long id);

    /**
     * 批量删除工单上线计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoOnlinePlanByIds(String[] ids);
}