package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.mapper.PpWoBookMainMapper;
import com.saas.pssc.service.IPpWoBookMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工单报工记录Service业务层处理
 * 
 * @author admin
 * @date 2021-07-24
 */
@Service
public class PpWoBookMainServiceImpl implements IPpWoBookMainService 
{
    @Autowired
    private PpWoBookMainMapper ppWoBookMainMapper;

    /**
     * 查询工单报工记录
     * 
     * @param id 工单报工记录ID
     * @return 工单报工记录
     */
    @Override
    public PpWoBookMain selectPpWoBookMainById(Long id)
    {
        PpWoBookMain bookMain = ppWoBookMainMapper.selectPpWoBookMainById(id);
        List<PpWoBookDetail> mdetailList = new ArrayList<PpWoBookDetail>();//用料信息
        List<PpWoBookDetail> craftdetailList = new ArrayList<PpWoBookDetail>();//工艺信息
        if(null!=bookMain && !StringUtils.isEmpty(bookMain.getPpWoBookDetailList())){
            for(PpWoBookDetail detail : bookMain.getPpWoBookDetailList()){
                if(Long.valueOf(detail.getMtype()) == 0){
                    mdetailList.add(detail);
                }
                if(Long.valueOf(detail.getMtype()) == 1){
                    craftdetailList.add(detail);
                }
            }
        }
        bookMain.setMdetailList(mdetailList);
        bookMain.setCraftdetailList(craftdetailList);
        return bookMain;
    }

    /**
     * 查询工单报工记录列表
     * 
     * @param ppWoBookMain 工单报工记录
     * @return 工单报工记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<PpWoBookMain> selectPpWoBookMainList(PpWoBookMain ppWoBookMain)
    {
        return ppWoBookMainMapper.selectPpWoBookMainList(ppWoBookMain);
    }

    /**
     * 新增工单报工记录
     * 
     * @param ppWoBookMain 工单报工记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPpWoBookMain(PpWoBookMain ppWoBookMain)
    {
        ppWoBookMain.setCreateTime(DateUtils.getNowDate());
        ppWoBookMain.setUpdateTime(DateUtils.getNowDate());
        int rows = ppWoBookMainMapper.insertPpWoBookMain(ppWoBookMain);
        insertPpWoBookDetail(ppWoBookMain);
        return rows;
    }

    /**
     * 修改工单报工记录
     * 
     * @param ppWoBookMain 工单报工记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePpWoBookMain(PpWoBookMain ppWoBookMain)
    {
        ppWoBookMain.setUpdateTime(DateUtils.getNowDate());
        ppWoBookMainMapper.deletePpWoBookDetailByMainId(ppWoBookMain.getId());
        insertPpWoBookDetail(ppWoBookMain);
        return ppWoBookMainMapper.updatePpWoBookMain(ppWoBookMain);
    }

    /**
     * 删除工单报工记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePpWoBookMainByIds(String ids)
    {
        ppWoBookMainMapper.deletePpWoBookDetailByMainIds(Convert.toStrArray(ids));
        return ppWoBookMainMapper.updatePpWoBookMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单报工记录信息
     * 
     * @param id 工单报工记录ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookMainById(Long id)
    {
        ppWoBookMainMapper.deletePpWoBookDetailByMainId(id);
        return ppWoBookMainMapper.updatePpWoBookMainById(id);
    }

    /**
     * 新增工单报工记录明细信息
     * 
     * @param ppWoBookMain 工单报工记录对象
     */
    public void insertPpWoBookDetail(PpWoBookMain ppWoBookMain)
    {
        List<PpWoBookDetail> ppWoBookDetailList =  new ArrayList<>();
        //用料信息
        if(!StringUtils.isEmpty(ppWoBookMain.getMdetailList())){
            for(PpWoBookDetail detail : ppWoBookMain.getMdetailList()){
                detail.setMtype("0");
                detail.setCreateBy(ShiroUtils.getLoginName());
                detail.setUpdateBy(ShiroUtils.getLoginName());
            }
            ppWoBookDetailList.addAll(ppWoBookMain.getMdetailList());
        }
        //工艺信息
        if(!StringUtils.isEmpty(ppWoBookMain.getCraftdetailList())){
            for(PpWoBookDetail detail : ppWoBookMain.getCraftdetailList()){
                detail.setMtype("1");
                detail.setCreateBy(ShiroUtils.getLoginName());
                detail.setUpdateBy(ShiroUtils.getLoginName());
            }
            ppWoBookDetailList.addAll(ppWoBookMain.getCraftdetailList());
        }
        Long id = ppWoBookMain.getId();
        if (StringUtils.isNotNull(ppWoBookDetailList))
        {
            List<PpWoBookDetail> list = new ArrayList<PpWoBookDetail>();
            for (PpWoBookDetail ppWoBookDetail : ppWoBookDetailList)
            {
                ppWoBookDetail.setMainId(id);
                list.add(ppWoBookDetail);
            }
            if (list.size() > 0)
            {
                ppWoBookMainMapper.batchPpWoBookDetail(list);
            }
        }
    }
}
