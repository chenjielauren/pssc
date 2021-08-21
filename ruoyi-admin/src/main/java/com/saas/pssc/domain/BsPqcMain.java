package com.saas.pssc.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 产品检验标准对象 bs_pqc_main
 * 
 * @author admin
 * @date 2021-07-15
 */
public class BsPqcMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    private String id;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String pcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String spec;

    /** 检验工序 */
    @Excel(name = "检验工序")
    private String procName;

    /** 是否有效 */
    private String isValid;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    /** 产品检验标准明细信息 */
    private List<BsPqcDetail> bsPqcDetailList;

    /** 首检标准明细信息 */
    private List<BsPqcDetail> sjDetailList;

    /** 自检标准明细信息 */
    private List<BsPqcDetail> zjDetailList;

    /** 专检验标准明细信息 */
    private List<BsPqcDetail> xjDetailList;

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
    
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
    public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<BsPqcDetail> getBsPqcDetailList()
    {
        return bsPqcDetailList;
    }

    public void setBsPqcDetailList(List<BsPqcDetail> bsPqcDetailList)
    {
        this.bsPqcDetailList = bsPqcDetailList;
    }

    
    public List<BsPqcDetail> getSjDetailList() {
        return sjDetailList;
    }

    public void setSjDetailList(List<BsPqcDetail> sjDetailList) {
        this.sjDetailList = sjDetailList;
    }

    public List<BsPqcDetail> getZjDetailList() {
        return zjDetailList;
    }

    public void setZjDetailList(List<BsPqcDetail> zjDetailList) {
        this.zjDetailList = zjDetailList;
    }

    public List<BsPqcDetail> getXjDetailList() {
        return xjDetailList;
    }

    public void setXjDetailList(List<BsPqcDetail> xjDetailList) {
        this.xjDetailList = xjDetailList;
    }   
    

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("Id", getId())
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
            .append("bsPqcDetailList", getBsPqcDetailList())
            .toString();
    }	


}
