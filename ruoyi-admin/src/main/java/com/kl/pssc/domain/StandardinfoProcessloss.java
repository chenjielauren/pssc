package com.kl.pssc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kl.common.annotation.Excel;
import com.kl.common.core.domain.BaseEntity;

/**
 * 工序损耗标准对象 standardinfo_processloss
 * 
 * @author ruoyi
 * @date 2021-07-14
 */
public class StandardinfoProcessloss extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序损耗标准ID */
    private Long processlossId;

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String processNumber;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 工序产能 */
    @Excel(name = "工序产能")
    private String processCapacity;

    /** 转换时间 */
    @Excel(name = "转换时间")
    private String convertTime;

    /** 损耗率 */
    @Excel(name = "损耗率")
    private String lossRate;

    /** Y:有效;N:无效 */
    @Excel(name = "Y:有效;N:无效")
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

    public void setProcesslossId(Long processlossId) 
    {
        this.processlossId = processlossId;
    }

    public Long getProcesslossId() 
    {
        return processlossId;
    }
    public void setProcessNumber(String processNumber) 
    {
        this.processNumber = processNumber;
    }

    public String getProcessNumber() 
    {
        return processNumber;
    }
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }
    public void setProcessCapacity(String processCapacity) 
    {
        this.processCapacity = processCapacity;
    }

    public String getProcessCapacity() 
    {
        return processCapacity;
    }
    public void setConvertTime(String convertTime) 
    {
        this.convertTime = convertTime;
    }

    public String getConvertTime() 
    {
        return convertTime;
    }
    public void setLossRate(String lossRate) 
    {
        this.lossRate = lossRate;
    }

    public String getLossRate() 
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
            .append("processlossId", getProcesslossId())
            .append("processNumber", getProcessNumber())
            .append("processName", getProcessName())
            .append("processCapacity", getProcessCapacity())
            .append("convertTime", getConvertTime())
            .append("lossRate", getLossRate())
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
