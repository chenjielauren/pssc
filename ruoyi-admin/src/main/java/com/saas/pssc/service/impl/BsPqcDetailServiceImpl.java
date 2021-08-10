package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.BsPqcDetailMapper;
import com.saas.pssc.domain.BsPqcDetail;
import com.saas.pssc.service.IBsPqcDetailService;
import com.saas.common.core.text.Convert;

/**
 * 产品检验标准明细Service业务层处理
 * 
 * @author admin
 * @date 2021-07-15
 */
@Service
public class BsPqcDetailServiceImpl implements IBsPqcDetailService 
{
    @Autowired
    private BsPqcDetailMapper bsPqcDetailMapper;

    /**
     * 查询产品检验标准明细
     * 
     * @param Id 产品检验标准明细ID
     * @return 产品检验标准明细
     */
    @Override
    public BsPqcDetail selectBsPqcDetailById(String Id)
    {
        return bsPqcDetailMapper.selectBsPqcDetailById(Id);
    }

    /**
     * 查询产品检验标准明细列表
     * 
     * @param bsPqcDetail 产品检验标准明细
     * @return 产品检验标准明细
     */
    @Override
    public List<BsPqcDetail> selectBsPqcDetailList(BsPqcDetail bsPqcDetail)
    {
        return bsPqcDetailMapper.selectBsPqcDetailList(bsPqcDetail);
    }

    /**
     * 新增产品检验标准明细
     * 
     * @param bsPqcDetail 产品检验标准明细
     * @return 结果
     */
    @Override
    public int insertBsPqcDetail(BsPqcDetail bsPqcDetail)
    {
        bsPqcDetail.setCreateTime(DateUtils.getNowDate());
        return bsPqcDetailMapper.insertBsPqcDetail(bsPqcDetail);
    }

    /**
     * 修改产品检验标准明细
     * 
     * @param bsPqcDetail 产品检验标准明细
     * @return 结果
     */
    @Override
    public int updateBsPqcDetail(BsPqcDetail bsPqcDetail)
    {
        bsPqcDetail.setUpdateTime(DateUtils.getNowDate());
        return bsPqcDetailMapper.updateBsPqcDetail(bsPqcDetail);
    }

    /**
     * 删除产品检验标准明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsPqcDetailByIds(String ids)
    {
        return bsPqcDetailMapper.deleteBsPqcDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品检验标准明细信息
     * 
     * @param Id 产品检验标准明细ID
     * @return 结果
     */
    @Override
    public int deleteBsPqcDetailById(String Id)
    {
        return bsPqcDetailMapper.deleteBsPqcDetailById(Id);
    }
}
