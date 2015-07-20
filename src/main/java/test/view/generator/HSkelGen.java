package test.view.generator;

/** HTML Skeleton (structure) Generator
 * Перестроить класс так чтобы можно было использовать его как Bean с именем "HSkelGenerator"
 * Должен быть в едином экземпляре и подключаться с использованием Bean
 */
public class HSkelGen extends WebGeneratorImpl {

    @Override
    public void generate( String url, StringBuffer out ) {
        System.out.println( "Начало генерации HTML-страницы ... " );
        System.out.println( "Запрос - " + url );
        out.append( "FuckOUT" );
        System.out.println( "HTML-страница сгенерирована" );
    }
}
