using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using TransitSoftNegocio;
using TransitSoftNegocio.ServiciosWS;

namespace PruebaConexionSOAP
{
    public class Program
    {
        static void Main(string[] args)
        {
            VehiculoBO vehiculoBO = new VehiculoBO();

            ImprimirVehiculos();

            vehiculo aux = new vehiculo();
            aux.anho = 2025;
            aux.placa = "AAA-BBB";
            aux.modelo = "SUV";
            aux.marca = "HYUNDAI";

            int id = vehiculoBO.Insertar(aux);

            ImprimirVehiculos();

            vehiculoBO.Eliminar(id);

            ImprimirVehiculos();

            System.Console.WriteLine("=========================================");
            System.Console.WriteLine("=============PROPIETARIOS================");
            System.Console.WriteLine("=========================================");

            PropietarioBO propietarioBO = new PropietarioBO();

            ImprimirPropietarios();

        }

        static void ImprimirVehiculos()
        {

            VehiculoBO vehiculoBO = new VehiculoBO();

            BindingList<vehiculo> vehiculos = vehiculoBO.ListarTodos();
            int cont = 0;
            foreach (vehiculo vehiculoDTO in vehiculos)
            {
                System.Console.WriteLine(vehiculoDTO.id + "   " + vehiculoDTO.marca + "    "
                    + vehiculoDTO.placa + "     " + vehiculoDTO.modelo);
            }
        }

        static void ImprimirPropietarios()
        {

            PropietarioBO propietarioBO = new PropietarioBO();

            BindingList<propietario> propietarios = propietarioBO.ListarTodos();
            int cont = 0;
            foreach (propietario propietarioDTO in propietarios)
            {
                System.Console.WriteLine(propietarioDTO.id + "  " + propietarioDTO.nombres + 
                    "   " + propietarioDTO.apellidos);
            }
        }

    }
}
