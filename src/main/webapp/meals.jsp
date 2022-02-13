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
                <th> ID </th>
                <th> Date </th>
                <th> Description </th>
                <th> Calories </th>
                <th> Edit </th>
                <th> Delete </th>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <tr style="color:${meal.excess ? 'red' : 'green'}">
                    <td><c:out value="${meal.id}"/> </td>
                    <td>
                        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                       type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                    </td>

                    <td><c:out value="${meal.description}"/></td>
                    <td><c:out value="${meal.calories}"/></td>
                    <td><a href="meals?action=edit&userId=<c:out value="${meal.id}"/>">Update</a></td>
                    <td><a href="meals?action=delete&userId=<c:out value="${meal.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
