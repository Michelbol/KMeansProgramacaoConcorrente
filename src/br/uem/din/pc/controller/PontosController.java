/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.pc.controller;

import br.uem.din.pc.model.PontosModel;

/**
 *
 * @author Luiz Flávio
 */
public class PontosController {  
    public static void imprimirPontos(PontosModel pontos){
        if(pontos.getPontos().isEmpty()){
            System.out.println("Nenhuma informação a ser impressa...");
            return;
        }
        
        for(int i = 0; i < pontos.getPontos().size(); i++){
            System.out.println("Ponto " + (i+1));
            System.out.println(pontos.getPontos().get(i).getCoordenada().toString());
        }
    }
    
    public static void calculaKMeansSequencial(PontosModel dadosBase, PontosModel dadosCentroit){
        System.out.println("sequencial");
    }
    
    public static void calculaKMeansParalelo(PontosModel dadosBase, PontosModel dadosCentroit){
        System.out.println("paralelo");
    }
}
