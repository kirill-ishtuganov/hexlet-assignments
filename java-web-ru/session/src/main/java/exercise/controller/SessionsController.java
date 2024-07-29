package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("name"));
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("sessions/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {

            var name = ctx.formParam("name");
            if (!UsersRepository.existsByName(name)) {
                throw new RuntimeException("Wrong username or password");
            }
            var user = UsersRepository.findByName(name);
            var password = user.getPassword();
            var enteredPassword = encrypt(ctx.formParam("password"));
            if (!password.equals(enteredPassword)) {
                throw new RuntimeException("Wrong username or password");
            }
            ctx.sessionAttribute("name", name);
            ctx.redirect("/");

        } catch (RuntimeException e) {

            var name = ctx.formParam("name");
            var page = new LoginPage(name, e.getMessage());
            ctx.render("sessions/build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("name", null);
        ctx.redirect("/");
    }
    // END
}
