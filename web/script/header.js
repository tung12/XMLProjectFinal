/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function openModal() {
    document.getElementById('id01').style.display = 'block';
}

function closeModal() {
    document.getElementById('id01').style.display = 'none';
}
function logOut() {
    try
    {
        var asyncRequest = new XMLHttpRequest();
        asyncRequest.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
               location.reload();
            }
        };
        asyncRequest.open('POST', 'LogOutServlet', true);
        asyncRequest.send(null);
    } catch (exception)
    {
        alert("Request failed");
    }
}

