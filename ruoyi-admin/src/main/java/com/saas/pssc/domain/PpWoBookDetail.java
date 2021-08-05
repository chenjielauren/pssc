package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工单制造信息对象 pp_wo_book_detail
 * 
 * @author admin
 * @date 2021-08-03
 */
public class PpWoBookDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 主表ID */
    private String mainId;

    /** 子工单号 */
    @Excel(name = "子工单号")
    private String soncode;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String mcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String mname;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String mspec;

    /** 开工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "开工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date stime;

    /** 完工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "完工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date etime;

    /** 生产工序 */
    @Excel(name = "生产工序")
    private String cwork;

    /** 生产机台 */
    @Excel(name = "生产机台")
    private String cstation;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private String planqty;

    /** 投入数量 */
    @Excel(name = "投入数量")
    private String inputqty;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private String outputqty;

    /** 标准耗时（小时） */
    @Excel(name = "标准耗时")
    private String stdtime;

    /** 标准成品率% */
    @Excel(name = "标准成品率%")
    private String stdrate;

    /** 实际耗时（小时） */
    @Excel(name = "实际耗时")
    private String acttime;

    /** 实际成品率% */
    @Excel(name = "实际成品率%")
    private String actrate;

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
    public void setSoncode(String soncode) 
    {
        this.soncode = soncode;
    }

    public String getSoncode() 
    {
        return soncode;
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
    public void setStime(Date stime) 
    {
        this.stime = stime;
    }

    public Date getStime() 
    {
        return stime;
    }
    public void setEtime(Date etime) 
    {
        this.etime = etime;
    }

    public Date getEtime() 
    {
        return etime;
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
    public void setPlanqty(String planqty) 
    {
        this.planqty = planqty;
    }

    public String getPlanqty() 
    {
        return planqty;
    }
    public void setInputqty(String inputqty) 
    {
        this.inputqty = inputqty;
    }

    public String getInputqty() 
    {
        return inputqty;
    }
    public void setOutputqty(String outputqty) 
    {
        this.outputqty = outputqty;
    }

    public String getOutputqty() 
    {
        return outputqty;
    }
    public void setStdtime(String stdtime) 
    {
        this.stdtime = stdtime;
    }

    public String getStdtime() 
    {
        return stdtime;
    }
    public void setStdrate(String stdrate) 
    {
        this.stdrate = stdrate;
    }

    public String getStdrate() 
    {
        return stdrate;
    }
    public void setActtime(String acttime) 
    {
        this.acttime = acttime;
    }

    public String getActtime() 
    {
        return acttime;
    }
    public void setActrate(String actrate) 
    {
        this.actrate = actrate;
    }

    public String getActrate() 
    {
        return actrate;
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
            .append("soncode", getSoncode())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("mspec", getMspec())
            .append("stime", getStime())
            .append("etime", getEtime())
            .append("cwork", getCwork())
            .append("cstation", getCstation())
            .append("planqty", getPlanqty())
            .append("inputqty", getInputqty())
            .append("outputqty", getOutputqty())
            .append("stdtime", getStdtime())
            .append("stdrate", getStdrate())
            .append("acttime", getActtime())
            .append("actrate", getActrate())
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
