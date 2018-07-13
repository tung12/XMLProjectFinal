<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : homeUser.xsl
    Created on : July 13, 2018, 2:52 PM
    Author     : ntien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:for-each select = "news/new"> 
            <xsl:variable name="myId" select="@id" />
            <a href="detail?id={$myId}"> 
                <article >
                    <h1>
                        <xsl:value-of select = "title"/>
                    </h1>
                    <img style="width:50px;">
                        <xsl:attribute name = "src">image?name=4e548258b3f75da904e6.jpg</xsl:attribute>
                    </img>
               
                    <p>
                        <xsl:value-of select = "discription"/>
                    </p>
                </article>
            </a>
        </xsl:for-each>
       
    </xsl:template>

</xsl:stylesheet>
