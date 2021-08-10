package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.QcProdCheckDetailMapper;
import com.saas.pssc.domain.QcProdCheckDetail;
import com.saas.pssc.service.IQcProdCheckDetailService;
import com.saas.common.core.text.Convert;

/**
 * 成品检验明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcProdCheckDetailServiceImpl implements IQcProdCheckDetailService 
{
    @Autowired
    private QcProdCheckDetailMapper qcProdCheckDetailMapper;

    /**
     * 查询成品检验明细
     * 
     * @param id 成品检验明细ID
     * @return 成品检验明细
     */
    @Override
    public QcProdCheckDetail selectQcProdCheckDetailById(String id)
    {
        return qcProdCheckDetailMapper.selectQcProdCheckDetailById(id);
    }

    /**
     * 查询成品检验明细列表
     * 
     * @param qcProdCheckDetail 成品检验明细
     * @return 成品检验明细
     */
    @Override
    public List<QcProdCheckDetail> selectQcProdCheckDetailList(QcProdCheckDetail qcProdCheckDetail)
    {
        return qcProdCheckDetailMapper.selectQcProdCheckDetailList(qcProdCheckDetail);
    }

    /**
     * 新增成品检验明细
     * 
     * @param qcProdCheckDetail 成品检验明细
     * @return 结果
     */
    @Override
    public int insertQcProdCheckDetail(QcProdCheckDetail qcProdCheckDetail)
    {
        qcProdCheckDetail.setCreateTime(DateUtils.getNowDate());
        return qcProdCheckDetailMapper.insertQcProdCheckDetail(qcProdCheckDetail);
    }

    /**
     * 修改成品检验明细
     * 
     * @param qcProdCheckDetail 成品检验明细
     * @return 结果
     */
    @Override
    public int updateQcProdCheckDetail(QcProdCheckDetail qcProdCheckDetail)
    {
        qcProdCheckDetail.setUpdateTime(DateUtils.getNowDate());
        return qcProdCheckDetailMapper.updateQcProdCheckDetail(qcProdCheckDetail);
    }

    /**
     * 删除成品检验明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQcProdCheckDetailByIds(String ids)
    {
        return qcProdCheckDetailMapper.deleteQcProdCheckDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检验信息
     * 
     * @param id 成品检验明细ID
     * @return 结果
     */
    @Override
    public int deleteQcProdCheckDetailById(String id)
    {
        return qcProdCheckDetailMapper.deleteQcProdCheckDetailById(id);
    }
}
