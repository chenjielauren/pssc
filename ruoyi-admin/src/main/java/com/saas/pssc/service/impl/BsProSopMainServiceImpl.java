package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.BsProSopDetail;
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.mapper.BsProSopMainMapper;
import com.saas.pssc.service.IBsProSopMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 成品检验标准Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class BsProSopMainServiceImpl implements IBsProSopMainService 
{
    @Autowired
    private BsProSopMainMapper bsProSopMainMapper;

    /**
     * 查询成品检验标准
     * 
     * @param id 成品检验标准ID
     * @return 成品检验标准
     */
    @Override
    public BsProSopMain selectBsProSopMainById(String id)
    {
        return bsProSopMainMapper.selectBsProSopMainById(id);
    }

    /**
     * 查询成品检验标准列表
     * 
     * @param bsProSopMain 成品检验标准
     * @return 成品检验标准
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsProSopMain> selectBsProSopMainList(BsProSopMain bsProSopMain)
    {
        return bsProSopMainMapper.selectBsProSopMainList(bsProSopMain);
    }

    /**
     * 新增成品检验标准
     * 
     * @param bsProSopMain 成品检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBsProSopMain(BsProSopMain bsProSopMain)
    {
        bsProSopMain.setId(IdUtils.fastSimpleUUID());
        bsProSopMain.setCreateTime(DateUtils.getNowDate());
        int rows = bsProSopMainMapper.insertBsProSopMain(bsProSopMain);
        insertBsProSopDetail(bsProSopMain);
        return rows;
    }

    /**
     * 修改成品检验标准
     * 
     * @param bsProSopMain 成品检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBsProSopMain(BsProSopMain bsProSopMain)
    {
        bsProSopMain.setUpdateTime(DateUtils.getNowDate());
        bsProSopMainMapper.deleteBsProSopDetailByMainId(bsProSopMain.getId());
        insertBsProSopDetail(bsProSopMain);
        return bsProSopMainMapper.updateBsProSopMain(bsProSopMain);
    }

    /**
     * 删除成品检验标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBsProSopMainByIds(String ids)
    {
        bsProSopMainMapper.deleteBsProSopDetailByMainIds(Convert.toStrArray(ids));
        return bsProSopMainMapper.updateBsProSopMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成品检验标准信息
     * 
     * @param id 成品检验标准ID
     * @return 结果
     */
    @Override
    public int deleteBsProSopMainById(String id)
    {
        bsProSopMainMapper.deleteBsProSopDetailByMainId(id);
        return bsProSopMainMapper.updateBsProSopMainById(id);
    }

    /**
     * 新增成品检验标准明细信息
     * 
     * @param bsProSopMain 成品检验标准对象
     */
    public void insertBsProSopDetail(BsProSopMain bsProSopMain)
    {
        List<BsProSopDetail> bsProSopDetailList = bsProSopMain.getBsProSopDetailList();
        String id = bsProSopMain.getId();
        if (StringUtils.isNotNull(bsProSopDetailList))
        {
            List<BsProSopDetail> list = new ArrayList<BsProSopDetail>();
            for (BsProSopDetail bsProSopDetail : bsProSopDetailList)
            {
                bsProSopDetail.setId(IdUtils.fastSimpleUUID());
                bsProSopDetail.setMainId(id);
                bsProSopDetail.setCreateBy(ShiroUtils.getLoginName());
                bsProSopDetail.setUpdateBy(ShiroUtils.getLoginName());
                list.add(bsProSopDetail);
            }
            if (list.size() > 0)
            {
                bsProSopMainMapper.batchBsProSopDetail(list);
            }
        }
    }
}
