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
import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.service.IBsBomMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * bom物料清单Controller
 * 
 * @author admin
 * @date 2021-07-19
 */
@Controller
@RequestMapping("/bs/bommain")
public class BsBomMainController extends BaseController
{
    private String prefix = "bs/bommain";

    @Autowired
    private IBsBomMainService bsBomMainService;

    @RequiresPermissions("bs:bommain:view")
    @GetMapping()
    public String bommain()
    {
        return prefix + "/bommain";
    }

    /**
     * 查询bom物料清单列表
     */
    @RequiresPermissions("bs:bommain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsBomMain bsBomMain)
    {
        startPage();
        List<BsBomMain> list = bsBomMainService.selectBsBomMainList(bsBomMain);
        return getDataTable(list);
    }

    /**
     * 导出bom物料清单列表
     */
    @RequiresPermissions("bs:bommain:export")
    @Log(title = "bom物料清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsBomMain bsBomMain)
    {
        List<BsBomMain> list = bsBomMainService.selectBsBomMainList(bsBomMain);
        ExcelUtil<BsBomMain> util = new ExcelUtil<BsBomMain>(BsBomMain.class);
        return util.exportExcel(list, "bom物料清单数据");
    }

    /**
     * 新增bom物料清单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存bom物料清单
     */
    @RequiresPermissions("bs:bommain:add")
    @Log(title = "bom物料清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsBomMain bsBomMain)
    {
        return toAjax(bsBomMainService.insertBsBomMain(bsBomMain));
    }

    /**
     * 修改bom物料清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsBomMain bsBomMain = bsBomMainService.selectBsBomMainById(id);
        mmap.put("bsBomMain", bsBomMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存bom物料清单
     */
    @RequiresPermissions("bs:bommain:edit")
    @Log(title = "bom物料清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsBomMain bsBomMain)
    {
        return toAjax(bsBomMainService.updateBsBomMain(bsBomMain));
    }

    /**
     * 删除bom物料清单
     */
    @RequiresPermissions("bs:bommain:remove")
    @Log(title = "bom物料清单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsBomMainService.deleteBsBomMainByIds(ids));
    }
}
