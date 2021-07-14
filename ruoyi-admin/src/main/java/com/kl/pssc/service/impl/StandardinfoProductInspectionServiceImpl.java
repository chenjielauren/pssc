package com.kl.pssc.service.impl;

import java.util.List;
import com.kl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kl.pssc.mapper.StandardinfoProductInspectionMapper;
import com.kl.pssc.domain.StandardinfoProductInspection;
import com.kl.pssc.service.IStandardinfoProductInspectionService;
import com.kl.common.core.text.Convert;

/**
 * 产品检验标准明细信息Service业务层处理
 * 
 * @author admin
 * @date 2021-07-14
 */
@Service
public class StandardinfoProductInspectionServiceImpl implements IStandardinfoProductInspectionService 
{
    @Autowired
    private StandardinfoProductInspectionMapper standardinfoProductInspectionMapper;

    /**
     * 查询产品检验标准明细信息
     * 
     * @param inspectionId 产品检验标准明细信息ID
     * @return 产品检验标准明细信息
     */
    @Override
    public StandardinfoProductInspection selectStandardinfoProductInspectionById(Long inspectionId)
    {
        return standardinfoProductInspectionMapper.selectStandardinfoProductInspectionById(inspectionId);
    }

    /**
     * 查询产品检验标准明细信息列表
     * 
     * @param standardinfoProductInspection 产品检验标准明细信息
     * @return 产品检验标准明细信息
     */
    @Override
    public List<StandardinfoProductInspection> selectStandardinfoProductInspectionList(StandardinfoProductInspection standardinfoProductInspection)
    {
        return standardinfoProductInspectionMapper.selectStandardinfoProductInspectionList(standardinfoProductInspection);
    }

    /**
     * 新增产品检验标准明细信息
     * 
     * @param standardinfoProductInspection 产品检验标准明细信息
     * @return 结果
     */
    @Override
    public int insertStandardinfoProductInspection(StandardinfoProductInspection standardinfoProductInspection)
    {
        standardinfoProductInspection.setCreateTime(DateUtils.getNowDate());
        return standardinfoProductInspectionMapper.insertStandardinfoProductInspection(standardinfoProductInspection);
    }

    /**
     * 修改产品检验标准明细信息
     * 
     * @param standardinfoProductInspection 产品检验标准明细信息
     * @return 结果
     */
    @Override
    public int updateStandardinfoProductInspection(StandardinfoProductInspection standardinfoProductInspection)
    {
        standardinfoProductInspection.setUpdateTime(DateUtils.getNowDate());
        return standardinfoProductInspectionMapper.updateStandardinfoProductInspection(standardinfoProductInspection);
    }

    /**
     * 删除产品检验标准明细信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoProductInspectionByIds(String ids)
    {
        return standardinfoProductInspectionMapper.deleteStandardinfoProductInspectionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品检验标准明细信息信息
     * 
     * @param inspectionId 产品检验标准明细信息ID
     * @return 结果
     */
    @Override
    public int deleteStandardinfoProductInspectionById(Long inspectionId)
    {
        return standardinfoProductInspectionMapper.deleteStandardinfoProductInspectionById(inspectionId);
    }
}
