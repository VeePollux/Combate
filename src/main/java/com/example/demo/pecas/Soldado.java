/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.pecas;
import com.example.demo.modelos.Pecas;
import com.example.demo.modelos.Posicao;
import com.example.demo.modelos.Tabuleiro;

/**
 *
 * @author aldri
 */
public class Soldado extends Pecas {
    private int nivel;
    public static int passos = 4;
    public static int quantidade = 3;
    
    public Soldado(String nome, String color, Posicao position, boolean escondida , boolean mobilidade, int nivel ) {
        this.nome = nome;
        this.color = color;
        this.position = position;
        this.escondida = escondida;
        this.mobilidade = mobilidade;
        this.nivel = nivel;
    }

    public Soldado() {
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public int getNivel() {
        return nivel;
    } 

    public static int getPassos() {
        return passos;
    }

    public static void setPassos(int passos) {
        Soldado.passos = passos;
    }
    @Override
        public void addPecas(Tabuleiro tabuleiro,String cor, boolean escondida){
        Posicao newPosition = new Posicao();
        newPosition = tabuleiro.geraPosPecaAleatTab(tabuleiro, cor);
        this.nome = "SO";
        this.color = cor;
        this.position = newPosition;
        this.escondida = escondida;
        this.mobilidade = true;
        tabuleiro.ListIniciais.add(this);
        tabuleiro.setPosicao(this, newPosition);}

    public void ataqueSoldado(Tabuleiro tabuleiro, Pecas adversario, Posicao posicao){
        if(adversario.getNome().equals("MA") || adversario.getNome().equals("CA")|| adversario.getNome().equals("BO")|| adversario.getNome().equals("SO")){
            tabuleiro.ListIniciais.remove(this);
            tabuleiro.setNullPosicao(this.position);
            if(adversario.getNome().equals("SO")){
                tabuleiro.ListIniciais.remove(adversario);
                tabuleiro.setNullPosicao(adversario.getPosition());} }
        else{
            tabuleiro.ListIniciais.remove(adversario);
            tabuleiro.moverPeca(this.position, posicao);}
}
}
