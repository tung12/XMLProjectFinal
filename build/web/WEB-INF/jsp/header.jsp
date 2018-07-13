<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/header.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
<script src="script/header.js" type="text/javascript"></script>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>My Website</h1>
    </div>   
    <div id="userInfo" style="float: right; padding: 10px; text-align: right;" >
        <c:choose>
            <c:when test="${not empty loginedUser}">
                Hello <b>${loginedUser.userName}</b>
                <br/>           
                <button onclick="logOut()" style="width:auto;">Logout</button>     
            </c:when>  
            <c:otherwise>
                <button onclick="openModal()" style="width:auto;">Login</button>
            </c:otherwise>
        </c:choose>

    </div>

    <!-- Button to open the modal login form -->
    Search <input name="search">


    <!-- The Modal -->
    <div id="id01" class="modal">
        <span onclick="closeModal()"
              class="close" title="Close Modal">&times;</span>

        <!-- Modal Content -->
        <form action="LoginServlet" method="POST">
            <div class="imgcontainer">             
                <img src="img/defaultAvatar.png" alt="Avatar" class="avatar"/>
            </div>

            <div class="container">
                <label for="uname"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="userName" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <input type="hidden" name="from" value="${requestScope['javax.servlet.forward.servlet_path']}">

                <button type="submit">Login</button>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <button type="button" class="cancelbtn" onclick="closeModal()">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form> 
    </div> 
</div>