package com.saas.pssc.controller;

import java.util.List;
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

import com.saas.common.annotation.Log;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.service.IBsBomMainService;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.common.core.page.TableDataInfo;

/**
 * bom物料清单Controller
 * 
 * @author admin
 * @date 2021-08-09
 */
@Controller
@RequestMapping("/bs/bommain")
public class BsBomMainController extends BaseController
{
    private String prefix = "bs/bommain";

    @Autowired
    private IBsBomMainService bsBomMainService;

    @RequiresPermissions("bs:bommain:view")
    @GetMapping()
    public String bommain()
    {
        return prefix + "/bommain";
    }

    /**
     * 查询bom物料清单列表
     */
    @RequiresPermissions("bs:bommain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BsBomMain bsBomMain)
    {
        startPage();
        List<BsBomMain> list = bsBomMainService.selectBsBomMainList(bsBomMain);
        return getDataTable(list);
    }

    /**
     * 导出bom物料清单列表
     */
    @RequiresPermissions("bs:bommain:export")
    @Log(title = "bom物料清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsBomMain bsBomMain)
    {
        List<BsBomMain> list = bsBomMainService.selectBsBomMainList(bsBomMain);
        ExcelUtil<BsBomMain> util = new ExcelUtil<BsBomMain>(BsBomMain.class);
        return util.exportExcel(list, "bom物料清单数据");
    }

    /**
     * 新增bom物料清单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存bom物料清单
     */
    @RequiresPermissions("bs:bommain:add")
    @Log(title = "bom物料清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BsBomMain bsBomMain)
    {
        return toAjax(bsBomMainService.insertBsBomMain(bsBomMain));
    }

    /**
     * 修改bom物料清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BsBomMain bsBomMain = bsBomMainService.selectBsBomMainById(id);
        mmap.put("bsBomMain", bsBomMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存bom物料清单
     */
    @RequiresPermissions("bs:bommain:edit")
    @Log(title = "bom物料清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BsBomMain bsBomMain)
    {
        return toAjax(bsBomMainService.updateBsBomMain(bsBomMain));
    }

    /**
     * 删除bom物料清单
     */
    @RequiresPermissions("bs:bommain:remove")
    @Log(title = "bom物料清单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bsBomMainService.deleteBsBomMainByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BsBomMain> util = new ExcelUtil<BsBomMain>(BsBomMain.class);
        return util.importTemplateExcel("BOM数据");
    }

    /**
     * 导入数据
     */
    @RequiresPermissions("bs:bommain:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BsBomMain> util = new ExcelUtil<BsBomMain>(BsBomMain.class);
        List<BsBomMain> bomList = util.importExcel(file.getInputStream());
        String message = importBom(bomList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入BOM数据
     * 
     * @param userList BOM数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importBom(List<BsBomMain> bomList, Boolean isUpdateSupport)
    {
        if (StringUtils.isEmpty(bomList) || bomList.size() == 0)
        {
            throw new BusinessException("导入BOM数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BsBomMain bsBomMain : bomList)
        {
            try
            {
                bsBomMain.setIsValid("1");
                boolean flag = false;
                List<BsBomMain>  dblist= bsBomMainService.selectBsBomMainList(bsBomMain);
                logger.info("同名BOM条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个BOM
				}
                if (!flag)
                {
                    bsBomMain.setCreateBy(ShiroUtils.getLoginName());
                    bsBomMain.setUpdateBy(ShiroUtils.getLoginName());
                    bsBomMainService.insertBsBomMain(bsBomMain);//插入BOM
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、BOM " +bsBomMain.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bsBomMain.setId(dblist.get(0).getId());
                    bsBomMain.setUpdateBy(ShiroUtils.getLoginName());
                	bsBomMainService.updateBsBomMain(bsBomMain);//修改BOM
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、BOM " +bsBomMain.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、BOM " +bsBomMain.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、BOM " + bsBomMain.getName()+ " 导入失败：";
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
