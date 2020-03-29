package com.example.entities;

import lombok.Data;

@Data
public class Matrix {

    private String strValue;

    private int[][] intValue;

    private int[][] generateIntValue(){
        strValue = makeStringMatrixPretty(strValue);
        intValue = convertToInt(strValue);
        return intValue;
    }

    private String makeStringMatrixPretty(String matrix) {

        String prettyMatrix = matrix.trim();
        prettyMatrix = prettyMatrix.replaceAll("\\p{Blank}{2,}", " ");
        prettyMatrix = prettyMatrix.replaceAll("[\r\n]{2,}", "\r\n");
        return prettyMatrix;
    }

    private int[][] convertToInt(String matrix) {

        String[] lines = matrix.split("\r\n");
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

    public void setStrValue(String strValue){
        this.strValue = strValue;
        this.intValue = null;
    }

    public void setIntValue(int[][] intValue) {
        this.intValue = intValue;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < intValue.length; i++) {
            for (int j = 0; j < intValue[i].length; j++) {
                builder.append(intValue[i][j]);
                if(j < intValue[i].length - 1) builder.append(" ");
            }
            if(i < intValue.length - 1) builder.append("\r\n");
        }
        this.strValue = builder.toString();
    }

}
