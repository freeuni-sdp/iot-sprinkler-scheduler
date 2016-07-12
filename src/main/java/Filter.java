import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rezo on 6/27/16.
 */
@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        final HttpServletResponse httpResponse = (HttpServletResponse) resp;

            httpResponse.addHeader("Access-Control-Allow-Origin","*" );
            httpResponse.addHeader("Access-Control-Allow-Methods","POST, GET, PUT, DELETE, OPTIONS" );
            httpResponse.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept" );

        chain.doFilter(req, resp);
    }



}
