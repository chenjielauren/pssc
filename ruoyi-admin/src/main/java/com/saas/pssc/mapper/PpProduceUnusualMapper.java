package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.PpProduceUnusual;

/**
 * 生产异常记录Mapper接口
 * 
 * @author admin
 * @date 2021-07-23
 */
public interface PpProduceUnusualMapper 
{
    /**
     * 查询生产异常记录
     * 
     * @param id 生产异常记录ID
     * @return 生产异常记录
     */
    public PpProduceUnusual selectPpProduceUnusualById(Long id);

    /**
     * 查询生产异常记录列表
     * 
     * @param ppProduceUnusual 生产异常记录
     * @return 生产异常记录集合
     */
    public List<PpProduceUnusual> selectPpProduceUnusualList(PpProduceUnusual ppProduceUnusual);

    /**
     * 新增生产异常记录
     * 
     * @param ppProduceUnusual 生产异常记录
     * @return 结果
     */
    public int insertPpProduceUnusual(PpProduceUnusual ppProduceUnusual);

    /**
     * 修改生产异常记录
     * 
     * @param ppProduceUnusual 生产异常记录
     * @return 结果
     */
    public int updatePpProduceUnusual(PpProduceUnusual ppProduceUnusual);

    /**
     * 失效生产异常记录
     * 
     * @param id 生产异常记录ID
     * @return 结果
     */
    public int updatePpProduceUnusualById(Long id);

    /**
     * 批量失效生产异常记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updatePpProduceUnusualByIds(String[] ids);

    /**
     * 删除生产异常记录
     * 
     * @param id 生产异常记录ID
     * @return 结果
     */
    public int deletePpProduceUnusualById(Long id);

    /**
     * 批量删除生产异常记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePpProduceUnusualByIds(String[] ids);
}
