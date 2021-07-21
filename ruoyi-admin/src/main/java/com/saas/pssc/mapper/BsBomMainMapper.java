package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsBomMain;

/**
 * bom物料清单Mapper接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface BsBomMainMapper 
{
    /**
     * 查询bom物料清单
     * 
     * @param id bom物料清单ID
     * @return bom物料清单
     */
    public BsBomMain selectBsBomMainById(Long id);

    /**
     * 查询bom物料清单列表
     * 
     * @param bsBomMain bom物料清单
     * @return bom物料清单集合
     */
    public List<BsBomMain> selectBsBomMainList(BsBomMain bsBomMain);

    /**
     * 新增bom物料清单
     * 
     * @param bsBomMain bom物料清单
     * @return 结果
     */
    public int insertBsBomMain(BsBomMain bsBomMain);

    /**
     * 修改bom物料清单
     * 
     * @param bsBomMain bom物料清单
     * @return 结果
     */
    public int updateBsBomMain(BsBomMain bsBomMain);

    /**
     * 删除bom物料清单
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    public int deleteBsBomMainById(Long id);

    /**
     * 批量删除bom物料清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomMainByIds(String[] ids);
}
