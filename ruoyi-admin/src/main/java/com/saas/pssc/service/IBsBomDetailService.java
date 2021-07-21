package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsBomDetail;
import com.saas.common.core.domain.Ztree;

/**
 * bom物料清单明细Service接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface IBsBomDetailService 
{
    /**
     * 查询bom物料清单明细
     * 
     * @param id bom物料清单明细ID
     * @return bom物料清单明细
     */
    public BsBomDetail selectBsBomDetailById(Long id);

    /**
     * 查询bom物料清单明细列表
     * 
     * @param bsBomDetail bom物料清单明细
     * @return bom物料清单明细集合
     */
    public List<BsBomDetail> selectBsBomDetailList(BsBomDetail bsBomDetail);

    /**
     * 新增bom物料清单明细
     * 
     * @param bsBomDetail bom物料清单明细
     * @return 结果
     */
    public int insertBsBomDetail(BsBomDetail bsBomDetail);

    /**
     * 修改bom物料清单明细
     * 
     * @param bsBomDetail bom物料清单明细
     * @return 结果
     */
    public int updateBsBomDetail(BsBomDetail bsBomDetail);

    /**
     * 批量删除bom物料清单明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomDetailByIds(String ids);

    /**
     * 删除bom物料清单明细信息
     * 
     * @param id bom物料清单明细ID
     * @return 结果
     */
    public int deleteBsBomDetailById(Long id);

    /**
     * 查询bom物料清单明细树列表
     * 
     * @return 所有bom物料清单明细信息
     */
    public List<Ztree> selectBsBomDetailTree();
}
