<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="main" class="main">
    <c:if test="${requestScope.validationError eq true}">
        XML файл не валиден
    </c:if>
    <c:forEach var="apartment" items="${requestScope.apartments}">
        <c:out value="${apartment.room_number}"/>
        <br>
    </c:forEach>
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
