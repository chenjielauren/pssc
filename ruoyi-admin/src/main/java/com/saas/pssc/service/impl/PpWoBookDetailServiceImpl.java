package com.saas.pssc.service.impl;

import java.util.List;
import java.util.Map;

import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.mapper.PpWoBookDetailMapper;
import com.saas.pssc.service.IPpWoBookDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工单制造信息Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class PpWoBookDetailServiceImpl implements IPpWoBookDetailService {
    @Autowired
    private PpWoBookDetailMapper ppWoBookDetailMapper;

    /**
     * 查询工单制造信息
     * 
     * @param id 工单制造信息ID
     * @return 工单制造信息
     */
    @Override
    public PpWoBookDetail selectPpWoBookDetailById(String id) {
        return ppWoBookDetailMapper.selectPpWoBookDetailById(id);
    }

    /**
     * 查询工单制造信息列表
     * 
     * @param ppWoBookDetail 工单制造信息
     * @return 工单制造信息
     */
    @Override
    public List<PpWoBookDetail> selectPpWoBookDetailList(PpWoBookDetail ppWoBookDetail) {
        return ppWoBookDetailMapper.selectPpWoBookDetailList(ppWoBookDetail);
    }

    /**
     * 新增工单制造信息
     * 
     * @param ppWoBookDetail 工单制造信息
     * @return 结果
     */
    @Override
    public int insertPpWoBookDetail(PpWoBookDetail ppWoBookDetail) {
        ppWoBookDetail.setCreateTime(DateUtils.getNowDate());
        return ppWoBookDetailMapper.insertPpWoBookDetail(ppWoBookDetail);
    }

    /**
     * 修改工单制造信息
     * 
     * @param ppWoBookDetail 工单制造信息
     * @return 结果
     */
    @Override
    public int updatePpWoBookDetail(PpWoBookDetail ppWoBookDetail) {
        ppWoBookDetail.setUpdateTime(DateUtils.getNowDate());
        return ppWoBookDetailMapper.updatePpWoBookDetail(ppWoBookDetail);
    }

    /**
     * 删除工单制造信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookDetailByIds(String ids) {
        return ppWoBookDetailMapper.deletePpWoBookDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单制造信息信息
     * 
     * @param id 工单制造信息ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookDetailById(String id) {
        return ppWoBookDetailMapper.deletePpWoBookDetailById(id);
    }

    @Override
    public List<PpWoBookDetail> loadLineChart(String dcode) {
        return ppWoBookDetailMapper.loadLineChart(dcode);
    }

    @Override
    public PpWoBookDetail selectPpWoBookDetailByMap(Map<String, Object> paramMap) {
        List <PpWoBookDetail> list = ppWoBookDetailMapper.selectPpWoBookDetailByMap(paramMap);
        PpWoBookDetail ppWoBookDetail = new PpWoBookDetail();
        if(StringUtils.isNotEmpty(list)){
            ppWoBookDetail = list.get(0);
        }
        return ppWoBookDetail;
    }
}
