package patitasolidaria;

import java.time.LocalDate;

public class Donacion {

    protected double monto;
    protected LocalDate fecha;

    public Donacion(double monto, LocalDate fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Donacion de $" + monto + " el " + fecha;
    }

}
