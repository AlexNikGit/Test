package test.view;

import test.view.generator.GenExImpl;

import java.util.Map;

/** View Generator Exchange
 * Класс обеспечивающий взаимодействие обработчиков представлений с генераторами представления.
 * Соединяет в себе запросы от всех сессий и распределяет их по целевому назначению в зависимости от запроса клиента.
 * Главным является хранилище всех сессий с запросами  и передача их на генерацию по напралвениям (HTML, CSS)
 *
 * В дальнейшем этот класс необходимо заменить на класс для соединения с портами конвейера (XPwVConnector)!
 */
public class ViewGenEx extends GenExImpl {
    private Map<String, Object> AllSessionsMap;    // в качестве Object будет использоваться структура сесисии

    @Override
    protected void setOutBuffer() {

    }
}