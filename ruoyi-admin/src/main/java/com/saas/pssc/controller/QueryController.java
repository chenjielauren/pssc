package com.saas.pssc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saas.common.core.controller.BaseController;
import com.saas.common.utils.StringUtils;
import com.saas.pssc.domain.BsCraftSopMain;
import com.saas.pssc.domain.BsMatSopMain;
import com.saas.pssc.domain.BsMcnMain;
import com.saas.pssc.domain.BsPqcMain;
import com.saas.pssc.domain.BsProSopMain;
import com.saas.pssc.domain.BsVendor;
import com.saas.pssc.domain.PpWoBookMain;
import com.saas.pssc.domain.QcMatCheckMain;
import com.saas.pssc.domain.QcProcessCheckMain;
import com.saas.pssc.domain.QcProdCheckMain;
import com.saas.pssc.domain.SdDelivery;
import com.saas.pssc.domain.SdOrder;
import com.saas.pssc.service.IBsCraftSopMainService;
import com.saas.pssc.service.IBsMatSopMainService;
import com.saas.pssc.service.IBsMcnMainService;
import com.saas.pssc.service.IBsPqcMainService;
import com.saas.pssc.service.IBsProSopMainService;
import com.saas.pssc.service.IBsVendorService;
import com.saas.pssc.service.IPpWoBookMainService;
import com.saas.pssc.service.IQcMatCheckMainService;
import com.saas.pssc.service.IQcProcessCheckMainService;
import com.saas.pssc.service.IQcProdCheckMainService;
import com.saas.pssc.service.ISdDeliveryService;
import com.saas.pssc.service.ISdOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private IPpWoBookMainService ppWoBookMainService;//产品BOM信息

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
    private IQcProdCheckMainService qcProdCheckMainService;//成品检验记录

    @Autowired
    private IBsVendorService bsVendorService;//合格供方目录

    @Autowired
    private IQcMatCheckMainService qcMatCheckMainService;//材料检验记录

    @Autowired
    private IQcProcessCheckMainService qcProcessCheckMainService;//过程检验记录

    /**
     * 查询订单
     */
    @GetMapping("/order/{pcode}/{vendor}")
    public String order(@PathVariable("pcode") String pcode,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        SdOrder sdOrder = new SdOrder();
        sdOrder.setPcode(pcode);
        sdOrder.setCreateBy(vendor);
        List<SdOrder> orderList = sdOrderService.selectSdOrderList(sdOrder);
        mmap.put("orderList", orderList);
        return prefix + "/order";
    }

    /**
     * 根据产品编号和供应商查询产品BOM信息、产品工艺技术标准和4M变更单
     * 查询标准
     */
    @GetMapping("/standard/{pcode}/{vendor}")
    public String standard(@PathVariable("pcode") String pcode,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pcode", pcode);
        paramMap.put("vendor", vendor);
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
    @GetMapping("/delivery/{pcode}/{vendor}")
    public String delivery(@PathVariable("pcode") String pcode,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        //查询库存信息
        SdDelivery sdDelivery = new SdDelivery();
        sdDelivery.setPcode(pcode);
        sdDelivery.setCreateBy(vendor);
        List<SdDelivery> sdlist = sdDeliveryService.selectSdDeliveryList(sdDelivery);
        if(StringUtils.isNotEmpty(sdlist)){
            sdDelivery = sdlist.get(0);
        }
        mmap.put("sdDelivery", sdDelivery);
        //查询材料检验标准
        BsMatSopMain bsMatSopMain = new BsMatSopMain();
        bsMatSopMain.setPcode(pcode);
        bsMatSopMain.setCreateBy(vendor);
        List<BsMatSopMain> matList = bsMatSopMainService.selectBsMatSopMainList(bsMatSopMain);
        if(StringUtils.isNotEmpty(matList)){
            bsMatSopMain = bsMatSopMainService.selectBsMatSopMainById(matList.get(0).getId());
        }
        mmap.put("bsMatSopMain", bsMatSopMain);
        //成品材料检验标准
        BsProSopMain bsProSopMain = new BsProSopMain();
        bsProSopMain.setPcode(pcode);
        bsProSopMain.setCreateBy(vendor);
        List<BsProSopMain> prodList = bsProSopMainService.selectBsProSopMainList(bsProSopMain);
        if(StringUtils.isNotEmpty(prodList)){
            bsProSopMain = bsProSopMainService.selectBsProSopMainById(prodList.get(0).getId());
        }
        mmap.put("bsProSopMain", bsProSopMain);
        //过程首检标准、过程自检标准和过程专检标准
        BsPqcMain bsPqcMain = new BsPqcMain();
        bsPqcMain.setPcode(pcode);
        bsPqcMain.setCreateBy(vendor);
        List<BsPqcMain> pqcList = bsPqcMainService.selectBsPqcMainList(bsPqcMain);
        if(StringUtils.isNotEmpty(pqcList)){
            bsPqcMain = bsPqcMainService.selectBsPqcMainById(pqcList.get(0).getId());
        }
        mmap.put("bsPqcMain", bsPqcMain);
        return prefix + "/delivery";
    }
   
    /**
     * 查询成品检验记录
     */
    @GetMapping("/prodcm/{pcodes}/{vendor}")
    public String prodcm(@PathVariable("pcodes") String[] pcodes,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        QcProdCheckMain qcProdCheckMain = new QcProdCheckMain();
        qcProdCheckMain.setPcodes(pcodes);//多选产品编号
        qcProdCheckMain.setCreateBy(vendor);//供应商
        List<QcProdCheckMain> prodList = qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
        mmap.put("prodList", prodList);
        return prefix + "/prodcm";
    }
    
    /**
     * 查询合格供方
     */
    @GetMapping("/bsvendor/{vendor}")
    public String bsvendor(@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        BsVendor bsVendor = new BsVendor();
        bsVendor.setCreateBy(vendor);
        List<BsVendor> bsVendorList = bsVendorService.selectBsVendorList(bsVendor);
        mmap.put("bsVendorList", bsVendorList);
        return prefix + "/vendor";
    }

    /**
     * 查询材料异常
     */
    @GetMapping("/matcm/unusual/{pcode}/{vendor}")
    public String matcmunusual(@PathVariable("pcode") String pcode,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        QcMatCheckMain qcMatCheckMain = new QcMatCheckMain();
        List<QcMatCheckMain> qcMatCheckMainList = qcMatCheckMainService.selectQcMatCheckMainList(qcMatCheckMain);
        mmap.put("qcMatCheckMainList", qcMatCheckMainList);
        return prefix + "/matcmunusual";
    }
    /**
     * 查询过程异常
     */
    @GetMapping("/proccm/unusual/{pcode}/{vendor}")
    public String proccmunusual(@PathVariable("pcode") String pcode,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        QcProcessCheckMain qcProcessCheckMain = new QcProcessCheckMain();
        List<QcProcessCheckMain> qcProcessCheckMainList = qcProcessCheckMainService.selectQcProcessCheckMainList(qcProcessCheckMain);
        mmap.put("qcProcessCheckMainList", qcProcessCheckMainList);
        return prefix + "/proccmunusual";
    }
    /**
     * 查询成品异常
     */
    @GetMapping("/prodcm/unusual/{pcode}/{vendor}")
    public String prodcmunusual(@PathVariable("pcode") String pcode,@PathVariable("vendor") String vendor, ModelMap mmap)
    {
        QcProdCheckMain qcProdCheckMain = new QcProdCheckMain();
        List<QcProdCheckMain> qcProdCheckMainList = qcProdCheckMainService.selectQcProdCheckMainList(qcProdCheckMain);
        mmap.put("qcProdCheckMainList", qcProdCheckMainList);
        return prefix + "/prodcmunusual";
    }
}
