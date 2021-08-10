package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsBomDetail;

/**
 * bom物料清单明细Mapper接口
 * 
 * @author admin
 * @date 2021-08-09
 */
public interface BsBomDetailMapper 
{
    /**
     * 查询bom物料清单明细
     * 
     * @param id bom物料清单明细ID
     * @return bom物料清单明细
     */
    public BsBomDetail selectBsBomDetailById(String id);

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
     * 删除bom物料清单明细
     * 
     * @param id bom物料清单明细ID
     * @return 结果
     */
    public int deleteBsBomDetailById(String id);

    /**
     * 批量删除bom物料清单明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomDetailByIds(String[] ids);
}
