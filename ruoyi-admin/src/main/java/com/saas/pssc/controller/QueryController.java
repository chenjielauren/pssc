package com.saas.pssc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.domain.entity.SysDictData;
import com.saas.common.core.domain.entity.SysUser;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.json.JSONObject;
import com.saas.common.json.JSONObject.JSONArray;
import com.saas.common.utils.ShiroUtils;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.domain.BsMatSopDetail;
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.domain.BsVendor;
import com.saas.pssc.domain.InStore;
import com.saas.pssc.domain.PpWoBookBom;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.domain.QcBadProjectMain;
import com.saas.pssc.domain.QcMatCheckMain;
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.domain.QcProdCheckMain;
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.domain.SdDeliveryDetail;
import com.saas.pssc.service.IBsBomMainService;
import com.saas.pssc.service.IBsCraftSopMainService;
import com.saas.pssc.service.IBsMatSopDetailService;
import com.saas.pssc.service.IBsMatSopMainService;
import com.saas.pssc.service.IBsMcnMainService;
import com.saas.pssc.service.IBsPqcMainService;
import com.saas.pssc.service.IBsProSopMainService;
import com.saas.pssc.service.IBsVendorService;
import com.saas.pssc.service.IInStoreService;
import com.saas.pssc.service.IPpWoBookDetailService;
import com.saas.pssc.service.IPpWoBookMainService;
import com.saas.pssc.service.IQcBadProjectMainService;
import com.saas.pssc.service.IQcMatCheckMainService;
import com.saas.pssc.service.IQcProcessCheckMainService;
import com.saas.pssc.service.IQcProdCheckMainService;
import com.saas.pssc.service.ISdDeliveryDetailService;
import com.saas.pssc.service.ISdDeliveryService;
import com.saas.system.service.ISysDictDataService;
import com.saas.system.service.ISysUserService;

import org.apache.shiro.util.AntPathMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

/**
 * ????????????Controller
 * 
 * @author admin
 * @date 2021-07-22
 */
@Controller
@RequestMapping("/query")
public class QueryController extends BaseController
{
    private String prefix = "query";
    
    private String prefix_vendor = "query/vendor";//??????????????????

    private String prefix_mat = "query/matunusual";//????????????

    private String prefix_proc = "query/procunusual";//????????????

    private String prefix_prod = "query/produnusual";//????????????

    @Autowired
    private ISysUserService sysUserService;//????????????

    @Autowired
    private ISysDictDataService dictDataService;//????????????

    @Autowired
    private IPpWoBookMainService ppWoBookMainService;//??????????????????

    @Autowired
    private IBsBomMainService bsBomMainService;//BOM????????????

    @Autowired
    private IInStoreService inStoreService;//????????????

    @Autowired
    private IBsCraftSopMainService bsCraftSopMainService;//??????????????????

    @Autowired
    private ISdDeliveryService sdDeliveryService;//????????????

    @Autowired
    private IBsPqcMainService bsPqcMainService;//??????????????????    

    @Autowired
    private IBsMcnMainService bsMcnMainService;//4M?????????

    @Autowired
    private IBsMatSopMainService bsMatSopMainService;//??????????????????

    @Autowired
    private IBsMatSopDetailService bsMatSopDetailService;//????????????????????????

    @Autowired
    private IBsProSopMainService bsProSopMainService;//??????????????????

    @Autowired
    private IPpWoBookDetailService ppWoBookDetailService;//??????????????????

    @Autowired
    private IQcMatCheckMainService qcMatCheckMainService;//??????????????????

    @Autowired
    private IQcProcessCheckMainService qcProcessCheckMainService;//??????????????????

    @Autowired
    private IQcProdCheckMainService qcProdCheckMainService;//??????????????????

    @Autowired
    private IQcBadProjectMainService qcBadProjectMainService;//??????????????????

    @Autowired
    private ISdDeliveryDetailService sdDeliveryDetailService;//??????????????????

    @Autowired
    private IBsVendorService bsVendorService;//??????????????????

