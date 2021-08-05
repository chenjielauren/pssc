package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.PpWoBookBom;

/**
 * 工单BOM信息Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IPpWoBookBomService 
{
    /**
     * 查询工单BOM信息
     * 
     * @param id 工单BOM信息ID
     * @return 工单BOM信息
     */
    public PpWoBookBom selectPpWoBookBomById(String id);

    /**
     * 查询工单BOM信息列表
     * 
     * @param ppWoBookBom 工单BOM信息
     * @return 工单BOM信息集合
     */
    public List<PpWoBookBom> selectPpWoBookBomList(PpWoBookBom ppWoBookBom);

    /**
     * 新增工单BOM信息
     * 
     * @param ppWoBookBom 工单BOM信息
     * @return 结果
     */
    public int insertPpWoBookBom(PpWoBookBom ppWoBookBom);

    /**
     * 修改工单BOM信息
     * 
     * @param ppWoBookBom 工单BOM信息
     * @return 结果
     */
    public int updatePpWoBookBom(PpWoBookBom ppWoBookBom);

    /**
     * 批量删除工单BOM信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookBomByIds(String ids);

    /**
     * 删除工单BOM信息信息
     * 
     * @param id 工单BOM信息ID
     * @return 结果
     */
    public int deletePpWoBookBomById(String id);
}
