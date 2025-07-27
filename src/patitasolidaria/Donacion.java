package patitasolidaria;

import java.time.LocalDate;

public class Donacion extends Movimiento {

    protected double monto;

    public Donacion(double monto, LocalDate fecha) {
        super(fecha);
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "$"+monto + " (" + fecha + ")";
    }

}
