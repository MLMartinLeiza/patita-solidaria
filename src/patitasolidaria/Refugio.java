package patitasolidaria;

import java.util.ArrayList;

public class Refugio {

    protected ArrayList<Animal> animales = new ArrayList<>();
    protected ArrayList<Donacion> donaciones = new ArrayList<>();

    private int contadorAnimales = 1;

    public String generarNuevoIdAnimal() {
        String id = "A" + String.format("%03d", contadorAnimales);
        contadorAnimales++;
        return id;
    }

    public void agregarDonacion(Donacion d) {
        donaciones.add(d);
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
        System.out.println("No hay coincidencia");
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
        System.out.println("Donaciones Recibidas: ");
        for (Donacion d : donaciones) {
            System.out.println(d);
        }
        System.out.println("Gastos de cada Animal: ");
        for (Animal a : animales) {
            System.out.println(a);
            for (Gasto g : a.getGastos()) {
                System.out.println("  - " + g);
            }
        }
    }

}
