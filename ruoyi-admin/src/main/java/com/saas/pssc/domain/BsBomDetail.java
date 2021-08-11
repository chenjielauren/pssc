package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * bom物料清单明细对象 bs_bom_detail
 * 
 * @author admin
 * @date 2021-08-09
 */
public class BsBomDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private String id;

    /** 主表ID */
    private String mainId;

    /** 序号 */
    private int no;

    /** 部件编号 */
    @Excel(name = "部件编号")
    private String mcode;

    /** 部件名称 */
    @Excel(name = "部件名称")
    private String mname;

    /** 部件规格 */
    @Excel(name = "部件规格")
    private String spec;

    /** 部件属性 */
    @Excel(name = "部件属性",readConverterExp = "0=生产,1=采购,2=未知")
    private String attribute;

    /** 上机尺寸 */
    @Excel(name = "上机尺寸")
    private String msize;

    /** 模切拼数 */
    @Excel(name = "模切拼数")
    private Long dnumber;

    /** 切纸拼数 */
    @Excel(name = "切纸拼数")
    private Long pnumber;

    /** 用量 */
    @Excel(name = "用量")
    private Long qty;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 有效否 0失效1有效 */
    private String isValid;

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
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
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
    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
    }
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setMsize(String msize) 
    {
        this.msize = msize;
    }

    public String getMsize() 
    {
        return msize;
    }
    public void setDnumber(Long dnumber) 
    {
        this.dnumber = dnumber;
    }

    public Long getDnumber() 
    {
        return dnumber;
    }
    public void setPnumber(Long pnumber) 
    {
        this.pnumber = pnumber;
    }

    public Long getPnumber() 
    {
        return pnumber;
    }
    public void setQty(Long qty) 
    {
        this.qty = qty;
    }

    public Long getQty() 
    {
        return qty;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
            .append("mainId", getMainId())
            .append("mcode", getMcode())
            .append("mname", getMname())
            .append("spec", getSpec())
            .append("attribute", getAttribute())
            .append("msize", getMsize())
            .append("dnumber", getDnumber())
            .append("pnumber", getPnumber())
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
