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
import com.saas.pssc.domain.BsProcLossSop;
import com.saas.pssc.service.IBsProcLossSopService;

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
 * 工序损耗标准Controller
 * 
 * @author admin
 * @date 2021-07-15
 */
@Controller
@RequestMapping("/bs/procloss")
public class BsProcLossSopController extends BaseController
{
    private String prefix = "bs/procloss";

    @Autowired
    private IBsProcLossSopService bsProcLossSopService;

    @RequiresPermissions("bs:procloss:view")
    @GetMapping()
    public String procloss()
    {
        return prefix + "/procloss";
    }

    /**
     * 查询工序损耗标准列表
     */
    @RequiresPermissions("bs:procloss:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsProcLossSop bsProcLossSop)
    {
        startPage();
        List<BsProcLossSop> list = bsProcLossSopService.selectBsProcLossSopList(bsProcLossSop);
        return getDataTable(list);
    }

    /**
     * 导出工序损耗标准列表
     */
    @RequiresPermissions("bs:procloss:export")
    @Log(title = "工序损耗标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsProcLossSop bsProcLossSop)
    {
        List<BsProcLossSop> list = bsProcLossSopService.selectBsProcLossSopList(bsProcLossSop);
        ExcelUtil<BsProcLossSop> util = new ExcelUtil<BsProcLossSop>(BsProcLossSop.class);
        return util.exportExcel(list, "工序损耗标准数据");
    }

    /**
     * 新增工序损耗标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工序损耗标准
     */
    @RequiresPermissions("bs:procloss:add")
    @Log(title = "工序损耗标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsProcLossSop bsProcLossSop)
    {
        return toAjax(bsProcLossSopService.insertBsProcLossSop(bsProcLossSop));
    }

    /**
     * 修改工序损耗标准
     */
    @GetMapping("/edit/{Id}")
    public String edit(@PathVariable("Id") Long Id, ModelMap mmap)
    {
        BsProcLossSop bsProcLossSop = bsProcLossSopService.selectBsProcLossSopById(Id);
        mmap.put("bsProcLossSop", bsProcLossSop);
        return prefix + "/edit";
    }

    /**
     * 修改保存工序损耗标准
     */
    @RequiresPermissions("bs:procloss:edit")
    @Log(title = "工序损耗标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsProcLossSop bsProcLossSop)
    {
        return toAjax(bsProcLossSopService.updateBsProcLossSop(bsProcLossSop));
    }

    /**
     * 删除工序损耗标准
     */
    @RequiresPermissions("bs:procloss:remove")
    @Log(title = "工序损耗标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsProcLossSopService.deleteBsProcLossSopByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsProcLossSop> util = new ExcelUtil<BsProcLossSop>(BsProcLossSop.class);
        return util.importTemplateExcel("工序损耗标准数据");
    }
    
    
    /**
     * 导入数据
     */
    @RequiresPermissions("bs:procloss:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsProcLossSop> util = new ExcelUtil<BsProcLossSop>(BsProcLossSop.class);
        List<BsProcLossSop> lossList = util.importExcel(file.getInputStream());
        String message = importLoss(lossList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入工序损耗数据
     * 
     * @param userList 工序损耗数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importLoss(List<BsProcLossSop> lossList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(lossList) || lossList.size() == 0)
        {
            throw new BusinessException("导入工序损耗标准数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsProcLossSop loss : lossList)
        {
            try
            {
                loss.setIsValid("1");
                boolean lossFlag = false;
                List<BsProcLossSop>  dblist= bsProcLossSopService.selectBsProcLossSopList(loss);
                logger.info("同名工序损耗标准条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个工序损耗标准
				}
                if (!lossFlag)
                {
                    loss.setCreateBy(ShiroUtils.getLoginName());
                    loss.setUpdateBy(ShiroUtils.getLoginName());
                    bsProcLossSopService.insertBsProcLossSop(loss);//插入工序损耗标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工序损耗标准 " +loss.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    loss.setId(dblist.get(0).getId());
                    loss.setUpdateBy(ShiroUtils.getLoginName());
                	bsProcLossSopService.updateBsProcLossSop(loss);//修改工序损耗标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工序损耗标准 " +loss.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工序损耗标准 " +loss.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工序损耗标准 " + loss.getName()+ " 导入失败：";
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
