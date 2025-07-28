public class App {
    public static void main(String[] args) throws Exception {

        LoginService login = new LoginService(new UsuarioRepository());
        System.out.println(login.autenticar(null, "123")); // Email não pode ser nulo
        System.out.println(login.autenticar("desconhecido@email.com", "123")); // Usuário não encontrado
        System.out.println(login.autenticar("ana@gmail.com", "qualquer")); // Usuário sem senha cadastrada
        System.out.println(login.autenticar("maria@gmail.com", "errado")); // Senha incorreta
        System.out.println(login.autenticar("maria@gmail.com", "1234")); // Login bem-sucedido para Maria

    }
}
