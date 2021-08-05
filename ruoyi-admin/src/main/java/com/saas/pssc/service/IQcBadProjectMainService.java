package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcBadProjectMain;

/**
 * 不良项目记录Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IQcBadProjectMainService 
{
    /**
     * 查询不良项目记录
     * 
     * @param id 不良项目记录ID
     * @return 不良项目记录
     */
    public QcBadProjectMain selectQcBadProjectMainById(String id);

    /**
     * 查询不良项目记录列表
     * 
     * @param qcBadProjectMain 不良项目记录
     * @return 不良项目记录集合
     */
    public List<QcBadProjectMain> selectQcBadProjectMainList(QcBadProjectMain qcBadProjectMain);

    /**
     * 新增不良项目记录
     * 
     * @param qcBadProjectMain 不良项目记录
     * @return 结果
     */
    public int insertQcBadProjectMain(QcBadProjectMain qcBadProjectMain);

    /**
     * 修改不良项目记录
     * 
     * @param qcBadProjectMain 不良项目记录
     * @return 结果
     */
    public int updateQcBadProjectMain(QcBadProjectMain qcBadProjectMain);

    /**
     * 批量删除不良项目记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcBadProjectMainByIds(String ids);

    /**
     * 删除不良项目记录信息
     * 
     * @param id 不良项目记录ID
     * @return 结果
     */
    public int deleteQcBadProjectMainById(String id);
}
