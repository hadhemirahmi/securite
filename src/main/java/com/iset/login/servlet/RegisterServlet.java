package com.iset.login.servlet;

import com.iset.login.dao.UserDAO;
import com.iset.login.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        String error = null;
        if (login == null || !login.trim().matches("[A-Za-z0-9_.-]{3,30}")) {
            error = "Login invalide (3 a 30 caracteres : lettres, chiffres, _ . -).";
        } else if (password == null || password.length() < 4) {
            error = "Le mot de passe doit contenir au moins 4 caracteres.";
        } else if (!password.equals(confirm)) {
            error = "Les mots de passe ne correspondent pas.";
        }

        try {
            if (error == null && dao.exists(login.trim())) {
                error = "Ce login existe deja.";
            }
            if (error == null) {
                dao.insert(new User(login.trim(), password));
                req.setAttribute("success", "Compte cree avec succes. Vous pouvez vous connecter.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error = "Erreur base de donnees : " + e.getMessage();
        }

        req.setAttribute("error", error);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
