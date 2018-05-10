package es.ucm.abd.practica2.controller;

import es.ucm.abd.practica2.model.Abedemon;

/**
 * Resultado de una pelea.
 * 
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public class FightResult {
    public static enum Winner {
        ONE, TWO, TIE
    }
    
    // Adversario izquierdo
    private final Abedemon abedemon1;
    // Adversario derecho
    private final Abedemon abedemon2;
    // Daño recibido por el izquierdp
    private final int damage1;
    // Daño recibido por el derecho
    private final int damage2;
    // Ganador (ONE = izquierdo, TWO = derecho, TIE = empate)
    private final Winner winner;

    public FightResult(Abedemon abedemon1, Abedemon abedemon2, int damage1, int damage2, Winner winner) {
        this.abedemon1 = abedemon1;
        this.abedemon2 = abedemon2;
        this.damage1 = damage1;
        this.damage2 = damage2;
        this.winner = winner;
    }

    public Abedemon getAbedemon1() {
        return abedemon1;
    }

    public Abedemon getAbedemon2() {
        return abedemon2;
    }

    public int getDamage1() {
        return damage1;
    }

    public int getDamage2() {
        return damage2;
    }

    public Winner getWinner() {
        return winner;
    }
}
