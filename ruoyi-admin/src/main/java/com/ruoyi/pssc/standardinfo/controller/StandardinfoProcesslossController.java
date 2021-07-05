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
import com.ruoyi.pssc.standardinfo.domain.StandardinfoProcessloss;
import com.ruoyi.pssc.standardinfo.service.IStandardinfoProcesslossService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序损耗标准Controller
 * 
 * @author lauren
 * @date 2021-07-05
 */
@Controller
@RequestMapping("/standardinfo/processloss")
public class StandardinfoProcesslossController extends BaseController
{
    private String prefix = "standardinfo/processloss";

    @Autowired
    private IStandardinfoProcesslossService standardinfoProcesslossService;

    @RequiresPermissions("standardinfo:processloss:view")
    @GetMapping()
    public String processloss()
    {
        return prefix + "/processloss";
    }

    /**
     * 查询工序损耗标准列表
     */
    @RequiresPermissions("standardinfo:processloss:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StandardinfoProcessloss standardinfoProcessloss)
    {
        startPage();
        List<StandardinfoProcessloss> list = standardinfoProcesslossService.selectStandardinfoProcesslossList(standardinfoProcessloss);
        return getDataTable(list);
    }

    /**
     * 导出工序损耗标准列表
     */
    @RequiresPermissions("standardinfo:processloss:export")
    @Log(title = "工序损耗标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StandardinfoProcessloss standardinfoProcessloss)
    {
        List<StandardinfoProcessloss> list = standardinfoProcesslossService.selectStandardinfoProcesslossList(standardinfoProcessloss);
        ExcelUtil<StandardinfoProcessloss> util = new ExcelUtil<StandardinfoProcessloss>(StandardinfoProcessloss.class);
        return util.exportExcel(list, "工序损耗标准数据");
    }

    /**
     * 新增工序损耗标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工序损耗标准
     */
    @RequiresPermissions("standardinfo:processloss:add")
    @Log(title = "工序损耗标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StandardinfoProcessloss standardinfoProcessloss)
    {
        return toAjax(standardinfoProcesslossService.insertStandardinfoProcessloss(standardinfoProcessloss));
    }

    /**
     * 修改工序损耗标准
     */
    @GetMapping("/edit/{processlossId}")
    public String edit(@PathVariable("processlossId") Long processlossId, ModelMap mmap)
    {
        StandardinfoProcessloss standardinfoProcessloss = standardinfoProcesslossService.selectStandardinfoProcesslossById(processlossId);
        mmap.put("standardinfoProcessloss", standardinfoProcessloss);
        return prefix + "/edit";
    }

    /**
     * 修改保存工序损耗标准
     */
    @RequiresPermissions("standardinfo:processloss:edit")
    @Log(title = "工序损耗标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StandardinfoProcessloss standardinfoProcessloss)
    {
        return toAjax(standardinfoProcesslossService.updateStandardinfoProcessloss(standardinfoProcessloss));
    }

    /**
     * 删除工序损耗标准
     */
    @RequiresPermissions("standardinfo:processloss:remove")
    @Log(title = "工序损耗标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(standardinfoProcesslossService.deleteStandardinfoProcesslossByIds(ids));
    }
}
