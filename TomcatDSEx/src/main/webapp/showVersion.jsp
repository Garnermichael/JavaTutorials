<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>MySQL version</title>
    </head>
    <body>
        MySQL version: <c:out value="${version}"/>
        
    </body>
</html>