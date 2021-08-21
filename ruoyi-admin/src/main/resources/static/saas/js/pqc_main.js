//增加首检标准行、自检标准行、专检标准行
function addColumn(ptype,obj) {
    if(ptype == 0){//首检标准
        var count = $("#bootstrap-table1").bootstrapTable('getData').length;
        sub.editColumn();
        
        $("#bootstrap-table1").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                orderno: $.table.serialNumber(count),
                ptype: "0",
                pcode: $.common.isNotEmpty(obj)?obj.pcode:"",
                name: $.common.isNotEmpty(obj)?obj.name:"",
                standard: $.common.isNotEmpty(obj)?obj.standard:"",
                method: $.common.isNotEmpty(obj)?obj.method:"",
                createBy: $("input[name='createBy']").val(),
                createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
                updateBy: $("input[name='updateBy']").val(),
                updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
            }
        });
    }
    if(ptype == 1){//自检标准
        var count = $("#bootstrap-table2").bootstrapTable('getData').length;
        sub.editColumn();
        
        $("#bootstrap-table2").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                orderno: $.table.serialNumber(count),
                ptype: "1",
                pcode: $.common.isNotEmpty(obj)?obj.pcode:"",
                name: $.common.isNotEmpty(obj)?obj.name:"",
                standard: $.common.isNotEmpty(obj)?obj.standard:"",
                method: $.common.isNotEmpty(obj)?obj.method:"",
                createBy: $("input[name='createBy']").val(),
                createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
                updateBy: $("input[name='updateBy']").val(),
                updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
            }
        });
    }
    if(ptype == 2){//专检标准
        var count = $("#bootstrap-table3").bootstrapTable('getData').length;
        sub.editColumn();
        
        $("#bootstrap-table3").bootstrapTable('insertRow', {
            index: count,
            row: {
                index: $.table.serialNumber(count),
                orderno: $.table.serialNumber(count),
                ptype: "2",
                pcode: $.common.isNotEmpty(obj)?obj.pcode:"",
                name: $.common.isNotEmpty(obj)?obj.name:"",
                standard: $.common.isNotEmpty(obj)?obj.standard:"",
                method: $.common.isNotEmpty(obj)?obj.method:"",
                createBy: $("input[name='createBy']").val(),
                createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
                updateBy: $("input[name='updateBy']").val(),
                updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
            }
        });
    }
    
}
//删除首检标准行、自检标准行、专检标准行
function delColumn(ptype) {
    if(ptype == 0){
        debugger;
        sub.editColumn();
        var subColumn = "pcode";
        var ids = $.table.selectColumns(subColumn);
        if (ids.length == 0) {
            $.modal.alertWarning("请至少选择一条记录");
            return;
        }
        $("#bootstrap-table1").bootstrapTable('remove', { field: subColumn, values: ids });
    }
    if(ptype == 1){
        sub.editColumn();
        var subColumn = "pcode" ;
        var ids = $.table.selectColumns(subColumn);
        if (ids.length == 0) {
            $.modal.alertWarning("请至少选择一条记录");
            return;
        }
        $("#bootstrap-table2").bootstrapTable('remove', { field: subColumn, values: ids });
    }
    if(ptype == 2){
        sub.editColumn();
        var subColumn = "pcode" ;
        var ids = $.table.selectColumns(subColumn);
        if (ids.length == 0) {
            $.modal.alertWarning("请至少选择一条记录");
            return;
        }
        $("#bootstrap-table3").bootstrapTable('remove', { field: subColumn, values: ids });
    }
}