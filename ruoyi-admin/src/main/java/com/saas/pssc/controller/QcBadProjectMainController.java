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
import com.saas.pssc.domain.QcBadProjectMain;
import com.saas.pssc.service.IQcBadProjectMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 不良项目记录Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/qc/badpromain")
public class QcBadProjectMainController extends BaseController
{
    private String prefix = "qc/badpromain";

    @Autowired
    private IQcBadProjectMainService qcBadProjectMainService;

    @RequiresPermissions("qc:badpromain:view")
    @GetMapping()
    public String badpromain()
    {
        return prefix + "/badpromain";
    }

    /**
     * 查询不良项目记录列表
     */
    @RequiresPermissions("qc:badpromain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcBadProjectMain qcBadProjectMain)
    {
        startPage();
        List<QcBadProjectMain> list = qcBadProjectMainService.selectQcBadProjectMainList(qcBadProjectMain);
        return getDataTable(list);
    }

    /**
     * 导出不良项目记录列表
     */
    @RequiresPermissions("qc:badpromain:export")
    @Log(title = "不良项目记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcBadProjectMain qcBadProjectMain)
    {
        List<QcBadProjectMain> list = qcBadProjectMainService.selectQcBadProjectMainList(qcBadProjectMain);
        ExcelUtil<QcBadProjectMain> util = new ExcelUtil<QcBadProjectMain>(QcBadProjectMain.class);
        return util.exportExcel(list, "不良项目记录数据");
    }

    /**
     * 新增不良项目记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存不良项目记录
     */
    @RequiresPermissions("qc:badpromain:add")
    @Log(title = "不良项目记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcBadProjectMain qcBadProjectMain)
    {
        return toAjax(qcBadProjectMainService.insertQcBadProjectMain(qcBadProjectMain));
    }

    /**
     * 修改不良项目记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcBadProjectMain qcBadProjectMain = qcBadProjectMainService.selectQcBadProjectMainById(id);
        mmap.put("qcBadProjectMain", qcBadProjectMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存不良项目记录
     */
    @RequiresPermissions("qc:badpromain:edit")
    @Log(title = "不良项目记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcBadProjectMain qcBadProjectMain)
    {
        return toAjax(qcBadProjectMainService.updateQcBadProjectMain(qcBadProjectMain));
    }

    /**
     * 删除不良项目记录
     */
    @RequiresPermissions("qc:badpromain:remove")
    @Log(title = "不良项目记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcBadProjectMainService.deleteQcBadProjectMainByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcBadProjectMain> util = new ExcelUtil<QcBadProjectMain>(QcBadProjectMain.class);
        return util.importTemplateExcel("不良项目记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:matcm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcBadProjectMain> util = new ExcelUtil<QcBadProjectMain>(QcBadProjectMain.class);
        List<QcBadProjectMain> qcBadProjectMainList = util.importExcel(file.getInputStream());
        String message = importQcBadProjectMain(qcBadProjectMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入不良项目记录数据
     * 
     * @param userList 不良项目记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importQcBadProjectMain(List<QcBadProjectMain> qcBadProjectMainList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(qcBadProjectMainList) || qcBadProjectMainList.size() == 0)
        {
            throw new BusinessException("导入不良项目记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QcBadProjectMain qcBadProjectMain : qcBadProjectMainList)
        {
            try
            {
                qcBadProjectMain.setIsValid("1");
                boolean lossFlag = false;
                List<QcBadProjectMain>  dblist= qcBadProjectMainService.selectQcBadProjectMainList(qcBadProjectMain);
                logger.info("同名不良项目记录条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个不良项目记录
				}
                if (!lossFlag)
                {
                    qcBadProjectMain.setCreateBy(ShiroUtils.getLoginName());
                    qcBadProjectMain.setUpdateBy(ShiroUtils.getLoginName());
                    // qcBadProjectMain.setIsValid("1");
                    qcBadProjectMainService.insertQcBadProjectMain(qcBadProjectMain);//插入不良项目记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、不良项目记录 " +qcBadProjectMain.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    qcBadProjectMain.setId(dblist.get(0).getId());
                    qcBadProjectMain.setUpdateBy(ShiroUtils.getLoginName());
                	qcBadProjectMainService.updateQcBadProjectMain(qcBadProjectMain);//修改不良项目记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、不良项目记录 " +qcBadProjectMain.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、不良项目记录 " +qcBadProjectMain.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、不良项目记录 " + qcBadProjectMain.getPname()+ " 导入失败：";
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
