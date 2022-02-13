
<%--
  Created by IntelliJ IDEA.
  User: mroka
  Date: 11.02.2022
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Meals</title>
</head>
<body>
        <h3> Edit meals </h3>
        <jsp:useBean id="editMeal" scope="request" type="ru.javawebinar.topjava.model.MealTo"/>
        <form method="post" action="meals?action=edit">
            User ID: <c:out value="${editMeal.id}"/>
            <input type="hidden" name="userId" value="${editMeal.id}">
            <input type="hidden" name="excess" value="${editMeal.excess}">
            <p>
            DateTime: <input type="datetime-local" name="dateTime" value="${editMeal.dateTime}"/>
            </p>
            <p>
            Description: <input type="text" name="description" value="${editMeal.description}"/>
            </p>
            <p>
            Calories: <input type="number" name="calories" value="${editMeal.calories}"/>
                <p> <input type="submit" value="UPDATE">
                    <input type="button" value="CANCEL">   </p>
            </p>
        </form>

</body>
</html>
