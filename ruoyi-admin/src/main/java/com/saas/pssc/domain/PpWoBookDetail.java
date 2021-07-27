package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工单报工记录明细对象 pp_wo_book_detail
 * 
 * @author admin
 * @date 2021-07-24
 */
public class PpWoBookDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 主表ID */
    private Long mainId;

    /** 材料类型 0用料信息 1工序信息 2未知 */
    private String mtype;

    /** 材料编号 */
    @Excel(name = "材料编号")
    private String mcode;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String mname;

    /** 出库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "出库时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date otime;

    /** 出库批次号 */
    @Excel(name = "出库批次号")
    private String olot;

    /** 材料检验结果 */
    @Excel(name = "材料检验结果",readConverterExp = "0=异常,1=合格,2=未知")
    private String qcResult;

    /** 预计上线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Excel(name = "预计上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expectOtime;

    /** 有效否 0失效1有效 */
    private String isValid;

    /** 加工机台编号 */
    @Excel(name = "加工机台编号")
    private String pmcode;

    /** 加工机台名称 */
    @Excel(name = "加工机台名称")
    private String pmname;

    /** 加工人员编号 */
    @Excel(name = "加工人员编号")
    private String ppcode;

    /** 加工人员名称 */
    @Excel(name = "加工人员名称")
    private String ppname;

    /** 投入数量 */
    @Excel(name = "投入数量")
    private Long incount;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private Long outcount;

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
    public void setMainId(Long mainId) 
    {
        this.mainId = mainId;
    }

    public Long getMainId() 
    {
        return mainId;
    }
    public void setMtype(String mtype) 
    {
        this.mtype = mtype;
    }

    public String getMtype() 
    {
        return mtype;
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
    public void setOtime(Date otime) 
    {
        this.otime = otime;
    }

    public Date getOtime() 
    {
        return otime;
    }
    public void setOlot(String olot) 
    {
        this.olot = olot;
    }

    public String getOlot() 
    {
        return olot;
    }
    public void setQcResult(String qcResult) 
    {
        this.qcResult = qcResult;
    }

    public String getQcResult() 
    {
        return qcResult;
    }
    public void setExpectOtime(Date expectOtime) 
    {
        this.expectOtime = expectOtime;
    }

    public Date getExpectOtime() 
    {
        return expectOtime;
    }
    public void setIsValid(String isValid) 
    {
        this.isValid = isValid;
    }

    public String getIsValid() 
    {
        return isValid;
    }
    public void setPmcode(String pmcode) 
    {
        this.pmcode = pmcode;
    }

    public String getPmcode() 
    {
        return pmcode;
    }
    public void setPmname(String pmname) 
    {
        this.pmname = pmname;
    }

    public String getPmname() 
    {
        return pmname;
    }
    public void setPpcode(String ppcode) 
    {
        this.ppcode = ppcode;
    }

    public String getPpcode() 
    {
        return ppcode;
    }
    public void setPpname(String ppname) 
    {
        this.ppname = ppname;
    }

    public String getPpname() 
    {
        return ppname;
    }
    public void setIncount(Long incount) 
    {
        this.incount = incount;
    }

    public Long getIncount() 
    {
        return incount;
    }
    public void setOutcount(Long outcount) 
    {
        this.outcount = outcount;
    }

    public Long getOutcount() 
    {
        return outcount;
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
            .append("mtype", getMtype())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("otime", getOtime())
            .append("olot", getOlot())
            .append("qcResult", getQcResult())
            .append("expectOtime", getExpectOtime())
            .append("isValid", getIsValid())
            .append("pmcode", getPmcode())
            .append("pmname", getPmname())
            .append("ppcode", getPpcode())
            .append("ppname", getPpname())
            .append("incount", getIncount())
            .append("outcount", getOutcount())
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