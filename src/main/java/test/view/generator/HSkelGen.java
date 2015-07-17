package test.view.generator;

/** HTML Skeleton (structure) Generator
 * Должен быть в едином экземпляре и подключаться с использованием Bean
 */
public class HSkelGen extends WebGeneratorImpl {

    @Override
    public void generate( ) {
        System.out.println( "Начало генерации HTML-страницы ... " );

        System.out.println( "HTML-страница сгенерирована" );
    }
}
