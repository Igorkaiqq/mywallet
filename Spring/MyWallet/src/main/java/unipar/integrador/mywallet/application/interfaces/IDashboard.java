package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.dashboard.BancosDTO;
import unipar.integrador.mywallet.application.dto.dashboard.ReceitasDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IDashboard {

    ReceitasDTO findResumo(LocalDateTime inicio, LocalDateTime fim);

    List<BancosDTO> findBancos();

    List<TransacaoUsuarioDTO> findMaioresReceitas(LocalDateTime inicio, LocalDateTime fim);

    List<TransacaoUsuarioDTO> findMaioresDespesas(LocalDateTime inicio, LocalDateTime fim);

}
