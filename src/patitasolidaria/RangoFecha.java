package patitasolidaria;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RangoFecha {

    LocalDate fechaInicio;
    LocalDate fechaFin;

    public RangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    public static RangoFecha pedirRangoValido(Scanner sc){

                    LocalDate fechaInicio = null;
                    LocalDate fechaFin = null;
                    boolean fechasValidas = false;

                    while (!fechasValidas) {
                        try {
                            System.out.println("Ingrese fecha inicial (yyyy-mm-dd):");
                            String fechaInicial = sc.nextLine();
                            System.out.println("Ingrese fecha final (yyyy-mm-dd):");
                            String fechaFinal = sc.nextLine();
                            fechaInicio = LocalDate.parse(fechaInicial);
                            fechaFin = LocalDate.parse(fechaFinal);
                            if (fechaInicio.isAfter(fechaFin)) {
                                System.out.println("La fecha inicial no puede ser posterior a la fecha final!");
                                continue;
                            } else {
                                fechasValidas = true;
                            }

                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Por favor, use el formato yyyy-mm-dd");
                        }
                    } return new RangoFecha(fechaInicio, fechaFin);
    }

}
