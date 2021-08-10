package com.saas.pssc.mapper;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.domain.BsMcnDetail;

/**
 * 4M变更单Mapper接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface BsMcnMainMapper 
{
    /**
     * 查询4M变更单
     * 
     * @param id 4M变更单ID
     * @return 4M变更单
     */
    public BsMcnMain selectBsMcnMainById(String id);

    /**
     * 查询4M变更单列表
     * 
     * @param bsMcnMain 4M变更单
     * @return 4M变更单集合
     */
    public List<BsMcnMain> selectBsMcnMainList(BsMcnMain bsMcnMain);

    /**
     * 新增4M变更单
     * 
     * @param bsMcnMain 4M变更单
     * @return 结果
     */
    public int insertBsMcnMain(BsMcnMain bsMcnMain);

    /**
     * 修改4M变更单
     * 
     * @param bsMcnMain 4M变更单
     * @return 结果
     */
    public int updateBsMcnMain(BsMcnMain bsMcnMain);

    /**
     * 失效4M变更单
     * 
     * @param id 4M变更单ID
     * @return 结果
    */
    public int updateBsMcnMainById(String id);

    /**
     * 批量失效4M变更单
     * 
     * @param ids 需要失效的数据ID
     * @return 结果
     */
    public int updateBsMcnMainByIds(String[] ids);
    
    /**
     * 删除4M变更单
     * 
     * @param id 4M变更单ID
     * @return 结果
     */
    public int deleteBsMcnMainById(String id);

    /**
     * 批量删除4M变更单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMcnMainByIds(String[] ids);

    /**
     * 批量删除4M变更单明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMcnDetailByMainIds(String[] ids);
    
    /**
     * 批量新增4M变更单明细
     * 
     * @param bsMcnDetailList 4M变更单明细列表
     * @return 结果
     */
    public int batchBsMcnDetail(List<BsMcnDetail> bsMcnDetailList);
    

    /**
     * 通过4M变更单ID删除4M变更单明细信息
     * 
     * @param string 4M变更单ID
     * @return 结果
     */
    public int deleteBsMcnDetailByMainId(String string);

	public BsMcnMain selectBsMcnMainByMap(Map<String, Object> paramMap);
}
