$("#bootstrap-table1").on("post-body.bs.table", function (e, args) {
    $("input[name$='uploadTime']").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
});
//增加技术标准行
function addColumn1() {
    var count = $("#bootstrap-table1").bootstrapTable('getData').length;
    sub.editColumn();
    
    $("#bootstrap-table1").bootstrapTable('insertRow', {
        index: count,
        row: {
            index: $.table.serialNumber(count),
            ptype: "0",
            fileTitle: "",
            fileName: "",
            uploadUser: $("input[name='createByName']").val(),
            uploadTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
            // isValid: "1",
            createBy: $("input[name='createBy']").val(),
            createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
            updateBy: $("input[name='updateBy']").val(),
            updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
        }
    });
}
//删除技术标准行
function delColumn1() {
    debugger;
    sub.editColumn();
    var subColumn = "fileTitle";
    var ids = $.table.selectColumns(subColumn);
    if (ids.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $("#bootstrap-table1").bootstrapTable('remove', { field: subColumn, values: ids });
}
$("#bootstrap-table2").on("post-body.bs.table", function (e, args) {
    $("input[name$='uploadTime']").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
});
//增加工艺标准行
function addColumn2() {
    var count = $("#bootstrap-table2").bootstrapTable('getData').length;
    sub.editColumn();
    
    $("#bootstrap-table2").bootstrapTable('insertRow', {
        index: count,
        row: {
            index: $.table.serialNumber(count),
            ptype: "1",
            fileTitle: "",
            fileName: "",
            uploadUser: $("input[name='createByName']").val(),
            uploadTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
            isValid: "1",
            createBy: $("input[name='createBy']").val(),
            createTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"),
            updateBy: $("input[name='updateBy']").val(),
            updateTime: $.common.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss")
        }
    });
}
//删除工艺标准行
function delColumn2() {
    sub.editColumn();
    var subColumn = "fileTitle";
    var ids = $.table.selectColumns(subColumn);
    if (ids.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    $("#bootstrap-table2").bootstrapTable('remove', { field: subColumn, values: ids });
}

//技术标准 上传文件
function uploadfile1(index){
    var options = {
        title: '上传附件',
        width: "500",
        height: "300",
        url:  prefix + '/toUpload'+"/"+index,
        callBack: doSubmit1
    };
    $.modal.openOptions(options);
}
//技术标准 上传文件后回调
function doSubmit1(index, layero) {
    var body = layer.getChildFrame('body', index);
    //当前行
    var rowIndex = body.find('#rowIndex').val();
    //上传的url
    var fileName = body.find('#fileName').val();
    //文件名称
    var fileTitle = body.find('#fileTitle').val();
    console.log(rowIndex);
    console.log(fileName);
    $("#bootstrap-table1").find("input[name='bsSopTechDetailList["+(rowIndex)+"].fileTitle']").val(fileTitle.substr(0,fileTitle.lastIndexOf(".")));//设置上传文件标题
    $("#bootstrap-table1").find("input[name='bsSopTechDetailList["+(rowIndex)+"].fileName']").val(fileName);//设置上传文件名称    
    $("#bootstrap-table1").find("#img"+rowIndex+"").attr("src",fileName); //设置展示img       
    layer.close(index);
}

//工艺标准 上传文件
function uploadfile2(index){
    var options = {
        title: '上传附件',
        width: "500",
        height: "300",
        url:  prefix + '/toUpload'+"/"+index,
        callBack: doSubmit2
    };
    $.modal.openOptions(options);
}
//工艺标准 上传文件后回调
function doSubmit2(index, layero) {
    var body = layer.getChildFrame('body', index);
    //当前行
    var rowIndex = body.find('#rowIndex').val();
    //上传的url
    var fileName = body.find('#fileName').val();
    //文件名称
    var fileTitle = body.find('#fileTitle').val();
    console.log(rowIndex);
    console.log(fileName);
    $("#bootstrap-table2").find("input[name='bsSopProdDetailList["+(rowIndex)+"].fileTitle']").val(fileTitle.substr(0,fileTitle.lastIndexOf(".")));//设置上传文件标题
    $("#bootstrap-table2").find("input[name='bsSopProdDetailList["+(rowIndex)+"].fileName']").val(fileName);//设置上传文件名称    
    $("#bootstrap-table2").find("#img"+rowIndex+"").attr("src",fileName); //设置展示img       
    layer.close(index);
}