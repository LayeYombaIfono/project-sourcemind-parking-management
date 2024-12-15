package controller;
0
import com.google.gson.Gson;
import com.sun.research.ws.wadl.Response;
import models.User;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import services.UserService;

import java.util.HashMap;
import java.util.List;

import static com.sun.research.ws.wadl.Response.*;

@Path("/users")
public class UserResource {
    private final UserService userService = new UserService();
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok(gson.toJson(users)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        try {
            User user = userService.getUserById(id);
            return Response.ok(gson.toJson(user)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson(errorResponse(e.getMessage())))
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String jsonBody) {
        try {
            User user = gson.fromJson(jsonBody, User.class);
            userService.registerUser(user);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson(successResponse("Utilisateur ajouté avec succès.")))
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(errorResponse(e.getMessage())))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, String jsonBody) {
        try {
            User user = gson.fromJson(jsonBody, User.class);
            user.setId(id);
            userService.updateUser(user);
            return Response.ok(gson.toJson(successResponse("Utilisateur mis à jour avec succès."))).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(errorResponse(e.getMessage())))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        try {
            userService.deleteUser(id);
            return Response.ok(gson.toJson(successResponse("Utilisateur supprimé avec succès."))).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson(errorResponse(e.getMessage())))
                    .build();
        }
    }

    private HashMap<String, Object> successResponse(String message) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return response;
    }

    private HashMap<String, Object> errorResponse(String errorMessage) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", errorMessage);
        return response;
    }

}
