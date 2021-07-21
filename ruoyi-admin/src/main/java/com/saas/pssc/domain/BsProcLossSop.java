package com.saas.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 工序损耗标准对象 bs_proc_loss_sop
 * 
 * @author admin
 * @date 2021-07-15
 */
public class BsProcLossSop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private Long id;

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String pcode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String name;

    /** 工序产能 */
    @Excel(name = "工序产能")
    private Long capacity;

    /** 转换时间 */
    @Excel(name = "转换时间")
    private Long convertTime;

    /** 损耗率 */
    @Excel(name = "损耗率")
    private Long lossRate;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private String isValid;

    /**  */
    @Excel(name = "")
    private String attribute1;

    /**  */
    @Excel(name = "")
    private String attribute2;

    /**  */
    @Excel(name = "")
    private String attribute3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }
    public void setConvertTime(Long convertTime) 
    {
        this.convertTime = convertTime;
    }

    public Long getConvertTime() 
    {
        return convertTime;
    }
    public void setLossRate(Long lossRate) 
    {
        this.lossRate = lossRate;
    }

    public Long getLossRate() 
    {
        return lossRate;
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
            .append("pcode", getPcode())
            .append("name", getName())
            .append("capacity", getCapacity())
            .append("convertTime", getConvertTime())
            .append("lossRate", getLossRate())
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
