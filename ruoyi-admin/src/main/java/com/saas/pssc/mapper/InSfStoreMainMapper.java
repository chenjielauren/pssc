package com.saas.pssc.mapper;

import java.util.List;

import com.saas.pssc.domain.InSfStoreDetail;
import com.saas.pssc.domain.InSfStoreMain;

/**
 * 库存Mapper接口
 * 
 * @author admin
 * @date 2021-07-22
 */
public interface InSfStoreMainMapper 
{
    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    public InSfStoreMain selectInStoreMainById(Long id);

    /**
     * 查询库存列表
     * 
     * @param inStoreMain 库存
     * @return 库存集合
     */
    public List<InSfStoreMain> selectInStoreMainList(InSfStoreMain inStoreMain);

    /**
     * 新增库存
     * 
     * @param inStoreMain 库存
     * @return 结果
     */
    public int insertInStoreMain(InSfStoreMain inStoreMain);

    /**
     * 修改库存
     * 
     * @param inStoreMain 库存
     * @return 结果
     */
    public int updateInStoreMain(InSfStoreMain inStoreMain);

    /**
     * 失效库存
     * 
     * @param id 库存ID
     * @return 结果
     */
    public int updateInStoreMainById(Long id);

    /**
     * 批量失效库存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateInStoreMainByIds(String[] ids);
    
    /**
     * 删除库存
     * 
     * @param id 库存ID
     * @return 结果
     */
    public int deleteInStoreMainById(Long id);

    /**
     * 批量删除库存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInStoreMainByIds(String[] ids);

    /**
     * 批量删除库存明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteInStoreDetailByMainIds(String[] ids);
    
    /**
     * 批量新增库存明细
     * 
     * @param inStoreDetailList 库存明细列表
     * @return 结果
     */
    public int batchInStoreDetail(List<InSfStoreDetail> inStoreDetailList);
    

    /**
     * 通过库存ID删除库存明细信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    public int deleteInStoreDetailByMainId(Long id);
}
