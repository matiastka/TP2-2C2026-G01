package edu.fiuba.algo3.modelo;

public class Agua extends Terreno{

    // Ver patron State/Strategy con Claude

    public boolean sosAgua(){
        return this.equals(Agua.class) ;
    }

    @Override
    public boolean esTransitable() {
        return true;
    }
}
