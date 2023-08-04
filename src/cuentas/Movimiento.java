package cuentas;

import java.util.Date;

public class Movimiento {
    private TipoMovimiento tipo;
    private Date fecha;
    private float monto;

    public Movimiento(TipoMovimiento tipo, Date fecha, float monto) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.monto = monto;
    }

    @Override
    public String toString() {
        String tipoStr = "";

        if(tipo == TipoMovimiento.ABONO)
            tipoStr += "[+] ";
        else
            tipoStr += "[-] ";

        return tipoStr + tipo + " -> Monto: $" + monto + ", Fecha: " + fecha;
    }
}
