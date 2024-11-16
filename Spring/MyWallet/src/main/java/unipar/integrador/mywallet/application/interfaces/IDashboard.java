package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.dashboard.BancosDTO;
import unipar.integrador.mywallet.application.dto.dashboard.ReceitasDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;

import java.util.List;

public interface IDashboard {

    ReceitasDTO findResumo();

    List<BancosDTO> findBancos();

    List<TransacaoUsuarioDTO> findMaioresReceitas();

    List<TransacaoUsuarioDTO> findMaioresDespesas();

}
