package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.QcProdCheckMain;
import com.saas.pssc.domain.QcProdCheckDetail;

/**
 * 成品检验记录Mapper接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface QcProdCheckMainMapper 
{
    /**
     * 查询成品检验记录
     * 
     * @param id 成品检验记录ID
     * @return 成品检验记录
     */
    public QcProdCheckMain selectQcProdCheckMainById(String id);

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
     * 删除成品检验记录
     * 
     * @param id 成品检验记录ID
     * @return 结果
     */
    public int deleteQcProdCheckMainById(String id);

    /**
     * 批量删除成品检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProdCheckMainByIds(String[] ids);

    /**
     * 批量删除成品检验明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProdCheckDetailByMainIds(String[] ids);
    
    /**
     * 批量新增成品检验明细
     * 
     * @param qcProdCheckDetailList 成品检验明细列表
     * @return 结果
     */
    public int batchQcProdCheckDetail(List<QcProdCheckDetail> qcProdCheckDetailList);
    

    /**
     * 通过成品检验记录ID删除检验信息
     * 
     * @param id 成品检验记录ID
     * @return 结果
     */
    public int deleteQcProdCheckDetailByMainId(String id);

    /**
     * 首页的成品批次号查找成品检验记录里对应的批次号结果
     * 
     * @param lot 订单号
     * @return 结果
     */
	public List<String> selectQcResult(String lot);
}
