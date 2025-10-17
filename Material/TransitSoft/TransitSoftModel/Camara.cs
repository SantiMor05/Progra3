using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoft.Model
{
    public class Camara
    {
        private int? id;
        private string modelo;
        private string codigoSerie;
        private long? latitud;
        private long? longitud;

        public int? Id { get => id; set => id = value; }
        public string Modelo { get => modelo; set => modelo = value; }
        public string CodigoSerie { get => codigoSerie; set => codigoSerie = value; }
        public long? Latitud { get => latitud; set => latitud = value; }
        public long? Longitud { get => longitud; set => longitud = value; }
    }
}
