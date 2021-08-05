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
import com.saas.pssc.domain.QcBadProjectDetail;
import com.saas.pssc.service.IQcBadProjectDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 不良项目记录明细Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/qc/badprodetail")
public class QcBadProjectDetailController extends BaseController
{
    private String prefix = "qc/badprodetail";

    @Autowired
    private IQcBadProjectDetailService qcBadProjectDetailService;

    @RequiresPermissions("qc:badprodetail:view")
    @GetMapping()
    public String badprodetail()
    {
        return prefix + "/badprodetail";
    }

    /**
     * 查询不良项目记录明细列表
     */
    @RequiresPermissions("qc:badprodetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcBadProjectDetail qcBadProjectDetail)
    {
        startPage();
        List<QcBadProjectDetail> list = qcBadProjectDetailService.selectQcBadProjectDetailList(qcBadProjectDetail);
        return getDataTable(list);
    }

    /**
     * 导出不良项目记录明细列表
     */
    @RequiresPermissions("qc:badprodetail:export")
    @Log(title = "不良项目记录明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcBadProjectDetail qcBadProjectDetail)
    {
        List<QcBadProjectDetail> list = qcBadProjectDetailService.selectQcBadProjectDetailList(qcBadProjectDetail);
        ExcelUtil<QcBadProjectDetail> util = new ExcelUtil<QcBadProjectDetail>(QcBadProjectDetail.class);
        return util.exportExcel(list, "不良项目记录明细数据");
    }

    /**
     * 新增不良项目记录明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存不良项目记录明细
     */
    @RequiresPermissions("qc:badprodetail:add")
    @Log(title = "不良项目记录明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcBadProjectDetail qcBadProjectDetail)
    {
        return toAjax(qcBadProjectDetailService.insertQcBadProjectDetail(qcBadProjectDetail));
    }

    /**
     * 修改不良项目记录明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcBadProjectDetail qcBadProjectDetail = qcBadProjectDetailService.selectQcBadProjectDetailById(id);
        mmap.put("qcBadProjectDetail", qcBadProjectDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存不良项目记录明细
     */
    @RequiresPermissions("qc:badprodetail:edit")
    @Log(title = "不良项目记录明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcBadProjectDetail qcBadProjectDetail)
    {
        return toAjax(qcBadProjectDetailService.updateQcBadProjectDetail(qcBadProjectDetail));
    }

    /**
     * 删除不良项目记录明细
     */
    @RequiresPermissions("qc:badprodetail:remove")
    @Log(title = "不良项目记录明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcBadProjectDetailService.deleteQcBadProjectDetailByIds(ids));
    }
    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcBadProjectDetail> util = new ExcelUtil<QcBadProjectDetail>(QcBadProjectDetail.class);
        return util.importTemplateExcel("不良项目记录明细列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcBadProjectDetail> util = new ExcelUtil<QcBadProjectDetail>(QcBadProjectDetail.class);
        List<QcBadProjectDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
