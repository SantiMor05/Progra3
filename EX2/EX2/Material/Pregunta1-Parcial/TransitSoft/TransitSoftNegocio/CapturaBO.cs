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
    public class CapturaBO
    {
        private ServicioCapturaClient capturaClientSOAP;

        public CapturaBO()
        {
            this.capturaClientSOAP = new ServicioCapturaClient();
        }

        BindingList<captura> ObtenerCapturasConExcesoDeVelocidad()
        {
            return new BindingList<captura>(this.capturaClientSOAP.obtenerCapturasConExcesoDeVelocidad());
        }
        bool Actualizar(captura modelo)
        {
            return this.capturaClientSOAP.actualizarCaptura(modelo);
        }
        public BindingList<captura> LeerTodos()
        {
            return new BindingList<captura>(this.capturaClientSOAP.listarTodasCapturas());
        }
        public int Insertar(captura capturaDTO)
        {
            return this.capturaClientSOAP.insertarCaptura(capturaDTO);
        }
        public int Modificar(captura capturaDTO)
        {
            return this.capturaClientSOAP.modificarCaptura(capturaDTO);
        }
        public int Eliminar(int id)
        {
            return this.capturaClientSOAP.eliminarCaptura(id);
        }
        public captura ObtenerPorId(int id)
        {
            return this.capturaClientSOAP.obtenerCapturaPorId(id);
        }
    }
}
