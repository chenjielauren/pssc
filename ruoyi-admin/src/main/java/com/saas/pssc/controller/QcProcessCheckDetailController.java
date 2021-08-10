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
import com.saas.pssc.domain.QcProcessCheckDetail;
import com.saas.pssc.service.IQcProcessCheckDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 过程检验明细Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/proccd")
public class QcProcessCheckDetailController extends BaseController
{
    private String prefix = "qc/proccd";

    @Autowired
    private IQcProcessCheckDetailService qcProcessCheckDetailService;

    @RequiresPermissions("qc:proccd:view")
    @GetMapping()
    public String proccd()
    {
        return prefix + "/proccd";
    }

    /**
     * 查询过程检验明细列表
     */
    @RequiresPermissions("qc:proccd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcProcessCheckDetail qcProcessCheckDetail)
    {
        startPage();
        List<QcProcessCheckDetail> list = qcProcessCheckDetailService.selectQcProcessCheckDetailList(qcProcessCheckDetail);
        return getDataTable(list);
    }

    /**
     * 导出过程检验明细列表
     */
    @RequiresPermissions("qc:proccd:export")
    @Log(title = "过程检验明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcProcessCheckDetail qcProcessCheckDetail)
    {
        List<QcProcessCheckDetail> list = qcProcessCheckDetailService.selectQcProcessCheckDetailList(qcProcessCheckDetail);
        ExcelUtil<QcProcessCheckDetail> util = new ExcelUtil<QcProcessCheckDetail>(QcProcessCheckDetail.class);
        return util.exportExcel(list, "过程检验明细数据");
    }

    /**
     * 新增过程检验明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存过程检验明细
     */
    @RequiresPermissions("qc:proccd:add")
    @Log(title = "过程检验明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcProcessCheckDetail qcProcessCheckDetail)
    {
        return toAjax(qcProcessCheckDetailService.insertQcProcessCheckDetail(qcProcessCheckDetail));
    }

    /**
     * 修改过程检验明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProcessCheckDetail qcProcessCheckDetail = qcProcessCheckDetailService.selectQcProcessCheckDetailById(id);
        mmap.put("qcProcessCheckDetail", qcProcessCheckDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存过程检验明细
     */
    @RequiresPermissions("qc:proccd:edit")
    @Log(title = "过程检验明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcProcessCheckDetail qcProcessCheckDetail)
    {
        return toAjax(qcProcessCheckDetailService.updateQcProcessCheckDetail(qcProcessCheckDetail));
    }

    /**
     * 删除过程检验明细
     */
    @RequiresPermissions("qc:proccd:remove")
    @Log(title = "过程检验明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcProcessCheckDetailService.deleteQcProcessCheckDetailByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcProcessCheckDetail> util = new ExcelUtil<QcProcessCheckDetail>(QcProcessCheckDetail.class);
        return util.importTemplateExcel("检验标准信息列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcProcessCheckDetail> util = new ExcelUtil<QcProcessCheckDetail>(QcProcessCheckDetail.class);
        List<QcProcessCheckDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
