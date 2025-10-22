using DataAccess.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TransitSoftBusiness.BO;
using TransitSoftBusiness.BOI;

namespace TransitSoftWA
{
    /* Escriba su codigo y nombre completo
    Codigo PUCP:
    Nombre Completo:
    */
    public partial class AsignarVehiculosAConductor : System.Web.UI.Page
    {
        private Propietario propietario;

        private IVehiculoBO vehiculoBO;
        private IPropietarioBO propietarioB0;
        private BindingList<Vehiculo> listaVehiculosAsignar;
        protected void Page_Load(object sender, EventArgs e)
        {
            vehiculoBO = new VehiculoBOImpl();
            propietarioB0 = new PropietarioBOImpl();

            if (!IsPostBack)
            {
                listaVehiculosAsignar = new BindingList<Vehiculo>();
                Session["listaVehiculosAsignar"] = listaVehiculosAsignar;

                ddlPropietarios.DataSource = propietarioB0.listarPropietariosSinVehiculoAsignado();
                ddlPropietarios.DataTextField = "DNINombreCompleto";
                ddlPropietarios.DataValueField = "Id";
                ddlPropietarios.DataBind();

                ddlVehiculos.DataSource = vehiculoBO.listarVehiculosSinPropietarioAsignado();
                ddlVehiculos.DataValueField = "Id";
                ddlVehiculos.DataTextField = "Datos";
                ddlVehiculos.DataBind();
            }
               

            String accion = Request.QueryString["accion"];
            if (accion == null)
            {
                lblTitulo.Text = "Asignar Vehículos a Propietario";
            }else if(accion == "visualizar")
            {
                propietario = (Propietario)Session["propietario"];
                gvVehiculos.DataSource = vehiculoBO.listarVehiculosPorIDPropietario(propietario.Id);
                gvVehiculos.DataBind();
                ddlPropietarios.Items.Insert(0, new ListItem(propietario.DNINombreCompleto, propietario.Id.ToString()));
                ddlPropietarios.SelectedIndex = 0;
                lblTitulo.Text = "Ver Vehículos del Propietario";
                ddlPropietarios.Enabled = false;
                ddlVehiculos.Enabled = false;
                lbGuardar.Visible = false;
                lbAgregarVehiculo.Visible = false;
                gvVehiculos.Enabled = false;
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarVehiculosDePropietario.aspx");
        }

        protected void lbAgregarVehiculo_Click(object sender, EventArgs e)
        {
            int idVehiculo = Int32.Parse(ddlVehiculos.SelectedValue);
            listaVehiculosAsignar = (BindingList<Vehiculo>)Session["listaVehiculosAsignar"];

            Vehiculo vehiculo = new Vehiculo();
            string[] datos = ddlVehiculos.SelectedItem.Text.Split(new string[] {" - "}, StringSplitOptions.None);
            for (int i = 0; i < datos.Length; i++)
            {
                datos[i] = datos[i].Trim();
            }
            vehiculo.Marca = datos[0];
            vehiculo.Modelo = datos[1];
            vehiculo.Placa = datos[2];
            vehiculo.Anho = Int32.Parse(datos[3]);
            vehiculo.Id = idVehiculo;
            listaVehiculosAsignar.Add(vehiculo);
            Session["listaVehiculosAsignar"] = listaVehiculosAsignar;
            CargarGrid();
        }

        private void CargarGrid()
        {
            listaVehiculosAsignar = (BindingList<Vehiculo>)Session["listaVehiculosAsignar"];
            gvVehiculos.DataSource = listaVehiculosAsignar;
            gvVehiculos.DataBind();
        }

        protected void lbBorrarLOV_Click(object sender, EventArgs e)
        {
            listaVehiculosAsignar = (BindingList<Vehiculo>)Session["listaVehiculosAsignar"];
            string placa = (sender as LinkButton).CommandArgument;
            for (int i = 0; i < listaVehiculosAsignar.Count; i++)
            {
                if (placa.Equals(listaVehiculosAsignar[i].Placa))
                {
                    listaVehiculosAsignar.RemoveAt(i);
                    break;
                }
            }
            CargarGrid();
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            propietario = new Propietario();
            listaVehiculosAsignar = (BindingList<Vehiculo>)Session["listaVehiculosAsignar"];
            propietario.Id = Int32.Parse(ddlPropietarios.SelectedItem.Value);
            propietario.Vehiculos = listaVehiculosAsignar;
            listaVehiculosAsignar = new BindingList<Vehiculo>();
            Session["listaVehiculosAsignar"] = listaVehiculosAsignar;

            propietarioB0.RegistrarAsignacionDeVehiculosAPropietario(propietario);

            Response.Redirect("AsignarVehiculosAPropietario.aspx");
        }
    }
}