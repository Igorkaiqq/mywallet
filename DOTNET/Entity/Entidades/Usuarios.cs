    using Entity.Enums;
    using Microsoft.AspNetCore.Identity;

    namespace Entity.Entidades
    {
        public class Usuarios : IdentityUser<Guid>
        {

            public string Nome { get; set; }
            public DateTime DataNascimento { get; set; }
            public GeneroEnum Genero { get; set; }
            public string Telefone { get; set; }
            public string PerguntaSeguranca { get; set; }
            public string RespostaSeguranca { get; set; }

        }
    }
