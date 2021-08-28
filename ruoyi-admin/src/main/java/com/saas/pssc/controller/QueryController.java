package com.saas.pssc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.BsBomMain;
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.domain.BsMatSopDetail;
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.domain.BsVendor;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.domain.QcBadProjectDetail;
import com.saas.pssc.domain.QcBadProjectMain;
import com.saas.pssc.domain.QcMatCheckMain;
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.domain.SdDeliveryDetail;
import com.saas.pssc.domain.SdOrder;
import com.saas.pssc.service.IBsBomMainService;
import com.saas.pssc.service.IBsCraftSopMainService;
import com.saas.pssc.service.IBsMatSopDetailService;
import com.saas.pssc.service.IBsMatSopMainService;
import com.saas.pssc.service.IBsMcnMainService;
import com.saas.pssc.service.IBsPqcMainService;
import com.saas.pssc.service.IBsProSopMainService;
import com.saas.pssc.service.IBsVendorService;
import com.saas.pssc.service.IPpWoBookDetailService;
import com.saas.pssc.service.IPpWoBookMainService;
import com.saas.pssc.service.IQcBadProjectDetailService;
import com.saas.pssc.service.IQcBadProjectMainService;
import com.saas.pssc.service.IQcMatCheckMainService;
import com.saas.pssc.service.IQcProcessCheckMainService;
import com.saas.pssc.service.IQcProdCheckMainService;
import com.saas.pssc.service.ISdDeliveryDetailService;
import com.saas.pssc.service.ISdDeliveryService;
import com.saas.pssc.service.ISdOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 综合查询Controller
 * 
 * @author admin
 * @date 2021-07-22
 */
@Controller
@RequestMapping("/query")
public class QueryController extends BaseController
{
    private String prefix = "query";

    @Autowired
    private ISdOrderService sdOrderService;//订单信息

    @Autowired
    private IPpWoBookMainService ppWoBookMainService;//工单记录信息

    @Autowired
    private IBsBomMainService bsBomMainService;//BOM明细信息

    @Autowired
    private IBsCraftSopMainService bsCraftSopMainService;//工艺技术标准

    @Autowired
    private ISdDeliveryService sdDeliveryService;//库存标准

    @Autowired
    private IBsPqcMainService bsPqcMainService;//产品检验标准    

    @Autowired
    private IBsMcnMainService bsMcnMainService;//4M变更单

    @Autowired
    private IBsMatSopMainService bsMatSopMainService;//材料检验标准

    @Autowired
    private IBsMatSopDetailService bsMatSopDetailService;//材料检验标准明细

    @Autowired
    private IBsProSopMainService bsProSopMainService;//成品检验标准

    @Autowired
    private IPpWoBookDetailService ppWoBookDetailService;//工单制造信息

    @Autowired
    private IQcMatCheckMainService qcMatCheckMainService;//材料检验记录

    @Autowired
    private IQcProcessCheckMainService qcProcessCheckMainService;//过程检验记录

    @Autowired
    private IQcProdCheckMainService qcProdCheckMainService;//成品检验记录

    @Autowired
    private IQcBadProjectMainService qcBadProjectMainService;//不良项目记录

    @Autowired
    private IQcBadProjectDetailService qcBadProjectDetailService;//不良项目记录明细

    @Autowired
    private ISdDeliveryDetailService sdDeliveryDetailService;//发货明细信息

    @Autowired
    private IBsVendorService bsVendorService;//供方名录信息

    /**
     * 查询发货信息列表
     */
    @PostMapping("/delivery/list")
    @ResponseBody
    public TableDataInfo list(SdDelivery sdDelivery)
    {
        startPage();
        List<SdDelivery> list = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        if(StringUtils.isNotEmpty(list)){
            for(SdDelivery obj:list){
                List<String> qcResultList = qcProdCheckMainService.selectQcResult(obj.getDcode());
                if(StringUtils.isEmpty(qcResultList)){
                    obj.setPpState("0");//未填报
                }else if(qcResultList.size() >= 1 && qcResultList.toString().contains("0")){
                    obj.setPpState("1");//异常 中间有一条为不合格即为异常，
                }else if(qcResultList.size() == 1 && "1".equals(qcResultList.toString())){
                    obj.setPpState("2");//合格 所有数据都要合格
                }
            }
        }
        return getDataTable(list);
    }
    /**
     * 查看供应商
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
     * 查询订单
     */
    @GetMapping("/order/{ocode}")
    public String order(@PathVariable("ocode") String ocode, ModelMap mmap)
    {
        mmap.put("ocode", ocode);
        return prefix + "/order";
    }

    /**
     * 查询订单信息列表
     */
    @PostMapping("/order/list")
    @ResponseBody
    public TableDataInfo list(SdOrder sdOrder)
    {
        startPage();
        List<SdOrder> list = sdOrderService.selectOrderAndPpList(sdOrder);
        return getDataTable(list);
    }

