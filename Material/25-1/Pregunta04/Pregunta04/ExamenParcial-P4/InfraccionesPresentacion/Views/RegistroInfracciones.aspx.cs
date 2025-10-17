using InfraccionesDominio;
using InfraccionesNegocio;
using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

namespace InfraccionesPresentacion
{
    public partial class RegistroInfracciones : System.Web.UI.Page
    {
        private ConductorBO conductorBO;
        private VehiculoBO vehiculoBO;
        private InfraccionBO infraccionBO;
        private Conductor conductor;
        private Infraccion infraccion;

        public Conductor Conductor { get => conductor; set => conductor = value; }

        protected void Page_Init(object sender, EventArgs e)
        {
            conductorBO = new ConductorBO();
            vehiculoBO = new VehiculoBO();
            infraccionBO = new InfraccionBO();
            conductor = null;
            infraccion = null;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            RptInfracciones.DataSource = infraccionBO.ListarInfracciones();
            RptInfracciones.DataBind();
        }

        protected void BtnBuscarConductor_Click(object sender, EventArgs e)
        {
            Conductor = conductorBO.ListarPorLicencia(TxtNumLicencia.Text.Trim());
            Session["conductor"] = Conductor;
            TxtConductor.Text = Conductor.NombreApellidos;
            Session["vehiculosConductor"] = vehiculoBO.ListarPorConductor(Conductor.ConductorId);
            DdlVehiculos.DataSource = (List<Vehiculo>)Session["vehiculosConductor"];
            DdlVehiculos.DataTextField = "Placa";
            DdlVehiculos.DataBind();
            LblPuntosAcumulados.Text = Conductor.PuntosAcumulados.ToString();
        }

        protected void BtnSeleccionarInfraccion_Click(object sender, EventArgs e)
        {
            int infraccionId = Int32.Parse((sender as LinkButton).CommandArgument);
            infraccion = this.infraccionBO.ObtenerInfraccion(infraccionId);
            Session["infraccion"] = infraccion;
            TxtInfraccion.Text = infraccion.Descripcion;
            TxtMontoMulta.Text = "S/ "+infraccion.MontoMulta.ToString();
            TxtGravedad.Text = infraccion.Gravedad.ToString();
            TxtPuntos.Text = infraccion.Puntos.ToString();
        }

        protected void BtnRegistrar_Click(object sender, EventArgs e)
        {
            RegistroInfraccion registro = new RegistroInfraccion();
            registro.Conductor = (Conductor)Session["conductor"];
            registro.Fecha = DateTime.Parse(TxtFecha.Text);
            registro.Infraccion = (Infraccion)Session["infraccion"];
            string placa = DdlVehiculos.SelectedValue;
            foreach (Vehiculo vc in (List<Vehiculo>)Session["vehiculosConductor"])
            {
                if(vc.Placa.Equals(placa))
                {
                    registro.Vehiculo = vc;
                    break;
                }
            }
            try
            {
                infraccionBO.RegistrarInfraccionConductor(registro);
                MostrarMensaje("Infraccion registrada correctamente.",true);
            }
            catch (ArgumentException ex)
            {
                // Error de validación del negocio
                MostrarMensaje(ex.Message, false);
            }
        }

        private void MostrarMensaje(string mensaje, bool esExito)
        {
            LblMensaje.Text = mensaje;
            LblMensaje.ForeColor = esExito ? System.Drawing.Color.Green : System.Drawing.Color.Red;
            LblMensaje.Visible = true;
        }
    }
}