package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.InStore;

/**
 * 库存信息Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IInStoreService 
{
    /**
     * 查询库存信息
     * 
     * @param id 库存信息ID
     * @return 库存信息
     */
    public InStore selectInStoreById(String id);

    /**
     * 查询库存信息列表
     * 
     * @param inStore 库存信息
     * @return 库存信息集合
     */
    public List<InStore> selectInStoreList(InStore inStore);

    /**
     * 新增库存信息
     * 
     * @param inStore 库存信息
     * @return 结果
     */
    public int insertInStore(InStore inStore);

    /**
     * 修改库存信息
     * 
     * @param inStore 库存信息
     * @return 结果
     */
    public int updateInStore(InStore inStore);

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInStoreByIds(String ids);

    /**
     * 删除库存信息信息
     * 
     * @param id 库存信息ID
     * @return 结果
     */
    public int deleteInStoreById(String id);
}
