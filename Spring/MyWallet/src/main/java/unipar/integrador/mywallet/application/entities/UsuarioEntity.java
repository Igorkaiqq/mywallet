package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.*;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioEntity extends Base {

    @Column(name = "Nome", length = 70, nullable = false)
    private String nome;

    @Column(name = "Username", length = 70, nullable = false)
    private String username;

    @Column(name = "Email", length = 70, nullable = false)
    private String email;

    @Column(name = "Senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "Telefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "Cpf", length = 14, nullable = false)
    private String cpf;

    @Column(name = "Genero", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @Column(name = "DataNascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @Column(name = "DataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "PerguntaSecreta", length = 70, nullable = false)
    private String perguntaSecreta;

    @Column(name = "RespostaSecreta", length = 70, nullable = false)
    private String respostaSecreta;

    @Enumerated(EnumType.STRING)
    @Column(name = "StatusRegistro", nullable = false)
    private StatusRegistroEnum statusRegistro;

}
