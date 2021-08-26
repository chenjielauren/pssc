package com.saas.pssc.mapper;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.domain.BsBomDetail;

/**
 * bom物料清单Mapper接口
 * 
 * @author admin
 * @date 2021-08-09
 */
public interface BsBomMainMapper 
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
     * 删除bom物料清单
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    public int deleteBsBomMainById(String id);

    /**
     * 批量删除bom物料清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomMainByIds(String[] ids);

    /**
     * 批量删除bom物料清单明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsBomDetailByMainIds(String[] ids);
    
    /**
     * 批量新增bom物料清单明细
     * 
     * @param bsBomDetailList bom物料清单明细列表
     * @return 结果
     */
    public int batchBsBomDetail(List<BsBomDetail> bsBomDetailList);
    

    /**
     * 通过bom物料清单ID删除bom物料清单明细信息
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    public int deleteBsBomDetailByMainId(String id);

	public int updateBsBomMainByIds(String[] strArray);

	public int updateBsBomMainById(String id);

	public BsBomMain selectBsBomMainByMap(Map<String, Object> paramMap);
}
