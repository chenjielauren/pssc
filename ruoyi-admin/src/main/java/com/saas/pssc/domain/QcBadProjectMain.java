package com.saas.pssc.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 不良项目记录对象 qc_bad_project_main
 * 
 * @author admin
 * @date 2021-08-03
 */
public class QcBadProjectMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 工单号 */
    @Excel(name = "工单号")
    private String wcode;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String pspec;

    /** 投入数量 */
    @Excel(name = "投入数量")
    private BigDecimal inqty;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private BigDecimal outqty;

    /** 良品率 */
    @Excel(name = "良品率")
    private BigDecimal okratio;

    /** 有效否 0失效1有效 */
    private String isValid;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;
    
    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    private String name;

    private Long value;

    /** 不良项目记录明细信息 */
    private List<QcBadProjectDetail> qcBadProjectDetailList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setWcode(String wcode) 
    {
        this.wcode = wcode;
    }

    public String getWcode() 
    {
        return wcode;
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
    public void setInqty(BigDecimal inqty) 
    {
        this.inqty = inqty;
    }

    public BigDecimal getInqty() 
    {
        return inqty;
    }
    public void setOutqty(BigDecimal outqty) 
    {
        this.outqty = outqty;
    }

    public BigDecimal getOutqty() 
    {
        return outqty;
    }
    public void setOkratio(BigDecimal okratio) 
    {
        this.okratio = okratio;
    }

    public BigDecimal getOkratio() 
    {
        return okratio;
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

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
    
    public List<QcBadProjectDetail> getQcBadProjectDetailList()
    {
        return qcBadProjectDetailList;
    }

    public void setQcBadProjectDetailList(List<QcBadProjectDetail> qcBadProjectDetailList)
    {
        this.qcBadProjectDetailList = qcBadProjectDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wcode", getWcode())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("pspec", getPspec())
            .append("inqty", getInqty())
            .append("outqty", getOutqty())
            .append("okratio", getOkratio())
            .append("remark", getRemark())
            .append("isValid", getIsValid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .append("qcBadProjectDetailList", getQcBadProjectDetailList())
            .toString();
    }
}
