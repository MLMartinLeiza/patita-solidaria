package patitasolidaria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Refugio {

    protected ArrayList<Animal> animales = new ArrayList<>();
    protected ArrayList<Donacion> donaciones = new ArrayList<>();
    protected ArrayList<Movimiento> movimientos = new ArrayList();

    private int contadorAnimales = 1;

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public String generarNuevoIdAnimal() {
        String id = "A" + String.format("%03d", contadorAnimales);
        contadorAnimales++;
        return id;
    }

    public void agregarDonacion(Donacion d) {
        donaciones.add(d);
        movimientos.add(d);
    }

    public void agregarGastoAAnimal(String idAnimal, String motivo, double monto) {
        Animal animal = buscarAnimalPorId(idAnimal);
        if (animal != null) {
            Gasto gasto = new Gasto(motivo, monto, LocalDate.now());
            animal.getGastos().add(gasto);
            movimientos.add(gasto);
            System.out.println("Gasto agregado correctamente!");
        } else {
            System.out.println("No se encontró animal con ese ID!");
        }
    }

    public void agregarAnimal(Animal a) {
        if (buscarAnimalPorId(a.getId()) != null) {
            System.out.println("Ya existe un animal con ese ID");
            return;
        }
        animales.add(a);
        System.out.println("Animal agregado correctamente");
    }

    public Animal buscarAnimalPorId(String id) {
        for (Animal a : animales) {
            if (a.getId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    public double totalDonado() {
        double totalDonado = 0;
        for (Donacion d : donaciones) {
            totalDonado += d.getMonto();
        }
        return totalDonado;
    }

    public double totalGastado() {
        double totalGastado = 0;
        for (Animal a : animales) {
            totalGastado += a.totalGastado();
        }
        return totalGastado;
    }

    public void mostrarResumen() {
        double donacionesRecibidas = totalDonado();
        double gastosTotales = totalGastado();
        double saldoDisponible = donacionesRecibidas - gastosTotales;
        System.out.println("Balance: "
                + "\n Donaciones recibidas: " + donacionesRecibidas
                + "\n Gastos realizados: " + gastosTotales
                + "\n Saldo Disponible: " + saldoDisponible);
    }

    public void listarMovimientos() {
        Collections.sort(movimientos, new Comparator<Movimiento>() {
            @Override
            public int compare(Movimiento m1, Movimiento m2) {
                return m1.getFecha().compareTo(m2.getFecha());
            }
        });

        for (Movimiento m : movimientos) {

            if (m instanceof Gasto) {
                encontrado:
                for (Animal a : animales) {
                    for (Gasto g : a.getGastos()) {
                        if (g.equals(m)) {
                            System.out.println("---- Gasto de: " + a.getNombre() + " (ID: " + a.getId() + ") ----");
                            break encontrado;
                        }
                    }
                }
            } else if (m instanceof Donacion) {
                System.out.println("---- Donación ----");
            }
            System.out.println(m);
        }
        mostrarResumen();
    }

    public void listarAnimales() {
        System.out.println("Animales registrados:");
        for (Animal a : animales) {
            System.out.println(a);
        }
    }

    public void filtrarGastosPorFechas(String id, LocalDate fechaInicial, LocalDate fechaFinal) {
        boolean gastoEncontrado = false;
        boolean animalEncontrado = false;
        for (Animal a : animales) {
            if (a.getId().equalsIgnoreCase(id)) {
                animalEncontrado = true;
                System.out.println("Gastos del animal: " + " " + a.getNombre() + " - ID: " + a.getId());
                for (Gasto g : a.getGastos()) {
                    if ((g.getFecha().isAfter(fechaInicial) || g.getFecha().isEqual(fechaInicial)) && (g.getFecha().isBefore(fechaFinal) || g.getFecha().isEqual(fechaFinal))) {
                        System.out.println(g);
                        gastoEncontrado = true;
                    }

                }
                if (!gastoEncontrado) {
                    System.out.println("No se encontraron gastos en ese lapso de fechas. ");
                }
            }

        }
        if (!animalEncontrado) {
            System.out.println("No se encontró animal con ese ID");
        }
    }

    public void filtrarDonacionPorFecha(LocalDate inicio, LocalDate fin) {
        boolean donacionEncontrada = false;
        for (Donacion d : donaciones) {
            if ((d.getFecha().isEqual(inicio) || d.getFecha().isAfter(inicio)) && (d.getFecha().isEqual(fin) || d.getFecha().isBefore(fin))) {
                System.out.println(d);
                donacionEncontrada = true;
            }
        }
        if (!donacionEncontrada) {
            System.out.println("No se encontró donación en ese lapso de fechas!");
        }
    }

}
