package test.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * В дальнейшем название требуется заменить на XProcBaseView
 */
public class BaseWebView implements WebView {
    private ViewGenEx viewGenEx;

    @Autowired
    private ApplicationContext applicationContext;

    public BaseWebView( ) {
        viewGenEx = applicationContext.getBean("ViewGenEx", ViewGenEx.class);
    }

}
