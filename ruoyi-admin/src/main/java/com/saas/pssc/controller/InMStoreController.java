package com.saas.pssc.controller;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.exception.BusinessException;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.common.utils.bean.BeanUtils;
import com.saas.common.utils.poi.ExcelUtil;
import com.saas.pssc.domain.InMStore;
import com.saas.pssc.domain.InStore;
import com.saas.pssc.service.IInStoreService;

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
 * 材料库存信息Controller
 * 
 * @author admin
 * @date 2021-08-03
 */
@Controller
@RequestMapping("/in/mstore")
public class InMStoreController extends BaseController
{
    private String prefix = "in/mstore";

    @Autowired
    private IInStoreService inStoreService;

    @RequiresPermissions("in:mstore:view")
    @GetMapping()
    public String mstore()
    {
        return prefix + "/mstore";
    }

    /**
     * 查询材料库存信息列表
     */
    @RequiresPermissions("in:mstore:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InStore inStore)
    {
        startPage();
        List<InStore> list = inStoreService.selectInStoreList(inStore);
        return getDataTable(list);
    }

    /**
     * 导出材料库存信息列表
     */
    @RequiresPermissions("in:mstore:export")
    @Log(title = "材料库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InStore inStore)
    {
        List<InStore> list = inStoreService.selectInStoreList(inStore);
        List<InMStore> mlist = new ArrayList<InMStore>();
        if(StringUtils.isNotEmpty(list)){
            for(InStore obj:list){
                InMStore inMStore = new InMStore();
                BeanUtils.copyProperties(obj, inMStore);
                mlist.add(inMStore);
            } 
        }
        ExcelUtil<InMStore> util = new ExcelUtil<InMStore>(InMStore.class);
        return util.exportExcel(mlist, "材料库存信息数据");
    }

    /**
     * 新增材料库存信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存材料库存信息
     */
    @RequiresPermissions("in:mstore:add")
    @Log(title = "材料库存信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InStore inStore)
    {
        return toAjax(inStoreService.insertInStore(inStore));
    }

    /**
     * 修改材料库存信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InStore inStore = inStoreService.selectInStoreById(id);
        mmap.put("inStore", inStore);
        return prefix + "/edit";
    }

    /**
     * 修改保存材料库存信息
     */
    @RequiresPermissions("in:mstore:edit")
    @Log(title = "材料库存信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InStore inStore)
    {
        return toAjax(inStoreService.updateInStore(inStore));
    }

    /**
     * 删除材料库存信息
     */
    @RequiresPermissions("in:mstore:remove")
    @Log(title = "材料库存信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(inStoreService.deleteInStoreByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<InMStore> util = new ExcelUtil<InMStore>(InMStore.class);
        return util.importTemplateExcel("材料库存记录列表");
    }
    
    /**
     * 导入数据
     */
    @RequiresPermissions("in:mstore:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<InMStore> util = new ExcelUtil<InMStore>(InMStore.class);
        List<InMStore> inMStoreList = util.importExcel(file.getInputStream());
        String message = importInMStore(inMStoreList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入材料库存记录数据
     * 
     * @param userList 材料库存记录数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importInMStore(List<InMStore> inMStoreList, Boolean isUpdateSupport)
    {
        if (StringUtils.isEmpty(inMStoreList) || inMStoreList.size() == 0)
        {
            throw new BusinessException("导入材料库存记录数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (InMStore inMStore : inMStoreList)
        {
            try
            {
                InStore inStore = new InStore();
                BeanUtils.copyProperties(inMStore, inStore);
                inStore.setPtype("0");//材料库存
                inStore.setIsValid("1");
                boolean flag = false;
                List<InStore>  dblist= inStoreService.selectInStoreList(inStore);
                logger.info("同名材料库存记录条数："+dblist.size());
                if (dblist.size()>0) {
                	flag = true;  // 验证是否存在这个材料库存记录
				}
                if (!flag)
                {
                    inStore.setCreateBy(ShiroUtils.getLoginName());
                    inStore.setUpdateBy(ShiroUtils.getLoginName());
                    // qcMatCheckMain.setIsValid("1");
                    inStoreService.insertInStore(inStore);//插入材料库存记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、材料编号 " +inStore.getMcode() + " 材料名称 " +inStore.getMname() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    inStore.setId(dblist.get(0).getId());
                    inStore.setUpdateBy(ShiroUtils.getLoginName());
                	inStoreService.updateInStore(inStore);//修改材料检验标准记录
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、材料编号 " +inStore.getMcode() + " 材料名称 " +inStore.getMname() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、材料编号 " +inStore.getMcode() + " 材料名称 " +inStore.getMname() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum  + "、材料编号 " +inMStore.getMcode() + " 材料名称 " +inMStore.getMname() + " 导入失败：";
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
