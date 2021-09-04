package com.saas.pssc.service;

import java.util.List;
import com.saas.pssc.domain.QcProdCheckMain;

/**
 * 成品入库检验记录或成品出货检验记录Service接口
 * 
 * @author admin
 * @date 2021-07-25
 */
public interface IQcProdCheckMainService 
{
    /**
     * 查询成品入库检验记录或成品出货检验记录
     * 
     * @param id 成品入库检验记录或成品出货检验记录ID
     * @return 成品入库检验记录或成品出货检验记录
     */
    public QcProdCheckMain selectQcProdCheckMainById(String id);

    /**
     * 查询成品入库检验记录或成品出货检验记录列表
     * 
     * @param qcProdCheckMain 成品入库检验记录或成品出货检验记录
     * @return 成品入库检验记录或成品出货检验记录集合
     */
    public List<QcProdCheckMain> selectQcProdCheckMainList(QcProdCheckMain qcProdCheckMain);

    /**
     * 新增成品入库检验记录或成品出货检验记录
     * 
     * @param qcProdCheckMain 成品入库检验记录或成品出货检验记录
     * @return 结果
     */
    public int insertQcProdCheckMain(QcProdCheckMain qcProdCheckMain);

    /**
     * 修改成品入库检验记录或成品出货检验记录
     * 
     * @param qcProdCheckMain 成品入库检验记录或成品出货检验记录
     * @return 结果
     */
    public int updateQcProdCheckMain(QcProdCheckMain qcProdCheckMain);

    /**
     * 批量删除成品入库检验记录或成品出货检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQcProdCheckMainByIds(String ids);

    /**
     * 删除基本信息
     * 
     * @param id 成品入库检验记录或成品出货检验记录ID
     * @return 结果
     */
    public int deleteQcProdCheckMainById(String id);

    /**
     * 首页的成品批次号查找成品检验记录对应的批次号结果
     * 
     * @param dcode 订单号
     * @return 结果
     */
	public List<String> selectQcResult(String lot);
}
