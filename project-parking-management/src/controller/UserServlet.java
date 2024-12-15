package controller;

import com.google.gson.Gson;
import models.User;
import services.UserService;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/users/*") // Le chemin inclut des sous-URL (ex: /users/1)
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Récupérer tous les utilisateurs
            List<User> users = userService.getAllUsers();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(gson.toJson(users));
        } else {
            // Récupérer un utilisateur spécifique
            try {
                int id = extractIdFromPath(pathInfo);
                User user = userService.getUserById(id);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(user));
            } catch (IllegalArgumentException e) {
                handleException(resp, e.getMessage(), HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = gson.fromJson(req.getReader(), User.class);
            userService.registerUser(user);
            handleSuccess(resp, "Utilisateur ajouté avec succès.", HttpServletResponse.SC_CREATED);
        } catch (IllegalArgumentException e) {
            handleException(resp, e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathInfo = req.getPathInfo();
            int id = extractIdFromPath(pathInfo);

            User user = gson.fromJson(req.getReader(), User.class);
            user.setId(id);
            userService.updateUser(user);
            handleSuccess(resp, "Utilisateur mis à jour avec succès.", HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            handleException(resp, e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathInfo = req.getPathInfo();
            int id = extractIdFromPath(pathInfo);

            userService.deleteUser(id);
            handleSuccess(resp, "Utilisateur supprimé avec succès.", HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            handleException(resp, e.getMessage(), HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private int extractIdFromPath(String pathInfo) {
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2 || pathParts[1].isEmpty()) {
            throw new IllegalArgumentException("ID invalide dans l'URL.");
        }
        try {
            return Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID invalide : " + pathParts[1]);
        }
    }

    private void handleSuccess(HttpServletResponse resp, String message, int statusCode) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(statusCode);
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        resp.getWriter().write(gson.toJson(response));
    }

    private void handleException(HttpServletResponse resp, String errorMessage, int statusCode) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(statusCode);
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", errorMessage);
        resp.getWriter().write(gson.toJson(response));
    }
}
