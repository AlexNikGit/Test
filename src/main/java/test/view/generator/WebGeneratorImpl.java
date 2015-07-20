package test.view.generator;

/**
 *  Все конечные реализации генераторов должны быть в единственном экземпляре (возможно даже класс-одиночка)
 *  и подключаться при помощи Bean
 */
public abstract class WebGeneratorImpl implements WebGenerator {
    public abstract void generate( String url, StringBuffer out );
}
