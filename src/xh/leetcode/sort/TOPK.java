package xh.leetcode.sort;

import java.util.PriorityQueue;

/**
 * @Author XH
 * @Description TODO TOP K 问题：一般采用 优先队列priorityQueue 或 大小根堆 O(NlogK)、快排O(NlogN) LeetCode 215
 * 最大的K个数（构建K小根堆）、最小的k个数（大根堆）
 * 小根堆Top K：先用前K个数创建小根堆，显然堆顶最小；再依次将数组中元素t与堆顶m比较：若t>min,则两者进行替换，并调整为小根堆；……直到最后一个元素与堆顶比较完成，此时K个元素的小根堆就保存了数组中前K大的元素，且堆顶即为第K大的元素
 * @Date 2019/3/7 19:36
 */
public class TOPK {
    /**
     * TODO 构建小根堆找最大的K个数：
     * 1、前K个元素创建小根堆；2、当前元素大于堆顶，则替换堆顶；3、替换后，调整维护为新的小根堆；4、数组遍历结束，小根堆中保存的K个元素即为数组中最大的K个数，且堆顶为第K大的元素
     * @param nums
     * @param K
     * @return
     */
    public int getMaxKNumsByHeap(int[] nums, int K){
        if(nums == null || nums.length < K || K < 0){
            return -1;
        }

        //1、前K个元素创建大根堆
        int[] kHeap = new int[K];
        for(int i = 0;i < K;i++){
            heapInsert(kHeap,i,nums[i]);
        }

        //2、当前元素大于堆顶，替换并维护小根堆
        for(int j = K;j < nums.length;j++){
            //替换堆顶
            if(nums[j] > kHeap[0]){
                kHeap[0] = nums[j];
                //调整堆
                heapify(kHeap,0,K);
            }
        }

        return kHeap[0];
    }

    //插入小根堆,从底向顶调整：因为插入是根据索引index大小往后插的
    public void heapInsert(int[] heap,int index,int value){
        heap[index] = value;
        while(index != 0){
            int parent = (index -1) / 2;
            if(heap[parent] > heap[index]){
                swap(heap,parent,index);
                index = parent;
            }else{//直接跳出整个循环，和插入顺序对应：每次直接将新元素插入尾部，如果当前插入元素的父节点和当前子节点依然满足小根堆特性，则其他元素必然满足，直接结束循环即可
                break;
            }
        }
    }

    //调整小根堆，从顶向底调整：因为每次只是改变了堆顶元素
    public void heapify(int[] heap,int index,int heapSize){
        //左孩子
        int left = 2 * index + 1;
        //右孩子
        int right = 2 * index + 2;
        //记录最小元素的索引
        int smallest = index;
        while(left < heapSize){
            if(heap[left] < heap[index]){
                //注意这里smallest会变化
                smallest = left;
            }
            //注意这里right比较的对象始终是smallest
            if(right < heapSize && heap[right] < heap[smallest]){
                smallest = right;
            }
            if(smallest != index){
                swap(heap,smallest,index);
            }else{//直接跳出整个循环，和只交换了堆顶元素对应，即堆顶附件都没有破坏小根堆的特性，那下面没有交换的部分更不可能被破坏，直接结束循环即可
                break;
            }
            index = smallest;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }

    }

    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * TODO 基于priorityQueue构建小根堆找TOP K
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     * @param nums
     * @param K
     * @return
     */
    public int getTopKbyMinHeap(int[] nums,int K){
        if(nums == null || nums.length < K || K < 0){
            return -1;
        }
        //基于优先级堆得极大优先级队列，默认自然序列升序
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        while(i != K){
           pq.add(nums[i++]);
        }

        for(int j = K;j < nums.length;j++){
            //大于堆顶元素
            if(nums[j] > pq.peek()){
                //移除堆顶
                pq.remove();
                //插入新元素
                pq.add(nums[j]);
            }
        }

        return pq.peek();
    }


    //基于降序快排找TOP K，时间复杂度为O(NlogN)
    public int getTopKbyQuickSort(int[] nums, int K) {
        if(nums == null || nums.length < K){
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;
        int index = partition(nums, low, high);
        //这里索引为index，对应第index+1大
        while(index != K - 1){
            if(index > K - 1){
                high = index - 1;
                index = partition(nums, low, high);
            }else{//index < K - 1
                low = index + 1;
                index = partition(nums, low, high);
            }
        }
        return nums[index];
    }

    //返回降序快排中的分区位置index：index左边比其大，右边比其小
    public int partition(int[] nums, int low, int high) {
        int t = nums[low];
        while (low < high) {
            //找到第一个比t大的
            while (low < high && nums[high] <= t) {
                high--;
            }
            if(low < high){
                nums[low] = nums[high];
            }
            //找到第一个比t小的
            while (low < high && nums[low] >= t) {
                low++;
            }
            if(low < high){
                nums[high] = nums[low];
            }
        }
        nums[low] = t;
        return low;
    }

    public static void main(String[] args) {
        int[] input = {3,2,3,1,2,4,5,5,6};
        int K = 4;
        TOPK topk = new TOPK();
        int result = topk.getMaxKNumsByHeap(input,K);
                //getTopKbyMinHeap(input,K);
                //getTopKbyQuickSort(input,K);
        System.out.println(result);
    }

}
