package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.QcBadProjectDetailMapper;
import com.saas.pssc.domain.QcBadProjectDetail;
import com.saas.pssc.service.IQcBadProjectDetailService;
import com.saas.common.core.text.Convert;

/**
 * 不良项目记录明细Service业务层处理
 * 
 * @author admin
 * @date 2021-09-17
 */
@Service
public class QcBadProjectDetailServiceImpl implements IQcBadProjectDetailService 
{
    @Autowired
    private QcBadProjectDetailMapper qcBadProjectDetailMapper;

    /**
     * 查询不良项目记录明细
     * 
     * @param id 不良项目记录明细ID
     * @return 不良项目记录明细
     */
    @Override
    public QcBadProjectDetail selectQcBadProjectDetailById(String id)
    {
        return qcBadProjectDetailMapper.selectQcBadProjectDetailById(id);
    }

    /**
     * 查询不良项目记录明细列表
     * 
     * @param qcBadProjectDetail 不良项目记录明细
     * @return 不良项目记录明细
     */
    @Override
    public List<QcBadProjectDetail> selectQcBadProjectDetailList(QcBadProjectDetail qcBadProjectDetail)
    {
        return qcBadProjectDetailMapper.selectQcBadProjectDetailList(qcBadProjectDetail);
    }

    /**
     * 新增不良项目记录明细
     * 
     * @param qcBadProjectDetail 不良项目记录明细
     * @return 结果
     */
    @Override
    public int insertQcBadProjectDetail(QcBadProjectDetail qcBadProjectDetail)
    {
        qcBadProjectDetail.setCreateTime(DateUtils.getNowDate());
        return qcBadProjectDetailMapper.insertQcBadProjectDetail(qcBadProjectDetail);
    }

    /**
     * 修改不良项目记录明细
     * 
     * @param qcBadProjectDetail 不良项目记录明细
     * @return 结果
     */
    @Override
    public int updateQcBadProjectDetail(QcBadProjectDetail qcBadProjectDetail)
    {
        qcBadProjectDetail.setUpdateTime(DateUtils.getNowDate());
        return qcBadProjectDetailMapper.updateQcBadProjectDetail(qcBadProjectDetail);
    }

    /**
     * 删除不良项目记录明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQcBadProjectDetailByIds(String ids)
    {
        return qcBadProjectDetailMapper.deleteQcBadProjectDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除不良项目记录明细信息
     * 
     * @param id 不良项目记录明细ID
     * @return 结果
     */
    @Override
    public int deleteQcBadProjectDetailById(String id)
    {
        return qcBadProjectDetailMapper.deleteQcBadProjectDetailById(id);
    }
}
