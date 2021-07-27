package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcProdCheckDetail;

/**
 * 成品检验明细Service接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface IQcProdCheckDetailService 
{
    /**
     * 查询成品检验明细
     * 
     * @param id 成品检验明细ID
     * @return 成品检验明细
     */
    public QcProdCheckDetail selectQcProdCheckDetailById(Long id);

    /**
     * 查询成品检验明细列表
     * 
     * @param qcProdCheckDetail 成品检验明细
     * @return 成品检验明细集合
     */
    public List<QcProdCheckDetail> selectQcProdCheckDetailList(QcProdCheckDetail qcProdCheckDetail);

    /**
     * 新增成品检验明细
     * 
     * @param qcProdCheckDetail 成品检验明细
     * @return 结果
     */
    public int insertQcProdCheckDetail(QcProdCheckDetail qcProdCheckDetail);

    /**
     * 修改成品检验明细
     * 
     * @param qcProdCheckDetail 成品检验明细
     * @return 结果
     */
    public int updateQcProdCheckDetail(QcProdCheckDetail qcProdCheckDetail);

    /**
     * 批量删除成品检验明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProdCheckDetailByIds(String ids);

    /**
     * 删除检验信息
     * 
     * @param id 成品检验明细ID
     * @return 结果
     */
    public int deleteQcProdCheckDetailById(Long id);
}
