package com.saas.pssc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工序成品率对象 pp_work_yield
 * 
 * @author admin
 * @date 2021-07-25
 */
public class PpWorkYield extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划工单号ID */
    private Long id;

    /** 计划工单号 */
    @Excel(name = "计划工单号")
    private String wcode;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private Long qty;

    /** 生产工序 */
    @Excel(name = "生产工序")
    private String pwork;

    /** 生产机台 */
    @Excel(name = "生产机台")
    private String machine;

    /** 标准成品率 */
    @Excel(name = "标准成品率")
    private BigDecimal standardYield;

    /** 实际成品率 */
    @Excel(name = "实际成品率")
    private BigDecimal actualYield;

    /** 有效否 */
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
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
    }
    public void setPwork(String pwork) 
    {
        this.pwork = pwork;
    }

    public String getPwork() 
    {
        return pwork;
    }
    public void setMachine(String machine) 
    {
        this.machine = machine;
    }

    public String getMachine() 
    {
        return machine;
    }
    public void setStandardYield(BigDecimal standardYield) 
    {
        this.standardYield = standardYield;
    }

    public BigDecimal getStandardYield() 
    {
        return standardYield;
    }
    public void setActualYield(BigDecimal actualYield) 
    {
        this.actualYield = actualYield;
    }

    public BigDecimal getActualYield() 
    {
        return actualYield;
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
            .append("wcode", getWcode())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("qty", getQty())
            .append("pwork", getPwork())
            .append("machine", getMachine())
            .append("standardYield", getStandardYield())
            .append("actualYield", getActualYield())
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
