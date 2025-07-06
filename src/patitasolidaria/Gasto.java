package patitasolidaria;

import java.time.LocalDate;

public class Gasto {

    protected String motivo;
    protected double monto;
    protected LocalDate fecha;

    public Gasto(String motivo, double monto, LocalDate fecha) {
        this.motivo = motivo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Gasto de " + monto + " en " + motivo + "(" + fecha + ")";
    }

}
