package com.saas.pssc.domain;

import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库存明细对象 in_store_detail
 * 
 * @author admin
 * @date 2021-07-22
 */
public class InMStoreDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 主表ID */
    private Long mainId;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String mname;

    /** 材料编号 */
    @Excel(name = "材料编号")
    private String mcode;

    /** 材料批次号 */
    @Excel(name = "材料批次号")
    private String mlot;

    /** 供应商 */
    @Excel(name = "供应商")
    private String vendor;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 入库数量 */
    @Excel(name = "入库数量")
    private Long qty;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long iqty;

    /** 有效否 */
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    private String ptype;//库存类型 0:材料库存 1:半成品库存 3:成品库存：

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
    public void setMlot(String mlot) 
    {
        this.mlot = mlot;
    }

    public String getMlot() 
    {
        return mlot;
    }
    public void setVendor(String vendor) 
    {
        this.vendor = vendor;
    }

    public String getVendor() 
    {
        return vendor;
    }
    
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
    }
    public void setIqty(Long iqty) 
    {
        this.iqty = iqty;
    }

    public Long getIqty() 
    {
        return iqty;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
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
    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainId", getMainId())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("mlot", getMlot())
            .append("vendor", getVendor())
            .append("qty", getQty())
            .append("iqty", getIqty())
            .append("unit", getUnit())
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
