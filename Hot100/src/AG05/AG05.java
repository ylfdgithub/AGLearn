package AG05;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class AG05 {
    public static void main(String[] args) {
        String a ="abc";
        System.out.println(longestPalindrome(a));
    }


    /**
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     */
    //最长回文子串，先用中心扩散法试试
    public static String longestPalindrome(String s) {
        int SLen = s.length();
        //先排除下两种特别情况
        if (s == null || SLen == 0) return "";
        if (SLen==1) return String.valueOf(s.charAt(0));

        int left = 0; //回文子串左边界下标
        int right = 0;//回文子串右边界下标
        int len = 1;//初始回文子串长度
        int startIndex = -1;//最长回文子串的起始点
        int maxLen = 1;

        for (int i = 0; i < SLen; i++) {
            len = 1;
            left = i-1;
            right = i+1;
            //下面分三种情况，前两种并非i位置元素为中心点，而是左边或者右边有与其相同的元素，类似baab
            //先往左边找到和中心元素相同的边界（退出循环的时候是边界外侧一个元素，也就是left-1，这个要注意，最后取子串的时候得加上1）
            while (left>=0 && s.charAt(i)==s.charAt(left)){
                len++;
                left--;
            }
            //第二种与第一种类似
            //找到相同的右边界
            while (right<SLen && s.charAt(i)==s.charAt(right)){
                len++;
                right++;
            }
            //第三种
            //开始比较两个边界，判断是否为回文
            while (left>=0 && right<SLen && s.charAt(left)==s.charAt(right)){
                len+=2;
                left--;
                right++;
            }
            //while循环结束后，根据长度判断是否需要记录该子串
            if (len>maxLen){
                startIndex = left;
                maxLen  = len;
            }
        }
        //这里还是一样的，记录的left实际上是边界-1，可以在这里加上，当然也可以在上面记录的时候
        return s.substring(startIndex+1,startIndex+maxLen+1);
    }
    public static String longestPalindromeDP(String s){
        int SLen = s.length();
        //先排除下两种特别情况
        if (s == null || SLen == 0) return "";
        if (SLen==1) return String.valueOf(s.charAt(0));

        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度
        //先构造一个二维数组，类似一个很多个点组成的矩形，长是子串起点，宽是子串终点，值是是否为回文子串；
        boolean[][] dp = new boolean[SLen][SLen];
        //整个求解过程就类似填表，下面我会用一个例子演示
        /**
         * s为 bccddc
         * 先构造一个矩形,0为false 1为true
         * [ ][0][0][0][0][0]
         * [ ][ ][1][0][0][0]
         * [ ][ ][ ][0][0][1]
         * [ ][ ][ ][ ][1][0]
         * [ ][ ][ ][ ][ ][0]
         * [ ][ ][ ][ ][ ][ ]
         * 大概就是如果例如 bccddc中 dd为回文子串，
         * 当判断cddc时只需要判断头尾是否相等以及中间内容是否为回文即可，
         * 而中间子串是否为回文的结果已经在前面的循环中记录到了数组中，查询是O(1)的时间复杂度，所以才叫空间换时间
         */
        for (int r = 1; r < SLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
