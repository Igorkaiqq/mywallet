using Entity.Noticacoes;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore.Metadata.Internal;

namespace Entity.Entidades
{
    public class Base
    {
        [Column(Order = 1)]
        public Guid Id { get; set; }

        [Column(Order = 2)]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Codigo { get; set; }

    }
}
