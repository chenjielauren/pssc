package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcMatCheckMain;

/**
 * 原材料检验记录Service接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface IQcMatCheckMainService 
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
     * 批量删除原材料检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcMatCheckMainByIds(String ids);

    /**
     * 删除原材料检验记录信息
     * 
     * @param id 原材料检验记录ID
     * @return 结果
     */
    public int deleteQcMatCheckMainById(String id);
}
