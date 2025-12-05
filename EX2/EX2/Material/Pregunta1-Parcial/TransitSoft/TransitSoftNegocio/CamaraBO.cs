using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoftNegocio.ServiciosWS;

namespace TransitSoftNegocio
{
    public class CamaraBO
    {

        private ServicioCamaraClient camaraClienteSOAP;

        public CamaraBO()
        {
            this.camaraClienteSOAP = new ServicioCamaraClient();
        }

        public int Insertar(camara camaraDTO)
        {
            return this.camaraClienteSOAP.insertarCamara(camaraDTO);
        }
        public int Modificar(camara camaraDTO)
        {
            return this.camaraClienteSOAP.modificarCamara(camaraDTO);
        }
        public int Eliminar(int id)
        {
            return this.camaraClienteSOAP.eliminarCamara(id);
        }
        public camara ObtenerPorId(int id)
        {
            return this.camaraClienteSOAP.obtenerCamaraPorId(id);
        }
        public BindingList<camara> ListarTodos()
        {
            return new BindingList<camara> (this.camaraClienteSOAP.listarTodosCamara());
        }
    }
}
