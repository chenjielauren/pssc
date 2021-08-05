package com.saas.pssc.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 库存信息对象 in_store_main
 * 
 * @author admin
 * @date 2021-08-03
 */
public class InStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 库存类型 */
    @Excel(name = "库存类型",readConverterExp ="0=材料库存,1=半成品库存,2=成品库存,3=未知")
    private String ptype;

    /** 材料/半成品/成品编号 */
    @Excel(name = "材料/半成品/成品编号")
    private String mcode;

    /** 材料/半成品/成品名称 */
    @Excel(name = "材料/半成品/成品名称")
    private String mname;

    /** 材料/半成品/成品规格 */
    @Excel(name = "材料/半成品/成品规格")
    private String mspec;

    /** 材料/半成品/成品批次号 */
    @Excel(name = "材料/半成品/成品批次号")
    private String mlot;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 供应商 */
    @Excel(name = "供应商")
    private String vendor;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private BigDecimal iqty;

    /** 库存更新时间 默认为当前时间 年月日时分秒 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "库存更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uptime;

    /** 有效否 0失效1有效 */
    private String isValid;

    @Excel(name = "备注")
    private String remark;
 
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
    public void setPtype(String ptype) 
    {
        this.ptype = ptype;
    }

    public String getPtype() 
    {
        return ptype;
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
    public void setMspec(String mspec) 
    {
        this.mspec = mspec;
    }

    public String getMspec() 
    {
        return mspec;
    }
    public void setMlot(String mlot) 
    {
        this.mlot = mlot;
    }

    public String getMlot() 
    {
        return mlot;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setVendor(String vendor) 
    {
        this.vendor = vendor;
    }

    public String getVendor() 
    {
        return vendor;
    }
    public void setIqty(BigDecimal iqty) 
    {
        this.iqty = iqty;
    }

    public BigDecimal getIqty() 
    {
        return iqty;
    }
    public void setUptime(Date uptime) 
    {
        this.uptime = uptime;
    }

    public Date getUptime() 
    {
        return uptime;
    }
    public void setIsValid(String isValid) 
    {
        this.isValid = isValid;
    }

    public String getIsValid() 
    {
        return isValid;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
            .append("ptype", getPtype())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("mspec", getMspec())
            .append("mlot", getMlot())
            .append("unit", getUnit())
            .append("vendor", getVendor())
            .append("iqty", getIqty())
            .append("uptime", getUptime())
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
