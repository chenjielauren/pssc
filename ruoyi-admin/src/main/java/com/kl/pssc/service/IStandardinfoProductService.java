package com.kl.pssc.service;

import java.util.List;
import com.kl.pssc.domain.StandardinfoProduct;

/**
 * 产品检验标准Service接口
 * 
 * @author admin
 * @date 2021-07-14
 */
public interface IStandardinfoProductService 
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
     * 批量删除产品检验标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardinfoProductByIds(String ids);

    /**
     * 删除产品检验标准信息
     * 
     * @param productId 产品检验标准ID
     * @return 结果
     */
    public int deleteStandardinfoProductById(Long productId);
}
