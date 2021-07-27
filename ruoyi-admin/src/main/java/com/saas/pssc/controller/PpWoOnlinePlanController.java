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
import com.saas.pssc.domain.PpWoOnlinePlan;
import com.saas.pssc.service.IPpWoOnlinePlanService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 工单上线计划Controller
 * 
 * @author admin
 * @date 2021-07-23
 */
@Controller
@RequestMapping("/pp/onlineplan")
public class PpWoOnlinePlanController extends BaseController
{
    private String prefix = "pp/onlineplan";

    @Autowired
    private IPpWoOnlinePlanService ppWoOnlinePlanService;

    @RequiresPermissions("pp:onlineplan:view")
    @GetMapping()
    public String onlineplan()
    {
        return prefix + "/onlineplan";
    }

    /**
     * 查询工单上线计划列表
     */
    @RequiresPermissions("pp:onlineplan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PpWoOnlinePlan ppWoOnlinePlan)
    {
        startPage();
        List<PpWoOnlinePlan> list = ppWoOnlinePlanService.selectPpWoOnlinePlanList(ppWoOnlinePlan);
        return getDataTable(list);
    }

    /**
     * 导出工单上线计划列表
     */
    @RequiresPermissions("pp:onlineplan:export")
    @Log(title = "工单上线计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PpWoOnlinePlan ppWoOnlinePlan)
    {
        List<PpWoOnlinePlan> list = ppWoOnlinePlanService.selectPpWoOnlinePlanList(ppWoOnlinePlan);
        ExcelUtil<PpWoOnlinePlan> util = new ExcelUtil<PpWoOnlinePlan>(PpWoOnlinePlan.class);
        return util.exportExcel(list, "工单上线计划数据");
    }

    /**
     * 新增工单上线计划
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工单上线计划
     */
    @RequiresPermissions("pp:onlineplan:add")
    @Log(title = "工单上线计划", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PpWoOnlinePlan ppWoOnlinePlan)
    {
        return toAjax(ppWoOnlinePlanService.insertPpWoOnlinePlan(ppWoOnlinePlan));
    }

    /**
     * 修改工单上线计划
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PpWoOnlinePlan ppWoOnlinePlan = ppWoOnlinePlanService.selectPpWoOnlinePlanById(id);
        mmap.put("ppWoOnlinePlan", ppWoOnlinePlan);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单上线计划
     */
    @RequiresPermissions("pp:onlineplan:edit")
    @Log(title = "工单上线计划", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PpWoOnlinePlan ppWoOnlinePlan)
    {
        return toAjax(ppWoOnlinePlanService.updatePpWoOnlinePlan(ppWoOnlinePlan));
    }

    /**
     * 删除工单上线计划
     */
    @RequiresPermissions("pp:onlineplan:remove")
    @Log(title = "工单上线计划", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ppWoOnlinePlanService.deletePpWoOnlinePlanByIds(ids));
    }

    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<PpWoOnlinePlan> util = new ExcelUtil<PpWoOnlinePlan>(PpWoOnlinePlan.class);
        return util.importTemplateExcel("工单上线计划列表");
    }
    /**
     * 导入数据
     */
    @RequiresPermissions("pp:onlineplan:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PpWoOnlinePlan> util = new ExcelUtil<PpWoOnlinePlan>(PpWoOnlinePlan.class);
        List<PpWoOnlinePlan> orderList = util.importExcel(file.getInputStream());
        String message = importOnlineplan(orderList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工单上线计划数据
     * 
     * @param orderList 工单上线计划数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importOnlineplan(List<PpWoOnlinePlan> orderList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(orderList) || orderList.size() == 0)
        {
            throw new BusinessException("导入工单上线计划数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PpWoOnlinePlan ppWoOnlinePlan : orderList)
        {
            try
            {
                ppWoOnlinePlan.setIsValid("1");
                boolean lossFlag = false;
                List<PpWoOnlinePlan>  dblist= ppWoOnlinePlanService.selectPpWoOnlinePlanList(ppWoOnlinePlan);
                logger.info("同名工单上线计划条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个工单上线计划
				}
                if (!lossFlag)
                {
                    ppWoOnlinePlan.setCreateBy(ShiroUtils.getLoginName());
                    ppWoOnlinePlan.setUpdateBy(ShiroUtils.getLoginName());
                    ppWoOnlinePlan.setIsValid("1");
                    ppWoOnlinePlanService.insertPpWoOnlinePlan(ppWoOnlinePlan) ;//插入工单上线计划
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工单上线计划 " +ppWoOnlinePlan.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    ppWoOnlinePlan.setId(dblist.get(0).getId());
                    ppWoOnlinePlan.setUpdateBy(ShiroUtils.getLoginName());
                	ppWoOnlinePlanService.updatePpWoOnlinePlan(ppWoOnlinePlan);//修改工单上线计划
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工单上线计划 " +ppWoOnlinePlan.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工单上线计划 " +ppWoOnlinePlan.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工单上线计划 " + ppWoOnlinePlan.getPname()+ " 导入失败：";
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