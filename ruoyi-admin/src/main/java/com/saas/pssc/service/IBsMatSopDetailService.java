package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsMatSopDetail;

/**
 * 材料检验标准明细Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IBsMatSopDetailService 
{
    /**
     * 查询材料检验标准明细
     * 
     * @param id 材料检验标准明细ID
     * @return 材料检验标准明细
     */
    public BsMatSopDetail selectBsMatSopDetailById(String id);

    /**
     * 查询材料检验标准明细列表
     * 
     * @param bsMatSopDetail 材料检验标准明细
     * @return 材料检验标准明细集合
     */
    public List<BsMatSopDetail> selectBsMatSopDetailList(BsMatSopDetail bsMatSopDetail);

    /**
     * 新增材料检验标准明细
     * 
     * @param bsMatSopDetail 材料检验标准明细
     * @return 结果
     */
    public int insertBsMatSopDetail(BsMatSopDetail bsMatSopDetail);

    /**
     * 修改材料检验标准明细
     * 
     * @param bsMatSopDetail 材料检验标准明细
     * @return 结果
     */
    public int updateBsMatSopDetail(BsMatSopDetail bsMatSopDetail);

    /**
     * 批量删除材料检验标准明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMatSopDetailByIds(String ids);

    /**
     * 删除材料检验标准明细信息
     * 
     * @param id 材料检验标准明细ID
     * @return 结果
     */
    public int deleteBsMatSopDetailById(String id);

	public List<BsMatSopDetail> selectBsMatSopDetailByPcode(String pcode);
}
