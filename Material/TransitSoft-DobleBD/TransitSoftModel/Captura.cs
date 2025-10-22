using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoft.Model
{
    public class Captura
    {
        private int? id;
        private string placa;
        private double? velocidad;
        private DateTime fechaCaptura;
        private Camara camara;
        private EstadoCaptura estado;
        private Vehiculo vehiculo;

        public int? Id { get => id; set => id = value; }
        public string Placa { get => placa; set => placa = value; }
        public double? Velocidad { get => velocidad; set => velocidad = value; }
        public DateTime FechaCaptura { get => fechaCaptura; set => fechaCaptura = value; }
        public Camara Camara { get => camara; set => camara = value; }
        public EstadoCaptura Estado { get => estado; set => estado = value; }
        public Vehiculo Vehiculo { get => vehiculo; set => vehiculo = value; }
    }
}
