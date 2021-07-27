package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工单上线计划对象 pp_wo_online_plan
 * 
 * @author admin
 * @date 2021-07-23
 */
public class PpWoOnlinePlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划工单号ID */
    private Long id;

    /** 计划工单号 */
    @Excel(name = "计划工单号")
    private String wcode;

    /** 计划编制时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "计划编制时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date ptime;

    /** 计划编制人 */
    @Excel(name = "计划编制人")
    private String puser;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private Long qty;

    /** 计划开工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Excel(name = "计划开工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date pstarTime;

    /** 计划完工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Excel(name = "计划完工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pendTime;

    /** 工序 */
    @Excel(name = "工序")
    private String pwork;

    /** 机台 */
    @Excel(name = "机台")
    private String machine;

    /** 转换时间(小时) */
    @Excel(name = "转换时间(小时)")
    private Double changeTimes;

    /** 生产耗时(小时) */
    @Excel(name = "生产耗时(小时)")
    private Double produceTimes;

    /** 有效否 0失效1有效 */
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
    public void setPtime(Date ptime) 
    {
        this.ptime = ptime;
    }

    public Date getPtime() 
    {
        return ptime;
    }
    public void setPuser(String puser) 
    {
        this.puser = puser;
    }

    public String getPuser() 
    {
        return puser;
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
    public void setPstarTime(Date pstarTime) 
    {
        this.pstarTime = pstarTime;
    }

    public Date getPstarTime() 
    {
        return pstarTime;
    }
    public void setPendTime(Date pendTime) 
    {
        this.pendTime = pendTime;
    }

    public Date getPendTime() 
    {
        return pendTime;
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
    public void setChangeTimes(Double changeTimes) 
    {
        this.changeTimes = changeTimes;
    }

    public Double getChangeTimes() 
    {
        return changeTimes;
    }
    public void setProduceTimes(Double produceTimes) 
    {
        this.produceTimes = produceTimes;
    }

    public Double getProduceTimes() 
    {
        return produceTimes;
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
            .append("ptime", getPtime())
            .append("puser", getPuser())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("qty", getQty())
            .append("pstarTime", getPstarTime())
            .append("pendTime", getPendTime())
            .append("pwork", getPwork())
            .append("machine", getMachine())
            .append("changeTimes", getChangeTimes())
            .append("produceTimes", getProduceTimes())
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