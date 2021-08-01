package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsBom;

/**
 * bom物料清单Mapper接口
 * 
 * @author admin
 * @date 2021-08-01
 */
public interface BsBomMapper 
{
    /**
     * 查询bom物料清单
     * 
     * @param id bom物料清单ID
     * @return bom物料清单
     */
    public BsBom selectBsBomById(Long id);

    /**
     * 查询bom物料清单列表
     * 
     * @param bsBom bom物料清单
     * @return bom物料清单集合
     */
    public List<BsBom> selectBsBomList(BsBom bsBom);

    /**
     * 新增bom物料清单
     * 
     * @param bsBom bom物料清单
     * @return 结果
     */
    public int insertBsBom(BsBom bsBom);

    /**
     * 修改bom物料清单
     * 
     * @param bsBom bom物料清单
     * @return 结果
     */
    public int updateBsBom(BsBom bsBom);

    /**
     * 删除bom物料清单
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    public int deleteBsBomById(Long id);

    /**
     * 批量删除bom物料清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomByIds(String[] ids);

	public int updateBsBomById(Long id);

	public int updateBsBomByIds(String[] strArray);
}
