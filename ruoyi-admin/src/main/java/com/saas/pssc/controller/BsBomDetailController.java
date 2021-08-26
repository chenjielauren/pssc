package com.saas.pssc.controller;

import java.text.NumberFormat;
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
import com.saas.pssc.domain.BsBomDetail;
import com.saas.pssc.service.IBsBomDetailService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * bom物料清单明细Controller
 * 
 * @author admin
 * @date 2021-08-09
 */
@Controller
@RequestMapping("/bs/bomdetail")
public class BsBomDetailController extends BaseController
{
    private String prefix = "bs/bomdetail";

    @Autowired
    private IBsBomDetailService bsBomDetailService;

    @RequiresPermissions("bs:bomdetail:view")
    @GetMapping()
    public String bomdetail()
    {
        return prefix + "/bomdetail";
    }

    /**
     * 查询bom物料清单明细列表
     */
    @RequiresPermissions("bs:bomdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsBomDetail bsBomDetail)
    {
        startPage();
        List<BsBomDetail> list = bsBomDetailService.selectBsBomDetailList(bsBomDetail);
        return getDataTable(list);
    }

    /**
     * 导出bom物料清单明细列表
     */
    @RequiresPermissions("bs:bomdetail:export")
    @Log(title = "bom物料清单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsBomDetail bsBomDetail)
    {
        List<BsBomDetail> list = bsBomDetailService.selectBsBomDetailList(bsBomDetail);
        ExcelUtil<BsBomDetail> util = new ExcelUtil<BsBomDetail>(BsBomDetail.class);
        return util.exportExcel(list, "bom物料清单明细数据");
    }

    /**
     * 新增bom物料清单明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存bom物料清单明细
     */
    @RequiresPermissions("bs:bomdetail:add")
    @Log(title = "bom物料清单明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsBomDetail bsBomDetail)
    {
        return toAjax(bsBomDetailService.insertBsBomDetail(bsBomDetail));
    }

    /**
     * 修改bom物料清单明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsBomDetail bsBomDetail = bsBomDetailService.selectBsBomDetailById(id);
        mmap.put("bsBomDetail", bsBomDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存bom物料清单明细
     */
    @RequiresPermissions("bs:bomdetail:edit")
    @Log(title = "bom物料清单明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsBomDetail bsBomDetail)
    {
        return toAjax(bsBomDetailService.updateBsBomDetail(bsBomDetail));
    }

    /**
     * 删除bom物料清单明细
     */
    @RequiresPermissions("bs:bomdetail:remove")
    @Log(title = "bom物料清单明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsBomDetailService.deleteBsBomDetailByIds(ids));
    }

    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsBomDetail> util = new ExcelUtil<BsBomDetail>(BsBomDetail.class);
        return util.importTemplateExcel("BOM明细列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsBomDetail> util = new ExcelUtil<BsBomDetail>(BsBomDetail.class);
        List<BsBomDetail> list = util.importExcel(file.getInputStream());
        if(StringUtils.isNotEmpty(list)){
            NumberFormat num = NumberFormat.getPercentInstance();
            num.setMinimumFractionDigits(2);
            for(BsBomDetail bom:list){
                if(StringUtils.isNotEmpty(bom.getUnratio())){
                    String unratio = num.format(Double.valueOf(bom.getUnratio()));//将小数转换为百分比
                    bom.setUnratio(unratio);
                }
            }
        }
        return AjaxResult.success(list);
    }
}
