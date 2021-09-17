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
 * @date 2021-09-17
 */
public class QcBadProjectMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID guid */
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
    private String okratio;

    /** 发生工序 */
    @Excel(name = "发生工序")
    private String cwork;

    /** 发生机台 */
    @Excel(name = "发生机台")
    private String cstation;

    /** 批次号 */
    @Excel(name = "批次号")
    private String lot;

    /** 不良项目名称 */
    @Excel(name = "不良项目名称")
    private String project;

    /** 不良项目数量 */
    @Excel(name = "不良项目数量")
    private BigDecimal qty;

    /** 报废数量 */
    @Excel(name = "报废数量")
    private BigDecimal unlessqty;

    /** 不良描述 */
    @Excel(name = "不良描述")
    private String memo;

    /** 临时对策 */
    @Excel(name = "临时对策")
    private String tempway;

    /** 原因分析 */
    @Excel(name = "原因分析")
    private String cause;

    /** 原因对策 */
    @Excel(name = "原因对策")
    private String causeway;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String handleway;

    /** 有效否 0失效1有效 */
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

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
    public void setOkratio(String okratio) 
    {
        this.okratio = okratio;
    }

    public String getOkratio() 
    {
        return okratio;
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
    public void setLot(String lot) 
    {
        this.lot = lot;
    }

    public String getLot() 
    {
        return lot;
    }
    public void setProject(String project) 
    {
        this.project = project;
    }

    public String getProject() 
    {
        return project;
    }
    public void setQty(BigDecimal qty) 
    {
        this.qty = qty;
    }

    public BigDecimal getQty() 
    {
        return qty;
    }
    public void setUnlessqty(BigDecimal unlessqty) 
    {
        this.unlessqty = unlessqty;
    }

    public BigDecimal getUnlessqty() 
    {
        return unlessqty;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setTempway(String tempway) 
    {
        this.tempway = tempway;
    }

    public String getTempway() 
    {
        return tempway;
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
            .append("cwork", getCwork())
            .append("cstation", getCstation())
            .append("lot", getLot())
            .append("project", getProject())
            .append("qty", getQty())
            .append("unlessqty", getUnlessqty())
            .append("memo", getMemo())
            .append("tempway", getTempway())
            .append("cause", getCause())
            .append("causeway", getCauseway())
            .append("handleway", getHandleway())
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
