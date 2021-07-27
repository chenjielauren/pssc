package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.QcMatCheckDetail;

/**
 * 原材料检验明细Mapper接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface QcMatCheckDetailMapper 
{
    /**
     * 查询原材料检验明细
     * 
     * @param id 原材料检验明细ID
     * @return 原材料检验明细
     */
    public QcMatCheckDetail selectQcMatCheckDetailById(Long id);

    /**
     * 查询原材料检验明细列表
     * 
     * @param qcMatCheckDetail 原材料检验明细
     * @return 原材料检验明细集合
     */
    public List<QcMatCheckDetail> selectQcMatCheckDetailList(QcMatCheckDetail qcMatCheckDetail);

    /**
     * 新增原材料检验明细
     * 
     * @param qcMatCheckDetail 原材料检验明细
     * @return 结果
     */
    public int insertQcMatCheckDetail(QcMatCheckDetail qcMatCheckDetail);

    /**
     * 修改原材料检验明细
     * 
     * @param qcMatCheckDetail 原材料检验明细
     * @return 结果
     */
    public int updateQcMatCheckDetail(QcMatCheckDetail qcMatCheckDetail);

    /**
     * 删除原材料检验明细
     * 
     * @param id 原材料检验明细ID
     * @return 结果
     */
    public int deleteQcMatCheckDetailById(Long id);

    /**
     * 批量删除原材料检验明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcMatCheckDetailByIds(String[] ids);
}
