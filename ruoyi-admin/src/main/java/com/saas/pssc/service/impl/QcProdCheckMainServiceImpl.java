package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.QcProdCheckDetail;
import com.saas.pssc.domain.QcProdCheckMain;
import com.saas.pssc.mapper.QcProdCheckMainMapper;
import com.saas.pssc.service.IQcProdCheckMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 成品检验记录Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcProdCheckMainServiceImpl implements IQcProdCheckMainService 
{
    @Autowired
    private QcProdCheckMainMapper qcProdCheckMainMapper;

    /**
     * 查询成品检验记录
     * 
     * @param id 成品检验记录ID
     * @return 成品检验记录
     */
    @Override
    public QcProdCheckMain selectQcProdCheckMainById(String id)
    {
        return qcProdCheckMainMapper.selectQcProdCheckMainById(id);
    }

    /**
     * 查询成品检验记录列表
     * 
     * @param qcProdCheckMain 成品检验记录
     * @return 成品检验记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<QcProdCheckMain> selectQcProdCheckMainList(QcProdCheckMain qcProdCheckMain)
    {
        return qcProdCheckMainMapper.selectQcProdCheckMainList(qcProdCheckMain);
    }

    /**
     * 新增成品检验记录
     * 
     * @param qcProdCheckMain 成品检验记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertQcProdCheckMain(QcProdCheckMain qcProdCheckMain)
    {
        qcProdCheckMain.setId(IdUtils.fastSimpleUUID());
        qcProdCheckMain.setCreateTime(DateUtils.getNowDate());
        int rows = qcProdCheckMainMapper.insertQcProdCheckMain(qcProdCheckMain);
        insertQcProdCheckDetail(qcProdCheckMain);
        return rows;
    }

    /**
     * 修改成品检验记录
     * 
     * @param qcProdCheckMain 成品检验记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateQcProdCheckMain(QcProdCheckMain qcProdCheckMain)
    {
        qcProdCheckMain.setUpdateTime(DateUtils.getNowDate());
        qcProdCheckMainMapper.deleteQcProdCheckDetailByMainId(qcProdCheckMain.getId());
        insertQcProdCheckDetail(qcProdCheckMain);
        return qcProdCheckMainMapper.updateQcProdCheckMain(qcProdCheckMain);
    }

    /**
     * 删除成品检验记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteQcProdCheckMainByIds(String ids)
    {
        qcProdCheckMainMapper.deleteQcProdCheckDetailByMainIds(Convert.toStrArray(ids));
        return qcProdCheckMainMapper.deleteQcProdCheckMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基本信息
     * 
     * @param id 成品检验记录ID
     * @return 结果
     */
    @Override
    public int deleteQcProdCheckMainById(String id)
    {
        qcProdCheckMainMapper.deleteQcProdCheckDetailByMainId(id);
        return qcProdCheckMainMapper.deleteQcProdCheckMainById(id);
    }

    /**
     * 新增检验信息
     * 
     * @param qcProdCheckMain 成品检验记录对象
     */
    public void insertQcProdCheckDetail(QcProdCheckMain qcProdCheckMain)
    {
        List<QcProdCheckDetail> qcProdCheckDetailList = qcProdCheckMain.getQcProdCheckDetailList();
        String id = qcProdCheckMain.getId();
        if (StringUtils.isNotNull(qcProdCheckDetailList))
        {
            List<QcProdCheckDetail> list = new ArrayList<QcProdCheckDetail>();
            for (QcProdCheckDetail qcProdCheckDetail : qcProdCheckDetailList)
            {
                qcProdCheckDetail.setId(IdUtils.fastSimpleUUID());
                qcProdCheckDetail.setCreateBy(ShiroUtils.getLoginName());
                qcProdCheckDetail.setUpdateBy(ShiroUtils.getLoginName());
                qcProdCheckDetail.setMainId(id);
                list.add(qcProdCheckDetail);
            }
            if (list.size() > 0)
            {
                qcProdCheckMainMapper.batchQcProdCheckDetail(list);
            }
        }
    }
    
    /**
     * 订单号→所有工单→成品检验报告结果
     * 
     * @param dcode 订单号
     * @return 结果
     */
    @Override
    public List<String> selectQcResult(String dcode) {
        return qcProdCheckMainMapper.selectQcResult(dcode);
    }
}
