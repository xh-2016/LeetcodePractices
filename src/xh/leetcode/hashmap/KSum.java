package xh.leetcode.hashmap;

import java.util.*;

/**
 * @Author XH
 * @Description TODO TwoSum ThreeSum NSum 给定一个数组和目标值k，找出2、3、n个和为k的数字对并返回。
 *
 * @Date 2019/3/13 22:24
 */
public class KSum {

    /** TODO LeetCode 1 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * @param input
     * @param target
     * @return
     */
    public int[] twoSum(int[] input,int target){
        if(input == null || input.length < 2){
            return null;
        }

        int[] res = new int[2];
        int len = input.length;
        //记录数组input中包含的数字，及其第一次出现的位置
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < len;i++){
            if(!map.containsKey(input[i])){
                map.put(input[i],i);
            }
        }
        for(int j = 0;j < len;j++){
            int t = target - input[j];
            //注意：这里要限制 不能重复利用这个数组中同样的元素
            //eg:input=[2,3,5],k=6,res=null.而不是[1,1]
            if(map.containsKey(t) && map.get(t) != j){
                res[0] = j;
                res[1] = map.get(t);
                return res;
            }
        }
        return null;
    }

    //TODO 求两数之和的所有可能并返回所有不重复的解集
    //数组排序后，通过判断相邻元素不等来去重重复解
    public List<List<Integer>> twoSum1(int[] nums,int target){
        if(nums == null || nums.length < 2){
            return null;
        }

        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        int L = 0;
        int R = len - 1;
        while(L < R){
            int t1 = nums[L];
            int t2 = nums[R];
            if(t1 + t2 < target){
                L++;
            }else if(t1 + t2 > target){
                R--;
            }else{
                //去除重复的可能：排序后，相邻元素不等；当l=0时，l-1越界，故l=0单独拿出来讨论
                if(L == 0 || nums[L] != nums[L-1]){
                    res.add(Arrays.asList(t1,t2));
                }
                L++;
                R--;
            }
        }
        return res;
    }

    /**
     * TODO LeetCode 15 三数之和
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //第一步：排序 默认升序
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // TODO 重点: 第一次 跳过可能重复的答案
            if (i == 0 || (nums[i] != nums[i - 1])) {
                int L = i + 1, R = nums.length - 1, sum = 0 - nums[i];
                while (L < R) {
                    int t1 = nums[L];
                    int t2 = nums[R];
                    if (t1 + t2 == sum) {
                        ls.add(Arrays.asList(nums[i], t1, t2));
                        //TODO 重点：第二次 跳过重复值
                        while (L < R && nums[L] == nums[L + 1]) {
                            L++;
                        }
                        while (L < R && nums[R] == nums[R - 1]) {
                            R--;
                        }
                        L++;
                        R--;
                    } else if (t1 + t2 < sum) {
//                        while (L < R && nums[L] == nums[L + 1]) {
//                            L++;   // 跳过重复值
//                        }
                        L++;
                    } else {
                        //跳过重复值
//                        while (L < R && nums[R] == nums[R - 1]) {
//                            R--;
//                        }
                        R--;
                    }
                }
            }
        }
        return ls;
    }

    // TODO 三数之和：排序+双指针+借助set去重重复解
    public List<List<Integer>> threeSum1(int[] nums,int target){
        if(nums == null || nums.length < 3){
            return null;
        }

        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //借助set去除重复的解
        Set<List<Integer>> set = new HashSet<>();
        //threeSum
        for(int i = 0;i < len - 2;i++){
            //===》 twoSum
            int L = i + 1;
            int R = len - 1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum > target){
                    R--;
                }else if(sum < target){
                    L++;
                }else{//sum == target
                    List<Integer> list = Arrays.asList(nums[i],nums[L],nums[R]);
                    if(set.add(list)){
                        res.add(list);
                    }
                    L++;
                    R--;
                }
            }
        }
        return res;
    }

    /**
     * TODO LeetCode 18 四数之和 = 排序+双指针+set去除重复解
     * 核心还是两数之和+双指针，外面加嵌套=》三数之和，再加嵌套=》四数之和,借助set去除重复解
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     * 注意：
     * 答案中不可以包含重复的四元组。
     * 示例：
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     * @param nums
     * @param target
     */
    public List<List<Integer>> fourSum(int[] nums,int target){
        if(nums == null || nums.length < 4){
            return null;
        }

        //第一步：排序 升序
        Arrays.sort(nums);

        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //set用来排除元素相同，顺序不同的解
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0;i < len - 3;i++){
            for(int j = i+1;j < len - 2;j++){
                int L = j + 1;
                int R = len - 1;
                while(L < R){
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if(sum > target){
                        R--;
                    }else if(sum < target){
                        L++;
                    }else{//sum==target
                        //set检查是否包含解：Arrays.asList(nums[i], nums[j], nums[L], nums[R]))
                        if (set.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]))) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        }
                        L++;
                        R--;
                        }
                }
            }
        }
        return res;
    }


    /**
     * TODO LeetCode 454 遍历A、B数组中两数和构成查找表AB_map,遍历C、D数组两数和sum，在AB_map中查找-sum出现的次数，借助hashMap
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
     * 例如:
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * 输出:
     * 2
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A,int[] B,int[] C,int[] D){
        int res = 0;
        int N = A.length;
        //将A、B数组包含的两数之和保存在map中并记录次数，key为sum，value为count，作为查找表
        Map<Integer,Integer> AB_map = new HashMap<>();
        for(int i = 0;i < N;i++){
            for(int j = 0;j < N;j++){
                int sum = A[i] + B[j];
                AB_map.put(sum,AB_map.getOrDefault(sum,0)+1);
            }
        }

        //遍历C、D数组，获得C、D两数之和，在查找表AB_map中查找-sum出现的次数
        for(int i = 0;i < N;i++){
            for(int j = 0;j < N;j++){
                int sum = C[i] + D[j];
                //在AB_map中查找sum出现的次数
                res += AB_map.getOrDefault(-sum,0);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = {11,3,2,7,6,5};
        int[] nums = {-2,0,-1,1,2,-1,1};
                //{-1, 0, 1, 2, -1, -4};
        int target = 9;
        int[] A = {1, 2};
        int[] B = {-2,-1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        KSum kSum = new KSum();
        List<List<Integer>> result = new ArrayList<>();
        result = kSum.threeSum(nums);
        result = kSum.threeSum1(nums,0);
        //result = kSum.fourSum(nums,0);
        int count = 0;
        count = kSum.fourSumCount(A,B,C,D);
        System.out.println(count);
        //System.out.println(result.toString());
    }
}
