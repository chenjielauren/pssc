package com.saas.pssc.domain;

import java.util.List;

import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工艺标准与CCP对象 bs_craft_sop_main
 * 
 * @author admin
 * @date 2021-07-19
 */
public class BsCraftSopMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工艺ID */
    private String id;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

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

    /** 工艺标准与CCP明细信息 */
    private List<BsCraftSopDetail> bsCraftSopDetailList;
    
    /** 技术标准信息 */
    private List<BsCraftSopDetail> bsSopTechDetailList;

    /** 工艺标准信息 */
    private List<BsCraftSopDetail> bsSopProdDetailList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPcode(String pcode) 
    {
        this.pcode = pcode;
    }

    public String getPcode() 
    {
        return pcode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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

    public List<BsCraftSopDetail> getBsCraftSopDetailList()
    {
        return bsCraftSopDetailList;
    }

    public void setBsCraftSopDetailList(List<BsCraftSopDetail> bsCraftSopDetailList)
    {
        this.bsCraftSopDetailList = bsCraftSopDetailList;
    }

    
    public List<BsCraftSopDetail> getBsSopTechDetailList() {
        return bsSopTechDetailList;
    }

    public void setBsSopTechDetailList(List<BsCraftSopDetail> bsSopTechDetailList) {
        this.bsSopTechDetailList = bsSopTechDetailList;
    }

    public List<BsCraftSopDetail> getBsSopProdDetailList() {
        return bsSopProdDetailList;
    }

    public void setBsSopProdDetailList(List<BsCraftSopDetail> bsSopProdDetailList) {
        this.bsSopProdDetailList = bsSopProdDetailList;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pcode", getPcode())
            .append("name", getName())
            .append("remark", getRemark())
            .append("isValid", getIsValid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .append("bsCraftSopDetailList", getBsCraftSopDetailList())
            .toString();
    }

}
