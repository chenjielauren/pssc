package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 材料检验标准明细对象 bs_mat_sop_detail
 * 
 * @author admin
 * @date 2021-08-03
 */
public class BsMatSopDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 主表ID */
    private String mainId;

    /** 序号 */
    private Long orderno;

    /** 检验项目编号 */
    @Excel(name = "检验项目编号")
    private String pcode;

    /** 检验项目名称 */
    @Excel(name = "检验项目名称")
    private String name;

    /** 检验标准 */
    @Excel(name = "检验标准")
    private String standard;

    /** 检验方法 */
    @Excel(name = "检验方法")
    private String pmethod;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 有效否 0失效1有效 */
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMainId(String mainId) 
    {
        this.mainId = mainId;
    }

    public String getMainId() 
    {
        return mainId;
    }
    public void setOrderno(Long orderno) 
    {
        this.orderno = orderno;
    }

    public Long getOrderno() 
    {
        return orderno;
    }
    public void setPcode(String pcode) 
    {
        this.pcode = pcode;
    }

    public String getPcode() 
    {
        return pcode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStandard(String standard) 
    {
        this.standard = standard;
    }

    public String getStandard() 
    {
        return standard;
    }
    public void setPmethod(String pmethod) 
    {
        this.pmethod = pmethod;
    }

    public String getPmethod() 
    {
        return pmethod;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
            .append("id", getId())
            .append("mainId", getMainId())
            .append("orderno", getOrderno())
            .append("pcode", getPcode())
            .append("name", getName())
            .append("standard", getStandard())
            .append("pmethod", getPmethod())
            .append("remark", getRemark())
            .append("isValid", getIsValid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .toString();
    }

   
}
