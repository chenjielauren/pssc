package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.InFStoreDetail;
import com.saas.pssc.domain.InFStoreMain;
import com.saas.pssc.mapper.InFStoreMainMapper;
import com.saas.pssc.service.IInFStoreMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存Service业务层处理
 * 
 * @author admin
 * @date 2021-07-22
 */
@Service
public class InFStoreMainServiceImpl implements IInFStoreMainService 
{
    @Autowired
    private InFStoreMainMapper inStoreMainMapper;

    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    @Override
    public InFStoreMain selectInStoreMainById(Long id)
    {
        return inStoreMainMapper.selectInStoreMainById(id);
    }

    /**
     * 查询库存列表
     * 
     * @param inStoreMain 库存
     * @return 库存
     */
    @Override
    public List<InFStoreMain> selectInStoreMainList(InFStoreMain inStoreMain)
    {
        return inStoreMainMapper.selectInStoreMainList(inStoreMain);
    }

    /**
     * 新增库存
     * 
     * @param inStoreMain 库存
     * @return 结果
     */
    @Transactional
    @Override
    public int insertInStoreMain(InFStoreMain inStoreMain)
    {
        inStoreMain.setCreateTime(DateUtils.getNowDate());
        int rows = inStoreMainMapper.insertInStoreMain(inStoreMain);
        insertInStoreDetail(inStoreMain);
        return rows;
    }

    /**
     * 修改库存
     * 
     * @param inStoreMain 库存
     * @return 结果
     */
    @Transactional
    @Override
    public int updateInStoreMain(InFStoreMain inStoreMain)
    {
        inStoreMain.setUpdateTime(DateUtils.getNowDate());
        inStoreMainMapper.deleteInStoreDetailByMainId(inStoreMain.getId());
        insertInStoreDetail(inStoreMain);
        return inStoreMainMapper.updateInStoreMain(inStoreMain);
    }

    /**
     * 删除库存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInStoreMainByIds(String ids)
    {
        inStoreMainMapper.deleteInStoreDetailByMainIds(Convert.toStrArray(ids));
        return inStoreMainMapper.updateInStoreMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    @Override
    public int deleteInStoreMainById(Long id)
    {
        inStoreMainMapper.deleteInStoreDetailByMainId(id);
        return inStoreMainMapper.updateInStoreMainById(id);
    }

    /**
     * 新增库存明细信息
     * 
     * @param inStoreMain 库存对象
     */
    public void insertInStoreDetail(InFStoreMain inStoreMain)
    {
        List<InFStoreDetail> inStoreDetailList = inStoreMain.getInStoreDetailList();
        Long id = inStoreMain.getId();
        if (StringUtils.isNotNull(inStoreDetailList))
        {
            List<InFStoreDetail> list = new ArrayList<InFStoreDetail>();
            for (InFStoreDetail inStoreDetail : inStoreDetailList)
            {
                inStoreDetail.setCreateBy(ShiroUtils.getLoginName());
                inStoreDetail.setUpdateBy(ShiroUtils.getLoginName());
                inStoreDetail.setMainId(id);
                list.add(inStoreDetail);
            }
            if (list.size() > 0)
            {
                inStoreMainMapper.batchInStoreDetail(list);
            }
        }
    }
}
