package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsMcnMain;

/**
 * 4M变更单Service接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface IBsMcnMainService 
{
    /**
     * 查询4M变更单
     * 
     * @param id 4M变更单ID
     * @return 4M变更单
     */
    public BsMcnMain selectBsMcnMainById(Long id);

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
     * 批量删除4M变更单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMcnMainByIds(String ids);

    /**
     * 删除4M变更单信息
     * 
     * @param id 4M变更单ID
     * @return 结果
     */
    public int deleteBsMcnMainById(Long id);
}
