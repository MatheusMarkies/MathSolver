package com.matheusmarkies.input;

import java.util.ArrayList;

public class InputMathExpression {

    static char[] alphabet = {
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'
    };
    static char[] numbers = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    static char[] separatorSymbols = {'{', '[', '(', ')', ']', '}'};
    static char[] mathSymbols = {'+', '-', '*', '/', '='};

    public static String addMultiplySymbol(String eq) {
        String newEq = "";
        String equation = eq.replace(" ", "");

        ArrayList<Integer> addSymbolIn = new ArrayList<>();

        boolean separatorSymbolLock = false;
        boolean alphabetSymbolLock = false;
        boolean numberSymbolLock = false;

        for (int i = 0; i < equation.length(); i++) {
            char s = equation.charAt(i);

            for (char separatorSymbol : separatorSymbols) {
                if (s == separatorSymbol) {
                    //System.out.println(s +" = "+ separatorSymbol);
                    if (s == '(' || s == '[' || s == '{')
                        if (alphabetSymbolLock || numberSymbolLock) {
                            addSymbolIn.add(i);
                            alphabetSymbolLock = false;
                            numberSymbolLock = false;
                        }

                    if (s == '}' || s == ']' || s == ')') {
                        separatorSymbolLock = true;
                    }else if(separatorSymbolLock)
                        separatorSymbolLock=false;
                    break;
                }
            }
            for (char chara : alphabet) {
                if (s == chara) {
                    //System.out.println(s +" = "+ chara);
                    if (numberSymbolLock || alphabetSymbolLock || separatorSymbolLock) {
                        addSymbolIn.add(i);
                        numberSymbolLock = false;
                        separatorSymbolLock = false;
                    }
                    alphabetSymbolLock = true;
                    break;
                }
            }
            for (char number : numbers) {
                if (s == number) {
                    //System.out.println(s +" = "+ number);
                    numberSymbolLock = true;
                    if (alphabetSymbolLock || separatorSymbolLock) {
                        addSymbolIn.add(i);
                        alphabetSymbolLock = false;
                        separatorSymbolLock = false;
                    }
                    break;
                }
            }
            for (char symbol : mathSymbols) {
                if (s == symbol) {
                    numberSymbolLock = false;
                    alphabetSymbolLock = false;
                    separatorSymbolLock = false;
                    break;
                }
            }
        }

        for (int i = 0; i < equation.length();) {
            int size = 0;
            boolean remove = false;
            for (int j = 0; j < addSymbolIn.size(); j++) {
                if (i == addSymbolIn.get(j)) {
                    newEq += "*";
                    size = j;
                    remove = true;
                    break;
                }
            }
            if (remove) {
                addSymbolIn.remove(size);
                remove = false;
            }else{
                newEq += equation.charAt(i);
                i++;
            }
        }
        System.out.println(newEq);
        return newEq;
    }

    public static void getEq(String eq) {
        /*
        y = 2+x
        0 = 2+x
        (x+y)y = 4 -> 4/y-y = x --> -xy + 4 = y^2
         */
        //Computer algebra system
        String equation = eq.replace(" ", "");
        equation = addMultiplySymbol(equation);
        ArrayList<String> components = new ArrayList<>();

        boolean symbol = false;
        boolean separator = false;
        ArrayList<Character> tempComponent = new ArrayList<>();

        ArrayList<String> openedSeparator = new ArrayList<>();

        for (int i = 0; i < equation.length(); i++) {
            char s = equation.charAt(i);

            for (char separatorSymbol : separatorSymbols) {
                if (s == separatorSymbol) {
                    if (s == '{' || s == '[' || s == '(') {
                        if (openedSeparator.size() > 0) {
                            String temp = openedSeparator.get(openedSeparator.size() - 1);
                            temp += " SEPARATOR ";
                            openedSeparator.set(openedSeparator.size() - 1, temp);
                        }
                        openedSeparator.add(new String());
                    } else {
                        components.add(openedSeparator.get(openedSeparator.size() - 1));
                        openedSeparator.remove(openedSeparator.size() - 1);
                    }
                    separator = true;
                    break;
                } else {
                    separator = false;
                    symbol = false;
                }
            }
            if (openedSeparator.size() == 0) {
                for (char mathSymbol : mathSymbols) {
                    if (s == mathSymbol) {
                        char[] componentChars = new char[tempComponent.size()];
                        int k = 0;
                        for (Character character : tempComponent) {
                            componentChars[k] = character;
                            k++;
                        }
                        components.add(new String(componentChars));
                        tempComponent.clear();
                        symbol = true;
                        break;
                    } else {
                        symbol = false;
                    }
                }
            }
            if (!symbol && !separator) {
                if (openedSeparator.size() > 0) {
                    String temp = openedSeparator.get(openedSeparator.size() - 1);
                    temp += s;
                    openedSeparator.set(openedSeparator.size() - 1, temp);
                } else {
                    tempComponent.add(s);
                }
            }
        }

        char[] componentChars = new char[tempComponent.size()];
        int k = 0;
        for (Character character : tempComponent) {
            componentChars[k] = character;
            k++;
        }
        components.add(new String(componentChars));

        for (int i = 0; i < components.size(); i++) {
            if (!"".equals(components.get(i))) {
                if (components.get(i).indexOf("SEPARATOR") == -1) {
                    System.out.println(components.get(i));
                } else {
                    String tm = components.get(i).replace("SEPARATOR", "component[" + (i - 1) + "]");
                    System.out.println(tm);
                }
            }

        }
    }
}
