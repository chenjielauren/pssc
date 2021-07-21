package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.TreeEntity;

/**
 * bom物料清单明细对象 bs_bom_detail
 * 
 * @author admin
 * @date 2021-07-19
 */
public class BsBomDetail extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private Long id;

    /** 主表ID */
    @Excel(name = "主表ID")
    private Long mainId;

    /** 材料编号 */
    @Excel(name = "材料编号")
    private String mcode;

    /** 部件名称 */
    @Excel(name = "部件名称")
    private String mname;

    /** 属性 */
    @Excel(name = "属性")
    private String attribute;

    /** 用量 */
    @Excel(name = "用量")
    private Long qty;

    /** 材料规格 */
    @Excel(name = "材料规格")
    private String spec;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private String isValid;

    /**  */
    @Excel(name = "")
    private String attribute1;

    /**  */
    @Excel(name = "")
    private String attribute2;

    /**  */
    @Excel(name = "")
    private String attribute3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMainId(Long mainId) 
    {
        this.mainId = mainId;
    }

    public Long getMainId() 
    {
        return mainId;
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
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
    }
    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
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
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("attribute", getAttribute())
            .append("qty", getQty())
            .append("spec", getSpec())
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
