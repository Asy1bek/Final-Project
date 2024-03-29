package kz.bitlab.academy.users.servlet;

import kz.bitlab.academy.users.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        String fullName = req.getParameter("fullName");

        String redirect = UserService.register(email, password, rePassword, fullName);

        if (redirect.isBlank()) {
            resp.sendRedirect("/login");
            return;
        }

        resp.sendRedirect("/register?" + redirect);
    }
}
