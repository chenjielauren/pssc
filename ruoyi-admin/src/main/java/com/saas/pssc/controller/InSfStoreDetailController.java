package com.saas.pssc.controller;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.bean.BeanUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.InSfStoreDetail;
import com.saas.pssc.domain.InStoreDetail;
import com.saas.pssc.service.IInStoreDetailService;

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
 * 半成品库存明细Controller
 * 
 * @author admin
 * @date 2021-07-27
 */
@Controller
@RequestMapping("/in/sfstoredetail")
public class InSfStoreDetailController extends BaseController
{
    private String prefix = "in/sfstoredetail";

    @Autowired
    private IInStoreDetailService inStoreDetailService;

    @RequiresPermissions("in:sfstoredetail:view")
    @GetMapping()
    public String sfstoredetail()
    {
        return prefix + "/sfstoredetail";
    }

    /**
     * 查询半成品库存明细列表
     */
    @RequiresPermissions("in:sfstoredetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InStoreDetail inStoreDetail)
    {
        startPage();
        List<InStoreDetail> list = inStoreDetailService.selectInStoreDetailList(inStoreDetail);
        return getDataTable(list);
    }

    /**
     * 导出半成品库存明细列表
     */
    @RequiresPermissions("in:sfstoredetail:export")
    @Log(title = "半成品库存明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InStoreDetail inStoreDetail)
    {
        List<InStoreDetail> list = inStoreDetailService.selectInStoreDetailList(inStoreDetail);
        ExcelUtil<InStoreDetail> util = new ExcelUtil<InStoreDetail>(InStoreDetail.class);
        return util.exportExcel(list, "半成品库存明细数据");
    }

    /**
     * 新增半成品库存明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存半成品库存明细
     */
    @RequiresPermissions("in:sfstoredetail:add")
    @Log(title = "半成品库存明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InStoreDetail inStoreDetail)
    {
        return toAjax(inStoreDetailService.insertInStoreDetail(inStoreDetail));
    }

    /**
     * 修改半成品库存明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        InStoreDetail inStoreDetail = inStoreDetailService.selectInStoreDetailById(id);
        mmap.put("inStoreDetail", inStoreDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存半成品库存明细
     */
    @RequiresPermissions("in:sfstoredetail:edit")
    @Log(title = "半成品库存明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InStoreDetail inStoreDetail)
    {
        return toAjax(inStoreDetailService.updateInStoreDetail(inStoreDetail));
    }

    /**
     * 删除半成品库存明细
     */
    @RequiresPermissions("in:sfstoredetail:remove")
    @Log(title = "半成品库存明细", businessType = BusinessType.DELETE)
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
        ExcelUtil<InSfStoreDetail> util = new ExcelUtil<InSfStoreDetail>(InSfStoreDetail.class);
        return util.importTemplateExcel("材料信息");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<InSfStoreDetail> util = new ExcelUtil<InSfStoreDetail>(InSfStoreDetail.class);
        List<InSfStoreDetail> list = util.importExcel(file.getInputStream());
        List<InStoreDetail> retList = new ArrayList<InStoreDetail>();
        if(StringUtils.isNotEmpty(list)){
            for(InSfStoreDetail sfdetail:list){
                InStoreDetail detail = new InStoreDetail();
                BeanUtils.copyProperties(sfdetail, detail);
                retList.add(detail);
            }
        }
        return AjaxResult.success(retList);
    }
}
