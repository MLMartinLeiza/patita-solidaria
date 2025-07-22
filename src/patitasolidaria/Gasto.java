package patitasolidaria;

import java.time.LocalDate;

public class Gasto extends Movimiento {

    protected String motivo;
    protected double monto;

    public Gasto(String motivo, double monto, LocalDate fecha) {
        super(fecha);
        this.motivo = motivo;
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Gasto de " + monto + " en " + motivo + " (" + fecha + ")";
    }

}
