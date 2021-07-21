package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsCraftSopDetailMapper;
import com.saas.pssc.domain.BsCraftSopDetail;
import com.saas.pssc.service.IBsCraftSopDetailService;
import com.saas.common.core.text.Convert;

/**
 * 工艺标准与CCP明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-19
 */
@Service
public class BsCraftSopDetailServiceImpl implements IBsCraftSopDetailService 
{
    @Autowired
    private BsCraftSopDetailMapper bsCraftSopDetailMapper;

    /**
     * 查询工艺标准与CCP明细
     * 
     * @param id 工艺标准与CCP明细ID
     * @return 工艺标准与CCP明细
     */
    @Override
    public BsCraftSopDetail selectBsCraftSopDetailById(Long id)
    {
        return bsCraftSopDetailMapper.selectBsCraftSopDetailById(id);
    }

    /**
     * 查询工艺标准与CCP明细列表
     * 
     * @param bsCraftSopDetail 工艺标准与CCP明细
     * @return 工艺标准与CCP明细
     */
    @Override
    public List<BsCraftSopDetail> selectBsCraftSopDetailList(BsCraftSopDetail bsCraftSopDetail)
    {
        return bsCraftSopDetailMapper.selectBsCraftSopDetailList(bsCraftSopDetail);
    }

    /**
     * 新增工艺标准与CCP明细
     * 
     * @param bsCraftSopDetail 工艺标准与CCP明细
     * @return 结果
     */
    @Override
    public int insertBsCraftSopDetail(BsCraftSopDetail bsCraftSopDetail)
    {
        bsCraftSopDetail.setCreateTime(DateUtils.getNowDate());
        return bsCraftSopDetailMapper.insertBsCraftSopDetail(bsCraftSopDetail);
    }

    /**
     * 修改工艺标准与CCP明细
     * 
     * @param bsCraftSopDetail 工艺标准与CCP明细
     * @return 结果
     */
    @Override
    public int updateBsCraftSopDetail(BsCraftSopDetail bsCraftSopDetail)
    {
        bsCraftSopDetail.setUpdateTime(DateUtils.getNowDate());
        return bsCraftSopDetailMapper.updateBsCraftSopDetail(bsCraftSopDetail);
    }

    /**
     * 删除工艺标准与CCP明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsCraftSopDetailByIds(String ids)
    {
        return bsCraftSopDetailMapper.deleteBsCraftSopDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工艺标准与CCP明细信息
     * 
     * @param id 工艺标准与CCP明细ID
     * @return 结果
     */
    @Override
    public int deleteBsCraftSopDetailById(Long id)
    {
        return bsCraftSopDetailMapper.deleteBsCraftSopDetailById(id);
    }
}
