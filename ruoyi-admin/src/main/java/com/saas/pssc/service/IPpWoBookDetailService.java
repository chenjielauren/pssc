package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.PpWoBookDetail;

/**
 * 工单制造信息Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IPpWoBookDetailService 
{
    /**
     * 查询工单制造信息
     * 
     * @param id 工单制造信息ID
     * @return 工单制造信息
     */
    public PpWoBookDetail selectPpWoBookDetailById(String id);

    /**
     * 查询工单制造信息列表
     * 
     * @param ppWoBookDetail 工单制造信息
     * @return 工单制造信息集合
     */
    public List<PpWoBookDetail> selectPpWoBookDetailList(PpWoBookDetail ppWoBookDetail);

    /**
     * 新增工单制造信息
     * 
     * @param ppWoBookDetail 工单制造信息
     * @return 结果
     */
    public int insertPpWoBookDetail(PpWoBookDetail ppWoBookDetail);

    /**
     * 修改工单制造信息
     * 
     * @param ppWoBookDetail 工单制造信息
     * @return 结果
     */
    public int updatePpWoBookDetail(PpWoBookDetail ppWoBookDetail);

    /**
     * 批量删除工单制造信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookDetailByIds(String ids);

    /**
     * 删除工单制造信息信息
     * 
     * @param id 工单制造信息ID
     * @return 结果
     */
    public int deletePpWoBookDetailById(String id);
}
