package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.InSfStoreDetail;

/**
 * 库存明细Mapper接口
 * 
 * @author admin
 * @date 2021-07-22
 */
public interface InSfStoreDetailMapper 
{
    /**
     * 查询库存明细
     * 
     * @param id 库存明细ID
     * @return 库存明细
     */
    public InSfStoreDetail selectInStoreDetailById(Long id);

    /**
     * 查询库存明细列表
     * 
     * @param inStoreDetail 库存明细
     * @return 库存明细集合
     */
    public List<InSfStoreDetail> selectInStoreDetailList(InSfStoreDetail inStoreDetail);

    /**
     * 新增库存明细
     * 
     * @param inStoreDetail 库存明细
     * @return 结果
     */
    public int insertInStoreDetail(InSfStoreDetail inStoreDetail);

    /**
     * 修改库存明细
     * 
     * @param inStoreDetail 库存明细
     * @return 结果
     */
    public int updateInStoreDetail(InSfStoreDetail inStoreDetail);

    /**
     * 删除库存明细
     * 
     * @param id 库存明细ID
     * @return 结果
     */
    public int deleteInStoreDetailById(Long id);

    /**
     * 批量删除库存明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInStoreDetailByIds(String[] ids);
}