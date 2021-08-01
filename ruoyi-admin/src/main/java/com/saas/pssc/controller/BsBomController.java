package com.saas.pssc.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.saas.common.annotation.Log;
import com.saas.common.enums.BusinessType;
import com.saas.pssc.domain.BsBom;
import com.saas.pssc.service.IBsBomService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.utils.StringUtils;
import com.saas.common.core.domain.Ztree;

/**
 * bom物料清单Controller
 * 
 * @author admin
 * @date 2021-08-01
 */
@Controller
@RequestMapping("/bs/bom")
public class BsBomController extends BaseController
{
    private String prefix = "bs/bom";

    @Autowired
    private IBsBomService bsBomService;

    @RequiresPermissions("bs:bom:view")
    @GetMapping()
    public String bom()
    {
        return prefix + "/bom";
    }

    /**
     * 查询bom物料清单树列表
     */
    @RequiresPermissions("bs:bom:list")
    @PostMapping("/list")
    @ResponseBody
    public List<BsBom> list(BsBom bsBom)
    {
        List<BsBom> list = bsBomService.selectBsBomList(bsBom);
        return list;
    }

    /**
     * 导出bom物料清单列表
     */
    @RequiresPermissions("bs:bom:export")
    @Log(title = "bom物料清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsBom bsBom)
    {
        List<BsBom> list = bsBomService.selectBsBomList(bsBom);
        ExcelUtil<BsBom> util = new ExcelUtil<BsBom>(BsBom.class);
        return util.exportExcel(list, "bom物料清单数据");
    }

    /**
     * 新增bom物料清单
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bsBom", bsBomService.selectBsBomById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存bom物料清单
     */
    @RequiresPermissions("bs:bom:add")
    @Log(title = "bom物料清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsBom bsBom)
    {
        return toAjax(bsBomService.insertBsBom(bsBom));
    }

    /**
     * 修改bom物料清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsBom bsBom = bsBomService.selectBsBomById(id);
        mmap.put("bsBom", bsBom);
        return prefix + "/edit";
    }

    /**
     * 修改保存bom物料清单
     */
    @RequiresPermissions("bs:bom:edit")
    @Log(title = "bom物料清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsBom bsBom)
    {
        return toAjax(bsBomService.updateBsBom(bsBom));
    }

    /**
     * 删除
     */
    @RequiresPermissions("bs:bom:remove")
    @Log(title = "bom物料清单", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(bsBomService.deleteBsBomById(id));
    }

    /**
     * 选择bom物料清单树
     */
    @GetMapping(value = { "/selectBomTree/{id}", "/selectBomTree/" })
    public String selectBomTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bsBom", bsBomService.selectBsBomById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载bom物料清单树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = bsBomService.selectBsBomTree();
        return ztrees;
    }
}
