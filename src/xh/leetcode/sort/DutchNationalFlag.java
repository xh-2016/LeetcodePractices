package xh.leetcode.sort;

/**
 * @Author XH
 * @Description TODO 荷兰国旗问题(荷兰国旗是由红白蓝三种颜色的条纹拼接而成)
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * TODO：给定一个数组和一个数，小于的放在数的左边，大于的放在数的右边，等于的放在中间
 * 【变形快排】start指向数组的开始，end指向数组的结尾，curr指向当前位置（从0开始）：
 * if curr = 0: swap nums[start] and curr; start++ curr++;
 * else if curr = 2: swap nums[end] and curr ;end--;
 * else curr++;
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * @Date 2019/3/7 21:37
 */
public class DutchNationalFlag {

    public void sortColors(int[] nums){
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int cur = 0;
        //注意循环结束条件是：cur<=end
        while(cur <= end){
            if(nums[cur] < 1){
                swap(nums,cur,start);
                start++;
                cur++;
            }else if(nums[cur] > 1){
                swap(nums,cur, end);
                end--;
            }else{
                cur++;
            }
        }
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag();
        dutchNationalFlag.sortColors(nums);
        for(int i : nums){
            System.out.println(i);
        }
    }
}
