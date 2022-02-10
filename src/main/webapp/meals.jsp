<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">HomePage</a></h3>
    <h2>Meals</h2>
        <table border="1" align="center">
            <thead>
                <th> Date </th>
                <th> Description </th>
                <th> Calories </th>
                <th>          </th>
                <th>          </th>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <tr style="color:${meal.excess ? 'red' : 'green'}">
                    <td>
                        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                       type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                    </td>

                    <td><c:out value="${meal.description}"/></td>
                    <td><c:out value="${meal.calories}"/></td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
