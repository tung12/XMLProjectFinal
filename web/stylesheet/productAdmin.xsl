<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : adminstylesheet.xsl
    Created on : July 5, 2018, 1:30 PM
    Author     : ntien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:param name="categoryPath" />
    <xsl:variable name="categorys" select="document($categoryPath)" />
   
    <xsl:template match = "/">
        <table border = "1" width = "100%">
            <tr>                               
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>               
            </tr>
            <xsl:for-each select = "products/product"> 
                <xsl:variable name="myId" select="@id" />               
                <tr>                                                      
                    <td>
                        <a onClick="openModalProduct({$myId})">                                                                               
                            <xsl:value-of select = "name"/>
                        </a>
                    </td>
                    <td>
                        <xsl:variable name="cateID" select="@idCategory"/>
<!--                        <xsl:value-of select = "$categorys/categorys/category/name"/>-->
<!--                        <xsl:value-of select = "$categoryss//category/name"/>-->
<!--                   <xsl:value-of select = "$categorys//category[@id=$cateID]/name"/>-->
                    </td>
                    <td>
                        <xsl:value-of select = "price"/>
                    </td>                  
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

</xsl:stylesheet>
