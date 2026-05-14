package unipar.integrador.mywallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Configuration
public class MyWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWalletApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "novaSenha123"; // Escolha sua nova senha
        String hash = encoder.encode(senha);
        System.out.println("Senha criptografada: " + hash);
    }

}
