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
import org.springframework.web.multipart.MultipartFile;

import com.saas.common.annotation.Log;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.service.IBsCraftSopMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
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
    public String edit(@PathVariable("id") String id, ModelMap mmap)
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

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsCraftSopMain> util = new ExcelUtil<BsCraftSopMain>(BsCraftSopMain.class);
        return util.importTemplateExcel("工艺标准数据");
    }

    /**
     * 导入数据
     */
    @RequiresPermissions("bs:craftsopmain:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsCraftSopMain> util = new ExcelUtil<BsCraftSopMain>(BsCraftSopMain.class);
        List<BsCraftSopMain> craftSopMainList = util.importExcel(file.getInputStream());
        String message = importCraftSopMain(craftSopMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工艺标准数据
     * 
     * @param userList 工艺标准数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importCraftSopMain(List<BsCraftSopMain> craftSopMainList, Boolean isUpdateSupport)
    {
        if (StringUtils.isEmpty(craftSopMainList) || craftSopMainList.size() == 0)
        {
            throw new BusinessException("导入工艺标准数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsCraftSopMain bsCraftSopMain : craftSopMainList)
        {
            try
            {
                bsCraftSopMain.setIsValid("1");
                boolean flag = false;
                List<BsCraftSopMain>  dblist= bsCraftSopMainService.selectBsCraftSopMainList(bsCraftSopMain);
                logger.info("同名工艺标准条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个工艺标准
				}
                if (!flag)
                {
                    bsCraftSopMain.setCreateBy(ShiroUtils.getLoginName());
                    bsCraftSopMain.setUpdateBy(ShiroUtils.getLoginName());
                    bsCraftSopMainService.insertBsCraftSopMain(bsCraftSopMain);//插入工艺标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工艺标准 " +bsCraftSopMain.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bsCraftSopMain.setId(dblist.get(0).getId());
                    bsCraftSopMain.setUpdateBy(ShiroUtils.getLoginName());
                	bsCraftSopMainService.updateBsCraftSopMain(bsCraftSopMain);//修改工艺标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工艺标准 " +bsCraftSopMain.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工艺标准 " +bsCraftSopMain.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工艺标准 " + bsCraftSopMain.getName()+ " 导入失败：";
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
