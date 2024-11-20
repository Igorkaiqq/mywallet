export interface Transacao {
  tipoTransacaoId: string | null;
  valor: string;
  categoriaId: string | null;
  subcategoriaId: string | null;
  metodoPagamentoId: string | null;
  contaBancariaId: string | null;
  descricao: string;
  data: string;
}
