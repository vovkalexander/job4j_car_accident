<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav" >
            <li class="nav-item">
                <a class ="nav-link" href="<c:url value='//create'/>">Добавить</a>
            </li>
            <li class="nav-item">
                <a class ="nav-link" href="<c:url value='/login'/>">Login as : ${user.username}</a>
            </li>

        </ul>
    </div>
</div>
<div class="container">
    <table id = "table"  class="table table-hover">
        <thead>
        <tr>
            <th>№<th>
            <th>Имя<th>
            <th>Описание<th>
            <th>Место события<th>
            <th>Тип</th>
            <th>Статья</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="accident" items = "${lists}">
            <tr>
                <td>
                    <span> <a href = "<c:url value='//edit?id=${accident.id}'/>"><i class="fas fa-edit"></i></a> </span>
                    <span> ${accident.id} </span>

                </td>
                <td>${accident.name}</td>
                <td>${accident.text}</td>
                <td>${accident.address}</td>
                <td>${accident.type}</td>
                <td>
                    <c:forEach var="rule" items="${accident.rules}" >
                        ${rule.name}
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
