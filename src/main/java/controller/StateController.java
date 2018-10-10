package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/main")
public class StateController extends HttpServlet {
    private static States state = States.GET;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("ajax") != null) {
            state = States.GET;
            printResp(req,resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF\\jsp\\greet.jsp");
            req.setAttribute("states", Arrays.asList("get","put","post","delete"));
            getCookieViews(req,resp);
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        state = States.PUT;
        resp.setContentType("text/html");
        printResp(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        state = States.POST;
        resp.setContentType("text/html");
        printResp(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        state = States.DELETE;
        resp.setContentType("text/html");
        printResp(req,resp);
    }

    private String getCookieClicks(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
    private void getCookieViews(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        Cookie count = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("countViews")) {
                count = cookie;
                break;
            }
        }
        if (count != null) {
            count.setValue(String.valueOf(Integer.parseInt(count.getValue()) + 1));
        } else count = new Cookie("countViews", String.valueOf(1));
        resp.addCookie(count);
        //return count.getValue();
    }

    private String getCountViews(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        Cookie count = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("countViews")) {
                count = cookie;
                break;
            }
        }
        return count.getValue();
    }

    private void printResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(" Pressed button: " + state + " ");
        out.print(" Count of clicks: " + getCookieClicks(req, resp)+ " ");
        out.print(" Count of views: " + getCountViews(req,resp));
        out.close();
    }
}