using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVideojuegos.Model
{
    public class GeneroDTO
    {
        private int? id_genero;
        private String descripcion;

        public GeneroDTO()
        {
            this.Id_genero = null;
            this.Descripcion = null;
        }

        public GeneroDTO(int? id_genero, string descripcion)
        {
            this.id_genero = id_genero;
            this.descripcion = descripcion;
        }

        public int? Id_genero { get => id_genero; set => id_genero = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
    }
}
