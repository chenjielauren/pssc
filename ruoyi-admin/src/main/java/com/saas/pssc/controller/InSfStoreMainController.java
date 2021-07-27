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
import com.saas.pssc.domain.InSfStoreMain;
import com.saas.pssc.service.IInSfStoreMainService;

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
 * 半成品库存Controller
 * 
 * @author admin
 * @date 2021-07-27
 */
@Controller
@RequestMapping("/in/sfstoremain")
public class InSfStoreMainController extends BaseController
{
    private String prefix = "in/sfstoremain";

    @Autowired
    private IInSfStoreMainService inStoreMainService;

    @RequiresPermissions("in:sfstoremain:view")
    @GetMapping()
    public String sfstoremain()
    {
        return prefix + "/storemain";
    }

    /**
     * 查询半成品库存列表
     */
    @RequiresPermissions("in:sfstoremain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InSfStoreMain inStoreMain)
    {
        startPage();
        List<InSfStoreMain> list = inStoreMainService.selectInStoreMainList(inStoreMain);
        return getDataTable(list);
    }

    /**
     * 导出半成品库存列表
     */
    @RequiresPermissions("in:sfstoremain:export")
    @Log(title = "半成品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InSfStoreMain inStoreMain)
    {
        inStoreMain.setPtype("1");//半成品库存
        inStoreMain.setIsValid("1");
        List<InSfStoreMain> list = inStoreMainService.selectInStoreMainList(inStoreMain);
        ExcelUtil<InSfStoreMain> util = new ExcelUtil<InSfStoreMain>(InSfStoreMain.class);
        return util.exportExcel(list, "半成品库存数据");
    }

    /**
     * 新增半成品库存
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存半成品库存
     */
    @RequiresPermissions("in:sfstoremain:add")
    @Log(title = "半成品库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InSfStoreMain inStoreMain)
    {
        inStoreMain.setPtype("1");//半成品库存
        return toAjax(inStoreMainService.insertInStoreMain(inStoreMain));
    }

    /**
     * 修改半成品库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        InSfStoreMain inStoreMain = inStoreMainService.selectInStoreMainById(id);
        mmap.put("inStoreMain", inStoreMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存半成品库存
     */
    @RequiresPermissions("in:sfstoremain:edit")
    @Log(title = "半成品库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InSfStoreMain inStoreMain)
    {
        return toAjax(inStoreMainService.updateInStoreMain(inStoreMain));
    }

    /**
     * 删除半成品库存
     */
    @RequiresPermissions("in:sfstoremain:remove")
    @Log(title = "半成品库存", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(inStoreMainService.deleteInStoreMainByIds(ids));
    }

    /**
     * 下载模板
    */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<InSfStoreMain> util = new ExcelUtil<InSfStoreMain>(InSfStoreMain.class);
        return util.importTemplateExcel("半成品库存");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("in:sfstoremain:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<InSfStoreMain> util = new ExcelUtil<InSfStoreMain>(InSfStoreMain.class);
        List<InSfStoreMain> list = util.importExcel(file.getInputStream());
        String message = importInStoreMain(list, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入半成品库存数据
     * 
     * @param userList 半成品库存数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importInStoreMain(List<InSfStoreMain> list, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(list) || list.size() == 0)
        {
            throw new BusinessException("导入半成品库存数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (InSfStoreMain inStoreMain : list)
        {
            try
            {
                inStoreMain.setPtype("1");//半成品库存
                inStoreMain.setIsValid("1");
                boolean lossFlag = false;
                List<InSfStoreMain>  dblist= inStoreMainService.selectInStoreMainList(inStoreMain);
                logger.info("同名半成品库存条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个半成品库存
				}
                if (!lossFlag)
                {
                    inStoreMain.setCreateBy(ShiroUtils.getLoginName());
                    inStoreMain.setUpdateBy(ShiroUtils.getLoginName());
                    // inStoreMain.setIsValid("1");
                    inStoreMainService.insertInStoreMain(inStoreMain);//插入半成品库存
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、半成品库存 " +inStoreMain.getPcode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    inStoreMain.setId(dblist.get(0).getId());
                    inStoreMain.setUpdateBy(ShiroUtils.getLoginName());
                	inStoreMainService.updateInStoreMain(inStoreMain);//修改半成品库存
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、半成品库存 " +inStoreMain.getPcode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、半成品库存 " +inStoreMain.getPcode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、半成品库存 " + inStoreMain.getPcode()+ " 导入失败：";
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