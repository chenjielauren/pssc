package com.saas.pssc.mapper;

import java.util.List;
import java.util.Map;

import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.domain.BsCraftSopDetail;

/**
 * 工艺标准与CCPMapper接口
 * 
 * @author admin
 * @date 2021-07-19
 */
public interface BsCraftSopMainMapper 
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
     * 失效工艺标准与CCP
     * 
     * @param id 工艺标准与CCPID
     * @return 结果
     */
    public int updateBsCraftSopMainById(String id);

    /**
     * 批量失效工艺标准与CCP
     * 
     * @param ids 需要失效的数据ID
     * @return 结果
     */
    public int updateBsCraftSopMainByIds(String[] ids);

    
    /**
     * 删除工艺标准与CCP
     * 
     * @param id 工艺标准与CCPID
     * @return 结果
     */
    public int deleteBsCraftSopMainById(Long id);

    /**
     * 批量删除工艺标准与CCP
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsCraftSopMainByIds(String[] ids);

    /**
     * 批量删除工艺标准与CCP明细
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBsCraftSopDetailByMainIds(String[] ids);
    
    /**
     * 批量新增工艺标准与CCP明细
     * 
     * @param bsCraftSopDetailList 工艺标准与CCP明细列表
     * @return 结果
     */
    public int batchBsCraftSopDetail(List<BsCraftSopDetail> bsCraftSopDetailList);
    

    /**
     * 通过工艺标准与CCPID删除工艺标准与CCP明细信息
     * 
     * @param id 工艺标准与CCPID
     * @return 结果
     */
    public int deleteBsCraftSopDetailByMainId(String id);

	public BsCraftSopMain selectBsCraftSopMainByMap(Map<String, Object> paramMap);
}
