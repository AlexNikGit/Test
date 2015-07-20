package test.view.generator;

import net.sf.saxon.s9api.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.*;

/** HTML Skeleton (structure) Generator
 * Перестроить класс так чтобы можно было использовать его как Bean с именем "HSkelGenerator"
 * Должен быть в едином экземпляре и подключаться с использованием Bean
 */
public class HSkelGen extends WebGeneratorImpl {

    @Override
    public void generate( String url, StringBuffer out ) {
        System.out.println( "Начало генерации HTML-страницы ... " );
        System.out.println("Запрос - " + url);


        String xslFilePath = "/mnt/data/Develop/AppData/RES/stHome.xslt";
        String srcFilePath = "/mnt/data/Develop/AppData/RES/wpContent/ctxHeader.xml";
        OutputStream htmlOut = new ByteArrayOutputStream( );
        try
        {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
// ... there is some more setting up the xmlReader here ...
            InputStream xsltStream = new FileInputStream( new File( xslFilePath ) );
            InputStream inputStream = new FileInputStream( new File( srcFilePath ) );
            InputSource xsltIS = new InputSource(xsltStream);
            xsltIS.setSystemId(xslFilePath);
            InputSource inputIS = new InputSource(inputStream);
            inputIS.setSystemId(srcFilePath);
            Source xsltSource = new SAXSource(xmlReader, xsltIS );
            Source inputSource = new SAXSource(xmlReader, inputIS);


// initialize transformation configuration
            Processor processor = new Processor(false);
            XdmNode input = processor.newDocumentBuilder().build(inputSource);
            XsltCompiler compiler = processor.newXsltCompiler();
            //compiler.setErrorListener(this);
            XsltExecutable executable = compiler.compile(xsltSource);
            Serializer serializer = new Serializer();
            /*serializer.setOutputProperty(Serializer.Property.METHOD, "html");
            serializer.setOutputProperty(Serializer.Property.ENCODING, "UTF-8");
            serial.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
            serializer.setOutputProperty(Serializer.Property.INDENT, "no");*/
            serializer.setOutputStream(htmlOut);

// execute transformation
            XsltTransformer transformer = executable.load();
            transformer.setInitialContextNode(input);
            //transformer.setErrorListener(this);
            transformer.setDestination(serializer);
            transformer.setSchemaValidationMode(ValidationMode.STRIP);
            transformer.transform();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        out.append( htmlOut.toString( ) );
        //out.append( "FuckOUT" );
        System.out.println( "HTML-страница сгенерирована" );
    }
}
