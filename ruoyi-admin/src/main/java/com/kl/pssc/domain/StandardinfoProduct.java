package com.kl.pssc.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kl.common.annotation.Excel;
import com.kl.common.core.domain.BaseEntity;

/**
 * 产品检验标准对象 standardinfo_product
 * 
 * @author admin
 * @date 2021-07-14
 */
public class StandardinfoProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    private Long productId;

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String productNumber;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String productName;

    /** 检验工序产能 */
    @Excel(name = "检验工序产能")
    private String inspectionStep;

    /** Y:有效;N:无效 */
    @Excel(name = "是否有效(Y:有效;N:无效)")
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    /** 产品检验标准明细信息信息 */
    private List<StandardinfoProductInspection> standardinfoProductInspectionList;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductNumber(String productNumber) 
    {
        this.productNumber = productNumber;
    }

    public String getProductNumber() 
    {
        return productNumber;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setInspectionStep(String inspectionStep) 
    {
        this.inspectionStep = inspectionStep;
    }

    public String getInspectionStep() 
    {
        return inspectionStep;
    }
    public void setIsValid(String isValid) 
    {
        this.isValid = isValid;
    }

    public String getIsValid() 
    {
        return isValid;
    }
    public void setAttribute1(String attribute1) 
    {
        this.attribute1 = attribute1;
    }

    public String getAttribute1() 
    {
        return attribute1;
    }
    public void setAttribute2(String attribute2) 
    {
        this.attribute2 = attribute2;
    }

    public String getAttribute2() 
    {
        return attribute2;
    }
    public void setAttribute3(String attribute3) 
    {
        this.attribute3 = attribute3;
    }

    public String getAttribute3() 
    {
        return attribute3;
    }

    public List<StandardinfoProductInspection> getStandardinfoProductInspectionList()
    {
        return standardinfoProductInspectionList;
    }

    public void setStandardinfoProductInspectionList(List<StandardinfoProductInspection> standardinfoProductInspectionList)
    {
        this.standardinfoProductInspectionList = standardinfoProductInspectionList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productNumber", getProductNumber())
            .append("productName", getProductName())
            .append("inspectionStep", getInspectionStep())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("isValid", getIsValid())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .append("standardinfoProductInspectionList", getStandardinfoProductInspectionList())
            .toString();
    }
}
