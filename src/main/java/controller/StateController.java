package controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/main")
public class StateController extends HttpServlet {
    private AtomicInteger count = new AtomicInteger(0);
    private static States state=States.GET;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        if (req.getParameter("ajax") != null) {
            state = States.GET;
            PrintWriter out = resp.getWriter();
            out.print(" Pressed button: " + state + " ");
            out.print("Count: " + getCookie(req, resp));
            out.close();
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF\\jsp\\greet.jsp");
            req.setAttribute("mes",state.toString());
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        state = States.PUT;
        PrintWriter out = resp.getWriter();
        out.print(" Pressed button: " + state + " ");
        out.print("Count: " + getCookie(req, resp));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        state = States.POST;
        PrintWriter out = resp.getWriter();
        out.print(" Pressed button: " + state + " ");
        out.print("Count: " + getCookie(req, resp));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        state = States.DELETE;
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("Pressed button: " + state + " ");
        out.print(" Count: " + getCookie(req, resp));
    }

    /*private int getCookies(HttpServletRequest req){
        HttpSession session=req.getSession();
        Integer cookie=(Integer) session.getAttribute("Count");
        int countViews;
        if (cookie==null){
            countViews=0;
            session.setAttribute("Count",countViews);
        }else {
            countViews=cookie;
            // int i=(Integer) req.getSession().getAttribute("Count");
            session.setAttribute("Count",++countViews);
        }
        return cookie;
    }*/
    private String getCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        Cookie count = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("count")) {
                count = cookie;
                break;
            }
        }
        if (count != null) {
            count.setValue(String.valueOf(Integer.parseInt(count.getValue()) + 1));
        } else count = new Cookie("count", String.valueOf(1));
        resp.addCookie(count);
        return count.getValue();
    }
}
