package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.domain.BsPqcDetail;

/**
 * 产品检验标准Mapper接口
 * 
 * @author admin
 * @date 2021-07-15
 */
public interface BsPqcMainMapper 
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
     * 删除产品检验标准
     * 
     * @param Id 产品检验标准ID
     * @return 结果
     */
    public int deleteBsPqcMainById(String Id);

    /**
     * 批量删除产品检验标准
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsPqcMainByIds(String[] Ids);

    /**
     * 失效产品检验标准
     * 
     * @param Id 产品检验标准ID
     * @return 结果
     */
    public int updateBsPqcMainById(String Id);

    /**
     * 批量失效产品检验标准
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBsPqcMainByIds(String[] Ids);

    /**
     * 批量删除产品检验标准明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsPqcDetailByMainIds(String[] Ids);
    
    /**
     * 批量新增产品检验标准明细
     * 
     * @param bsPqcDetailList 产品检验标准明细列表
     * @return 结果
     */
    public int batchBsPqcDetail(List<BsPqcDetail> bsPqcDetailList);
    

    /**
     * 通过产品检验标准ID删除产品检验标准明细信息
     * 
     * @param string 产品检验标准ID
     * @return 结果
     */
    public int deleteBsPqcDetailByMainId(String string);
}
