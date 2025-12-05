using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoftNegocio.ServiciosWS;

namespace TransitSoftNegocio
{
    public class VehiculoBO
    {
        private ServicioVehiculoClient vehiculoClienteSOAP;
        
        public VehiculoBO()
        {
            this.vehiculoClienteSOAP = new ServicioVehiculoClient();
        }

        public int Insertar(vehiculo vehiculoDTO)
        {
            return this.vehiculoClienteSOAP.insertarVehiculo(vehiculoDTO);
        }
        public int Modificar(vehiculo vehiculoDTO)
        {
            return this.vehiculoClienteSOAP.modificarVehiculo(vehiculoDTO);
        }
        public int Eliminar(int id)
        {
            return this.vehiculoClienteSOAP.eliminarVehiculo(id);
        }
        public vehiculo ObtenerPorId(int id)
        {
            return this.vehiculoClienteSOAP.obtenerVehiculoPorId(id);
        }
        public BindingList<vehiculo> ListarTodos()
        {
            return new BindingList<vehiculo>(this.vehiculoClienteSOAP.listarTodosVehiculos());
        }

    }
}
