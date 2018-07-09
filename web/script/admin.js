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
function loadCategoryData(id) {
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            loadProductData(id, xhttp.responseXML);
        }
    };
    xhttp.open("POST", "category", true);
    xhttp.send();

}
function loadProductData(id, xmlCategory) {
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
                clearOption("caterogySelect");
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
        setValueText("productName", "");
        setValueText("productPrice", "");
    }


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

    loadCategoryData(id);
    document.getElementById("formProduct").style.display = "block";
    console.log(test);


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
        xhttp.open("POST", "category?id=" + id, true);
        xhttp.send("");
    } else {
        setValueText("categoryName", "");
        document.getElementById("formCategory").style.display = "block";
    }
    


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
function setValueOption(id, value) {
    var select = document.getElementById(id);
    select.value = value;
}
function setValueText(id, value) {
    var inputText = document.getElementById(id);
    inputText.value = value;
}

