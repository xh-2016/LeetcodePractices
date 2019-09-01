package xh.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO LeetCode 77 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *【回溯法】
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * @Date 2019/4/10 22:07
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        combine_helper(n,k,1,cur,res);
        return res;
    }

    public void combine_helper(int n,int k,int start,List<Integer> cur,List<List<Integer>> res){
        if(k < 0){
            return;
        }else if(k == 0){
            res.add(new ArrayList<>(cur));
        }else{
            for(int i = start;i <= n;i++){
                cur.add(i);
                combine_helper(n,k-1,i+1,cur,res);
                //回退
                cur.remove(cur.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Combine solution = new Combine();
        int n = 4;
        int k = 2;
        List<List<Integer>> res = new ArrayList<>();
        res = solution.combine(n,k);
        System.out.println(res);
    }

}
