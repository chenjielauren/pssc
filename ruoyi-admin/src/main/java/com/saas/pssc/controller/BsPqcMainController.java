package com.saas.pssc.controller;

import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.service.IBsPqcMainService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品检验标准Controller
 * 
 * @author admin
 * @date 2021-07-15
 */
@Controller
@RequestMapping("/bs/pqcmain")
public class BsPqcMainController extends BaseController
{
    private String prefix = "bs/pqcmain";

    @Autowired
    private IBsPqcMainService bsPqcMainService;

    @RequiresPermissions("bs:pqcmain:view")
    @GetMapping()
    public String pqcmain()
    {
        return prefix + "/pqcmain";
    }

    /**
     * 查询产品检验标准列表
     */
    @RequiresPermissions("bs:pqcmain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsPqcMain bsPqcMain)
    {
        startPage();
        List<BsPqcMain> list = bsPqcMainService.selectBsPqcMainList(bsPqcMain);
        return getDataTable(list);
    }

    /**
     * 导出产品检验标准列表
     */
    @RequiresPermissions("bs:pqcmain:export")
    @Log(title = "产品检验标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsPqcMain bsPqcMain)
    {
        List<BsPqcMain> list = bsPqcMainService.selectBsPqcMainList(bsPqcMain);
        ExcelUtil<BsPqcMain> util = new ExcelUtil<BsPqcMain>(BsPqcMain.class);
        return util.exportExcel(list, "产品检验标准数据");
    }

    /**
     * 新增产品检验标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品检验标准
     */
    @RequiresPermissions("bs:pqcmain:add")
    @Log(title = "产品检验标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsPqcMain bsPqcMain)
    {
        return toAjax(bsPqcMainService.insertBsPqcMain(bsPqcMain));
    }

    /**
     * 修改产品检验标准
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BsPqcMain bsPqcMain = bsPqcMainService.selectBsPqcMainById(id);
        mmap.put("bsPqcMain", bsPqcMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品检验标准
     */
    @RequiresPermissions("bs:pqcmain:edit")
    @Log(title = "产品检验标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsPqcMain bsPqcMain)
    {
        return toAjax(bsPqcMainService.updateBsPqcMain(bsPqcMain));
    }

    /**
     * 删除产品检验标准
     */
    @RequiresPermissions("bs:pqcmain:remove")
    @Log(title = "产品检验标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsPqcMainService.deleteBsPqcMainByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsPqcMain> util = new ExcelUtil<BsPqcMain>(BsPqcMain.class);
        return util.importTemplateExcel("产品检验标准数据");
    }

    /**
     * 导入数据
     */
    @RequiresPermissions("bs:pqcmain:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsPqcMain> util = new ExcelUtil<BsPqcMain>(BsPqcMain.class);
        List<BsPqcMain> pqcMainList = util.importExcel(file.getInputStream());
        String message = importPqcMain(pqcMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入产品检验标准数据
     * 
     * @param userList 产品检验标准数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importPqcMain(List<BsPqcMain> pqcMainList, Boolean isUpdateSupport)
    {
        if (StringUtils.isEmpty(pqcMainList) || pqcMainList.size() == 0)
        {
            throw new BusinessException("导入产品检验标准标准数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsPqcMain pqcMain : pqcMainList)
        {
            try
            {
                pqcMain.setIsValid("1");
                boolean pqcMainFlag = false;
                List<BsPqcMain>  dblist= bsPqcMainService.selectBsPqcMainList(pqcMain);
                logger.info("同名产品检验标准标准条数："+dblist.size());
                if (dblist.size()>0) {
                	pqcMainFlag = true;  // 验证是否存在这个产品检验标准标准
				}
                if (!pqcMainFlag)
                {
                    pqcMain.setCreateBy(ShiroUtils.getLoginName());
                    pqcMain.setUpdateBy(ShiroUtils.getLoginName());
                    pqcMain.setIsValid("1");
                    bsPqcMainService.insertBsPqcMain(pqcMain);//插入产品检验标准标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品检验标准标准 " +pqcMain.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    pqcMain.setId(dblist.get(0).getId());
                    pqcMain.setUpdateBy(ShiroUtils.getLoginName());
                	bsPqcMainService.updateBsPqcMain(pqcMain);//修改产品检验标准标准
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品检验标准标准 " +pqcMain.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品检验标准标准 " +pqcMain.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品检验标准标准 " + pqcMain.getName()+ " 导入失败：";
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
