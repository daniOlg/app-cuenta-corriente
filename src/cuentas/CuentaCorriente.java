package cuentas;

import admin.AdministradorCuentas;

import java.util.ArrayList;
import java.util.Date;

public class CuentaCorriente {
    private String titular;
    private ArrayList<Movimiento> movimientos = new ArrayList<>(10);
    private int numero;
    private float saldo;


    // imprimir una cuenta
    @Override
    public String toString() {
        String movimientosStr = "\n";

        // dependiendo de la cantidad de movimientos asigna un string
        if (movimientos.size() == 0)
            movimientosStr += "Aun no hay movimientos";
        else if (movimientos.size() == 1)
            movimientosStr += "Ultimo movimiento:";
        else
            movimientosStr += "Ultimos " + movimientos.size() + " movimientos:";

        // añadir los movimientos al string final
        for(Movimiento movimiento : movimientos)
            movimientosStr +=  "\n" + movimiento.toString();

        return "--------------------------------------------------------"
                + "\nTitular: " + this.titular
                + "\nNúmero: " + this.numero
                + "\nSaldo: $" + this.saldo
                + movimientosStr;
    }

    public String datosBasicosToString() {
        return "Titular: " + this.titular + ", Número: " + this.numero;
    }

    // abonar y agregar a los movimientos
    public void abonar(float cantidad) {
        if(cantidad < 0) return;
        this.saldo += cantidad;

        addMovimiento(TipoMovimiento.ABONO, cantidad);
    }

    // cargar y agregar a los movimientos
    public void cargar(float cantidad) {
        this.saldo -= cantidad;

        if(this.saldo < 0) {
            cantidad += saldo; // calcular monto exacto que se carga de la cuenta, no puede extraer mas del saldo
            this.saldo = 0;
        }

        // Añadir cargo al array de movimientos
        addMovimiento(TipoMovimiento.CARGO, cantidad);
    }


    // añadir movimiento a lista de movimientos
    public void addMovimiento(TipoMovimiento tipo, float cantidad) {
        // si ya existen 10 movimientos elimina el primero para dejar paso a agregar otro
        if(movimientos.size() >= 10) {
            movimientos.remove(0);
        }

        this.movimientos.add(new Movimiento(tipo, new Date(), cantidad));
    }

    // constructores (pone saldo en cero, asigna un número aleatorio que no esté usado ya)
    public CuentaCorriente(String titular) {
        this.titular = titular;

        // cuando se abre una cuenta corriente el saldo inicia en cero
        this.saldo = 0;

        AdministradorCuentas.agregarCuenta(this);
    }

    // getters & setters (solo se crean los necesarios)
    public int getNumero() {
        return numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    // no se usa el try/catch, el parámetro que recibe si o si es un int
    // si no daria un compile error y no un runtime error
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
