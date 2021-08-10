package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 产品检验标准明细对象 bs_pqc_detail
 * 
 * @author admin
 * @date 2021-07-15
 */
public class BsPqcDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private String id;

    /** 主表ID */
    private String mainId;

    /** 检验类型 0首检 1自检 2巡检 3未知 */
    // @Excel(name = "检验类型", readConverterExp = "0=首检,1=自检,2=巡检,3=未知")
    private String ptype;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String pcode;

    /** 检验项目名称 */
    @Excel(name = "检验项目名称")
    private String name;

    /** 检验标准 */
    @Excel(name = "检验标准")
    private String standard;

    /** 检验方法 */
    @Excel(name = "检验方法")
    private String method;

    /** 有效否 */
    private String isValid;

    private String attribute1;

    private String attribute2;

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
    public void setPtype(String ptype) 
    {
        this.ptype = ptype;
    }

    public String getPtype() 
    {
        return ptype;
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
    public void setStandard(String standard) 
    {
        this.standard = standard;
    }

    public String getStandard() 
    {
        return standard;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
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
            .append("Id", getId())
            .append("mainId", getMainId())
            .append("ptype", getPtype())
            .append("pcode", getPcode())
            .append("name", getName())
            .append("standard", getStandard())
            .append("method", getMethod())
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
