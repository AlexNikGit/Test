package test.view;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * HTTP Web View
 * Класс представления обрабатывающий запросы полученные по протоколу HTTP
 * <p/>
 * В дальнейшем название необходимо заменить на XProcHView
 */
public class HWebView extends AbstractUrlBasedView {
    private BaseWebView wvProc;        // Web View Processor

    public HWebView() {
        wvProc = new BaseWebView( );
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    }

}
