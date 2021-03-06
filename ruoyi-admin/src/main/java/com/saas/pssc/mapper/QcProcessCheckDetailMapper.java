package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.QcProcessCheckDetail;

/**
 * 过程检验明细Mapper接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface QcProcessCheckDetailMapper 
{
    /**
     * 查询过程检验明细
     * 
     * @param id 过程检验明细ID
     * @return 过程检验明细
     */
    public QcProcessCheckDetail selectQcProcessCheckDetailById(String id);

    /**
     * 查询过程检验明细列表
     * 
     * @param qcProcessCheckDetail 过程检验明细
     * @return 过程检验明细集合
     */
    public List<QcProcessCheckDetail> selectQcProcessCheckDetailList(QcProcessCheckDetail qcProcessCheckDetail);

    /**
     * 新增过程检验明细
     * 
     * @param qcProcessCheckDetail 过程检验明细
     * @return 结果
     */
    public int insertQcProcessCheckDetail(QcProcessCheckDetail qcProcessCheckDetail);

    /**
     * 修改过程检验明细
     * 
     * @param qcProcessCheckDetail 过程检验明细
     * @return 结果
     */
    public int updateQcProcessCheckDetail(QcProcessCheckDetail qcProcessCheckDetail);

    /**
     * 删除过程检验明细
     * 
     * @param id 过程检验明细ID
     * @return 结果
     */
    public int deleteQcProcessCheckDetailById(String id);

    /**
     * 批量删除过程检验明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProcessCheckDetailByIds(String[] ids);
}
