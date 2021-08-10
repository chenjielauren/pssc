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
import com.saas.pssc.domain.SdOrder;
import com.saas.pssc.service.ISdOrderService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 订单信息Controller
 * 
 * @author admin
 * @date 2021-07-22
 */
@Controller
@RequestMapping("/sd/order")
public class SdOrderController extends BaseController
{
    private String prefix = "sd/order";

    @Autowired
    private ISdOrderService sdOrderService;

    @RequiresPermissions("sd:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单信息列表
     */
    @RequiresPermissions("sd:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SdOrder sdOrder)
    {
        startPage();
        List<SdOrder> list = sdOrderService.selectSdOrderList(sdOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
    @RequiresPermissions("sd:order:export")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SdOrder sdOrder)
    {
        List<SdOrder> list = sdOrderService.selectSdOrderList(sdOrder);
        ExcelUtil<SdOrder> util = new ExcelUtil<SdOrder>(SdOrder.class);
        return util.exportExcel(list, "订单信息数据");
    }

    /**
     * 新增订单信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单信息
     */
    @RequiresPermissions("sd:order:add")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SdOrder sdOrder)
    {
        return toAjax(sdOrderService.insertSdOrder(sdOrder));
    }

    /**
     * 修改订单信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        SdOrder sdOrder = sdOrderService.selectSdOrderById(id);
        mmap.put("sdOrder", sdOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单信息
     */
    @RequiresPermissions("sd:order:edit")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SdOrder sdOrder)
    {
        return toAjax(sdOrderService.updateSdOrder(sdOrder));
    }

    /**
     * 删除订单信息
     */
    @RequiresPermissions("sd:order:remove")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sdOrderService.deleteSdOrderByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SdOrder> util = new ExcelUtil<SdOrder>(SdOrder.class);
        return util.importTemplateExcel("订单列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("sd:order:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SdOrder> util = new ExcelUtil<SdOrder>(SdOrder.class);
        List<SdOrder> orderList = util.importExcel(file.getInputStream());
        String message = importOrder(orderList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入订单数据
     * 
     * @param userList 订单数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importOrder(List<SdOrder> orderList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(orderList) || orderList.size() == 0)
        {
            throw new BusinessException("导入订单数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SdOrder order : orderList)
        {
            try
            {
                order.setIsValid("1");
                boolean lossFlag = false;
                List<SdOrder>  dblist= sdOrderService.selectSdOrderList(order);
                logger.info("同名订单条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个订单
				}
                if (!lossFlag)
                {
                    order.setCreateBy(ShiroUtils.getLoginName());
                    order.setUpdateBy(ShiroUtils.getLoginName());
                    // order.setIsValid("1");
                    sdOrderService.insertSdOrder(order);//插入订单
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、订单 " +order.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    order.setId(dblist.get(0).getId());
                    order.setUpdateBy(ShiroUtils.getLoginName());
                	sdOrderService.updateSdOrder(order);//修改订单
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、订单 " +order.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、订单 " +order.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、订单 " + order.getPname()+ " 导入失败：";
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
