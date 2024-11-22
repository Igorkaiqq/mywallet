package unipar.integrador.mywallet.application.services;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.metas.MetasUsuarioDTO;
import unipar.integrador.mywallet.application.entities.CategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.entities.MetasUsuarioEntity;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.enums.TipoTransacaoEnum;
import unipar.integrador.mywallet.application.exception.EntityNotFoundException;
import unipar.integrador.mywallet.application.interfaces.IMetasUsuario;
import unipar.integrador.mywallet.infrastructure.repository.MetasUsuarioRepository;
import unipar.integrador.mywallet.infrastructure.repository.TipoTransacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MetasUsuarioService implements IMetasUsuario {


    private final MetasUsuarioRepository metasUsuarioRepository;
    private final TipoTransacaoRepository tipoTransacaoRepository;

    public UUID getUsuarioAutenticadoId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    public MetasUsuarioService(MetasUsuarioRepository metasUsuarioRepository, TipoTransacaoRepository tipoTransacaoRepository) {
        this.metasUsuarioRepository = metasUsuarioRepository;
        this.tipoTransacaoRepository = tipoTransacaoRepository;
    }

    @Override
    public MetasUsuarioEntity save(MetasUsuarioDTO dto) {

        MetasUsuarioEntity metasUsuario = new MetasUsuarioEntity();

        metasUsuario.setId(UUID.randomUUID());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(getUsuarioAutenticadoId());

        CategoriaUsuarioEntity categoria = new CategoriaUsuarioEntity();
        categoria.setId(dto.categoriaId());

        metasUsuario.setCategoriaId(categoria);
        metasUsuario.setUsuarioEntity(usuario);
        metasUsuario.setValor(dto.valor());
        metasUsuario.setStatusRegistro(StatusRegistroEnum.ATIVO);

        return metasUsuarioRepository.save(metasUsuario);
    }

    @Override
    public Optional<MetasUsuarioEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<MetasUsuarioEntity> findAll() {
        return List.of();
    }

    @Override
    public MetasUsuarioEntity update(UUID id, MetasUsuarioDTO dto) {
        MetasUsuarioEntity existing = metasUsuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meta com ID " + id + " não encontrada."));

        existing.setValor(dto.valor());

        return metasUsuarioRepository.save(existing);
    }

    @Override
    public MetasUsuarioEntity findByCategoriaId(UUID categoriaId) {

        TipoTransacaoEntity despesa = tipoTransacaoRepository.findByTipoTransacaoEnum(TipoTransacaoEnum.DESPESA);

        return metasUsuarioRepository.findByCategoriaId_IdAndCategoriaId_TipoTransacaoEntity_Id(categoriaId, despesa.getId());

    }

}
