$("#bootstrap-table1").on("post-body.bs.table", function (e, args) {
    //出库时间
    $("input[name$='otime']").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
    //预计上线时间
    $("input[name$='expectOtime']").datetimepicker({
        format: "yyyy-mm-dd",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
    
});
//增加用料信息行或工序信息行
function addColumn(mtype,obj) {
    if(mtype == 0){//用料信息行
        var count = $("#bootstrap-table1").bootstrapTable('getData').length;
        sub.editColumn();
        
        $("#bootstrap-table1").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                mtype: "0",//0:用料信息
                mcode: $.common.isNotEmpty(obj)?obj.mcode:"",
                mname: $.common.isNotEmpty(obj)?obj.mname:"",
                otime: $.common.isNotEmpty(obj)?obj.otime:"",
                olot: $.common.isNotEmpty(obj)?obj.olot:"",
                qcResult: $.common.isNotEmpty(obj)?obj.qcResult:"",
                expectOtime: $.common.isNotEmpty(obj)?obj.expectOtime:"",
                createBy: $("input[name='createBy']").val(),
                createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
                updateBy: $("input[name='updateBy']").val(),
                updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
            }
        });
    }
    if(mtype == 1){//工序信息行
        var count = $("#bootstrap-table2").bootstrapTable('getData').length;
        sub.editColumn();
        
        $("#bootstrap-table2").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                mtype: "1",//0:工艺信息
                mcode: $.common.isNotEmpty(obj)?obj.mcode:"",
                mname: $.common.isNotEmpty(obj)?obj.mname:"",
                pmcode: $.common.isNotEmpty(obj)?obj.pmcode:"",
                pmname: $.common.isNotEmpty(obj)?obj.pmname:"",
                ppcode: $.common.isNotEmpty(obj)?obj.ppcode:"",
                ppname: $.common.isNotEmpty(obj)?obj.ppname:"",
                incount: $.common.isNotEmpty(obj)?obj.incount:"",
                outcount: $.common.isNotEmpty(obj)?obj.outcount:"",
                createBy: $("input[name='createBy']").val(),
                createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
                updateBy: $("input[name='updateBy']").val(),
                updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
            }
        });
    }
    
}
//删除技术信息行或工序信息行
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