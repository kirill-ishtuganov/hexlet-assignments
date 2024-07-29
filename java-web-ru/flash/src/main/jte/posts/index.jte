@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <table>
    @for(var post : page.getPosts())
        <tr>
            <td><h1>${post.getName()}</h1></td>
            <td><a>${post.getBody()}</a></td>
        </tr>
    @endfor
    </table>
`
)
