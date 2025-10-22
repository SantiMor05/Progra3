using System;

namespace TransitSoft.Model
{
    public class Propietario
    {
        private int? id;
        private string dni;
        private string nombres;
        private string apellidos;
        private string direccion;

        public int? Id { get => id; set => id = value; }
        public string Dni { get => dni; set => dni = value; }
        public string Nombres { get => nombres; set => nombres = value; }
        public string Apellidos { get => apellidos; set => apellidos = value; }
        public string Direccion { get => direccion; set => direccion = value; }
    }
}