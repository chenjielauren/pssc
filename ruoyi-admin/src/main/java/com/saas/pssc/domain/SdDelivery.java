package com.saas.pssc.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 发货信息对象 sd_delivery
 * 
 * @author admin
 * @date 2021-08-01
 */
public class SdDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发货ID */
    private String id;

    /** 发货批次号 */
    @Excel(name = "发货批次号")
    private String dlot;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dtime;

    /** 订单号 */
    @Excel(name = "订单号")
    private String dcode;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String pspec;

    /** 成品批次号 */
    @Excel(name = "成品批次号")
    private String flot;

    /** 发货数量 */
    @Excel(name = "发货数量")
    private Double qty;

    /** 有效否 0失效1有效 */
    private String isValid;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 成品检验状态
     *  成品检验和过程检验下所有单据中的状态，中间有一条为不合格即为异常，如果查询无数据即为未填报
     */
    private String ppState;
    
    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

     /** 发货单标注明细信息 */
     private List<SdDeliveryDetail> sdDeliveryDetailList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDlot(String dlot) 
    {
        this.dlot = dlot;
    }

    public String getDlot() 
    {
        return dlot;
    }
    
    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public void setDcode(String dcode) 
    {
        this.dcode = dcode;
    }

    public String getDcode() 
    {
        return dcode;
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
    public void setFlot(String flot) 
    {
        this.flot = flot;
    }

    public String getFlot() 
    {
        return flot;
    }
    public void setQty(Double qty) 
    {
        this.qty = qty;
    }

    public Double getQty() 
    {
        return qty;
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
    public String getPpState() {
        return ppState;
    }

    public void setPpState(String ppState) {
        this.ppState = ppState;
    }
    public List<SdDeliveryDetail> getSdDeliveryDetailList()
    {
        return sdDeliveryDetailList;
    }

    public void setSdDeliveryDetailList(List<SdDeliveryDetail> sdDeliveryDetailList)
    {
        this.sdDeliveryDetailList = sdDeliveryDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dlot", getDlot())
            .append("dcode", getDcode())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("pspec", getPspec())
            .append("flot", getFlot())
            .append("qty", getQty())
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
