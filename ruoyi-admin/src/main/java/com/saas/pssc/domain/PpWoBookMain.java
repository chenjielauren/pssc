package com.saas.pssc.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工单记录对象 pp_wo_book_main
 * 
 * @author admin
 * @date 2021-08-03
 */
public class PpWoBookMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 工单号 */
    @Excel(name = "工单号")
    private String wcode;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderno;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pname;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String pspec;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private BigDecimal qty;

    /** 开工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "开工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date pstarTime;

    /** 完工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "完工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date pendTime;

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

    /** 工单制造信息信息 */
    private List<PpWoBookDetail> ppWoBookDetailList;

    /** 工单BOM信息 */
    private List<PpWoBookBom> ppWoBookBomList;

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
    public void setOrderno(String orderno) 
    {
        this.orderno = orderno;
    }

    public String getOrderno() 
    {
        return orderno;
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
    public void setQty(BigDecimal qty) 
    {
        this.qty = qty;
    }

    public BigDecimal getQty() 
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

    public List<PpWoBookDetail> getPpWoBookDetailList()
    {
        return ppWoBookDetailList;
    }

    public void setPpWoBookDetailList(List<PpWoBookDetail> ppWoBookDetailList)
    {
        this.ppWoBookDetailList = ppWoBookDetailList;
    }

    public List<PpWoBookBom> getPpWoBookBomList() {
        return ppWoBookBomList;
    }

    public void setPpWoBookBomList(List<PpWoBookBom> ppWoBookBomList) {
        this.ppWoBookBomList = ppWoBookBomList;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wcode", getWcode())
            .append("orderno", getOrderno())
            .append("pcode", getPcode())
            .append("pname", getPname())
            .append("pspec", getPspec())
            .append("qty", getQty())
            .append("pstarTime", getPstarTime())
            .append("pendTime", getPendTime())
            .append("remark", getRemark())
            .append("isValid", getIsValid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .append("ppWoBookDetailList", getPpWoBookDetailList())
            .toString();
    }

}
