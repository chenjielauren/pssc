package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.BsPqcDetail;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.mapper.BsPqcMainMapper;
import com.saas.pssc.service.IBsPqcMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产品检验标准Service业务层处理
 * 
 * @author admin
 * @date 2021-07-15
 */
@Service
public class BsPqcMainServiceImpl implements IBsPqcMainService 
{
    @Autowired
    private BsPqcMainMapper bsPqcMainMapper;

    /**
     * 查询产品检验标准
     * 
     * @param Id 产品检验标准ID
     * @return 产品检验标准
     */
    @Override
    public BsPqcMain selectBsPqcMainById(String Id)
    {
        BsPqcMain bsPqcMain = bsPqcMainMapper.selectBsPqcMainById(Id);
        List<BsPqcDetail> sjDetailList = new ArrayList<BsPqcDetail>();//首检标准列表
        List<BsPqcDetail> zjDetailList = new ArrayList<BsPqcDetail>();//自检标准列表
        List<BsPqcDetail> xjDetailList = new ArrayList<BsPqcDetail>();//专检标准列表
        if(null!=bsPqcMain && !StringUtils.isEmpty(bsPqcMain.getBsPqcDetailList())){           
            for(BsPqcDetail bqd : bsPqcMain.getBsPqcDetailList()){
                if(Long.valueOf(bqd.getPtype()) == 0){
                    sjDetailList.add(bqd);
                }
                if(Long.valueOf(bqd.getPtype()) == 1){
                    zjDetailList.add(bqd);
                }
                if(Long.valueOf(bqd.getPtype()) == 2){
                    xjDetailList.add(bqd);
                }
            }
        }
        bsPqcMain.setSjDetailList(sjDetailList);
        bsPqcMain.setZjDetailList(zjDetailList);
        bsPqcMain.setXjDetailList(xjDetailList);
        return bsPqcMain;
    }

    /**
     * 查询产品检验标准列表
     * 
     * @param bsPqcMain 产品检验标准
     * @return 产品检验标准
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsPqcMain> selectBsPqcMainList(BsPqcMain bsPqcMain)
    {
        return bsPqcMainMapper.selectBsPqcMainList(bsPqcMain);
    }

    /**
     * 新增产品检验标准
     * 
     * @param bsPqcMain 产品检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBsPqcMain(BsPqcMain bsPqcMain)
    {
        bsPqcMain.setId(IdUtils.fastSimpleUUID());
        bsPqcMain.setCreateBy(ShiroUtils.getLoginName());
        bsPqcMain.setCreateTime(DateUtils.getNowDate());
        int rows = bsPqcMainMapper.insertBsPqcMain(bsPqcMain);
        insertBsPqcDetail(bsPqcMain);
        return rows;
    }

    /**
     * 修改产品检验标准
     * 
     * @param bsPqcMain 产品检验标准
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBsPqcMain(BsPqcMain bsPqcMain)
    {
        bsPqcMain.setUpdateBy(ShiroUtils.getLoginName());
        bsPqcMain.setUpdateTime(DateUtils.getNowDate());
        bsPqcMainMapper.deleteBsPqcDetailByMainId(bsPqcMain.getId());
        insertBsPqcDetail(bsPqcMain);
        return bsPqcMainMapper.updateBsPqcMain(bsPqcMain);
    }

    /**
     * 删除产品检验标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBsPqcMainByIds(String ids)
    {
        bsPqcMainMapper.deleteBsPqcDetailByMainIds(Convert.toStrArray(ids));
        return bsPqcMainMapper.updateBsPqcMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品检验标准信息
     * 
     * @param Id 产品检验标准ID
     * @return 结果
     */
    @Override
    public int deleteBsPqcMainById(String Id)
    {
        bsPqcMainMapper.deleteBsPqcDetailByMainId(Id);
        return bsPqcMainMapper.updateBsPqcMainById(Id);
    }

    /**
     * 新增产品检验标准明细信息
     * 
     * @param bsPqcMain 产品检验标准对象
     */
    public void insertBsPqcDetail(BsPqcMain bsPqcMain)
    {
        List<BsPqcDetail> bsPqcDetailList = new ArrayList<>();
        //首检列表
        if(!StringUtils.isEmpty(bsPqcMain.getSjDetailList())){
            for(BsPqcDetail bpd: bsPqcMain.getSjDetailList()){
                bpd.setPtype("0");
                bpd.setCreateBy(ShiroUtils.getLoginName());
            }
            bsPqcDetailList.addAll(bsPqcMain.getSjDetailList());
        }
        //自检列表
        if(!StringUtils.isEmpty(bsPqcMain.getZjDetailList())){
            for(BsPqcDetail bpd: bsPqcMain.getZjDetailList()){
                bpd.setPtype("1");
                bpd.setCreateBy(ShiroUtils.getLoginName());
            }
            bsPqcDetailList.addAll(bsPqcMain.getZjDetailList());
        }
        //巡检列表
        if(!StringUtils.isEmpty(bsPqcMain.getXjDetailList())){
            for(BsPqcDetail bpd: bsPqcMain.getXjDetailList()){
                bpd.setPtype("2");
                bpd.setCreateBy(ShiroUtils.getLoginName());
            }
            bsPqcDetailList.addAll(bsPqcMain.getXjDetailList());
        }
        String Id = bsPqcMain.getId();
        if (StringUtils.isNotNull(bsPqcDetailList))
        {
            List<BsPqcDetail> list = new ArrayList<BsPqcDetail>();
            for (BsPqcDetail bsPqcDetail : bsPqcDetailList)
            {
                bsPqcDetail.setId(IdUtils.fastSimpleUUID());
                bsPqcDetail.setMainId(Id);
                list.add(bsPqcDetail);
            }
            if (list.size() > 0)
            {
                bsPqcMainMapper.batchBsPqcDetail(list);
            }
        }
    }
}
