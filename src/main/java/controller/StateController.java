package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class StateController extends HttpServlet {
    private AtomicInteger count = new AtomicInteger(0);
    private Cookie amountOfPressed;//=new Cookie("amount", String.valueOf(count));
    private States state;
    private LinkedList<States> innerStates = new LinkedList<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        if (ajax) {
            state = States.GET;
            if (!innerStates.isEmpty()) {
                resp.getWriter().print("Last state: " + innerStates.getLast());
            } else resp.getWriter().print("List of states is empty ");
            print(resp);
        } else {
            amountOfPressed = new Cookie("amount", String.valueOf(count));
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF\\jsp\\greet.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        state = States.PUT;
        if (innerStates.isEmpty()||!state.equals(innerStates.getLast())) innerStates.add(state);
        print(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        state = States.POST;
        innerStates.add(state);
        print(resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        state = States.DELETE;
        if(!innerStates.isEmpty())innerStates.remove(innerStates.getLast());
        print(resp);
    }

    private void print(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        amountOfPressed.setValue(Integer.toString(Integer.parseInt(amountOfPressed.getValue()) + 1));
        resp.addCookie(amountOfPressed);

        out.print(" Pressed button: " + state);
        out.print(" Count of pressed: " + amountOfPressed.getValue());
        out.print(" List of states: ");
        for (States states : innerStates) {
            out.print(states + " ");
        }
    }

}
