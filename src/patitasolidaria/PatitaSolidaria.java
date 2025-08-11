package patitasolidaria;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.util.List;

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

    public static SeleccionGasto seleccionarGastoDeAnimal(Scanner sc, Refugio refugio) {
        System.out.println("Ingrese el ID del animal");
        String idAnimal = sc.nextLine();

        Animal a = refugio.buscarAnimalPorId(idAnimal);

        if (a == null) {
            System.out.println("No se encontró animal con ese ID!");
            return null;
        }

        List<Gasto> gastos = a.getGastos();

        if (gastos.isEmpty()) {
            System.out.println("Este animal no tiene gastos registrados!");
            return null;
        }

        System.out.println("------Gastos registrados de " + a.getNombre() + "------");

        for (int i = 0; i < gastos.size(); i++) {
            System.out.println(i + ": " + gastos.get(i));
        }

        System.out.println("Ingrese el índice del gasto: ");
        int indice = sc.nextInt();
        sc.nextLine();

        if (indice < 0 || indice >= gastos.size()) {
            System.out.println("Índice inválido");
            return null;
        }
        return new SeleccionGasto(idAnimal, indice);
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
                    + "\n 9. Modificar motivo de un gasto"
                    + "\n 10. Modificar monto de un gasto"
                    + "\n 11. Eliminar gasto de un animal"
                    + "\n 12. Contador de animales ayudados"
                    + "\n 13. Salir");

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
                    SeleccionGasto seleccionGasto = seleccionarGastoDeAnimal(sc, refugio);
                    if (seleccionGasto == null) {
                        break;
                    }

                    System.out.println("Ingrese el nuevo motivo:");
                    String nuevoMotivo = sc.nextLine();

                    refugio.modificarMotivoGasto(seleccionGasto.getId(), seleccionGasto.getIndice(), nuevoMotivo);

                    break;

                case 10:

                    seleccionGasto = seleccionarGastoDeAnimal(sc, refugio);
                    if (seleccionGasto == null) {
                        break;
                    }

                    double nuevoMonto;
                    do {
                        System.out.println("Ingrese el nuevo monto");
                        nuevoMonto = sc.nextDouble();
                        sc.nextLine();
                        if (nuevoMonto <= 0) {
                            System.out.println("Ingrese un monto válido!");
                        }
                    } while (nuevoMonto <= 0);

                    refugio.modificarMontoGasto(seleccionGasto.getId(), seleccionGasto.getIndice(), nuevoMonto);

                    break;

                case 11:
                    seleccionGasto = seleccionarGastoDeAnimal(sc, refugio);
                    if (seleccionGasto == null) {
                        break;
                    }
                    refugio.eliminarGastoAnimal(seleccionGasto.getId(), seleccionGasto.getIndice());
                    break;

                case 12:
                    int totalAyudados = refugio.contarAnimalesAyudados();
                    System.out.println("Animales ayudados: " + totalAyudados);
                    break;

                case 13:
                    System.out.println("Saliendo, gracias por tu colaboración!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente!");
                    break;
            }

        } while (opcion != 13);
    }

}
