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
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.service.IBsMatSopMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 材料检验标准Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/bs/matmain")
public class BsMatSopMainController extends BaseController
{
    private String prefix = "bs/matmain";

    @Autowired
    private IBsMatSopMainService bsMatSopMainService;

    @RequiresPermissions("bs:matmain:view")
    @GetMapping()
    public String matmain()
    {
        return prefix + "/matmain";
    }

    /**
     * 查询材料检验标准列表
     */
    @RequiresPermissions("bs:matmain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsMatSopMain bsMatSopMain)
    {
        startPage();
        List<BsMatSopMain> list = bsMatSopMainService.selectBsMatSopMainList(bsMatSopMain);
        return getDataTable(list);
    }

    /**
     * 导出材料检验标准列表
     */
    @RequiresPermissions("bs:matmain:export")
    @Log(title = "材料检验标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsMatSopMain bsMatSopMain)
    {
        List<BsMatSopMain> list = bsMatSopMainService.selectBsMatSopMainList(bsMatSopMain);
        ExcelUtil<BsMatSopMain> util = new ExcelUtil<BsMatSopMain>(BsMatSopMain.class);
        return util.exportExcel(list, "材料检验标准数据");
    }

    /**
     * 新增材料检验标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存材料检验标准
     */
    @RequiresPermissions("bs:matmain:add")
    @Log(title = "材料检验标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsMatSopMain bsMatSopMain)
    {
        return toAjax(bsMatSopMainService.insertBsMatSopMain(bsMatSopMain));
    }

    /**
     * 修改材料检验标准
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsMatSopMain bsMatSopMain = bsMatSopMainService.selectBsMatSopMainById(id);
        mmap.put("bsMatSopMain", bsMatSopMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存材料检验标准
     */
    @RequiresPermissions("bs:matmain:edit")
    @Log(title = "材料检验标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsMatSopMain bsMatSopMain)
    {
        return toAjax(bsMatSopMainService.updateBsMatSopMain(bsMatSopMain));
    }

    /**
     * 删除材料检验标准
     */
    @RequiresPermissions("bs:matmain:remove")
    @Log(title = "材料检验标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsMatSopMainService.deleteBsMatSopMainByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsMatSopMain> util = new ExcelUtil<BsMatSopMain>(BsMatSopMain.class);
        return util.importTemplateExcel("材料检验标准记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:matcm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsMatSopMain> util = new ExcelUtil<BsMatSopMain>(BsMatSopMain.class);
        List<BsMatSopMain> bsMatSopMainList = util.importExcel(file.getInputStream());
        String message = importBsMatSopMain(bsMatSopMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入材料检验标准记录数据
     * 
     * @param userList 材料检验标准记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importBsMatSopMain(List<BsMatSopMain> bsMatSopMainList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(bsMatSopMainList) || bsMatSopMainList.size() == 0)
        {
            throw new BusinessException("导入材料检验标准记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsMatSopMain bsMatSopMain : bsMatSopMainList)
        {
            try
            {
                bsMatSopMain.setIsValid("1");
                boolean flag = false;
                List<BsMatSopMain>  dblist= bsMatSopMainService.selectBsMatSopMainList(bsMatSopMain);
                logger.info("同名材料检验标准记录条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个材料检验标准记录
				}
                if (!flag)
                {
                    bsMatSopMain.setCreateBy(ShiroUtils.getLoginName());
                    bsMatSopMain.setUpdateBy(ShiroUtils.getLoginName());
                    // qcMatCheckMain.setIsValid("1");
                    bsMatSopMainService.insertBsMatSopMain(bsMatSopMain);//插入材料检验标准记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、材料编号 " +bsMatSopMain.getPcode() + " 材料名称 " +bsMatSopMain.getName()+ " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bsMatSopMain.setId(dblist.get(0).getId());
                    bsMatSopMain.setUpdateBy(ShiroUtils.getLoginName());
                	bsMatSopMainService.updateBsMatSopMain(bsMatSopMain);//修改材料检验标准记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、材料编号 " +bsMatSopMain.getPcode() + " 材料名称 " +bsMatSopMain.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、材料编号 " +bsMatSopMain.getPcode() + " 材料名称 " +bsMatSopMain.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、材料编号 " +bsMatSopMain.getPcode() + " 材料名称 " +bsMatSopMain.getName()+ " 导入失败：";
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
