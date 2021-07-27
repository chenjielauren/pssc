package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.InFStoreDetail;
import com.saas.pssc.service.IInFStoreDetailService;

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

/**
 * 成品库存明细Controller
 * 
 * @author admin
 * @date 2021-07-27
 */
@Controller
@RequestMapping("/in/fstoredetail")
public class InFStoreDetailController extends BaseController
{
    private String prefix = "in/fstoredetail";

    @Autowired
    private IInFStoreDetailService inStoreDetailService;

    @RequiresPermissions("in:fstoredetail:view")
    @GetMapping()
    public String fstoredetail()
    {
        return prefix + "/fstoredetail";
    }

    /**
     * 查询成品库存明细列表
     */
    @RequiresPermissions("in:fstoredetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InFStoreDetail inStoreDetail)
    {
        startPage();
        List<InFStoreDetail> list = inStoreDetailService.selectInStoreDetailList(inStoreDetail);
        return getDataTable(list);
    }

    /**
     * 导出成品库存明细列表
     */
    @RequiresPermissions("in:fstoredetail:export")
    @Log(title = "成品库存明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InFStoreDetail inStoreDetail)
    {
        List<InFStoreDetail> list = inStoreDetailService.selectInStoreDetailList(inStoreDetail);
        ExcelUtil<InFStoreDetail> util = new ExcelUtil<InFStoreDetail>(InFStoreDetail.class);
        return util.exportExcel(list, "成品库存明细数据");
    }

    /**
     * 新增成品库存明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成品库存明细
     */
    @RequiresPermissions("in:fstoredetail:add")
    @Log(title = "成品库存明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InFStoreDetail inStoreDetail)
    {
        return toAjax(inStoreDetailService.insertInStoreDetail(inStoreDetail));
    }

    /**
     * 修改成品库存明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        InFStoreDetail inStoreDetail = inStoreDetailService.selectInStoreDetailById(id);
        mmap.put("inStoreDetail", inStoreDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品库存明细
     */
    @RequiresPermissions("in:fstoredetail:edit")
    @Log(title = "成品库存明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InFStoreDetail inStoreDetail)
    {
        return toAjax(inStoreDetailService.updateInStoreDetail(inStoreDetail));
    }

    /**
     * 删除成品库存明细
     */
    @RequiresPermissions("in:fstoredetail:remove")
    @Log(title = "成品库存明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(inStoreDetailService.deleteInStoreDetailByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<InFStoreDetail> util = new ExcelUtil<InFStoreDetail>(InFStoreDetail.class);
        return util.importTemplateExcel("材料信息");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<InFStoreDetail> util = new ExcelUtil<InFStoreDetail>(InFStoreDetail.class);
        List<InFStoreDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
