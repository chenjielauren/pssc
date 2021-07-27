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
import com.saas.pssc.domain.BsCraftSopDetail;
import com.saas.pssc.service.IBsCraftSopDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 工艺标准与CCP明细Controller
 * 
 * @author admin
 * @date 2021-07-19
 */
@Controller
@RequestMapping("/bs/craftsopdetail")
public class BsCraftSopDetailController extends BaseController
{
    private String prefix = "bs/craftsopdetail";

    @Autowired
    private IBsCraftSopDetailService bsCraftSopDetailService;

    @RequiresPermissions("bs:craftsopdetail:view")
    @GetMapping()
    public String craftsopdetail()
    {
        return prefix + "/craftsopdetail";
    }

    /**
     * 查询工艺标准与CCP明细列表
     */
    @RequiresPermissions("bs:craftsopdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsCraftSopDetail bsCraftSopDetail)
    {
        startPage();
        List<BsCraftSopDetail> list = bsCraftSopDetailService.selectBsCraftSopDetailList(bsCraftSopDetail);
        return getDataTable(list);
    }

    /**
     * 导出工艺标准与CCP明细列表
     */
    @RequiresPermissions("bs:craftsopdetail:export")
    @Log(title = "工艺标准与CCP明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsCraftSopDetail bsCraftSopDetail)
    {
        List<BsCraftSopDetail> list = bsCraftSopDetailService.selectBsCraftSopDetailList(bsCraftSopDetail);
        ExcelUtil<BsCraftSopDetail> util = new ExcelUtil<BsCraftSopDetail>(BsCraftSopDetail.class);
        return util.exportExcel(list, "工艺标准与CCP明细数据");
    }

    /**
     * 新增工艺标准与CCP明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工艺标准与CCP明细
     */
    @RequiresPermissions("bs:craftsopdetail:add")
    @Log(title = "工艺标准与CCP明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsCraftSopDetail bsCraftSopDetail)
    {
        return toAjax(bsCraftSopDetailService.insertBsCraftSopDetail(bsCraftSopDetail));
    }

    /**
     * 修改工艺标准与CCP明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsCraftSopDetail bsCraftSopDetail = bsCraftSopDetailService.selectBsCraftSopDetailById(id);
        mmap.put("bsCraftSopDetail", bsCraftSopDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存工艺标准与CCP明细
     */
    @RequiresPermissions("bs:craftsopdetail:edit")
    @Log(title = "工艺标准与CCP明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsCraftSopDetail bsCraftSopDetail)
    {
        return toAjax(bsCraftSopDetailService.updateBsCraftSopDetail(bsCraftSopDetail));
    }

    /**
     * 删除工艺标准与CCP明细
     */
    @RequiresPermissions("bs:craftsopdetail:remove")
    @Log(title = "工艺标准与CCP明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsCraftSopDetailService.deleteBsCraftSopDetailByIds(ids));
    }

    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsCraftSopDetail> util = new ExcelUtil<BsCraftSopDetail>(BsCraftSopDetail.class);
        return util.importTemplateExcel("工艺标准与CCP明细列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsCraftSopDetail> util = new ExcelUtil<BsCraftSopDetail>(BsCraftSopDetail.class);
        List<BsCraftSopDetail> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(list);
    }
}
