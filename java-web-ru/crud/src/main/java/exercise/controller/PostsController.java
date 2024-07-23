package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var post = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Page not found"));
            var page = new PostPage(post);
            ctx.render("posts/show.jte", model("page", page));
        } catch (NotFoundResponse e) {
            ctx.status(404);
            ctx.result(e.getMessage());
        }
    }

    public static void index(Context ctx) {
        var currentPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        currentPage = currentPage == 0 ? 1 : currentPage;
        var lastPage = (int) Math.ceil((double) PostRepository.getEntities().size() / 5);
        currentPage = currentPage > lastPage ? lastPage : currentPage;
        var currentPosts = PostRepository.findAll(currentPage, 5);
        var page = new PostsPage(currentPage, currentPosts);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END
}
