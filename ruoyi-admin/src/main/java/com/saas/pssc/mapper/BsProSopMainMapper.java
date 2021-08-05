package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.domain.BsProSopDetail;

/**
 * 成品检验标准Mapper接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface BsProSopMainMapper 
{
    /**
     * 查询成品检验标准
     * 
     * @param id 成品检验标准ID
     * @return 成品检验标准
     */
    public BsProSopMain selectBsProSopMainById(String id);

    /**
     * 查询成品检验标准列表
     * 
     * @param bsProSopMain 成品检验标准
     * @return 成品检验标准集合
     */
    public List<BsProSopMain> selectBsProSopMainList(BsProSopMain bsProSopMain);

    /**
     * 新增成品检验标准
     * 
     * @param bsProSopMain 成品检验标准
     * @return 结果
     */
    public int insertBsProSopMain(BsProSopMain bsProSopMain);

    /**
     * 修改成品检验标准
     * 
     * @param bsProSopMain 成品检验标准
     * @return 结果
     */
    public int updateBsProSopMain(BsProSopMain bsProSopMain);

    /**
     * 删除成品检验标准
     * 
     * @param id 成品检验标准ID
     * @return 结果
     */
    public int deleteBsProSopMainById(String id);

    /**
     * 批量删除成品检验标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsProSopMainByIds(String[] ids);

    /**
     * 批量删除成品检验标准明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsProSopDetailByMainIds(String[] ids);
    
    /**
     * 批量新增成品检验标准明细
     * 
     * @param bsProSopDetailList 成品检验标准明细列表
     * @return 结果
     */
    public int batchBsProSopDetail(List<BsProSopDetail> bsProSopDetailList);
    

    /**
     * 通过成品检验标准ID删除成品检验标准明细信息
     * 
     * @param id 成品检验标准ID
     * @return 结果
     */
    public int deleteBsProSopDetailByMainId(String id);

	public int updateBsProSopMainById(String id);

	public int updateBsProSopMainByIds(String[] strArray);
}
