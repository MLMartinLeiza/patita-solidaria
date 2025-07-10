package patitasolidaria;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class PatitaSolidaria {

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
                    + "\n 6. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            double monto = 0;

            switch (opcion) {
                case 1:
                    boolean entradaValida = false;
                    System.out.println("Ingrese el monto a donar");

                    while (!entradaValida) {
                        try {
                            monto = sc.nextDouble();
                            if (monto <= 0) {
                                System.out.println("El monto debe ser mayor que cero.");
                            } else {
                                entradaValida = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: ingresar solo números!");
                            sc.nextLine();
                        }
                    }
                    sc.nextLine();
                    LocalDate fecha = LocalDate.now();
                    Donacion donacion = new Donacion(monto, fecha);
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
                    String id;

                    id = refugio.generarNuevoIdAnimal();
                    Animal animal = new Animal(nombre, especie, id);
                    refugio.agregarAnimal(animal);
                    break;

                case 3:
                    System.out.println("Ingrese ID del animal");
                    id = sc.nextLine();

                    Animal a = refugio.buscarAnimalPorId(id);

                    if (a != null) {
                        System.out.println("Ingrese motivo del gasto");
                        String motivo = sc.nextLine();

                        System.out.println("Ingrese el monto del gasto");
                        monto = 0;
                        entradaValida = false;

                        while (!entradaValida) {
                            try {
                                monto = sc.nextDouble();
                                sc.nextLine();

                                if (monto <= 0) {
                                    System.out.println("El gasto debe ser mayor que cero");
                                } else {
                                    entradaValida = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: ingresar solo números!");
                                sc.nextLine();
                            }
                        }

                        fecha = LocalDate.now();
                        Gasto g = new Gasto(motivo, monto, fecha);
                        a.agregarGasto(g);

                        System.out.println("Gasto agregado correctamente");
                    } else {
                        System.out.println("No se encontró animal con ese ID");
                    }
                    break;

                case 4:
                    refugio.mostrarResumen();
                    break;

                case 5:
                    refugio.listarMovimientos();
                    break;

                case 6:
                    System.out.println("Saliendo, gracias por tu colaboración");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    break;
            }

        } while (opcion != 0);
    }

}
