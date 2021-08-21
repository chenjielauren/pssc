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
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.service.IBsProSopMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * 成品检验标准Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/bs/promain")
public class BsProSopMainController extends BaseController
{
    private String prefix = "bs/promain";

    @Autowired
    private IBsProSopMainService bsProSopMainService;

    @RequiresPermissions("bs:promain:view")
    @GetMapping()
    public String promain()
    {
        return prefix + "/promain";
    }

    /**
     * 查询成品检验标准列表
     */
    @RequiresPermissions("bs:promain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsProSopMain bsProSopMain)
    {
        startPage();
        List<BsProSopMain> list = bsProSopMainService.selectBsProSopMainList(bsProSopMain);
        return getDataTable(list);
    }

    /**
     * 导出成品检验标准列表
     */
    @RequiresPermissions("bs:promain:export")
    @Log(title = "成品检验标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsProSopMain bsProSopMain)
    {
        List<BsProSopMain> list = bsProSopMainService.selectBsProSopMainList(bsProSopMain);
        ExcelUtil<BsProSopMain> util = new ExcelUtil<BsProSopMain>(BsProSopMain.class);
        return util.exportExcel(list, "成品检验标准数据");
    }

    /**
     * 新增成品检验标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成品检验标准
     */
    @RequiresPermissions("bs:promain:add")
    @Log(title = "成品检验标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsProSopMain bsProSopMain)
    {
        return toAjax(bsProSopMainService.insertBsProSopMain(bsProSopMain));
    }

    /**
     * 修改成品检验标准
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsProSopMain bsProSopMain = bsProSopMainService.selectBsProSopMainById(id);
        mmap.put("bsProSopMain", bsProSopMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品检验标准
     */
    @RequiresPermissions("bs:promain:edit")
    @Log(title = "成品检验标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsProSopMain bsProSopMain)
    {
        return toAjax(bsProSopMainService.updateBsProSopMain(bsProSopMain));
    }

    /**
     * 删除成品检验标准
     */
    @RequiresPermissions("bs:promain:remove")
    @Log(title = "成品检验标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsProSopMainService.deleteBsProSopMainByIds(ids));
    }
    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsProSopMain> util = new ExcelUtil<BsProSopMain>(BsProSopMain.class);
        return util.importTemplateExcel("成品检验记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("qc:matcm:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsProSopMain> util = new ExcelUtil<BsProSopMain>(BsProSopMain.class);
        List<BsProSopMain> bsProSopMainList = util.importExcel(file.getInputStream());
        String message = importBsProSopMain(bsProSopMainList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入成品检验记录数据
     * 
     * @param userList 成品检验记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importBsProSopMain(List<BsProSopMain> bsProSopMainList, Boolean isUpdateSupport)
    {
        if (CollectionUtils.isEmpty(bsProSopMainList) || bsProSopMainList.size() == 0)
        {
            throw new BusinessException("导入成品检验记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsProSopMain bsProSopMain : bsProSopMainList)
        {
            try
            {
                bsProSopMain.setIsValid("1");
                boolean lossFlag = false;
                List<BsProSopMain>  dblist= bsProSopMainService.selectBsProSopMainList(bsProSopMain);
                logger.info("同名成品检验记录条数："+dblist.size());
                if (dblist.size()>0) {
                	lossFlag = true;  // 验证是否存在这个成品检验记录
				}
                if (!lossFlag)
                {
                    bsProSopMain.setCreateBy(ShiroUtils.getLoginName());
                    bsProSopMain.setUpdateBy(ShiroUtils.getLoginName());
                    // bsProSopMain.setIsValid("1");
                    bsProSopMainService.insertBsProSopMain(bsProSopMain);//插入成品检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品编号 " +bsProSopMain.getPcode()+ " 产品名称 " +bsProSopMain.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bsProSopMain.setId(dblist.get(0).getId());
                    bsProSopMain.setUpdateBy(ShiroUtils.getLoginName());
                	bsProSopMainService.updateBsProSopMain(bsProSopMain);//修改成品检验记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品编号 " +bsProSopMain.getPcode()+ " 产品名称 " +bsProSopMain.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品编号 " +bsProSopMain.getPcode()+ " 产品名称 " +bsProSopMain.getName()  + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品编号 " +bsProSopMain.getPcode()+ " 产品名称 " +bsProSopMain.getName() + " 导入失败：";
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
