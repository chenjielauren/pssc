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
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.service.ISdDeliveryService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 发货信息Controller
 * 
 * @author admin
 * @date 2021-08-01
 */
@Controller
@RequestMapping("/sd/delivery")
public class SdDeliveryController extends BaseController
{
    private String prefix = "sd/delivery";

    @Autowired
    private ISdDeliveryService sdDeliveryService;

    @RequiresPermissions("sd:delivery:view")
    @GetMapping()
    public String delivery()
    {
        return prefix + "/delivery";
    }

    /**
     * 查询发货信息列表
     */
    @RequiresPermissions("sd:delivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SdDelivery sdDelivery)
    {
        startPage();
        List<SdDelivery> list = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        return getDataTable(list);
    }

    /**
     * 导出发货信息列表
     */
    @RequiresPermissions("sd:delivery:export")
    @Log(title = "发货信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SdDelivery sdDelivery)
    {
        List<SdDelivery> list = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        ExcelUtil<SdDelivery> util = new ExcelUtil<SdDelivery>(SdDelivery.class);
        return util.exportExcel(list, "发货信息数据");
    }

    /**
     * 新增发货信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存发货信息
     */
    @RequiresPermissions("sd:delivery:add")
    @Log(title = "发货信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SdDelivery sdDelivery)
    {
        return toAjax(sdDeliveryService.insertSdDelivery(sdDelivery));
    }

    /**
     * 修改发货信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        SdDelivery sdDelivery = sdDeliveryService.selectSdDeliveryById(id);
        mmap.put("sdDelivery", sdDelivery);
        return prefix + "/edit";
    }

    /**
     * 修改保存发货信息
     */
    @RequiresPermissions("sd:delivery:edit")
    @Log(title = "发货信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SdDelivery sdDelivery)
    {
        return toAjax(sdDeliveryService.updateSdDelivery(sdDelivery));
    }

    /**
     * 删除发货信息
     */
    @RequiresPermissions("sd:delivery:remove")
    @Log(title = "发货信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sdDeliveryService.deleteSdDeliveryByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SdDelivery> util = new ExcelUtil<SdDelivery>(SdDelivery.class);
        return util.importTemplateExcel("发货单列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("sd:delivery:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SdDelivery> util = new ExcelUtil<SdDelivery>(SdDelivery.class);
        List<SdDelivery> orderList = util.importExcel(file.getInputStream());
        String message = importDelivery(orderList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入发货单数据
     * 
     * @param userList 发货单数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importDelivery(List<SdDelivery> orderList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(orderList) || orderList.size() == 0)
        {
            throw new BusinessException("导入发货单数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SdDelivery sdDelivery : orderList)
        {
            try
            {
                sdDelivery.setIsValid("1");
                boolean lossFlag = false;
                List<SdDelivery>  dblist= sdDeliveryService.selectSdDeliveryList(sdDelivery);
                logger.info("同名发货单条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个发货单
				}
                if (!lossFlag)
                {
                    sdDelivery.setCreateBy(ShiroUtils.getLoginName());
                    sdDelivery.setUpdateBy(ShiroUtils.getLoginName());
                    // sdDelivery.setIsValid("1");
                    sdDeliveryService.insertSdDelivery(sdDelivery);//插入发货单
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、发货单 " +sdDelivery.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    sdDelivery.setId(dblist.get(0).getId());
                    sdDelivery.setUpdateBy(ShiroUtils.getLoginName());
                	sdDeliveryService.updateSdDelivery(sdDelivery);//修改发货单
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、发货单 " +sdDelivery.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、发货单 " +sdDelivery.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、发货单 " + sdDelivery.getPname()+ " 导入失败：";
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
