package com.example.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Matrix {

    private String value;

    @Setter(value = AccessLevel.NONE)
    private int[][] intValue;

    private int[][] generateIntValue(){
        value = makeStringMatrixPretty(value);
        intValue = convertToInt(value);
        return intValue;
    }

    private String makeStringMatrixPretty(String matrix) {

        String prettyMatrix = matrix.trim();
        prettyMatrix = prettyMatrix.replaceAll("\\p{Blank}{2,}", " ");
        prettyMatrix = prettyMatrix.replaceAll("[\r\n]{2,}", "\n");
        return prettyMatrix;
    }

    private int[][] convertToInt(String matrix) {

        String[] lines = matrix.split("[\n]");
        String[][] strRes = new String[lines.length][];
        int[][] res = new int[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            strRes[i] = lines[i].split("\\p{Blank}");
            res[i] = new int[strRes[i].length];
            for (int j = 0; j < strRes[i].length; j++)
                res[i][j] = Integer.parseInt(strRes[i][j]);
        }
        return res;
    }

    public int[][] getIntValue(){
        return intValue == null ? generateIntValue() : intValue;
    }

    public void setValue(String value){
        this.value = value;
        this.intValue = null;
    }

}
