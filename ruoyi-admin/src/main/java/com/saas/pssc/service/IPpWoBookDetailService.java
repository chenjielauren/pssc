package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.PpWoBookDetail;

/**
 * 工单报工记录明细Service接口
 * 
 * @author admin
 * @date 2021-07-24
 */
public interface IPpWoBookDetailService 
{
    /**
     * 查询工单报工记录明细
     * 
     * @param id 工单报工记录明细ID
     * @return 工单报工记录明细
     */
    public PpWoBookDetail selectPpWoBookDetailById(Long id);

    /**
     * 查询工单报工记录明细列表
     * 
     * @param ppWoBookDetail 工单报工记录明细
     * @return 工单报工记录明细集合
     */
    public List<PpWoBookDetail> selectPpWoBookDetailList(PpWoBookDetail ppWoBookDetail);

    /**
     * 新增工单报工记录明细
     * 
     * @param ppWoBookDetail 工单报工记录明细
     * @return 结果
     */
    public int insertPpWoBookDetail(PpWoBookDetail ppWoBookDetail);

    /**
     * 修改工单报工记录明细
     * 
     * @param ppWoBookDetail 工单报工记录明细
     * @return 结果
     */
    public int updatePpWoBookDetail(PpWoBookDetail ppWoBookDetail);

    /**
     * 批量删除工单报工记录明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookDetailByIds(String ids);

    /**
     * 删除工单报工记录明细信息
     * 
     * @param id 工单报工记录明细ID
     * @return 结果
     */
    public int deletePpWoBookDetailById(Long id);
}