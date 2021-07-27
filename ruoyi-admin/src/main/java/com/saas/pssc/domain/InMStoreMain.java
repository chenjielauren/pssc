package com.saas.pssc.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 库存对象 in_store_main
 * 
 * @author admin
 * @date 2021-07-22
 */
public class InMStoreMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 库存类型 0:材料库存 1:半成品库存 3:成品库存 */
    private String ptype;

    /** 计划工单号 */
    @Excel(name = "计划工单号")
    private String pcode;

    /** 入库人 */
    @Excel(name = "入库人")
    private String inputUser;

    /** 入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;

    /** 有效否 0失效1有效 */
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
    private String attribute3;

    /** 库存明细信息 */
    private List<InMStoreDetail> inStoreDetailList;

    /** 材料库存明细信息 */
    private List<InMStoreDetail> inMStoreDetailList;

    /** 半成品库存明细信息 */
    private List<InMStoreDetail> inSfStoreDetailList;

    /** 成品库存明细信息 */
    private List<InMStoreDetail> inFStoreDetailList;

    private Date inputStarttime;

    private Date inputEndtime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setInputUser(String inputUser) 
    {
        this.inputUser = inputUser;
    }

    public String getInputUser() 
    {
        return inputUser;
    }
    public void setInputTime(Date inputTime) 
    {
        this.inputTime = inputTime;
    }

    public Date getInputTime() 
    {
        return inputTime;
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

    
    public Date getInputStarttime() {
        return inputStarttime;
    }

    public void setInputStarttime(Date inputStarttime) {
        this.inputStarttime = inputStarttime;
    }

    public Date getInputEndtime() {
        return inputEndtime;
    }

    public void setInputEndtime(Date inputEndtime) {
        this.inputEndtime = inputEndtime;
    }

    public List<InMStoreDetail> getInStoreDetailList()
    {
        return inStoreDetailList;
    }

    public void setInStoreDetailList(List<InMStoreDetail> inStoreDetailList)
    {
        this.inStoreDetailList = inStoreDetailList;
    }

    
    public List<InMStoreDetail> getInMStoreDetailList() {
        return inMStoreDetailList;
    }

    public void setInMStoreDetailList(List<InMStoreDetail> inMStoreDetailList) {
        this.inMStoreDetailList = inMStoreDetailList;
    }

    public List<InMStoreDetail> getInSfStoreDetailList() {
        return inSfStoreDetailList;
    }

    public void setInSfStoreDetailList(List<InMStoreDetail> inSfStoreDetailList) {
        this.inSfStoreDetailList = inSfStoreDetailList;
    }

    public List<InMStoreDetail> getInFStoreDetailList() {
        return inFStoreDetailList;
    }

    public void setInFStoreDetailList(List<InMStoreDetail> inFStoreDetailList) {
        this.inFStoreDetailList = inFStoreDetailList;
    }

    

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ptype", getPtype())
            .append("pcode", getPcode())
            .append("inputUser", getInputUser())
            .append("inputTime", getInputTime())
            .append("remark", getRemark())
            .append("isValid", getIsValid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("attribute1", getAttribute1())
            .append("attribute2", getAttribute2())
            .append("attribute3", getAttribute3())
            .append("inStoreDetailList", getInStoreDetailList())
            .toString();
    }

}