    /**
     * ????????????????????????
     */
    @PostMapping("/delivery/list")
    @ResponseBody
    public TableDataInfo list(SdDelivery sdDelivery)
    {
        startPage();
        List<SdDelivery> list = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        if(StringUtils.isNotEmpty(list)){
            for(SdDelivery obj:list){
                List<String> qcResultList = qcProdCheckMainService.selectQcResult(obj.getFlot());
                if(StringUtils.isEmpty(qcResultList)){
                    obj.setPpState("0");//?????????
                }else if(qcResultList.size() >= 1 && qcResultList.toString().contains("0")){
                    obj.setPpState("1");//?????? ??????????????????????????????????????????
                }else if(qcResultList.size() == 1 && "1".equals(qcResultList.get(0))){
                    obj.setPpState("2");//?????? ????????????????????????
                }
            }
        }
        return getDataTable(list);
    }
    /**
     * ???????????????
     */
    @PostMapping("/findVendor")
    @ResponseBody
    public AjaxResult findVendor(SdDelivery sdDelivery)
    {
        AjaxResult ajaxResult = AjaxResult.success();
        if(StringUtils.isNotEmpty(sdDelivery.getDlot())){
            List<BsVendor> list = bsVendorService.selectVendor(sdDelivery.getDlot());
            BsVendor bsVendor = new BsVendor();
            if(StringUtils.isNotEmpty(list)){
                bsVendor = list.get(0);
            }
            ajaxResult.put("data", bsVendor);
        }
        return ajaxResult;
    }
    /**
     * ?????????????????????
     */
    @PostMapping("/blockdlot")
    @ResponseBody
    public AjaxResult blockdlot(String dlot)
    {
        String dictType = "dlot_keyword";
        SysDictData sdd = new SysDictData();
        sdd.setDictType(dictType);
        List<SysDictData> list = dictDataService.selectDictDataList(sdd);
        SysDictData dictData = new SysDictData();
        dictData.setDictLabel(dlot);
        dictData.setDictValue(dlot);
        dictData.setDictType(dictType);
        dictData.setDictSort(StringUtils.isNotEmpty(list)?Long.valueOf(list.size()):0);
        dictData.setCreateBy(ShiroUtils.getLoginName());
        dictData.setCreateTime(new Date());
        dictDataService.insertDictData(dictData);
        return success();
    }

    /**
     * ????????????
     */
    @GetMapping("/order/{loginName}/**")
    public String order(@PathVariable(value = "loginName") String loginName,HttpServletRequest request, ModelMap mmap)
    {
        //??????????????????????????????????????????
        final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String flot = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        //??????????????????????????????BOM??????
        PpWoBookMain ppWoBookMain = new PpWoBookMain();
        ppWoBookMain.setAttribute1(flot);
        List<PpWoBookMain> list = ppWoBookMainService.selectPpWoBookMainList(ppWoBookMain);
        if(StringUtils.isNotEmpty(list)){
            ppWoBookMain  = ppWoBookMainService.selectPpWoBookMainById(list.get(0).getId());
            List<PpWoBookBom> ppWoBookBomList = ppWoBookMain.getPpWoBookBomList();
            InStore inStore = new InStore(); 
            List<InStore> inStoreList = inStoreService.selectInStoreList(inStore);
            Map<String,BigDecimal> inStoreMap = new HashMap<String,BigDecimal>();
            if(StringUtils.isNotEmpty(inStoreList)){
                inStoreMap = inStoreList.stream().collect(Collectors.toMap(InStore::getMcode, InStore::getIqty,(oldValue,newValue)->newValue));
            }
            if(StringUtils.isNotEmpty(ppWoBookBomList)){
                for(PpWoBookBom ppWoBookBom : ppWoBookBomList){
                    //????????????????????????????????????
                    ppWoBookBom.setIqty(inStoreMap.get(ppWoBookBom.getMcode()));
                }
            }
            mmap.put("ppWoBookBomList", ppWoBookBomList);
            mmap.put("ppWoBookDetailList", ppWoBookMain.getPpWoBookDetailList());
        }
        //???????????????????????????????????????????????????????????????
        QcProcessCheckMain qcProcessCheckMain = new QcProcessCheckMain();
        qcProcessCheckMain.setQcode(flot);
        List<QcProcessCheckMain> qcProcessCheckMainList = qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
        mmap.put("qcProcessCheckMainList", qcProcessCheckMainList);
        //???????????????????????????????????????????????????????????????
        QcBadProjectMain qcBadProjectMain = new QcBadProjectMain();
        qcBadProjectMain.setWcode(flot);
        List<QcBadProjectMain> qcBadProjectList = qcBadProjectMainService.selectQcBadProjectMainList(qcBadProjectMain);
        mmap.put("qcBadProjectList", qcBadProjectList);
        mmap.put("flot", flot);
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        if(StringUtils.isNotNull(sysUser)){
            mmap.put("vendor", sysUser.getUserName());
        }
        return prefix + "/ppdetail";
    }

