package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();
        var token = Security.generateToken();
        var user = new User(firstName, lastName, email, password, token);
        var id = (long) UserRepository.getEntities().size() + 1;
        UserRepository.save(user);
        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(id));
    }

    public static void show(Context ctx) {

        var showedUserId = ctx.pathParamAsClass("id", Long.class).get();
        var showedUser = UserRepository.find(showedUserId).get();

        if (ctx.cookie("token").equals(showedUser.getToken())) {

            var page = new UserPage(showedUser);
            ctx.render("users/show.jte", model("page", page));

        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
