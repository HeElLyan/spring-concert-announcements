//package ru.itis.filters;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//import ru.itis.models.scope.ApplicationData;
//import ru.itis.models.scope.RequestData;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component("customFilter")
//public class CustomFilter extends GenericFilterBean {
//
//    @Autowired
//    private RequestData requestData;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////      The User-Agent request header is a characteristic string that lets servers and network peers identify the application, operating system, vendor
//        String type = ((HttpServletRequest) request).getHeader("User-Agent");
//        requestData.setUserAgent(type);
//        chain.doFilter(request, response);
//    }
//}
