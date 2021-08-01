package com.saas.pssc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.DataScope;
import com.saas.common.core.domain.Ztree;
import com.saas.common.core.text.Convert;
import com.saas.common.utils.DateUtils;
import com.saas.pssc.domain.BsBom;
import com.saas.pssc.mapper.BsBomMapper;
import com.saas.pssc.service.IBsBomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * bom物料清单Service业务层处理
 * 
 * @author admin
 * @date 2021-08-01
 */
@Service
public class BsBomServiceImpl implements IBsBomService 
{
    @Autowired
    private BsBomMapper bsBomMapper;

    /**
     * 查询bom物料清单
     * 
     * @param id bom物料清单ID
     * @return bom物料清单
     */
    @Override
    public BsBom selectBsBomById(Long id)
    {
        return bsBomMapper.selectBsBomById(id);
    }

    /**
     * 查询bom物料清单列表
     * 
     * @param bsBom bom物料清单
     * @return bom物料清单
     */
    @Override
    @DataScope(userAlias = "su")
    public List<BsBom> selectBsBomList(BsBom bsBom)
    {
        return bsBomMapper.selectBsBomList(bsBom);
    }

    /**
     * 新增bom物料清单
     * 
     * @param bsBom bom物料清单
     * @return 结果
     */
    @Override
    public int insertBsBom(BsBom bsBom)
    {
        bsBom.setCreateTime(DateUtils.getNowDate());
        return bsBomMapper.insertBsBom(bsBom);
    }

    /**
     * 修改bom物料清单
     * 
     * @param bsBom bom物料清单
     * @return 结果
     */
    @Override
    public int updateBsBom(BsBom bsBom)
    {
        bsBom.setUpdateTime(DateUtils.getNowDate());
        return bsBomMapper.updateBsBom(bsBom);
    }

    /**
     * 批量失效bom物料清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBsBomByIds(String ids)
    {
        return bsBomMapper.updateBsBomByIds(Convert.toStrArray(ids));
    }

    /**
     * 失效bom物料清单信息
     * 
     * @param id bom物料清单ID
     * @return 结果
     */
    @Override
    public int deleteBsBomById(Long id)
    {
        return bsBomMapper.updateBsBomById(id);
    }

    /**
     * 查询bom物料清单树列表
     * 
     * @return 所有bom物料清单信息
     */
    @Override
    public List<Ztree> selectBsBomTree()
    {
        List<BsBom> bsBomList = bsBomMapper.selectBsBomList(new BsBom());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BsBom bsBom : bsBomList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(bsBom.getId());
            ztree.setpId(bsBom.getIdParent());
            ztree.setName(bsBom.getMname());
            ztree.setTitle(bsBom.getMname());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
