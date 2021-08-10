package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsCraftSopDetail;

/**
 * 工艺标准与CCP明细Service接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface IBsCraftSopDetailService 
{
    /**
     * 查询工艺标准与CCP明细
     * 
     * @param id 工艺标准与CCP明细ID
     * @return 工艺标准与CCP明细
     */
    public BsCraftSopDetail selectBsCraftSopDetailById(String id);

    /**
     * 查询工艺标准与CCP明细列表
     * 
     * @param bsCraftSopDetail 工艺标准与CCP明细
     * @return 工艺标准与CCP明细集合
     */
    public List<BsCraftSopDetail> selectBsCraftSopDetailList(BsCraftSopDetail bsCraftSopDetail);

    /**
     * 新增工艺标准与CCP明细
     * 
     * @param bsCraftSopDetail 工艺标准与CCP明细
     * @return 结果
     */
    public int insertBsCraftSopDetail(BsCraftSopDetail bsCraftSopDetail);

    /**
     * 修改工艺标准与CCP明细
     * 
     * @param bsCraftSopDetail 工艺标准与CCP明细
     * @return 结果
     */
    public int updateBsCraftSopDetail(BsCraftSopDetail bsCraftSopDetail);

    /**
     * 批量删除工艺标准与CCP明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsCraftSopDetailByIds(String ids);

    /**
     * 删除工艺标准与CCP明细信息
     * 
     * @param id 工艺标准与CCP明细ID
     * @return 结果
     */
    public int deleteBsCraftSopDetailById(String id);
}
