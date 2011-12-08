
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author Emerson Borges de Oliveira    90619
 * @author Lorena Lopes dos Santos       92145
 */

public class RankingDAO {
    private RecordStore rs;

    public RankingDAO() {
        try {
            rs = RecordStore.openRecordStore("ranking", true);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    public void addRanking(String r){
        byte[] registro = r.getBytes();
        try {
            rs.addRecord(registro, 0, registro.length);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    public void removeRanking(int id){
        try {
            rs.deleteRecord(id);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    public String[] getRankings(){
        String[] rankings = null;
        int i=0;
        try {
            ComparatorRanking comparator = new ComparatorRanking();
            RecordEnumeration re = rs.enumerateRecords(null, comparator, false);
            if((rs.getNumRecords() > 0)){
                if(rs.getNumRecords() < 5)
                    rankings = new String[5];
                else
                    rankings = new String[rs.getNumRecords()];
            }
                
            while(re.hasNextElement()){
                rankings[i] = new String(rs.getRecord(re.nextRecordId()));
                i++;
            }

        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        }
        catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return rankings;
    }

    public void fechaRS() {
        try {
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
}
