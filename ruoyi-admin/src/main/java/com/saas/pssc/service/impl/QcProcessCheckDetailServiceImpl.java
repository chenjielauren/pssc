package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.QcProcessCheckDetailMapper;
import com.saas.pssc.domain.QcProcessCheckDetail;
import com.saas.pssc.service.IQcProcessCheckDetailService;
import com.saas.common.core.text.Convert;

/**
 * 过程检验明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcProcessCheckDetailServiceImpl implements IQcProcessCheckDetailService 
{
    @Autowired
    private QcProcessCheckDetailMapper qcProcessCheckDetailMapper;

    /**
     * 查询过程检验明细
     * 
     * @param id 过程检验明细ID
     * @return 过程检验明细
     */
    @Override
    public QcProcessCheckDetail selectQcProcessCheckDetailById(String id)
    {
        return qcProcessCheckDetailMapper.selectQcProcessCheckDetailById(id);
    }

    /**
     * 查询过程检验明细列表
     * 
     * @param qcProcessCheckDetail 过程检验明细
     * @return 过程检验明细
     */
    @Override
    public List<QcProcessCheckDetail> selectQcProcessCheckDetailList(QcProcessCheckDetail qcProcessCheckDetail)
    {
        return qcProcessCheckDetailMapper.selectQcProcessCheckDetailList(qcProcessCheckDetail);
    }

    /**
     * 新增过程检验明细
     * 
     * @param qcProcessCheckDetail 过程检验明细
     * @return 结果
     */
    @Override
    public int insertQcProcessCheckDetail(QcProcessCheckDetail qcProcessCheckDetail)
    {
        qcProcessCheckDetail.setCreateTime(DateUtils.getNowDate());
        return qcProcessCheckDetailMapper.insertQcProcessCheckDetail(qcProcessCheckDetail);
    }

    /**
     * 修改过程检验明细
     * 
     * @param qcProcessCheckDetail 过程检验明细
     * @return 结果
     */
    @Override
    public int updateQcProcessCheckDetail(QcProcessCheckDetail qcProcessCheckDetail)
    {
        qcProcessCheckDetail.setUpdateTime(DateUtils.getNowDate());
        return qcProcessCheckDetailMapper.updateQcProcessCheckDetail(qcProcessCheckDetail);
    }

    /**
     * 删除过程检验明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQcProcessCheckDetailByIds(String ids)
    {
        return qcProcessCheckDetailMapper.deleteQcProcessCheckDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检验标准信息
     * 
     * @param id 过程检验明细ID
     * @return 结果
     */
    @Override
    public int deleteQcProcessCheckDetailById(String id)
    {
        return qcProcessCheckDetailMapper.deleteQcProcessCheckDetailById(id);
    }
}
