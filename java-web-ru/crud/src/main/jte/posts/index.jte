@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link"
                                     href="${NamedRoutes.postsPath()}?page=${page.getPage() - 1}">Previous</a></li>
            <li class="page-item"><a class="page-link"
                                     href="${NamedRoutes.postsPath()}?page=${page.getPage() + 1}">Next</a></li>
        </ul>
    </nav>

    <tr>
    @for(var post : page.getPosts())
        <td>
            <div>
                <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
            </div>
        </td>
    @endfor
    </tr>
    <div>${page.getPage()}</div>
    `
)
