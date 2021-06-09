
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить событие</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form  action="<c:url value='//save'/>" method='POST'>
        <table>
            <tr>
                    <td>Имя:</td>
                    <td><input type="text" name="name"></td>
                </tr>

            <tr>
                <td>Описание:</td>
                <td><input type="text" name="text"></td>

            </tr>
            <tr>
                <td>Место события:</td>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
                <td>Тип:</td>
                <td>
                    <select name="type.id" >
                        <option value="" selected></option>
                        <c:forEach var="type" items="${types}" >
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Статьи:</td>
                <td>
                    <select name="rIds" multiple>
                        <c:forEach var="rule" items="${rules}" >
                            <option value="${rule.id}">${rule.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" class="btn btn-primary" value="Сохранить" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
