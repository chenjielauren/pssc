package com.saas.pssc.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 原材料检验记录对象 qc_mat_check_main
 * 
 * @author admin
 * @date 2021-07-25
 */
public class QcMatCheckMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划工单号ID */
    private Long id;

    /** 检验单号 */
    @Excel(name = "检验单号")
    private String qcode;

    /** 材料编号 */
    @Excel(name = "材料编号")
    private String pcode;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String pname;

    /** 批次号 */
    @Excel(name = "批次号")
    private String lot;

    /** 检验人 */
    @Excel(name = "检验人员")
    private String quser;

    /** 检验时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "检验时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date qtime;

    /** 供方名称 */
    @Excel(name = "供方名称")
    private String vendor;

    /** 检验结果 */
    @Excel(name = "检验结果",readConverterExp = "0=异常,1=合格,2=未知")
    private String qcResult;

    /** 有效否 */
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    private Date qStarttime;//检验开始时间

    private Date qEndtime;//检验结束时间

    /** 材料检验信息 */
    private List<QcMatCheckDetail> qcMatCheckDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQcode(String qcode) 
    {
        this.qcode = qcode;
    }

    public String getQcode() 
    {
        return qcode;
    }
    public void setQtime(Date qtime) 
    {
        this.qtime = qtime;
    }

    public Date getQtime() 
    {
        return qtime;
    }
    public void setQuser(String quser) 
    {
        this.quser = quser;
    }

    public String getQuser() 
    {
        return quser;
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
    public void setLot(String lot) 
    {
        this.lot = lot;
    }

    public String getLot() 
    {
        return lot;
    }
    public void setVendor(String vendor) 
    {
        this.vendor = vendor;
    }

    public String getVendor() 
    {
        return vendor;
    }
    public void setQcResult(String qcResult) 
    {
        this.qcResult = qcResult;
    }

    public String getQcResult() 
    {
        return qcResult;
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

    
    public Date getqStarttime() {
        return qStarttime;
    }

    public void setqStarttime(Date qStarttime) {
        this.qStarttime = qStarttime;
    }

    public Date getqEndtime() {
        return qEndtime;
    }

    public void setqEndtime(Date qEndtime) {
        this.qEndtime = qEndtime;
    }

    public List<QcMatCheckDetail> getQcMatCheckDetailList()
    {
        return qcMatCheckDetailList;
    }

    public void setQcMatCheckDetailList(List<QcMatCheckDetail> qcMatCheckDetailList)
    {
        this.qcMatCheckDetailList = qcMatCheckDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qcode", getQcode())
            .append("qtime", getQtime())
            .append("quser", getQuser())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("lot", getLot())
            .append("vendor", getVendor())
            .append("qcResult", getQcResult())
            .append("remark", getRemark())
            .append("isValid", getIsValid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .append("qcMatCheckDetailList", getQcMatCheckDetailList())
            .toString();
    }
    
}
