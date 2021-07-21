package com.saas.pssc.mapper;

import java.util.List;
import com.saas.pssc.domain.BsProcLossSop;

/**
 * 工序损耗标准Mapper接口
 * 
 * @author admin
 * @date 2021-07-15
 */
public interface BsProcLossSopMapper 
{
    /**
     * 查询工序损耗标准
     * 
     * @param Id 工序损耗标准ID
     * @return 工序损耗标准
     */
    public BsProcLossSop selectBsProcLossSopById(Long Id);

    /**
     * 查询工序损耗标准列表
     * 
     * @param bsProcLossSop 工序损耗标准
     * @return 工序损耗标准集合
     */
    public List<BsProcLossSop> selectBsProcLossSopList(BsProcLossSop bsProcLossSop);

    /**
     * 新增工序损耗标准
     * 
     * @param bsProcLossSop 工序损耗标准
     * @return 结果
     */
    public int insertBsProcLossSop(BsProcLossSop bsProcLossSop);

    /**
     * 修改工序损耗标准
     * 
     * @param bsProcLossSop 工序损耗标准
     * @return 结果
     */
    public int updateBsProcLossSop(BsProcLossSop bsProcLossSop);

    /**
     * 删除工序损耗标准
     * 
     * @param Id 工序损耗标准ID
     * @return 结果
     */
    public int deleteBsProcLossSopById(Long Id);

     /**
     * 失效工序损耗标准
     * 
     * @param Id 工序损耗标准ID
     * @return 结果
     */
    public int updateBsProcLossSopById(Long Id);


    /**
     * 批量删除工序损耗标准
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsProcLossSopByIds(String[] Ids);

    /**
     * 批量失效工序损耗标准
     * 
     * @param Ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBsProcLossSopByIds(String[] Ids);
}
