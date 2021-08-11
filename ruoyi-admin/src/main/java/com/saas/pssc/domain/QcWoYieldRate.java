package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工单良品率分析对象 qc_wo_yield_rate
 * 
 * @author admin
 * @date 2021-07-25
 */
public class QcWoYieldRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划工单号ID */
    private String id;

    /** 工单号 */
    @Excel(name = "工单号")
    private String wo;

    /** 成品编号 */
    @Excel(name = "成品编号")
    private String pcode;

    /** 成品名称 */
    @Excel(name = "成品名称")
    private String pname;

    /** 投入数量 */
    @Excel(name = "投入数量")
    private Long iqty;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private Long oqty;

    /** 良品率 */
    @Excel(name = "良品率")
    private Double rate;

    /** 有效否 */
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
    public void setWo(String wo) 
    {
        this.wo = wo;
    }

    public String getWo() 
    {
        return wo;
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
    public void setIqty(Long iqty) 
    {
        this.iqty = iqty;
    }

    public Long getIqty() 
    {
        return iqty;
    }
    public void setOqty(Long oqty) 
    {
        this.oqty = oqty;
    }

    public Long getOqty() 
    {
        return oqty;
    }
    public void setRate(Double rate) 
    {
        this.rate = rate;
    }

    public Double getRate() 
    {
        return rate;
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
            .append("wo", getWo())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("iqty", getIqty())
            .append("oqty", getOqty())
            .append("rate", getRate())
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
