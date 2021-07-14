package com.kl.pssc.mapper;

import java.util.List;
import com.kl.pssc.domain.StandardinfoProduct;
import com.kl.pssc.domain.StandardinfoProductInspection;

/**
 * 产品检验标准Mapper接口
 * 
 * @author admin
 * @date 2021-07-14
 */
public interface StandardinfoProductMapper 
{
    /**
     * 查询产品检验标准
     * 
     * @param productId 产品检验标准ID
     * @return 产品检验标准
     */
    public StandardinfoProduct selectStandardinfoProductById(Long productId);

    /**
     * 查询产品检验标准列表
     * 
     * @param standardinfoProduct 产品检验标准
     * @return 产品检验标准集合
     */
    public List<StandardinfoProduct> selectStandardinfoProductList(StandardinfoProduct standardinfoProduct);

    /**
     * 新增产品检验标准
     * 
     * @param standardinfoProduct 产品检验标准
     * @return 结果
     */
    public int insertStandardinfoProduct(StandardinfoProduct standardinfoProduct);

    /**
     * 修改产品检验标准
     * 
     * @param standardinfoProduct 产品检验标准
     * @return 结果
     */
    public int updateStandardinfoProduct(StandardinfoProduct standardinfoProduct);

    /**
     * 删除产品检验标准
     * 
     * @param productId 产品检验标准ID
     * @return 结果
     */
    public int deleteStandardinfoProductById(Long productId);

    /**
     * 批量删除产品检验标准
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardinfoProductByIds(String[] productIds);

     /**
     * 失效产品检验标准
     * 
     * @param productId 产品检验标准ID
     * @return 结果
     */
    public int updateStandardinfoProductById(Long productId);

    /**
     * 批量失效删除产品检验标准
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int updateStandardinfoProductByIds(String[] productIds);

    /**
     * 批量删除产品检验标准明细信息
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int updateStandardinfoProductInspectionByProductIds(String[] productIds);
    
    /**
     * 批量删除产品检验标准明细信息
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardinfoProductInspectionByProductIds(String[] productIds);
    
    /**
     * 批量新增产品检验标准明细信息
     * 
     * @param standardinfoProductInspectionList 产品检验标准明细信息列表
     * @return 结果
     */
    public int batchStandardinfoProductInspection(List<StandardinfoProductInspection> standardinfoProductInspectionList);
    

    /**
     * 通过产品检验标准ID删除产品检验标准明细信息信息
     * 
     * @param productId 产品检验标准ID
     * @return 结果
     */
    public int deleteStandardinfoProductInspectionByProductId(Long productId);

    /**
     * 通过产品检验标准ID删除产品检验标准明细信息信息
     * 
     * @param productId 产品检验标准ID
     * @return 结果
     */
    public int updateStandardinfoProductInspectionByProductId(Long productId);
}
