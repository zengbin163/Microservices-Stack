//上传文件操作
function UploadFile(fileBoxName) {
    var value = $("#" + fileBoxName).filebox('getValue');
    var files = $("#" + fileBoxName).next().find('input[type=file]')[0].files;
    
    if (value && files[0]) {
        //构建一个FormData存储复杂对象
        var formData = new FormData();
        formData.append("folder", '数据导入文件');
        formData.append('file', files[0]);//默认的文件数据名为“Filedata”

        $.ajax({
            url: host + '/file/upload', //单文件上传
            type: 'POST',
            processData: false,
            contentType: false,
            data: formData,
            success: function(fileAccess) {
            	changeFileBoxValue(fileAccess);
            },
            error: function (xhr, status, error) {
                $.messager.alert("提示", "操作失败");
            }
        });
    }
}