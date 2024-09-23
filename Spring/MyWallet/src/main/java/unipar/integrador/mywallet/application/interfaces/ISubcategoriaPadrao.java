package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.entities.SubcategoriaPadraoEntity;

import java.util.List;
import java.util.UUID;

public interface ISubcategoriaPadrao {

    List<SubcategoriaPadraoEntity> findAllSubcategoriasAtivas();
    List<SubcategoriaPadraoEntity> findAllSubcategoriasAtivasByCategoriaId(UUID categoriaId);

}
