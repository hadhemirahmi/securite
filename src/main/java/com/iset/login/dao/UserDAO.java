package com.iset.login.dao;

import com.iset.login.model.User;
import com.iset.login.util.DBConnection;
import com.iset.login.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /** Ajoute un utilisateur dans la base (mot de passe hache). */
    public boolean insert(User u) throws SQLException {
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, u.getLogin());
            ps.setString(2, PasswordUtil.hash(u.getPassword()));
            return ps.executeUpdate() > 0;
        }
    }

    public boolean exists(String login) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE login = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /** Retourne l'utilisateur si login/password corrects, sinon null. */
    public User authenticate(String login, String password) throws SQLException {
        String sql = "SELECT id, login, password FROM users WHERE login = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    String hashedInput = PasswordUtil.hash(password);

                    // Verifie le hash SHA-256 ou le mot de passe en clair (si ajoute manuellement en base)
                    if (hashedInput.equalsIgnoreCase(storedPassword) || password.equals(storedPassword)) {
                        // Met a jour automatiquement vers SHA-256 s'il etait stocke en clair
                        if (password.equals(storedPassword) && !hashedInput.equalsIgnoreCase(storedPassword)) {
                            try (PreparedStatement updatePs = cn.prepareStatement("UPDATE users SET password = ? WHERE id = ?")) {
                                updatePs.setString(1, hashedInput);
                                updatePs.setInt(2, rs.getInt("id"));
                                updatePs.executeUpdate();
                            }
                        }
                        User u = new User();
                        u.setId(rs.getInt("id"));
                        u.setLogin(rs.getString("login"));
                        return u;
                    }
                }
            }
        }
        return null;
    }
}
