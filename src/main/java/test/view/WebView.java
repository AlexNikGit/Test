package test.view;

/**
 * В дальнейшем название требуется заменить на XProcView
 */
public interface WebView {
    void init( );
    void getHTMLSkeleton( String url, StringBuffer out );
}
