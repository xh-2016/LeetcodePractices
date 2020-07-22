package xh.leetcode.回溯法.LC_79_单词搜索;

/**
 * @Author XH
 * @Description TODO 回溯法 medium
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些【TODO 水平相邻或垂直相邻：元素可以向上、下、左、右移动 】的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 *
 * @Date 2020/7/5 17:39
 */
public class Solution {

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(exist_helper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    /** 回溯法 */
    public boolean exist_helper(char[][] board, String word, int row, int col, int index) {
        /** 集中判断是否越界 */
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        /** 当前元素不匹配 */
        if(board[row][col] != word.charAt(index)) {
            return false;
        }
        /** TODO:最后一个字母也匹配上了，注意这里index的最大值是 word.length() - 1 */
        if(index == word.length() - 1) {
            return true;
        }

        /** 选中当前元素 */
        char temp = board[row][col];
        /** 避免重复使用元素，将当前元素位临时置为0-->对应ascii中空字符null */
        board[row][col] = 0;
        /** 递归判断元素的上下左右是否符合题意 */
        boolean up = exist_helper(board, word, row - 1, col, index + 1);
        boolean down = exist_helper(board, word, row + 1, col, index + 1);
        boolean left = exist_helper(board, word, row, col - 1, index + 1);
        boolean right = exist_helper(board, word, row, col + 1, index + 1);
        if(up || down || left || right) {
            return true;
        }

        /** 都不通，回溯到上一个状态 */
        board[row][col] = temp;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCB";
        System.out.println(solution.exist(board, word));
    }

}
