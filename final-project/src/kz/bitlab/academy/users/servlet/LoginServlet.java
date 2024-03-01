package kz.bitlab.academy.users.servlet;

import kz.bitlab.academy.users.entity.UserEntity;
import kz.bitlab.academy.users.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserEntity currentUser = UserService.authenticate(email, password);

        if (currentUser != null) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", currentUser);
            resp.sendRedirect("/profile");
            return;
        }

        resp.sendRedirect("/login?incorrectCredentials");
    }
}
