package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.BsVendor;
import com.saas.pssc.mapper.BsVendorMapper;
import com.saas.pssc.service.IBsVendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 合格供方名录Service业务层处理
 * 
 * @author admin
 * @date 2021-07-15
 */
@Service
public class BsVendorServiceImpl implements IBsVendorService 
{
    @Autowired
    private BsVendorMapper bsVendorMapper;

    /**
     * 查询合格供方名录
     * 
     * @param Id 合格供方名录ID
     * @return 合格供方名录
     */
    @Override
    public BsVendor selectBsVendorById(Long Id)
    {
        return bsVendorMapper.selectBsVendorById(Id);
    }

    /**
     * 查询合格供方名录列表
     * 
     * @param bsVendor 合格供方名录
     * @return 合格供方名录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsVendor> selectBsVendorList(BsVendor bsVendor)
    {
        return bsVendorMapper.selectBsVendorList(bsVendor);
    }

    /**
     * 新增合格供方名录
     * 
     * @param bsVendor 合格供方名录
     * @return 结果
     */
    @Override
    public int insertBsVendor(BsVendor bsVendor)
    {
        bsVendor.setCreateTime(DateUtils.getNowDate());
        return bsVendorMapper.insertBsVendor(bsVendor);
    }

    /**
     * 修改合格供方名录
     * 
     * @param bsVendor 合格供方名录
     * @return 结果
     */
    @Override
    public int updateBsVendor(BsVendor bsVendor)
    {
        bsVendor.setUpdateTime(DateUtils.getNowDate());
        return bsVendorMapper.updateBsVendor(bsVendor);
    }

    /**
     * 删除合格供方名录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsVendorByIds(String ids)
    {
        return bsVendorMapper.updateBsVendorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合格供方名录信息
     * 
     * @param Id 合格供方名录ID
     * @return 结果
     */
    @Override
    public int deleteBsVendorById(Long Id)
    {
        return bsVendorMapper.updateBsVendorById(Id);
    }
}
