package com.matheusmarkies.input;

public class Expression {

    //y^2 = (x+1)y + 3x
    public String add(String a, String b){
        String result = "";

        while (true){
            boolean added = false;
            for(int y =0; y<b.length();y++){
                for(int x =0; x<a.length();x++){
                    if(b.charAt(y) == a.charAt(x)){
                        added = true;
                    }
                }
            }
            if(!added)
            break;
        }

        return result;
    }

    public String subtract(String a, String b){
        String result = "";

        return result;
    }

    public String multiply(String a, String b){
        String result = "";

        return result;
    }

    public String divide(String a, String b){
        String result = "";

        return result;
    }
}