    /**
     * ????????????
     */
    @GetMapping("/ppdetail/matcm/{pcode}")
    public String matcm(@PathVariable(value = "pcode", required = false) String pcode, ModelMap mmap)
    {
        //?????????????????????????????????????????????
        QcMatCheckMain qcMatCheckMain = new QcMatCheckMain();
        qcMatCheckMain.setAttribute1(pcode);
        List<QcMatCheckMain> qcMatCheckMainList = qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
        mmap.put("qcMatCheckMainList", qcMatCheckMainList);
        //?????????????????????????????????????????????
        List<BsMatSopDetail> bsMatSopDetailList = bsMatSopDetailService.selectBsMatSopDetailByPcode(pcode);
        mmap.put("bsMatSopDetailList", bsMatSopDetailList);
        mmap.put("pcode", pcode);
        return prefix + "/matcm";
    }

    /**
     * ??????????????????????????????????????????BOM????????????????????????????????????4M?????????
     * ????????????
     */
    @GetMapping("/standard/{pcode}/{loginName}")
    public String standard(@PathVariable("pcode") String pcode,@PathVariable("loginName") String loginName, ModelMap mmap)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pcode", pcode);
        BsBomMain bsBomMain = bsBomMainService.selectBsBomMainByMap(paramMap);
        mmap.put("bsBomMain", bsBomMain);
        BsCraftSopMain bsCraftSopMain = bsCraftSopMainService.selectBsCraftSopMainByMap(paramMap);
        mmap.put("bsCraftSopMain", bsCraftSopMain);
        BsMcnMain bsMcnMain = bsMcnMainService.selectBsMcnMainByMap(paramMap);
        mmap.put("bsMcnMain", bsMcnMain);
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        if(StringUtils.isNotNull(sysUser)){
            mmap.put("vendor", sysUser.getUserName());
        }
        return prefix + "/standard";
    }
    /**
     * ??????????????????????????????????????????
     * ????????????
     */
    @GetMapping("/delivery/{pcode}")
    public String delivery(@PathVariable("pcode") String pcode, ModelMap mmap)
    {
        //??????????????????
        SdDelivery sdDelivery = new SdDelivery();
        sdDelivery.setPcode(pcode);
        List<SdDelivery> sdlist = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        if(StringUtils.isNotEmpty(sdlist)){
            sdDelivery = sdlist.get(0);
        }
        mmap.put("sdDelivery", sdDelivery);
        //????????????????????????
        BsMatSopMain bsMatSopMain = new BsMatSopMain();
        bsMatSopMain.setPcode(pcode);
        List<BsMatSopMain> matList = bsMatSopMainService.selectBsMatSopMainList(bsMatSopMain);
        if(StringUtils.isNotEmpty(matList)){
            bsMatSopMain = bsMatSopMainService.selectBsMatSopMainById(matList.get(0).getId());
        }
        mmap.put("bsMatSopMain", bsMatSopMain);
        //????????????????????????
        BsProSopMain bsProSopMain = new BsProSopMain();
        bsProSopMain.setPcode(pcode);
        List<BsProSopMain> prodList = bsProSopMainService.selectBsProSopMainList(bsProSopMain);
        if(StringUtils.isNotEmpty(prodList)){
            bsProSopMain = bsProSopMainService.selectBsProSopMainById(prodList.get(0).getId());
        }
        mmap.put("bsProSopMain", bsProSopMain);
        //????????????????????????????????????????????????????????????
        BsPqcMain bsPqcMain = new BsPqcMain();
        bsPqcMain.setPcode(pcode);
        List<BsPqcMain> pqcList = bsPqcMainService.selectBsPqcMainList(bsPqcMain);
        if(StringUtils.isNotEmpty(pqcList)){
            bsPqcMain = bsPqcMainService.selectBsPqcMainById(pqcList.get(0).getId());
        }
        mmap.put("bsPqcMain", bsPqcMain);
        return prefix + "/delivery";
    }
    

    /**
     * ??????
     */
    @GetMapping("/remark/{id}/{dlot}")
    public String remark(@PathVariable(value = "id", required = false) String id,@PathVariable(value = "dlot", required = false) String dlot, ModelMap mmap)
    {
        SdDeliveryDetail sdDeliveryDetail = new SdDeliveryDetail();
        sdDeliveryDetail.setMainId(id);
        List<SdDeliveryDetail> sdDeliveryDetailList = sdDeliveryDetailService.selectSdDeliveryDetailList(sdDeliveryDetail);
        SdDelivery sdDelivery = new SdDelivery();
        sdDelivery.setId(id);
        sdDelivery.setDlot(dlot);
        sdDelivery.setSdDeliveryDetailList(sdDeliveryDetailList);
        mmap.put("sdDelivery", sdDelivery);
        return prefix + "/remark";
    }

    /**
     * ??????????????????
     */
    @GetMapping(value = {"/remark/toUpload/{rowIndex}"})
    public String toUpload(@PathVariable(value="rowIndex") Long rowIndex,ModelMap mmap)
    {
        mmap.put("rowIndex", rowIndex);
        return prefix + "/upload";
    }

    /**
     * ??????????????????
     */
    @Log(title = "????????????", businessType = BusinessType.UPDATE)
    @PostMapping("/remark/edit")
    @ResponseBody
    public AjaxResult editSave(SdDelivery sdDelivery)
    {
        sdDeliveryService.batchSdDeliveryDetail(sdDelivery);
        return AjaxResult.success();
    }

    //??????????????????
    @PostMapping("/loadPieChartLeft")
	@ResponseBody
	public AjaxResult loadPieChartLeft(String dcode) {
        AjaxResult ajaxResult = AjaxResult.success();
        //?????????????????????????????????????????????????????????????????????????????????/????????????????????????????????????10?????????????????????
        if(StringUtils.isNotEmpty(dcode)){
            // List<QcBadProjectDetail> projectList= qcBadProjectDetailService.loadPieChartBydcode(dcode); 
            // ajaxResult.put("projectList", projectList);
        }
        return ajaxResult;
    }
    
    //??????????????????
    @PostMapping("/loadPieChartRight")
	@ResponseBody
	public AjaxResult loadPieChartRight(String vendor) {
        AjaxResult ajaxResult = AjaxResult.success();
        //?????????????????????????????????????????????/??????????????????
        if(StringUtils.isNotEmpty(vendor)){
            // List<QcBadProjectDetail> pnameList= qcBadProjectDetailService.loadPieChartByVendor(vendor);
            // ajaxResult.put("pnameList", pnameList);
        }
        return ajaxResult;
    }
    
    // ???????????????
	@PostMapping("/loadLineChart")
	@ResponseBody
	public Map<String, Object> loadLineChart(String dcode) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        if(dcode != null){
            List<PpWoBookDetail> ppWoBookDetailList = ppWoBookDetailService.loadLineChart(dcode);
            //??????????????????
            List<String> cworkList =  ppWoBookDetailList.stream().map(PpWoBookDetail::getCwork).distinct().collect(Collectors.toList());
            retMap.put("cworkList", StringUtils.isNotEmpty(cworkList)?cworkList.toArray():"");
            //??????????????????
            List<String> soncodeList =  ppWoBookDetailList.stream().map(PpWoBookDetail::getSoncode).distinct().collect(Collectors.toList());
            retMap.put("soncodeList", StringUtils.isNotEmpty(soncodeList)?soncodeList.toArray():"");
            //?????????????????? ???????????????
            JSONArray actualYieldList = new JSONArray();
            if(StringUtils.isNotEmpty(ppWoBookDetailList) && StringUtils.isNotEmpty(soncodeList) && StringUtils.isNotEmpty(cworkList)){
                for(String soncode:soncodeList){
                    JSONObject item = new JSONObject();
                    item.put("name", soncode);
                    item.put("type", "line");
                    List<String> list = new ArrayList<String>();
                    for(int i = 0;i<cworkList.size();i++){
                        Map<String,Object> paramMap = new HashMap<String,Object>();
                        paramMap.put("soncode", soncode);
                        paramMap.put("cwork", cworkList.get(i));
                        PpWoBookDetail obj = ppWoBookDetailService.selectPpWoBookDetailByMap(paramMap);
                        if(obj != null && obj.getActrate() != null){
                            list.add(obj.getActrate().replaceAll("%", ""));
                        }else{
                            list.add("0");
                        }
                        item.put("data", list);
                    }
                    actualYieldList.add(item);
                }
                retMap.put("actualYieldList",actualYieldList);
            }
        }
        return retMap;
    }

    @GetMapping("/qc/prodcm/in/{pcode}/{lot}/{qcResult}")
    public String prodcm(@PathVariable(value = "pcode", required = false) String pcode,@PathVariable(value = "lot", required = false) String lot,@PathVariable(value = "qcResult", required = false) String qcResult, ModelMap mmap)
    {
        mmap.put("pcode", pcode);
        mmap.put("lot", lot);
        mmap.put("qcResult", qcResult);
        return prefix + "/prodcmin";
    }
    /**
     * ????????????????????????????????????
     */
    @PostMapping("/qc/prodcm/in/list")
    @ResponseBody
    public TableDataInfo list(QcProdCheckMain qcProdCheckMain)
    {
        startPage();
        qcProdCheckMain.setOperType("0");
        List<QcProdCheckMain> list = qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
        return getDataTable(list);
    }
    /**
     * ??????????????????????????????
     */
    @GetMapping("/qc/prodcm/in/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProdCheckMain qcProdCheckMain = qcProdCheckMainService.selectQcProdCheckMainById(id);
        mmap.put("qcProdCheckMain", qcProdCheckMain);
        return prefix + "/prodcmindetail";
    }

    /**
     * ??????????????????
    */
    @GetMapping("/vendor/{loginName}")
    public String vendor(@PathVariable(value = "loginName") String loginName, ModelMap mmap)
    {
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        if(StringUtils.isNotNull(sysUser)){
            mmap.put("vendor", sysUser.getUserName());
        }
        return prefix_vendor + "/vendor";
    }
    /**
     * ?????????????????????
     */
    @PostMapping("/vendor/list")
    @ResponseBody
    public TableDataInfo vendorList(BsVendor bsVendor)
    {
        startPage();
        List<BsVendor> list = bsVendorService.selectBsVendorList(bsVendor);
        return getDataTable(list);
    }
    /**
     * ?????????????????????
     */
    @GetMapping("/vendor/detail/{id}")
    public String vendorDetail(@PathVariable("id") String id, ModelMap mmap)
    {
        BsVendor bsVendor = bsVendorService.selectBsVendorById(id);
        mmap.put("bsVendor", bsVendor);
        return prefix_vendor + "/detail";
    }

    /**
     * ??????????????????
    */
    @GetMapping("/matunusual/{qcResult}/{loginName}")
    public String matunusual(@PathVariable(value = "qcResult") String qcResult,@PathVariable(value = "loginName") String loginName, ModelMap mmap)
    {
        mmap.put("qcResult", qcResult);
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        if(StringUtils.isNotNull(sysUser)){
            mmap.put("vendor", sysUser.getUserName());
        }
        return prefix_mat + "/matunusual";
    }
    /**
     * ????????????????????????
     */
    @PostMapping("/matunusual/list")
    @ResponseBody
    public TableDataInfo matunusualList(QcMatCheckMain qcMatCheckMain)
    {
        startPage();
        List<QcMatCheckMain> list = qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
        return getDataTable(list);
    }
    /**
     * ????????????????????????
     */
    @GetMapping("/matunusual/detail/{id}")
    public String matunusualDetail(@PathVariable("id") String id, ModelMap mmap)
    {
        QcMatCheckMain qcMatCheckMain = qcMatCheckMainService.selectQcMatCheckMainById(id);
        mmap.put("qcMatCheckMain", qcMatCheckMain);
        return prefix_mat + "/detail";
    }

    /**
     * ??????????????????
    */
    @GetMapping("/procunusual/{qcResult}/{loginName}/**")
    public String procunusual(@PathVariable(value = "qcResult") String qcResult,@PathVariable(value = "loginName") String loginName,HttpServletRequest request, ModelMap mmap)
    {
        mmap.put("qcResult", qcResult);
        //??????????????????????????????????????????
        final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        mmap.put("qcode", arguments);
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        if(StringUtils.isNotNull(sysUser)){
            mmap.put("vendor", sysUser.getUserName());
        }
        return prefix_proc + "/procunusual";
    }
    /**
     * ????????????????????????
     */
    @PostMapping("/procunusual/list")
    @ResponseBody
    public TableDataInfo procunusualList(QcProcessCheckMain qcProcessCheckMain)
    {
        startPage();
        List<QcProcessCheckMain> list = qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
        return getDataTable(list);
    }
    /**
     * ????????????????????????
     */
    @GetMapping("/procunusual/detail/{id}")
    public String procunusualDetail(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProcessCheckMain qcProcessCheckMain = qcProcessCheckMainService.selectQcProcessCheckMainById(id);
        mmap.put("qcProcessCheckMain", qcProcessCheckMain);
        return prefix_proc + "/detail";
    }

    /**
     * ???????????????????????????????????????????????????
    */
    @GetMapping(value = {"/produnusual/{qcResult}/{operType}/{loginName}/**"})
    public String produnusual(@PathVariable(value = "qcResult") String qcResult, @PathVariable(value = "operType") String operType,@PathVariable(value = "loginName",required = false) String loginName,HttpServletRequest request, ModelMap mmap)
    {
        mmap.put("qcResult", qcResult);
        mmap.put("operType", operType);
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        if(StringUtils.isNotNull(sysUser)){
            mmap.put("vendor", sysUser.getUserName());
        }
        //??????????????????????????????????????????
        final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        mmap.put("lot", arguments);
        return prefix_prod + "/produnusual";
    }
    /**
     * ?????????????????????????????????????????????????????????
     */
    @PostMapping("/produnusual/list")
    @ResponseBody
    public TableDataInfo produnusualList(QcProdCheckMain qcProdCheckMain)
    {
        startPage();
        List<QcProdCheckMain> list = qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
        return getDataTable(list);
    }
    /**
     * ?????????????????????????????????????????????????????????
     */
    @GetMapping("/produnusual/detail/{id}")
    public String produnusualDetail(@PathVariable("id") String id, ModelMap mmap)
    {
        QcProdCheckMain qcProdCheckMain = qcProdCheckMainService.selectQcProdCheckMainById(id);
        mmap.put("qcProdCheckMain", qcProdCheckMain);
        return prefix_prod + "/detail";
    }
}
