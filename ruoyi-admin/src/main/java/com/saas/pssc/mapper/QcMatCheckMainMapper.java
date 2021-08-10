package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.QcMatCheckMain;
import com.saas.pssc.domain.QcMatCheckDetail;

/**
 * 原材料检验记录Mapper接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface QcMatCheckMainMapper 
{
    /**
     * 查询原材料检验记录
     * 
     * @param id 原材料检验记录ID
     * @return 原材料检验记录
     */
    public QcMatCheckMain selectQcMatCheckMainById(String id);

    /**
     * 查询原材料检验记录列表
     * 
     * @param qcMatCheckMain 原材料检验记录
     * @return 原材料检验记录集合
     */
    public List<QcMatCheckMain> selectQcMatCheckMainList(QcMatCheckMain qcMatCheckMain);

    /**
     * 新增原材料检验记录
     * 
     * @param qcMatCheckMain 原材料检验记录
     * @return 结果
     */
    public int insertQcMatCheckMain(QcMatCheckMain qcMatCheckMain);

    /**
     * 修改原材料检验记录
     * 
     * @param qcMatCheckMain 原材料检验记录
     * @return 结果
     */
    public int updateQcMatCheckMain(QcMatCheckMain qcMatCheckMain);

    /**
     * 删除原材料检验记录
     * 
     * @param id 原材料检验记录ID
     * @return 结果
     */
    public int deleteQcMatCheckMainById(String id);

    /**
     * 批量删除原材料检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcMatCheckMainByIds(String[] ids);

    /**
     * 批量删除原材料检验明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcMatCheckDetailByMainIds(String[] ids);
    
    /**
     * 批量新增原材料检验明细
     * 
     * @param qcMatCheckDetailList 原材料检验明细列表
     * @return 结果
     */
    public int batchQcMatCheckDetail(List<QcMatCheckDetail> qcMatCheckDetailList);
    

    /**
     * 通过原材料检验记录ID删除材料检验信息
     * 
     * @param id 原材料检验记录ID
     * @return 结果
     */
    public int deleteQcMatCheckDetailByMainId(String id);

	public int updateQcMatCheckMainByIds(String[] strArray);

	public int updateQcMatCheckMainById(String id);
}
