package com.kl.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kl.common.annotation.Excel;
import com.kl.common.core.domain.BaseEntity;

/**
 * 产品检验标准明细信息对象 standardinfo_product_inspection
 * 
 * @author admin
 * @date 2021-07-14
 */
public class StandardinfoProductInspection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检验ID */
    private Long inspectionId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 检验类型（0首检 1自检 2巡检 3未知） */
    @Excel(name = "检验类型", readConverterExp = "0=首检,1=自检,2=巡检,3=未知")
    private String inspectionType;

    /** 检验项目编号 */
    @Excel(name = "检验项目编号")
    private String inspectionNumber;

    /** 检验项目名称 */
    @Excel(name = "检验项目名称")
    private String inspectionName;

    /** 检验工序标准 */
    @Excel(name = "检验工序标准")
    private String inspectionStandard;

    /** 检验方法 */
    @Excel(name = "检验方法")
    private String inspectionMethod;

    /** Y:有效;N:无效 */
    @Excel(name = "Y:有效;N:无效")
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    public void setInspectionId(Long inspectionId) 
    {
        this.inspectionId = inspectionId;
    }

    public Long getInspectionId() 
    {
        return inspectionId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setInspectionType(String inspectionType) 
    {
        this.inspectionType = inspectionType;
    }

    public String getInspectionType() 
    {
        return inspectionType;
    }
    public void setInspectionNumber(String inspectionNumber) 
    {
        this.inspectionNumber = inspectionNumber;
    }

    public String getInspectionNumber() 
    {
        return inspectionNumber;
    }
    public void setInspectionName(String inspectionName) 
    {
        this.inspectionName = inspectionName;
    }

    public String getInspectionName() 
    {
        return inspectionName;
    }
    public void setInspectionStandard(String inspectionStandard) 
    {
        this.inspectionStandard = inspectionStandard;
    }

    public String getInspectionStandard() 
    {
        return inspectionStandard;
    }
    public void setInspectionMethod(String inspectionMethod) 
    {
        this.inspectionMethod = inspectionMethod;
    }

    public String getInspectionMethod() 
    {
        return inspectionMethod;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inspectionId", getInspectionId())
            .append("productId", getProductId())
            .append("inspectionType", getInspectionType())
            .append("inspectionNumber", getInspectionNumber())
            .append("inspectionName", getInspectionName())
            .append("inspectionStandard", getInspectionStandard())
            .append("inspectionMethod", getInspectionMethod())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("isValid", getIsValid())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .toString();
    }
}
