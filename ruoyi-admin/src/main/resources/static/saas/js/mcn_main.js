//上传文件
function uploadfile(index){
    var options = {
        title: '上传附件',
        width: "500",
        height: "300",
        url:  prefix + '/toUpload'+"/"+index,
        callBack: doSubmit
    };
    $.modal.openOptions(options);
}
//上传文件后回调
function doSubmit(index, layero) {
    var body = layer.getChildFrame('body', index);
    //当前行
    var rowIndex = body.find('#rowIndex').val();
    //文件名称
    var attachment = body.find('#attachment').val();
    //文件url
    var fileUrl = body.find('#fileUrl').val();
    console.log(rowIndex);
    console.log(attachment);
    $("input[name='bsMcnDetailList["+(rowIndex)+"].attachment']").val(attachment);//设置上传文件名称    
    $("input[name='bsMcnDetailList["+(rowIndex)+"].fileUrl']").val(fileUrl);//设置上传文件路径       
    $("#a"+rowIndex+"").attr("href",fileUrl); //设置文档超链接  
    $("#a"+rowIndex+"").text(attachment); //设置超链接名称
    layer.close(index);
}
$("#bootstrap-table").on("post-body.bs.table", function (e, args) {
    //变更日期
    $("input[name$='mdate']").datetimepicker({
        format: "yyyy-mm-dd",
        minView: "month",
        autoclose: true,
        pickerPosition:'top-right'
    });
    //上传日期
    $("input[name$='uploadTime']").datetimepicker({
        format: "yyyy-mm-dd",
        minView: "month",
        autoclose: true,
        initialDate: new Date(),
        pickerPosition:'top-right'
    });
});