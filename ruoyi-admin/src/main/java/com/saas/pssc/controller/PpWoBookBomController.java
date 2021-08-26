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
import com.saas.pssc.domain.PpWoBookBom;
import com.saas.pssc.service.IPpWoBookBomService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 工单BOM信息Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/pp/wbbom")
public class PpWoBookBomController extends BaseController
{
    private String prefix = "pp/wbbom";

    @Autowired
    private IPpWoBookBomService ppWoBookBomService;

    @RequiresPermissions("pp:wbbom:view")
    @GetMapping()
    public String wbbom()
    {
        return prefix + "/wbbom";
    }

    /**
     * 查询工单BOM信息列表
     */
    @RequiresPermissions("pp:wbbom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PpWoBookBom ppWoBookBom)
    {
        startPage();
        List<PpWoBookBom> list = ppWoBookBomService.selectPpWoBookBomList(ppWoBookBom);
        return getDataTable(list);
    }

    /**
     * 导出工单BOM信息列表
     */
    @RequiresPermissions("pp:wbbom:export")
    @Log(title = "工单BOM信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PpWoBookBom ppWoBookBom)
    {
        List<PpWoBookBom> list = ppWoBookBomService.selectPpWoBookBomList(ppWoBookBom);
        ExcelUtil<PpWoBookBom> util = new ExcelUtil<PpWoBookBom>(PpWoBookBom.class);
        return util.exportExcel(list, "工单BOM信息数据");
    }

    /**
     * 新增工单BOM信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工单BOM信息
     */
    @RequiresPermissions("pp:wbbom:add")
    @Log(title = "工单BOM信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PpWoBookBom ppWoBookBom)
    {
        return toAjax(ppWoBookBomService.insertPpWoBookBom(ppWoBookBom));
    }

    /**
     * 修改工单BOM信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        PpWoBookBom ppWoBookBom = ppWoBookBomService.selectPpWoBookBomById(id);
        mmap.put("ppWoBookBom", ppWoBookBom);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单BOM信息
     */
    @RequiresPermissions("pp:wbbom:edit")
    @Log(title = "工单BOM信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PpWoBookBom ppWoBookBom)
    {
        return toAjax(ppWoBookBomService.updatePpWoBookBom(ppWoBookBom));
    }

    /**
     * 删除工单BOM信息
     */
    @RequiresPermissions("pp:wbbom:remove")
    @Log(title = "工单BOM信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ppWoBookBomService.deletePpWoBookBomByIds(ids));
    }
    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<PpWoBookBom> util = new ExcelUtil<PpWoBookBom>(PpWoBookBom.class);
        return util.importTemplateExcel("工单BOM信息列表");
    }
    
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PpWoBookBom> util = new ExcelUtil<PpWoBookBom>(PpWoBookBom.class);
        List<PpWoBookBom> list = util.importExcel(file.getInputStream());
        if(StringUtils.isNotEmpty(list)){
            NumberFormat num = NumberFormat.getPercentInstance();
            num.setMinimumFractionDigits(2);
            for(PpWoBookBom bom : list){
                if(StringUtils.isNotEmpty(bom.getUnratio())){
                    String unratio = num.format(Double.valueOf(bom.getUnratio()));//将小数转换为百分比
                    bom.setUnratio(unratio);
                }
            }
        }
        return AjaxResult.success(list);
    }
}
