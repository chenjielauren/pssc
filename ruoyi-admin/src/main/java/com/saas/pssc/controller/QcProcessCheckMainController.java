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
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.service.IQcProcessCheckMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 过程检验记录Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/proccm")
public class QcProcessCheckMainController extends BaseController
{
    private String prefix = "qc/proccm";

    @Autowired
    private IQcProcessCheckMainService qcProcessCheckMainService;

    @RequiresPermissions("qc:proccm:view")
    @GetMapping()
    public String proccm()
    {
        return prefix + "/proccm";
    }

    /**
     * 查询过程检验记录列表
     */
    @RequiresPermissions("qc:proccm:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcProcessCheckMain qcProcessCheckMain)
    {
        startPage();
        List<QcProcessCheckMain> list = qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
        return getDataTable(list);
    }

    /**
     * 导出过程检验记录列表
     */
    @RequiresPermissions("qc:proccm:export")
    @Log(title = "过程检验记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcProcessCheckMain qcProcessCheckMain)
    {
        List<QcProcessCheckMain> list = qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
        ExcelUtil<QcProcessCheckMain> util = new ExcelUtil<QcProcessCheckMain>(QcProcessCheckMain.class);
        return util.exportExcel(list, "过程检验记录数据");
    }

    /**
     * 新增过程检验记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存过程检验记录
     */
    @RequiresPermissions("qc:proccm:add")
    @Log(title = "过程检验记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcProcessCheckMain qcProcessCheckMain)
    {
        return toAjax(qcProcessCheckMainService.insertQcProcessCheckMain(qcProcessCheckMain));
    }

    /**
     * 修改过程检验记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProcessCheckMain qcProcessCheckMain = qcProcessCheckMainService.selectQcProcessCheckMainById(id);
        mmap.put("qcProcessCheckMain", qcProcessCheckMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存过程检验记录
     */
    @RequiresPermissions("qc:proccm:edit")
    @Log(title = "过程检验记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcProcessCheckMain qcProcessCheckMain)
    {
        return toAjax(qcProcessCheckMainService.updateQcProcessCheckMain(qcProcessCheckMain));
    }

    /**
     * 删除过程检验记录
     */
    @RequiresPermissions("qc:proccm:remove")
    @Log(title = "过程检验记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcProcessCheckMainService.deleteQcProcessCheckMainByIds(ids));
    }
    
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcProcessCheckMain> util = new ExcelUtil<QcProcessCheckMain>(QcProcessCheckMain.class);
        return util.importTemplateExcel("过程检验记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:proccm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcProcessCheckMain> util = new ExcelUtil<QcProcessCheckMain>(QcProcessCheckMain.class);
        List<QcProcessCheckMain> list = util.importExcel(file.getInputStream());
        String message = importProcCheckMain(list, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入过程检验记录数据
     * 
     * @param userList 过程检验记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importProcCheckMain(List<QcProcessCheckMain> list, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(list) || list.size() == 0)
        {
            throw new BusinessException("导入过程检验记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QcProcessCheckMain qcProcessCheckMain : list)
        {
            try
            {
                qcProcessCheckMain.setIsValid("1");
                boolean lossFlag = false;
                List<QcProcessCheckMain>  dblist= qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
                logger.info("同名过程检验记录条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个过程检验记录
				}
                if (!lossFlag)
                {
                    qcProcessCheckMain.setCreateBy(ShiroUtils.getLoginName());
                    qcProcessCheckMain.setUpdateBy(ShiroUtils.getLoginName());
                    // qcProcessCheckMain.setIsValid("1");
                    qcProcessCheckMainService.insertQcProcessCheckMain(qcProcessCheckMain);//插入过程检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、过程检验记录 " +qcProcessCheckMain.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    qcProcessCheckMain.setId(dblist.get(0).getId());
                    qcProcessCheckMain.setUpdateBy(ShiroUtils.getLoginName());
                	qcProcessCheckMainService.updateQcProcessCheckMain(qcProcessCheckMain);//修改过程检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、过程检验记录 " +qcProcessCheckMain.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、过程检验记录 " +qcProcessCheckMain.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、过程检验记录 " + qcProcessCheckMain.getPname()+ " 导入失败：";
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
