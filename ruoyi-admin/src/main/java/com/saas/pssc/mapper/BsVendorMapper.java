package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsVendor;

/**
 * 合格供方名录Mapper接口
 * 
 * @author admin
 * @date 2021-07-15
 */
public interface BsVendorMapper 
{
    /**
     * 查询合格供方名录
     * 
     * @param Id 合格供方名录ID
     * @return 合格供方名录
     */
    public BsVendor selectBsVendorById(String Id);

    /**
     * 查询合格供方名录列表
     * 
     * @param bsVendor 合格供方名录
     * @return 合格供方名录集合
     */
    public List<BsVendor> selectBsVendorList(BsVendor bsVendor);

    /**
     * 新增合格供方名录
     * 
     * @param bsVendor 合格供方名录
     * @return 结果
     */
    public int insertBsVendor(BsVendor bsVendor);

    /**
     * 修改合格供方名录
     * 
     * @param bsVendor 合格供方名录
     * @return 结果
     */
    public int updateBsVendor(BsVendor bsVendor);

    /**
     * 删除合格供方名录
     * 
     * @param Id 合格供方名录ID
     * @return 结果
     */
    public int deleteBsVendorById(String Id);
    
    /**
     * 失效合格供方名录
     * 
     * @param Id 合格供方名录ID
     * @return 结果
     */
    public int updateBsVendorById(String Id);

    /**
     * 批量删除合格供方名录
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsVendorByIds(String[] Ids);

    /**
     * 批量失效合格供方名录
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBsVendorByIds(String[] Ids);

	public List<BsVendor> selectVendor(String dlot);
}
