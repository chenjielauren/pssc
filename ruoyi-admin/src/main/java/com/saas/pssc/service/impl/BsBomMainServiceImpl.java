package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsBomMainMapper;
import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.service.IBsBomMainService;
import com.saas.common.core.text.Convert;

/**
 * bom物料清单Service业务层处理
 * 
 * @author admin
 * @date 2021-07-19
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
    public BsBomMain selectBsBomMainById(Long id)
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
    @Override
    public int insertBsBomMain(BsBomMain bsBomMain)
    {
        bsBomMain.setCreateTime(DateUtils.getNowDate());
        return bsBomMainMapper.insertBsBomMain(bsBomMain);
    }

    /**
     * 修改bom物料清单
     * 
     * @param bsBomMain bom物料清单
     * @return 结果
     */
    @Override
    public int updateBsBomMain(BsBomMain bsBomMain)
    {
        bsBomMain.setUpdateTime(DateUtils.getNowDate());
        return bsBomMainMapper.updateBsBomMain(bsBomMain);
    }

    /**
     * 删除bom物料清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsBomMainByIds(String ids)
    {
        return bsBomMainMapper.deleteBsBomMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bom物料清单信息
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    @Override
    public int deleteBsBomMainById(Long id)
    {
        return bsBomMainMapper.deleteBsBomMainById(id);
    }
}
