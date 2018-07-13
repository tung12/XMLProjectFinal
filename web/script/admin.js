/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var xmlDom = null;
var current_page = 1;
var records_per_page = 50;
function prevPage()
{
    if (current_page > 1) {
        current_page--;
        changePage(current_page);
    }
}

function nextPage()
{
    if (current_page < numPages()) {
        current_page++;
        changePage(current_page);
    }
}

function changePage(page)
{
    var btn_next = document.getElementById("btn_next");
    var btn_prev = document.getElementById("btn_prev");
    var listing_table = document.getElementById("example");
    var page_span = document.getElementById("page");

    // Validate page
    if (page < 1)
        page = 1;
    if (page > numPages())
        page = numPages();

    listing_table.innerHTML = "";

    for (var i = (page - 1) * records_per_page; i < (page * records_per_page) && i < objJson.length; i++) {
        listing_table.innerHTML += objJson[i].adName + "<br>";
    }
    page_span.innerHTML = page + "/" + numPages();

    if (page == 1) {
        btn_prev.style.visibility = "hidden";
    } else {
        btn_prev.style.visibility = "visible";
    }

    if (page == numPages()) {
        btn_next.style.visibility = "hidden";
    } else {
        btn_next.style.visibility = "visible";
    }
}

function numPages()
{
    return Math.ceil(objJson.length / records_per_page);
}
function getCount() {
    console.log(xmlDom.getElementsByTagName("product").length);
}
function loadCategoryData(id, callback) {
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            callback(id, xhttp.responseXML);
        }
    };
    xhttp.open("GET", "detailCategory", true);
    xhttp.send();

}
function loadProductData(id, callback) {
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            callback(id, xhttp.responseXML);
        }
    };
    xhttp.open("GET", "detailProduct?id=" + id, true);
    xhttp.send();

}
function detailProduct(id, xmlCategory) {
    clearOption("caterogySelect");
    if (id != null || typeof id != 'undefined') {
        if (window.ActiveXObject)
        {
            xhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } else
        {
            xhttp = new XMLHttpRequest();
        }
        xhttp.open("GET", "productDetail?id=" + id, false);
        try {
            xhttp.responseType = "msxml-document"
        } catch (err) {
        } // Helping IE11
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                xmlProductDetail = xhttp.responseXML;
                //set category 
                textNode = xmlCategory.getElementsByTagName('name');
                valueNode = xmlCategory.getElementsByTagName("category");
                for (i = 0; i < valueNode.length; i++) {
                    value = valueNode[i].getAttributeNode("id").nodeValue;
                    text = textNode[i].childNodes[0].nodeValue;
                    addOption("caterogySelect", text, value);
                }
                setValueOption("caterogySelect", xmlProductDetail.getElementsByTagName('product')[0].getAttributeNode("idCategory").nodeValue);
                setValueText("productName", xmlProductDetail.getElementsByTagName('name')[0].childNodes[0].nodeValue);
                setValueText("productPrice", xmlProductDetail.getElementsByTagName('price')[0].childNodes[0].nodeValue);

            }
        };
        xhttp.send();


    } else {
        textNode = xmlCategory.getElementsByTagName('name');
        valueNode = xmlCategory.getElementsByTagName("category");
        for (i = 0; i < valueNode.length; i++) {
            value = valueNode[i].getAttributeNode("id").nodeValue;
            text = textNode[i].childNodes[0].nodeValue;
            addOption("caterogySelect", text, value);
        }
        setValueText("productName", "");
        setValueText("productPrice", "");
    }


}
function detailNews() {

}
function loadXMLDoc(filename)
{
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            return xhttp.responseXML;
        }
    };
    xhttp.open("GET", filename, false);
    try {
        xhttp.responseType = "msxml-document"
    } catch (err) {
    } // Helping IE11
    xhttp.send("");

}
function loadXMLDocPost(filename)
{
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.open("POST", filename, false);
    try {
        xhttp.responseType = "msxml-document"
    } catch (err) {
    } // Helping IE11
    xhttp.send("");
    return xhttp.responseXML;
}
function displayResult()
{
    xml = loadXMLDoc("cdcatalog.xml");
    xsl = loadXMLDoc("cdcatalog.xsl");
// code for IE
    if (window.ActiveXObject || xhttp.responseType == "msxml-document")
    {
        ex = xml.transformNode(xsl);
        document.getElementById("example").innerHTML = ex;
    }
// code for Chrome, Firefox, Opera, etc.
    else if (document.implementation && document.implementation.createDocument)
    {
        xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xsl);
        resultDocument = xsltProcessor.transformToFragment(xml, document);
        document.getElementById("example").appendChild(resultDocument);
    }
}
function loadProduct() {
    try
    {
        var param = "?offset=0"
        var asyncRequest = new XMLHttpRequest();
        asyncRequest.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {

            }
        };
        asyncRequest.open('POST', 'ProductServlet' + param, true);
        asyncRequest.send();
    } catch (exception)
    {
        alert("Request failed");
    }
}

