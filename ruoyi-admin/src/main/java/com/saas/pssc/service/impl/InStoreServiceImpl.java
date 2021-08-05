package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.InStore;
import com.saas.pssc.mapper.InStoreMapper;
import com.saas.pssc.service.IInStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库存信息Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class InStoreServiceImpl implements IInStoreService 
{
    @Autowired
    private InStoreMapper inStoreMapper;

    /**
     * 查询库存信息
     * 
     * @param id 库存信息ID
     * @return 库存信息
     */
    @Override
    public InStore selectInStoreById(String id)
    {
        return inStoreMapper.selectInStoreById(id);
    }

    /**
     * 查询库存信息列表
     * 
     * @param inStore 库存信息
     * @return 库存信息
     */
    @Override
    @DataScope(userAlias = "su")
    public List<InStore> selectInStoreList(InStore inStore)
    {
        return inStoreMapper.selectInStoreList(inStore);
    }

    /**
     * 新增库存信息
     * 
     * @param inStore 库存信息
     * @return 结果
     */
    @Override
    public int insertInStore(InStore inStore)
    {
        inStore.setId(IdUtils.fastSimpleUUID());
        inStore.setCreateTime(DateUtils.getNowDate());
        return inStoreMapper.insertInStore(inStore);
    }

    /**
     * 修改库存信息
     * 
     * @param inStore 库存信息
     * @return 结果
     */
    @Override
    public int updateInStore(InStore inStore)
    {
        inStore.setUpdateTime(DateUtils.getNowDate());
        return inStoreMapper.updateInStore(inStore);
    }

    /**
     * 失效库存信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInStoreByIds(String ids)
    {
        return inStoreMapper.updateInStoreByIds(Convert.toStrArray(ids));
    }

    /**
     * 失效库存信息信息
     * 
     * @param id 库存信息ID
     * @return 结果
     */
    @Override
    public int deleteInStoreById(String id)
    {
        return inStoreMapper.updateInStoreById(id);
    }
}
