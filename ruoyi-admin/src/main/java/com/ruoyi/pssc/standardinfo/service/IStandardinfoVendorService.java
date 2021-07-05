package com.ruoyi.pssc.standardinfo.service;

import java.util.List;
import com.ruoyi.pssc.standardinfo.domain.StandardinfoVendor;

/**
 * 合格供方名录Service接口
 * 
 * @author lauren
 * @date 2021-07-05
 */
public interface IStandardinfoVendorService 
{
    /**
     * 查询合格供方名录
     * 
     * @param vendorId 合格供方名录ID
     * @return 合格供方名录
     */
    public StandardinfoVendor selectStandardinfoVendorById(Long vendorId);

    /**
     * 查询合格供方名录列表
     * 
     * @param standardinfoVendor 合格供方名录
     * @return 合格供方名录集合
     */
    public List<StandardinfoVendor> selectStandardinfoVendorList(StandardinfoVendor standardinfoVendor);

    /**
     * 新增合格供方名录
     * 
     * @param standardinfoVendor 合格供方名录
     * @return 结果
     */
    public int insertStandardinfoVendor(StandardinfoVendor standardinfoVendor);

    /**
     * 修改合格供方名录
     * 
     * @param standardinfoVendor 合格供方名录
     * @return 结果
     */
    public int updateStandardinfoVendor(StandardinfoVendor standardinfoVendor);

    /**
     * 批量删除合格供方名录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardinfoVendorByIds(String ids);

    /**
     * 删除合格供方名录信息
     * 
     * @param vendorId 合格供方名录ID
     * @return 结果
     */
    public int deleteStandardinfoVendorById(Long vendorId);
}
