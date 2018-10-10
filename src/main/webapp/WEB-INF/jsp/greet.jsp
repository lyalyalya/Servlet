<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#getState').click(function () {
            $.ajax({
                type: "get",
                url: "/simplewebapp/main?ajax=true",
                success: function (msg) {
                    $('#output').text(msg);
                }
            });
        });
        $('#putState').click(function () {
            $.ajax({
                type: "put",
                url: "main",
                success: function (msg) {
                    $('#output').text(msg);
                }
            });
        });
        $('#postState').click(function () {
            $.ajax({
                type: "post",
                url: "main",
                success: function (msg) {
                    $('#output').text(msg);
                }
            });
        });
        $('#deleteState').click(function () {
            $.ajax({
                type: "delete",
                url: "main",
                success: function (msg) {
                    $('#output').text(msg);
                }
            });
        });
    });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
<input type="button" value="Get" id="getState"/>
<input type="button" value="Put" id="putState"/>
<input type="button" value="Post" id="postState"/>
<input type="button" value="Delete" id="deleteState"/>

<div id="output"></div>
<c:set var="jstl" value="Available states:"/>
<c:out value='${jstl}'/>
<c:forEach items="${states}" var="state"><p>${state}</p> </c:forEach>
</body>
</html>