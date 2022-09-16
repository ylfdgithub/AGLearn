package AG04;



public class AG04 {
    public static void main(String[] args) {
        int[] arr02 = {3,4};
        int[] arr01 = {1,2};
        double r = ag03(arr01, arr02);
        System.out.println(r);
    }
    //合并数组
    //数组正序[1,3,8,10,14] [2,5,7,9,13]
    //
    public static double ag01(int[] nums1,int[] nums2){
        int len1=nums1.length;
        int len2=nums2.length;
        int len = len1+len2;
        //创建合并后数组
        int[] total = new int[len];
        int index01=0;
        int index02=0;
        for (int i = 0; i < len; i++) {
            if (index01>len1-1) {
                total[i]= nums2[index02];
                index02++;
                continue;
            }
            if (index02>len2-1){
                total[i]= nums1[index01];
                index01++;
                continue;
            }
            total[i]= Math.min(nums1[index01],nums2[index02]);
            if (nums1[index01]<nums2[index02]) index01++;
            else index02++;
        }
        if (len%2==1) return total[len/2];
        else return (double) (total[len/2]+total[len/2-1])/2;
    }


    public static double ag02(int[] nums1,int[] nums2){
        int len1=nums1.length;
        int len2=nums2.length;
        int len = len1+len2;
        int target = len/2;
        int container = 0;
        int count = -1;
        int index01=0;
        int index02=0;
        while (true){
            if (index01>len1-1){
                count++;
                if (count==target-1) container=nums2[index02];
                if (count==target){
                    if (len%2==0){
                        return (double) (container+nums2[index02])/2;
                    }else return nums2[index02];
                }else{
                    index02++;
                    continue;
                }
            }
            if (index02>len2-1){
                count++;
                if (count==target-1) container=nums1[index01];
                if (count==target){
                    if (len%2==0){
                        return (double) (container+nums1[index01])/2;
                    }else return nums1[index01];
                }else{
                    index01++;
                    continue;
                }
            }
            count++;
            if (count==target-1){
                if (nums1[index01]<nums2[index02]) container = nums1[index01];
                else container = nums2[index02];
            }
            if (count==target){
                if (nums1[index01]<nums2[index02]){
                    if (len%2==0) return (double) (container+nums1[index01])/2;
                    else return nums1[index01];
                }else{
                    if (len%2==0) return (double) (container+nums2[index02])/2;
                    else return nums2[index02];
                }
            }else {
                if (nums1[index01]<nums2[index02]) index01++;
                else index02++;
            }
        }
    }
    public static double ag21(int[] nums1,int[] nums2){
        int len1=nums1.length,len2=nums2.length;
        int len = len1+len2;
        int target = len/2;
        int left = 0, right = 0;
        int index1 = 0,index2 = 0;
        for (int i = 0; i < target + 1; i++) {
            left=right;
            if (index1<len1&&(index2>=len2||nums1[index1]<nums2[index2]))right = nums1[index1++];
            else right = nums2[index2++];
        }
        if (len%2==0) return (double) (left+right)/2;
        else return right;
    }

    //数组正序[1,3,8,10,14] [2,5,7,9,13]
    public static double ag03(int[] nums1,int[] nums2){
        int len1=nums1.length,len2=nums2.length;
        int len = len1+len2;
        int k = len/2+1;
        if (len%2==1){
            return getMiddle(nums1,nums2,k);
        }else {
            return ((double) (getMiddle(nums1,nums2,k-1)+getMiddle(nums1,nums2,k))/2);
        }
    }
    public static int getMiddle(int[] nums1,int[] nums2,int k){
        int index1 = 0;
        int index2 = 0;
        while (true){
            if (index1==nums1.length) return nums2[index2+k-1];
            if (index2==nums2.length) return nums1[index1+k-1];
            if (k==1) return Math.min(nums1[index1],nums2[index2]);
            int newIndex1=Math.min(index1+(k/2-1),nums1.length-1);
            int newIndex2=Math.min(index2+(k/2-1),nums2.length-1);
            if (nums1[newIndex1]<nums2[newIndex2]){
                k-=(newIndex1-index1+1);
                index1 = newIndex1+1;
            }
            else {
                k-=(newIndex2-index2+1);
                index2 = newIndex2+1;
            }
        }
    }

}
