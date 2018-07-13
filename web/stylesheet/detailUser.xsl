<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : detailUser.xsl
    Created on : July 13, 2018, 6:47 PM
    Author     : ntien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:param name="xmlProduct" />
    <xsl:param name="xmlCategory" />
    <xsl:output method="html"/>
    
    
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <div class="news">
            <div class="image-news">
                <div class="small-image">
                    <span class="item-image">
                        <img alt="News" src="image?name=4e548258b3f75da904e6.jpg" />
                    </span>
                    <span class="item-image">
                        <img alt="News" src="image?name=4e548258b3f75da904e6.jpg" />
                    </span>
                    <span class="item-image">
                        <img alt="News" src="image?name=4e548258b3f75da904e6.jpg" />
                    </span>
                    <span class="item-image">
                        <img alt="News" src="image?name=4e548258b3f75da904e6.jpg" />
                    </span>
                    <span class="item-image more">
                        <img alt="News" src="image?name=4e548258b3f75da904e6.jpg" />
                        <span class="more-text">Xem thêm</span>
                    </span>
                </div>
                <div class="big-image">
                    <img alt="News" src="image?name=4e548258b3f75da904e6.jpg" />
                </div>
            </div >
            <div class="content-news">
                <h1 class="news-title"><xsl:value-of select = "news/new/title"/></h1>
                <div class="news-rate">
                    <span class="rating-box">
                        <svg class="icon-star-empty"></svg>
                        <span class="icon-star-empty"></span>
                    <i class="icon-star-empty"></i>
                    <i class="star"></i>
                    <i class="star"></i>
                    </span>
                </div>
            </div>    
        </div>
        <p><xsl:value-of select = "$xmlProduct//product/name"/> </p>
    </xsl:template>

</xsl:stylesheet>
