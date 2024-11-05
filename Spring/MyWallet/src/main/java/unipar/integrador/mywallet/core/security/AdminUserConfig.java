package unipar.integrador.mywallet.core.security;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import unipar.integrador.mywallet.application.entities.Role;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.RoleRepository;
import unipar.integrador.mywallet.infrastructure.repository.UsuarioRepository;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByNome(Role.Values.ADMIN.name());

        var userAdmin = usuarioRepository.findByUsername("ADMIN");

       if (userAdmin != null) {
           System.out.println("Admin já existe");
        } else {
           var admin = new UsuarioEntity();
           admin.setUsername("ADMIN");
           admin.setEmail("admin@admin.com");
           admin.setSenha(bCryptPasswordEncoder.encode("admin"));
           admin.setRoles(Set.of(roleAdmin));
           admin.setCpf("00000000000");
           admin.setNome("Admin");
           admin.setTelefone("00000000000");
           admin.setGenero(GeneroEnum.MASCULINO);
           admin.setPerguntaSecreta("Qual o nome do seu cachorro?");
           admin.setRespostaSecreta("Rex");
           admin.setDataNascimento(java.time.LocalDate.now());
           admin.setDataCadastro(java.time.LocalDate.now());
           admin.setStatusRegistro(StatusRegistroEnum.ATIVO);
           usuarioRepository.save(admin);
       }


    }
}
