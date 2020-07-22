package xh.leetcode.回溯法.LC_89_格雷编码;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XH
 * @Description TODO  medium 镜像反射法
 * https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 *  
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 * @Date 2020/7/5 18:15
 */
public class Solution {

    /** 镜像反射法 */
    /** 设 n 阶格雷码集合为 G(n)，则 G(n+1)阶格雷码为：
     给 G(n) 阶格雷码每个元素二进制形式前面添加 0，得到 G'(n)；
     设 G(n) 集合倒序（镜像）为 R(n)，给 R(n) 每个元素二进制形式前面添加 1，得到 R'(n)；
     G(n+1) = G'(n) ∪ R'(n) 拼接两个集合即可得到下一阶格雷码。
     根据以上规律，可从 0 阶格雷码推导致任何阶格雷码。
     代码解析：
     1、由于最高位前默认为 0，因此 G'(n) = G(n)，只需在 res(即 G(n) )后添加 R'(n)即可；
     2、计算 R'(n)：执行 head = 1 << i =（2^i）， 计算出对应位数，即给 R(n) 前的最高位添加 1 得到对应 R'(n)；
     3、倒序遍历 res(即 G(n) )：依次求得 R'(n) 各元素添加至 res 尾端，遍历完成后 res(即 G(n+1))。
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        /** 添加初始值，grayCode(0) */
        res.add(0);
        if(n == 0) {
            return res;
        }

        /** 递归，依次求出grayCode(1) -> grayCode(n) */
        for(int i = 1; i <= n; i++) {
            /** 求出最高位1代表的值,等价于2^(i-1) */
            int head = 1 << (i-1);
            /** 镜像：倒序排列后，依次加上 2^(i-1), 即在最高位加上1 */
            for(int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        System.out.println(solution.grayCode(2));
    }

}
