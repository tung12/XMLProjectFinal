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
        <title>Product</title>
    </head>
    <script> test = <%=request.getAttribute("totalPages")%>;</script>
    <body >       
        <jsp:include page="../jsp/header.jsp"></jsp:include>
        <jsp:include page="menuAdmin.jsp"></jsp:include>
        <x:parse var="xmlNews" doc="${newsXml}" scope="session" />6;       
        <c:import var="xslNews" url="../../stylesheet/newsAdmin.xsl" />
        <button id="myBtn" onclick="openModalNews()">modal</button>
        <div id="example" >
            <x:transform xml="${xmlNews}" xslt="${xslNews}" >
                
            </x:transform>
        </div>
        <div id="formNews" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Modal Header</h2>
                </div>
                <div class="modal-body">
                    <input type="checkbox" id="selectDB"  onchange="selectFormDB()"/>Select item from DB<br>
                    
                    <div id="divSearch" style="display: none;">
                        Selected:<p id="selectedText"></p>
                        Search product:<input type="text" id="searchProduct" onkeyup="searchFunc()"/>
                        <ul id="listSearch"></ul>
                    </div>
                    <form id="newForm" method="POST" action="detailNews" enctype="multipart/form-data">
                        <input type="text" style="display: none" id="idProduct" name="idProduct"/>
                    <p>Name</p><input id="newsName" type="text" name="name"/><br>                   
                    <p>Category</p><select id="caterogySelect" name="category"> </select><br>
                    <p>Title</p><input id="newsTitle"  type="text" name="title"/><br>                                    
                    <p>Discription</p><textarea id="newsDiscription" name="discription"></textarea><br>
                    <p>Image</p><input type="file" multiple accept="image/*" name="images" id="newsImage"/>
                    <input type="submit" value="Add New"/>
                    </form>
                    
                </div>               
            </div>
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
        <jsp:include page="../jsp/footer.jsp"></jsp:include>
    </body>
</html>
