<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : newsAdmin.xsl
    Created on : July 9, 2018, 2:38 PM
    Author     : ntien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
             
>
         
    <xsl:template match = "/">
        <table border = "1" width = "100%"  >
            <tr>                               
                <th>Name</th>
                <th>Title</th>
                <th>User</th>
                <th>View</th>
                <th>Discription</th>               
            </tr>
            <xsl:for-each select = "news/new"> 
                <xsl:variable name="myId" select="@id" />               
                <tr>                                                      
                    <td>
                        <a onClick="openModalNews({$myId})">                                                                               
                            <xsl:value-of select = "name"/>
                        </a>
                    </td>
                    <td>
                        <xsl:value-of select = "title"/>
                    </td>
                    <td>
                        <!--                        <xsl:value-of select = "price"/>-->
                    </td>
                    <td>
                        <xsl:value-of select = "view"/>
                    </td> 
                    <td>
                        <xsl:value-of select = "discription"/>
                    </td>                 
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

</xsl:stylesheet>
