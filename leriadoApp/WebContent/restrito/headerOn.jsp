<!-- MENU DESKTOP-->
<header>
    <nav>
        <div class="container">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo "><img class="logo" src="../img/logo.png" /></a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="#"><i class="material-icons right">face</i>${usuarioLogado.nome }</a></li>
                    <li><a href="/leriadoApp/Leriado?command=UsuarioController&acao=logout"><i class="material-icons right">exit_to_app</i>Sair</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- MENU MOBILE -->
<div class="row container hide-on-large-only">
    <form method="post" action="Leriado?command=UsuarioController&acao=login">
        <div class="input-field col s12">
            <input class="validate" type="text" id="emailLogin" name="login" required
                pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$" />
            <label for="email">Email</label>
        </div>
        <div class="input-field col s12">
            <input type="password" name="senha" id="senha" required min="8" />
            <label for="senha">Senha</label>
        </div>
        <div class="input-field col s2">
            <button type="submit" class="btn waves-effect waves-light center">Enviar</button>
        </div>
    </form>
</div>