package com.kl.pssc.controller;

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
import com.kl.common.annotation.Log;
import com.kl.common.enums.BusinessType;
import com.kl.pssc.domain.StandardinfoProductInspection;
import com.kl.pssc.service.IStandardinfoProductInspectionService;
import com.kl.common.core.controller.BaseController;
import com.kl.common.core.domain.AjaxResult;
import com.kl.common.utils.poi.ExcelUtil;
import com.kl.common.core.page.TableDataInfo;

/**
 * 产品检验标准明细信息Controller
 * 
 * @author admin
 * @date 2021-07-14
 */
@Controller
@RequestMapping("/standardinfo/productinspection")
public class StandardinfoProductInspectionController extends BaseController
{
    private String prefix = "standardinfo/productinspection";

    @Autowired
    private IStandardinfoProductInspectionService standardinfoProductInspectionService;

    @RequiresPermissions("standardinfo:productinspection:view")
    @GetMapping()
    public String productinspection()
    {
        return prefix + "/productinspection";
    }

    /**
     * 查询产品检验标准明细信息列表
     */
    @RequiresPermissions("standardinfo:productinspection:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StandardinfoProductInspection standardinfoProductInspection)
    {
        startPage();
        List<StandardinfoProductInspection> list = standardinfoProductInspectionService.selectStandardinfoProductInspectionList(standardinfoProductInspection);
        return getDataTable(list);
    }

    /**
     * 导出产品检验标准明细信息列表
     */
    @RequiresPermissions("standardinfo:productinspection:export")
    @Log(title = "产品检验标准明细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StandardinfoProductInspection standardinfoProductInspection)
    {
        List<StandardinfoProductInspection> list = standardinfoProductInspectionService.selectStandardinfoProductInspectionList(standardinfoProductInspection);
        ExcelUtil<StandardinfoProductInspection> util = new ExcelUtil<StandardinfoProductInspection>(StandardinfoProductInspection.class);
        return util.exportExcel(list, "产品检验标准明细信息数据");
    }

    /**
     * 新增产品检验标准明细信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品检验标准明细信息
     */
    @RequiresPermissions("standardinfo:productinspection:add")
    @Log(title = "产品检验标准明细信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StandardinfoProductInspection standardinfoProductInspection)
    {
        return toAjax(standardinfoProductInspectionService.insertStandardinfoProductInspection(standardinfoProductInspection));
    }

    /**
     * 修改产品检验标准明细信息
     */
    @GetMapping("/edit/{inspectionId}")
    public String edit(@PathVariable("inspectionId") Long inspectionId, ModelMap mmap)
    {
        StandardinfoProductInspection standardinfoProductInspection = standardinfoProductInspectionService.selectStandardinfoProductInspectionById(inspectionId);
        mmap.put("standardinfoProductInspection", standardinfoProductInspection);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品检验标准明细信息
     */
    @RequiresPermissions("standardinfo:productinspection:edit")
    @Log(title = "产品检验标准明细信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StandardinfoProductInspection standardinfoProductInspection)
    {
        return toAjax(standardinfoProductInspectionService.updateStandardinfoProductInspection(standardinfoProductInspection));
    }

    /**
     * 删除产品检验标准明细信息
     */
    @RequiresPermissions("standardinfo:productinspection:remove")
    @Log(title = "产品检验标准明细信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(standardinfoProductInspectionService.deleteStandardinfoProductInspectionByIds(ids));
    }
}
