package com.saas.pssc.controller;

import java.math.BigDecimal;
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
import com.saas.pssc.domain.PpWorkYield;
import com.saas.pssc.service.IPpWorkYieldService;

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
 * 工序成品率Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/pp/workyield")
public class PpWorkYieldController extends BaseController
{
    private String prefix = "pp/workyield";

    @Autowired
    private IPpWorkYieldService ppWorkYieldService;

    @RequiresPermissions("pp:workyield:view")
    @GetMapping()
    public String workyield()
    {
        return prefix + "/workyield";
    }

    /**
     * 查询工序成品率列表
     */
    @RequiresPermissions("pp:workyield:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PpWorkYield ppWorkYield)
    {
        startPage();
        List<PpWorkYield> list = ppWorkYieldService.selectPpWorkYieldList(ppWorkYield);
        return getDataTable(list);
    }

    /**
     * 导出工序成品率列表
     */
    @RequiresPermissions("pp:workyield:export")
    @Log(title = "工序成品率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PpWorkYield ppWorkYield)
    {
        List<PpWorkYield> list = ppWorkYieldService.selectPpWorkYieldList(ppWorkYield);
        ExcelUtil<PpWorkYield> util = new ExcelUtil<PpWorkYield>(PpWorkYield.class);
        return util.exportExcel(list, "工序成品率数据");
    }

    /**
     * 新增工序成品率
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工序成品率
     */
    @RequiresPermissions("pp:workyield:add")
    @Log(title = "工序成品率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PpWorkYield ppWorkYield)
    {
        return toAjax(ppWorkYieldService.insertPpWorkYield(ppWorkYield));
    }

    /**
     * 修改工序成品率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PpWorkYield ppWorkYield = ppWorkYieldService.selectPpWorkYieldById(id);
        mmap.put("ppWorkYield", ppWorkYield);
        return prefix + "/edit";
    }

    /**
     * 修改保存工序成品率
     */
    @RequiresPermissions("pp:workyield:edit")
    @Log(title = "工序成品率", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PpWorkYield ppWorkYield)
    {
        return toAjax(ppWorkYieldService.updatePpWorkYield(ppWorkYield));
    }

    /**
     * 删除工序成品率
     */
    @RequiresPermissions("pp:workyield:remove")
    @Log(title = "工序成品率", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ppWorkYieldService.deletePpWorkYieldByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<PpWorkYield> util = new ExcelUtil<PpWorkYield>(PpWorkYield.class);
        return util.importTemplateExcel("工序成品率列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("pp:workyield:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PpWorkYield> util = new ExcelUtil<PpWorkYield>(PpWorkYield.class);
        List<PpWorkYield> yieldList = util.importExcel(file.getInputStream());
        String message = importWorkyield(yieldList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工序成品率数据
     * 
     * @param yieldList 工序成品率数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importWorkyield(List<PpWorkYield> yieldList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(yieldList) || yieldList.size() == 0)
        {
            throw new BusinessException("导入工序成品率数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PpWorkYield ppWorkYield : yieldList)
        {
            try
            {
                ppWorkYield.setIsValid("1");
                boolean lossFlag = false;
                List<PpWorkYield>  dblist= ppWorkYieldService.selectPpWorkYieldList(ppWorkYield);
                logger.info("同名工序成品率条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个工序成品率
				}
                if (!lossFlag)
                {
                    ppWorkYield.setCreateBy(ShiroUtils.getLoginName());
                    ppWorkYield.setUpdateBy(ShiroUtils.getLoginName());
                    // order.setIsValid("1");
                    ppWorkYieldService.insertPpWorkYield(ppWorkYield);//插入工序成品率
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工序成品率 " +ppWorkYield.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    ppWorkYield.setId(dblist.get(0).getId());
                    ppWorkYield.setUpdateBy(ShiroUtils.getLoginName());
                	ppWorkYieldService.updatePpWorkYield(ppWorkYield);//修改工序成品率
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工序成品率 " +ppWorkYield.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工序成品率 " +ppWorkYield.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工序成品率 " + ppWorkYield.getPname()+ " 导入失败：";
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
        PpWorkYield ppWorkYield = new PpWorkYield();
        List<PpWorkYield> qcWoYieldRateList = ppWorkYieldService.selectPpWorkYieldList(ppWorkYield);
        if(StringUtils.isNotEmpty(qcWoYieldRateList)){
            //计划工单号列表
            List<String> wCodeList =  qcWoYieldRateList.stream().map(PpWorkYield::getWcode).collect(Collectors.toList());
            map.put("wCodeList", StringUtils.isNotEmpty(wCodeList)?wCodeList.toArray():"");
            //标准成品率
            List<BigDecimal> standardYieldList = qcWoYieldRateList.stream().map(PpWorkYield::getStandardYield).collect(Collectors.toList());
            map.put("standardYieldList",StringUtils.isNotEmpty(standardYieldList)?standardYieldList.toArray():"");
            //实际成品率
            List<BigDecimal> actualYieldList = qcWoYieldRateList.stream().map(PpWorkYield::getActualYield).collect(Collectors.toList());
            map.put("actualYieldList",StringUtils.isNotEmpty(actualYieldList)?actualYieldList.toArray():"");
            
        }
        return map;
    }
}
