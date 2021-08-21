package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 订单信息对象 sd_order
 * 
 * @author admin
 * @date 2021-07-22
 */
public class SdOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private String id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String ocode;

    /** 订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date otime;

    /** 订单数量 */
    @Excel(name = "订单数量")
    private Double qty;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 产品类别 */
    @Excel(name = "产品类别")
    private String ptype;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String pspec;

    /** 有效否 */
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

    /** 开始日期  */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date oStarttime;

    /** 结束日期  */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date oEndtime;

    private int ppCount;//查询订单下所有工单数据为空时显示未填报，有工单为正常

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOcode(String ocode) 
    {
        this.ocode = ocode;
    }

    public String getOcode() 
    {
        return ocode;
    }
    public void setOtime(Date otime) 
    {
        this.otime = otime;
    }

    public Date getOtime() 
    {
        return otime;
    }
    public void setQty(Double qty) 
    {
        this.qty = qty;
    }

    public Double getQty() 
    {
        return qty;
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
    public void setPtype(String ptype) 
    {
        this.ptype = ptype;
    }

    public String getPtype() 
    {
        return ptype;
    }
    public void setPspec(String pspec) 
    {
        this.pspec = pspec;
    }

    public String getPspec() 
    {
        return pspec;
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
    
    public Date getoStarttime() {
        return oStarttime;
    }

    public void setoStarttime(Date oStarttime) {
        this.oStarttime = oStarttime;
    }

    public Date getoEndtime() {
        return oEndtime;
    }

    public void setoEndtime(Date oEndtime) {
        this.oEndtime = oEndtime;
    }
    
    public int getPpCount() {
        return ppCount;
    }

    public void setPpCount(int ppCount) {
        this.ppCount = ppCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ocode", getOcode())
            .append("otime", getOtime())
            .append("qty", getQty())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("ptype", getPtype())
            .append("pspec", getPspec())
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
