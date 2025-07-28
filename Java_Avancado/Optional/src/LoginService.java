import java.util.Optional;

public class LoginService {

    private UsuarioRepository repo;

    public LoginService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public String autenticar(String email, String senha) {

        return Optional.ofNullable(email)
                .map(e -> repo.buscarPorEmail(e)
                        .map(usuario -> Optional.ofNullable(usuario.getSenha())
                                .map(s -> s.equals(senha)
                                        ? "Login bem-sucedido para " + usuario.getNome()
                                        : "Senha incorreta")
                                .orElse("Usuário sem senha cadastrada"))
                        .orElse("Usuário não encontrado"))
                .orElse("Email não pode ser nulo");
    }

}
