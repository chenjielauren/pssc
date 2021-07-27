package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.QcProcessCheckDetail;
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.mapper.QcProcessCheckMainMapper;
import com.saas.pssc.service.IQcProcessCheckMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 过程检验记录Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcProcessCheckMainServiceImpl implements IQcProcessCheckMainService 
{
    @Autowired
    private QcProcessCheckMainMapper qcProcessCheckMainMapper;

    /**
     * 查询过程检验记录
     * 
     * @param id 过程检验记录ID
     * @return 过程检验记录
     */
    @Override
    public QcProcessCheckMain selectQcProcessCheckMainById(Long id)
    {
        return qcProcessCheckMainMapper.selectQcProcessCheckMainById(id);
    }

    /**
     * 查询过程检验记录列表
     * 
     * @param qcProcessCheckMain 过程检验记录
     * @return 过程检验记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<QcProcessCheckMain> selectQcProcessCheckMainList(QcProcessCheckMain qcProcessCheckMain)
    {
        return qcProcessCheckMainMapper.selectQcProcessCheckMainList(qcProcessCheckMain);
    }

    /**
     * 新增过程检验记录
     * 
     * @param qcProcessCheckMain 过程检验记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertQcProcessCheckMain(QcProcessCheckMain qcProcessCheckMain)
    {
        qcProcessCheckMain.setCreateTime(DateUtils.getNowDate());
        int rows = qcProcessCheckMainMapper.insertQcProcessCheckMain(qcProcessCheckMain);
        insertQcProcessCheckDetail(qcProcessCheckMain);
        return rows;
    }

    /**
     * 修改过程检验记录
     * 
     * @param qcProcessCheckMain 过程检验记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateQcProcessCheckMain(QcProcessCheckMain qcProcessCheckMain)
    {
        qcProcessCheckMain.setUpdateTime(DateUtils.getNowDate());
        qcProcessCheckMainMapper.deleteQcProcessCheckDetailByMainId(qcProcessCheckMain.getId());
        insertQcProcessCheckDetail(qcProcessCheckMain);
        return qcProcessCheckMainMapper.updateQcProcessCheckMain(qcProcessCheckMain);
    }

    /**
     * 删除过程检验记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteQcProcessCheckMainByIds(String ids)
    {
        qcProcessCheckMainMapper.deleteQcProcessCheckDetailByMainIds(Convert.toStrArray(ids));
        return qcProcessCheckMainMapper.deleteQcProcessCheckMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基本信息
     * 
     * @param id 过程检验记录ID
     * @return 结果
     */
    @Override
    public int deleteQcProcessCheckMainById(Long id)
    {
        qcProcessCheckMainMapper.deleteQcProcessCheckDetailByMainId(id);
        return qcProcessCheckMainMapper.deleteQcProcessCheckMainById(id);
    }

    /**
     * 新增检验标准信息
     * 
     * @param qcProcessCheckMain 过程检验记录对象
     */
    public void insertQcProcessCheckDetail(QcProcessCheckMain qcProcessCheckMain)
    {
        List<QcProcessCheckDetail> qcProcessCheckDetailList = qcProcessCheckMain.getQcProcessCheckDetailList();
        Long id = qcProcessCheckMain.getId();
        if (StringUtils.isNotNull(qcProcessCheckDetailList))
        {
            List<QcProcessCheckDetail> list = new ArrayList<QcProcessCheckDetail>();
            for (QcProcessCheckDetail qcProcessCheckDetail : qcProcessCheckDetailList)
            {
                qcProcessCheckDetail.setCreateBy(ShiroUtils.getLoginName());
                qcProcessCheckDetail.setUpdateBy(ShiroUtils.getLoginName());
                qcProcessCheckDetail.setMainId(id);
                list.add(qcProcessCheckDetail);
            }
            if (list.size() > 0)
            {
                qcProcessCheckMainMapper.batchQcProcessCheckDetail(list);
            }
        }
    }
}
