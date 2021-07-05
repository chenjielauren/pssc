package com.ruoyi.pssc.standardinfo.mapper;

import java.util.List;
import com.ruoyi.pssc.standardinfo.domain.StandardinfoProcessloss;

/**
 * 工序损耗标准Mapper接口
 * 
 * @author lauren
 * @date 2021-07-05
 */
public interface StandardinfoProcesslossMapper 
{
    /**
     * 查询工序损耗标准
     * 
     * @param processlossId 工序损耗标准ID
     * @return 工序损耗标准
     */
    public StandardinfoProcessloss selectStandardinfoProcesslossById(Long processlossId);

    /**
     * 查询工序损耗标准列表
     * 
     * @param standardinfoProcessloss 工序损耗标准
     * @return 工序损耗标准集合
     */
    public List<StandardinfoProcessloss> selectStandardinfoProcesslossList(StandardinfoProcessloss standardinfoProcessloss);

    /**
     * 新增工序损耗标准
     * 
     * @param standardinfoProcessloss 工序损耗标准
     * @return 结果
     */
    public int insertStandardinfoProcessloss(StandardinfoProcessloss standardinfoProcessloss);

    /**
     * 修改工序损耗标准
     * 
     * @param standardinfoProcessloss 工序损耗标准
     * @return 结果
     */
    public int updateStandardinfoProcessloss(StandardinfoProcessloss standardinfoProcessloss);

    /**
     * 删除工序损耗标准
     * 
     * @param processlossId 工序损耗标准ID
     * @return 结果
     */
    public int deleteStandardinfoProcesslossById(Long processlossId);

    /**
     * 批量删除工序损耗标准
     * 
     * @param processlossIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardinfoProcesslossByIds(String[] processlossIds);
}
