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
import com.saas.pssc.domain.BsVendor;
import com.saas.pssc.service.IBsVendorService;

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
 * 合格供方名录Controller
 * 
 * @author admin
 * @date 2021-07-15
 */
@Controller
@RequestMapping("/bs/vendor")
public class BsVendorController extends BaseController
{
    private String prefix = "bs/vendor";

    @Autowired
    private IBsVendorService bsVendorService;

    @RequiresPermissions("bs:vendor:view")
    @GetMapping()
    public String vendor()
    {
        return prefix + "/vendor";
    }

    /**
     * 查询合格供方名录列表
     */
    @RequiresPermissions("bs:vendor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsVendor bsVendor)
    {
        startPage();
        List<BsVendor> list = bsVendorService.selectBsVendorList(bsVendor);
        return getDataTable(list);
    }

    /**
     * 导出合格供方名录列表
     */
    @RequiresPermissions("bs:vendor:export")
    @Log(title = "合格供方名录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsVendor bsVendor)
    {
        List<BsVendor> list = bsVendorService.selectBsVendorList(bsVendor);
        ExcelUtil<BsVendor> util = new ExcelUtil<BsVendor>(BsVendor.class);
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
    @RequiresPermissions("bs:vendor:add")
    @Log(title = "合格供方名录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsVendor bsVendor)
    {
        return toAjax(bsVendorService.insertBsVendor(bsVendor));
    }

    /**
     * 修改合格供方名录
     */
    @GetMapping("/edit/{Id}")
    public String edit(@PathVariable("Id") Long Id, ModelMap mmap)
    {
        BsVendor bsVendor = bsVendorService.selectBsVendorById(Id);
        mmap.put("bsVendor", bsVendor);
        return prefix + "/edit";
    }

    /**
     * 修改保存合格供方名录
     */
    @RequiresPermissions("bs:vendor:edit")
    @Log(title = "合格供方名录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsVendor bsVendor)
    {
        return toAjax(bsVendorService.updateBsVendor(bsVendor));
    }

    /**
     * 删除合格供方名录
     */
    @RequiresPermissions("bs:vendor:remove")
    @Log(title = "合格供方名录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsVendorService.deleteBsVendorByIds(ids));
    }

     /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsVendor> util = new ExcelUtil<BsVendor>(BsVendor.class);
        return util.importTemplateExcel("合格供方目录数据");
    }
    
    
    /**
     * 导入数据
     */
    @RequiresPermissions("bs:vendor:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsVendor> util = new ExcelUtil<BsVendor>(BsVendor.class);
        List<BsVendor> vendorList = util.importExcel(file.getInputStream());
        String message = importVendor(vendorList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入供应商数据
     * 
     * @param userList 供应商数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importVendor(List<BsVendor> vendorList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(vendorList) || vendorList.size() == 0)
        {
            throw new BusinessException("导入合格供方目录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsVendor vendor : vendorList)
        {
            try
            {
                boolean vendorFlag = false;
                vendor.setIsValid("1");
                // BsVendor d=new BsVendor();
                // d.setVendorName(vendor.getName());
                // d.setVendorNumber(vendor.getVendorNumber());
                List<BsVendor>  dblist= bsVendorService.selectBsVendorList(vendor);
                logger.info("同名合格供方目录条数："+dblist.size());
                if (dblist.size()>0) {
                	vendorFlag = true;  // 验证是否存在这个合格供方目录
				}
                if (!vendorFlag)
                {
                    // BsVendor sdv=new BsVendor();
                    vendor.setCreateBy(ShiroUtils.getLoginName());
                    vendor.setUpdateBy(ShiroUtils.getLoginName());
                    bsVendorService.insertBsVendor(vendor);//插入合格供方目录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合格供方目录 " +vendor.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    vendor.setId(dblist.get(0).getId());
                    vendor.setUpdateBy(ShiroUtils.getLoginName());
                	bsVendorService.updateBsVendor(vendor);//修改合格供方目录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合格供方目录 " +vendor.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、合格供方目录 " +vendor.getName()  + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合格供方目录 " + vendor.getName()+ " 导入失败：";
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
