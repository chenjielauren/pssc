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
import com.saas.pssc.domain.QcBadProject;
import com.saas.pssc.service.IQcBadProjectService;

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
 * 不良项目汇总Controller
 * 
 * @author admin
 * @date 2021-07-25
 */
@Controller
@RequestMapping("/qc/badproject")
public class QcBadProjectController extends BaseController
{
    private String prefix = "qc/badproject";

    @Autowired
    private IQcBadProjectService qcBadProjectService;

    @RequiresPermissions("qc:badproject:view")
    @GetMapping()
    public String badproject()
    {
        return prefix + "/badproject";
    }

    /**
     * 查询不良项目汇总列表
     */
    @RequiresPermissions("qc:badproject:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QcBadProject qcBadProject)
    {
        startPage();
        List<QcBadProject> list = qcBadProjectService.selectQcBadProjectList(qcBadProject);
        return getDataTable(list);
    }

    /**
     * 导出不良项目汇总列表
     */
    @RequiresPermissions("qc:badproject:export")
    @Log(title = "不良项目汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QcBadProject qcBadProject)
    {
        List<QcBadProject> list = qcBadProjectService.selectQcBadProjectList(qcBadProject);
        ExcelUtil<QcBadProject> util = new ExcelUtil<QcBadProject>(QcBadProject.class);
        return util.exportExcel(list, "不良项目汇总数据");
    }

    /**
     * 新增不良项目汇总
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存不良项目汇总
     */
    @RequiresPermissions("qc:badproject:add")
    @Log(title = "不良项目汇总", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QcBadProject qcBadProject)
    {
        return toAjax(qcBadProjectService.insertQcBadProject(qcBadProject));
    }

    /**
     * 修改不良项目汇总
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        QcBadProject qcBadProject = qcBadProjectService.selectQcBadProjectById(id);
        mmap.put("qcBadProject", qcBadProject);
        return prefix + "/edit";
    }

    /**
     * 修改保存不良项目汇总
     */
    @RequiresPermissions("qc:badproject:edit")
    @Log(title = "不良项目汇总", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QcBadProject qcBadProject)
    {
        return toAjax(qcBadProjectService.updateQcBadProject(qcBadProject));
    }

    /**
     * 删除不良项目汇总
     */
    @RequiresPermissions("qc:badproject:remove")
    @Log(title = "不良项目汇总", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qcBadProjectService.deleteQcBadProjectByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<QcBadProject> util = new ExcelUtil<QcBadProject>(QcBadProject.class);
        return util.importTemplateExcel("不良项目汇总列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:badproject:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QcBadProject> util = new ExcelUtil<QcBadProject>(QcBadProject.class);
        List<QcBadProject> badProjectList = util.importExcel(file.getInputStream());
        String message = importBadProject(badProjectList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入不良项目汇总数据
     * 
     * @param badProjectList 不良项目汇总数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importBadProject(List<QcBadProject> badProjectList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(badProjectList) || badProjectList.size() == 0)
        {
            throw new BusinessException("导入不良项目汇总数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QcBadProject qcBadProject : badProjectList)
        {
            try
            {
                qcBadProject.setIsValid("1");
                boolean lossFlag = false;
                List<QcBadProject>  dblist= qcBadProjectService.selectQcBadProjectList(qcBadProject);
                logger.info("同名不良项目汇总条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个不良项目汇总
				}
                if (!lossFlag)
                {
                    qcBadProject.setCreateBy(ShiroUtils.getLoginName());
                    qcBadProject.setUpdateBy(ShiroUtils.getLoginName());
                    // qcBadProject.setIsValid("1");
                    qcBadProjectService.insertQcBadProject(qcBadProject);//插入不良项目汇总
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、不良项目汇总 " +qcBadProject.getPname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    qcBadProject.setId(dblist.get(0).getId());
                    qcBadProject.setUpdateBy(ShiroUtils.getLoginName());
                	qcBadProjectService.updateQcBadProject(qcBadProject) ;//修改不良项目汇总
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、不良项目汇总 " +qcBadProject.getPname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、不良项目汇总 " +qcBadProject.getPname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、不良项目汇总 " + qcBadProject.getPname()+ " 导入失败：";
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

    //加载饼图
    @PostMapping("/loadPieChart")
	@ResponseBody
	public AjaxResult loadPieChart(QcBadProject qcBadProject) {
        AjaxResult ajaxResult = AjaxResult.success();
        //查询不良项目饼图数据
        List<QcBadProject> projectList= qcBadProjectService.loadPieChartByProject(qcBadProject);        
        ajaxResult.put("projectList", projectList);
        //查询不良项目饼图数据
        List<QcBadProject> pnameList= qcBadProjectService.loadPieChartByPname(qcBadProject);        
        ajaxResult.put("pnameList", pnameList);
        return ajaxResult;
	}
}
