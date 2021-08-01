package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.TreeEntity;

/**
 * bom物料清单对象 bs_bom
 * 
 * @author admin
 * @date 2021-08-01
 */
public class BsBom extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private Long id;

    /** 上级物料类型 */
    @Excel(name = "上级物料类型")
    private Long idParent;

    /** 部件编号 */
    @Excel(name = "部件编号")
    private String mcode;

    /** 部件名称 */
    @Excel(name = "部件名称")
    private String mname;

    /** 部件规格 */
    @Excel(name = "部件规格")
    private String spec;

    /** 部件属性(生产/采购) */
    @Excel(name = "部件属性(生产/采购)")
    private String attribute;

    /** 上机尺寸 */
    @Excel(name = "上机尺寸")
    private String msize;

    /** 模切拼数 */
    @Excel(name = "模切拼数")
    private Double dnumber;

    /** 切纸拼数 */
    @Excel(name = "切纸拼数")
    private Double pnumber;

    /** 用量 */
    @Excel(name = "用量")
    private Double qty;

    /** 有效否 0失效1有效 */
    @Excel(name = "有效否 0失效1有效")
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIdParent(Long idParent) 
    {
        this.idParent = idParent;
    }

    public Long getIdParent() 
    {
        return idParent;
    }
    public void setMcode(String mcode) 
    {
        this.mcode = mcode;
    }

    public String getMcode() 
    {
        return mcode;
    }
    public void setMname(String mname) 
    {
        this.mname = mname;
    }

    public String getMname() 
    {
        return mname;
    }
    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
    }
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setMsize(String msize) 
    {
        this.msize = msize;
    }

    public String getMsize() 
    {
        return msize;
    }
    public void setDnumber(Double dnumber) 
    {
        this.dnumber = dnumber;
    }

    public Double getDnumber() 
    {
        return dnumber;
    }
    public void setPnumber(Double pnumber) 
    {
        this.pnumber = pnumber;
    }

    public Double getPnumber() 
    {
        return pnumber;
    }
    public void setQty(Double qty) 
    {
        this.qty = qty;
    }

    public Double getQty() 
    {
        return qty;
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
            .append("idParent", getIdParent())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("spec", getSpec())
            .append("attribute", getAttribute())
            .append("msize", getMsize())
            .append("dnumber", getDnumber())
            .append("pnumber", getPnumber())
            .append("qty", getQty())
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
