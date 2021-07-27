package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcProcessCheckMain;

/**
 * 过程检验记录Service接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface IQcProcessCheckMainService 
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
     * 批量删除过程检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProcessCheckMainByIds(String ids);

    /**
     * 删除过程检验记录信息
     * 
     * @param id 过程检验记录ID
     * @return 结果
     */
    public int deleteQcProcessCheckMainById(Long id);
}
