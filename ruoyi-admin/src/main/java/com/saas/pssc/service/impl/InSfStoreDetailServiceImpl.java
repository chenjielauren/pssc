package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.InSfStoreDetail;
import com.saas.pssc.mapper.InSfStoreDetailMapper;
import com.saas.pssc.service.IInSfStoreDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库存明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-22
 */
@Service
public class InSfStoreDetailServiceImpl implements IInSfStoreDetailService 
{
    @Autowired
    private InSfStoreDetailMapper inStoreDetailMapper;

    /**
     * 查询库存明细
     * 
     * @param id 库存明细ID
     * @return 库存明细
     */
    @Override
    public InSfStoreDetail selectInStoreDetailById(Long id)
    {
        return inStoreDetailMapper.selectInStoreDetailById(id);
    }

    /**
     * 查询库存明细列表
     * 
     * @param inStoreDetail 库存明细
     * @return 库存明细
     */
    @Override
    @DataScope(userAlias = "su")
    public List<InSfStoreDetail> selectInStoreDetailList(InSfStoreDetail inStoreDetail)
    {
        return inStoreDetailMapper.selectInStoreDetailList(inStoreDetail);
    }

    /**
     * 新增库存明细
     * 
     * @param inStoreDetail 库存明细
     * @return 结果
     */
    @Override
    public int insertInStoreDetail(InSfStoreDetail inStoreDetail)
    {
        inStoreDetail.setCreateTime(DateUtils.getNowDate());
        return inStoreDetailMapper.insertInStoreDetail(inStoreDetail);
    }

    /**
     * 修改库存明细
     * 
     * @param inStoreDetail 库存明细
     * @return 结果
     */
    @Override
    public int updateInStoreDetail(InSfStoreDetail inStoreDetail)
    {
        inStoreDetail.setUpdateTime(DateUtils.getNowDate());
        return inStoreDetailMapper.updateInStoreDetail(inStoreDetail);
    }

    /**
     * 删除库存明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInStoreDetailByIds(String ids)
    {
        return inStoreDetailMapper.deleteInStoreDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存明细信息
     * 
     * @param id 库存明细ID
     * @return 结果
     */
    @Override
    public int deleteInStoreDetailById(Long id)
    {
        return inStoreDetailMapper.deleteInStoreDetailById(id);
    }
}