// Get the modal


// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal


// When the user clicks the button, open the modal 
function openModalProduct(id) {
    loadCategoryData(id, detailProduct);
    document.getElementById("formProduct").style.display = "block";
}
function openModalCategory(id) {

    if (id != null || typeof id != 'undefined') {
        if (window.ActiveXObject)
        {
            xhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } else
        {
            xhttp = new XMLHttpRequest();
        }
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                xmlCategory = xhttp.responseXML;
                setValueText("categoryName", xmlCategory.getElementsByTagName('name')[0].childNodes[0].nodeValue);
                document.getElementById("formCategory").style.display = "block";
            }
        };
        xhttp.open("GET", "detailCategory?id=" + id, true);

        xhttp.send("");
    } else {
        setValueText("categoryName", "");
        document.getElementById("formCategory").style.display = "block";
    }



}
function setCategoryForNews(id, xmlCategory) {
    clearOption("caterogySelect");
    textNode = xmlCategory.getElementsByTagName('name');
    valueNode = xmlCategory.getElementsByTagName("category");
    for (i = 0; i < valueNode.length; i++) {
        value = valueNode[i].getAttributeNode("id").nodeValue;
        text = textNode[i].childNodes[0].nodeValue;
        addOption("caterogySelect", text, value);
    }
}
function setProductForNews(id, xmlCategory) {
    clearOption("caterogySelect");
    textNode = xmlCategory.getElementsByTagName('name');
    valueNode = xmlCategory.getElementsByTagName("category");
    for (i = 0; i < valueNode.length; i++) {
        value = valueNode[i].getAttributeNode("id").nodeValue;
        text = textNode[i].childNodes[0].nodeValue;
        addOption("caterogySelect", text, value);
    }
}
function openModalNews(id) {
    loadCategoryData(id, setCategoryForNews);
    document.getElementById("formNews").style.display = "block";
}

function selectFormDB() {
    var checked = document.querySelector('#selectDB').checked;

    if (checked) {
        document.getElementById("divSearch").style.display = "block";
    } else {
        document.getElementById("divSearch").style.display = "none";
    }
}
function searchFunc() {
    document.getElementById("listSearch").innerHTML = "";
    var name = document.getElementById("searchProduct").value;
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            xmlProduct = xhttp.responseXML;
            textNode = xmlProduct.getElementsByTagName('name');
            valueNode = xmlProduct.getElementsByTagName("product");
            for (i = 0; i < valueNode.length; i++) {
                value = valueNode[i].getAttributeNode("id").nodeValue;
                text = textNode[i].childNodes[0].nodeValue;
                addLi("listSearch",text,value);
            }
        }
    };
    xhttp.open("GET", "productDetail?name=" + name, true);

    xhttp.send("");
}
// When the user clicks on <span> (x), close the modal
document.getElementsByClassName("close")[0].onclick = function () {

}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == document.getElementById("formProduct")) {
        document.getElementById("formProduct").style.display = "none";
    }
    if (event.target == document.getElementById("formCategory")) {
        document.getElementById("formCategory").style.display = "none";
    }
    if (event.target == document.getElementById("formNews")) {
        document.getElementById("formNews").style.display = "none";
    }
}

function clearOption(id) {
    document.getElementById(id).options.length = 0;
}
function addOption(id, text, value) {
    var select = document.getElementById(id);
    var option = document.createElement("option");
    option.text = text;
    option.value = value;
    select.add(option);
}
function addLi(id, text,idProduct) {
    var ul = document.getElementById(id);
    var li = document.createElement("li");
    li.onclick = function(){ setProductForNews(idProduct,text); };
    ul.appendChild(li);

    t = document.createTextNode(text);

    li.innerHTML = li.innerHTML + text;
}
function setProductForNews(id,text){
    var p =  document.getElementById("selectedText");
    var idProduct = document.getElementById("idProduct");
    p.innerHTML="";
    console.log("id: "+id+" text: "+text);
    idProduct.value = id;
    console.log(idProduct.value);
   p.innerHTML=text;
}
function setValueOption(id, value) {
    var select = document.getElementById(id);
    select.value = value;
}
function setValueText(id, value) {
    var inputText = document.getElementById(id);
    inputText.value = value;
}

function submitNews(){
    var name = document.getElementById("newsName").value;
    var category = document.getElementById("categorySelect").value;
    var title = document.getElementById("newsTitle").value;
    var discription = document.getElementById("newsDiscription").value;
    var idProduct = document.getElementById("idProduct").value;
    console.log(idProduct);
    var images = document.getElementById("newsImage").value;
    var dataString = 'name=' + name + '&category=' + category + '&title=' +title +
            '&discription=' + discription+'&images=' + images+'&idProduct=' + idProduct;
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            
            }
        
    };
    xhttp.open("POST", "detailNews", true);

    xhttp.send(dataString);
}
