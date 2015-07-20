package test.view.generator;

/** CSS Style Generator
 * Перестроить класс так чтобы можно было использовать его как Bean с именем "CStyleGenerator"
 * Должен быть в едином экземпляре и подключаться с использованием Bean
 */
public class CStyleGen extends WebGeneratorImpl {
    @Override
    public void generate( String url, StringBuffer out ) { }
}
