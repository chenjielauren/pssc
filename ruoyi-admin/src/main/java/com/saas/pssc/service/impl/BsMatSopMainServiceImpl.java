package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.BsMatSopDetail;
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.mapper.BsMatSopMainMapper;
import com.saas.pssc.service.IBsMatSopMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 材料检验标准Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class BsMatSopMainServiceImpl implements IBsMatSopMainService 
{
    @Autowired
    private BsMatSopMainMapper bsMatSopMainMapper;

    /**
     * 查询材料检验标准
     * 
     * @param id 材料检验标准ID
     * @return 材料检验标准
     */
    @Override
    public BsMatSopMain selectBsMatSopMainById(String id)
    {
        return bsMatSopMainMapper.selectBsMatSopMainById(id);
    }

    /**
     * 查询材料检验标准列表
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 材料检验标准
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsMatSopMain> selectBsMatSopMainList(BsMatSopMain bsMatSopMain)
    {
        return bsMatSopMainMapper.selectBsMatSopMainList(bsMatSopMain);
    }

    /**
     * 新增材料检验标准
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBsMatSopMain(BsMatSopMain bsMatSopMain)
    {
        bsMatSopMain.setId(IdUtils.fastSimpleUUID());
        bsMatSopMain.setCreateTime(DateUtils.getNowDate());
        int rows = bsMatSopMainMapper.insertBsMatSopMain(bsMatSopMain);
        insertBsMatSopDetail(bsMatSopMain);
        return rows;
    }

    /**
     * 修改材料检验标准
     * 
     * @param bsMatSopMain 材料检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBsMatSopMain(BsMatSopMain bsMatSopMain)
    {
        bsMatSopMain.setUpdateTime(DateUtils.getNowDate());
        bsMatSopMainMapper.deleteBsMatSopDetailByMainId(bsMatSopMain.getId());
        insertBsMatSopDetail(bsMatSopMain);
        return bsMatSopMainMapper.updateBsMatSopMain(bsMatSopMain);
    }

    /**
     * 删除材料检验标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBsMatSopMainByIds(String ids)
    {
        bsMatSopMainMapper.deleteBsMatSopDetailByMainIds(Convert.toStrArray(ids));
        return bsMatSopMainMapper.updateBsMatSopMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除材料检验标准信息
     * 
     * @param id 材料检验标准ID
     * @return 结果
     */
    @Override
    public int deleteBsMatSopMainById(String id)
    {
        bsMatSopMainMapper.deleteBsMatSopDetailByMainId(id);
        return bsMatSopMainMapper.updateBsMatSopMainById(id);
    }

    /**
     * 新增材料检验标准明细信息
     * 
     * @param bsMatSopMain 材料检验标准对象
     */
    public void insertBsMatSopDetail(BsMatSopMain bsMatSopMain)
    {
        List<BsMatSopDetail> bsMatSopDetailList = bsMatSopMain.getBsMatSopDetailList();
        String id = bsMatSopMain.getId();
        if (StringUtils.isNotNull(bsMatSopDetailList))
        {
            List<BsMatSopDetail> list = new ArrayList<BsMatSopDetail>();
            for (BsMatSopDetail bsMatSopDetail : bsMatSopDetailList)
            {
                bsMatSopDetail.setId(IdUtils.fastSimpleUUID());
                bsMatSopDetail.setMainId(id);
                bsMatSopDetail.setCreateBy(ShiroUtils.getLoginName());
                bsMatSopDetail.setUpdateBy(ShiroUtils.getLoginName());
                list.add(bsMatSopDetail);
            }
            if (list.size() > 0)
            {
                bsMatSopMainMapper.batchBsMatSopDetail(list);
            }
        }
    }
}
