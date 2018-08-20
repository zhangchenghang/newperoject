<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 代码编辑器</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/codemirror/codemirror.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/codemirror/ambiance.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>数据库信息</h5>
                </div>
                <div class="ibox-content">
                    <form method="get" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">数据库表</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" name="account" id="tablesselect">
                                    <c:forEach items="${tableslist}" var="item">
                                        <option value="${item.tablename}">${item.tablecomment}(${item.tablename})</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">代码类型</label>
                            <div class="col-sm-10">
                                <label class="radio-inline i-checks">
                                    <input type="radio" value="1" name="tempname"
                                           checked="checked">Model模型</label>
                                <label class="radio-inline i-checks">
                                    <input type="radio" value="2" name="tempname">SQL语句</label>
                                <label class="radio-inline i-checks">
                                    <input type="radio" value="3" name="tempname">接口文件</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    数据列
                                </div>
                                <div class="panel-body" id="columns">
                                    <c:forEach items="${columns}" var="item">
                                        <div class="checkbox i-checks">
                                            <label><input type="checkbox" name='columnchk' checked=""
                                                          value="${item.ordinalposition}-${item.columnname}-${item.datatype}-${item.columncomment}-${item.columnkey}">
                                                <i></i> ${item.columncomment}(${item.columnname},${item.datatype})</label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-5">
                                <button class="btn btn-primary" type="button" id="subbtn">生成代码</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>主题示例</h5>
                </div>
                <div class="ibox-content">
                        <textarea id="code2">
                            这里显示生成的代码 </textarea>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

<!-- Peity -->
<script src="${pageContext.request.contextPath}/js/plugins/peity/jquery.peity.min.js"></script>

<!-- CodeMirror -->
<script src="${pageContext.request.contextPath}/js/plugins/codemirror/codemirror.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/codemirror/mode/javascript/javascript.js"></script>
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>
<script>
    $(document).ready(function () {
        var editor_two = CodeMirror.fromTextArea(document.getElementById("code2"), {
            lineNumbers: true,
            matchBrackets: true,
            styleActiveLine: true,
            theme:"default"
        });

        $("#tablesselect").change(function () {
            var tablename = $("#tablesselect").val();
            var str = '';
            $.ajax({
                url: '${pageContext.request.contextPath}/getcolumn',
                dataType: 'JSON',
                type: 'POST',
                data: {'tablename': tablename},
                success: function (data) {
                    $.each(data, function (index, value) {
                        var ss = "<div class='checkbox i-checks'>\n" + " <label><input name='columnchk' type='checkbox'  checked=''  value='" + value.ordinalposition +"-"+value.columnname+"-"+value.datatype+"-"+value.columncomment+"-"+value.columnkey+"'>\n" +
                            "   <i></i> " + value.columncomment + "(" + value.columnname + "," + value.datatype + ")</label>\n" + "  </div>\n";
                        str += ss;
                    });
                    $("#columns").html('');
                    $("#columns").html(str);
                    $('.i-checks').iCheck({
                        checkboxClass: 'icheckbox_square-green',
                        radioClass: 'iradio_square-green',
                    });
                }
            });
        });
        $("#subbtn").click(function () {
            var columns = new Array();
            var i = 0;
            $("input[name='columnchk']:checkbox").each(function () {
                if (true == $(this).is(':checked')) {
                    //alert($(this).val());
                    columns[i] = $(this).val();
                    i++;
                }
            });
            var tablename = $("#tablesselect").val();
            var optype = $("input[name='tempname']:checked").val();
            $.ajax({
                url: '${pageContext.request.contextPath}/bulidcode',
                dataType: 'text',
                type: 'POST',
                data: {'tablename': tablename, 'optype': optype, 'columns': columns},
                success: function (data) {
                    //alert(data);
                    editor_two.setValue(data);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    // 状态码
                    alert(XMLHttpRequest.status);
                    // 状态
                    alert(XMLHttpRequest.readyState);
                    // 错误信息
                    alert(textStatus);
                }
            });
        });
    });
</script>
</body>
</html>
