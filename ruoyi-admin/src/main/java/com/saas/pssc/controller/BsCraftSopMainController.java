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
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.service.IBsCraftSopMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 工艺标准与CCPController
 * 
 * @author admin
 * @date 2021-07-19
 */
@Controller
@RequestMapping("/bs/craftsopmain")
public class BsCraftSopMainController extends BaseController
{
    private String prefix = "bs/craftsopmain";

    @Autowired
    private IBsCraftSopMainService bsCraftSopMainService;

    @RequiresPermissions("bs:craftsopmain:view")
    @GetMapping()
    public String craftsopmain()
    {
        return prefix + "/craftsopmain";
    }

    /**
     * 查询工艺标准与CCP列表
     */
    @RequiresPermissions("bs:craftsopmain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsCraftSopMain bsCraftSopMain)
    {
        startPage();
        List<BsCraftSopMain> list = bsCraftSopMainService.selectBsCraftSopMainList(bsCraftSopMain);
        return getDataTable(list);
    }

    /**
     * 导出工艺标准与CCP列表
     */
    @RequiresPermissions("bs:craftsopmain:export")
    @Log(title = "工艺标准与CCP", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsCraftSopMain bsCraftSopMain)
    {
        List<BsCraftSopMain> list = bsCraftSopMainService.selectBsCraftSopMainList(bsCraftSopMain);
        ExcelUtil<BsCraftSopMain> util = new ExcelUtil<BsCraftSopMain>(BsCraftSopMain.class);
        return util.exportExcel(list, "工艺标准与CCP数据");
    }

    /**
     * 新增工艺标准与CCP
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工艺标准与CCP
     */
    @RequiresPermissions("bs:craftsopmain:add")
    @Log(title = "工艺标准与CCP", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsCraftSopMain bsCraftSopMain)
    {
        return toAjax(bsCraftSopMainService.insertBsCraftSopMain(bsCraftSopMain));
    }

    /**
     * 修改工艺标准与CCP
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsCraftSopMain bsCraftSopMain = bsCraftSopMainService.selectBsCraftSopMainById(id);
        mmap.put("bsCraftSopMain", bsCraftSopMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存工艺标准与CCP
     */
    @RequiresPermissions("bs:craftsopmain:edit")
    @Log(title = "工艺标准与CCP", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsCraftSopMain bsCraftSopMain)
    {
        return toAjax(bsCraftSopMainService.updateBsCraftSopMain(bsCraftSopMain));
    }

    /**
     * 删除工艺标准与CCP
     */
    @RequiresPermissions("bs:craftsopmain:remove")
    @Log(title = "工艺标准与CCP", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsCraftSopMainService.deleteBsCraftSopMainByIds(ids));
    }
    
    @GetMapping(value = {"/toUpload/{rowIndex}"})
    public String toUpload(@PathVariable(value="rowIndex") Long rowIndex,ModelMap mmap)
    {
        mmap.put("rowIndex", rowIndex);
        return prefix + "/upload";
    }
}
