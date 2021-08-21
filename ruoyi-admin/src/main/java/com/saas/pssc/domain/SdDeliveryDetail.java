package com.saas.pssc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.saas.common.annotation.Excel;
import com.saas.common.core.domain.BaseEntity;

/**
 * 发货单标注明细对象 sd_delivery_detail
 * 
 * @author admin
 * @date 2021-08-18
 */
public class SdDeliveryDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private String id;

    /** 主表ID */
    @Excel(name = "主表ID")
    private String mainId;

    /** 文件标题 */
    @Excel(name = "文件标题")
    private String fileTitle;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String fileUrl;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadUser;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    /** 有效否 0失效1有效 */
    @Excel(name = "有效否 0失效1有效")
    private String isValid;

    /**  */
    private String attribute1;

    /**  */
    private String attribute2;

    /**  */
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

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
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
            .append("fileName", getFileName())
            .append("fileUrl", getFileUrl())
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
