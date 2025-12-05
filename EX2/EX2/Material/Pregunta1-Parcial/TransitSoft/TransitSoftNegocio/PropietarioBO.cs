using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoftNegocio.ServiciosWS;

namespace TransitSoftNegocio
{
    public class PropietarioBO
    {
        private ServicioPropietarioClient propietarioClientSOAP;
        public PropietarioBO() { 
            this.propietarioClientSOAP = new ServicioPropietarioClient();
        }

        public int Insertar(propietario propietarioDTO)
        {
            return this.propietarioClientSOAP.insertarPropietario(propietarioDTO);
        }
        public int Modificar(propietario propietarioDTO)
        {
            return this.propietarioClientSOAP.modificarPropietario(propietarioDTO);
        }
        public int Eliminar(int id)
        {
            return this.propietarioClientSOAP.eliminarPropietario(id);
        }
        public propietario ObtenerPorId(int id)
        {
            return this.propietarioClientSOAP.obtenerPropietarioPorId(id);
        }
        public BindingList<propietario> ListarTodos()
        {
            return new BindingList<propietario>(this.propietarioClientSOAP.listarTodosPropietarios());
        }
    }
}
