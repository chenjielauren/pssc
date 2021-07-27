package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcWoYieldRate;

/**
 * 工单良品率分析Service接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface IQcWoYieldRateService 
{
    /**
     * 查询工单良品率分析
     * 
     * @param id 工单良品率分析ID
     * @return 工单良品率分析
     */
    public QcWoYieldRate selectQcWoYieldRateById(Long id);

    /**
     * 查询工单良品率分析列表
     * 
     * @param qcWoYieldRate 工单良品率分析
     * @return 工单良品率分析集合
     */
    public List<QcWoYieldRate> selectQcWoYieldRateList(QcWoYieldRate qcWoYieldRate);

    /**
     * 新增工单良品率分析
     * 
     * @param qcWoYieldRate 工单良品率分析
     * @return 结果
     */
    public int insertQcWoYieldRate(QcWoYieldRate qcWoYieldRate);

    /**
     * 修改工单良品率分析
     * 
     * @param qcWoYieldRate 工单良品率分析
     * @return 结果
     */
    public int updateQcWoYieldRate(QcWoYieldRate qcWoYieldRate);

    /**
     * 批量删除工单良品率分析
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcWoYieldRateByIds(String ids);

    /**
     * 删除工单良品率分析信息
     * 
     * @param id 工单良品率分析ID
     * @return 结果
     */
    public int deleteQcWoYieldRateById(Long id);
}
