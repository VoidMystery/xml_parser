<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Index Page</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <script src="../../js/jquery-3.3.1.slim.min.js"></script>
    <script src="../../js/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/viewport.js"></script>
</head>
<body>
<div id="main" class="main">
    <div id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form class="form-login login-form" action="controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="print"/>
                        <div class="form-group">
                            <div class="form-row">
                                <input class="form-control" id="file" type="file" name="file"/>
                            </div>
                            <br>
                            <div class="form-row">
                                <label for="sax">SAX:&nbsp;</label>
                                <input type="radio" class="form-control" id="sax" value = "sax" checked="checked" name="parser_type">
                                <label for="sax">StAX:&nbsp;</label>
                                <input type="radio" class="form-control" id="stax" value = "stax" name="parser_type">
                            </div>
                        </div>
                        <button class="btn btn-light" type="submit">Отправить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('main')
</script>
</html>
