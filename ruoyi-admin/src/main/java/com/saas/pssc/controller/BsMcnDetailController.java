package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.BsMcnDetail;
import com.saas.pssc.service.IBsMcnDetailService;

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
 * 4M变更单明细Controller
 * 
 * @author admin
 * @date 2021-07-19
 */
@Controller
@RequestMapping("/bs/mcndetail")
public class BsMcnDetailController extends BaseController
{
    private String prefix = "bs/mcndetail";

    @Autowired
    private IBsMcnDetailService bsMcnDetailService;

    @RequiresPermissions("bs:mcndetail:view")
    @GetMapping()
    public String mcndetail()
    {
        return prefix + "/mcndetail";
    }

    /**
     * 查询4M变更单明细列表
     */
    @RequiresPermissions("bs:mcndetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsMcnDetail bsMcnDetail)
    {
        startPage();
        List<BsMcnDetail> list = bsMcnDetailService.selectBsMcnDetailList(bsMcnDetail);
        return getDataTable(list);
    }

    /**
     * 导出4M变更单明细列表
     */
    @RequiresPermissions("bs:mcndetail:export")
    @Log(title = "4M变更单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsMcnDetail bsMcnDetail)
    {
        List<BsMcnDetail> list = bsMcnDetailService.selectBsMcnDetailList(bsMcnDetail);
        ExcelUtil<BsMcnDetail> util = new ExcelUtil<BsMcnDetail>(BsMcnDetail.class);
        return util.exportExcel(list, "4M变更单明细数据");
    }

    /**
     * 新增4M变更单明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存4M变更单明细
     */
    @RequiresPermissions("bs:mcndetail:add")
    @Log(title = "4M变更单明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsMcnDetail bsMcnDetail)
    {
        return toAjax(bsMcnDetailService.insertBsMcnDetail(bsMcnDetail));
    }

    /**
     * 修改4M变更单明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsMcnDetail bsMcnDetail = bsMcnDetailService.selectBsMcnDetailById(id);
        mmap.put("bsMcnDetail", bsMcnDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存4M变更单明细
     */
    @RequiresPermissions("bs:mcndetail:edit")
    @Log(title = "4M变更单明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsMcnDetail bsMcnDetail)
    {
        return toAjax(bsMcnDetailService.updateBsMcnDetail(bsMcnDetail));
    }

    /**
     * 删除4M变更单明细
     */
    @RequiresPermissions("bs:mcndetail:remove")
    @Log(title = "4M变更单明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsMcnDetailService.deleteBsMcnDetailByIds(ids));
    }
    
    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsMcnDetail> util = new ExcelUtil<BsMcnDetail>(BsMcnDetail.class);
        return util.importTemplateExcel("4M变更单明细列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsMcnDetail> util = new ExcelUtil<BsMcnDetail>(BsMcnDetail.class);
        List<BsMcnDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
