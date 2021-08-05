package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 4M变更单明细对象 bs_mcn_detail
 * 
 * @author admin
 * @date 2021-07-19
 */
public class BsMcnDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private Long id;

    /** 主表ID */
    private Long mainId;

    /** 变更单号 */
    @Excel(name = "变更单号")
    private String mcode;

    /** 变更日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Excel(name = "变更日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date mdate;

    /** 变更内容 */
    @Excel(name = "变更内容")
    private String mcontent;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String attachment;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadUser;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

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
    public void setMcode(String mcode) 
    {
        this.mcode = mcode;
    }

    public String getMcode() 
    {
        return mcode;
    }
    public void setMdate(Date mdate) 
    {
        this.mdate = mdate;
    }

    public Date getMdate() 
    {
        return mdate;
    }
    public void setMcontent(String mcontent) 
    {
        this.mcontent = mcontent;
    }

    public String getMcontent() 
    {
        return mcontent;
    }
    public void setAttachment(String attachment) 
    {
        this.attachment = attachment;
    }

    public String getAttachment() 
    {
        return attachment;
    }
    public void setUploadUser(String uploadUser) 
    {
        this.uploadUser = uploadUser;
    }

    public String getUploadUser() 
    {
        return uploadUser;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
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
            .append("mdate", getMdate())
            .append("mcontent", getMcontent())
            .append("attachment", getAttachment())
            .append("uploadUser", getUploadUser())
            .append("uploadTime", getUploadTime())
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
