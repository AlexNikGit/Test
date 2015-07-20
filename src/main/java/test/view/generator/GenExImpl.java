package test.view.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**   View Generator Exchange
 * Абстрактная реализация интерфейса взаимодействия обработчиков с генераторами представления.
 * Выступает промежуточным звеном для обмена информацией с генераторами при разных методах комуникации с клиентом,
 * обеспечивая консолидированный (единый) доступ к генераторам представления при обработке запросов по различным
 * протоколам HTTP (класс HWebView), WebSocket (класс WSWebView), SockJS (класс SWebView)
 *
 * Обеспечивает обработку запросов, собираемых в своей конечной реализации (потомок) и распределение их между
 * генераторами в зависимости от направления (HTML, CSS). Инициализирует объекты генераторов по мере и необходимости
 * их использования. Основными хранилищами являются карта генераторов и карта буфферов обмена данными к генераторам (должна
 * состоять из входного запроса и выходного буфера а также всех необходимых параметров ).
  *
 * В дальнейшем этот класс необходимо заменить на абстракную реализацию "соединителя" для взаимодействия с портами
 * импорта/экспорта конвейера (PIEViewConn)!
 */
public abstract class GenExImpl implements GenEx {
    private Map<String, WebGenerator> webGenerators;    // карта генераторов
    /*private Map<String, Object> AllBeffers;*/ // в качестве Object будет использоваться структура для буферов. на каждую сессию свой набор буфферов

    @Autowired
    private ApplicationContext applicationContext;

    protected GenExImpl( ) {
        this.webGenerators = new HashMap<String, WebGenerator>( );
    }

    public void init( ) {
        //WebGenerator web_generator = applicationContext.getBean("HSkelGenerator", HSkelGen.class);
        WebGenerator web_generator = new HSkelGen( );
        this.webGenerators.put("HTML", web_generator);
    }

    public void genHTML( String input, StringBuffer result ) {
        this.webGenerators.get("HTML").generate( input, result);
    }

   /*
    private void genCSS( String input, String result ) {}
    public void genContext( ) {}
    protected abstract void setOutBuffer(  );*/
}
