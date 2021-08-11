package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.BsBomDetail;
import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.mapper.BsBomMainMapper;
import com.saas.pssc.service.IBsBomMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * bom物料清单Service业务层处理
 * 
 * @author admin
 * @date 2021-08-09
 */
@Service
public class BsBomMainServiceImpl implements IBsBomMainService 
{
    @Autowired
    private BsBomMainMapper bsBomMainMapper;

    /**
     * 查询bom物料清单
     * 
     * @param id bom物料清单ID
     * @return bom物料清单
     */
    @Override
    public BsBomMain selectBsBomMainById(String id)
    {
        return bsBomMainMapper.selectBsBomMainById(id);
    }

    /**
     * 查询bom物料清单列表
     * 
     * @param bsBomMain bom物料清单
     * @return bom物料清单
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsBomMain> selectBsBomMainList(BsBomMain bsBomMain)
    {
        return bsBomMainMapper.selectBsBomMainList(bsBomMain);
    }

    /**
     * 新增bom物料清单
     * 
     * @param bsBomMain bom物料清单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBsBomMain(BsBomMain bsBomMain)
    {
        bsBomMain.setId(IdUtils.fastSimpleUUID());
        bsBomMain.setCreateTime(DateUtils.getNowDate());
        int rows = bsBomMainMapper.insertBsBomMain(bsBomMain);
        insertBsBomDetail(bsBomMain);
        return rows;
    }

    /**
     * 修改bom物料清单
     * 
     * @param bsBomMain bom物料清单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBsBomMain(BsBomMain bsBomMain)
    {
        bsBomMain.setUpdateTime(DateUtils.getNowDate());
        bsBomMainMapper.deleteBsBomDetailByMainId(bsBomMain.getId());
        insertBsBomDetail(bsBomMain);
        return bsBomMainMapper.updateBsBomMain(bsBomMain);
    }

    /**
     * 删除bom物料清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBsBomMainByIds(String ids)
    {
        bsBomMainMapper.deleteBsBomDetailByMainIds(Convert.toStrArray(ids));
        return bsBomMainMapper.updateBsBomMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bom物料清单信息
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    @Override
    public int deleteBsBomMainById(String id)
    {
        bsBomMainMapper.deleteBsBomDetailByMainId(id);
        return bsBomMainMapper.updateBsBomMainById(id);
    }

    /**
     * 新增bom物料清单明细信息
     * 
     * @param bsBomMain bom物料清单对象
     */
    public void insertBsBomDetail(BsBomMain bsBomMain)
    {
        List<BsBomDetail> bsBomDetailList = bsBomMain.getBsBomDetailList();
        String id = bsBomMain.getId();
        if (StringUtils.isNotNull(bsBomDetailList))
        {
            List<BsBomDetail> list = new ArrayList<BsBomDetail>();
            for (BsBomDetail bsBomDetail : bsBomDetailList)
            {
                bsBomDetail.setId(IdUtils.fastSimpleUUID());
                bsBomDetail.setCreateBy(ShiroUtils.getLoginName());
                bsBomDetail.setUpdateBy(ShiroUtils.getLoginName());
                bsBomDetail.setMainId(id);
                list.add(bsBomDetail);
            }
            if (list.size() > 0)
            {
                bsBomMainMapper.batchBsBomDetail(list);
            }
        }
    }
}
