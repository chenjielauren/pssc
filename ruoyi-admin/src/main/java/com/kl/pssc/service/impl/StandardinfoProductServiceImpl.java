package com.kl.pssc.service.impl;

import java.util.List;
import com.kl.common.utils.DateUtils;
import com.kl.common.utils.ShiroUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.kl.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.kl.pssc.domain.StandardinfoProductInspection;
import com.kl.pssc.mapper.StandardinfoProductMapper;
import com.kl.pssc.domain.StandardinfoProduct;
import com.kl.pssc.service.IStandardinfoProductService;
import com.kl.common.core.text.Convert;

/**
 * 产品检验标准Service业务层处理
 * 
 * @author admin
 * @date 2021-07-14
 */
@Service
public class StandardinfoProductServiceImpl implements IStandardinfoProductService 
{
    @Autowired
    private StandardinfoProductMapper standardinfoProductMapper;

    /**
     * 查询产品检验标准
     * 
     * @param productId 产品检验标准ID
     * @return 产品检验标准
     */
    @Override
    public StandardinfoProduct selectStandardinfoProductById(Long productId)
    {
        return standardinfoProductMapper.selectStandardinfoProductById(productId);
    }

    /**
     * 查询产品检验标准列表
     * 
     * @param standardinfoProduct 产品检验标准
     * @return 产品检验标准
     */
    @Override
    public List<StandardinfoProduct> selectStandardinfoProductList(StandardinfoProduct standardinfoProduct)
    {
        return standardinfoProductMapper.selectStandardinfoProductList(standardinfoProduct);
    }

    /**
     * 新增产品检验标准
     * 
     * @param standardinfoProduct 产品检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int insertStandardinfoProduct(StandardinfoProduct standardinfoProduct)
    {
        standardinfoProduct.setCreateTime(DateUtils.getNowDate());
        int rows = standardinfoProductMapper.insertStandardinfoProduct(standardinfoProduct);
        insertStandardinfoProductInspection(standardinfoProduct);
        return rows;
    }

    /**
     * 修改产品检验标准
     * 
     * @param standardinfoProduct 产品检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStandardinfoProduct(StandardinfoProduct standardinfoProduct)
    {
        standardinfoProduct.setUpdateTime(DateUtils.getNowDate());
        standardinfoProductMapper.deleteStandardinfoProductInspectionByProductId(standardinfoProduct.getProductId());
        insertStandardinfoProductInspection(standardinfoProduct);
        return standardinfoProductMapper.updateStandardinfoProduct(standardinfoProduct);
    }

    /**
     * 删除产品检验标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStandardinfoProductByIds(String ids)
    {
        standardinfoProductMapper.updateStandardinfoProductInspectionByProductIds(Convert.toStrArray(ids));
        return standardinfoProductMapper.updateStandardinfoProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品检验标准信息
     * 
     * @param productId 产品检验标准ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoProductById(Long productId)
    {
        standardinfoProductMapper.updateStandardinfoProductInspectionByProductId(productId);
        return standardinfoProductMapper.updateStandardinfoProductById(productId);
    }

    /**
     * 新增产品检验标准明细信息信息
     * 
     * @param standardinfoProduct 产品检验标准对象
     */
    public void insertStandardinfoProductInspection(StandardinfoProduct standardinfoProduct)
    {
        List<StandardinfoProductInspection> standardinfoProductInspectionList = standardinfoProduct.getStandardinfoProductInspectionList();
        Long productId = standardinfoProduct.getProductId();
        if (StringUtils.isNotNull(standardinfoProductInspectionList))
        {
            List<StandardinfoProductInspection> list = new ArrayList<StandardinfoProductInspection>();
            for (StandardinfoProductInspection standardinfoProductInspection : standardinfoProductInspectionList)
            {
                standardinfoProductInspection.setCreateBy(ShiroUtils.getLoginName());
                standardinfoProductInspection.setUpdateBy(ShiroUtils.getLoginName());
                standardinfoProductInspection.setProductId(productId);
                list.add(standardinfoProductInspection);
            }
            if (list.size() > 0)
            {
                standardinfoProductMapper.batchStandardinfoProductInspection(list);
            }
        }
    }
}
