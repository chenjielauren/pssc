package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsPqcDetail;

/**
 * 产品检验标准明细Service接口
 * 
 * @author admin
 * @date 2021-07-15
 */
public interface IBsPqcDetailService 
{
    /**
     * 查询产品检验标准明细
     * 
     * @param Id 产品检验标准明细ID
     * @return 产品检验标准明细
     */
    public BsPqcDetail selectBsPqcDetailById(String Id);

    /**
     * 查询产品检验标准明细列表
     * 
     * @param bsPqcDetail 产品检验标准明细
     * @return 产品检验标准明细集合
     */
    public List<BsPqcDetail> selectBsPqcDetailList(BsPqcDetail bsPqcDetail);

    /**
     * 新增产品检验标准明细
     * 
     * @param bsPqcDetail 产品检验标准明细
     * @return 结果
     */
    public int insertBsPqcDetail(BsPqcDetail bsPqcDetail);

    /**
     * 修改产品检验标准明细
     * 
     * @param bsPqcDetail 产品检验标准明细
     * @return 结果
     */
    public int updateBsPqcDetail(BsPqcDetail bsPqcDetail);

    /**
     * 批量删除产品检验标准明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsPqcDetailByIds(String ids);

    /**
     * 删除产品检验标准明细信息
     * 
     * @param Id 产品检验标准明细ID
     * @return 结果
     */
    public int deleteBsPqcDetailById(String Id);
}
