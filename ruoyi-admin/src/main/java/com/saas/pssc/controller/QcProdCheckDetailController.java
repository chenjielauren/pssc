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
import com.saas.pssc.domain.QcProdCheckDetail;
import com.saas.pssc.service.IQcProdCheckDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 成品检验明细Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/prodcd")
public class QcProdCheckDetailController extends BaseController
{
    private String prefix = "qc/prodcd";

    @Autowired
    private IQcProdCheckDetailService qcProdCheckDetailService;

    @RequiresPermissions("qc:prodcd:view")
    @GetMapping()
    public String prodcd()
    {
        return prefix + "/prodcd";
    }

    /**
     * 查询成品检验明细列表
     */
    @RequiresPermissions("qc:prodcd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcProdCheckDetail qcProdCheckDetail)
    {
        startPage();
        List<QcProdCheckDetail> list = qcProdCheckDetailService.selectQcProdCheckDetailList(qcProdCheckDetail);
        return getDataTable(list);
    }

    /**
     * 导出成品检验明细列表
     */
    @RequiresPermissions("qc:prodcd:export")
    @Log(title = "成品检验明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcProdCheckDetail qcProdCheckDetail)
    {
        List<QcProdCheckDetail> list = qcProdCheckDetailService.selectQcProdCheckDetailList(qcProdCheckDetail);
        ExcelUtil<QcProdCheckDetail> util = new ExcelUtil<QcProdCheckDetail>(QcProdCheckDetail.class);
        return util.exportExcel(list, "成品检验明细数据");
    }

    /**
     * 新增成品检验明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成品检验明细
     */
    @RequiresPermissions("qc:prodcd:add")
    @Log(title = "成品检验明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcProdCheckDetail qcProdCheckDetail)
    {
        return toAjax(qcProdCheckDetailService.insertQcProdCheckDetail(qcProdCheckDetail));
    }

    /**
     * 修改成品检验明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProdCheckDetail qcProdCheckDetail = qcProdCheckDetailService.selectQcProdCheckDetailById(id);
        mmap.put("qcProdCheckDetail", qcProdCheckDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品检验明细
     */
    @RequiresPermissions("qc:prodcd:edit")
    @Log(title = "成品检验明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcProdCheckDetail qcProdCheckDetail)
    {
        return toAjax(qcProdCheckDetailService.updateQcProdCheckDetail(qcProdCheckDetail));
    }

    /**
     * 删除成品检验明细
     */
    @RequiresPermissions("qc:prodcd:remove")
    @Log(title = "成品检验明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcProdCheckDetailService.deleteQcProdCheckDetailByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcProdCheckDetail> util = new ExcelUtil<QcProdCheckDetail>(QcProdCheckDetail.class);
        return util.importTemplateExcel("成品检验信息列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcProdCheckDetail> util = new ExcelUtil<QcProdCheckDetail>(QcProdCheckDetail.class);
        List<QcProdCheckDetail> prodCheckDetailList = util.importExcel(file.getInputStream());
        return AjaxResult.success(prodCheckDetailList);
    }
}
