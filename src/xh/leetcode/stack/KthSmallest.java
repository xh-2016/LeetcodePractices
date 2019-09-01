package xh.leetcode.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author XH
 * @Description TODO LeetCode 378 给定一个 n * n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 【大根堆 PriorityQueue】TOP k问题：将前k个元素加入大根堆；然后继续遍历矩阵中元素，若当前元素 小于堆顶，则替换堆顶；遍历结束，堆中元素为前k小的元素，且堆顶元素即为矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 * 示例:
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 * @Date 2019/3/19 18:47
 */
public class KthSmallest {

    //TOP K ====》大根堆
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        //借助PriorityQueue实现大根堆，默认时小根堆，故需重写比较器
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //大根堆：o2 > o1,交换两者
                return o2.compareTo(o1);
            }
        });

        for(int i = 0;i < len;i++){
            for(int j = 0;j < len;j++){
                int cur = matrix[i][j];
                //前k个元素入栈，构建大根堆
                if(pq.size() < k){
                    pq.add(cur);
                }else{
                    //当前元素小于堆顶，则需交换
                    if(cur < pq.peek()){
                        pq.remove();
                        pq.add(cur);
                    }
                }
            }
        }

        return pq.peek();
    }


    //TODO 二分查找第k小的数:矩阵中行、列均升序排列 不太懂！！
    public int KthSmallestByBinarySearch(int[][] input,int k){
        int len = input.length;
        int low = input[0][0];
        int high = input[len-1][len-1];
        int res = 0;

        //TODO 注意 结束条件
        while(low <= high){
            int mid = low + (low + high) / 2;
            //矩阵中小于mid的数字个数 小于 k
            if(cntLow(input,k,mid)){
                //TODO 这里不懂！
                res = mid;
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        return res;
    }

    //判断矩阵input中小于g的数字数目是否 小于 k,是 则返回true，否 则返回false
    public boolean cntLow(int[][] input,int k,int g){
        int len = input.length;
        int count = 0;
        for(int i = 0;i < len;i++){
            int low = 0;
            int high = len - 1;
            int cur_count = 0;
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(input[i][mid] < g){
                    low = mid + 1;
                    //[0……mid]   共mid+1个数字 < g
                    cur_count = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
            count += cur_count;
        }

        return count < k ? true : false;
    }
    

    public static void main(String[] args) {
        int[][] input = {
                {1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        KthSmallest kthSmallest = new KthSmallest();
        int result = 0;
        result = kthSmallest.kthSmallest(input,k);
        System.out.println(result);
    }

}
