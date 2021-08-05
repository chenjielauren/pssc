package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsProSopDetail;

/**
 * 成品检验标准明细Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IBsProSopDetailService 
{
    /**
     * 查询成品检验标准明细
     * 
     * @param id 成品检验标准明细ID
     * @return 成品检验标准明细
     */
    public BsProSopDetail selectBsProSopDetailById(String id);

    /**
     * 查询成品检验标准明细列表
     * 
     * @param bsProSopDetail 成品检验标准明细
     * @return 成品检验标准明细集合
     */
    public List<BsProSopDetail> selectBsProSopDetailList(BsProSopDetail bsProSopDetail);

    /**
     * 新增成品检验标准明细
     * 
     * @param bsProSopDetail 成品检验标准明细
     * @return 结果
     */
    public int insertBsProSopDetail(BsProSopDetail bsProSopDetail);

    /**
     * 修改成品检验标准明细
     * 
     * @param bsProSopDetail 成品检验标准明细
     * @return 结果
     */
    public int updateBsProSopDetail(BsProSopDetail bsProSopDetail);

    /**
     * 批量删除成品检验标准明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsProSopDetailByIds(String ids);

    /**
     * 删除成品检验标准明细信息
     * 
     * @param id 成品检验标准明细ID
     * @return 结果
     */
    public int deleteBsProSopDetailById(String id);
}
