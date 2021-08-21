package com.saas.pssc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saas.common.annotation.Log;
import com.saas.common.core.controller.BaseController;
import com.saas.common.core.domain.AjaxResult;
import com.saas.common.core.page.TableDataInfo;
import com.saas.common.enums.BusinessType;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.domain.PpWoBookBom;
import com.saas.pssc.domain.PpWoBookDetail;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.domain.QcBadProjectMain;
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.domain.SdDeliveryDetail;
import com.saas.pssc.domain.SdOrder;
import com.saas.pssc.service.IBsCraftSopMainService;
import com.saas.pssc.service.IBsMatSopMainService;
import com.saas.pssc.service.IBsMcnMainService;
import com.saas.pssc.service.IBsPqcMainService;
import com.saas.pssc.service.IBsProSopMainService;
import com.saas.pssc.service.IPpWoBookBomService;
import com.saas.pssc.service.IPpWoBookDetailService;
import com.saas.pssc.service.IPpWoBookMainService;
import com.saas.pssc.service.IQcBadProjectMainService;
import com.saas.pssc.service.IQcProcessCheckMainService;
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
    private IBsProSopMainService bsProSopMainService;//成品检验标准

    @Autowired
    private IPpWoBookBomService ppWoBookBomService;//工单BOM信息

    @Autowired
    private IPpWoBookDetailService ppWoBookDetailService;//工单制造信息

    @Autowired
    private IQcProcessCheckMainService qcProcessCheckMainService;//过程检验记录

    @Autowired
    private IQcBadProjectMainService qcBadProjectMainService;//不良项目记录

    @Autowired
    private ISdDeliveryDetailService sdDeliveryDetailService;//发货明细信息
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
        PpWoBookBom ppWoBookBom = new PpWoBookBom();
        ppWoBookBom.setAttribute1(pcode);
        List<PpWoBookBom> ppWoBookBomList =  ppWoBookBomService.selectPpWoBookBomList(ppWoBookBom);
        mmap.put("ppWoBookBomList", ppWoBookBomList);
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
     * 根据产品编号和供应商查询产品BOM信息、产品工艺技术标准和4M变更单
     * 查询标准
     */
    @GetMapping("/standard/{pcode}")
    public String standard(@PathVariable("pcode") String pcode, ModelMap mmap)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pcode", pcode);
        PpWoBookMain ppWoBookMain = ppWoBookMainService.selectPpWoBookMainByMap(paramMap);
        mmap.put("ppWoBookMain", ppWoBookMain);
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
}
