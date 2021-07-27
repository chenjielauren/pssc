package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 生产异常记录对象 pp_produce_unusual
 * 
 * @author admin
 * @date 2021-07-23
 */
public class PpProduceUnusual extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划工单号ID */
    private Long id;

    /** 计划工单号 */
    @Excel(name = "计划工单号")
    private String wcode;

    /** 异常发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "异常发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date utime;

    /** 异常处理人 */
    @Excel(name = "异常处理人")
    private String uuser;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 异常发生工序 */
    @Excel(name = "异常发生工序")
    private String pwork;

    /** 异常影响数量 */
    @Excel(name = "异常影响数量")
    private Long qty;

    /** 异常持续时间(小时) */
    @Excel(name = "异常持续时间(小时)")
    private Double lastTime;

    /** 问题项目描述 */
    @Excel(name = "问题项目描述")
    private String problemDesc;

    /** 有效否 0失效1有效 */
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    private Date uStarttime;//异常开始时间

    private Date uEndtime;//异常结束时间
    

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
    public void setUtime(Date utime) 
    {
        this.utime = utime;
    }

    public Date getUtime() 
    {
        return utime;
    }
    public void setUuser(String uuser) 
    {
        this.uuser = uuser;
    }

    public String getUuser() 
    {
        return uuser;
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
    public void setPwork(String pwork) 
    {
        this.pwork = pwork;
    }

    public String getPwork() 
    {
        return pwork;
    }
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
    }
    public void setLastTime(Double lastTime) 
    {
        this.lastTime = lastTime;
    }

    public Double getLastTime() 
    {
        return lastTime;
    }
    public void setProblemDesc(String problemDesc) 
    {
        this.problemDesc = problemDesc;
    }

    public String getProblemDesc() 
    {
        return problemDesc;
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

    
    public Date getuStarttime() {
        return uStarttime;
    }

    public void setuStarttime(Date uStarttime) {
        this.uStarttime = uStarttime;
    }

    public Date getuEndtime() {
        return uEndtime;
    }

    public void setuEndtime(Date uEndtime) {
        this.uEndtime = uEndtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wcode", getWcode())
            .append("utime", getUtime())
            .append("uuser", getUuser())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("pwork", getPwork())
            .append("qty", getQty())
            .append("lastTime", getLastTime())
            .append("problemDesc", getProblemDesc())
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
