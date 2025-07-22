package patitasolidaria;
import java.time.LocalDate;

public abstract class Movimiento {
    protected LocalDate fecha;

    public Movimiento(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public abstract String toString();   
}
