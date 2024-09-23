package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.categoriaPadrao.CategoriaPadraoDTO;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.dto.subcategoriaUsuario.SubcategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDto;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.interfaces.IUsuario;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaUsuarioRepository;
import unipar.integrador.mywallet.infrastructure.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements IUsuario {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaPadraoService categoriaPadraoService;

    @Autowired
    private CategoriaUsuarioService categoriaUsuarioService;

    @Autowired
    private SubcategoriaPadraoService subcategoriaPadraoService;

    @Autowired
    private SubcategoriaUsuarioService subcategoriaUsuarioService;

    @Override
    public UsuarioEntity save(CadastroUsuarioDto dto) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setUsername(dto.username());
        usuarioEntity.setEmail(dto.email());
        usuarioEntity.setSenha(dto.senha());
        usuarioEntity.setTelefone(dto.telefone());
        usuarioEntity.setCpf(dto.cpf());
        usuarioEntity.setGenero(GeneroEnum.valueOf(dto.genero()));
        usuarioEntity.setDataNascimento(LocalDate.parse(dto.dataNascimento()));
        usuarioEntity.setDataCadastro(LocalDateTime.now());
        usuarioEntity.setPerguntaSecreta(dto.perguntaSecreta());
        usuarioEntity.setRespostaSecreta(dto.respostaSecreta());
        usuarioEntity.setStatusRegistro(StatusRegistroEnum.ATIVO);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        List<CategoriaPadraoEntity> categoriasPadrao = categoriaPadraoService.findAllCategoriasAtivasEntities();

        for (CategoriaPadraoEntity categoriaPadrao : categoriasPadrao) {
            CategoriaUsuarioEntity categoriaUsuario = new CategoriaUsuarioEntity();
            categoriaUsuario.setUsuarioEntity(usuarioSalvo);
            categoriaUsuario.setTipoTransacaoEntity(categoriaPadrao.getTipoTransacao());
            categoriaUsuario.setCategoriaPadraoEntity(categoriaPadrao);
            categoriaUsuario.setNome(categoriaPadrao.getNome());
            categoriaUsuario.setStatusRegistro(StatusRegistroEnum.ATIVO);

            CategoriaUsuarioDTO categoriaUsuarioDto = categoriaUsuarioService.convertToDto(categoriaUsuario);

            CategoriaUsuarioEntity categoriaSalva = categoriaUsuarioService.save(categoriaUsuarioDto);

            List<SubcategoriaPadraoEntity> subcategoriasPadrao = subcategoriaPadraoService.findAllSubcategoriasAtivasByCategoriaId(categoriaPadrao.getId());

            for (SubcategoriaPadraoEntity subcategoriaPadrao : subcategoriasPadrao) {
                SubcategoriaUsuarioEntity subcategoriaUsuario = new SubcategoriaUsuarioEntity();

                subcategoriaUsuario.setUsuarioEntity(usuarioSalvo);
                subcategoriaUsuario.setCategoriaUsuario(categoriaSalva);

                SubcategoriaPadraoEntity subcategoriaPadraoEntity = new SubcategoriaPadraoEntity();
                subcategoriaPadraoEntity.setId(subcategoriaPadrao.getId());
                subcategoriaUsuario.setSubcategoriaPadrao(subcategoriaPadraoEntity);

                subcategoriaUsuario.setNome(subcategoriaPadrao.getNome());
                subcategoriaUsuario.setStatusRegistro(StatusRegistroEnum.ATIVO);

                SubcategoriaUsuarioDTO subcategoriaUsuarioDto = subcategoriaUsuarioService.convertToDto(subcategoriaUsuario);

                // Salvar a subcategoria do usuário
                subcategoriaUsuarioService.save(subcategoriaUsuarioDto);
            }
        }
        return usuarioSalvo;
    }

    @Override
    public Optional<UsuarioEntity> findById (UUID id){
        return usuarioRepository.findById(id);
    }

    @Override
    public List<UsuarioEntity> findAll () {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity update (UsuarioEntity usuarioEntity){
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public void deleteById (UUID id){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow();
        usuarioEntity.setStatusRegistro(StatusRegistroEnum.DELETADO);
        usuarioRepository.save(usuarioEntity);
    }

}
