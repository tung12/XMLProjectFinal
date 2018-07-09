<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : adminstylesheet.xsl
    Created on : July 5, 2018, 1:30 PM
    Author     : ntien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">         
    <xsl:template match = "/">
        <table border = "1" width = "100%">
            <tr>                               
                <th>Name</th>
                <th></th>                          
            </tr>
            <xsl:for-each select = "categorys/category"> 
                <xsl:variable name="myId" select="@id" />               
                <tr>                                                      
                    <td>
                        <a onClick="openModalCategory({$myId})">                                                                               
                            <xsl:value-of select = "name"/>
                        </a>
                    </td>
                    <td>
                        <button>Delete</button>
                    </td>                                     
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

</xsl:stylesheet>