    /**
     * 查询工单
     */
    @GetMapping("/ppwb/{orderno}")
    public String ppwb(@PathVariable("orderno") String orderno, ModelMap mmap)
    {
        mmap.put("orderno", orderno);
        return prefix + "/ppwb";
    }

    /**
     * 查询工单信息列表
     */
    @PostMapping("/ppwb/list")
    @ResponseBody
    public TableDataInfo list(PpWoBookMain ppWoBookMain)
    {
        startPage();
        List<PpWoBookMain> list = ppWoBookMainService.selectPpWoBookMainList(ppWoBookMain);
        return getDataTable(list);
    }

    /**
     * 查询详情
     */
    @GetMapping("/ppdetail/{wcode}/{pcode}")
    public String detail(@PathVariable(value = "wcode", required = false) String wcode,@PathVariable(value = "pcode", required = false) String pcode, ModelMap mmap)
    {
        //根据产品编号查询工单BOM信息
        PpWoBookMain ppWoBookMain = new PpWoBookMain();
        ppWoBookMain.setPcode(pcode);
        List<PpWoBookMain> list = ppWoBookMainService.selectPpWoBookMainList(ppWoBookMain);
        if(StringUtils.isNotEmpty(list)){
            ppWoBookMain  = ppWoBookMainService.selectPpWoBookMainById(list.get(0).getId());
            mmap.put("ppWoBookBomList", ppWoBookMain.getPpWoBookBomList());
        }
        //根据母工单号模糊查询子工单制造信息
        PpWoBookDetail ppWoBookDetail = new PpWoBookDetail();
        ppWoBookDetail.setSoncode(wcode);
        List<PpWoBookDetail> ppWoBookDetailList = ppWoBookDetailService.selectPpWoBookDetailList(ppWoBookDetail);
        mmap.put("ppWoBookDetailList", ppWoBookDetailList);
        //根据母工单号模糊查询子工单过程检验记录信息
        QcProcessCheckMain qcProcessCheckMain = new QcProcessCheckMain();
        qcProcessCheckMain.setQcode(wcode);
        List<QcProcessCheckMain> qcProcessCheckMainList = qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
        mmap.put("qcProcessCheckMainList", qcProcessCheckMainList);
        //根据母工单号模糊查询子工单不良项目记录信息
        QcBadProjectMain qcBadProjectMain = new QcBadProjectMain();
        qcBadProjectMain.setWcode(wcode);
        List<QcBadProjectMain> qcBadProjectMainList = qcBadProjectMainService.selectQcBadProjectMainList(qcBadProjectMain);
        mmap.put("qcBadProjectMainList", qcBadProjectMainList);
        mmap.put("wcode", wcode);
        mmap.put("pcode", pcode);
        return prefix + "/ppdetail";
    }

    /**
     * 查询详情
     */
    @GetMapping("/ppdetail/matcm/{pcode}")
    public String matcm(@PathVariable(value = "pcode", required = false) String pcode, ModelMap mmap)
    {
        //根据产品编号查询原材料检验记录
        QcMatCheckMain qcMatCheckMain = new QcMatCheckMain();
        qcMatCheckMain.setAttribute1(pcode);
        List<QcMatCheckMain> qcMatCheckMainList = qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
        mmap.put("qcMatCheckMainList", qcMatCheckMainList);
        //根据产品编号查询原材料检验标准
        List<BsMatSopDetail> bsMatSopDetailList = bsMatSopDetailService.selectBsMatSopDetailByPcode(pcode);
        mmap.put("bsMatSopDetailList", bsMatSopDetailList);
        mmap.put("pcode", pcode);
        return prefix + "/matcm";
    }

