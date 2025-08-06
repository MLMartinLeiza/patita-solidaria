package patitasolidaria;

import java.time.LocalDate;

public class Gasto extends Movimiento {

    protected String motivo;
    protected double monto;
    private String idAnimal;

    public Gasto(String motivo, double monto, String idAnimal, LocalDate fecha) {
        super(fecha);
        this.motivo = motivo;
        this.monto = monto;
        this.idAnimal = idAnimal;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return  "$" + monto + " en " + motivo + " (" + fecha + ")";
    }

}
