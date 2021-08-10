package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.BsPqcMain;

/**
 * 产品检验标准Service接口
 * 
 * @author admin
 * @date 2021-07-15
 */
public interface IBsPqcMainService 
{
    /**
     * 查询产品检验标准
     * 
     * @param Id 产品检验标准ID
     * @return 产品检验标准
     */
    public BsPqcMain selectBsPqcMainById(String Id);

    /**
     * 查询产品检验标准列表
     * 
     * @param bsPqcMain 产品检验标准
     * @return 产品检验标准集合
     */
    public List<BsPqcMain> selectBsPqcMainList(BsPqcMain bsPqcMain);

    /**
     * 新增产品检验标准
     * 
     * @param bsPqcMain 产品检验标准
     * @return 结果
     */
    public int insertBsPqcMain(BsPqcMain bsPqcMain);

    /**
     * 修改产品检验标准
     * 
     * @param bsPqcMain 产品检验标准
     * @return 结果
     */
    public int updateBsPqcMain(BsPqcMain bsPqcMain);

    /**
     * 批量删除产品检验标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsPqcMainByIds(String ids);

    /**
     * 删除产品检验标准信息
     * 
     * @param Id 产品检验标准ID
     * @return 结果
     */
    public int deleteBsPqcMainById(String Id);
}
