package test.view.generator;

import net.sf.saxon.s9api.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import test.view.generator.xslt.InputURIResolver;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
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

        /* Проверить последовательность использования компонентов Saxon для осуществления XSLT преобразований
         * и привести в соответствие с документацией
         * http://www.saxonica.com/documentation/#!using-xsl/embedding/s9api-transformation */
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
// ... there is some more setting up the xmlReader here ...
            InputStream xsltStream = new FileInputStream( new File( xslFilePath ) );
            InputStream inputStream = new FileInputStream( new File( srcFilePath ) );

            /* задание директории в которой производится поиск включаемых (include/import) XSL-файлов.
             * ЗАМЕНЕНО на использование собственного обработчика URIResolver! */
            // InputSource xsltIS = new InputSource(xsltStream);
            // xsltIS.setSystemId("/mnt/data/Develop/AppData/RES/");
            Source xsltSource = new SAXSource( xmlReader, new InputSource(xsltStream) );
            Source inputSource = new SAXSource( xmlReader, new InputSource(inputStream) );


// initialize transformation configuration
            Processor processor = new Processor(false);
            XdmNode input = processor.newDocumentBuilder().build(inputSource);
            XsltCompiler compiler = processor.newXsltCompiler();
            compiler.setURIResolver(new InputURIResolver( ));
            //compiler.setErrorListener(this);
            XsltExecutable executable = compiler.compile(xsltSource);
            Serializer serializer = new Serializer();       // с версии 9.6 объект Serializer должен всегда создаваться, используя один из newSerializer-методов класса Processor.
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
        System.out.println( "HTML-страница сгенерирована" );


        try {
            Processor proc = new Processor(false);
            XsltCompiler comp = proc.newXsltCompiler();
            XsltExecutable templates1 = comp.compile(new StreamSource(new File("data/books.xsl")));
            XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File("data/books.xml")));


            Serializer output = new Serializer();
            output.setOutputProperty(Serializer.Property.METHOD, "html");
            output.setOutputProperty(Serializer.Property.INDENT, "yes");
            output.setOutputFile(new File("data/books.html"));
            XsltTransformer trans1 = templates1.load();
            trans1.setInitialContextNode(source);

            String stylesheet2 =
                    "<xsl:transform version='2.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>" +
                        "<xsl:template match='/'>" +
                            "<xsl:for-each select=\"//ul | //ol\">" +
                                "<ul>" +
                                "<xsl:for-each select=\"li\">" +
                                    "<li>" +
                                    "<xsl:value-of select=\"current()\"/>" +    // "current()" аналогично "." для <xsl:value-of>, использутся в <xsl:apply-templates>
                                     "</li>" +
                                "</xsl:for-each>" +
                                "</ul>" +
                            "</xsl:for-each>" +
                        "</xsl:template>" +
                    "</xsl:transform>";
            XsltExecutable templates2 = comp.compile(new StreamSource(new StringReader(stylesheet2)));
            XsltTransformer trans2 = templates2.load();
            XdmDestination resultTree = new XdmDestination();
            trans2.setDestination(resultTree);
            trans2.setDestination(output);

            trans1.setDestination(trans2);
            //trans1.setDestination(output);
            trans1.transform();


            //System.out.println(resultTree.getXdmNode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
