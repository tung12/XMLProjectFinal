<%-- 
    Document   : index
    Created on : Jun 28, 2018, 1:42:48 PM
    Author     : ntien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body >
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="menu.jsp"></jsp:include>
         <x:parse var="xmlNews" doc="${newsXml}" scope="session" />     
        <c:import var="xslNews" url="../../stylesheet/homeUser.xsl" />
        <div id="example" >
            <x:transform xml="${xmlNews}" xslt="${xslNews}" >               
            </x:transform>
        </div>
        <c:if test="${totalPages > 1}">
            <div class="page-navigator">
                <c:forEach items="${navPages}" var = "page">
                    <c:if test="${page != -1 }">
                        <a href="product?page=${page}" class="nav-item">${page}</a>
                    </c:if>
                    <c:if test="${page == -1 }">
                        <span class="nav-item"> ... </span>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
