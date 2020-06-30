package xh.leetcode.回溯法.LC_51_N皇后;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO 回溯法 hard
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意位置
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例:
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * TODO 回溯法框架： 决策树的遍历（for循环里递归，递归前做选择，递归后撤销选择）
 * 1、路径
 * 2、选择列表
 * 3、结束条件
 * @Date 2020/6/30 23:53
 */
public class Solution {
    /** 保存所有解法 */
    private List<List<String>> res;
    /** 保存一种解法 */
    private List<String> board;
    /** 总行数row */
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        res = new ArrayList<>();
        board = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append('.');
        }
        for(int i = 0; i < n; i++) {
            board.add(sb.toString());
        }
        /**  从第0行开始回溯 */
        backtrack(0);
        return res;
    }

    /** 回溯法 */
    public void backtrack(int row) {
        /** 结束条件 */
        if(row == n) {
            res.add(new ArrayList<>(board));
            return;
        }
        /** 遍历第row行的列 */
        for(int col = 0; col < n; col++) {
            /** 校验是否满足皇后的条件 */
            if(!isValid(row,col)) {
                continue;
            }
            /** 递归前选择 */
            setChar(row, col, 'Q');
            /** 递归下一行 */
            backtrack(row + 1);
            /** 递归后撤销选择 */
            setChar(row, col, '.');
        }
    }

    /** 校验在row行选择col列是否符合皇后的条件：因为依次从第0行开始选择皇后，所以左下角和右下角不用校验（还没放皇后，肯定符合） */
    public boolean isValid(int row, int col) {
        /** 1、校验第col列 */
        for(int i = 0; i < row; i++) {
            if(board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        /** 2、校验左上角 */
        for(int i = row - 1,j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        /** 3、校验右上角 */
        for(int i = row - 1,j = col + 1; i >= 0 &&j < n; i--,j++) {
            if(board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }

    /** 设置board中row行col列为c */
    public void setChar(int row, int col, char c) {
       StringBuilder sb = new StringBuilder(board.get(row));
       sb.setCharAt(col, c);
       board.set(row, sb.toString());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        List<List<String>> res = solution.solveNQueens(n);
        res.stream().forEach(System.out::println);
    }

}
