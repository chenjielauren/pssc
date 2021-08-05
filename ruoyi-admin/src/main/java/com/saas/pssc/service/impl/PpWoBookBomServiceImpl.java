package com.saas.pssc.service.impl;

import java.util.List;
import com.saas.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saas.pssc.mapper.PpWoBookBomMapper;
import com.saas.pssc.domain.PpWoBookBom;
import com.saas.pssc.service.IPpWoBookBomService;
import com.saas.common.core.text.Convert;

/**
 * 工单BOM信息Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class PpWoBookBomServiceImpl implements IPpWoBookBomService 
{
    @Autowired
    private PpWoBookBomMapper ppWoBookBomMapper;

    /**
     * 查询工单BOM信息
     * 
     * @param id 工单BOM信息ID
     * @return 工单BOM信息
     */
    @Override
    public PpWoBookBom selectPpWoBookBomById(String id)
    {
        return ppWoBookBomMapper.selectPpWoBookBomById(id);
    }

    /**
     * 查询工单BOM信息列表
     * 
     * @param ppWoBookBom 工单BOM信息
     * @return 工单BOM信息
     */
    @Override
    public List<PpWoBookBom> selectPpWoBookBomList(PpWoBookBom ppWoBookBom)
    {
        return ppWoBookBomMapper.selectPpWoBookBomList(ppWoBookBom);
    }

    /**
     * 新增工单BOM信息
     * 
     * @param ppWoBookBom 工单BOM信息
     * @return 结果
     */
    @Override
    public int insertPpWoBookBom(PpWoBookBom ppWoBookBom)
    {
        ppWoBookBom.setCreateTime(DateUtils.getNowDate());
        return ppWoBookBomMapper.insertPpWoBookBom(ppWoBookBom);
    }

    /**
     * 修改工单BOM信息
     * 
     * @param ppWoBookBom 工单BOM信息
     * @return 结果
     */
    @Override
    public int updatePpWoBookBom(PpWoBookBom ppWoBookBom)
    {
        ppWoBookBom.setUpdateTime(DateUtils.getNowDate());
        return ppWoBookBomMapper.updatePpWoBookBom(ppWoBookBom);
    }

    /**
     * 删除工单BOM信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookBomByIds(String ids)
    {
        return ppWoBookBomMapper.deletePpWoBookBomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单BOM信息信息
     * 
     * @param id 工单BOM信息ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookBomById(String id)
    {
        return ppWoBookBomMapper.deletePpWoBookBomById(id);
    }
}
