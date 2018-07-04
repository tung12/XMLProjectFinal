<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/header.css">
<script>

// Get the modal
    var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

</script>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>My </h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
        Hello <b>${loginedUser.userName}</b>
        <br/>
        Search <input name="search">
        <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Login</button>
    </div>
    <!-- Button to open the modal login form -->
    
    
    
        <!-- The Modal -->
    <div id="id01" class="modal">
        <span onclick="document.getElementById('id01').style.display = 'none'"
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
                <button type="button" class="cancelbtn">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form> 
    </div> 
</div>