package admin;

import cuentas.CuentaCorriente;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministradorCuentas {
    public static ArrayList<CuentaCorriente> cuentas = new ArrayList<>();
    public static HashSet<Integer> identificadoresOcupados = new HashSet<>();

    // opciones posibles para el usuario
    private static final int LISTAR_CUENTAS = 1, VER_DETALLE = 2, CREAR_CUENTA = 3, ELIMINAR_CUENTA = 4, ABONAR = 5, CARGAR = 6, SALIR = 7;

    // inicializar scanner
    public static final Scanner scanner = new Scanner(System.in);


    public static void agregarCuenta(CuentaCorriente cuenta) {
        // añade el ID a los que ya se han ocupado
        AdministradorCuentas.identificadoresOcupados.add(cuenta.getNumero());

        // genera un ID random y lo asigna al número de la cuenta
        cuenta.setNumero(Utils.generarIdRandom());

        // añadir la cuenta al listado de cuentas
        AdministradorCuentas.cuentas.add(cuenta);
    }

    public static void programaUsuario() {

        // creamos un loop infinito
        while(true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Que deseas hacer? (*:necesario)");
            System.out.println("1. Listar cuentas existentes");
            System.out.println("2. Ver detalle de una cuenta (codigo*)");
            System.out.println("3. Crear una cuenta");
            System.out.println("4. Eliminar una cuenta (codigo*)");
            System.out.println("5. Abonar (codigo*)");
            System.out.println("6. Cargar (codigo*)");
            System.out.println("7. Salir");
            System.out.println();
            System.out.print("Seleccion: ");

            // comprueba si el numero ingresado es valido
            int seleccion;
            try {
                seleccion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Debes elegir una opcion valida!!");
                scanner.nextLine(); // usa la línea que queda en el buffer del scanner
                continue;
            }

            // selección con switch
            switch (seleccion) {
                case LISTAR_CUENTAS -> listarCuentas();
                case VER_DETALLE    -> verDetalle();
                case CREAR_CUENTA   -> crearCuenta();
                case ELIMINAR_CUENTA -> eliminarCuenta();
                case ABONAR         -> movimiento(ABONAR);
                case CARGAR         -> movimiento(CARGAR);
                case SALIR          -> salir();
                default -> System.out.println("El numero ingresado no es valido, intente nuevamente.\n");
            }
        }
    }

    private static void listarCuentas() {
        // decoración
        System.out.println("--------------------------------------------------------");
        System.out.println("Cuentas existentes:");

        // recorrer array y mostrar datos básicos
        for(CuentaCorriente cuenta : AdministradorCuentas.cuentas) {
            System.out.println("- " + cuenta.datosBasicosToString());
        }
    }

    private static void verDetalle() {
        // solicita el numero de cuenta
        System.out.print("Ingrese el numero de la cuenta: ");

        // comprueba si el numero ingresado es valido
        int numero;
        try {
            numero = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Debes ingresar un codigo valido!");
            scanner.nextLine();
            return;
        }

        // busca la cuenta
        CuentaCorriente cuenta = buscarCuentaPorId(numero);

        if(cuenta == null) {
            System.out.println("No se ha encontrado la cuenta con el numero " + numero);
            return;
        }

        // imprime los detalles de la cuenta que encuentra
        System.out.println(cuenta);
    }

    private static void crearCuenta() {
        // solicita nombre del titular
        System.out.print("Ingrese el nombre del titular: ");
        scanner.nextLine();
        String titular = scanner.nextLine();

        // crea una cuenta
        CuentaCorriente nuevaCuenta = new CuentaCorriente(titular);

        // imprime los datos de la nueva cuenta
        System.out.println("Cuenta creada con los siguientes datos:");
        System.out.println(nuevaCuenta);
    }

    private static void eliminarCuenta() {
        System.out.print("Ingrese el numero de la cuenta que desea eliminar: ");

        // comprueba si el numero ingresado es valido
        int numero;
        try {
            numero = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Debes ingresar un numero de cuenta valido!");
            scanner.nextLine(); // usa la línea que queda en el buffer del scanner
            return;
        }

        CuentaCorriente cuenta = buscarCuentaPorId(numero);

        // comprueba si la cuenta fue encontrada
        if(cuenta == null) {
            System.out.println("No se ha encontrado la cuenta con el numero " + numero);
            return;
        }

        // si encuentra la cuenta se muestra el saldo actual
        System.out.println("Se ha encontrado la cuenta, el titular de la cuenta es: " + cuenta.getTitular());

        // comprueba si la cuenta tiene saldo
        if(cuenta.getSaldo() > 0) {
            System.out.println("La cuenta no debe tener saldo para poder eliminarla primero extraiga el dinero y vuelva a intentar.");

            System.out.println("El saldo actual es de $" + cuenta.getSaldo());
            return;
        } else {
            System.out.println("Esta seguro que desea eliminar la cuenta con el titular " + cuenta.getTitular() + "? [s/n]");
        }

        String respuesta;
        while(true) {
            respuesta = scanner.next();

            if(respuesta.equalsIgnoreCase("n")) {
                System.out.println("No se ha eliminado la cuenta por decision del usuario");
                return;
            } else if(respuesta.equalsIgnoreCase("s")) {
                cuentas.remove(cuenta);
                System.out.println("La cuenta de " + cuenta.getTitular() + " ha sido eliminada.");
                break;
            } else {
                System.out.println("Ingresa una de las respuestas disponibles 's' para si, 'n' para no.");
            }

        }

    }

    private static void movimiento(int tipoMovimiento) {
        System.out.print("Ingrese el numero de la cuenta: ");


        // comprueba si el numero ingresado es valido
        int numero;
        try {
            numero = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Debes ingresar un numero de cuenta valido!");
            scanner.nextLine(); // usa la línea que queda en el buffer del scanner
            return;
        }

        // busca la cuenta en el array de cuentas
        CuentaCorriente cuenta = buscarCuentaPorId(numero);

        // comprueba si la cuenta fue encontrada
        if(cuenta == null) {
            System.out.println("No se ha encontrado la cuenta con el numero " + numero);
            return;
        }

        // si encuentra la cuenta se muestra el saldo actual
        System.out.println("Se ha encontrado la cuenta, el saldo actual es de: $" + cuenta.getSaldo());

        // lee la cantidad que el usuario ingresa
        float cantidad;
        while(true) {

            // imprime lo que el usuario debe ingresar dependiendo de la acción
            if(tipoMovimiento == ABONAR)
                System.out.print("Cuanto desea abonar?: ");
            else
                System.out.print("Cuanto desea cargar?: ");

            // intenta leer la cantidad si no repite el proceso hasta que se use un número válido
            try {
                cantidad = scanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("El numero ingresado no es valido, intente nuevamente.");
                scanner.nextLine();
            }
        }

        // ejecuta la acción que el usuario desea
        if(tipoMovimiento == ABONAR)
            cuenta.abonar(cantidad);
        else
            cuenta.cargar(cantidad);

        // se imprime el nuevo saldo de la cuenta
        System.out.println("El nuevo saldo es de: " + cuenta.getSaldo());
    }

    private static void salir() {
        System.exit(0);
    }

    private static CuentaCorriente buscarCuentaPorId(int numero) {
        // recorre el array buscando la cuenta que coincida con el número
        for(CuentaCorriente cuenta : AdministradorCuentas.cuentas)
            if(cuenta.getNumero() == numero)
                return cuenta;

        return null;
    }
}