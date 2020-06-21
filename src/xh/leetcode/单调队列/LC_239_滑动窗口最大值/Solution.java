package xh.leetcode.单调队列.LC_239_滑动窗口最大值;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author XH
 * @Description TODO 单调队列+滑动窗口 hard
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *  
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 *
 * TODO 单调队列：队列中元素单调递增或递减
 * push（）：从队尾入队，当队尾元素小于新元素时，不断移除队尾元素，再入队新元素，保证队列单调递减
 * pop（）：从队首出队,当队首元素不等于目标元素时
 * max（）：队首元素
 *
 * @Date 2020/6/21 16:50
 */
public class Solution {
    /** 无需自定义单调数组，借助双端队列实现 */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> max = new ArrayDeque<>();
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int[] res = new int[n - k + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {

            /** i>= k时，需要移除双端队列最大元素 */
            if (i >= k) {
                if (max.peekFirst() == nums[i - k]) {
                    max.removeFirst();
                }
            }

            /** 队尾入队，保证队列递减 */
            while (!max.isEmpty() && nums[i] > max.getLast()) {
                max.removeLast();
            }
            max.addLast(nums[i]);

            /** i >= k-1时，队首最大值加入res集合 */
            if (i >= k - 1) {
                res[index] = max.getFirst();
                index++;
            }
        }
        return res;
    }

    /** 自定义单调队列 + 滑动窗口 */
    public int[] maxSlidingWindow_old(int[] nums, int k) {
        int len = nums.length;
        /** 自定义的单调数组 */
        MonotonousQueue window = new MonotonousQueue();
        /** 异常处理 */
        if(len == 0) {
            /** 空数组 */
            return nums;
        }

        int[] res = new int[len - k + 1];
        for(int i = 0; i < len; i++) {
            if(i < k - 1) {
                window.push(nums[i]);
            } else {
                /** 移动滑动窗口 */
                /** 队尾入队，保持单调递减， i >= k - 1时，需操作 */
                window.push(nums[i]);
                /** 队首出队，i >= k时，需操作 */
                if(i >= k) {
                    window.pop(nums[i - k]);
                }
                /** 队首最大值加入res， i >= k - 1时，需操作 */
                res[i - k + 1] = window.max();
            }
        }

        return res;
    }

    /** 单调递减数据结构：双端队列
     *  push（）：从队尾入队，当队尾元素小于新元素时，不断移除队尾元素，再入队新元素，保证队列单调递减
     *  pop（）：从队首出队
     *  max（）：队首元素
     * */
    public class MonotonousQueue {
       private Deque<Integer> data = new ArrayDeque<>();
       /** 队尾入队 */
       public void push(int n) {
           while(!data.isEmpty() && data.getLast() < n) {
               data.removeLast();
           }
           data.addLast(n);
       }

       /** 获取最大值 */
       public int max() {
           return data.getFirst();
       }

       /** 队首元素为n时，移除元素 */
       public void pop(int n) {
           /** 注意：这里队头元素！= n时，不用删除，因为队头元素不是最大值时，会在 push过程中被移除了 */
           if(!data.isEmpty() && data.getFirst() == n) {
               data.removeFirst();
           }
       }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        Arrays.stream(solution.maxSlidingWindow(nums, k)).forEach(System.out::println);
    }

}
