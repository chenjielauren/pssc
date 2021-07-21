package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsPqcDetail;

/**
 * 产品检验标准明细Mapper接口
 * 
 * @author admin
 * @date 2021-07-15
 */
public interface BsPqcDetailMapper 
{
    /**
     * 查询产品检验标准明细
     * 
     * @param Id 产品检验标准明细ID
     * @return 产品检验标准明细
     */
    public BsPqcDetail selectBsPqcDetailById(Long Id);

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
     * 删除产品检验标准明细
     * 
     * @param Id 产品检验标准明细ID
     * @return 结果
     */
    public int deleteBsPqcDetailById(Long Id);

    /**
     * 批量删除产品检验标准明细
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsPqcDetailByIds(String[] Ids);
}
