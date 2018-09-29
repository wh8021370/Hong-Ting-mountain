<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传测试</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <style>
        #pic img{
            width: 200px;
            height: 150px;
        }
    </style>
</head>
<body>

<div>
    <div>
        <form enctype="multipart/form-data" method="post">
            <div>
                <label for="label">图片标签：</label>
                <input id="label" type="text" name="labels" placeholder="请输入标签，多个用##分隔">
            </div>

            <div>
                <label for="desc">图片描述：</label>
                <textarea id="desc" name="desc">请输入描述~</textarea>
            </div>
            <div>
                <label for="fileInput">点击上传图片</label>
                <input id="fileInput" class="" type="file" onchange="readInfo()" accept="image/*" hidden>
            </div>

        </form>
    </div>
<#--图片预览区域-->
    <div id="pic">

    </div>


    <div>
        <button id="btn">提交</button>
    </div>
</div>

<script>

    let fileList = [];

    // 读取文件信息并存储到本地变量中用于上传
    function readInfo() {
        let fileArr = document.getElementById("fileInput").files;
        if (fileArr.length > 0) {
            fileList.push(fileArr[0]);
            let reader = new FileReader();
            reader.onload = function (e) {
                var img = document.createElement('img');
                img.src = this.result;
                $('#pic').append(img);
            }
            reader.readAsDataURL(fileArr[0]);
        }
    }

    function doSubmit() {
        var data = new FormData($("#form2")[0]);
        for (var i = 0; i < fileList.length; i++) {
            data.append('file', fileList[i]);
        }
        $.ajax({
            type: 'post',
            url: '/demoForm',
            data: data,
            contentType: false,
            processData: false,
            success: function (e) {
                console.log(e);
            }
        })
    }

    $(document).ready(function () {
        // 通过formData提交带有文件的表单数据
        $('#btn').click(function () {
            var formData = new FormData($('form')[0]);
            for (var i = 0; i < fileList.length; i++) {
                formData.append('file', fileList[i]);
            }
            console.log(formData);
            $.ajax({
                type: 'post',
                url: '/demoForm',
                dataType: 'json',
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.code === '200') {
                        console.log("success!");
                    } else {
                        console.log("fail");
                    }
                }
            });
        });

        $("#info").click(function () {
            console.log(fileList)
        });
    });
</script>
</body>
</html>