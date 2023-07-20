package cuentas;

public class CuentaCorriente {
    private String titular;
    private int numero;
    private float saldo;

    // Métodos implementados
    public String toString() {
        return "Titular: " + this.titular + "\nNúmero: " + this.numero + "\nSaldo: " + this.saldo;
    }

    public void abonar(float cantidad) {
        if(cantidad < 0) return;

        this.saldo += cantidad;
    }

    public void cargar(float cantidad) {
        this.saldo -= cantidad;

        if(this.saldo < 0)
            this.saldo = 0;
    }

    // Creamos funcion adicional para ver saldo
    public void verSaldo() {
        System.out.println("$"+this.saldo + " pesos");
    }

    // Constructores
    public CuentaCorriente(String titular, int numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public CuentaCorriente(String titular, int numero, float saldo) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = saldo;
    }

    // Getters & Setters

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
