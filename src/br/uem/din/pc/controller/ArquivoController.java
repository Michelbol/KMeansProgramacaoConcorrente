/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.pc.controller;

import br.uem.din.pc.model.CoordenadasModel;
import br.uem.din.pc.model.PontosModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luiz Flávio
 */
public class ArquivoController {
    //métodos responsáveis por selecionar o arquivo
    public static String selecionarArquivo(){
        JFileChooser file = new JFileChooser(); 
        
        file.setDialogTitle("Selecione o arquivo desejado");
        
        //adicionando filtro para arquivos .txt
        file.setFileFilter(new FileNameExtensionFilter("Dados (.data)", "data"));
        file.showDialog(file, "Abrir");
        
        
        try{
            return file.getSelectedFile().toString().trim();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado ou arquivo inválido.\n\n                  >>> Programa abortado <<<", "Falha ao abrir arquivo", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return "Nenhum arquivo foi lido...";
        }  
    }
    
    public static List<CoordenadasModel> iniciarlizarDados(int tipoArq, PontosModel pontos){
        String caminhoArquivo;
        List<CoordenadasModel> pontosTemp = new ArrayList<>();
        CoordenadasModel coordAux = new CoordenadasModel();
        
        //obtendo caminho do arquivo e validando informações
        if(tipoArq == 1){//arquivo base
            caminhoArquivo = selecionarArquivo();
            
            //validando nomenclatura do arquivo lido
            if(!caminhoArquivo.toLowerCase().contains("base")){
                System.out.println("O arquivo lido não contém dados de base...");
                return new ArrayList<>();
            }
            
            //se o centroit já foi lido, então a base deve ter a mesma quantidade de coordenadas por ponto
            if(!pontos.getPontos().isEmpty() && !caminhoArquivo.contains(String.valueOf(pontos.getPontos().get(0).getCoordenada().size()))){
                System.out.println("O arquivo lido não contém a quantidade de coordenadas iguais ao centroit lido...");
                return new ArrayList<>();
            }
        }else{//arquivo centroid
            caminhoArquivo = selecionarArquivo();
            
            //validando nomenclatura do arquivo lido
            if(!caminhoArquivo.toLowerCase().contains("centroid")){
                System.out.println("O arquivo lido não contém dados de centroid...");
                return new ArrayList<>();
            }
            
            //se a base já foi lida, então o centroit deve ter a mesma quantidade de coordenadas por ponto
            if(!pontos.getPontos().isEmpty() && !caminhoArquivo.contains(String.valueOf(pontos.getPontos().get(0).getCoordenada().size()))){
                System.out.println("O arquivo lido não contém a quantidade de coordenadas iguais ao da base lida...");
                return new ArrayList<>();
            }
        }
        
        //apresentando diretório e arquivo selecionado
        System.out.println("Arquivo lido -> ".concat(caminhoArquivo));
        
        //realizando leitura do arquivo
        try (
                BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String linha = br.readLine();
            
            while (linha != null) {
                //obtendo dados da primeira linha, quebrando por vígurla e salvando em vetor temporário
                String[] valoresLinha;
                valoresLinha = linha.split(",");
                
                //adicionando cada valor inteiro como uma 'Coordenada' do ponto que está sendo lido
                for (String vlrsDaLinha : valoresLinha) {                
                    Integer vlrInt = Integer.parseInt(vlrsDaLinha);    
                    coordAux.getCoordenada().add(vlrInt);
                }
                
                //adicionando coordenadas/ponto ao objeto a ser retornando e lendo outra linha do arquivo
                pontosTemp.add(coordAux);
                coordAux = new CoordenadasModel(); 
                linha = br.readLine();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao ler arquivo!\nPrograma Abortado\nErro: ".concat(e.getMessage().trim()), "Falha ao ler arquivo", JOptionPane.ERROR_MESSAGE);
            System.gc();
            System.exit(0);
        }
        return pontosTemp;  
    }
}
