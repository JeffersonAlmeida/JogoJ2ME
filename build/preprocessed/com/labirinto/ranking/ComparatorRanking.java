package com.labirinto.ranking;

/**
 *
 * @author Jefferson Rodrigues De Almeida Matricula: 100 854
 */
import javax.microedition.rms.RecordComparator;

public class ComparatorRanking implements RecordComparator {

    public int compare(byte[] rec1, byte[] rec2) {
        String s1 = new String(rec1);
        String s2 = new String(rec2);       

        System.out.println("String 1 = "+s1);
        System.out.println("String 2 = "+s2);
        int comparation = s2.compareTo(s1);
        if (comparation == 0) {
            return RecordComparator.EQUIVALENT;
        } else {
            if (comparation > 0) {
                return RecordComparator.PRECEDES;
            } else {
                return RecordComparator.FOLLOWS;
            }
        }
    }
}
