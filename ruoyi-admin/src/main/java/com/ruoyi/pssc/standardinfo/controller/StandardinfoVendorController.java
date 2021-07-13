package com.ruoyi.pssc.standardinfo.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pssc.standardinfo.domain.StandardinfoVendor;
import com.ruoyi.pssc.standardinfo.service.IStandardinfoVendorService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
 * 合格供方名录Controller
 * 
 * @author lauren
 * @date 2021-07-05
 */
@Controller
@RequestMapping("/standardinfo/vendor")
public class StandardinfoVendorController extends BaseController
{
    private String prefix = "standardinfo/vendor";

    @Autowired
    private IStandardinfoVendorService standardinfoVendorService;

    @RequiresPermissions("standardinfo:vendor:view")
    @GetMapping()
    public String vendor()
    {
        return prefix + "/vendor";
    }

    /**
     * 查询合格供方名录列表
     */
    @RequiresPermissions("standardinfo:vendor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StandardinfoVendor standardinfoVendor)
    {
        startPage();
        List<StandardinfoVendor> list = standardinfoVendorService.selectStandardinfoVendorList(standardinfoVendor);
        return getDataTable(list);
    }

    /**
     * 导出合格供方名录列表
     */
    @RequiresPermissions("standardinfo:vendor:export")
    @Log(title = "合格供方名录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StandardinfoVendor standardinfoVendor)
    {
        List<StandardinfoVendor> list = standardinfoVendorService.selectStandardinfoVendorList(standardinfoVendor);
        ExcelUtil<StandardinfoVendor> util = new ExcelUtil<StandardinfoVendor>(StandardinfoVendor.class);
        return util.exportExcel(list, "合格供方名录数据");
    }

    /**
     * 新增合格供方名录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合格供方名录
     */
    @RequiresPermissions("standardinfo:vendor:add")
    @Log(title = "合格供方名录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StandardinfoVendor standardinfoVendor)
    {
        return toAjax(standardinfoVendorService.insertStandardinfoVendor(standardinfoVendor));
    }

    /**
     * 修改合格供方名录
     */
    @GetMapping("/edit/{vendorId}")
    public String edit(@PathVariable("vendorId") Long vendorId, ModelMap mmap)
    {
        StandardinfoVendor standardinfoVendor = standardinfoVendorService.selectStandardinfoVendorById(vendorId);
        mmap.put("standardinfoVendor", standardinfoVendor);
        return prefix + "/edit";
    }

    /**
     * 修改保存合格供方名录
     */
    @RequiresPermissions("standardinfo:vendor:edit")
    @Log(title = "合格供方名录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StandardinfoVendor standardinfoVendor)
    {
        return toAjax(standardinfoVendorService.updateStandardinfoVendor(standardinfoVendor));
    }

    /**
     * 删除合格供方名录
     */
    @RequiresPermissions("standardinfo:vendor:remove")
    @Log(title = "合格供方名录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(standardinfoVendorService.deleteStandardinfoVendorByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<StandardinfoVendor> util = new ExcelUtil<StandardinfoVendor>(StandardinfoVendor.class);
        return util.importTemplateExcel("合格供方目录数据");
    }
    
    
    /**
     * 导入数据
     */
    @RequiresPermissions("standardinfo:vendor:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<StandardinfoVendor> util = new ExcelUtil<StandardinfoVendor>(StandardinfoVendor.class);
        List<StandardinfoVendor> vendorList = util.importExcel(file.getInputStream());
        String message = importVendor(vendorList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importVendor(List<StandardinfoVendor> vendorList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(vendorList) || vendorList.size() == 0)
        {
            throw new BusinessException("导入合格供方目录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (StandardinfoVendor vendor : vendorList)
        {
            try
            {
                boolean vendorFlag = false;
                // StandardinfoVendor d=new StandardinfoVendor();
                // d.setVendorName(vendor.getVendorName());
                // d.setVendorNumber(vendor.getVendorNumber());
                List<StandardinfoVendor>  dblist= standardinfoVendorService.selectStandardinfoVendorList(vendor);
                logger.info("同名合格供方目录条数："+dblist.size());
                if (dblist.size()>0) {
                	vendorFlag = true;  // 验证是否存在这个合格供方目录
				}
                if (!vendorFlag)
                {
                    // StandardinfoVendor sdv=new StandardinfoVendor();
                    vendor.setCreateBy(ShiroUtils.getLoginName());
                    vendor.setUpdateBy(ShiroUtils.getLoginName());
                    vendor.setUpdateTime(new Date());
                    standardinfoVendorService.insertStandardinfoVendor(vendor);//插入合格供方目录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合格供方目录 " +vendor.getVendorName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    vendor.setVendorId(dblist.get(0).getVendorId());
                    vendor.setUpdateBy(ShiroUtils.getLoginName());
                    vendor.setUpdateTime(new Date());
                	standardinfoVendorService.updateStandardinfoVendor(vendor);//修改合格供方目录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合格供方目录 " +vendor.getVendorName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、合格供方目录 " +vendor.getVendorName()  + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合格供方目录 " + vendor.getVendorName()+ " 导入失败：";
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
