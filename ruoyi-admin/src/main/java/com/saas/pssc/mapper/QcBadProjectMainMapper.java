package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.QcBadProjectMain;
import com.saas.pssc.domain.QcBadProjectDetail;

/**
 * 不良项目记录Mapper接口
 * 
 * @author admin
 * @date 2021-09-17
 */
public interface QcBadProjectMainMapper 
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
     * 删除不良项目记录
     * 
     * @param id 不良项目记录ID
     * @return 结果
     */
    public int deleteQcBadProjectMainById(String id);

    /**
     * 批量删除不良项目记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcBadProjectMainByIds(String[] ids);

    /**
     * 批量删除不良项目记录明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcBadProjectDetailByMainIds(String[] ids);
    
    /**
     * 批量新增不良项目记录明细
     * 
     * @param qcBadProjectDetailList 不良项目记录明细列表
     * @return 结果
     */
    public int batchQcBadProjectDetail(List<QcBadProjectDetail> qcBadProjectDetailList);
    

    /**
     * 通过不良项目记录ID删除不良项目记录明细信息
     * 
     * @param id 不良项目记录ID
     * @return 结果
     */
    public int deleteQcBadProjectDetailByMainId(String id);

	public int updateQcBadProjectMainByIds(String[] strArray);

	public int updateQcBadProjectMainById(String id);
}
