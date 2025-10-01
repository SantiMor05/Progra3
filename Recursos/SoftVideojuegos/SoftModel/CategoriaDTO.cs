using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVideojuegos.Model
{
    public class CategoriaDTO
    {
        private char? id;
        private string descripcion;

        public CategoriaDTO() {
            this.Id = null;
            this.Descripcion = null;
        }
        public CategoriaDTO(char? id, string descripcion)
        {
            this.id = id;
            this.descripcion = descripcion;
        }

        public char? Id { get => id; set => id = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
    }
}
