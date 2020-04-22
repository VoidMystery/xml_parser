<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <script src="../../js/jquery-3.3.1.slim.min.js"></script>
    <script src="../../js/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/vp.js"></script>
</head>
<body>
<div id="main" class="main">

    <c:if test="${requestScope.validationError eq true}">
        <div style="width: 100%; text-align: -webkit-center">XML файл не валиден</div>
    </c:if>
    <c:if test="${requestScope.validationError ne true}">
        <table class = "table" style="margin:auto;">
            <thead class="thead-dark">
            <tr>
                <td>ID</td>
                <td>Этаж</td>
                <td>Комната</td>
                <td>Балкон</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="apartment" items="${requestScope.apartments}">
                <tr>
                    <td><c:out value="${apartment.id}"/></td>
                    <td><c:out value="${apartment.floor}"/></td>
                    <td><c:out value="${apartment.room_number}"/></td>
                    <td>
                        <c:if test="${apartment.balcony eq true}">
                            Болкон есть
                        </c:if>
                        <c:if test="${apartment.balcony ne true}">
                            Балкона нет
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <br>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="main">
        <button type="submit">На главную</button>
    </form>
</div>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('main')
</script>
</html>
