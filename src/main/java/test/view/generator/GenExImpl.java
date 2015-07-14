package test.view.generator;

/**   View Generator Exchange
 * Абстрактная реализация интерфейса взаимодействия обработчиков с генераторами представления.
 * Выступает промежуточным звеном для обмена информацией с генераторами при разных методах комуникации с клиентом,
 * обеспечивая консолидированный (единый) доступ к генераторам представления при обработке запросов по различным
 * протоколам HTTP (класс HWebView), WebSocket (класс WSWebView), SockJS (класс SWebView)
 *
 * В дальнейшем этот класс необходимо заменить на абстракную реализацию "соединителя" для взаимодействия с портами
 * импорта/экспорта конвейера (PIEViewConn)!
 */
public abstract class GenExImpl implements GenEx {
    private WebCreator webCreator;      // нужно выбирать интерфейс к которому будет выводится конкретная реализация

    protected GenExImpl( ) { }
    protected GenExImpl( WebCreator web_creator ) {
        this.webCreator = web_creator;
    }

    public void setWebCreator( WebCreator web_creator ) {
        this.webCreator = web_creator;
    }
}
