<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>H+ 后台主题UI框架 - 登录</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-6">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>[ H+ ]</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>H+ 后台主题UI框架</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                </ul>
                <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-6">
            <form id="formlogin" method="post" class="form-horizontal">
                <h4 class="no-margins">登录：</h4>
                <div class="alert alert-danger alert-login text-center" id="errormsg">
                    H+是一个很棒的后台UI框架 <a class="alert-link" href="notifications.html#">了解更多</a>.
                </div>
                <div class="form-group formpanel">
                    <input type="text" class="form-control uname" placeholder="用户名" name="username" id="username"/>
                </div>
                <div class="form-group formpanel">
                    <input type="password" class="form-control pword" placeholder="密码" name="password" id="password"/>
                </div>
                <div class="form-group formpanel">
                    <div class="col-sm-8 vcodediv">
                        <input type="text" class="form-control" placeholder="验证码" name="vcode" id="vcode"/></div>
                    <div class="col-sm-4">
                        <img id="codeImg" alt="验证码" class="vcode" src="${pageContext.request.contextPath}/code"
                             onclick="changeImg()"/></div>
                </div>
                <div class="form-group formpanel">
                    <a href="" class="left">忘记密码了？</a>
                    <a href="" class="right hidden">没有账户？去注册</a>
                </div>
                <input type="hidden" value="${returnurl}" id="returnurl" name="returnurl">
                <button class="btn btn-success btn-block" type="button" id="submitbtn">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2015 All Rights Reserved. H+
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 弹出框 -->
<script src="${pageContext.request.contextPath}/js/plugins/layer/layer.js"></script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>
<script>
    var redirectUrl = "${redirect}";
    var LOGIN = {
        checkInput: function () {
            if ($("#username").val() == "") {
                $("#errormsg").show();
                $("#errormsg").html('请输入用户名');
                // layer.alert('请输入用户名', {
                //     icon: 2,
                //     skin: 'layer-ext-moon'
                // });
                $("#username").focus();
                return false;
            }
            if ($("#password").val() == "") {
                $("#errormsg").show();
                $("#errormsg").html('请输入密码');
                // layer.alert('请输入密码', {
                //     icon: 2,
                //     skin: 'layer-ext-moon'
                // });
                $("#password").focus();
                return false;
            }
            if ($("#vcode").val() == "") {
                $("#errormsg").show();
                $("#errormsg").html('请输入验证码');
                // layer.alert('请输入验证码', {
                //     icon: 2,
                //     skin: 'layer-ext-moon'
                // });
                $("#vcode").focus();
                return false;
            }
            return true;
        },
        doLogin: function () {
            $.post("${pageContext.request.contextPath}/dologin", $("#formlogin").serialize(), function (data) {
                if (data.status == 200) {
                    $("#errormsg").hide();
                    // layer.alert('登陆成功', {
                    //     icon: 1,
                    //     skin: 'layer-ext-moon'
                    // });
                    if (redirectUrl == "") {
                        location.href = "http://localhost:8081${pageContext.request.contextPath}/";
                    } else {
                        location.href = redirectUrl;
                    }
                } else {
                    $("#errormsg").show();
                    $("#errormsg").html(data.msg);
                    // layer.alert('登陆失败，原因是：'+data.msg, {
                    //     icon: 2,
                    //     skin: 'layer-ext-moon'
                    // });
                    $("#username").select();
                }
            });
        },
        login: function () {
            if (this.checkInput()) {
                this.doLogin();
            }
        }
    };
    $(function () {
        $("#errormsg").hide();
        $("#submitbtn").click(function () {
            LOGIN.login();
        });
    });

    function changeImg() {
        var imgSrc = $("#codeImg");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }

    //加入时间戳，去缓存机制
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        if ((url.indexOf("&") >= 0)) {
            url = url + "&timestamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
</script>
</body>
</html>
