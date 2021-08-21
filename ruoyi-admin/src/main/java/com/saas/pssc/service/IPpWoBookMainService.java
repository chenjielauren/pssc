package com.saas.pssc.service;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.PpWoBookMain;

/**
 * 工单记录Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IPpWoBookMainService 
{
    /**
     * 查询工单记录
     * 
     * @param id 工单记录ID
     * @return 工单记录
     */
    public PpWoBookMain selectPpWoBookMainById(String id);

    /**
     * 查询工单记录列表
     * 
     * @param ppWoBookMain 工单记录
     * @return 工单记录集合
     */
    public List<PpWoBookMain> selectPpWoBookMainList(PpWoBookMain ppWoBookMain);

    /**
     * 新增工单记录
     * 
     * @param ppWoBookMain 工单记录
     * @return 结果
     */
    public int insertPpWoBookMain(PpWoBookMain ppWoBookMain);

    /**
     * 修改工单记录
     * 
     * @param ppWoBookMain 工单记录
     * @return 结果
     */
    public int updatePpWoBookMain(PpWoBookMain ppWoBookMain);

    /**
     * 批量删除工单记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookMainByIds(String ids);

    /**
     * 删除工单记录信息
     * 
     * @param id 工单记录ID
     * @return 结果
     */
    public int deletePpWoBookMainById(String id);

	public PpWoBookMain selectPpWoBookMainByMap(Map<String, Object> paramMap);

	public String checkPcodeUnique(PpWoBookMain ppWoBookMain);
}
