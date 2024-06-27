import java.util.HashMap;

interface Number {
    String convert(int num);
}

class CardinalNumber implements Number {
    private final String[] unidade = {"", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
    private final String[] dez_a_dezenove = {"dez", "onze", "doze", "treze", "catorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
    private final String[] dezenas = {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
    private final String[] centenas = {"", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"};

    public String convert(int num) {
        if (num < 0 || num > 999) {
            return "Fora do intervalo válido (1-999)";
        }
        if (num == 0) {
            return "zero";
        }
        if (num < 10) {
            return unidade[num];
        }
        if (num < 20) {
            return dez_a_dezenove[num - 10];
        }
        if (num < 100) {
            return dezenas[num / 10] + ((num % 10 != 0) ? " e " + unidade[num % 10] : "");
        }
        if (num < 1000) {
            return centenas[num / 100] + ((num % 100 != 0) ? " e " + convert(num % 100) : "");
        }
        return "";
    }
}

class OrdinalNumber implements Number {
    private final String[] unidade = {"primeiro", "segundo", "terceiro", "quarto", "quinto", "sexto", "sétimo", "oitavo", "nono"};
    private final String[] dez_a_dezenove = {"décimo", "décimo primeiro", "décimo segundo", "décimo terceiro", "décimo quarto", "décimo quinto", "décimo sexto", "décimo sétimo", "décimo oitavo", "décimo nono"};
    private final String[] dezenas = {"", "vigésimo", "trigésimo", "quadragésimo", "quinquagésimo", "sexagésimo", "septuagésimo", "octogésimo", "nonagésimo"};
    private final String[] centenas = {"", "centésimo", "ducentésimo", "trecentésimo", "quadrigentésimo", "quingentésimo", "sexcentésimo", "septingentésimo", "octingentésimo", "nongentésimo"};

    public String convert(int num) {
        if (num < 0 || num > 999) {
            return "Fora do intervalo válido (1-999)";
        }
        if (num == 0) {
            return "zero";
        }
        if (num < 10) {
            return unidade[num - 1];
        }
        if (num < 20) {
            return dez_a_dezenove[num - 10];
        }
        if (num < 100) {
            return dezenas[num / 10 - 1] + ((num % 10 != 0) ? " " + unidade[num % 10 - 1] : "");
        }
        if (num < 1000) {
            return centenas[num / 100] + ((num % 100 != 0) ? " " + convert(num % 100) : "");
        }
        return "";
    }
}

class RomanNumber implements Number {
    private final int[] values = {1000, 900, 500, 400,100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final String[] symbols = {"M", "CM", "D", "CD","C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String convert(int num) {
        if (num < 1 || num > 999) {
            return "Fora do intervalo válido (1-999)";
        }
        StringBuilder roman = new StringBuilder();
        int i = 0;
        while (num > 0) {
            if (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            } else {
                i++;
            }
        }
        return roman.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Number number = new CardinalNumber();
        System.out.println(number.convert(234));

        number = new OrdinalNumber();
        System.out.println(number.convert(56));

        number = new RomanNumber();
        System.out.println(number.convert(78));
    }
}
