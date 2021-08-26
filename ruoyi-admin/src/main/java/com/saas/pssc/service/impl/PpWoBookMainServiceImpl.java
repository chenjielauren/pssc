package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saas.common.annotation.DataScope;
import com.saas.common.constant.UserConstants;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.uuid.IdUtils;
import com.saas.pssc.domain.PpWoBookBom;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.mapper.PpWoBookMainMapper;
import com.saas.pssc.service.IPpWoBookMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工单记录Service业务层处理
 * 
 * @author admin
 * @date 2021-08-03
 */
@Service
public class PpWoBookMainServiceImpl implements IPpWoBookMainService {
    @Autowired
    private PpWoBookMainMapper ppWoBookMainMapper;

    /**
     * 查询工单记录
     * 
     * @param id 工单记录ID
     * @return 工单记录
     */
    @Override
    public PpWoBookMain selectPpWoBookMainById(String id) {
        return ppWoBookMainMapper.selectPpWoBookMainById(id);
    }

    /**
     * 查询工单记录列表
     * 
     * @param ppWoBookMain 工单记录
     * @return 工单记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<PpWoBookMain> selectPpWoBookMainList(PpWoBookMain ppWoBookMain) {
        return ppWoBookMainMapper.selectPpWoBookMainList(ppWoBookMain);
    }

    /**
     * 新增工单记录
     * 
     * @param ppWoBookMain 工单记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPpWoBookMain(PpWoBookMain ppWoBookMain) {
        ppWoBookMain.setId(IdUtils.fastSimpleUUID());
        ppWoBookMain.setCreateTime(DateUtils.getNowDate());
        int rows = ppWoBookMainMapper.insertPpWoBookMain(ppWoBookMain);
        insertPpWoBookDetailAndBom(ppWoBookMain);
        return rows;
    }

    /**
     * 修改工单记录
     * 
     * @param ppWoBookMain 工单记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePpWoBookMain(PpWoBookMain ppWoBookMain) {
        ppWoBookMain.setUpdateTime(DateUtils.getNowDate());
        ppWoBookMainMapper.deletePpWoBookDetailByMainId(ppWoBookMain.getId());
        ppWoBookMainMapper.deletePpWoBookBomByMainId(ppWoBookMain.getId());
        insertPpWoBookDetailAndBom(ppWoBookMain);
        return ppWoBookMainMapper.updatePpWoBookMain(ppWoBookMain);
    }

    /**
     * 删除工单记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePpWoBookMainByIds(String ids) {
        ppWoBookMainMapper.deletePpWoBookDetailByMainIds(Convert.toStrArray(ids));
        ppWoBookMainMapper.deletePpWoBookBomByMainIds(Convert.toStrArray(ids));
        return ppWoBookMainMapper.updatePpWoBookMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工单记录信息
     * 
     * @param id 工单记录ID
     * @return 结果
     */
    @Override
    public int deletePpWoBookMainById(String id) {
        ppWoBookMainMapper.deletePpWoBookDetailByMainId(id);
        return ppWoBookMainMapper.updatePpWoBookMainById(id);
    }

    /**
     * 新增工单制造信息
     * 
     * @param ppWoBookMain 工单记录对象
     */
    public void insertPpWoBookDetailAndBom(PpWoBookMain ppWoBookMain) {
        // 工单制造信息
        List<PpWoBookDetail> ppWoBookDetailList = ppWoBookMain.getPpWoBookDetailList();
        String id = ppWoBookMain.getId();
        if (StringUtils.isNotNull(ppWoBookDetailList)) {
            List<PpWoBookDetail> list = new ArrayList<PpWoBookDetail>();
            for (PpWoBookDetail ppWoBookDetail : ppWoBookDetailList) {
                ppWoBookDetail.setId(IdUtils.fastSimpleUUID());
                ppWoBookDetail.setMainId(id);
                ppWoBookDetail.setCreateBy(ShiroUtils.getLoginName());
                ppWoBookDetail.setUpdateBy(ShiroUtils.getLoginName());
                list.add(ppWoBookDetail);
            }
            if (list.size() > 0) {
                ppWoBookMainMapper.batchPpWoBookDetail(list);
            }
        }
        // 工单BOM信息
        List<PpWoBookBom> ppWoBookBomList = ppWoBookMain.getPpWoBookBomList();
        if (StringUtils.isNotNull(ppWoBookBomList)) {
            List<PpWoBookBom> list = new ArrayList<PpWoBookBom>();
            for (PpWoBookBom ppWoBookBom : ppWoBookBomList) {
                ppWoBookBom.setId(IdUtils.fastSimpleUUID());
                ppWoBookBom.setMainId(id);
                ppWoBookBom.setCreateBy(ShiroUtils.getLoginName());
                ppWoBookBom.setUpdateBy(ShiroUtils.getLoginName());
                list.add(ppWoBookBom);
            }
            if (list.size() > 0) {
                ppWoBookMainMapper.batchPpWoBookBom(list);
            }
        }
    }

    @Override
    public PpWoBookMain selectPpWoBookMainByMap(Map<String, Object> paramMap) {
        PpWoBookMain ppWoBookMain = new PpWoBookMain();
        List<PpWoBookMain> list = ppWoBookMainMapper.selectPpWoBookMainByMap(paramMap);
        if(StringUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return ppWoBookMain;
    }

    @Override
    public String checkPcodeUnique(PpWoBookMain ppWoBookMain) {
        if(StringUtils.isNotEmpty(ppWoBookMain.getPcode()) && StringUtils.isNotEmpty(ppWoBookMain.getPname())){
            List<PpWoBookMain> list = ppWoBookMainMapper.checkPcodeUnique(ppWoBookMain);
            if (StringUtils.isNotEmpty(list))
            {
                return UserConstants.DEPT_NAME_NOT_UNIQUE;
            }
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public PpWoBookMain selectPpWoBookDetail(PpWoBookMain ppWoBookMain) {
        List<PpWoBookMain> list = ppWoBookMainMapper.selectPpWoBookDetail(ppWoBookMain);
        if(StringUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return ppWoBookMain;
    }
}
