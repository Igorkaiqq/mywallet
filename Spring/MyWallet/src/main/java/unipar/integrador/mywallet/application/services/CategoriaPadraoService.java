package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.categoriaPadrao.CategoriaPadraoDTO;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.interfaces.ICategoriaPadrao;
import unipar.integrador.mywallet.infrastructure.repository.CategoriaPadraoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaPadraoService implements ICategoriaPadrao {

    @Autowired
    private CategoriaPadraoRepository categoriaPadraoRepository;


    @Override
    public List<CategoriaPadraoDTO> findAllCategoriasAtivas() {

        List<CategoriaPadraoEntity> categorias = categoriaPadraoRepository.findAllByStatusRegistro(StatusRegistroEnum.ATIVO);

        return categorias.stream().map(categoria -> new CategoriaPadraoDTO(
                categoria.getId(),
                categoria.getTipoTransacao().getId(),
                categoria.getNome(),
                categoria.getStatusRegistro()
        )).collect(Collectors.toList());
    }

    @Override
    public List<CategoriaPadraoEntity> findAllCategoriasAtivasEntities() {
        return categoriaPadraoRepository.findAllByStatusRegistro(StatusRegistroEnum.ATIVO);
    }



}
