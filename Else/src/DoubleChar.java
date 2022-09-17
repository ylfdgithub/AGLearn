public class DoubleChar {


    public static void main(String[] args) {
        System.out.println(getChars(4));

    }
    public static String getChars(int target){
        StringBuilder sb = new StringBuilder();
        int index = 0;
        do {
            if ((target & 1) == 1) {
                sb.append((char) ('a' + index));
            }
            index++;
            target = target >>> 1;
        } while (target != 0);
        return sb.toString();
    }



}
