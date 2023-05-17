/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.pecas;
import com.example.demo.modelos.Pecas;
import com.example.demo.modelos.Posicao;
import com.example.demo.modelos.Tabuleiro;
import java.util.Random;
/**
 *
 * @author aldri
 */
public class Espiao extends Pecas{
    private int nivel = 1;
    public static int passos = 1;
    private static int quantidade = 1;
    
    public Espiao(String nome, String color, Posicao position, boolean escondida , boolean mobilidade, int nivel) {
        this.nome = nome;
        this.color = color;
        this.position = position;
        this.escondida = escondida;
        this.mobilidade = mobilidade;
        this.nivel = nivel;
    }

    public Espiao() {
    }

    public static int getPassos() {
        return passos;
    }

    public static void setPassos(int passos) {
        Espiao.passos = passos;
    }

    public int getNivel() {
        return nivel;
    }

    public static int getQuantidade() {
        return quantidade;
    }
    
    @Override
    public void addPecas(Tabuleiro tabuleiro,String cor, boolean escondida){
        Posicao newPosition = new Posicao();
        newPosition = tabuleiro.geraPosPecaAleatTab(tabuleiro, cor);
        this.nome = "ES";
        this.color = cor;
        this.position = newPosition;
        this.escondida = escondida;
        this.mobilidade = true;
        tabuleiro.ListIniciais.add(this);
        tabuleiro.setPosicao(this, newPosition);}



    public void ataqueEspiao(Tabuleiro tabuleiro, Pecas adversario, Posicao posicao){
        if(adversario.getNome().equals("ES") || adversario.getNome().equals("CA")|| adversario.getNome().equals("BO")|| adversario.getNome().equals("SO")){
            tabuleiro.ListIniciais.remove(this);
            tabuleiro.setNullPosicao(this.position);
            if(adversario.getNome().equals("ES")){
                tabuleiro.ListIniciais.remove(adversario);
            tabuleiro.setNullPosicao(adversario.getPosition());} }
        else{
            tabuleiro.ListIniciais.remove(adversario);
            tabuleiro.moverPeca(this.position, posicao);}
}
}