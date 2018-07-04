/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.codemodel.JCodeModel;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import crawler.LimeorangeCrawler;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.I;
import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author ntien
 */
public class Utils {

    public void XJCGenerateJavaObj(String pathSchema, String pathOutput,String packageName) {
        try {
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "error:"+ saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "fatal", saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "warning", saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "info", saxpe.getMessage());
                }
            });
            sc.forcePackageName(packageName);
            File schema = new File(pathSchema);
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
           S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(pathOutput));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean validateXmlSchema(String schemaPath, String document){           
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);           
            Schema schema = factory.newSchema(new File(schemaPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(document.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex); 
            return false;
    }
        return true;
}
    public boolean isAlphaChar(char x){
        return (x > 'a' && x<='z');
    }
    public String getTagName(String document){
        if (document.charAt(document.length()-2)=='/') {
            return null;
        }
        String res="";
        int i=1;
        if (document.charAt(i)=='/') {
            res = res+'/';
            i++;
        }
        while(isAlphaChar(document.charAt(i))){
            res =res+document.charAt(i);
            i++;
        }
        if (res.length()==0 || (res.length() ==1 && res.charAt(0)=='/')) {
            return null;
            
        }
        return res;
    }
    public String fixWellFormedUseStack(String document){
        List<String> stack = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        List<String> addTag = new ArrayList<>();
        
        int size= document.length();
        int mark[] = new int[size];
        Arrays.fill(mark, -1);
        int i=0;
        while(i < document.length()){
            if (document.charAt(i) =='<') {
                int j = i+1;
                String tagTmp = ""+document.charAt(i);
                while(j<document.length() && document.charAt(j)!= '>'){
                    tagTmp = tagTmp + document.charAt(j);
                    j++;
                }
                
                int curEnd = j;
                tagTmp = tagTmp +'>';
                i = j+1;
                String tag =  getTagName(tagTmp);
                if (tag !=null) {
                    if (tag.charAt(0)!='/') {
                        stack.add(tag);
                        li.add(curEnd);
                    }
                    else{
                        while(stack.size()>0){
                            if (stack.get(stack.size()-1).equals(tag.substring(1))) {
                                stack.remove(stack.size()-1);
                                li.remove(li.size()-1);
                                break;
                            }
                            else{
                                addTag.add(stack.get(stack.size()-1));
                                mark[li.get(li.size()-1)] = addTag.size()-1;
                                stack.remove(stack.size()-1);
                                li.remove(li.size()-1);
                            }
                        }
                    }
                }
                else i++;
            }
        }
        while(stack.size()>0){
            addTag.add(stack.get(stack.size()-1));
            mark[li.get(li.size()-1)] = addTag.size()-1;
            stack.remove(stack.size()-1);
            li.remove(li.size()-1);
        }
        String newDocument="";
        
        for (int j = 0; j < document.length(); j++) {
            newDocument = newDocument + document.charAt(j);
            if (mark[j]!=-1) {
                newDocument = newDocument +"</"+ addTag.get(mark[j])+">";
            }
        }
        return  newDocument ;
        
    }
}
