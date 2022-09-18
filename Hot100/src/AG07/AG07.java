package AG07;

public class AG07 {


    /**
     * 给定一个长度为 n 的整数数组 height有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     *
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 返回容器可以储存的最大水量。
     * 先来个暴力解法吧,结果超时
     */
    public static int maxArea1(int[] height) {
        int maxV = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxV=Math.max(maxV,(j-i)*Math.min(height[i],height[j]));
            }
        }
        return maxV;
    }

    /**
     * 正确题解双指针
     * [1,3,4,5,6,87,9]
     */

    public static int maxArea2(int[] height) {
        if (height.length<=1) return 0;
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while (true){
            max = height[left]<height[right]?Math.max(max,(right-left)*height[left++]):
                    Math.max(max,(right-left)*height[right--]);
            if (right==left) return max;
        }


    }
}
