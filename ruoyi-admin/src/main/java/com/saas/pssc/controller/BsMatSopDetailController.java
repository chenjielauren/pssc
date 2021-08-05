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
import com.saas.pssc.domain.BsMatSopDetail;
import com.saas.pssc.service.IBsMatSopDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 材料检验标准明细Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/bs/matdetail")
public class BsMatSopDetailController extends BaseController
{
    private String prefix = "bs/matdetail";

    @Autowired
    private IBsMatSopDetailService bsMatSopDetailService;

    @RequiresPermissions("bs:matdetail:view")
    @GetMapping()
    public String matdetail()
    {
        return prefix + "/matdetail";
    }

    /**
     * 查询材料检验标准明细列表
     */
    @RequiresPermissions("bs:matdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsMatSopDetail bsMatSopDetail)
    {
        startPage();
        List<BsMatSopDetail> list = bsMatSopDetailService.selectBsMatSopDetailList(bsMatSopDetail);
        return getDataTable(list);
    }

    /**
     * 导出材料检验标准明细列表
     */
    @RequiresPermissions("bs:matdetail:export")
    @Log(title = "材料检验标准明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsMatSopDetail bsMatSopDetail)
    {
        List<BsMatSopDetail> list = bsMatSopDetailService.selectBsMatSopDetailList(bsMatSopDetail);
        ExcelUtil<BsMatSopDetail> util = new ExcelUtil<BsMatSopDetail>(BsMatSopDetail.class);
        return util.exportExcel(list, "材料检验标准明细数据");
    }

    /**
     * 新增材料检验标准明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存材料检验标准明细
     */
    @RequiresPermissions("bs:matdetail:add")
    @Log(title = "材料检验标准明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsMatSopDetail bsMatSopDetail)
    {
        return toAjax(bsMatSopDetailService.insertBsMatSopDetail(bsMatSopDetail));
    }

    /**
     * 修改材料检验标准明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsMatSopDetail bsMatSopDetail = bsMatSopDetailService.selectBsMatSopDetailById(id);
        mmap.put("bsMatSopDetail", bsMatSopDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存材料检验标准明细
     */
    @RequiresPermissions("bs:matdetail:edit")
    @Log(title = "材料检验标准明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsMatSopDetail bsMatSopDetail)
    {
        return toAjax(bsMatSopDetailService.updateBsMatSopDetail(bsMatSopDetail));
    }

    /**
     * 删除材料检验标准明细
     */
    @RequiresPermissions("bs:matdetail:remove")
    @Log(title = "材料检验标准明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsMatSopDetailService.deleteBsMatSopDetailByIds(ids));
    }
    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsMatSopDetail> util = new ExcelUtil<BsMatSopDetail>(BsMatSopDetail.class);
        return util.importTemplateExcel("材料检验明细列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsMatSopDetail> util = new ExcelUtil<BsMatSopDetail>(BsMatSopDetail.class);
        List<BsMatSopDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
