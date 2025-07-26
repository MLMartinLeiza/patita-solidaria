package patitasolidaria;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

public class PatitaSolidaria {

    private static double pedirMontoPositivo(Scanner sc) {
        boolean entradaValida = false;
        double monto = 0;
        System.out.println("Ingrese el monto:");

        while (!entradaValida) {
            try {
                monto = sc.nextDouble();
                if (monto <= 0) {
                    System.out.println("El monto debe ser mayor que cero!");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: ingresar solo números!");
            } finally {
                sc.nextLine();
            }
        }
        return monto;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Refugio refugio = new Refugio();
        int opcion;

        do {
            System.out.println("Menú principal "
                    + "\n 1. Agregar una donación"
                    + "\n 2. Agregar un animal"
                    + "\n 3. Agregar gasto a animal existente"
                    + "\n 4. Ver resumen general"
                    + "\n 5. Ver movimientos"
                    + "\n 6. Listar animales registrados"
                    + "\n 7. Filtrar gasto de animal por fecha"
                    + "\n 8. Filtrar donación por fecha"
                    + "\n 9. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            double monto = 0;

            switch (opcion) {
                case 1:
                    monto = pedirMontoPositivo(sc);
                    LocalDate fechaDonacion = LocalDate.now();
                    Donacion donacion = new Donacion(monto, fechaDonacion);
                    refugio.agregarDonacion(donacion);
                    System.out.println("Gracias por tu aporte!");
                    break;

                case 2:

                    String nombre;
                    String especie;

                    do {
                        System.out.println("Ingrese el nombre del animal");
                        nombre = sc.nextLine();

                        System.out.println("Ingrese la especie");
                        especie = sc.nextLine();

                        if (nombre.trim().isEmpty() || especie.trim().isEmpty()) {
                            System.out.println("Nombre y especie no pueden estar vacíos!");
                        }
                    } while (nombre.trim().isEmpty() || especie.trim().isEmpty());
                    String idAnimal;

                    idAnimal = refugio.generarNuevoIdAnimal();
                    Animal animal = new Animal(nombre, especie, idAnimal);
                    refugio.agregarAnimal(animal);
                    break;

                case 3:
                    System.out.println("Ingrese ID del animal");
                    idAnimal = sc.nextLine();

                    if (refugio.buscarAnimalPorId(idAnimal) != null) {
                        System.out.println("Ingrese motivo del gasto");
                        String motivo = sc.nextLine();

                        monto = pedirMontoPositivo(sc);

                        refugio.agregarGastoAAnimal(idAnimal, motivo, monto);
                        continue;
                    } else {
                        System.out.println("No se encontró animal con ese ID!");
                    }
                    break;

                case 4:
                    refugio.mostrarResumen();
                    break;

                case 5:
                    refugio.listarMovimientos();
                    break;

                case 6:
                    refugio.listarAnimales();
                    break;

                case 7:
                    System.out.println("Ingrese ID del animal:");
                    idAnimal = sc.nextLine();

                    RangoFecha rangoGasto = RangoFecha.pedirRangoValido(sc);
                    refugio.filtrarGastosPorFechas(idAnimal, rangoGasto.getFechaInicio(), rangoGasto.getFechaFin());
                    break;

                case 8:
                    RangoFecha rangoDonacion = RangoFecha.pedirRangoValido(sc);

                    refugio.filtrarDonacionPorFecha(rangoDonacion.getFechaInicio(), rangoDonacion.getFechaFin());
                    break;

                case 9:
                    System.out.println("Saliendo, gracias por tu colaboración!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente!");
                    break;
            }

        } while (opcion != 9);
    }

}
