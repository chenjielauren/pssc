package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.PpWorkYield;

/**
 * 工序成品率Mapper接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface PpWorkYieldMapper 
{
    /**
     * 查询工序成品率
     * 
     * @param id 工序成品率ID
     * @return 工序成品率
     */
    public PpWorkYield selectPpWorkYieldById(Long id);

    /**
     * 查询工序成品率列表
     * 
     * @param ppWorkYield 工序成品率
     * @return 工序成品率集合
     */
    public List<PpWorkYield> selectPpWorkYieldList(PpWorkYield ppWorkYield);

    /**
     * 新增工序成品率
     * 
     * @param ppWorkYield 工序成品率
     * @return 结果
     */
    public int insertPpWorkYield(PpWorkYield ppWorkYield);

    /**
     * 修改工序成品率
     * 
     * @param ppWorkYield 工序成品率
     * @return 结果
     */
    public int updatePpWorkYield(PpWorkYield ppWorkYield);

    /**
     * 失效工序成品率
     * 
     * @param id 工序成品率ID
     * @return 结果
     */
    public int updatePpWorkYieldById(Long id);

    /**
     * 批量失效工序成品率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updatePpWorkYieldByIds(String[] ids);

    /**
     * 删除工序成品率
     * 
     * @param id 工序成品率ID
     * @return 结果
     */
    public int deletePpWorkYieldById(Long id);

    /**
     * 批量删除工序成品率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpWorkYieldByIds(String[] ids);
}
