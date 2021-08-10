package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsBomDetailMapper;
import com.saas.pssc.domain.BsBomDetail;
import com.saas.pssc.service.IBsBomDetailService;
import com.saas.common.core.text.Convert;

/**
 * bom物料清单明细Service业务层处理
 * 
 * @author admin
 * @date 2021-08-09
 */
@Service
public class BsBomDetailServiceImpl implements IBsBomDetailService 
{
    @Autowired
    private BsBomDetailMapper bsBomDetailMapper;

    /**
     * 查询bom物料清单明细
     * 
     * @param id bom物料清单明细ID
     * @return bom物料清单明细
     */
    @Override
    public BsBomDetail selectBsBomDetailById(String id)
    {
        return bsBomDetailMapper.selectBsBomDetailById(id);
    }

    /**
     * 查询bom物料清单明细列表
     * 
     * @param bsBomDetail bom物料清单明细
     * @return bom物料清单明细
     */
    @Override
    public List<BsBomDetail> selectBsBomDetailList(BsBomDetail bsBomDetail)
    {
        return bsBomDetailMapper.selectBsBomDetailList(bsBomDetail);
    }

    /**
     * 新增bom物料清单明细
     * 
     * @param bsBomDetail bom物料清单明细
     * @return 结果
     */
    @Override
    public int insertBsBomDetail(BsBomDetail bsBomDetail)
    {
        bsBomDetail.setCreateTime(DateUtils.getNowDate());
        return bsBomDetailMapper.insertBsBomDetail(bsBomDetail);
    }

    /**
     * 修改bom物料清单明细
     * 
     * @param bsBomDetail bom物料清单明细
     * @return 结果
     */
    @Override
    public int updateBsBomDetail(BsBomDetail bsBomDetail)
    {
        bsBomDetail.setUpdateTime(DateUtils.getNowDate());
        return bsBomDetailMapper.updateBsBomDetail(bsBomDetail);
    }

    /**
     * 删除bom物料清单明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsBomDetailByIds(String ids)
    {
        return bsBomDetailMapper.deleteBsBomDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bom物料清单明细信息
     * 
     * @param id bom物料清单明细ID
     * @return 结果
     */
    @Override
    public int deleteBsBomDetailById(String id)
    {
        return bsBomDetailMapper.deleteBsBomDetailById(id);
    }
}
