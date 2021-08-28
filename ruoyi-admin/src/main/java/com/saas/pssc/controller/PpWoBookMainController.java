package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.service.IPpWoBookMainService;

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
 * 工单记录Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/pp/wbmain")
public class PpWoBookMainController extends BaseController
{
    private String prefix = "pp/wbmain";

    @Autowired
    private IPpWoBookMainService ppWoBookMainService;

    @RequiresPermissions("pp:wbmain:view")
    @GetMapping()
    public String wbmain()
    {
        return prefix + "/wbmain";
    }

    /**
     * 查询工单记录列表
     */
    @RequiresPermissions("pp:wbmain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PpWoBookMain ppWoBookMain)
    {
        startPage();
        List<PpWoBookMain> list = ppWoBookMainService.selectPpWoBookMainList(ppWoBookMain);
        return getDataTable(list);
    }

    /**
     * 导出工单记录列表
     */
    @RequiresPermissions("pp:wbmain:export")
    @Log(title = "工单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PpWoBookMain ppWoBookMain)
    {
        List<PpWoBookMain> list = ppWoBookMainService.selectPpWoBookMainList(ppWoBookMain);
        ExcelUtil<PpWoBookMain> util = new ExcelUtil<PpWoBookMain>(PpWoBookMain.class);
        return util.exportExcel(list, "工单记录数据");
    }

    /**
     * 新增工单记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工单记录
     */
    @RequiresPermissions("pp:wbmain:add")
    @Log(title = "工单记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PpWoBookMain ppWoBookMain)
    {
        return toAjax(ppWoBookMainService.insertPpWoBookMain(ppWoBookMain));
    }

    /**
     * 修改工单记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        PpWoBookMain ppWoBookMain = ppWoBookMainService.selectPpWoBookMainById(id);
        mmap.put("ppWoBookMain", ppWoBookMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单记录
     */
    @RequiresPermissions("pp:wbmain:edit")
    @Log(title = "工单记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PpWoBookMain ppWoBookMain)
    {
        return toAjax(ppWoBookMainService.updatePpWoBookMain(ppWoBookMain));
    }

    /**
     * 删除工单记录
     */
    @RequiresPermissions("pp:wbmain:remove")
    @Log(title = "工单记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ppWoBookMainService.deletePpWoBookMainByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<PpWoBookMain> util = new ExcelUtil<PpWoBookMain>(PpWoBookMain.class);
        return util.importTemplateExcel("工单记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:matcm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PpWoBookMain> util = new ExcelUtil<PpWoBookMain>(PpWoBookMain.class);
        List<PpWoBookMain> ppWoBookMainList = util.importExcel(file.getInputStream());
        String message = importPpWoBookMain(ppWoBookMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工单记录数据
     * 
     * @param userList 工单记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importPpWoBookMain(List<PpWoBookMain> ppWoBookMainList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(ppWoBookMainList) || ppWoBookMainList.size() == 0)
        {
            throw new BusinessException("导入工单记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PpWoBookMain ppWoBookMain : ppWoBookMainList)
        {
            try
            {
                ppWoBookMain.setIsValid("1");
                boolean lossFlag = false;
                List<PpWoBookMain>  dblist= ppWoBookMainService.selectPpWoBookMainList(ppWoBookMain);
                logger.info("同名工单记录条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个工单记录
				}
                if (!lossFlag)
                {
                    ppWoBookMain.setCreateBy(ShiroUtils.getLoginName());
                    ppWoBookMain.setUpdateBy(ShiroUtils.getLoginName());
                    // ppWoBookMain.setIsValid("1");
                    ppWoBookMainService.insertPpWoBookMain(ppWoBookMain);//插入工单记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品编号 " +ppWoBookMain.getPcode() + " 产品名称" +ppWoBookMain.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    ppWoBookMain.setId(dblist.get(0).getId());
                    ppWoBookMain.setUpdateBy(ShiroUtils.getLoginName());
                	ppWoBookMainService.updatePpWoBookMain(ppWoBookMain);//修改工单记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品编号 " +ppWoBookMain.getPcode() + " 产品名称" +ppWoBookMain.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品编号 " +ppWoBookMain.getPcode() + " 产品名称" +ppWoBookMain.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品编号 " +ppWoBookMain.getPcode() + " 产品名称" +ppWoBookMain.getPname()+ " 导入失败：";
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

    /**
     * 校验产品编号和产品名称唯一
     */
    @PostMapping("/checkPcodeUnique")
    @ResponseBody
    public String checkPcodeUnique(PpWoBookMain ppWoBookMain)
    {
        return ppWoBookMainService.checkPcodeUnique(ppWoBookMain);
    }
}
