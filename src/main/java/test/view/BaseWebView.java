package test.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * В дальнейшем название требуется заменить на XProcBaseView
 * Данный класс обрабатывает всех подписчиков по разным протоколам, обеспечивая единый инструмент взаимодействия с
 * генераторами. Подключает взаимодействие с генераторами. Для взаимодействии с генераторами передаёт идентификатор
 * клиента, поскольку соединитель с генераторами один, а представления на каждое соединение разные.
 * Основным хранимым набором является карта соединений по каждой сессии для разных протоколов. Собирает связь со всех
 * протоколов в одну сессию.
 */
public class BaseWebView implements WebView {
    private ViewGenEx viewGenEx;
    private Map<String, Object> AllProtocolsSession;    // в качестве Object будет использоваться структура каждого объекта сессии

    @Autowired
    private ApplicationContext applicationContext;

    public BaseWebView( ) {
        viewGenEx = applicationContext.getBean("ViewGenEx", ViewGenEx.class);
    }

}
