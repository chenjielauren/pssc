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
import com.saas.pssc.domain.QcMatCheckDetail;
import com.saas.pssc.service.IQcMatCheckDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 原材料检验明细Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/matcd")
public class QcMatCheckDetailController extends BaseController
{
    private String prefix = "qc/matcd";

    @Autowired
    private IQcMatCheckDetailService qcMatCheckDetailService;

    @RequiresPermissions("qc:matcd:view")
    @GetMapping()
    public String matcd()
    {
        return prefix + "/matcd";
    }

    /**
     * 查询原材料检验明细列表
     */
    @RequiresPermissions("qc:matcd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcMatCheckDetail qcMatCheckDetail)
    {
        startPage();
        List<QcMatCheckDetail> list = qcMatCheckDetailService.selectQcMatCheckDetailList(qcMatCheckDetail);
        return getDataTable(list);
    }

    /**
     * 导出原材料检验明细列表
     */
    @RequiresPermissions("qc:matcd:export")
    @Log(title = "原材料检验明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcMatCheckDetail qcMatCheckDetail)
    {
        List<QcMatCheckDetail> list = qcMatCheckDetailService.selectQcMatCheckDetailList(qcMatCheckDetail);
        ExcelUtil<QcMatCheckDetail> util = new ExcelUtil<QcMatCheckDetail>(QcMatCheckDetail.class);
        return util.exportExcel(list, "原材料检验明细数据");
    }

    /**
     * 新增原材料检验明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存原材料检验明细
     */
    @RequiresPermissions("qc:matcd:add")
    @Log(title = "原材料检验明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcMatCheckDetail qcMatCheckDetail)
    {
        return toAjax(qcMatCheckDetailService.insertQcMatCheckDetail(qcMatCheckDetail));
    }

    /**
     * 修改原材料检验明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcMatCheckDetail qcMatCheckDetail = qcMatCheckDetailService.selectQcMatCheckDetailById(id);
        mmap.put("qcMatCheckDetail", qcMatCheckDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存原材料检验明细
     */
    @RequiresPermissions("qc:matcd:edit")
    @Log(title = "原材料检验明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcMatCheckDetail qcMatCheckDetail)
    {
        return toAjax(qcMatCheckDetailService.updateQcMatCheckDetail(qcMatCheckDetail));
    }

    /**
     * 删除原材料检验明细
     */
    @RequiresPermissions("qc:matcd:remove")
    @Log(title = "原材料检验明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcMatCheckDetailService.deleteQcMatCheckDetailByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcMatCheckDetail> util = new ExcelUtil<QcMatCheckDetail>(QcMatCheckDetail.class);
        return util.importTemplateExcel("材料检验信息列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcMatCheckDetail> util = new ExcelUtil<QcMatCheckDetail>(QcMatCheckDetail.class);
        List<QcMatCheckDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
