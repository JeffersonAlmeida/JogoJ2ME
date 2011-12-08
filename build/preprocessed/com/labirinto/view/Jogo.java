package com.labirinto.view;

/**
 *
 *@author Jefferson Rodrigues De Almeida Matricula: 100 854
 */

import com.labirinto.persistence.RankingDAO;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Jogo extends MIDlet {

    private Display tela;
    private LabirintoCanvas canvas;
    List menu, menuLabirinto;
    int labirinto = 0;
    Form fRank;
    TextField txtRank1,txtRank2,txtRank3,txtRank4,txtRank5;    
    private TextBox tbInstrucoes;
    private Alert alerta;
    private Image imgAlerta;
    
    public Jogo() throws IOException {
        tela = Display.getDisplay(this);
        initComponentes();
    }

    private void initComponentes() {
        try {
            imgAlerta = Image.createImage("bomber_labirinto_logo_2.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        alerta = new Alert("Labirinto", "   Versão x300 \n   JeffersonAlmeida",imgAlerta, AlertType.INFO);
        alerta.setTimeout(5000);
        txtRank1 = new TextField("1º", null, 5,TextField.UNEDITABLE);
        txtRank2 = new TextField("2º", null, 5,TextField.UNEDITABLE);
        txtRank3 = new TextField("3º", null, 5,TextField.UNEDITABLE);
        txtRank4 = new TextField("4º", null, 5,TextField.UNEDITABLE);
        txtRank5 = new TextField("5º", null, 5,TextField.UNEDITABLE);
        fRank = new Form("Ranking");
        fRank.append(txtRank1);
        fRank.append(txtRank2);
        fRank.append(txtRank3);
        fRank.append(txtRank4);
        fRank.append(txtRank5);
        fRank.addCommand(new Command("OK", Command.OK, 0));
        fRank.setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                if(c.getCommandType() == Command.OK){
                    tela.setCurrent(menu);
                }
            }
        });
        menu = new List("Menu", List.IMPLICIT);
        menu.append("Iniciar Jogo", null);
        menu.append("Ranking", null);
        menu.append("Instruções", null);
        menu.append("Sair", null);
        menu.addCommand(new Command("OK", Command.OK, 0));
        menu.addCommand(new Command("Encerrar", Command.EXIT, 1));

        menu.setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                int tipo = c.getCommandType();
                int idMenu;

                if (tipo == Command.EXIT) {
                    notifyDestroyed();
                } else if (tipo == Command.OK) {
                    idMenu = menu.getSelectedIndex();
                    if (idMenu == 0) {
                        tela.setCurrent(menuLabirinto);
                    } else if (idMenu == 1) {
                        limpaRankings();
                        setRankings();
                        tela.setCurrent(fRank);
                    } else if (idMenu == 2) {
                        tela.setCurrent(tbInstrucoes);
                    } else if (idMenu == 3) {
                        destroyApp(true);
                    }
                }
            }
        });
        menuLabirinto = new List("Escolha um cenario!", List.IMPLICIT);
        menuLabirinto.append("Fácil", null);
        menuLabirinto.append("Médio", null);
        menuLabirinto.append("Difícil", null);
        menuLabirinto.addCommand(new Command("OK", Command.OK, 0));
        menuLabirinto.addCommand(new Command("Voltar", Command.BACK, 0));
        menuLabirinto.setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                int tipo = c.getCommandType();
                int idMenu;
                if (tipo == Command.BACK) {
                    tela.setCurrent(menu);
                } else if (tipo == Command.OK) {
                    idMenu = menuLabirinto.getSelectedIndex();
                    if (idMenu == 0) {
                        canvas = new LabirintoCanvas(1);
                        tela.setCurrent(canvas);
                        canvas.inicia();
                    } else if (idMenu == 1) {
                        canvas = new LabirintoCanvas(3);
                        tela.setCurrent(canvas);
                        canvas.inicia();
                    } else if (idMenu == 2) {
                        canvas = new LabirintoCanvas(2);
                        tela.setCurrent(canvas);
                        canvas.inicia();
                    }
                    addComandoCanvas();
                }
            }
        });
        String instrucoes = "Labirinto é um jogo que tem o objetivo de encontrar a saída de um labirinto em menos tempo. Para andar para a esquerda use a tecla direcional esquerda, para andar para a direita use a tecla direcional direita, para subir use a tecla direcional cima e para descer use a tecla direcional baixo. É um jogo simples e divertido!";
        tbInstrucoes = new TextBox("Instruções", instrucoes,1000,0);
        tbInstrucoes.setConstraints(TextField.UNEDITABLE);
        tbInstrucoes.addCommand(new Command("Voltar",Command.BACK,0));
        tbInstrucoes.setCommandListener (new CommandListener() {

            public void commandAction(Command c, Displayable d) {
               int tipo = c.getCommandType();

               if(tipo == Command.BACK){
                   tela.setCurrent(menu);
               }
            }
        });

    }

    private void addComandoCanvas() {
        if (canvas != null) {
            canvas.addCommand(new Command("Sair", Command.CANCEL, 0));
            canvas.setCommandListener(new CommandListener() {

                public void commandAction(Command c, Displayable d) {
                    if (c.getCommandType() == Command.CANCEL) {
                        canvas.getRankingDAO().fechaRS();
                        pauseApp();
                        tela.setCurrent(menu);
                    }
                }
            });
        }
    }

    private void setRankings(){
            String[] ranks = new RankingDAO().getRankings();
            if(ranks != null){
                txtRank1.setString(ranks[0]);
                txtRank2.setString(ranks[1]);
                txtRank3.setString(ranks[2]);
                txtRank4.setString(ranks[3]);
                txtRank5.setString(ranks[4]);
            }
    }

    private void limpaRankings(){
        txtRank1.setString("");
        txtRank2.setString("");
        txtRank3.setString("");
        txtRank4.setString("");
        txtRank5.setString("");
    }

    public void startApp() {
        tela.setCurrent(alerta, menu);
    }

    public void pauseApp() {
        canvas.pausa();
    }

    public void destroyApp(boolean unconditional) {
        if(canvas != null)
            canvas.pausa();
        notifyDestroyed();
    }
}
