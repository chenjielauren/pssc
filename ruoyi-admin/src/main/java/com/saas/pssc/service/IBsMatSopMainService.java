package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsMatSopMain;

/**
 * 材料检验标准Service接口
 * 
 * @author admin
 * @date 2021-08-03
 */
public interface IBsMatSopMainService 
{
    /**
     * 查询材料检验标准
     * 
     * @param id 材料检验标准ID
     * @return 材料检验标准
     */
    public BsMatSopMain selectBsMatSopMainById(String id);

    /**
     * 查询材料检验标准列表
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 材料检验标准集合
     */
    public List<BsMatSopMain> selectBsMatSopMainList(BsMatSopMain bsMatSopMain);

    /**
     * 新增材料检验标准
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 结果
     */
    public int insertBsMatSopMain(BsMatSopMain bsMatSopMain);

    /**
     * 修改材料检验标准
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 结果
     */
    public int updateBsMatSopMain(BsMatSopMain bsMatSopMain);

    /**
     * 批量删除材料检验标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsMatSopMainByIds(String ids);

    /**
     * 删除材料检验标准信息
     * 
     * @param id 材料检验标准ID
     * @return 结果
     */
    public int deleteBsMatSopMainById(String id);
}
