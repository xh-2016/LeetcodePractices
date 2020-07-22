package xh.leetcode.二分查找.LC_852_山脉数组的峰顶索引;

/**
 * @Author XH
 * @Description TODO xh.leetcode.二分查找 easy
 * 我们把符合下列属性的数组 A 称作山脉：
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 *
 * 示例 1：
 * 输入：[0,1,0]
 * 输出：1
 * 示例 2：
 * 输入：[0,2,1,0]
 * 输出：1
 *
 * 提示：
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 *
 * TODO 模板1：while(left<=right) 循环体内有3个分支,在循环体中返回目标索引
 * TODO 模板2：while(left<right) 循环体内有2个分支,在循环体外返回目标索引，在循环体内缩减搜索区间
 * 1、A[mid] < A[mid + 1],则 山脉一定在右边，即舍弃左边===> start = mid + 1
 * 2、A[mid] >= A[mid + 1],则 山脉一定在左边，即舍弃右边===> end= mid
 * 3、return start；
 * @Date 2020/6/16 1:35
 */
public class Solution {

    /** 模板1：while(left<=right) 循环体内有3个分支,在循环体中返回目标索引 */
    public int peakIndexInMountainArray1(int[] A) {
        int start = 0;
        int len = A.length;
        int end = len - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
                return mid;
            }else if(A[mid] < A[mid + 1]){
                start = mid + 1;
            }else {
                /** 分支3：A[mid] < A[mid - 1] */
                end = mid - 1;
            }
        }
        return -1;
    }

    /** 模板2：while(left<right) 循环体内有2个分支,在循环体外返回目标索引，在循环体内缩减搜索区间 */
    public int peakIndexInMountainArray2(int[] A) {
        int start = 0;
        int len = A.length;
        int end = len - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(A[mid] < A[mid + 1]) {
                start = mid + 1;
            }else {
                end = mid;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {0,2,1,0};
        System.out.println(solution.peakIndexInMountainArray1(A));
    }

}
