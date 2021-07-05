package com.ruoyi.pssc.standardinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pssc.standardinfo.mapper.StandardinfoVendorMapper;
import com.ruoyi.pssc.standardinfo.domain.StandardinfoVendor;
import com.ruoyi.pssc.standardinfo.service.IStandardinfoVendorService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合格供方名录Service业务层处理
 * 
 * @author lauren
 * @date 2021-07-05
 */
@Service
public class StandardinfoVendorServiceImpl implements IStandardinfoVendorService 
{
    @Autowired
    private StandardinfoVendorMapper standardinfoVendorMapper;

    /**
     * 查询合格供方名录
     * 
     * @param vendorId 合格供方名录ID
     * @return 合格供方名录
     */
    @Override
    public StandardinfoVendor selectStandardinfoVendorById(Long vendorId)
    {
        return standardinfoVendorMapper.selectStandardinfoVendorById(vendorId);
    }

    /**
     * 查询合格供方名录列表
     * 
     * @param standardinfoVendor 合格供方名录
     * @return 合格供方名录
     */
    @Override
    public List<StandardinfoVendor> selectStandardinfoVendorList(StandardinfoVendor standardinfoVendor)
    {
        return standardinfoVendorMapper.selectStandardinfoVendorList(standardinfoVendor);
    }

    /**
     * 新增合格供方名录
     * 
     * @param standardinfoVendor 合格供方名录
     * @return 结果
     */
    @Override
    public int insertStandardinfoVendor(StandardinfoVendor standardinfoVendor)
    {
        standardinfoVendor.setCreateTime(DateUtils.getNowDate());
        return standardinfoVendorMapper.insertStandardinfoVendor(standardinfoVendor);
    }

    /**
     * 修改合格供方名录
     * 
     * @param standardinfoVendor 合格供方名录
     * @return 结果
     */
    @Override
    public int updateStandardinfoVendor(StandardinfoVendor standardinfoVendor)
    {
        standardinfoVendor.setUpdateTime(DateUtils.getNowDate());
        return standardinfoVendorMapper.updateStandardinfoVendor(standardinfoVendor);
    }

    /**
     * 删除合格供方名录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoVendorByIds(String ids)
    {
        return standardinfoVendorMapper.deleteStandardinfoVendorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合格供方名录信息
     * 
     * @param vendorId 合格供方名录ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoVendorById(Long vendorId)
    {
        return standardinfoVendorMapper.deleteStandardinfoVendorById(vendorId);
    }
}
