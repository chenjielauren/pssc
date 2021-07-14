package com.kl.pssc.service;

import java.util.List;
import com.kl.pssc.domain.StandardinfoProductInspection;

/**
 * 产品检验标准明细信息Service接口
 * 
 * @author admin
 * @date 2021-07-14
 */
public interface IStandardinfoProductInspectionService 
{
    /**
     * 查询产品检验标准明细信息
     * 
     * @param inspectionId 产品检验标准明细信息ID
     * @return 产品检验标准明细信息
     */
    public StandardinfoProductInspection selectStandardinfoProductInspectionById(Long inspectionId);

    /**
     * 查询产品检验标准明细信息列表
     * 
     * @param standardinfoProductInspection 产品检验标准明细信息
     * @return 产品检验标准明细信息集合
     */
    public List<StandardinfoProductInspection> selectStandardinfoProductInspectionList(StandardinfoProductInspection standardinfoProductInspection);

    /**
     * 新增产品检验标准明细信息
     * 
     * @param standardinfoProductInspection 产品检验标准明细信息
     * @return 结果
     */
    public int insertStandardinfoProductInspection(StandardinfoProductInspection standardinfoProductInspection);

    /**
     * 修改产品检验标准明细信息
     * 
     * @param standardinfoProductInspection 产品检验标准明细信息
     * @return 结果
     */
    public int updateStandardinfoProductInspection(StandardinfoProductInspection standardinfoProductInspection);

    /**
     * 批量删除产品检验标准明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardinfoProductInspectionByIds(String ids);

    /**
     * 删除产品检验标准明细信息信息
     * 
     * @param inspectionId 产品检验标准明细信息ID
     * @return 结果
     */
    public int deleteStandardinfoProductInspectionById(Long inspectionId);
}
