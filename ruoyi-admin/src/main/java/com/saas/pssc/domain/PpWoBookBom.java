package com.saas.pssc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工单BOM信息对象 pp_wo_book_bom
 * 
 * @author admin
 * @date 2021-08-03
 */
public class PpWoBookBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划工单号ID guid */
    private String id;

    /** 主表ID */
    private String mainId;

    /** 序号 */
    private String orderno;

    /** 部件编号 */
    @Excel(name = "部件编号")
    private String mcode;

    /** 部件名称 */
    @Excel(name = "部件名称")
    private String mname;

    /** 部件规格 */
    @Excel(name = "部件规格")
    private String mspec;

    /** 属性 */
    @Excel(name = "属性",readConverterExp = "0=生产,1=采购,2=未知")
    private String attribute;

    /** 上机尺寸 */
    @Excel(name = "上机尺寸")
    private String onsize;

    /** 模切拼数 */
    @Excel(name = "模切拼数")
    private BigDecimal moqieqty;

    /** 切纸拼数 */
    @Excel(name = "切纸拼数")
    private BigDecimal qiezhiqty;

    /** 用量 */
    @Excel(name = "用量")
    private BigDecimal qty;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 损耗率 */
    @Excel(name = "损耗率")
    private String unratio;

    /** 工艺路线 */
    @Excel(name = "工艺路线")
    private String ruter;

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
    public void setMainId(String mainId) 
    {
        this.mainId = mainId;
    }

    public String getMainId() 
    {
        return mainId;
    }
    public void setOrderno(String orderno) 
    {
        this.orderno = orderno;
    }

    public String getOrderno() 
    {
        return orderno;
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
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setOnsize(String onsize) 
    {
        this.onsize = onsize;
    }

    public String getOnsize() 
    {
        return onsize;
    }
    public void setMoqieqty(BigDecimal moqieqty) 
    {
        this.moqieqty = moqieqty;
    }

    public BigDecimal getMoqieqty() 
    {
        return moqieqty;
    }
    public void setQiezhiqty(BigDecimal qiezhiqty) 
    {
        this.qiezhiqty = qiezhiqty;
    }

    public BigDecimal getQiezhiqty() 
    {
        return qiezhiqty;
    }
    public void setQty(BigDecimal qty) 
    {
        this.qty = qty;
    }

    public BigDecimal getQty() 
    {
        return qty;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setUnratio(String unratio) 
    {
        this.unratio = unratio;
    }

    public String getUnratio() 
    {
        return unratio;
    }
    public void setRuter(String ruter) 
    {
        this.ruter = ruter;
    }

    public String getRuter() 
    {
        return ruter;
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
            .append("mainId", getMainId())
            .append("orderno", getOrderno())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("mspec", getMspec())
            .append("attribute", getAttribute())
            .append("onsize", getOnsize())
            .append("moqieqty", getMoqieqty())
            .append("qiezhiqty", getQiezhiqty())
            .append("qty", getQty())
            .append("unit", getUnit())
            .append("unratio", getUnratio())
            .append("ruter", getRuter())
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
