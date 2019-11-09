/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.pc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luiz Flávio
 */
public class CoordenadasModel {
    List<Integer> coordenada = new ArrayList<>();

    public CoordenadasModel(){}
    
    public List<Integer> getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(List<Integer> coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.coordenada);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CoordenadasModel other = (CoordenadasModel) obj;
        return Objects.equals(this.coordenada, other.coordenada);
    }
}
