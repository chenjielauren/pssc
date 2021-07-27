package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcProdCheckMain;

/**
 * 成品检验记录Service接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface IQcProdCheckMainService 
{
    /**
     * 查询成品检验记录
     * 
     * @param id 成品检验记录ID
     * @return 成品检验记录
     */
    public QcProdCheckMain selectQcProdCheckMainById(Long id);

    /**
     * 查询成品检验记录列表
     * 
     * @param qcProdCheckMain 成品检验记录
     * @return 成品检验记录集合
     */
    public List<QcProdCheckMain> selectQcProdCheckMainList(QcProdCheckMain qcProdCheckMain);

    /**
     * 新增成品检验记录
     * 
     * @param qcProdCheckMain 成品检验记录
     * @return 结果
     */
    public int insertQcProdCheckMain(QcProdCheckMain qcProdCheckMain);

    /**
     * 修改成品检验记录
     * 
     * @param qcProdCheckMain 成品检验记录
     * @return 结果
     */
    public int updateQcProdCheckMain(QcProdCheckMain qcProdCheckMain);

    /**
     * 批量删除成品检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProdCheckMainByIds(String ids);

    /**
     * 删除基本信息
     * 
     * @param id 成品检验记录ID
     * @return 结果
     */
    public int deleteQcProdCheckMainById(Long id);
}
