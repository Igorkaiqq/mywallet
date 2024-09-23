package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.categoriaPadrao.CategoriaPadraoDTO;
import unipar.integrador.mywallet.application.entities.CategoriaPadraoEntity;

import java.util.List;

public interface ICategoriaPadrao {


    List<CategoriaPadraoDTO> findAllCategoriasAtivas();
    List<CategoriaPadraoEntity> findAllCategoriasAtivasEntities();

}
