using System;

namespace TransitSoft.Model
{
    public class Vehiculo
    {
        private int? id;
        private string placa;
        private string marca;
        private string modelo;
        private int? anho;
        private Propietario propietario;

        public int? Id { get => id; set => id = value; }
        public string Placa { get => placa; set => placa = value; }
        public string Marca { get => marca; set => marca = value; }
        public string Modelo { get => modelo; set => modelo = value; }
        public int? Anho { get => anho; set => anho = value; }
        public Propietario Propietario { get => propietario; set => propietario = value; }
    }
}