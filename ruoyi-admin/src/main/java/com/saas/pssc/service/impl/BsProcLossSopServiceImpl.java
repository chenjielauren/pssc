package com.saas.pssc.service.impl;

import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.BsProcLossSop;
import com.saas.pssc.mapper.BsProcLossSopMapper;
import com.saas.pssc.service.IBsProcLossSopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工序损耗标准Service业务层处理
 * 
 * @author admin
 * @date 2021-07-15
 */
@Service
public class BsProcLossSopServiceImpl implements IBsProcLossSopService 
{
    @Autowired
    private BsProcLossSopMapper bsProcLossSopMapper;

    /**
     * 查询工序损耗标准
     * 
     * @param Id 工序损耗标准ID
     * @return 工序损耗标准
     */
    @Override
    public BsProcLossSop selectBsProcLossSopById(Long Id)
    {
        return bsProcLossSopMapper.selectBsProcLossSopById(Id);
    }

    /**
     * 查询工序损耗标准列表
     * 
     * @param bsProcLossSop 工序损耗标准
     * @return 工序损耗标准
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsProcLossSop> selectBsProcLossSopList(BsProcLossSop bsProcLossSop)
    {
        return bsProcLossSopMapper.selectBsProcLossSopList(bsProcLossSop);
    }

    /**
     * 新增工序损耗标准
     * 
     * @param bsProcLossSop 工序损耗标准
     * @return 结果
     */
    @Override
    public int insertBsProcLossSop(BsProcLossSop bsProcLossSop)
    {
        bsProcLossSop.setCreateTime(DateUtils.getNowDate());
        return bsProcLossSopMapper.insertBsProcLossSop(bsProcLossSop);
    }

    /**
     * 修改工序损耗标准
     * 
     * @param bsProcLossSop 工序损耗标准
     * @return 结果
     */
    @Override
    public int updateBsProcLossSop(BsProcLossSop bsProcLossSop)
    {
        bsProcLossSop.setUpdateTime(DateUtils.getNowDate());
        return bsProcLossSopMapper.updateBsProcLossSop(bsProcLossSop);
    }

    /**
     * 删除工序损耗标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsProcLossSopByIds(String ids)
    {
        return bsProcLossSopMapper.updateBsProcLossSopByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工序损耗标准信息
     * 
     * @param Id 工序损耗标准ID
     * @return 结果
     */
    @Override
    public int deleteBsProcLossSopById(Long Id)
    {
        return bsProcLossSopMapper.updateBsProcLossSopById(Id);
    }
}
