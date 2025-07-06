package patitasolidaria;

import java.util.ArrayList;

public class Animal {

    protected String nombre;
    protected String especie;
    protected String id;
    protected ArrayList<Gasto> gastos = new ArrayList<>();

    public Animal(String nombre, String especie, String id) {
        this.nombre = nombre;
        this.especie = especie;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }

    public String getId() {
        return id;
    }

    public void agregarGasto(Gasto g) {
        gastos.add(g);
    }

    public double totalGastado() {
        double gastoTotal = 0;
        for (Gasto g : gastos) {
            gastoTotal += g.getMonto();
        }
        return gastoTotal;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + "(" + especie + ")";
    }

}
