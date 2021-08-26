$("#bootstrap-table2").on("post-body.bs.table", function (e, args) {
    //开工时间
    $("input[name$='stime']").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
    //完工时间
    $("input[name$='etime']").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
    
});
//设置BOM信息行变色
function setRowStyle(row,index){
    if($.common.isNotEmpty(row.attribute) && row.attribute == '0'){
        return {
            css: {
                background: '#66cc99'
            }
        } 
    }else{
        return {
            css: {
                
            }
        }
    }
}
//增加工单BOM信息行或工单制造信息行
function addColumn(mtype,obj) {
    if(mtype == 0){//工单BOM信息行
        var count = $("#bootstrap-table1").bootstrapTable('getData').length;
        sub.editColumn();
        debugger;
        $("#bootstrap-table1").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                orderno: $.common.isNotEmpty(obj)?obj.orderno:"",
                mcode: $.common.isNotEmpty(obj)?obj.mcode:"",
                mname: $.common.isNotEmpty(obj)?obj.mname:"",
                mspec: $.common.isNotEmpty(obj)?obj.mspec:"",
                attribute: $.common.isNotEmpty(obj)?obj.attribute:"",
                onsize: $.common.isNotEmpty(obj)?obj.onsize:"",
                moqieqty: $.common.isNotEmpty(obj)?obj.moqieqty:"",
                qiezhiqty: $.common.isNotEmpty(obj)?obj.qiezhiqty:"",
                qty: $.common.isNotEmpty(obj)?obj.qty:"",
                unit: $.common.isNotEmpty(obj)?obj.unit:"",
                unratio: $.common.isNotEmpty(obj)?obj.unratio:"",
                ruter: $.common.isNotEmpty(obj)?obj.ruter:"",
                remark: $.common.isNotEmpty(obj)?obj.remark:""
            }
            
        });
    }
    if(mtype == 1){//工单制造信息行
        var count = $("#bootstrap-table2").bootstrapTable('getData').length;
        sub.editColumn();
        
        $("#bootstrap-table2").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                orderno: $.table.serialNumber(count),
                soncode: $.common.isNotEmpty(obj)?obj.soncode:"",
                mcode: $.common.isNotEmpty(obj)?obj.mcode:"",
                mname: $.common.isNotEmpty(obj)?obj.mname:"",
                mspec: $.common.isNotEmpty(obj)?obj.mspec:"",
                stime: $.common.isNotEmpty(obj)?obj.stime:"",
                etime: $.common.isNotEmpty(obj)?obj.etime:"",
                cwork: $.common.isNotEmpty(obj)?obj.cwork:"",
                cstation: $.common.isNotEmpty(obj)?obj.cstation:"",
                planqty: $.common.isNotEmpty(obj)?obj.planqty:"",
                inputqty: $.common.isNotEmpty(obj)?obj.inputqty:"",
                outputqty: $.common.isNotEmpty(obj)?obj.outputqty:"",
                stdtime: $.common.isNotEmpty(obj)?obj.stdtime:"",
                stdrate: $.common.isNotEmpty(obj)?obj.stdrate:"",
                acttime: $.common.isNotEmpty(obj)?obj.acttime:"",
                actrate: $.common.isNotEmpty(obj)?obj.actrate:"",
                remark: $.common.isNotEmpty(obj)?obj.remark:""
            }
            
        });
    }
    
}
//删除工单BOM信息行或工单制造信息行
function delColumn(mtype) {
    debugger;
    sub.editColumn();
    var subColumn = "index";
    var ids = $.table.selectColumns(subColumn);
    if (ids.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    if(mtype == 0){
        $("#bootstrap-table1").bootstrapTable('remove', { field: subColumn, values: ids });
    }
    if(mtype == 1){
        $("#bootstrap-table2").bootstrapTable('remove', { field: subColumn, values: ids });
    }
    
}

function importExcel(type,formId, width, height) {
    table.set();
    var currentId = $.common.isEmpty(formId) ? 'importMTpl' : formId;
    var _width = $.common.isEmpty(width) ? "400" : width;
    var _height = $.common.isEmpty(height) ? "230" : height;
    layer.open({
        type: 1,
        area: [_width + 'px', _height + 'px'],
        fix: false,
        //不固定
        maxmin: true,
        shade: 0.3,
        title: '导入' + (formId=="importMTpl"?"工单制造信息":"工单BOM信息") + '数据',
        content: $('#' + currentId).html(),
        btn: ['<i class="fa fa-check"></i> 导入', '<i class="fa fa-remove"></i> 取消'],
        // 弹层外区域关闭
        shadeClose: true,
        btn1: function(index, layero){
            var file = layero.find('#file').val();
            if (file == '' || (!$.common.endWith(file, '.xls') && !$.common.endWith(file, '.xlsx'))){
                $.modal.msgWarning("请选择后缀为 “xls”或“xlsx”的文件。");
                return false;
            }
            var index = layer.load(2, {shade: false});
            var formData = new FormData(layero.find('form')[0]);
            $.ajax({
                url: (formId=="importMTpl"?ctx + "pp/wbdetail/importData":ctx + "pp/wbbom/importData"),
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                type: 'POST',
                success: function (result) {
                    if (result.code == web_status.SUCCESS) {
                        $.modal.closeAll();
                        var data = result.data;
                        for(var i=0;i<data.length;i++){
                            if($.common.isNotEmpty(type)){
                                addColumn(type,data[i]);
                            }else{
                                addColumn(data[i]);
                            }
                        }
                    } else {
                        layer.close(index);
                        $.modal.enable();
                        $.modal.alertError(result.msg);
                    }
                }
            });
        }
    });
}

//导入模板
function importTemplate(type) {
    table.set();
    $.get(type=="0"?ctx + "pp/wbbom/importTemplate":ctx + "pp/wbdetail/importTemplate", function(result) {
        if (result.code == web_status.SUCCESS) {
            window.location.href = ctx + "common/download?fileName=" + encodeURI(result.msg) + "&delete=" + true;
        } else if (result.code == web_status.WARNING) {
            $.modal.alertWarning(result.msg)
        } else {
            $.modal.alertError(result.msg);
        }
    });
}