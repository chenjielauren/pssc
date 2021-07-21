package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.service.IBsMcnMainService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 4M变更单Controller
 * 
 * @author admin
 * @date 2021-07-19
 */
@Controller
@RequestMapping("/bs/mcnmain")
public class BsMcnMainController extends BaseController
{
    private String prefix = "bs/mcnmain";

    @Autowired
    private IBsMcnMainService bsMcnMainService;

    @RequiresPermissions("bs:mcnmain:view")
    @GetMapping()
    public String mcnmain()
    {
        return prefix + "/mcnmain";
    }

    /**
     * 查询4M变更单列表
     */
    @RequiresPermissions("bs:mcnmain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsMcnMain bsMcnMain)
    {
        startPage();
        List<BsMcnMain> list = bsMcnMainService.selectBsMcnMainList(bsMcnMain);
        return getDataTable(list);
    }

    /**
     * 导出4M变更单列表
     */
    @RequiresPermissions("bs:mcnmain:export")
    @Log(title = "4M变更单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsMcnMain bsMcnMain)
    {
        List<BsMcnMain> list = bsMcnMainService.selectBsMcnMainList(bsMcnMain);
        ExcelUtil<BsMcnMain> util = new ExcelUtil<BsMcnMain>(BsMcnMain.class);
        return util.exportExcel(list, "4M变更单数据");
    }

    /**
     * 新增4M变更单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存4M变更单
     */
    @RequiresPermissions("bs:mcnmain:add")
    @Log(title = "4M变更单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsMcnMain bsMcnMain)
    {
        return toAjax(bsMcnMainService.insertBsMcnMain(bsMcnMain));
    }

    /**
     * 修改4M变更单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsMcnMain bsMcnMain = bsMcnMainService.selectBsMcnMainById(id);
        mmap.put("bsMcnMain", bsMcnMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存4M变更单
     */
    @RequiresPermissions("bs:mcnmain:edit")
    @Log(title = "4M变更单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsMcnMain bsMcnMain)
    {
        return toAjax(bsMcnMainService.updateBsMcnMain(bsMcnMain));
    }

    /**
     * 删除4M变更单
     */
    @RequiresPermissions("bs:mcnmain:remove")
    @Log(title = "4M变更单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsMcnMainService.deleteBsMcnMainByIds(ids));
    }

    @GetMapping(value = {"/toUpload/{rowIndex}"})
    public String toUpload(@PathVariable(value="rowIndex") Long rowIndex,ModelMap mmap)
    {
        mmap.put("rowIndex", rowIndex);
        return prefix + "/upload";
    }
}
