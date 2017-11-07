<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Listing books</title>
    </head>
    <body>
        <table border="1" cellpadding="2">
            <caption><h2>List of my books</h2></caption>
            <tr>
                <th>Id</th>
                <th>Author</th>
                <th>Title</th>
                <th>Published</th>
                <th>Remark</th>
            </tr>
            <c:forEach var="mybook" items="${mybooks}">
                <tr>
                    <td><c:out value="${mybook.id}" /></td>
                    <td><c:out value="${mybook.author}" /></td>
                    <td><c:out value="${mybook.title}" /></td>
                    <td><c:out value="${mybook.published}" /></td>
                    <td><c:out value="${mybook.remark}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
