<%-- 
    Document   : detail
    Created on : Jul 13, 2018, 4:48:55 PM
    Author     : ntien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
    </head>
    <script>
        var id = ${requestScope.id};
    </script>
    <body onload="loadData();">
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="menu.jsp"></jsp:include>
        <div id="example"></div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
