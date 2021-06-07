<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать событие</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav" >
            <li class="nav-item">
                <a class ="nav-link" href="<c:url value='/'/>">На главную страницу</a>
            </li>
        </ul>
    </div>
</div>
<div class="container">
<form action="<c:url value='//save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="name" value="${accident.name}"></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type="text" name="text" value="${accident.text}"></td>

        </tr>
        <tr>
            <td>Место события:</td>
            <td><input type="text" name="address" value="${accident.address}"></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <option selected hidden>${accident.type.name}</option>
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" class="btn btn-primary" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
