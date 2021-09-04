package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcBadProject;

/**
 * 不良项目汇总Service接口
 * 
 * @author admin
 * @date 2021-09-04
 */
public interface IQcBadProjectService 
{
    /**
     * 查询不良项目汇总
     * 
     * @param id 不良项目汇总ID
     * @return 不良项目汇总
     */
    public QcBadProject selectQcBadProjectById(String id);

    /**
     * 查询不良项目汇总列表
     * 
     * @param qcBadProject 不良项目汇总
     * @return 不良项目汇总集合
     */
    public List<QcBadProject> selectQcBadProjectList(QcBadProject qcBadProject);

    /**
     * 新增不良项目汇总
     * 
     * @param qcBadProject 不良项目汇总
     * @return 结果
     */
    public int insertQcBadProject(QcBadProject qcBadProject);

    /**
     * 修改不良项目汇总
     * 
     * @param qcBadProject 不良项目汇总
     * @return 结果
     */
    public int updateQcBadProject(QcBadProject qcBadProject);

    /**
     * 批量删除不良项目汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcBadProjectByIds(String ids);

    /**
     * 删除不良项目汇总信息
     * 
     * @param id 不良项目汇总ID
     * @return 结果
     */
    public int deleteQcBadProjectById(String id);
}
