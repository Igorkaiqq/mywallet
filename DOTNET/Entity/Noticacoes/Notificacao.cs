using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entity.Noticacoes
{
    public class Notificacao
    {

        public Notificacao()
        {
            notificacoes = new List<Notificacao>();
        }

        [NotMapped]
        public string NomePropriedade { get; set; }

        [NotMapped]
        public string Mensagem { get; set; }

        [NotMapped]
        public List<Notificacao> notificacoes;

        public bool ValidaPropriedadeString (string nome, string valor)
        {
            if (string.IsNullOrWhiteSpace(valor) || string.IsNullOrWhiteSpace(nome))
            {
                notificacoes.Add(new Notificacao { Mensagem = "Campo obrigatório", NomePropriedade = nome });
                return false;
            }
            return true;
        }

        public bool ValidaPropriedadeInt (string nome, int valor)
        {
            if (valor < 1 || string.IsNullOrWhiteSpace(nome))
            {
                notificacoes.Add(new Notificacao { Mensagem = "Campo obrigatório", NomePropriedade = nome });
                return false;
            }
            return true;
        }

    }
}
