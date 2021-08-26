package com.saas.pssc.service;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.BsBomMain;

/**
 * bom物料清单Service接口
 * 
 * @author admin
 * @date 2021-08-09
 */
public interface IBsBomMainService 
{
    /**
     * 查询bom物料清单
     * 
     * @param id bom物料清单ID
     * @return bom物料清单
     */
    public BsBomMain selectBsBomMainById(String id);

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
     * 批量删除bom物料清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomMainByIds(String ids);

    /**
     * 删除bom物料清单信息
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    public int deleteBsBomMainById(String id);

	public BsBomMain selectBsBomMainByMap(Map<String, Object> paramMap);
}
