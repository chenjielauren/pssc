package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 成品检验明细对象 qc_prod_check_detail
 * 
 * @author admin
 * @date 2021-07-25
 */
public class QcProdCheckDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 主表ID */
    private Long mainId;

    /** 检验项目编号 */
    @Excel(name = "检验项目编号")
    private String projectCode;

    /** 检验项目 */
    @Excel(name = "检验项目")
    private String cproject;

    /** 检验标准 */
    @Excel(name = "检验标准")
    private String csop;

    /** 检验方法 */
    @Excel(name = "检验方法")
    private String cmethod;

    /** 检验结果 */
    @Excel(name = "检验结果",readConverterExp = "0=异常,1=合格,2=未知")
    private String qcResult;

    /** 有效否 */
    private String isValid;

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
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setCproject(String cproject) 
    {
        this.cproject = cproject;
    }

    public String getCproject() 
    {
        return cproject;
    }
    public void setCsop(String csop) 
    {
        this.csop = csop;
    }

    public String getCsop() 
    {
        return csop;
    }
    public void setCmethod(String cmethod) 
    {
        this.cmethod = cmethod;
    }

    public String getCmethod() 
    {
        return cmethod;
    }
    public void setQcResult(String qcResult) 
    {
        this.qcResult = qcResult;
    }

    public String getQcResult() 
    {
        return qcResult;
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
            .append("projectCode", getProjectCode())
            .append("cproject", getCproject())
            .append("csop", getCsop())
            .append("cmethod", getCmethod())
            .append("qcResult", getQcResult())
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
