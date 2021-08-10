package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 合格供方名录对象 bs_vendor
 * 
 * @author admin
 * @date 2021-07-15
 */
public class BsVendor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private Long id;

    /** 供方分类 */
    @Excel(name = "供方分类",readConverterExp = "0=主材供应商,1=辅材供应商,2=未知")
    private String category;

    /** 供方编号 */
    @Excel(name = "供方编号")
    private String vcode;

    /** 供方名称 */
    @Excel(name = "供方名称")
    private String name;

    /** 供方电话 */
    @Excel(name = "供方电话")
    private String phone;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contractUser;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contractMobile;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 有效否 */
    private String isValid;
    
    /** 备注 */
    @Excel(name = "备注")
    private String remark;
    
    private String attribute1;
 
    private String attribute2;

    private String attribute3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setVcode(String vcode) 
    {
        this.vcode = vcode;
    }

    public String getVcode() 
    {
        return vcode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setContractUser(String contractUser) 
    {
        this.contractUser = contractUser;
    }

    public String getContractUser() 
    {
        return contractUser;
    }
    public void setContractMobile(String contractMobile) 
    {
        this.contractMobile = contractMobile;
    }

    public String getContractMobile() 
    {
        return contractMobile;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("Id", getId())
            .append("category", getCategory())
            .append("vcode", getVcode())
            .append("name", getName())
            .append("phone", getPhone())
            .append("contractUser", getContractUser())
            .append("contractMobile", getContractMobile())
            .append("address", getAddress())
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
