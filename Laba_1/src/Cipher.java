public class Cipher {
    private String in;
    private  int num;
    private boolean state;

    public Cipher(String in, int num, boolean state){
        this.in = in;
        this.num = num;
        this.state = state;
    }

    public String ciph(){
        String new_str = "";
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == ' ' || Character.isDigit(in.charAt(i)))
                new_str += in.charAt(i);
            else {
                new_str += proverka(in.charAt(i));
            }
        }
        return new_str;
    }

    private char proverka(char sym) {
        if (sym >= 'A' && sym <= 'Z') {
            num = num % 26;
            if (state) {
                if (((sym + num) <= 90 && (sym + num) >= 65))
                    sym += num;
                else
                    sym = (char) (sym + num - 26);
            } else {
                if (((sym - num) <= 90 && (sym - num) >= 65))
                    sym -= num;
                else
                    sym = (char)(sym - num + 26);
            }
        } else if (sym >= 'a' && sym <= 'z') {
            num = num % 26;
            if (state) {
                if ((sym + num) <= 122 && (sym + num) >= 97)
                    sym += num;
                else
                    sym = (char)(sym + num - 26);
            } else {
                if ((sym - num) <= 122 && (sym - num) >= 97)
                    sym -= num;
                else
                    sym = (char)(sym - num + 26);
            }
        } else if (sym >= 'А' && sym <= 'Я') {
            num = num % 32;
            if (state) {
                if ((sym + num) <= 1071 && (sym + num) >= 1040)
                    sym += num;
                else
                    sym = (char)(sym + num - 32);
            } else {
                if ((sym - num) <= 1071 && (sym - num) >= 1040)
                    sym -= num;
                else
                    sym = (char)(sym - num + 32);
            }
        } else if (sym >= 'а' && sym <= 'я') {
            num = num % 32;
            if (state) {
                if (((sym + num) <= 1103) && (sym + num) >= 1072)
                    sym += num;
                else
                    sym = (char)(sym + num - 32);
            } else {
                if (((sym - num) <= 1103) && (sym - num) >= 1072)
                    sym -= num;
                else
                    sym = (char)(sym - num + 32);
            }
        }
        return sym;
    }

}
