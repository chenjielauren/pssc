package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.BsCraftSopDetail;
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.mapper.BsCraftSopMainMapper;
import com.saas.pssc.service.IBsCraftSopMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工艺标准与CCPService业务层处理
 * 
 * @author admin
 * @date 2021-07-19
 */
@Service
public class BsCraftSopMainServiceImpl implements IBsCraftSopMainService 
{
    @Autowired
    private BsCraftSopMainMapper bsCraftSopMainMapper;

    /**
     * 查询工艺标准与CCP
     * 
     * @param id 工艺标准与CCPID
     * @return 工艺标准与CCP
     */
    @Override
    public BsCraftSopMain selectBsCraftSopMainById(Long id)
    {
        BsCraftSopMain bsSopMain = bsCraftSopMainMapper.selectBsCraftSopMainById(id);
        if(null!=bsSopMain && !StringUtils.isEmpty(bsSopMain.getBsCraftSopDetailList())){
            List<BsCraftSopDetail> techList = new ArrayList<BsCraftSopDetail>();//技术标准
            List<BsCraftSopDetail> prodList = new ArrayList<BsCraftSopDetail>();//工艺标准
            for(BsCraftSopDetail detail : bsSopMain.getBsCraftSopDetailList()){
                detail.setIsValid("1");
                detail.setCreateBy(ShiroUtils.getLoginName());
                detail.setCreateTime(DateUtils.getNowDate());
                detail.setUpdateBy(ShiroUtils.getLoginName());
                detail.setUpdateTime(DateUtils.getNowDate());
                if(Long.valueOf(detail.getPtype()) == 0){
                    techList.add(detail);
                }
                if(Long.valueOf(detail.getPtype()) == 1){
                    prodList.add(detail);
                }
            }
            bsSopMain.setBsSopTechDetailList(techList);
            bsSopMain.setBsSopProdDetailList(prodList);
        }
        return bsSopMain;
    }

    /**
     * 查询工艺标准与CCP列表
     * 
     * @param bsCraftSopMain 工艺标准与CCP
     * @return 工艺标准与CCP
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsCraftSopMain> selectBsCraftSopMainList(BsCraftSopMain bsCraftSopMain)
    {
        return bsCraftSopMainMapper.selectBsCraftSopMainList(bsCraftSopMain);
    }

    /**
     * 新增工艺标准与CCP
     * 
     * @param bsCraftSopMain 工艺标准与CCP
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBsCraftSopMain(BsCraftSopMain bsCraftSopMain)
    {
        bsCraftSopMain.setCreateTime(DateUtils.getNowDate());
        bsCraftSopMain.setUpdateTime(DateUtils.getNowDate());
        int rows = bsCraftSopMainMapper.insertBsCraftSopMain(bsCraftSopMain);
        insertBsCraftSopDetail(bsCraftSopMain);
        return rows;
    }

    /**
     * 修改工艺标准与CCP
     * 
     * @param bsCraftSopMain 工艺标准与CCP
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBsCraftSopMain(BsCraftSopMain bsCraftSopMain)
    {
        bsCraftSopMain.setUpdateTime(DateUtils.getNowDate());
        bsCraftSopMainMapper.deleteBsCraftSopDetailByMainId(bsCraftSopMain.getId());
        insertBsCraftSopDetail(bsCraftSopMain);
        return bsCraftSopMainMapper.updateBsCraftSopMain(bsCraftSopMain);
    }

    /**
     * 删除工艺标准与CCP对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBsCraftSopMainByIds(String ids)
    {
        bsCraftSopMainMapper.deleteBsCraftSopDetailByMainIds(Convert.toStrArray(ids));
        return bsCraftSopMainMapper.updateBsCraftSopMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工艺标准与CCP信息
     * 
     * @param id 工艺标准与CCPID
     * @return 结果
     */
    @Override
    public int deleteBsCraftSopMainById(Long id)
    {
        bsCraftSopMainMapper.deleteBsCraftSopDetailByMainId(id);
        return bsCraftSopMainMapper.updateBsCraftSopMainById(id);
    }

    /**
     * 新增工艺标准与CCP明细信息
     * 
     * @param bsCraftSopMain 工艺标准与CCP对象
     */
    public void insertBsCraftSopDetail(BsCraftSopMain bsCraftSopMain)
    {
        List<BsCraftSopDetail> bsCraftSopDetailList = new ArrayList<>();
        //技术标准
        if(!StringUtils.isEmpty(bsCraftSopMain.getBsSopTechDetailList())){
            for(BsCraftSopDetail detail: bsCraftSopMain.getBsSopTechDetailList()){
                detail.setPtype("0");
                detail.setCreateBy(ShiroUtils.getLoginName());
            }
            bsCraftSopDetailList.addAll(bsCraftSopMain.getBsSopTechDetailList());
        }
        //工艺标准
        if(!StringUtils.isEmpty(bsCraftSopMain.getBsSopProdDetailList())){
            for(BsCraftSopDetail detail: bsCraftSopMain.getBsSopProdDetailList()){
                detail.setPtype("1");
                detail.setCreateBy(ShiroUtils.getLoginName());
            }
            bsCraftSopDetailList.addAll(bsCraftSopMain.getBsSopProdDetailList());
        }
        Long id = bsCraftSopMain.getId();
        if (StringUtils.isNotNull(bsCraftSopDetailList))
        {
            List<BsCraftSopDetail> list = new ArrayList<BsCraftSopDetail>();
            for (BsCraftSopDetail bsCraftSopDetail : bsCraftSopDetailList)
            {
                bsCraftSopDetail.setMainId(id);
                list.add(bsCraftSopDetail);
            }
            if (list.size() > 0)
            {
                bsCraftSopMainMapper.batchBsCraftSopDetail(list);
            }
        }
    }
}
