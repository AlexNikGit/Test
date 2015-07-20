package test;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import test.view.BaseWebView;
import test.view.WebView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
	Document document;
	Element main;

	private static Map<String,Source> modelMap = new HashMap<String,Source>( );


	/* ModelAndView служит для совмещения модели и представления в Web-контроллере.
	 * Представление может определяется в конфигурационном классе (файле) ViewResolver-ом по имени при необходимости построения типового представления
	 * либо объект представления может быть определен непосредственно в ModelAndView если необходима тонкая настрой представления.
	 * Модель представляем собой Map, позволяющую многократное добавление объектов модели определяя их строковым идентификатором. Если указано
	 * использование определённой модели (setSourceKey) то будет использоваться только она, иначе буду использоваться все добавленные.  */
	/*@RequestMapping(value="*//*")
	public ModelAndView mvDynGenTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println( "Начало динамического создания страницы ... " );
		System.out.println( "Запрашиваемый путь:   " +  request.getRequestURI( ) );

		String srcFile_1 = "wpContent/ctxHeader.xml";
		String srcFilePath_1 = "/mnt/data/Develop/AppData/RES" + File.separator + srcFile_1;
		//Source model_1 = new StreamSource(new File(srcFilePath_1));
		Source model_1 = new StreamSource(new InputStreamReader(new FileInputStream( new File(srcFilePath_1) ),"UTF-8") );
		modelMap.put("ctxHeader", model_1);


		ModelAndView mvContext = new ModelAndView( "DynGenTest", modelMap );	// в параметре передаётся название статического аттрибута в значении которого передаётся шаблон

		System.out.println( "Старница динамические сформирована. " );

		return mvContext;
	}*/


	// Процедура возврата изображения контроллером
	@RequestMapping(value="/img/menu_border.gif")
	@ResponseBody
	public HttpEntity<byte[]> sayHello( HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println( "Запрашиваемая картинка:   " +  request.getRequestURI( ) );
		FileSystemResource resource = new FileSystemResource("/mnt/data/Develop/AppData/DATA/img/menu_border.gif");
		BufferedReader bufferedReader = null;
		HttpHeaders headers = new HttpHeaders();
		byte[] image = new byte[(int) resource.getFile().length()];
		try {
			resource.getInputStream().read(image);
			headers.setContentType(MediaType.IMAGE_GIF);
			headers.setContentLength(image.length);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HttpEntity<byte[]>(image, headers);
	}

	@RequestMapping(value="/*", produces = "text/html; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> genHTML( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		WebView wvProc;
		StringBuffer buf = new StringBuffer( /*"Выходное значение:   "*/ );

		wvProc = new BaseWebView( );
		wvProc.init();
		wvProc.getHTMLSkeleton(request.getRequestURI(), buf);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType("text", "html", Charset.forName("UTF-8"))));

		System.out.println( "Отправлено клиенту: " + buf.toString( ) );

		return new HttpEntity<String>(buf.toString( ), headers);
	}
}