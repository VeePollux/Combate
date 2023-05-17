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
public class Marechal extends Pecas{
    public static int quantidade = 1;
    public static int passos = 1;
    private int nivel = 10;
    
    public Marechal(String nome, String color, Posicao position, boolean escondida , boolean mobilidade, int nivel) {
        this.nome = nome;
        this.color = color;
        this.position = position;
        this.escondida = escondida;
        this.mobilidade = mobilidade;
        this.nivel = nivel;
    }

    public Marechal() {
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
        Marechal.passos = passos;
    }

        @Override
        public void addPecas(Tabuleiro tabuleiro,String cor,boolean escondida){
        Posicao newPosition = new Posicao();
        newPosition = tabuleiro.geraPosPecaAleatTab(tabuleiro, cor);
        this.nome = "MA";
        this.color = cor;
        this.position = newPosition;
        this.escondida = escondida;
        this.mobilidade = true;
        tabuleiro.ListIniciais.add(this);
        tabuleiro.setPosicao(this, newPosition);}
        
        public void ataqueMarechal(Tabuleiro tabuleiro, Pecas adversario, Posicao posicao){
        if(adversario.getNome().equals("MA") || adversario.getNome().equals("BO")){
            tabuleiro.ListIniciais.remove(this);
            tabuleiro.setNullPosicao(this.position);
            if(adversario.getNome().equals("MA")){
                tabuleiro.ListIniciais.remove(adversario);
                tabuleiro.setNullPosicao(adversario.getPosition());} }
        else{
            tabuleiro.ListIniciais.remove(adversario);
            tabuleiro.moverPeca(this.position, posicao);}
}
}

