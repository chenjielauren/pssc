package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.PpWoBookMain;

/**
 * 工单报工记录Service接口
 * 
 * @author admin
 * @date 2021-07-24
 */
public interface IPpWoBookMainService 
{
    /**
     * 查询工单报工记录
     * 
     * @param id 工单报工记录ID
     * @return 工单报工记录
     */
    public PpWoBookMain selectPpWoBookMainById(Long id);

    /**
     * 查询工单报工记录列表
     * 
     * @param ppWoBookMain 工单报工记录
     * @return 工单报工记录集合
     */
    public List<PpWoBookMain> selectPpWoBookMainList(PpWoBookMain ppWoBookMain);

    /**
     * 新增工单报工记录
     * 
     * @param ppWoBookMain 工单报工记录
     * @return 结果
     */
    public int insertPpWoBookMain(PpWoBookMain ppWoBookMain);

    /**
     * 修改工单报工记录
     * 
     * @param ppWoBookMain 工单报工记录
     * @return 结果
     */
    public int updatePpWoBookMain(PpWoBookMain ppWoBookMain);

    /**
     * 批量删除工单报工记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookMainByIds(String ids);

    /**
     * 删除工单报工记录信息
     * 
     * @param id 工单报工记录ID
     * @return 结果
     */
    public int deletePpWoBookMainById(Long id);
}
