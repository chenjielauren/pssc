package com.saas.pssc.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.saas.common.annotation.Log;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.pssc.domain.PpProduceUnusual;
import com.saas.pssc.service.IPpProduceUnusualService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 生产异常记录Controller
 * 
 * @author admin
 * @date 2021-07-23
 */
@Controller
@RequestMapping("/pp/prounusual")
public class PpProduceUnusualController extends BaseController
{
    private String prefix = "pp/prounusual";

    @Autowired
    private IPpProduceUnusualService ppProduceUnusualService;

    @RequiresPermissions("pp:prounusual:view")
    @GetMapping()
    public String prounusual()
    {
        return prefix + "/prounusual";
    }

    /**
     * 查询生产异常记录列表
     */
    @RequiresPermissions("pp:prounusual:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PpProduceUnusual ppProduceUnusual)
    {
        startPage();
        List<PpProduceUnusual> list = ppProduceUnusualService.selectPpProduceUnusualList(ppProduceUnusual);
        return getDataTable(list);
    }

    /**
     * 导出生产异常记录列表
     */
    @RequiresPermissions("pp:prounusual:export")
    @Log(title = "生产异常记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PpProduceUnusual ppProduceUnusual)
    {
        List<PpProduceUnusual> list = ppProduceUnusualService.selectPpProduceUnusualList(ppProduceUnusual);
        ExcelUtil<PpProduceUnusual> util = new ExcelUtil<PpProduceUnusual>(PpProduceUnusual.class);
        return util.exportExcel(list, "生产异常记录数据");
    }

    /**
     * 新增生产异常记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产异常记录
     */
    @RequiresPermissions("pp:prounusual:add")
    @Log(title = "生产异常记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PpProduceUnusual ppProduceUnusual)
    {
        return toAjax(ppProduceUnusualService.insertPpProduceUnusual(ppProduceUnusual));
    }

    /**
     * 修改生产异常记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PpProduceUnusual ppProduceUnusual = ppProduceUnusualService.selectPpProduceUnusualById(id);
        mmap.put("ppProduceUnusual", ppProduceUnusual);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产异常记录
     */
    @RequiresPermissions("pp:prounusual:edit")
    @Log(title = "生产异常记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PpProduceUnusual ppProduceUnusual)
    {
        return toAjax(ppProduceUnusualService.updatePpProduceUnusual(ppProduceUnusual));
    }

    /**
     * 删除生产异常记录
     */
    @RequiresPermissions("pp:prounusual:remove")
    @Log(title = "生产异常记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ppProduceUnusualService.deletePpProduceUnusualByIds(ids));
    }
    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<PpProduceUnusual> util = new ExcelUtil<PpProduceUnusual>(PpProduceUnusual.class);
        return util.importTemplateExcel("工单异常记录列表");
    }
    /**
     * 导入数据
     */
    @RequiresPermissions("pp:prounusual:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PpProduceUnusual> util = new ExcelUtil<PpProduceUnusual>(PpProduceUnusual.class);
        List<PpProduceUnusual> unusualList = util.importExcel(file.getInputStream());
        String message = importProunusual(unusualList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工单异常记录数据
     * 
     * @param unusualList 工单异常记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importProunusual(List<PpProduceUnusual> unusualList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(unusualList) || unusualList.size() == 0)
        {
            throw new BusinessException("导入工单异常记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PpProduceUnusual ppProduceUnusual : unusualList)
        {
            try
            {
                ppProduceUnusual.setIsValid("1");
                boolean lossFlag = false;
                List<PpProduceUnusual>  dblist= ppProduceUnusualService.selectPpProduceUnusualList(ppProduceUnusual);
                logger.info("同名工单异常记录条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个工单异常记录
				}
                if (!lossFlag)
                {
                    ppProduceUnusual.setCreateBy(ShiroUtils.getLoginName());
                    ppProduceUnusual.setUpdateBy(ShiroUtils.getLoginName());
                    ppProduceUnusual.setIsValid("1");
                    ppProduceUnusualService.insertPpProduceUnusual(ppProduceUnusual);//插入工单异常记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工单异常记录 " +ppProduceUnusual.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    ppProduceUnusual.setId(dblist.get(0).getId());
                    ppProduceUnusual.setUpdateBy(ShiroUtils.getLoginName());
                	ppProduceUnusualService.updatePpProduceUnusual(ppProduceUnusual);//修改工单异常记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工单异常记录 " +ppProduceUnusual.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工单异常记录 " +ppProduceUnusual.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工单异常记录 " + ppProduceUnusual.getPname()+ " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
