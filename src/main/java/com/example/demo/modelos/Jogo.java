/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modelos;
import com.example.demo.pecas.Lago;
import com.example.demo.pecas.Soldado;
import com.example.demo.pecas.Marechal;
import com.example.demo.pecas.Bomba;
import com.example.demo.pecas.Caboarmeiro;
import com.example.demo.pecas.Espiao;
import com.example.demo.pecas.Bandeira;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.Random;


public class Jogo {

    Tabuleiro tabuleiro;
    Tabuleiro inicialTabuleiro;
    private boolean jogoAleatorio;

    private int quantDica = 0;
    private boolean win;
    private boolean gameover;

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }


    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getquantDica() {
        return quantDica;
    }

    public void setquantDica(int quantDica) {
        this.quantDica = quantDica;
    }

    public boolean BombaCol(int Index) { //Função pra ver se tem bomba
        System.out.println(Index);
        tabuleiro.imprimeTabuleiro();
        for (int i = 0; i < tabuleiro.ListIniciais.size(); i++) {
            if (tabuleiro.ListIniciais.get(i).getNome().equals("BO") && tabuleiro.ListIniciais.get(i).getColor().equals("Azul"))
                for (int j = 0; j < 5; j++) {
                    if (tabuleiro.ListIniciais.get(i).getPosition().equals(new Posicao(Index, j)))
                        return true;
                }
        }
        return false;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public boolean isJogoAleatorio() {
        return jogoAleatorio;
    }

    public void setJogoAleatorio(boolean jogoAleatorio) {
        this.jogoAleatorio = jogoAleatorio;
    }

    public void JogoAleatorio() {
        CriaTabuleiro();
        addLagoAleat();
        addPecasComp();
        addPecasPlayer();
    }

    public void CriaTabuleiro() {
        Tabuleiro tabuleiro = new Tabuleiro();
        setTabuleiro(tabuleiro);
    }

    public void PlayGame() {
        int x = 0;
        Tabuleiro tabuleiro = new Tabuleiro();
        setTabuleiro(tabuleiro);
        this.jogoAleatorio = true;
        if (isJogoAleatorio())
            JogoAleatorio();
        tabuleiro.imprimeTabuleiro();
    }

    public void addPecasComp() {
        colocaPecas("Azul", true);
    }

    public void addPecasPlayer() {
        colocaPecas("Vermelho", false);
    }

    public void addPecasPlayerManual(GridPane GPTabuleiro, int linIndex, int colIndex, String nome) throws
            FileNotFoundException { //Adiciona cada peça no tabuleiro de forma manual
        int MA = 0, CA = 0, BO = 0, BA = 0, ES = 0, SO = 0;
        if (this.tabuleiro.isNullPosicao(new Posicao(linIndex, colIndex))) {
            for (int i = 0; i < this.tabuleiro.ListIniciais.size(); i++) {
                if (this.tabuleiro.ListIniciais.get(i).getColor().equals("Vermelho")) {
                    if (this.tabuleiro.ListIniciais.get(i).getNome().equals("MA"))
                        MA++;
                    if (this.tabuleiro.ListIniciais.get(i).getNome().equals("BO"))
                        BO++;
                    if (this.tabuleiro.ListIniciais.get(i).getNome().equals("BA"))
                        BA++;
                    if (this.tabuleiro.ListIniciais.get(i).getNome().equals("ES"))
                        ES++;
                    if (this.tabuleiro.ListIniciais.get(i).getNome().equals("SO"))
                        SO++;
                    if (this.tabuleiro.ListIniciais.get(i).getNome().equals("CA"))
                        CA++;
                }
            }
            if (nome.equals("BA"))
                if (BA < Bandeira.getQuantidade()) {
                    Pecas peca = new Bandeira();
                    peca.nome = "BA";
                    addPecasIndividual(peca, "Vermelho", false, false, GPTabuleiro, linIndex, colIndex);
                }
            if (nome.equals("BO"))
                if (BO < Bomba.getQuantidade()) {
                    Pecas peca = new Bomba();
                    peca.nome = "BO";
                    addPecasIndividual(peca, "Vermelho", false, false, GPTabuleiro, linIndex, colIndex);
                }
            if (nome.equals("ES"))
                if (ES < Espiao.getQuantidade()) {
                    Pecas peca = new Espiao();
                    peca.nome = "ES";
                    addPecasIndividual(peca, "Vermelho", false, true, GPTabuleiro, linIndex, colIndex);
                }
            if (nome.equals("CA"))
                if (CA < Caboarmeiro.getQuantidade()) {
                    Pecas peca = new Caboarmeiro();
                    peca.nome = "CA";
                    addPecasIndividual(peca, "Vermelho", false, true, GPTabuleiro, linIndex, colIndex);
                }
            if (nome.equals("MA"))
                if (MA < Marechal.getQuantidade()) {
                    Pecas peca = new Marechal();
                    peca.nome = "MA";
                    addPecasIndividual(peca, "Vermelho", false, true, GPTabuleiro, linIndex, colIndex);
                }
            if (nome.equals("SO"))
                if (SO < Soldado.getQuantidade()) {
                    Pecas peca = new Soldado();
                    peca.nome = "SO";
                    addPecasIndividual(peca, "Vermelho", false, true, GPTabuleiro, linIndex, colIndex);
                }
        }
        tabuleiro.imprimeTabuleiro();
    }

    public void addPecasIndividual(Pecas peca, String cor, boolean escondida, boolean mobilidade, GridPane GPTabuleiro, int linIndex, int colIndex) throws
            FileNotFoundException {
        String nome = peca.getNome();
        peca.color = "Vermelho";
        peca.escondida = false;
        peca.position = new Posicao(linIndex, colIndex);
        peca.mobilidade = false;
        tabuleiro.ListIniciais.add(peca);
        tabuleiro.setPosicao(peca, peca.getPosition());
        tabuleiro.addImagem(GPTabuleiro, linIndex, colIndex, nome, "Vermelho", false);
    }

    public void addLagoAleat() {
        for (int i = 0; i < Lago.getQuantidade(); i++) {
            Pecas lago = new Lago();
            lago.addPecas(tabuleiro, "Indefinido", false);
        }
    }

    public void colocaPecas(String cor, boolean escondida) { //Coloca todas peças no tabuleiro
        for (int i = 0; i < Bandeira.getQuantidade(); i++) {
            Pecas bandeira = new Bandeira();
            bandeira.addPecas(tabuleiro, cor, escondida);
        }
        for (int i = 0; i < Bomba.getQuantidade(); i++) {
            Pecas bomba = new Bomba();
            bomba.addPecas(tabuleiro, cor, escondida);
        }
        for (int i = 0; i < Espiao.getQuantidade(); i++) {
            Pecas espiao = new Espiao();
            espiao.addPecas(tabuleiro, cor, escondida);
        }
        for (int i = 0; i < Soldado.getQuantidade(); i++) {
            Pecas soldado = new Soldado();
            soldado.addPecas(tabuleiro, cor, escondida);
        }
        for (int i = 0; i < Marechal.getQuantidade(); i++) {
            Pecas marechal = new Marechal();
            marechal.addPecas(tabuleiro, cor, escondida);
        }
        for (int i = 0; i < Caboarmeiro.getQuantidade(); i++) {
            Pecas caboarmeiro = new Caboarmeiro();
            caboarmeiro.addPecas(tabuleiro, cor, escondida);
        }
    }

    public void ataqueCom() { //Ataque random de peças e posições do computador
        int X, X1, randomPassos = 1;
        boolean flag = true, flagPodeAtacar = true;
        Random gerador = new Random();
        Pecas peca;
        Posicao position;

        do {
            flagPodeAtacar = true; //Verifica se deu algum passo, caso tenha dado não pode atacar
            peca = randomPecasComp();
            X = gerador.nextInt(2);
            X1 = gerador.nextInt(2);
            if (peca instanceof Soldado)
                randomPassos = gerador.nextInt(Soldado.passos) + 1;
            if (peca instanceof Caboarmeiro)
                randomPassos = gerador.nextInt(Caboarmeiro.passos) + 1;
            if (peca instanceof Espiao)
                randomPassos = gerador.nextInt(Espiao.passos) + 1;
            if (peca instanceof Marechal)
                randomPassos = gerador.nextInt(Marechal.passos) + 1;

            do {
                if (X == 0) {
                    if (X1 == 0)
                        position = new Posicao(peca.getPosition().getX() - 1, peca.getPosition().getY());
                    else
                        position = new Posicao(peca.getPosition().getX() + 1, peca.getPosition().getY());
                } else {
                    if (X1 == 0)
                        position = new Posicao(peca.getPosition().getX(), peca.getPosition().getY() - 1);
                    else
                        position = new Posicao(peca.getPosition().getX(), peca.getPosition().getY() + 1);
                }
                randomPassos = randomPassos - 1;
                if ((position.getX() < 5 && position.getX() >= 0) && (position.getY() < 5 && position.getY() >= 0)) {
                    if (tabuleiro.isNullPosicao(position))
                        flagPodeAtacar = false;
                } else
                    randomPassos = 0;
            }
            while (randomPassos > 0);

            if ((position.getX() < 5 && position.getX() >= 0) && (position.getY() < 5 && position.getY() >= 0)) {
                if (this.tabuleiro.isNullPosicao(position)) {
                    flag = false;
                } else {
                    if (this.tabuleiro.getPecaPosicao(position).getColor().equals("Azul") && this.tabuleiro.getPecaPosicao(position).getNome().equals("LA")) {
                        flag = true;
                    } else {
                        if (this.tabuleiro.getPecaPosicao(position).getColor().equals("Vermelho")) {
                            flag = false;
                        }
                    }
                }
            }
        } while (flag);

        if (!flagPodeAtacar)
            this.tabuleiro.moverPeca(peca.getPosition(), position);
        else {
            if (peca instanceof Soldado)
                ((Soldado) peca).ataqueSoldado(tabuleiro, this.tabuleiro.getPecaPosicao(position), position);
            if (peca instanceof Caboarmeiro)
                ((Caboarmeiro) peca).ataqueCaboarmeiro(tabuleiro, this.tabuleiro.getPecaPosicao(position), position);
            if (peca instanceof Espiao)
                ((Espiao) peca).ataqueEspiao(tabuleiro, this.tabuleiro.getPecaPosicao(position), position);
            if (peca instanceof Marechal)
                ((Marechal) peca).ataqueMarechal(tabuleiro, this.tabuleiro.getPecaPosicao(position), position);
        }
    }

    public Pecas randomPecasComp() {
        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(this.tabuleiro.ListIniciais.size());
        }
        while (this.tabuleiro.ListIniciais.get(randomIndex).getColor().equals("Vermelho") || !(this.tabuleiro.ListIniciais.get(randomIndex).isMobilidade()));
        return this.tabuleiro.ListIniciais.get(randomIndex);
    }

    public boolean verificaPecasJogaveis(String cor) {
        for (int i = 0; i < this.tabuleiro.ListIniciais.size(); i++) {
            if (!this.tabuleiro.ListIniciais.get(i).getNome().equals("BO") || !this.tabuleiro.ListIniciais.get(i).getNome().equals("BA") && this.tabuleiro.ListIniciais.get(i).getColor().equals(cor))
                return true;
        }
        return false;
    }

    public void gameoverPlayer() {
        boolean flag = true;
        for (int i = 0; i < this.tabuleiro.ListIniciais.size(); i++) {
            if (this.tabuleiro.ListIniciais.get(i) instanceof Bandeira && this.tabuleiro.ListIniciais.get(i).getColor().equals("Vermelho"))
                flag = false;
        }
        if (verificaPecasJogaveis("Vermelho"))
            flag = false;
        setGameover(flag);
    }

    public void winPlayer() {
        boolean flag = true;
        for (int i = 0; i < this.tabuleiro.ListIniciais.size(); i++) {
            if (this.tabuleiro.ListIniciais.get(i) instanceof Bandeira && this.tabuleiro.ListIniciais.get(i).getColor().equals("Azul"))
                flag = false;
        }
        if (verificaPecasJogaveis("Azul"))
            flag = false;
        setWin(flag);
    }

    public boolean JogadaPlayer(int linOrig, int colOrig, int linDest, int colDest) { //Valida os passos do player;
        int quandPassos = 1;

        if (tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig)) instanceof Soldado)
            quandPassos = Soldado.getPassos();
        if (tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig)) instanceof Caboarmeiro)
            quandPassos = Caboarmeiro.getPassos();
        if (tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig)) instanceof Espiao)
            quandPassos = Espiao.getPassos();
        if (tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig)) instanceof Marechal)
            quandPassos = Marechal.getPassos();
        if (!((linOrig == linDest && colDest == colOrig) || (linOrig != linDest && colDest != colOrig))) {
            if (linOrig == linDest) {
                if (colDest > colOrig){
                    if ((colDest - colOrig) <= quandPassos)
                        return true;}
                else {
                    if ((colOrig - colDest) <= quandPassos)
                        return true;}
                    }
            else {
                if (linDest > linOrig) {
                    if ((linDest - linOrig) <= quandPassos)
                        return true;
                } else if ((linOrig - linDest) <= quandPassos) {
                        return true;
                        }
                    }
                }
        return false;
    }

    public boolean PecaSelecionadaPlayer(int colOrig, int linOrig) {
        if (!tabuleiro.isNullPosicao(new Posicao(linOrig, colOrig))){
            if (tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig)).getColor().equals("Vermelho") && tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig)).isMobilidade()){
                return true;
            }}

        return false;
    }

    public void AtaquePlayer(int colOrig, int linOrig, int colDest, int linDest) { ///Descobre a peça selecionada para fazer o ataque
        Pecas peca;                                                                 //Ou apenas mover
        peca = tabuleiro.getPecaPosicao(new Posicao(linOrig, colOrig));
        System.out.println(peca.getNome());
        if (!tabuleiro.isNullPosicao(new Posicao(linDest, colDest))) {
            System.out.println(peca.getNome());
            if (peca instanceof Soldado)
                ((Soldado) peca).ataqueSoldado(this.tabuleiro,tabuleiro.getPecaPosicao(new Posicao(linDest,colDest)),(new Posicao(linDest,colDest)));
            if (peca instanceof Caboarmeiro)
                ((Caboarmeiro) peca).ataqueCaboarmeiro(this.tabuleiro,tabuleiro.getPecaPosicao(new Posicao(linDest,colDest)),(new Posicao(linDest,colDest)));
            if (peca instanceof Espiao)
                ((Espiao) peca).ataqueEspiao(this.tabuleiro,tabuleiro.getPecaPosicao(new Posicao(linDest,colDest)),(new Posicao(linDest,colDest)));
            if (peca instanceof Marechal)
                ((Marechal) peca).ataqueMarechal(this.tabuleiro,tabuleiro.getPecaPosicao(new Posicao(linDest,colDest)),(new Posicao(linDest,colDest)));
        }
        else{
            System.out.println("TESTE");
            tabuleiro.moverPeca(peca.getPosition(), new Posicao(linDest, colDest));}
    }
}
