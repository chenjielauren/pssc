package com.saas.pssc.service;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.BsCraftSopMain;

/**
 * 工艺标准与CCPService接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface IBsCraftSopMainService 
{
    /**
     * 查询工艺标准与CCP
     * 
     * @param id 工艺标准与CCPID
     * @return 工艺标准与CCP
     */
    public BsCraftSopMain selectBsCraftSopMainById(String id);

    /**
     * 查询工艺标准与CCP列表
     * 
     * @param bsCraftSopMain 工艺标准与CCP
     * @return 工艺标准与CCP集合
     */
    public List<BsCraftSopMain> selectBsCraftSopMainList(BsCraftSopMain bsCraftSopMain);

    /**
     * 新增工艺标准与CCP
     * 
     * @param bsCraftSopMain 工艺标准与CCP
     * @return 结果
     */
    public int insertBsCraftSopMain(BsCraftSopMain bsCraftSopMain);

    /**
     * 修改工艺标准与CCP
     * 
     * @param bsCraftSopMain 工艺标准与CCP
     * @return 结果
     */
    public int updateBsCraftSopMain(BsCraftSopMain bsCraftSopMain);

    /**
     * 批量删除工艺标准与CCP
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsCraftSopMainByIds(String ids);

    /**
     * 删除工艺标准与CCP信息
     * 
     * @param id 工艺标准与CCPID
     * @return 结果
     */
    public int deleteBsCraftSopMainById(String id);

	public BsCraftSopMain selectBsCraftSopMainByMap(Map<String, Object> paramMap);
}
