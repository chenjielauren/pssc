package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 发货信息对象 sd_delivery
 * 
 * @author admin
 * @date 2021-08-01
 */
public class SdDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发货ID */
    private Long id;

    /** 发货批次号 */
    @Excel(name = "发货批次号")
    private String dlot;

    /** 订单号 */
    @Excel(name = "订单号")
    private String dcode;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String pspec;

    /** 成品批次号 */
    @Excel(name = "成品批次号")
    private String flot;

    /** 发货数量 */
    @Excel(name = "发货数量")
    private Long qty;

    /** 有效否 0失效1有效 */
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
    public void setDlot(String dlot) 
    {
        this.dlot = dlot;
    }

    public String getDlot() 
    {
        return dlot;
    }
    public void setDcode(String dcode) 
    {
        this.dcode = dcode;
    }

    public String getDcode() 
    {
        return dcode;
    }
    public void setPcode(String pcode) 
    {
        this.pcode = pcode;
    }

    public String getPcode() 
    {
        return pcode;
    }
    public void setPname(String pname) 
    {
        this.pname = pname;
    }

    public String getPname() 
    {
        return pname;
    }
    public void setPspec(String pspec) 
    {
        this.pspec = pspec;
    }

    public String getPspec() 
    {
        return pspec;
    }
    public void setFlot(String flot) 
    {
        this.flot = flot;
    }

    public String getFlot() 
    {
        return flot;
    }
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
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
            .append("dlot", getDlot())
            .append("dcode", getDcode())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("pspec", getPspec())
            .append("flot", getFlot())
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
