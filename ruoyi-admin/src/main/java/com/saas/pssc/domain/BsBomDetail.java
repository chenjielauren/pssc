package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * bom物料清单明细对象 bs_bom_detail
 * 
 * @author admin
 * @date 2021-08-21
 */
public class BsBomDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private String id;

    /** 序号 */
    private Long orderno;

    /** 主表ID */
    private String mainId;

    /** 部件编号 */
    @Excel(name = "部件编号")
    private String mcode;

    /** 部件名称 */
    @Excel(name = "部件名称")
    private String mname;

    /** 部件规格 */
    @Excel(name = "部件规格")
    private String mspec;

    /** 部件属性(生产/采购) */
    @Excel(name = "部件属性",readConverterExp = "0=生产,1=采购,2=未知")
    private String attribute;

    /** 上机尺寸 */
    @Excel(name = "上机尺寸")
    private String onsize;

    /** 模切拼数 */
    @Excel(name = "模切拼数")
    private Long moqieqty;

    /** 切纸拼数 */
    @Excel(name = "切纸拼数")
    private Long qiezhiqty;  

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 损耗率 */
    @Excel(name = "损耗率")
    private String unratio;

    /** 工艺路线 */
    @Excel(name = "工艺路线")
    private String ruter;
    
    /** 用量 */
    @Excel(name = "用量")
    private Long qty;  

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
    public void setOrderno(Long orderno) 
    {
        this.orderno = orderno;
    }

    public Long getOrderno() 
    {
        return orderno;
    }
    public void setMainId(String mainId) 
    {
        this.mainId = mainId;
    }

    public String getMainId() 
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
    
    public String getMspec() {
        return mspec;
    }

    public void setMspec(String mspec) {
        this.mspec = mspec;
    }
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }

    public String getOnsize() {
        return onsize;
    }

    public void setOnsize(String onsize) {
        this.onsize = onsize;
    }
    
    public void setMoqieqty(Long moqieqty) 
    {
        this.moqieqty = moqieqty;
    }

    public Long getMoqieqty() 
    {
        return moqieqty;
    }
    public void setQiezhiqty(Long qiezhiqty) 
    {
        this.qiezhiqty = qiezhiqty;
    }

    public Long getQiezhiqty() 
    {
        return qiezhiqty;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
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
            .append("orderno", getOrderno())
            .append("mainId", getMainId())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("mspec", getMspec())
            .append("attribute", getAttribute())
            .append("onsize", getOnsize())
            .append("moqieqty", getMoqieqty())
            .append("qiezhiqty", getQiezhiqty())
            .append("unit", getUnit())
            .append("qty", getQty())
            .append("unratio", getUnratio())
            .append("remark", getRemark())
            .append("ruter", getRuter())
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
