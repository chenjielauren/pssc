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
import com.saas.pssc.domain.BsProSopDetail;
import com.saas.pssc.service.IBsProSopDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 成品检验标准明细Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/bs/prodetail")
public class BsProSopDetailController extends BaseController
{
    private String prefix = "bs/prodetail";

    @Autowired
    private IBsProSopDetailService bsProSopDetailService;

    @RequiresPermissions("bs:prodetail:view")
    @GetMapping()
    public String prodetail()
    {
        return prefix + "/prodetail";
    }

    /**
     * 查询成品检验标准明细列表
     */
    @RequiresPermissions("bs:prodetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsProSopDetail bsProSopDetail)
    {
        startPage();
        List<BsProSopDetail> list = bsProSopDetailService.selectBsProSopDetailList(bsProSopDetail);
        return getDataTable(list);
    }

    /**
     * 导出成品检验标准明细列表
     */
    @RequiresPermissions("bs:prodetail:export")
    @Log(title = "成品检验标准明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsProSopDetail bsProSopDetail)
    {
        List<BsProSopDetail> list = bsProSopDetailService.selectBsProSopDetailList(bsProSopDetail);
        ExcelUtil<BsProSopDetail> util = new ExcelUtil<BsProSopDetail>(BsProSopDetail.class);
        return util.exportExcel(list, "成品检验标准明细数据");
    }

    /**
     * 新增成品检验标准明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成品检验标准明细
     */
    @RequiresPermissions("bs:prodetail:add")
    @Log(title = "成品检验标准明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsProSopDetail bsProSopDetail)
    {
        return toAjax(bsProSopDetailService.insertBsProSopDetail(bsProSopDetail));
    }

    /**
     * 修改成品检验标准明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsProSopDetail bsProSopDetail = bsProSopDetailService.selectBsProSopDetailById(id);
        mmap.put("bsProSopDetail", bsProSopDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品检验标准明细
     */
    @RequiresPermissions("bs:prodetail:edit")
    @Log(title = "成品检验标准明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsProSopDetail bsProSopDetail)
    {
        return toAjax(bsProSopDetailService.updateBsProSopDetail(bsProSopDetail));
    }

    /**
     * 删除成品检验标准明细
     */
    @RequiresPermissions("bs:prodetail:remove")
    @Log(title = "成品检验标准明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsProSopDetailService.deleteBsProSopDetailByIds(ids));
    }
    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsProSopDetail> util = new ExcelUtil<BsProSopDetail>(BsProSopDetail.class);
        return util.importTemplateExcel("成品检验标准明细列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsProSopDetail> util = new ExcelUtil<BsProSopDetail>(BsProSopDetail.class);
        List<BsProSopDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
