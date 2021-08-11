package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.QcWoYieldRate;
import com.saas.pssc.mapper.QcWoYieldRateMapper;
import com.saas.pssc.service.IQcWoYieldRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工单良品率分析Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcWoYieldRateServiceImpl implements IQcWoYieldRateService 
{
    @Autowired
    private QcWoYieldRateMapper qcWoYieldRateMapper;

    /**
     * 查询工单良品率分析
     * 
     * @param id 工单良品率分析ID
     * @return 工单良品率分析
     */
    @Override
    public QcWoYieldRate selectQcWoYieldRateById(String id)
    {
        return qcWoYieldRateMapper.selectQcWoYieldRateById(id);
    }

    /**
     * 查询工单良品率分析列表
     * 
     * @param qcWoYieldRate 工单良品率分析
     * @return 工单良品率分析
     */
    @Override
    @DataScope(userAlias = "su")
    public List<QcWoYieldRate> selectQcWoYieldRateList(QcWoYieldRate qcWoYieldRate)
    {
        return qcWoYieldRateMapper.selectQcWoYieldRateList(qcWoYieldRate);
    }

    /**
     * 新增工单良品率分析
     * 
     * @param qcWoYieldRate 工单良品率分析
     * @return 结果
     */
    @Override
    public int insertQcWoYieldRate(QcWoYieldRate qcWoYieldRate)
    {
        qcWoYieldRate.setId(IdUtils.fastSimpleUUID());
        qcWoYieldRate.setCreateTime(DateUtils.getNowDate());
        return qcWoYieldRateMapper.insertQcWoYieldRate(qcWoYieldRate);
    }

    /**
     * 修改工单良品率分析
     * 
     * @param qcWoYieldRate 工单良品率分析
     * @return 结果
     */
    @Override
    public int updateQcWoYieldRate(QcWoYieldRate qcWoYieldRate)
    {
        qcWoYieldRate.setUpdateTime(DateUtils.getNowDate());
        return qcWoYieldRateMapper.updateQcWoYieldRate(qcWoYieldRate);
    }

    /**
     * 删除工单良品率分析对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQcWoYieldRateByIds(String ids)
    {
        return qcWoYieldRateMapper.updateQcWoYieldRateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单良品率分析信息
     * 
     * @param id 工单良品率分析ID
     * @return 结果
     */
    @Override
    public int deleteQcWoYieldRateById(String id)
    {
        return qcWoYieldRateMapper.updateQcWoYieldRateById(id);
    }
}
