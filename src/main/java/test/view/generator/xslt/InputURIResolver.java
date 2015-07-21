package test.view.generator.xslt;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import java.io.File;


/**
 * Данный класс является обработчиком URI (унифицированный идентификатор ресурса) входящих XSL-документов
 * (по спецификации JAXP). В нём обрабатываются URI предоставленные функциями fn:doc() и fn:document() (XPath-функции
 * используемые в XSLT/XQuery) и возвращается содержимое файла соответствующего запрашиваемому идентификатолру.
 * В процессе XSLT преобразования этот класс используется для обработки URI указанных в объявлениях xsl:include,
 * xsl:import и xsl:import-schema.
 *
 * По аналогии требуется реализовать обработчик URI для исходящих XSL-документов описаный интерфейсом OutputURIResolver
 * предоставляемым Saxon для вызова при использовании xsl:result-document (атрибут href) в процессе XSLT-преобразования
 *
 * Изучить предоставляемые Saxon интерфейсы которые наследуют URIResolver (CollationURIResolver, ModuleURIResolver,
 * NonDelegatingURIResolver, RelativeURIResolver, SchemaURIResolver, UnparsedTextURIResolver)
 *
 * В дальнейшей работе заменить этот класс на CollectionURIResolver предоставляемый Saxon-ом!
 */
public class InputURIResolver implements URIResolver {

	@Override
	public Source resolve( String href, String base ) throws TransformerException {
		
		System.out.println( "Получение зависимостей...");
		
		/* Вместо загрузки шаблона XLST Stylesheet из файла, осуществляется загрузка из любой области данный (памяти, СУБД)
		 * используя преобразование в один из типов данных поддерживаемых StreamSource например, String в Reader используя
		 * new StringReader(...).
		 * Поскольку для шаблона на данном этапе нет необходимости производить какие-либо преобразования, то для максимально
		 * быстрой его загрузки необходимо использовать SAX I/O (SAX parser) */
		
		String contextPath = "/mnt/data/Develop/AppData/RES";
		StringBuffer pathTemplate = new StringBuffer( contextPath );
		System.out.println( "Файл зависимостей:   " + pathTemplate.toString( ) );
		pathTemplate.append( File.separator );
		System.out.println( "Файл зависимостей:   " + pathTemplate.toString( ) );
		pathTemplate.append( href );
		System.out.println( "Файл зависимостей:   " + pathTemplate.toString( ) );
		File xsltTemplateFile = new File( pathTemplate.toString( ) );
		if( xsltTemplateFile.exists( ) ) return new StreamSource( xsltTemplateFile );
		
		return null;
	}

}
