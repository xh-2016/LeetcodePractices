package xh.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author XH 回溯法：组合总和系列
 * @Description
 *
 * @Date 2019/4/10 20:40
 */
public class CombinationSum {

    /**
     * TODO LeetCode 39 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * 【回溯法】
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        //先升序排序
        Arrays.sort(candidates);
        List<Integer> cur = new ArrayList<>();
        combinationSum_helper(candidates,target,0,cur,res);
        return res;
    }

    public void combinationSum_helper(int[] candidates, int target, int start, List<Integer> cur, List<List<Integer>> res){
        if(target < 0){ //凑过头了
            return;
        } else if(target == 0){
            //注意：这里需要拷贝一份cur，不能直接引用，否则最后都会是空
            res.add(new ArrayList<>(cur));
            return;
        }else{
            for(int i = start;i < candidates.length;i++)
            {
                int t = candidates[i];
                cur.add(t);
                //可以允许重复值：下一个start依然是i
                combinationSum_helper(candidates,target-t,i,cur,res);
                //恢复
                cur.remove(cur.size() - 1);
            }
        }
    }


    //回溯法：需要去重数组中元素

    /**
     * TODO leetCode 40 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * 【回溯法】：注意此时数组中可能包含重复元素，排序后，重复元素必然在一起，需要去除重复元素
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        //先升序排序
        Arrays.sort(candidates);
        List<Integer> cur = new ArrayList<>();
        combinationSum2_helper(candidates,target,0,cur,res);
        return res;
    }

    public void combinationSum2_helper(int[] candidates, int target, int start, List<Integer> cur, List<List<Integer>> res){
        if(target < 0){ //凑过头了
            return;
        } else if(target == 0){
            //注意：这里需要拷贝一份cur，不能直接引用，否则最后都会是空
            res.add(new ArrayList<>(cur));
            return;
        }else{
            for(int i = start;i < candidates.length;i++)
            {
                int t = candidates[i];
                //排序后，比较相邻值是否相等，即可去除重复值
                if(i-1 >= start && t == candidates[i-1]){
                    continue;
                }
                cur.add(t);
                //不可以允许重复值：下一个start是i+1
                combinationSum2_helper(candidates,target-t,i+1,cur,res);
                //恢复
                cur.remove(cur.size() - 1);
            }
        }

    }

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> res = new ArrayList<>();
        res = solution.combinationSum(candidates,target);
        System.out.println(res);
    }

}
