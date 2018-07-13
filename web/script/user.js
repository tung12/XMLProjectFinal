/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var xmlNews = null;
var xmlProduct = null;
var xmlCategory = null;
function showDetail() {
    xsl = loadXMLDoc("stylesheet/detailUser.xsl");
    console.log(xsl);
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
        xsltProcessor.setParameter(null,'xmlProduct', xmlProduct);
        xsltProcessor.setParameter(null,'xmlCategory', xmlCategory);
        
        resultDocument = xsltProcessor.transformToFragment(xmlNews, document);
        document.getElementById("example").appendChild(resultDocument);
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
    xhttp.open("GET", filename, false);
    try {
        xhttp.responseType = "msxml-document"
    } catch (err) {
    } // Helping IE11
    xhttp.send("");
    return xhttp.responseXML;
}
function loadData() {
    console.log(id);
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            xmlNews = xhttp.responseXML;
            valueNode = xmlNews.getElementsByTagName("new");
            loadProduct(valueNode[0].getAttributeNode("idProduct").nodeValue);
        }
    };
    xhttp.open("GET", "detailNews?id=" + id, true);
    xhttp.send();
}

function loadProduct(productId) {

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
            valueNode = xmlProduct.getElementsByTagName("product");
            loadCategory(valueNode[0].getAttributeNode("idCategory").nodeValue);
        }
    };
    xhttp.open("GET", "productDetail?id=" + productId, true);
    xhttp.send();
}

function loadCategory(categoryId) {

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
            showDetail();
        }
    };
    xhttp.open("GET", "detailCategory?id=" + categoryId, true);
    xhttp.send();
}




