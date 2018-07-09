<%-- 
    Document   : product
    Created on : Jul 5, 2018, 1:44:57 PM
    Author     : ntien
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tlds/pagingTaglib.tld" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
    </head>
    <script> test = <%=request.getAttribute("totalPages")%>;</script>
    <body >       
        <jsp:include page="../jsp/header.jsp"></jsp:include>
        <jsp:include page="menuAdmin.jsp"></jsp:include>        
        <x:parse var="xmlCategory" doc="${categoryXml}"  />
        <c:import var="xslCategory" url="../../stylesheet/categoryAdmin.xsl" />
        <button id="myBtn" onclick="openModalCategory()">modal</button>      
        <div id="example" >
            <x:transform xml="${xmlCategory}" xslt="${xslCategory}" >               
            </x:transform>
        </div>
        <div id="formCategory" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Modal Header</h2>
                </div>
                <div class="modal-body">
                    <p>Name</p><input id="categoryName" type="text" /><br>                   
                </div>               
            </div>
        </div>
        <c:if test="${totalPages > 1}">
            <div class="page-navigator">
                <c:forEach items="${navPages}" var = "page">
                    <c:if test="${page != -1 }">
                        <a href="category?page=${page}" class="nav-item">${page}</a>
                    </c:if>
                    <c:if test="${page == -1 }">
                        <span class="nav-item"> ... </span>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
        <jsp:include page="../jsp/footer.jsp"></jsp:include>
    </body>
</html>
