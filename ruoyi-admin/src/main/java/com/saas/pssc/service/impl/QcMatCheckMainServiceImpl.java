package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.QcMatCheckDetail;
import com.saas.pssc.domain.QcMatCheckMain;
import com.saas.pssc.mapper.QcMatCheckMainMapper;
import com.saas.pssc.service.IQcMatCheckMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 原材料检验记录Service业务层处理
 * 
 * @author admin
 * @date 2021-07-25
 */
@Service
public class QcMatCheckMainServiceImpl implements IQcMatCheckMainService 
{
    @Autowired
    private QcMatCheckMainMapper qcMatCheckMainMapper;

    /**
     * 查询原材料检验记录
     * 
     * @param id 原材料检验记录ID
     * @return 原材料检验记录
     */
    @Override
    public QcMatCheckMain selectQcMatCheckMainById(String id)
    {
        return qcMatCheckMainMapper.selectQcMatCheckMainById(id);
    }

    /**
     * 查询原材料检验记录列表
     * 
     * @param qcMatCheckMain 原材料检验记录
     * @return 原材料检验记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<QcMatCheckMain> selectQcMatCheckMainList(QcMatCheckMain qcMatCheckMain)
    {
        return qcMatCheckMainMapper.selectQcMatCheckMainList(qcMatCheckMain);
    }

    /**
     * 新增原材料检验记录
     * 
     * @param qcMatCheckMain 原材料检验记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertQcMatCheckMain(QcMatCheckMain qcMatCheckMain)
    {
        qcMatCheckMain.setId(IdUtils.fastSimpleUUID());
        qcMatCheckMain.setCreateTime(DateUtils.getNowDate());
        int rows = qcMatCheckMainMapper.insertQcMatCheckMain(qcMatCheckMain);
        insertQcMatCheckDetail(qcMatCheckMain);
        return rows;
    }

    /**
     * 修改原材料检验记录
     * 
     * @param qcMatCheckMain 原材料检验记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateQcMatCheckMain(QcMatCheckMain qcMatCheckMain)
    {
        qcMatCheckMain.setUpdateTime(DateUtils.getNowDate());
        qcMatCheckMainMapper.deleteQcMatCheckDetailByMainId(qcMatCheckMain.getId());
        insertQcMatCheckDetail(qcMatCheckMain);
        return qcMatCheckMainMapper.updateQcMatCheckMain(qcMatCheckMain);
    }

    /**
     * 删除原材料检验记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteQcMatCheckMainByIds(String ids)
    {
        qcMatCheckMainMapper.deleteQcMatCheckDetailByMainIds(Convert.toStrArray(ids));
        return qcMatCheckMainMapper.updateQcMatCheckMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除原材料检验记录信息
     * 
     * @param id 原材料检验记录ID
     * @return 结果
     */
    @Override
    public int deleteQcMatCheckMainById(String id)
    {
        qcMatCheckMainMapper.deleteQcMatCheckDetailByMainId(id);
        return qcMatCheckMainMapper.updateQcMatCheckMainById(id);
    }

    /**
     * 新增材料检验信息
     * 
     * @param qcMatCheckMain 原材料检验记录对象
     */
    public void insertQcMatCheckDetail(QcMatCheckMain qcMatCheckMain)
    {
        List<QcMatCheckDetail> qcMatCheckDetailList = qcMatCheckMain.getQcMatCheckDetailList();
        String id = qcMatCheckMain.getId();
        if (StringUtils.isNotNull(qcMatCheckDetailList))
        {
            List<QcMatCheckDetail> list = new ArrayList<QcMatCheckDetail>();
            for (QcMatCheckDetail qcMatCheckDetail : qcMatCheckDetailList)
            {
                qcMatCheckDetail.setId(IdUtils.fastSimpleUUID());
                qcMatCheckDetail.setCreateBy(ShiroUtils.getLoginName());
                qcMatCheckDetail.setUpdateBy(ShiroUtils.getLoginName());
                qcMatCheckDetail.setMainId(id);
                list.add(qcMatCheckDetail);
            }
            if (list.size() > 0)
            {
                qcMatCheckMainMapper.batchQcMatCheckDetail(list);
            }
        }
    }
}
