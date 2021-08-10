package com.saas.pssc.mapper;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.domain.PpWoBookBom;
import com.saas.pssc.domain.PpWoBookDetail;

/**
 * 工单记录Mapper接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface PpWoBookMainMapper 
{
    /**
     * 查询工单记录
     * 
     * @param id 工单记录ID
     * @return 工单记录
     */
    public PpWoBookMain selectPpWoBookMainById(String id);

    /**
     * 查询工单记录列表
     * 
     * @param ppWoBookMain 工单记录
     * @return 工单记录集合
     */
    public List<PpWoBookMain> selectPpWoBookMainList(PpWoBookMain ppWoBookMain);

    /**
     * 新增工单记录
     * 
     * @param ppWoBookMain 工单记录
     * @return 结果
     */
    public int insertPpWoBookMain(PpWoBookMain ppWoBookMain);

    /**
     * 修改工单记录
     * 
     * @param ppWoBookMain 工单记录
     * @return 结果
     */
    public int updatePpWoBookMain(PpWoBookMain ppWoBookMain);

    /**
     * 删除工单记录
     * 
     * @param id 工单记录ID
     * @return 结果
     */
    public int deletePpWoBookMainById(String id);

    /**
     * 批量删除工单记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookMainByIds(String[] ids);

    /**
     * 批量删除工单制造信息
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWoBookDetailByMainIds(String[] ids);
    
    /**
     * 批量新增工单制造信息
     * 
     * @param ppWoBookDetailList 工单制造信息列表
     * @return 结果
     */
    public int batchPpWoBookDetail(List<PpWoBookDetail> ppWoBookDetailList);
    

    /**
     * 通过工单记录ID删除工单制造信息信息
     * 
     * @param id 工单记录ID
     * @return 结果
     */
    public int deletePpWoBookDetailByMainId(String id);

    /**
     * 批量新增工单Bom信息
     * 
     * @param ppWoBookDetailList 工单Bom信息列表
     * @return 结果
     */
	public void batchPpWoBookBom(List<PpWoBookBom> list);

	public void deletePpWoBookBomByMainId(String id);

	public void deletePpWoBookBomByMainIds(String[] strArray);

	public int updatePpWoBookMainById(String id);

	public int updatePpWoBookMainByIds(String[] strArray);

	public PpWoBookMain selectPpWoBookMainByMap(Map<String, Object> paramMap);
}
