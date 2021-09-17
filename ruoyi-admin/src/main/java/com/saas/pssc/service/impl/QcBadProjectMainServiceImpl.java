package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;

import org.springframework.transaction.annotation.Transactional;
import com.saas.pssc.domain.QcBadProjectDetail;
import com.saas.pssc.mapper.QcBadProjectMainMapper;
import com.saas.pssc.domain.QcBadProjectMain;
import com.saas.pssc.service.IQcBadProjectMainService;
import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;

/**
 * 不良项目记录Service业务层处理
 * 
 * @author admin
 * @date 2021-09-17
 */
@Service
public class QcBadProjectMainServiceImpl implements IQcBadProjectMainService 
{
    @Autowired
    private QcBadProjectMainMapper qcBadProjectMainMapper;

    /**
     * 查询不良项目记录
     * 
     * @param id 不良项目记录ID
     * @return 不良项目记录
     */
    @Override
    public QcBadProjectMain selectQcBadProjectMainById(String id)
    {
        return qcBadProjectMainMapper.selectQcBadProjectMainById(id);
    }

    /**
     * 查询不良项目记录列表
     * 
     * @param qcBadProjectMain 不良项目记录
     * @return 不良项目记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<QcBadProjectMain> selectQcBadProjectMainList(QcBadProjectMain qcBadProjectMain)
    {
        return qcBadProjectMainMapper.selectQcBadProjectMainList(qcBadProjectMain);
    }

    /**
     * 新增不良项目记录
     * 
     * @param qcBadProjectMain 不良项目记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertQcBadProjectMain(QcBadProjectMain qcBadProjectMain)
    {
        qcBadProjectMain.setId(IdUtils.fastSimpleUUID());
        qcBadProjectMain.setCreateTime(DateUtils.getNowDate());
        int rows = qcBadProjectMainMapper.insertQcBadProjectMain(qcBadProjectMain);
        insertQcBadProjectDetail(qcBadProjectMain);
        return rows;
    }

    /**
     * 修改不良项目记录
     * 
     * @param qcBadProjectMain 不良项目记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateQcBadProjectMain(QcBadProjectMain qcBadProjectMain)
    {
        qcBadProjectMain.setUpdateTime(DateUtils.getNowDate());
        qcBadProjectMainMapper.deleteQcBadProjectDetailByMainId(qcBadProjectMain.getId());
        insertQcBadProjectDetail(qcBadProjectMain);
        return qcBadProjectMainMapper.updateQcBadProjectMain(qcBadProjectMain);
    }

    /**
     * 删除不良项目记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteQcBadProjectMainByIds(String ids)
    {
        qcBadProjectMainMapper.deleteQcBadProjectDetailByMainIds(Convert.toStrArray(ids));
        return qcBadProjectMainMapper.updateQcBadProjectMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除不良项目记录信息
     * 
     * @param id 不良项目记录ID
     * @return 结果
     */
    @Override
    public int deleteQcBadProjectMainById(String id)
    {
        qcBadProjectMainMapper.deleteQcBadProjectDetailByMainId(id);
        return qcBadProjectMainMapper.updateQcBadProjectMainById(id);
    }

    /**
     * 新增不良项目记录明细信息
     * 
     * @param qcBadProjectMain 不良项目记录对象
     */
    public void insertQcBadProjectDetail(QcBadProjectMain qcBadProjectMain)
    {
        List<QcBadProjectDetail> qcBadProjectDetailList = qcBadProjectMain.getQcBadProjectDetailList();
        String id = qcBadProjectMain.getId();
        if (StringUtils.isNotNull(qcBadProjectDetailList))
        {
            List<QcBadProjectDetail> list = new ArrayList<QcBadProjectDetail>();
            for (QcBadProjectDetail qcBadProjectDetail : qcBadProjectDetailList)
            {
                qcBadProjectDetail.setId(IdUtils.fastSimpleUUID());
                qcBadProjectDetail.setMainId(id);
                qcBadProjectDetail.setCreateBy(ShiroUtils.getLoginName());
                qcBadProjectDetail.setUpdateBy(ShiroUtils.getLoginName());
                list.add(qcBadProjectDetail);
            }
            if (list.size() > 0)
            {
                qcBadProjectMainMapper.batchQcBadProjectDetail(list);
            }
        }
    }
}
