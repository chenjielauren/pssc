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
import com.saas.common.annotation.Log;
import com.saas.common.enums.BusinessType;
import com.saas.pssc.domain.SdDeliveryDetail;
import com.saas.pssc.service.ISdDeliveryDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 发货单标注明细Controller
 * 
 * @author admin
 * @date 2021-08-18
 */
@Controller
@RequestMapping("/sd/detail")
public class SdDeliveryDetailController extends BaseController
{
    private String prefix = "sd/detail";

    @Autowired
    private ISdDeliveryDetailService sdDeliveryDetailService;

    @RequiresPermissions("sd:detail:view")
    @GetMapping()
    public String detail()
    {
        return prefix + "/detail";
    }

    /**
     * 查询发货单标注明细列表
     */
    @RequiresPermissions("sd:detail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SdDeliveryDetail sdDeliveryDetail)
    {
        startPage();
        List<SdDeliveryDetail> list = sdDeliveryDetailService.selectSdDeliveryDetailList(sdDeliveryDetail);
        return getDataTable(list);
    }

    /**
     * 导出发货单标注明细列表
     */
    @RequiresPermissions("sd:detail:export")
    @Log(title = "发货单标注明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SdDeliveryDetail sdDeliveryDetail)
    {
        List<SdDeliveryDetail> list = sdDeliveryDetailService.selectSdDeliveryDetailList(sdDeliveryDetail);
        ExcelUtil<SdDeliveryDetail> util = new ExcelUtil<SdDeliveryDetail>(SdDeliveryDetail.class);
        return util.exportExcel(list, "发货单标注明细数据");
    }

    /**
     * 新增发货单标注明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存发货单标注明细
     */
    @RequiresPermissions("sd:detail:add")
    @Log(title = "发货单标注明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SdDeliveryDetail sdDeliveryDetail)
    {
        return toAjax(sdDeliveryDetailService.insertSdDeliveryDetail(sdDeliveryDetail));
    }

    /**
     * 修改发货单标注明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        SdDeliveryDetail sdDeliveryDetail = sdDeliveryDetailService.selectSdDeliveryDetailById(id);
        mmap.put("sdDeliveryDetail", sdDeliveryDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存发货单标注明细
     */
    @RequiresPermissions("sd:detail:edit")
    @Log(title = "发货单标注明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SdDeliveryDetail sdDeliveryDetail)
    {
        return toAjax(sdDeliveryDetailService.updateSdDeliveryDetail(sdDeliveryDetail));
    }

    /**
     * 删除发货单标注明细
     */
    @RequiresPermissions("sd:detail:remove")
    @Log(title = "发货单标注明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sdDeliveryDetailService.deleteSdDeliveryDetailByIds(ids));
    }
}
