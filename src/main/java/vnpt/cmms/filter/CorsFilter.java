package vnpt.cmms.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//
//@Component
//@Order(1)
//public class CorsFilter extends OncePerRequestFilter {
//
//    @Autowired
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        final CacheServletRequestWrapper wrappedRequest = new CacheServletRequestWrapper(request);
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
//        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
////        if ("OPTIONS".equals(request.getMethod())) {
////            loggingService.logRequest(wrappedRequest,null);
////            response.setStatus(HttpServletResponse.SC_OK);
////        } else {
//            filterChain.doFilter(request, response);
////        }
//    }
//}
