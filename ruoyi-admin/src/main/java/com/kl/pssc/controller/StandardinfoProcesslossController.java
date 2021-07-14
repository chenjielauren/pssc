package com.kl.pssc.controller;

import java.util.List;

import com.kl.common.annotation.Log;
import com.kl.common.core.controller.BaseController;
import com.kl.common.core.domain.AjaxResult;
import com.kl.common.core.page.TableDataInfo;
import com.kl.common.enums.BusinessType;
import com.kl.common.exception.BusinessException;
import com.kl.common.utils.ShiroUtils;
import com.kl.common.utils.poi.ExcelUtil;
import com.kl.pssc.domain.StandardinfoProcessloss;
import com.kl.pssc.service.IStandardinfoProcesslossService;

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

/**
 * 工序损耗标准Controller
 * 
 * @author ruoyi
 * @date 2021-07-14
 */
@Controller
@RequestMapping("/standardinfo/processloss")
public class StandardinfoProcesslossController extends BaseController
{
    private String prefix = "standardinfo/processloss";

    @Autowired
    private IStandardinfoProcesslossService standardinfoProcesslossService;

    @RequiresPermissions("standardinfo:processloss:view")
    @GetMapping()
    public String processloss()
    {
        return prefix + "/processloss";
    }

    /**
     * 查询工序损耗标准列表
     */
    @RequiresPermissions("standardinfo:processloss:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StandardinfoProcessloss standardinfoProcessloss)
    {
        startPage();
        List<StandardinfoProcessloss> list = standardinfoProcesslossService.selectStandardinfoProcesslossList(standardinfoProcessloss);
        return getDataTable(list);
    }

    /**
     * 导出工序损耗标准列表
     */
    @RequiresPermissions("standardinfo:processloss:export")
    @Log(title = "工序损耗标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StandardinfoProcessloss standardinfoProcessloss)
    {
        List<StandardinfoProcessloss> list = standardinfoProcesslossService.selectStandardinfoProcesslossList(standardinfoProcessloss);
        ExcelUtil<StandardinfoProcessloss> util = new ExcelUtil<StandardinfoProcessloss>(StandardinfoProcessloss.class);
        return util.exportExcel(list, "工序损耗标准数据");
    }

    /**
     * 新增工序损耗标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工序损耗标准
     */
    @RequiresPermissions("standardinfo:processloss:add")
    @Log(title = "工序损耗标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StandardinfoProcessloss standardinfoProcessloss)
    {
        return toAjax(standardinfoProcesslossService.insertStandardinfoProcessloss(standardinfoProcessloss));
    }

    /**
     * 修改工序损耗标准
     */
    @GetMapping("/edit/{processlossId}")
    public String edit(@PathVariable("processlossId") Long processlossId, ModelMap mmap)
    {
        StandardinfoProcessloss standardinfoProcessloss = standardinfoProcesslossService.selectStandardinfoProcesslossById(processlossId);
        mmap.put("standardinfoProcessloss", standardinfoProcessloss);
        return prefix + "/edit";
    }

    /**
     * 修改保存工序损耗标准
     */
    @RequiresPermissions("standardinfo:processloss:edit")
    @Log(title = "工序损耗标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StandardinfoProcessloss standardinfoProcessloss)
    {
        return toAjax(standardinfoProcesslossService.updateStandardinfoProcessloss(standardinfoProcessloss));
    }

    /**
     * 删除工序损耗标准
     */
    @RequiresPermissions("standardinfo:processloss:remove")
    @Log(title = "工序损耗标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(standardinfoProcesslossService.deleteStandardinfoProcesslossByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<StandardinfoProcessloss> util = new ExcelUtil<StandardinfoProcessloss>(StandardinfoProcessloss.class);
        return util.importTemplateExcel("工序损耗标准数据");
    }
    
    
    /**
     * 导入数据
     */
    @RequiresPermissions("standardinfo:processloss:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<StandardinfoProcessloss> util = new ExcelUtil<StandardinfoProcessloss>(StandardinfoProcessloss.class);
        List<StandardinfoProcessloss> lossList = util.importExcel(file.getInputStream());
        String message = importVendor(lossList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importVendor(List<StandardinfoProcessloss> lossList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(lossList) || lossList.size() == 0)
        {
            throw new BusinessException("导入工序损耗标准数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (StandardinfoProcessloss loss : lossList)
        {
            try
            {
                boolean lossFlag = false;
                List<StandardinfoProcessloss>  dblist= standardinfoProcesslossService.selectStandardinfoProcesslossList(loss);
                logger.info("同名工序损耗标准条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个工序损耗标准
				}
                if (!lossFlag)
                {
                    loss.setCreateBy(ShiroUtils.getLoginName());
                    loss.setUpdateBy(ShiroUtils.getLoginName());
                    standardinfoProcesslossService.insertStandardinfoProcessloss(loss);//插入工序损耗标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工序损耗标准 " +loss.getProcessName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    loss.setProcesslossId(dblist.get(0).getProcesslossId());
                    loss.setUpdateBy(ShiroUtils.getLoginName());
                	standardinfoProcesslossService.updateStandardinfoProcessloss(loss);//修改工序损耗标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工序损耗标准 " +loss.getProcessName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工序损耗标准 " +loss.getProcessName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工序损耗标准 " + loss.getProcessName()+ " 导入失败：";
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
