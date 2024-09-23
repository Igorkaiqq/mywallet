package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.entities.SubcategoriaPadraoEntity;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.interfaces.ISubcategoriaPadrao;
import unipar.integrador.mywallet.infrastructure.repository.SubcategoriaPadraoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SubcategoriaPadraoService implements ISubcategoriaPadrao {

    @Autowired
    SubcategoriaPadraoRepository subcategoriaPadraoRepository;

    @Override
    public List<SubcategoriaPadraoEntity> findAllSubcategoriasAtivas() {
        return subcategoriaPadraoRepository.findAllByStatusRegistro(StatusRegistroEnum.ATIVO);
    }

    @Override
    public List<SubcategoriaPadraoEntity> findAllSubcategoriasAtivasByCategoriaId(UUID categoriaId) {
        return subcategoriaPadraoRepository.findAllByStatusRegistroAndCategoriaPadraoId(StatusRegistroEnum.ATIVO, categoriaId);
    }
}
