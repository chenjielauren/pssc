package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.BsMcnDetail;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.mapper.BsMcnMainMapper;
import com.saas.pssc.service.IBsMcnMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 4M变更单Service业务层处理
 * 
 * @author admin
 * @date 2021-07-19
 */
@Service
public class BsMcnMainServiceImpl implements IBsMcnMainService {
    @Autowired
    private BsMcnMainMapper bsMcnMainMapper;

    /**
     * 查询4M变更单
     * 
     * @param id 4M变更单ID
     * @return 4M变更单
     */
    @Override
    public BsMcnMain selectBsMcnMainById(String id) {
        return bsMcnMainMapper.selectBsMcnMainById(id);
    }

    /**
     * 查询4M变更单列表
     * 
     * @param bsMcnMain 4M变更单
     * @return 4M变更单
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsMcnMain> selectBsMcnMainList(BsMcnMain bsMcnMain) {
        return bsMcnMainMapper.selectBsMcnMainList(bsMcnMain);
    }

    /**
     * 新增4M变更单
     * 
     * @param bsMcnMain 4M变更单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBsMcnMain(BsMcnMain bsMcnMain) {
        bsMcnMain.setId(IdUtils.fastSimpleUUID());
        bsMcnMain.setCreateBy(ShiroUtils.getLoginName());
        bsMcnMain.setCreateTime(DateUtils.getNowDate());
        bsMcnMain.setUpdateTime(DateUtils.getNowDate());
        int rows = bsMcnMainMapper.insertBsMcnMain(bsMcnMain);
        insertBsMcnDetail(bsMcnMain);
        return rows;
    }

    /**
     * 修改4M变更单
     * 
     * @param bsMcnMain 4M变更单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBsMcnMain(BsMcnMain bsMcnMain) {
        bsMcnMain.setUpdateTime(DateUtils.getNowDate());
        bsMcnMainMapper.deleteBsMcnDetailByMainId(bsMcnMain.getId());
        insertBsMcnDetail(bsMcnMain);
        return bsMcnMainMapper.updateBsMcnMain(bsMcnMain);
    }

    /**
     * 删除4M变更单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBsMcnMainByIds(String ids) {
        bsMcnMainMapper.deleteBsMcnDetailByMainIds(Convert.toStrArray(ids));
        return bsMcnMainMapper.updateBsMcnMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除4M变更单信息
     * 
     * @param id 4M变更单ID
     * @return 结果
     */
    @Override
    public int deleteBsMcnMainById(String id) {
        bsMcnMainMapper.deleteBsMcnDetailByMainId(id);
        return bsMcnMainMapper.updateBsMcnMainById(id);
    }

    /**
     * 新增4M变更单明细信息
     * 
     * @param bsMcnMain 4M变更单对象
     */
    public void insertBsMcnDetail(BsMcnMain bsMcnMain) {
        List<BsMcnDetail> bsMcnDetailList = bsMcnMain.getBsMcnDetailList();
        String id = bsMcnMain.getId();
        if (StringUtils.isNotNull(bsMcnDetailList)) {
            List<BsMcnDetail> list = new ArrayList<BsMcnDetail>();
            for (BsMcnDetail bsMcnDetail : bsMcnDetailList) {
                bsMcnDetail.setId(IdUtils.fastSimpleUUID());
                bsMcnDetail.setCreateBy(ShiroUtils.getLoginName());
                bsMcnDetail.setCreateTime(DateUtils.getNowDate());
                bsMcnDetail.setUpdateBy(ShiroUtils.getLoginName());
                bsMcnDetail.setUpdateTime(DateUtils.getNowDate());
                bsMcnDetail.setMainId(id);
                list.add(bsMcnDetail);
            }
            if (list.size() > 0) {
                bsMcnMainMapper.batchBsMcnDetail(list);
            }
        }
    }

    @Override
    public BsMcnMain selectBsMcnMainByMap(Map<String, Object> paramMap) {
        BsMcnMain bsMcnMain = new BsMcnMain();
        List<BsMcnMain> list = bsMcnMainMapper.selectBsMcnMainByMap(paramMap);
        if(StringUtils.isNotEmpty(list)){
            bsMcnMain = list.get(0);
        }
        return bsMcnMain;
    }
}
