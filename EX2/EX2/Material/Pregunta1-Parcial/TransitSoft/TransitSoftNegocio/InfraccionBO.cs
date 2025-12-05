using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using TransitSoftNegocio.ServiciosWS;

namespace TransitSoftNegocio
{
    public class InfraccionBO
    {
        private ServicioInfraccionClient infraccionClientSOAP;

        public InfraccionBO()
        {
            this.infraccionClientSOAP = new ServicioInfraccionClient();
        }

        BindingList<infraccion> CrearInfracciones(BindingList<captura> capturasConExceso)
        {
            return new BindingList<infraccion> (this.infraccionClientSOAP.crearInfracciones(capturasConExceso.ToArray<captura>()));
        }
        public int Insertar(infraccion infraccionDTO)
        {
            return this.infraccionClientSOAP.insertarInfraccion(infraccionDTO);
        }
        public int Modificar(infraccion infraccionDTO)
        {
            return this.infraccionClientSOAP.modificarInfraccion(infraccionDTO);
        }
        public int Eliminar(int id)
        {
            return this.infraccionClientSOAP.eliminarInfraccion(id);
        }
        public infraccion ObtenerPorId(int id)
        {
            return this.infraccionClientSOAP.obtenerInfraccionPorId(id);
        }
        public BindingList<infraccion> ListarTodos()
        {
            return new BindingList<infraccion> (this.infraccionClientSOAP.listarTodasInfracciones());
        }
    }
}
