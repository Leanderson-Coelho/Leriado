<script>
        $('.dropdown-trigger').dropdown();
        </script>
<!-- MENU DESKTOP-->
<ul id="dropdown1" class="dropdown-content">
  <li><a href="#!">one</a></li>
  <li><a href="#!">two</a></li>
  <li class="divider"></li>
  <li><a href="#!">three</a></li>
</ul>
<header>
    <nav>
        <div class="container">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo "><img class="logo" src="../img/logo.png" /></a>
                <ul class="right hide-on-med-and-down">
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown1">Dropdown<i class="material-icons right">arrow_drop_down</i></a></li>
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