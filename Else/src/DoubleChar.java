import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DoubleChar {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("7 6");
        list.add("2 1 5");
        list.add("1 2");
        list.add("1 1");
        list.add("1 4");
        list.add("0");
        list.add("0");
        list.add("0");
        list.add("1 0");
        list.add("1 1");
        list.add("0 1");
        list.add("1 3");
        list.add("0 3");
        list.add("0 0");
        System.out.println(source(list));

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
    public static int source(List<String> input){
        //这个数组下标就是资源id,元素就是资源依赖的其他元素
        List<List<Integer>> sources = new ArrayList<>();
        //下面就是把资源的依赖项目加载到list中
        String[] s = input.get(0).split(" ");
        for (int i = 1; i < Integer.parseInt(s[0])+1; i++) {
            String[] s1 = input.get(i).split(" ");
            List<Integer> source = new ArrayList<>();
            for (int i1 = 1; i1 < s1.length; i1++) {
                source.add(Integer.parseInt(s1[i1]));
            }
            sources.add(source);
        }
        int max = 0;
        //先创建两个HashSet
        //请求资源
        HashSet<Integer> require = new HashSet<>();
        //需求资源
        HashSet<Integer> need = new HashSet<>();
        //后面开始模拟资源管理器
        for (int i = Integer.parseInt(s[0])+1; i < input.size(); i++) {
            int code = Integer.parseInt( input.get(i).split(" ")[0]);
            int sourceID = Integer.parseInt( input.get(i).split(" ")[1]);
            if (code==1){
                if (!need.contains(sourceID)&&!require.contains(sourceID)){
                    require.add(sourceID);
                    List<Integer> integers = sources.get(sourceID);
                    for (int i1 = 0; i1 < integers.size(); i1++) {
                        if (!require.contains(integers.get(i1))&&!need.contains(integers.get(i1))){
                            need.add(integers.get(i1));
                        }
                    }
                }else if (need.contains(sourceID)&&!require.contains(sourceID)){
                    need.remove(sourceID);
                    require.add(sourceID);
                    List<Integer> integers = sources.get(sourceID);
                    for (int i1 = 0; i1 < integers.size(); i1++) {
                        if (!require.contains(integers.get(i1))&&!need.contains(integers.get(i1))){
                            need.add(integers.get(i1));
                        }
                    }
                }
                max = Math.max(max,require.size()+need.size());
                //code =0
            }else {
                boolean flag = false;
                for (Integer integer : require) {
                    if (integer!=sourceID){
                        List<Integer> integers = sources.get(integer);
                        for (int i1 = 0; i1 < integers.size(); i1++) {
                            if (integers.get(i1)==sourceID) flag=true;
                        }
                    }
                }
                for (Integer integer : need) {
                    if (integer!=sourceID){
                        List<Integer> integers = sources.get(integer);
                        for (int i1 = 0; i1 < integers.size(); i1++) {
                            if (integers.get(i1)==sourceID) flag=true;
                        }
                    }
                }
                if (!flag){
                    require.remove(sourceID);
                    List<Integer> integers = sources.get(sourceID);
                    for (int i1 = 0; i1 < integers.size(); i1++) {
                        boolean flag1 = false;
                        for (Integer integer : require) {
                            if (integer!=integers.get(i1)){
                                List<Integer> integers1 = sources.get(integer);
                                for (int i2 = 0; i2 < integers.size(); i2++) {
                                    if (integers.get(i2)==sourceID) flag=true;
                                }
                            }
                        }
                        for (Integer integer : need) {
                            if (integer!=integers.get(i1)){
                                List<Integer> integers1 = sources.get(integer);
                                for (int i2 = 0; i2 < integers.size(); i2++) {
                                    if (integers.get(i2)==sourceID) flag=true;
                                }
                            }
                        }
                        if (!flag1){
                            need.remove(integers.get(i1));
                        }
                    }
                }
                else {
                    need.add(sourceID);
                    require.remove(sourceID);
                }
            }
        }
        return max;
    }


}
