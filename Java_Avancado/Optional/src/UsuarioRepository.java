import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    
    private List<Usuario> usuarios = Arrays.asList(         
        new Usuario("Maria", "maria@gmail.com", "1234"),         
        new Usuario("João", null, "abcd"),         
        new Usuario("Ana", "ana@gmail.com", null)     
    );      
    public Optional<Usuario> buscarPorEmail(String email) {         
        return usuarios.stream()             
            .filter(u -> email != null && email.equalsIgnoreCase(u.getEmail()))             
            .findFirst();     
    } 
}
