package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.time.LocalDate;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Transactional
public class UsuarioEntity extends Base {

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Column(name = "Username", length = 70, nullable = false, unique = true)
    private String username;

    @Column(name = "Email", length = 70, nullable = false, unique = true)
    private String email;

    @Column(name = "Senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "Telefone", length = 15, nullable = false, unique = true)
    private String telefone;

    @Column(name = "Cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "Genero", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @Column(name = "DataNascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @Column(name = "DataCadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "PerguntaSecreta", length = 70, nullable = false)
    private String perguntaSecreta;

    @Column(name = "RespostaSecreta", length = 70, nullable = false)
    private String respostaSecreta;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
