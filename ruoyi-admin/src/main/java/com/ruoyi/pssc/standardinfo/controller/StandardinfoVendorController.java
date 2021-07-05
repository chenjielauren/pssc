package com.ruoyi.pssc.standardinfo.controller;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.pssc.standardinfo.domain.StandardinfoVendor;
import com.ruoyi.pssc.standardinfo.service.IStandardinfoVendorService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合格供方名录Controller
 * 
 * @author lauren
 * @date 2021-07-05
 */
@Controller
@RequestMapping("/standardinfo/vendor")
public class StandardinfoVendorController extends BaseController
{
    private String prefix = "standardinfo/vendor";

    @Autowired
    private IStandardinfoVendorService standardinfoVendorService;

    @RequiresPermissions("standardinfo:vendor:view")
    @GetMapping()
    public String vendor()
    {
        return prefix + "/vendor";
    }

    /**
     * 查询合格供方名录列表
     */
    @RequiresPermissions("standardinfo:vendor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StandardinfoVendor standardinfoVendor)
    {
        startPage();
        List<StandardinfoVendor> list = standardinfoVendorService.selectStandardinfoVendorList(standardinfoVendor);
        return getDataTable(list);
    }

    /**
     * 导出合格供方名录列表
     */
    @RequiresPermissions("standardinfo:vendor:export")
    @Log(title = "合格供方名录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StandardinfoVendor standardinfoVendor)
    {
        List<StandardinfoVendor> list = standardinfoVendorService.selectStandardinfoVendorList(standardinfoVendor);
        ExcelUtil<StandardinfoVendor> util = new ExcelUtil<StandardinfoVendor>(StandardinfoVendor.class);
        return util.exportExcel(list, "合格供方名录数据");
    }

    /**
     * 新增合格供方名录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合格供方名录
     */
    @RequiresPermissions("standardinfo:vendor:add")
    @Log(title = "合格供方名录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StandardinfoVendor standardinfoVendor)
    {
        return toAjax(standardinfoVendorService.insertStandardinfoVendor(standardinfoVendor));
    }

    /**
     * 修改合格供方名录
     */
    @GetMapping("/edit/{vendorId}")
    public String edit(@PathVariable("vendorId") Long vendorId, ModelMap mmap)
    {
        StandardinfoVendor standardinfoVendor = standardinfoVendorService.selectStandardinfoVendorById(vendorId);
        mmap.put("standardinfoVendor", standardinfoVendor);
        return prefix + "/edit";
    }

    /**
     * 修改保存合格供方名录
     */
    @RequiresPermissions("standardinfo:vendor:edit")
    @Log(title = "合格供方名录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StandardinfoVendor standardinfoVendor)
    {
        return toAjax(standardinfoVendorService.updateStandardinfoVendor(standardinfoVendor));
    }

    /**
     * 删除合格供方名录
     */
    @RequiresPermissions("standardinfo:vendor:remove")
    @Log(title = "合格供方名录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(standardinfoVendorService.deleteStandardinfoVendorByIds(ids));
    }
}
