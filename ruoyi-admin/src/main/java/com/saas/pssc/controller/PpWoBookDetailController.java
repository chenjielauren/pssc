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
import org.springframework.web.multipart.MultipartFile;

import com.saas.common.annotation.Log;
import com.saas.common.enums.BusinessType;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.service.IPpWoBookDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 工单报工记录明细Controller
 * 
 * @author admin
 * @date 2021-07-24
 */
@Controller
@RequestMapping("/pp/wobookdetail")
public class PpWoBookDetailController extends BaseController
{
    private String prefix = "pp/wobookdetail";

    @Autowired
    private IPpWoBookDetailService ppWoBookDetailService;

    @RequiresPermissions("pp:wobookdetail:view")
    @GetMapping()
    public String wobookdetail()
    {
        return prefix + "/wobookdetail";
    }

    /**
     * 查询工单报工记录明细列表
     */
    @RequiresPermissions("pp:wobookdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PpWoBookDetail ppWoBookDetail)
    {
        startPage();
        List<PpWoBookDetail> list = ppWoBookDetailService.selectPpWoBookDetailList(ppWoBookDetail);
        return getDataTable(list);
    }

    /**
     * 导出工单报工记录明细列表
     */
    @RequiresPermissions("pp:wobookdetail:export")
    @Log(title = "工单报工记录明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PpWoBookDetail ppWoBookDetail)
    {
        List<PpWoBookDetail> list = ppWoBookDetailService.selectPpWoBookDetailList(ppWoBookDetail);
        ExcelUtil<PpWoBookDetail> util = new ExcelUtil<PpWoBookDetail>(PpWoBookDetail.class);
        return util.exportExcel(list, "工单报工记录明细数据");
    }

    /**
     * 新增工单报工记录明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工单报工记录明细
     */
    @RequiresPermissions("pp:wobookdetail:add")
    @Log(title = "工单报工记录明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PpWoBookDetail ppWoBookDetail)
    {
        return toAjax(ppWoBookDetailService.insertPpWoBookDetail(ppWoBookDetail));
    }

    /**
     * 修改工单报工记录明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PpWoBookDetail ppWoBookDetail = ppWoBookDetailService.selectPpWoBookDetailById(id);
        mmap.put("ppWoBookDetail", ppWoBookDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单报工记录明细
     */
    @RequiresPermissions("pp:wobookdetail:edit")
    @Log(title = "工单报工记录明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PpWoBookDetail ppWoBookDetail)
    {
        return toAjax(ppWoBookDetailService.updatePpWoBookDetail(ppWoBookDetail));
    }

    /**
     * 删除工单报工记录明细
     */
    @RequiresPermissions("pp:wobookdetail:remove")
    @Log(title = "工单报工记录明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ppWoBookDetailService.deletePpWoBookDetailByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<PpWoBookDetail> util = new ExcelUtil<PpWoBookDetail>(PpWoBookDetail.class);
        return util.importTemplateExcel("工单报工记录详细信息列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PpWoBookDetail> util = new ExcelUtil<PpWoBookDetail>(PpWoBookDetail.class);
        List<PpWoBookDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}