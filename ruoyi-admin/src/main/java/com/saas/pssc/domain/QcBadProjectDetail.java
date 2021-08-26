package com.saas.pssc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 不良项目记录明细对象 qc_bad_project_detail
 * 
 * @author admin
 * @date 2021-08-03
 */
public class QcBadProjectDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 主表ID */
    private String mainId;

    /** 序号 */
    private int orderno;

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

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 有效否 0失效1有效 */
    private String isValid;
    
    /**  */
    private String attribute1;

    /**  */
    private String attribute3;

    /**  */
    private String attribute2;

    private String name;

    private Long value;

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
    
    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
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
    public void setAttribute3(String attribute3) 
    {
        this.attribute3 = attribute3;
    }

    public String getAttribute3() 
    {
        return attribute3;
    }
    public void setAttribute2(String attribute2) 
    {
        this.attribute2 = attribute2;
    }

    public String getAttribute2() 
    {
        return attribute2;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainId", getMainId())
            .append("cwork", getCwork())
            .append("cstation", getCstation())
            .append("lot", getLot())
            .append("project", getProject())
            .append("qty", getQty())
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
            .append("attribute3", getAttribute3())
            .append("attribute2", getAttribute2())
            .toString();
    }

}
