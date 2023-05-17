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
public class Lago extends Pecas{
    private static int quantidade = 1;
    public Lago(String nome, String color, Posicao position, boolean escondida, boolean mobilidade) {
        this.nome = nome;
        this.color = color;
        this.position = position;
        this.escondida = escondida;
        this.mobilidade = mobilidade;

    }

    public Lago() {
    }

    public static int getQuantidade() {
        return quantidade;
    }
    
    @Override
    public void addPecas(Tabuleiro tabuleiro,String cor,boolean escondida){
        Posicao newPosition = new Posicao();
        Random geradorX = new Random();
        newPosition.setX(geradorX.nextInt(5));
        newPosition.setY(2);
        this.nome = "LA";
        this.color = "Indefinido";
        this.position = newPosition;
        this.escondida = escondida;
        this.mobilidade = false;
        tabuleiro.setPosicao(this, newPosition);}
}
