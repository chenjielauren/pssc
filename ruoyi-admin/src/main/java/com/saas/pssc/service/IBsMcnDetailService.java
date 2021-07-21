package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsMcnDetail;

/**
 * 4M变更单明细Service接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface IBsMcnDetailService 
{
    /**
     * 查询4M变更单明细
     * 
     * @param id 4M变更单明细ID
     * @return 4M变更单明细
     */
    public BsMcnDetail selectBsMcnDetailById(Long id);

    /**
     * 查询4M变更单明细列表
     * 
     * @param bsMcnDetail 4M变更单明细
     * @return 4M变更单明细集合
     */
    public List<BsMcnDetail> selectBsMcnDetailList(BsMcnDetail bsMcnDetail);

    /**
     * 新增4M变更单明细
     * 
     * @param bsMcnDetail 4M变更单明细
     * @return 结果
     */
    public int insertBsMcnDetail(BsMcnDetail bsMcnDetail);

    /**
     * 修改4M变更单明细
     * 
     * @param bsMcnDetail 4M变更单明细
     * @return 结果
     */
    public int updateBsMcnDetail(BsMcnDetail bsMcnDetail);

    /**
     * 批量删除4M变更单明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMcnDetailByIds(String ids);

    /**
     * 删除4M变更单明细信息
     * 
     * @param id 4M变更单明细ID
     * @return 结果
     */
    public int deleteBsMcnDetailById(Long id);
}
