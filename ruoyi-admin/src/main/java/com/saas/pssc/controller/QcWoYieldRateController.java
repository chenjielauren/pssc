package com.saas.pssc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.QcWoYieldRate;
import com.saas.pssc.service.IQcWoYieldRateService;

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
 * 工单良品率分析Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/yieldrate")
public class QcWoYieldRateController extends BaseController
{
    private String prefix = "qc/yieldrate";

    @Autowired
    private IQcWoYieldRateService qcWoYieldRateService;

    @RequiresPermissions("qc:yieldrate:view")
    @GetMapping()
    public String yieldrate()
    {
        return prefix + "/yieldrate";
    }

    /**
     * 查询工单良品率分析列表
     */
    @RequiresPermissions("qc:yieldrate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcWoYieldRate qcWoYieldRate)
    {
        startPage();
        List<QcWoYieldRate> list = qcWoYieldRateService.selectQcWoYieldRateList(qcWoYieldRate);
        return getDataTable(list);
    }

    /**
     * 导出工单良品率分析列表
     */
    @RequiresPermissions("qc:yieldrate:export")
    @Log(title = "工单良品率分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcWoYieldRate qcWoYieldRate)
    {
        List<QcWoYieldRate> list = qcWoYieldRateService.selectQcWoYieldRateList(qcWoYieldRate);
        ExcelUtil<QcWoYieldRate> util = new ExcelUtil<QcWoYieldRate>(QcWoYieldRate.class);
        return util.exportExcel(list, "工单良品率分析数据");
    }

    /**
     * 新增工单良品率分析
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工单良品率分析
     */
    @RequiresPermissions("qc:yieldrate:add")
    @Log(title = "工单良品率分析", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcWoYieldRate qcWoYieldRate)
    {
        return toAjax(qcWoYieldRateService.insertQcWoYieldRate(qcWoYieldRate));
    }

    /**
     * 修改工单良品率分析
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcWoYieldRate qcWoYieldRate = qcWoYieldRateService.selectQcWoYieldRateById(id);
        mmap.put("qcWoYieldRate", qcWoYieldRate);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单良品率分析
     */
    @RequiresPermissions("qc:yieldrate:edit")
    @Log(title = "工单良品率分析", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcWoYieldRate qcWoYieldRate)
    {
        return toAjax(qcWoYieldRateService.updateQcWoYieldRate(qcWoYieldRate));
    }

    /**
     * 删除工单良品率分析
     */
    @RequiresPermissions("qc:yieldrate:remove")
    @Log(title = "工单良品率分析", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcWoYieldRateService.deleteQcWoYieldRateByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcWoYieldRate> util = new ExcelUtil<QcWoYieldRate>(QcWoYieldRate.class);
        return util.importTemplateExcel("工单良品率分析列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:yieldrate:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcWoYieldRate> util = new ExcelUtil<QcWoYieldRate>(QcWoYieldRate.class);
        List<QcWoYieldRate> yieldRateList = util.importExcel(file.getInputStream());
        String message = importYieldRate(yieldRateList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工单良品率分析数据
     * 
     * @param yieldRateList 工单良品率分析数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importYieldRate(List<QcWoYieldRate> yieldRateList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(yieldRateList) || yieldRateList.size() == 0)
        {
            throw new BusinessException("导入工单良品率分析数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QcWoYieldRate qcWoYieldRate : yieldRateList)
        {
            try
            {
                qcWoYieldRate.setIsValid("1");
                boolean flag = false;
                List<QcWoYieldRate>  dblist= qcWoYieldRateService.selectQcWoYieldRateList(qcWoYieldRate);
                logger.info("同名工单良品率分析条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个工单良品率分析
				}
                if (!flag)
                {
                    qcWoYieldRate.setCreateBy(ShiroUtils.getLoginName());
                    qcWoYieldRate.setUpdateBy(ShiroUtils.getLoginName());
                    // qcWoYieldRate.setIsValid("1");
                    qcWoYieldRateService.insertQcWoYieldRate(qcWoYieldRate);//插入工单良品率分析
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、成品编号 " +qcWoYieldRate.getPcode() + " 成品名称 " +qcWoYieldRate.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    qcWoYieldRate.setId(dblist.get(0).getId());
                    qcWoYieldRate.setUpdateBy(ShiroUtils.getLoginName());
                	qcWoYieldRateService.updateQcWoYieldRate(qcWoYieldRate);//修改工单良品率分析
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、成品编号 " +qcWoYieldRate.getPcode() + " 成品名称 " +qcWoYieldRate.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、成品编号 " +qcWoYieldRate.getPcode() + " 成品名称 " +qcWoYieldRate.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、成品编号 " +qcWoYieldRate.getPcode() + " 成品名称 " +qcWoYieldRate.getPname() + " 导入失败：";
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

    // 加载折线图
	@PostMapping("/loadLineChart")
	@ResponseBody
	public Map<String, Object> loadLineChart() {
        Map<String, Object> map = new HashMap<String, Object>();
        QcWoYieldRate qcWoYieldRate = new QcWoYieldRate();
        List<QcWoYieldRate> qcWoYieldRateList = qcWoYieldRateService.selectQcWoYieldRateList(qcWoYieldRate);
        if(StringUtils.isNotEmpty(qcWoYieldRateList)){
            //计划工单号列表
            List<String> wCodeList =  qcWoYieldRateList.stream().map(QcWoYieldRate::getWo).collect(Collectors.toList());
            map.put("wCodeList", StringUtils.isNotEmpty(wCodeList)?wCodeList.toArray():"");
            //标准成品率
            List<Long> standardYieldList = qcWoYieldRateList.stream().map(QcWoYieldRate::getIqty).collect(Collectors.toList());
            map.put("standardYieldList",StringUtils.isNotEmpty(standardYieldList)?standardYieldList.toArray():"");
            //实际成品率
            List<Long> actualYieldList = qcWoYieldRateList.stream().map(QcWoYieldRate::getOqty).collect(Collectors.toList());
            map.put("actualYieldList",StringUtils.isNotEmpty(actualYieldList)?actualYieldList.toArray():"");
        }
        return map;
    }
}
