<%-- 
    Document   : index
    Created on : Jun 28, 2018, 1:42:48 PM
    Author     : ntien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tlds/pagingTaglib.tld" %>
<!DOCTYPE html>
<script src="script/admin.js" type="text/javascript"></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body >
        <button onclick="loadProduct()">xxx</button>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="menuAdmin.jsp"></jsp:include>   
        <div id="example" />
        <tag:paginate max="10" offset="${offset}" count="${count}" uri="/ProductServlet" next="&raquo;" previous="&laquo;"/>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
