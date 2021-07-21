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
    //上传的url
    var attachment = body.find('#attachment').val();
    //文件名称
    var fileName = body.find('#fileName').val();
    var mcontent = fileName.substr(0,fileName.lastIndexOf("."));
    console.log(rowIndex);
    console.log(attachment);
    $("input[name='bsMcnDetailList["+(rowIndex)+"].mcontent']").val(mcontent);//设置变更单号
    $("input[name='bsMcnDetailList["+(rowIndex)+"].fileName']").val(fileName);//设置上传文件名称
    $("input[name='bsMcnDetailList["+(rowIndex)+"].attachment']").val(attachment);//设置图片保存路径       
    $("#img"+rowIndex+"").attr("src",attachment); //设置展示img       
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