package com.kl.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kl.common.annotation.Excel;
import com.kl.common.core.domain.BaseEntity;

/**
 * 合格供方名录对象 standardinfo_vendor
 * 
 * @author admin
 * @date 2021-07-14
 */
public class StandardinfoVendor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    private Long vendorId;

    /** 供应商分类（0主材供应商 1辅材供应商 2未知） */
    @Excel(name = "供应商分类", readConverterExp = "0=主材供应商,1=辅材供应商,2=未知")
    private String vendorCategory;

    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private String vendorNumber;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String vendorName;

    /** 供方电话 */
    @Excel(name = "供方电话")
    private String vendorPhone;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contractName;

    /** 联系手机 */
    @Excel(name = "联系手机")
    private String contractMobile;

    /** 地址 */
    @Excel(name = "地址")
    private String vendorAddr;

    /** Y:有效;N:无效 */
    @Excel(name = "Y:有效;N:无效")
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    public void setVendorId(Long vendorId) 
    {
        this.vendorId = vendorId;
    }

    public Long getVendorId() 
    {
        return vendorId;
    }
    public void setVendorCategory(String vendorCategory) 
    {
        this.vendorCategory = vendorCategory;
    }

    public String getVendorCategory() 
    {
        return vendorCategory;
    }
    public void setVendorNumber(String vendorNumber) 
    {
        this.vendorNumber = vendorNumber;
    }

    public String getVendorNumber() 
    {
        return vendorNumber;
    }
    public void setVendorName(String vendorName) 
    {
        this.vendorName = vendorName;
    }

    public String getVendorName() 
    {
        return vendorName;
    }
    public void setVendorPhone(String vendorPhone) 
    {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorPhone() 
    {
        return vendorPhone;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setContractMobile(String contractMobile) 
    {
        this.contractMobile = contractMobile;
    }

    public String getContractMobile() 
    {
        return contractMobile;
    }
    public void setVendorAddr(String vendorAddr) 
    {
        this.vendorAddr = vendorAddr;
    }

    public String getVendorAddr() 
    {
        return vendorAddr;
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
            .append("vendorId", getVendorId())
            .append("vendorCategory", getVendorCategory())
            .append("vendorNumber", getVendorNumber())
            .append("vendorName", getVendorName())
            .append("vendorPhone", getVendorPhone())
            .append("contractName", getContractName())
            .append("contractMobile", getContractMobile())
            .append("vendorAddr", getVendorAddr())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("isValid", getIsValid())
            .append("remark", getRemark())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .toString();
    }
}
