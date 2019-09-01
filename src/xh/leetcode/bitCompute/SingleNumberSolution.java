package xh.leetcode.bitCompute;

/**
 * @Author XH
 * @Description TODO 找只出现一次字符的系列题 LeetCode 136 137 260
 * @Date 2019/3/21 20:12
 */
public class SingleNumberSolution {

    /**
     * TODO LeetCode 136 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 【遍历做异或运算】
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     * @param nums
     * @return
     */
    public int singleNumber1(int[] nums) {
        int xor = 0;
        for(int i : nums){
            xor ^= i;
        }
        return xor;
    }

    /**
     * TODO LeetCode 260 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
     *【思路】如果可以把two number single的问题变成两个single number的问题，就可以套用之前《LeetCode 136. 只出现一次的数字》的解法了，也就是说我们可以通过某种方式把数组分为两组，每组只包含那两个special number中的一个。
     * 问题的关键就变成如何分组了，利用《136. 只出现一次的数字》解法求出的结果为两个special number的异或值，因两数不同，异或值必有至少一个位为1，根据该位即可将原数组中的数分为两组；
     * 示例 :
     * 输入: [1,2,1,3,2,5]
     * 输出: [3,5]
     * 注意：
     * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
     * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {
        //第一步、遍历做异或运算
        int temp_xor = 0;
        for(int i : nums){
            temp_xor ^= i;
        }

        //第二步、找出异或运算后的int 类型的32位中第一次为1的位数
        //最终diff 中仅某一位为1，其余均为0
        int diff = 1;
        //依次判断temp_xor中第i位是否为1
        for(int i = 0;i < 32;i++){
            //temp_xor中第i位为1
            if(((temp_xor >> i) & 1) == 1){
                //第i位为1 ===》区分条件
                diff = (diff << i);
                break;
            }
        }

        int[] res = new int[2];
        //第三步、根据区分条件diff 分为两个 singleNumber1问题
        for(int num:nums){
            //注意这里的判断条件:不符合diff条件
            if((num & diff) == 0){
                res[0] ^= num;
            }else{
                res[1] ^= num;
            }
        }

        return res;
    }

    /**
     * TODO LeetCode 137 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * TODO 由于所有数都是整数，所以最大位数为32位，对于出现三次的数，统计每一位上1出现的次数一定能被3整除，而不能被三整除的位一定是单独出现的数造成的，所以依次统计每一位的次数，并把不能被3整除的位数设为1赋给结果。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,3,2]
     * 输出: 3
     * 示例 2:
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        int res = 0;
        int tmp = 0;
        //int 类型是4 bytes，32位; i 代表位数
        for(int i = 0;i < 32;i++){
            int sum = 0;
            for(int num : nums){
                //当前数字num的第i位（num>>i）是否为1，为1则tmp=1，否则tmp=0
                tmp = (num >> i) & 1;
                //统计第i位为1的数字的个数
                sum += tmp;
            }
            //数组遍历结束，第i位出现次数不为3的倍数，则第i位在目标数字中必然为1
            if(sum % 3 != 0){
                //复原目标数字 ，与第i位做 或运算
                //TODO 注意：不能被3整除的位数设为1赋给结果
                res |=  (1 << i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input1 = {4,1,2,1,2};
        int[] input2 = {1,2,1,3,2,5};
        int[] input3 = {0,1,0,1,0,1,99};
        SingleNumberSolution solution = new SingleNumberSolution();
        int singleNumber1 = 0;
        singleNumber1 = solution.singleNumber1(input1);
        int[] singleNumber2 = new int[2];
        singleNumber2 = solution.singleNumber2(input2);
        int singleNumber3 = 0;
        singleNumber3 = solution.singleNumber3(input3);
        System.out.println("singleNumber1\t" + singleNumber1);
        for(int res : singleNumber2){
            System.out.println("singleNumber2\t" + res);
        }
        System.out.println("singleNumber3\t" + singleNumber3);
    }

}
