package patitasolidaria;

import java.util.Scanner;
import java.time.LocalDate;

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

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el monto a donar");
                    double monto = sc.nextDouble();

                    if (monto <= 0) {
                        System.out.println("El monto debe ser mayor a 0.");
                        break;
                    }

                    sc.nextLine();
                    LocalDate fecha = LocalDate.now();
                    Donacion donacion = new Donacion(monto, fecha);
                    refugio.agregarDonacion(donacion);
                    break;

                case 2:
                    System.out.println("Ingrese el nombre del animal");
                    String nombre = sc.nextLine();

                    System.out.println("Ingrese la especie");
                    String especie = sc.nextLine();

                    String id = refugio.generarNuevoIdAnimal();
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
                        monto = sc.nextDouble();
                        sc.nextLine();

                        if (monto <= 0) {
                            System.out.println("El gasto debe ser mayor que cero");
                            break;
                        }

                        fecha = LocalDate.now();
                        Gasto g = new Gasto(motivo, monto, fecha);
                        a.agregarGasto(g);

                        System.out.println("Gasto agregado correctamente");
                        return;
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
            }

        } while (opcion != 0);
    }

}
