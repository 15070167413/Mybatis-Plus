package study.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {


//        HttpSession session = req.getSession();
//        String uri=req.getRequestURI();
//        if(uri.endsWith("ssmlogin")||uri.endsWith("login")||uri.endsWith("logindo")||uri.endsWith("validcode")){
//
//
//        }else{
//            Object object=session.getAttribute("user");
//            if(object==null){
//                res.sendRedirect("/ssmlogin");
//            }
//        }
//        System.out.println("处理拦截之前");
//        httpServletRequest.setAttribute("startTime", new Date().getTime());
//        System.out.println(((HandlerMethod) o).getBean().getClass().getName());
//        System.out.println(((HandlerMethod) o).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView modelAndView) throws Exception {







        //        System.out.println("开始处理拦截");
//        Long start = (Long) httpServletRequest.getAttribute("startTime");
//        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("处理拦截之后");
//        Long start = (Long) httpServletRequest.getAttribute("startTime");
//        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
//        System.out.println("异常信息 " + e);
    }
}
