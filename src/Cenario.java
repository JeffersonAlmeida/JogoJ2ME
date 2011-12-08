
import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

/**
 *
 * @author Emerson Borges de Oliveira    90619
 * @author Lorena Lopes dos Santos       92145
 */
public class Cenario extends TiledLayer{
    private int[] mapa;
    public Cenario(int[] mapa) throws IOException{
        super(50, 50, Image.createImage("plataforma_labirinto_1.png"), 16, 16);
        this.mapa = mapa;
        inicia_mapa();
    }

    private void inicia_mapa() {
        int linha, coluna;
        for (int i = 0; i < 2500; i++) {
            coluna = i % 50;         
            linha = i / 50;             
            setCell(coluna, linha, mapa[i]);
        }
    }
}
