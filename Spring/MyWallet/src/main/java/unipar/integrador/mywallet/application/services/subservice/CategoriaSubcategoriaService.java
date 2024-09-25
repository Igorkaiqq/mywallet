package unipar.integrador.mywallet.application.services.subservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.services.CategoriaUsuarioService;
import unipar.integrador.mywallet.application.services.SubcategoriaPadraoService;
import unipar.integrador.mywallet.application.services.SubcategoriaUsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaSubcategoriaService {

    @Autowired
    CategoriaUsuarioService categoriaUsuarioService;

    @Autowired
    SubcategoriaUsuarioService subcategoriaUsuarioService;

    @Autowired
    SubcategoriaPadraoService subcategoriaPadraoService;

    public void associarCategoriasComUsuario(UsuarioEntity usuarioSalvo, List<CategoriaPadraoEntity> categoriasPadrao) {
        for (CategoriaPadraoEntity categoriaPadrao : categoriasPadrao) {
            Optional<CategoriaUsuarioEntity> categoriaExistente = categoriaUsuarioService
                    .findByUsuarioIdAndCategoriaPadraoId(usuarioSalvo.getId(), categoriaPadrao.getId());

            CategoriaUsuarioEntity categoriaUsuario = categoriaExistente.orElseGet(() -> {
                CategoriaUsuarioEntity novaCategoria = new CategoriaUsuarioEntity();
                novaCategoria.setUsuarioEntity(usuarioSalvo);
                novaCategoria.setTipoTransacaoEntity(categoriaPadrao.getTipoTransacao());
                novaCategoria.setCategoriaPadraoEntity(categoriaPadrao);
                novaCategoria.setNome(categoriaPadrao.getNome());
                novaCategoria.setStatusRegistro(StatusRegistroEnum.ATIVO);

                return categoriaUsuarioService.save(categoriaUsuarioService.convertToDto(novaCategoria));
            });

            associarSubcategoriasComUsuario(usuarioSalvo, categoriaUsuario, categoriaPadrao);
        }
    }

    private void associarSubcategoriasComUsuario(UsuarioEntity usuarioSalvo, CategoriaUsuarioEntity categoriaUsuario, CategoriaPadraoEntity categoriaPadrao) {
        List<SubcategoriaPadraoEntity> subcategoriasPadrao = subcategoriaPadraoService.findAllSubcategoriasAtivasByCategoriaId(categoriaPadrao.getId());

        for (SubcategoriaPadraoEntity subcategoriaPadrao : subcategoriasPadrao) {
            Optional<SubcategoriaUsuarioEntity> subcategoriaExistente = subcategoriaUsuarioService
                    .findByUsuarioIdAndCategoriaUsuarioIdAndSubcategoriaPadraoId(
                            usuarioSalvo.getId(), categoriaUsuario.getId(), subcategoriaPadrao.getId());

            subcategoriaExistente.orElseGet(() -> {
                SubcategoriaUsuarioEntity novaSubcategoria = new SubcategoriaUsuarioEntity();
                novaSubcategoria.setUsuarioEntity(usuarioSalvo);
                novaSubcategoria.setCategoriaUsuario(categoriaUsuario);
                novaSubcategoria.setSubcategoriaPadrao(subcategoriaPadrao);
                novaSubcategoria.setNome(subcategoriaPadrao.getNome());
                novaSubcategoria.setStatusRegistro(StatusRegistroEnum.ATIVO);

                return subcategoriaUsuarioService.save(subcategoriaUsuarioService.convertToDto(novaSubcategoria));
            });
        }
    }

}
