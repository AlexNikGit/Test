package test.view.generator;

/**   Generator Exchange
 * Интерфейс описывающий взаимодействие с генераторами представления
 *
 * В дальнейшем необходимо заменить на интерфейс описывающий соединение с портами конвейера (PIEConnector)!
 */
public interface GenEx {
    void init( );
    void genHTML( String input, StringBuffer result );
}
