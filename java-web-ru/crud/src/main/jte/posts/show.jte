@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
        <div>
            <a>${page.getPost().getName()}</a>
        </div>
        <div>
            <a>${page.getPost().getBody()}</a>
        </div>
        <div>
            <a href="/posts">All posts</a>
        </div>
    `
)