package xh.leetcode.array;

/**
 * @Author XH
 * @Description TODO 双指针题型
 * @Date 2019/3/7 18:24
 */
public class DoublePointer {

    /**
     * 给定一个已按照 升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     * 说明:
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * 示例:
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * @param number
     * @param target
     * @return
     */
    public int[] twoSum(int[] number,int target){
        if(number == null || number.length < 2){
            return null;
        }

        int len = number.length;
        int start = 0;
        int end = len - 1;
        int[] res = new int[2];
        while(start < end){
            int temp = number[start] + number[end];
            if(temp > target){
                end--;
            }else if(temp < target){
                start++;
            }else{//temp = target,res中下表从1开始计数，故这里位置对应索引+1
                res[0] = start + 1;
                res[1] = end + 1;
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] numbers = {2,2,11,15};
        int target = 4;
        DoublePointer doublePointer = new DoublePointer();
        int[] res = doublePointer.twoSum(numbers,target);
        System.out.println(res[0] + " " + res[1]);
    }
}
