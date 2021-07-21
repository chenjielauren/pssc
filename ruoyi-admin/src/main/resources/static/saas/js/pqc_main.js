//增加首检行
function addColumn1() {
    // alert($("input[name='createBy']").val());
    var count = $("#bootstrap-table1").bootstrapTable('getData').length;
    sub.editColumn();
    
    $("#bootstrap-table1").bootstrapTable('insertRow', {
        index: count,
        row: {
            index: $.table.serialNumber(count),
            ptype: "0",
            pcode: "",
            name: "",
            standard: "",
            method: "",
            remark: "",
            isValid: "1",
            createBy: $("input[name='createBy']").val(),
            createTime: "",
            updateBy: $("input[name='updateBy']").val(),
            updateTime: ""
        }
    });
}
//删除首检行
function delColumn1() {
    var subColumn = "index";
    var ids = $.table.selectColumns(subColumn);
    if (ids.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $("#bootstrap-table1").bootstrapTable('remove', { field: subColumn, values: ids });
}

//增加自检行
function addColumn2() {
    var count = $("#bootstrap-table2").bootstrapTable('getData').length;
    sub.editColumn();
    
    $("#bootstrap-table2").bootstrapTable('insertRow', {
        index: count,
        row: {
            index: $.table.serialNumber(count),
            ptype: "1",
            pcode: "",
            name: "",
            standard: "",
            method: "",
            remark: "",
            isValid: "",
            createBy: $("input[name='createBy']").val(),
            createTime: "",
            updateBy: $("input[name='updateBy']").val(),
            updateTime: ""
        }
    });
}
//删除自检行
function delColumn2() {
    sub.editColumn();
    var subColumn = "index" ;
    var ids = $.table.selectColumns(subColumn);
    if (ids.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $("#bootstrap-table2").bootstrapTable('remove', { field: subColumn, values: ids });
}
//增加巡检行
function addColumn3() {
    var count = $("#bootstrap-table3").bootstrapTable('getData').length;
    sub.editColumn();
    
    $("#bootstrap-table3").bootstrapTable('insertRow', {
        index: count,
        row: {
            index: $.table.serialNumber(count),
            ptype: "2",
            pcode: "",
            name: "",
            standard: "",
            method: "",
            remark: "",
            isValid: "",
            createBy: $("input[name='createBy']").val(),
            createTime: "",
            updateBy: $("input[name='updateBy']").val(),
            updateTime: ""
        }
    });
}
//删除巡检行
function delColumn3() {
    sub.editColumn();
    var subColumn = "index" ;
    var ids = $.table.selectColumns(subColumn);
    if (ids.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $("#bootstrap-table3").bootstrapTable('remove', { field: subColumn, values: ids });
}