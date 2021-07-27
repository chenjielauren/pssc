package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.domain.PpWoBookDetail;

/**
 * 工单报工记录Mapper接口
 * 
 * @author admin
 * @date 2021-07-24
 */
public interface PpWoBookMainMapper 
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
     * 失效工单报工记录
     * 
     * @param id 工单报工记录ID
     * @return 结果
     */
    public int updatePpWoBookMainById(Long id);

    /**
     * 批量失效工单报工记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updatePpWoBookMainByIds(String[] ids);
    /**
     * 删除工单报工记录
     * 
     * @param id 工单报工记录ID
     * @return 结果
     */
    public int deletePpWoBookMainById(Long id);

    /**
     * 批量删除工单报工记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookMainByIds(String[] ids);

    /**
     * 批量删除工单报工记录明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookDetailByMainIds(String[] ids);
    
    /**
     * 批量新增工单报工记录明细
     * 
     * @param ppWoBookDetailList 工单报工记录明细列表
     * @return 结果
     */
    public int batchPpWoBookDetail(List<PpWoBookDetail> ppWoBookDetailList);
    

    /**
     * 通过工单报工记录ID删除工单报工记录明细信息
     * 
     * @param id 工单报工记录ID
     * @return 结果
     */
    public int deletePpWoBookDetailByMainId(Long id);
}
