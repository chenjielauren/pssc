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
import com.saas.pssc.domain.QcMatCheckMain;
import com.saas.pssc.service.IQcMatCheckMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 原材料检验记录Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/matcm")
public class QcMatCheckMainController extends BaseController
{
    private String prefix = "qc/matcm";

    @Autowired
    private IQcMatCheckMainService qcMatCheckMainService;

    @RequiresPermissions("qc:matcm:view")
    @GetMapping()
    public String matcm()
    {
        return prefix + "/matcm";
    }

    /**
     * 查询原材料检验记录列表
     */
    @RequiresPermissions("qc:matcm:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcMatCheckMain qcMatCheckMain)
    {
        startPage();
        List<QcMatCheckMain> list = qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
        return getDataTable(list);
    }

    /**
     * 导出原材料检验记录列表
     */
    @RequiresPermissions("qc:matcm:export")
    @Log(title = "原材料检验记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcMatCheckMain qcMatCheckMain)
    {
        List<QcMatCheckMain> list = qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
        ExcelUtil<QcMatCheckMain> util = new ExcelUtil<QcMatCheckMain>(QcMatCheckMain.class);
        return util.exportExcel(list, "原材料检验记录数据");
    }

    /**
     * 新增原材料检验记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存原材料检验记录
     */
    @RequiresPermissions("qc:matcm:add")
    @Log(title = "原材料检验记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcMatCheckMain qcMatCheckMain)
    {
        return toAjax(qcMatCheckMainService.insertQcMatCheckMain(qcMatCheckMain));
    }

    /**
     * 修改原材料检验记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        QcMatCheckMain qcMatCheckMain = qcMatCheckMainService.selectQcMatCheckMainById(id);
        mmap.put("qcMatCheckMain", qcMatCheckMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存原材料检验记录
     */
    @RequiresPermissions("qc:matcm:edit")
    @Log(title = "原材料检验记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcMatCheckMain qcMatCheckMain)
    {
        return toAjax(qcMatCheckMainService.updateQcMatCheckMain(qcMatCheckMain));
    }

    /**
     * 删除原材料检验记录
     */
    @RequiresPermissions("qc:matcm:remove")
    @Log(title = "原材料检验记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcMatCheckMainService.deleteQcMatCheckMainByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcMatCheckMain> util = new ExcelUtil<QcMatCheckMain>(QcMatCheckMain.class);
        return util.importTemplateExcel("原材料检验记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:matcm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcMatCheckMain> util = new ExcelUtil<QcMatCheckMain>(QcMatCheckMain.class);
        List<QcMatCheckMain> qcMatCheckMainList = util.importExcel(file.getInputStream());
        String message = importQcMatCheckMain(qcMatCheckMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入原材料检验记录数据
     * 
     * @param userList 原材料检验记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importQcMatCheckMain(List<QcMatCheckMain> qcMatCheckMainList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(qcMatCheckMainList) || qcMatCheckMainList.size() == 0)
        {
            throw new BusinessException("导入原材料检验记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QcMatCheckMain qcMatCheckMain : qcMatCheckMainList)
        {
            try
            {
                qcMatCheckMain.setIsValid("1");
                boolean lossFlag = false;
                List<QcMatCheckMain>  dblist= qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
                logger.info("同名原材料检验记录条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个原材料检验记录
				}
                if (!lossFlag)
                {
                    qcMatCheckMain.setCreateBy(ShiroUtils.getLoginName());
                    qcMatCheckMain.setUpdateBy(ShiroUtils.getLoginName());
                    // qcMatCheckMain.setIsValid("1");
                    qcMatCheckMainService.insertQcMatCheckMain(qcMatCheckMain);//插入原材料检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、原材料检验记录 " +qcMatCheckMain.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    qcMatCheckMain.setId(dblist.get(0).getId());
                    qcMatCheckMain.setUpdateBy(ShiroUtils.getLoginName());
                	qcMatCheckMainService.updateQcMatCheckMain(qcMatCheckMain);//修改原材料检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、原材料检验记录 " +qcMatCheckMain.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原材料检验记录 " +qcMatCheckMain.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、原材料检验记录 " + qcMatCheckMain.getPname()+ " 导入失败：";
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
