@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    @if(page.getName() == null)
        <div><a href="${NamedRoutes.buildSessionPath()}">Войти</a></div>
    @endif
    @if(page.getName() != null)
        <div><a>${page.getName().toString()}</a></div>
        <div><a href="${NamedRoutes.logoutPath()}">Выйти</a></div>
    @endif
`
)