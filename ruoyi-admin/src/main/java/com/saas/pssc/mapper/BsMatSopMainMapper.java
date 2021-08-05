package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.domain.BsMatSopDetail;

/**
 * 材料检验标准Mapper接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface BsMatSopMainMapper 
{
    /**
     * 查询材料检验标准
     * 
     * @param id 材料检验标准ID
     * @return 材料检验标准
     */
    public BsMatSopMain selectBsMatSopMainById(String id);

    /**
     * 查询材料检验标准列表
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 材料检验标准集合
     */
    public List<BsMatSopMain> selectBsMatSopMainList(BsMatSopMain bsMatSopMain);

    /**
     * 新增材料检验标准
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 结果
     */
    public int insertBsMatSopMain(BsMatSopMain bsMatSopMain);

    /**
     * 修改材料检验标准
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 结果
     */
    public int updateBsMatSopMain(BsMatSopMain bsMatSopMain);

    /**
     * 失效材料检验标准
     * 
     * @param id 材料检验标准ID
     * @return 结果
     */
    public int updateBsMatSopMainById(String id);

    /**
     * 批量失效材料检验标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBsMatSopMainByIds(String[] ids);

    /**
     * 删除材料检验标准
     * 
     * @param id 材料检验标准ID
     * @return 结果
     */
    public int deleteBsMatSopMainById(String id);

    /**
     * 批量删除材料检验标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMatSopMainByIds(String[] ids);

    /**
     * 批量删除材料检验标准明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMatSopDetailByMainIds(String[] ids);
    
    /**
     * 批量新增材料检验标准明细
     * 
     * @param bsMatSopDetailList 材料检验标准明细列表
     * @return 结果
     */
    public int batchBsMatSopDetail(List<BsMatSopDetail> bsMatSopDetailList);
    

    /**
     * 通过材料检验标准ID删除材料检验标准明细信息
     * 
     * @param id 材料检验标准ID
     * @return 结果
     */
    public int deleteBsMatSopDetailByMainId(String id);
}
