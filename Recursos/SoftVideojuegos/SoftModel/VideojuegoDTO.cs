using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVideojuegos.Model
{
    public class VideojuegoDTO
    {
        private int? id_videojuego;
        private String nombre_videojuego;
        private DateTime? fecha_lanzamiento;
        private double? precio;
        private int? num_jugadores;
        private CategoriaDTO categoria;
        private GeneroDTO genero;


        public VideojuegoDTO() {
            this.Id_videojuego = null;
            this.Nombre_videojuego = null;
            this.Fecha_lanzamiento = null;
            this.Precio = null;
            this.Num_jugadores = null;
            this.Categoria = null;
            this.Genero = null;
        }
        public VideojuegoDTO(int? id_videojuego, string nombre_videojuego, DateTime? fecha_lanzamiento, double? precio, int? num_jugadores)
        {
            this.id_videojuego = id_videojuego;
            this.nombre_videojuego = nombre_videojuego;
            this.fecha_lanzamiento = fecha_lanzamiento;
            this.precio = precio;
            this.num_jugadores = num_jugadores;
        }

        public int? Id_videojuego { get => id_videojuego; set => id_videojuego = value; }
        public string Nombre_videojuego { get => nombre_videojuego; set => nombre_videojuego = value; }
        public DateTime? Fecha_lanzamiento { get => fecha_lanzamiento; set => fecha_lanzamiento = value; }
        public double? Precio { get => precio; set => precio = value; }
        public int? Num_jugadores { get => num_jugadores; set => num_jugadores = value; }
        public CategoriaDTO Categoria { get => categoria; set => categoria = value; }
        public GeneroDTO Genero { get => genero; set => genero = value; }
    }
}
