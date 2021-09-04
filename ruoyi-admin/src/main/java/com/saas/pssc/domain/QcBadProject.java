package com.saas.pssc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 不良项目汇总对象 qc_bad_project
 * 
 * @author admin
 * @date 2021-09-04
 */
public class QcBadProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 工单号 */
    @Excel(name = "工单号")
    private String wcode;

    /** 成品编号 */
    @Excel(name = "成品编号")
    private String pcode;

    /** 成品名称 */
    @Excel(name = "成品名称")
    private String pname;

    /** 不良项目 */
    @Excel(name = "不良项目")
    private String project;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String pspec;

    /** 批次号 */
    @Excel(name = "批次号")
    private String lot;

    /** 投入数量 */
    @Excel(name = "投入数量")
    private BigDecimal inqty;

    /** 不良数量 */
    @Excel(name = "不良数量")
    private Long qty;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private BigDecimal outqty;

    /** 良品率 */
    @Excel(name = "良品率")
    private String okratio;

    /** 有效否 */
    private String isValid;

    /** 发生工序 */
    @Excel(name = "发生工序")
    private String cwork;

    /** 发生机台 */
    @Excel(name = "发生机台")
    private String cstation;

    /**  */
    private String attribute1;

    /** 不良描述 */
    @Excel(name = "不良描述")
    private String memo;

    /**  */
    private String attribute2;

    /** 临时对策 */
    @Excel(name = "临时对策")
    private String tempway;

    /**  */
    private String attribute3;

    /** 原因分析 */
    @Excel(name = "原因分析")
    private String cause;

    /** 原因对策 */
    @Excel(name = "原因对策")
    private String causeway;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String handleway;

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
    public void setProject(String project) 
    {
        this.project = project;
    }

    public String getProject() 
    {
        return project;
    }
    public void setPspec(String pspec) 
    {
        this.pspec = pspec;
    }

    public String getPspec() 
    {
        return pspec;
    }
    public void setLot(String lot) 
    {
        this.lot = lot;
    }

    public String getLot() 
    {
        return lot;
    }
    public void setInqty(BigDecimal inqty) 
    {
        this.inqty = inqty;
    }

    public BigDecimal getInqty() 
    {
        return inqty;
    }
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
    }
    public void setOutqty(BigDecimal outqty) 
    {
        this.outqty = outqty;
    }

    public BigDecimal getOutqty() 
    {
        return outqty;
    }
    public void setOkratio(String okratio) 
    {
        this.okratio = okratio;
    }

    public String getOkratio() 
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
    public void setCwork(String cwork) 
    {
        this.cwork = cwork;
    }

    public String getCwork() 
    {
        return cwork;
    }
    public void setCstation(String cstation) 
    {
        this.cstation = cstation;
    }

    public String getCstation() 
    {
        return cstation;
    }
    public void setAttribute1(String attribute1) 
    {
        this.attribute1 = attribute1;
    }

    public String getAttribute1() 
    {
        return attribute1;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setAttribute2(String attribute2) 
    {
        this.attribute2 = attribute2;
    }

    public String getAttribute2() 
    {
        return attribute2;
    }
    public void setTempway(String tempway) 
    {
        this.tempway = tempway;
    }

    public String getTempway() 
    {
        return tempway;
    }
    public void setAttribute3(String attribute3) 
    {
        this.attribute3 = attribute3;
    }

    public String getAttribute3() 
    {
        return attribute3;
    }
    public void setCause(String cause) 
    {
        this.cause = cause;
    }

    public String getCause() 
    {
        return cause;
    }
    public void setCauseway(String causeway) 
    {
        this.causeway = causeway;
    }

    public String getCauseway() 
    {
        return causeway;
    }
    public void setHandleway(String handleway) 
    {
        this.handleway = handleway;
    }

    public String getHandleway() 
    {
        return handleway;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wcode", getWcode())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("project", getProject())
            .append("pspec", getPspec())
            .append("lot", getLot())
            .append("inqty", getInqty())
            .append("qty", getQty())
            .append("outqty", getOutqty())
            .append("remark", getRemark())
            .append("okratio", getOkratio())
            .append("isValid", getIsValid())
            .append("cwork", getCwork())
            .append("createBy", getCreateBy())
            .append("cstation", getCstation())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("memo", getMemo())
            .append("attribute2", getAttribute2())
            .append("tempway", getTempway())
            .append("attribute3", getAttribute3())
            .append("cause", getCause())
            .append("causeway", getCauseway())
            .append("handleway", getHandleway())
            .toString();
    }
}
