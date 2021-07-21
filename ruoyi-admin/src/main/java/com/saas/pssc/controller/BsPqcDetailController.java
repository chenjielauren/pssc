package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.BsPqcDetail;
import com.saas.pssc.service.IBsPqcDetailService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品检验标准明细Controller
 * 
 * @author admin
 * @date 2021-07-15
 */
@Controller
@RequestMapping("/bs/pqcdetail")
public class BsPqcDetailController extends BaseController
{
    private String prefix = "bs/pqcdetail";

    @Autowired
    private IBsPqcDetailService bsPqcDetailService;

    @RequiresPermissions("bs:pqcdetail:view")
    @GetMapping()
    public String pqcdetail()
    {
        return prefix + "/pqcdetail";
    }

    /**
     * 查询产品检验标准明细列表
     */
    @RequiresPermissions("bs:pqcdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsPqcDetail bsPqcDetail)
    {
        startPage();
        List<BsPqcDetail> list = bsPqcDetailService.selectBsPqcDetailList(bsPqcDetail);
        return getDataTable(list);
    }

    /**
     * 导出产品检验标准明细列表
     */
    @RequiresPermissions("bs:pqcdetail:export")
    @Log(title = "产品检验标准明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsPqcDetail bsPqcDetail)
    {
        List<BsPqcDetail> list = bsPqcDetailService.selectBsPqcDetailList(bsPqcDetail);
        ExcelUtil<BsPqcDetail> util = new ExcelUtil<BsPqcDetail>(BsPqcDetail.class);
        return util.exportExcel(list, "产品检验标准明细数据");
    }

    /**
     * 新增产品检验标准明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品检验标准明细
     */
    @RequiresPermissions("bs:pqcdetail:add")
    @Log(title = "产品检验标准明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsPqcDetail bsPqcDetail)
    {
        return toAjax(bsPqcDetailService.insertBsPqcDetail(bsPqcDetail));
    }

    /**
     * 修改产品检验标准明细
     */
    @GetMapping("/edit/{Id}")
    public String edit(@PathVariable("Id") Long Id, ModelMap mmap)
    {
        BsPqcDetail bsPqcDetail = bsPqcDetailService.selectBsPqcDetailById(Id);
        mmap.put("bsPqcDetail", bsPqcDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品检验标准明细
     */
    @RequiresPermissions("bs:pqcdetail:edit")
    @Log(title = "产品检验标准明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsPqcDetail bsPqcDetail)
    {
        return toAjax(bsPqcDetailService.updateBsPqcDetail(bsPqcDetail));
    }

    /**
     * 删除产品检验标准明细
     */
    @RequiresPermissions("bs:pqcdetail:remove")
    @Log(title = "产品检验标准明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsPqcDetailService.deleteBsPqcDetailByIds(ids));
    }
}
