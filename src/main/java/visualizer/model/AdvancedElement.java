/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author Anytram
 */
public class AdvancedElement implements Comparable<AdvancedElement> {

    private String name;
    @JsonProperty("atomic_mass")
    private double atomicMass;
    private double boil;
    private double density;
    private double melt;
    @JsonProperty("number")
    private int atomicNumber;
    private int period;
    private String phase;
    private String summary;
    private String symbol;
    private int[] shells;

    public AdvancedElement() {
    }

    public AdvancedElement(String name, int atomicMass, int boil, double density, int melt, int atomicNumber, int period, String phase, String summary, String symbol, int[] shells) {
        this.name = name;
        this.atomicMass = atomicMass;
        this.boil = boil;
        this.density = density;
        this.melt = melt;
        this.atomicNumber = atomicNumber;
        this.period = period;
        this.phase = phase;
        this.summary = summary;
        this.symbol = symbol;
        this.shells = shells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public void setAtomicMass(int atomicMass) {
        this.atomicMass = atomicMass;
    }

    public double getBoil() {
        return boil;
    }

    public void setBoil(int boil) {
        this.boil = boil;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getMelt() {
        return melt;
    }

    public void setMelt(int melt) {
        this.melt = melt;
    }

    public int getNeutronNumber() {
        int electrons = 0;
        for (int j = 0; j < getShells().length; j++) {
            electrons += shells[j];
        }
        int neutrons = (int) Math.round(getAtomicMass()) - electrons;
        return neutrons;
    }

    public double getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(int number) {
        this.atomicNumber = number;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int[] getShells() {
        return shells;
    }

    public void setShells(int[] shells) {
        this.shells = shells;
    }

    public boolean isGas() {
 
        return getPhase().equalsIgnoreCase("Gas");
    }

    public boolean isSolid() {

        return getPhase().equalsIgnoreCase("Solid");
    }

    public boolean isLiquid() {

        return getPhase().equalsIgnoreCase("Liquid");
    }

    @Override
    public String toString() {
        return "AdvancedElement{" + "name=" + name + ", atomicMass=" + atomicMass + ", boil=" + boil + ", density=" + density + ", melt=" + melt + ", number=" + atomicNumber + ", period=" + period + ", phase=" + phase + ", summary=" + summary + ", symbol=" + symbol + ", shells=" + shells + '}';
    }

    @Override
    public int compareTo(AdvancedElement o) {
        
        return (int) this.getAtomicNumber() - (int) o.getAtomicNumber();
    }

}
