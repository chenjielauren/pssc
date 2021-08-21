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
import com.saas.pssc.domain.QcProdCheckMain;
import com.saas.pssc.service.IQcProdCheckMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 成品检验记录Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/prodcm")
public class QcProdCheckMainController extends BaseController
{
    private String prefix = "qc/prodcm";

    @Autowired
    private IQcProdCheckMainService qcProdCheckMainService;

    @RequiresPermissions("qc:prodcm:view")
    @GetMapping(value = {"","/{pcode}/''","/{qcResult}" })
    public String prodcm(@PathVariable(value = "pcode", required = false) String pcode,@PathVariable(value = "qcResult", required = false) String qcResult, ModelMap mmap)
    {
        mmap.put("pcode", pcode);
        mmap.put("qcResult", qcResult);
        return prefix + "/prodcm";
    }

    /**
     * 查询成品检验记录列表
     */
    @RequiresPermissions("qc:prodcm:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcProdCheckMain qcProdCheckMain)
    {
        startPage();
        List<QcProdCheckMain> list = qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
        return getDataTable(list);
    }

    /**
     * 导出成品检验记录列表
     */
    @RequiresPermissions("qc:prodcm:export")
    @Log(title = "成品检验记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcProdCheckMain qcProdCheckMain)
    {
        List<QcProdCheckMain> list = qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
        ExcelUtil<QcProdCheckMain> util = new ExcelUtil<QcProdCheckMain>(QcProdCheckMain.class);
        return util.exportExcel(list, "成品检验记录数据");
    }

    /**
     * 新增成品检验记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成品检验记录
     */
    @RequiresPermissions("qc:prodcm:add")
    @Log(title = "成品检验记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcProdCheckMain qcProdCheckMain)
    {
        return toAjax(qcProdCheckMainService.insertQcProdCheckMain(qcProdCheckMain));
    }

    /**
     * 修改成品检验记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProdCheckMain qcProdCheckMain = qcProdCheckMainService.selectQcProdCheckMainById(id);
        mmap.put("qcProdCheckMain", qcProdCheckMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品检验记录
     */
    @RequiresPermissions("qc:prodcm:edit")
    @Log(title = "成品检验记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcProdCheckMain qcProdCheckMain)
    {
        return toAjax(qcProdCheckMainService.updateQcProdCheckMain(qcProdCheckMain));
    }

    /**
     * 删除成品检验记录
     */
    @RequiresPermissions("qc:prodcm:remove")
    @Log(title = "成品检验记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcProdCheckMainService.deleteQcProdCheckMainByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcProdCheckMain> util = new ExcelUtil<QcProdCheckMain>(QcProdCheckMain.class);
        return util.importTemplateExcel("成品检验记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:prodcm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcProdCheckMain> util = new ExcelUtil<QcProdCheckMain>(QcProdCheckMain.class);
        List<QcProdCheckMain> list = util.importExcel(file.getInputStream());
        String message = importProdCheckMain(list, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入成品检验记录数据
     * 
     * @param userList 成品检验记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importProdCheckMain(List<QcProdCheckMain> list, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(list) || list.size() == 0)
        {
            throw new BusinessException("导入成品检验记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QcProdCheckMain qcProdCheckMain : list)
        {
            try
            {
                qcProdCheckMain.setIsValid("1");
                boolean flag = false;
                List<QcProdCheckMain>  dblist= qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
                logger.info("同名成品检验记录条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个成品检验记录
				}
                if (!flag)
                {
                    qcProdCheckMain.setCreateBy(ShiroUtils.getLoginName());
                    qcProdCheckMain.setUpdateBy(ShiroUtils.getLoginName());
                    // qcProdCheckMain.setIsValid("1");
                    qcProdCheckMainService.insertQcProdCheckMain(qcProdCheckMain);//插入成品检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品编号 " +qcProdCheckMain.getPcode() + " 产品名称" +qcProdCheckMain.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    qcProdCheckMain.setId(dblist.get(0).getId());
                    qcProdCheckMain.setUpdateBy(ShiroUtils.getLoginName());
                	qcProdCheckMainService.updateQcProdCheckMain(qcProdCheckMain);//修改成品检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品编号 " +qcProdCheckMain.getPcode() + " 产品名称" +qcProdCheckMain.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品编号 " +qcProdCheckMain.getPcode() + " 产品名称" +qcProdCheckMain.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品编号 " +qcProdCheckMain.getPcode() + " 产品名称" +qcProdCheckMain.getPname() + " 导入失败：";
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
