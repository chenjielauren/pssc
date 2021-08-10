package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.QcMatCheckDetailMapper;
import com.saas.pssc.domain.QcMatCheckDetail;
import com.saas.pssc.service.IQcMatCheckDetailService;
import com.saas.common.core.text.Convert;

/**
 * 原材料检验明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcMatCheckDetailServiceImpl implements IQcMatCheckDetailService 
{
    @Autowired
    private QcMatCheckDetailMapper qcMatCheckDetailMapper;

    /**
     * 查询原材料检验明细
     * 
     * @param id 原材料检验明细ID
     * @return 原材料检验明细
     */
    @Override
    public QcMatCheckDetail selectQcMatCheckDetailById(String id)
    {
        return qcMatCheckDetailMapper.selectQcMatCheckDetailById(id);
    }

    /**
     * 查询原材料检验明细列表
     * 
     * @param qcMatCheckDetail 原材料检验明细
     * @return 原材料检验明细
     */
    @Override
    public List<QcMatCheckDetail> selectQcMatCheckDetailList(QcMatCheckDetail qcMatCheckDetail)
    {
        return qcMatCheckDetailMapper.selectQcMatCheckDetailList(qcMatCheckDetail);
    }

    /**
     * 新增原材料检验明细
     * 
     * @param qcMatCheckDetail 原材料检验明细
     * @return 结果
     */
    @Override
    public int insertQcMatCheckDetail(QcMatCheckDetail qcMatCheckDetail)
    {
        qcMatCheckDetail.setCreateTime(DateUtils.getNowDate());
        return qcMatCheckDetailMapper.insertQcMatCheckDetail(qcMatCheckDetail);
    }

    /**
     * 修改原材料检验明细
     * 
     * @param qcMatCheckDetail 原材料检验明细
     * @return 结果
     */
    @Override
    public int updateQcMatCheckDetail(QcMatCheckDetail qcMatCheckDetail)
    {
        qcMatCheckDetail.setUpdateTime(DateUtils.getNowDate());
        return qcMatCheckDetailMapper.updateQcMatCheckDetail(qcMatCheckDetail);
    }

    /**
     * 删除原材料检验明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQcMatCheckDetailByIds(String ids)
    {
        return qcMatCheckDetailMapper.deleteQcMatCheckDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除材料检验信息
     * 
     * @param id 原材料检验明细ID
     * @return 结果
     */
    @Override
    public int deleteQcMatCheckDetailById(String id)
    {
        return qcMatCheckDetailMapper.deleteQcMatCheckDetailById(id);
    }
}
