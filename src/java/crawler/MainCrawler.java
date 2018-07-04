/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletContext;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author ntien
 */
public class MainCrawler {

    private ServletContext context;
    public String domain;

    public MainCrawler(ServletContext context) {
        this.context = context;
    }

    public ServletContext getContext() {
        return this.context;
    }

    protected BufferedReader getBufferdReaderUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        urlConnection.addRequestProperty("User-Agent", "Morilla/5.0 (Window NT 10.0; Win64; x64)");
        InputStream is = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return br;
    }

    public String fixWellForm(String document) {
        String result = "";
        String[] x = document.split(">");
        for (int i = 0; i < x.length; i++) {
            if (x[i].contains("<img") && x[i].charAt(x[i].length() - 1)
                    != '/') {
                x[i] = x[i] + "></img>";
                result += x[i];
            } else {
                if (x[i].contains("<br")) {
                    x[i] = x[i] + "/>";
                    result += x[i];
                } else {
                    result += x[i] + ">";
                }

            }

        }
        return result;
    }

    public String convertEntityName(String document) {
        String result = document;
        if (document.contains("&")) {
            result = document.replaceAll("&", "&amp;");
        }
        if (document.contains("<br>")) {
            result = result.replaceAll("<br>", "");
        }
        return fixWellForm(result);
    }

    protected XMLEventReader parseStringtoXMLUseStAX(String xmlString) throws UnsupportedEncodingException, XMLStreamException {
        byte[] byteArray = xmlString.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        inputFactory.setProperty(XMLInputFactory.IS_VALIDATING, false);
        //parse special chacracter
        inputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);

        XMLEventReader eventReader = inputFactory.createXMLEventReader(bais);
        return eventReader;
    }

    public BigInteger parsePrice(String price) {
        String result = price.replaceAll("[^\\d.]", "");;
        return BigInteger.valueOf(Long.parseLong(result.trim()));
    }
}
