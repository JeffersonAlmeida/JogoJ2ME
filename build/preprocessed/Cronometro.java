/**
 *
 * @author Emerson Borges de Oliveira    90619
 * @author Lorena Lopes dos Santos       92145
 */
public class Cronometro {
    private int tempo;
    private int minuto;
    private int segundo;
    private int estado;
    
    public Cronometro(int tempo){
        this.tempo = tempo;
        minuto = tempo;
        segundo = 0;
        estado = PARADO;
    }

    public void decrementa(){
        if(acabou())
            estado = PARADO;
        else if(estado == EXECUTANDO){
            if(segundo == 0){
                if(minuto > 0){
                    minuto--;
                    segundo = 59;
                }
            }
            else
                segundo--;
            }        
        
    }

    public void executa(){
        this.estado = EXECUTANDO;
    }

    public void para(){
        this.estado = PARADO;
    }
    public void setEstado(int estado){
        this.estado = estado;
    }

    public int getEstado(){
        return this.estado;
    }

    public boolean acabou(){
        if((minuto == 0) && (segundo == 0))
            return true;
        return false;
    }

    public String getTempoRestante(){
        return new String(minuto+":"+segundo);
    }

    public String getTempoDecorrido(){
        String dec;
        int min,seg;
        seg = segundo;
        if(segundo == 0)
            seg = 60;
        min = tempo-minuto-1;
        if(min < 0)
            min = 0;
        dec = (min) + ":" + (60-seg);
        return dec;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public static final int
            EXECUTANDO = 0,
            PARADO = 1;
}