    /**
     * 根据产品编号和供应商查询产品BOM信息、产品工艺技术标准和4M变更单
     * 查询标准
     */
    @GetMapping("/standard/{pcode}")
    public String standard(@PathVariable("pcode") String pcode, ModelMap mmap)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pcode", pcode);
        BsBomMain bsBomMain = bsBomMainService.selectBsBomMainByMap(paramMap);
        mmap.put("bsBomMain", bsBomMain);
        BsCraftSopMain bsCraftSopMain = bsCraftSopMainService.selectBsCraftSopMainByMap(paramMap);
        mmap.put("bsCraftSopMain", bsCraftSopMain);
        BsMcnMain bsMcnMain = bsMcnMainService.selectBsMcnMainByMap(paramMap);
        mmap.put("bsMcnMain", bsMcnMain);
        return prefix + "/standard";
    }
    /**
     * 根据产品编号查库存及检验标准
     * 查询标准
     */
    @GetMapping("/delivery/{pcode}")
    public String delivery(@PathVariable("pcode") String pcode, ModelMap mmap)
    {
        //查询库存信息
        SdDelivery sdDelivery = new SdDelivery();
        sdDelivery.setPcode(pcode);
        List<SdDelivery> sdlist = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        if(StringUtils.isNotEmpty(sdlist)){
            sdDelivery = sdlist.get(0);
        }
        mmap.put("sdDelivery", sdDelivery);
        //查询材料检验标准
        BsMatSopMain bsMatSopMain = new BsMatSopMain();
        bsMatSopMain.setPcode(pcode);
        List<BsMatSopMain> matList = bsMatSopMainService.selectBsMatSopMainList(bsMatSopMain);
        if(StringUtils.isNotEmpty(matList)){
            bsMatSopMain = bsMatSopMainService.selectBsMatSopMainById(matList.get(0).getId());
        }
        mmap.put("bsMatSopMain", bsMatSopMain);
        //成品材料检验标准
        BsProSopMain bsProSopMain = new BsProSopMain();
        bsProSopMain.setPcode(pcode);
        List<BsProSopMain> prodList = bsProSopMainService.selectBsProSopMainList(bsProSopMain);
        if(StringUtils.isNotEmpty(prodList)){
            bsProSopMain = bsProSopMainService.selectBsProSopMainById(prodList.get(0).getId());
        }
        mmap.put("bsProSopMain", bsProSopMain);
        //过程首检标准、过程自检标准和过程专检标准
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
     * 标注
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
     * 标注上传文件
     */
    @GetMapping(value = {"/remark/toUpload/{rowIndex}"})
    public String toUpload(@PathVariable(value="rowIndex") Long rowIndex,ModelMap mmap)
    {
        mmap.put("rowIndex", rowIndex);
        return prefix + "/upload";
    }

    /**
     * 修改保存标注
     */
    @Log(title = "保存标注", businessType = BusinessType.UPDATE)
    @PostMapping("/remark/edit")
    @ResponseBody
    public AjaxResult editSave(SdDelivery sdDelivery)
    {
        sdDeliveryService.batchSdDeliveryDetail(sdDelivery);
        return AjaxResult.success();
    }

    //加载左侧饼图
    @PostMapping("/loadPieChartLeft")
	@ResponseBody
	public AjaxResult loadPieChartLeft(String dcode) {
        AjaxResult ajaxResult = AjaxResult.success();
        //同订单号下所有工单→不良项目记录明细信息→不良项目名称/不良项目数量（显示数量前10位的不良项目）
        if(StringUtils.isNotEmpty(dcode)){
            List<QcBadProjectDetail> projectList= qcBadProjectDetailService.loadPieChartBydcode(dcode); 
            ajaxResult.put("projectList", projectList);
        }
        return ajaxResult;
    }
    
    //加载右侧饼图
    @PostMapping("/loadPieChartRight")
	@ResponseBody
	public AjaxResult loadPieChartRight(String vendor) {
        AjaxResult ajaxResult = AjaxResult.success();
        //所选用户下所有产品不良项目名称/不良项目数量
        if(StringUtils.isNotEmpty(vendor)){
            List<QcBadProjectDetail> pnameList= qcBadProjectDetailService.loadPieChartByVendor(vendor);
            ajaxResult.put("pnameList", pnameList);
        }
        return ajaxResult;
    }
    
    // 加载折线图
	@PostMapping("/loadLineChart")
	@ResponseBody
	public Map<String, Object> loadLineChart(String dcode) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        if(dcode != null){
            List<PpWoBookDetail> ppWoBookDetailList = ppWoBookDetailService.loadLineChart(dcode);
            //计划工单号列表
            List<String> wCodeList =  ppWoBookDetailList.stream().map(PpWoBookDetail::getSoncode).collect(Collectors.toList());
            retMap.put("wCodeList", StringUtils.isNotEmpty(wCodeList)?wCodeList.toArray():"");
            //标准成品率
            String standardYield = ppWoBookDetailList.stream().map(PpWoBookDetail::getStdrate).collect(Collectors.joining(";"));
            if(StringUtils.isNotEmpty(standardYield)){
                standardYield = standardYield.replaceAll("%", "");
                List<String> standardYieldList = Arrays.asList(standardYield.split(";"));
                retMap.put("standardYieldList",standardYieldList);
            }
            //实际成品率
            String actualYield = ppWoBookDetailList.stream().map(PpWoBookDetail::getActrate).collect(Collectors.joining(";"));
            if(StringUtils.isNotEmpty(actualYield)){
                actualYield = actualYield.replaceAll("%", "");
                List<String> actualYieldList = Arrays.asList(actualYield.split(";"));
                retMap.put("actualYieldList",actualYieldList);
            }
        }
        return retMap;
    }
}
