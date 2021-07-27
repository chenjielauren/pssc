package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.domain.QcProcessCheckDetail;

/**
 * 过程检验记录Mapper接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface QcProcessCheckMainMapper 
{
    /**
     * 查询过程检验记录
     * 
     * @param id 过程检验记录ID
     * @return 过程检验记录
     */
    public QcProcessCheckMain selectQcProcessCheckMainById(Long id);

    /**
     * 查询过程检验记录列表
     * 
     * @param qcProcessCheckMain 过程检验记录
     * @return 过程检验记录集合
     */
    public List<QcProcessCheckMain> selectQcProcessCheckMainList(QcProcessCheckMain qcProcessCheckMain);

    /**
     * 新增过程检验记录
     * 
     * @param qcProcessCheckMain 过程检验记录
     * @return 结果
     */
    public int insertQcProcessCheckMain(QcProcessCheckMain qcProcessCheckMain);

    /**
     * 修改过程检验记录
     * 
     * @param qcProcessCheckMain 过程检验记录
     * @return 结果
     */
    public int updateQcProcessCheckMain(QcProcessCheckMain qcProcessCheckMain);

    /**
     * 删除过程检验记录
     * 
     * @param id 过程检验记录ID
     * @return 结果
     */
    public int deleteQcProcessCheckMainById(Long id);

    /**
     * 批量删除过程检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProcessCheckMainByIds(String[] ids);

    /**
     * 批量删除过程检验明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProcessCheckDetailByMainIds(String[] ids);
    
    /**
     * 批量新增过程检验明细
     * 
     * @param qcProcessCheckDetailList 过程检验明细列表
     * @return 结果
     */
    public int batchQcProcessCheckDetail(List<QcProcessCheckDetail> qcProcessCheckDetailList);
    

    /**
     * 通过过程检验记录ID删除检验标准信息
     * 
     * @param id 过程检验记录ID
     * @return 结果
     */
    public int deleteQcProcessCheckDetailByMainId(Long id);
}
