package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.service.IBsMcnMainService;

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

/**
 * 4M变更单Controller
 * 
 * @author admin
 * @date 2021-07-19
 */
@Controller
@RequestMapping("/bs/mcnmain")
public class BsMcnMainController extends BaseController
{
    private String prefix = "bs/mcnmain";

    @Autowired
    private IBsMcnMainService bsMcnMainService;

    @RequiresPermissions("bs:mcnmain:view")
    @GetMapping()
    public String mcnmain()
    {
        return prefix + "/mcnmain";
    }

    /**
     * 查询4M变更单列表
     */
    @RequiresPermissions("bs:mcnmain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsMcnMain bsMcnMain)
    {
        startPage();
        List<BsMcnMain> list = bsMcnMainService.selectBsMcnMainList(bsMcnMain);
        return getDataTable(list);
    }

    /**
     * 导出4M变更单列表
     */
    @RequiresPermissions("bs:mcnmain:export")
    @Log(title = "4M变更单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsMcnMain bsMcnMain)
    {
        List<BsMcnMain> list = bsMcnMainService.selectBsMcnMainList(bsMcnMain);
        ExcelUtil<BsMcnMain> util = new ExcelUtil<BsMcnMain>(BsMcnMain.class);
        return util.exportExcel(list, "4M变更单数据");
    }

    /**
     * 新增4M变更单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存4M变更单
     */
    @RequiresPermissions("bs:mcnmain:add")
    @Log(title = "4M变更单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsMcnMain bsMcnMain)
    {
        return toAjax(bsMcnMainService.insertBsMcnMain(bsMcnMain));
    }

    /**
     * 修改4M变更单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsMcnMain bsMcnMain = bsMcnMainService.selectBsMcnMainById(id);
        mmap.put("bsMcnMain", bsMcnMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存4M变更单
     */
    @RequiresPermissions("bs:mcnmain:edit")
    @Log(title = "4M变更单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsMcnMain bsMcnMain)
    {
        return toAjax(bsMcnMainService.updateBsMcnMain(bsMcnMain));
    }

    /**
     * 删除4M变更单
     */
    @RequiresPermissions("bs:mcnmain:remove")
    @Log(title = "4M变更单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsMcnMainService.deleteBsMcnMainByIds(ids));
    }

    @GetMapping(value = {"/toUpload/{rowIndex}"})
    public String toUpload(@PathVariable(value="rowIndex") Long rowIndex,ModelMap mmap)
    {
        mmap.put("rowIndex", rowIndex);
        return prefix + "/upload";
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsMcnMain> util = new ExcelUtil<BsMcnMain>(BsMcnMain.class);
        return util.importTemplateExcel("4M变更单数据");
    }

    /**
     * 导入数据
     */
    @RequiresPermissions("bs:mcnmain:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsMcnMain> util = new ExcelUtil<BsMcnMain>(BsMcnMain.class);
        List<BsMcnMain> mcnMainList = util.importExcel(file.getInputStream());
        String message = importMcnMain(mcnMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入4M变更单数据
     * 
     * @param userList 4M变更单数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importMcnMain(List<BsMcnMain> mcnMainList, Boolean isUpdateSupport)
    {
        if (StringUtils.isEmpty(mcnMainList) || mcnMainList.size() == 0)
        {
            throw new BusinessException("导入4M变更单标准数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsMcnMain bsMcnMain : mcnMainList)
        {
            try
            {
                bsMcnMain.setIsValid("1");
                boolean flag = false;
                List<BsMcnMain>  dblist= bsMcnMainService.selectBsMcnMainList(bsMcnMain);
                logger.info("同名4M变更单标准条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个4M变更单标准
				}
                if (!flag)
                {
                    bsMcnMain.setCreateBy(ShiroUtils.getLoginName());
                    bsMcnMain.setUpdateBy(ShiroUtils.getLoginName());
                    bsMcnMainService.insertBsMcnMain(bsMcnMain);//插入4M变更单标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、4M变更单标准 " +bsMcnMain.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bsMcnMain.setId(dblist.get(0).getId());
                    bsMcnMain.setUpdateBy(ShiroUtils.getLoginName());
                	bsMcnMainService.updateBsMcnMain(bsMcnMain);//修改4M变更单标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、4M变更单标准 " +bsMcnMain.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、4M变更单标准 " +bsMcnMain.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、4M变更单标准 " + bsMcnMain.getName()+ " 导入失败：";
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
