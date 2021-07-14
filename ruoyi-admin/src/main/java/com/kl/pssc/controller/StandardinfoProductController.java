package com.kl.pssc.controller;

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
import com.kl.common.annotation.Log;
import com.kl.common.enums.BusinessType;
import com.kl.pssc.domain.StandardinfoProduct;
import com.kl.pssc.service.IStandardinfoProductService;
import com.kl.common.core.controller.BaseController;
import com.kl.common.core.domain.AjaxResult;
import com.kl.common.utils.poi.ExcelUtil;
import com.kl.common.core.page.TableDataInfo;

/**
 * 产品检验标准Controller
 * 
 * @author admin
 * @date 2021-07-14
 */
@Controller
@RequestMapping("/standardinfo/product")
public class StandardinfoProductController extends BaseController
{
    private String prefix = "standardinfo/product";

    @Autowired
    private IStandardinfoProductService standardinfoProductService;

    @RequiresPermissions("standardinfo:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询产品检验标准列表
     */
    @RequiresPermissions("standardinfo:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StandardinfoProduct standardinfoProduct)
    {
        startPage();
        List<StandardinfoProduct> list = standardinfoProductService.selectStandardinfoProductList(standardinfoProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品检验标准列表
     */
    @RequiresPermissions("standardinfo:product:export")
    @Log(title = "产品检验标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StandardinfoProduct standardinfoProduct)
    {
        List<StandardinfoProduct> list = standardinfoProductService.selectStandardinfoProductList(standardinfoProduct);
        ExcelUtil<StandardinfoProduct> util = new ExcelUtil<StandardinfoProduct>(StandardinfoProduct.class);
        return util.exportExcel(list, "产品检验标准数据");
    }

    /**
     * 新增产品检验标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品检验标准
     */
    @RequiresPermissions("standardinfo:product:add")
    @Log(title = "产品检验标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StandardinfoProduct standardinfoProduct)
    {
        return toAjax(standardinfoProductService.insertStandardinfoProduct(standardinfoProduct));
    }

    /**
     * 修改产品检验标准
     */
    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable("productId") Long productId, ModelMap mmap)
    {
        StandardinfoProduct standardinfoProduct = standardinfoProductService.selectStandardinfoProductById(productId);
        mmap.put("standardinfoProduct", standardinfoProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品检验标准
     */
    @RequiresPermissions("standardinfo:product:edit")
    @Log(title = "产品检验标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StandardinfoProduct standardinfoProduct)
    {
        return toAjax(standardinfoProductService.updateStandardinfoProduct(standardinfoProduct));
    }

    /**
     * 删除产品检验标准
     */
    @RequiresPermissions("standardinfo:product:remove")
    @Log(title = "产品检验标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(standardinfoProductService.deleteStandardinfoProductByIds(ids));
    }
}
