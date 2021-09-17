package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcBadProjectDetail;

/**
 * 不良项目记录明细Service接口
 * 
 * @author admin
 * @date 2021-09-17
 */
public interface IQcBadProjectDetailService 
{
    /**
     * 查询不良项目记录明细
     * 
     * @param id 不良项目记录明细ID
     * @return 不良项目记录明细
     */
    public QcBadProjectDetail selectQcBadProjectDetailById(String id);

    /**
     * 查询不良项目记录明细列表
     * 
     * @param qcBadProjectDetail 不良项目记录明细
     * @return 不良项目记录明细集合
     */
    public List<QcBadProjectDetail> selectQcBadProjectDetailList(QcBadProjectDetail qcBadProjectDetail);

    /**
     * 新增不良项目记录明细
     * 
     * @param qcBadProjectDetail 不良项目记录明细
     * @return 结果
     */
    public int insertQcBadProjectDetail(QcBadProjectDetail qcBadProjectDetail);

    /**
     * 修改不良项目记录明细
     * 
     * @param qcBadProjectDetail 不良项目记录明细
     * @return 结果
     */
    public int updateQcBadProjectDetail(QcBadProjectDetail qcBadProjectDetail);

    /**
     * 批量删除不良项目记录明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcBadProjectDetailByIds(String ids);

    /**
     * 删除不良项目记录明细信息
     * 
     * @param id 不良项目记录明细ID
     * @return 结果
     */
    public int deleteQcBadProjectDetailById(String id);
}
