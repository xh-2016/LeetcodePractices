package xh.leetcode.dynamicProgramming;

/**
 * @Author XH
 * @Description TODO 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * https://blog.csdn.net/fuxuemingzhu/article/details/82656899
 * 【动态规划：难！】总共两种状态：sell 、 hold
 * sell[i]:当天结束手里没有股票的情况下，获取的最大收益(1、前一天手里也没有股票；2、前一天有股票，当天卖出)
 * hold[i]:当天结束手里有股票的情况下，获得的最大收益(1、前一天也有股票；2、当天买入股票--》前天卖出股票)
 * 状态转移方程：
 * sell[i] = max{sell[i-1],hold[i-1] + price[i]}
 * hold[i] = max{hold[i-1],sell[i-2] - price[i]}
 * 注意：第一天只能买入或啥也不做，不能卖出，需单独判断
 * return sell[n-1]   <===== 最后一天，手里没有股票的情况下，获得的最大利益
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * @Date 2019/4/21 19:31
 */
public class LeetCode309 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        int[] sell = new int[n];
        int[] hold = new int[n];
        sell[0] = 0;
        hold[0] = - prices[0];
        for(int i = 1;i < n;i++){
            sell[i] = Math.max(sell[i-1],hold[i-1] + prices[i]);
            //当i == 1时，sell[i-2]越界，取0
            hold[i] = Math.max(hold[i-1],(i >= 2 ? sell[i-2] : 0) - prices[i]);
        }
        return sell[n-1];
    }

    public static void main(String[] args) {
        LeetCode309 solution = new LeetCode309();
        int[] prices = {1,2,3,0,2};
        int res = 0;
        res = solution.maxProfit(prices);
        System.out.println(res);
    }

}
