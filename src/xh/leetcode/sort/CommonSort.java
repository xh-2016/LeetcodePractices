package xh.leetcode.sort;

/**
 * @Author XH
 * @Description TODO 常用排序算法：冒泡排序（重点）、快速排序（重点）、选择排序、插入排序、希尔排序、归并排序（重点）、堆排序（重点）、桶排序
 * @Date 2019/3/11 12:56
 */
public class CommonSort {

    /**
     * 冒泡排序（升序）：稳定，添加标志位flag，辅助提前结束循环，平均时间复杂度为O(N*N),最好情况（数据有序）可达到O(N),空间复杂度为O(1)。常考
     *
     * @param nums
     * @return
     */
    public int[] sort_bubble(int[] nums) {
        int len = nums.length;
        //标志位：是否发送数据交换，没有则表示排序已提前完成
        boolean flag = true;
        //注意：for循环条件中需同时哦按段flag标志，当第二层循环中发生数据交换操作才继续外层循环，否则，等价于 排序已提前完成，结束循环
        for (int i = 0; i < len - 1 && flag; i++) {
            flag = false;
            //从后向前冒泡（升序）：冒泡一轮，在数组前方得到一个最小的元素
            for (int j = len - 1; j > i; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                    flag = true;
                }
            }
            //遍历一轮，发现没有发生数据交换，则排序已提前结束
            if (!flag) {
                break;
            }
        }

        return nums;
    }

    /**
     * 快速排序（升序）【常考，经典递归】：时间复杂度为O(NlogN),空间复杂度为O(NlogN)~O(N)
     * 1、选取某个元素作为分割线，将小于其的元素交换至其左边，大于其的元素交换至其右边，最终返回分区元素在数组中的新索引index
     * 2、分为左右两个区间，分别递归调用快排
     * @param nums
     * @return
     */
    public int[] sort_quick(int[] nums){
        int len = nums.length;
        sort_quick_helper(nums,0,len-1);
        return nums;
    }

    public void sort_quick_helper(int[] nums,int low,int high){
        if(low < high){
            //找到分割位置
            int p = partition(nums,low,high);
            //递归
            sort_quick_helper(nums,low,p-1);
            sort_quick_helper(nums,p+1,high);
        }
    }

    //升序排列，默认选取nums[low]作为分割元素，将比其小的元素放在左边，比其大的放在右边
    public int partition(int[] nums,int low,int high){
        //选取左边的nums[low]作为分割元素
        int mid = nums[low];
        while(low < high){
            //从右边开始，找到第一个比分割元素mid小的元素
            //注意：这里的=很重要，不能将>=轻易改为>
            while(low < high && nums[high] >= mid){
                high--;
            }
            if(low < high){
                nums[low] = nums[high];
            }
            //找到第一个比分割元素mid大的元素
            while(low < high && nums[low] <= mid){
                low++;
            }
            if(low < high){
                nums[high] = nums[low];
            }
        }
        nums[low] = mid;
        return low;
    }
    /**
     * 选择排序（升序）：不稳定，时间复杂度为O(N*N),空间复杂度为O(1),选出无序序列中最小的元素插入有序序列尾，相当于冒泡排序减少了交换次数
     *
     * @param nums
     * @return
     */
    public int[] sort_chooose(int[] nums) {
        //升序排序：每轮遍历，选出无序序列中最小的元素，放在有序序列尾
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int smallest = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[smallest]) {
                    smallest = j;
                }
            }
            //遍历一轮，得到无序序列中最小元素,与有序序列中尾部元素交换
            if (smallest != i) {
                swap(nums, i, smallest);
            }
        }
        return nums;
    }

    /**
     * 插入排序（升序）:稳定，时间复杂度为O(N*N),空间复杂度为O(1),将当前元素插入有序序列中合适的位置，边比较边移动
     *
     * @param nums
     * @return
     */
    public int[] sort_insert(int[] nums) {
        int len = nums.length;
        //这里i指无序区间中当前元素的索引，[0，……，i-1]为升序区间
        for (int i = 1; i < len; i++) {
            //当前元素与升序区间元素依次进行比较，当前者小时，交换两者；遍历一遍升序序列，知道找到正确的位置
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }

        return nums;
    }

    /**
     * 希尔排序（升序）：时间复杂度范围为O(NlogN)~O(N*N),与步长gap的选取有关；空间复杂度为O（1），插入排序的升级版【增量缩小插入排序】，将数组以gap分为若干组，分别进行插入排序. 用的不多
     *
     * @param nums
     * @return
     */
    public int[] sort_shell(int[] nums) {
        //增量gap初始化为N/2,且gap=gap/2
        int len = nums.length;
        //gap=1时，本质上是插入排序，不过此时数组已经基本有序
        for (int gap = len / 2; gap >= 1; gap /= 2) {
            //从索引i为gap开始，依次遍历比较数组中元素，相邻元素一般处于不同分组
            for (int i = gap; i < len; i++) {
                //分组进行插入排序，除了索引的步长为gap，其他和插入排序完全一样
                for (int j = i; j > gap && nums[j] < nums[j - gap]; j -= gap) {
                    swap(nums, j, j - gap);
                }
            }
        }
        return nums;
    }

    /**
     * 归并排序（升序）：时间复杂度为O(NlogN),空间复杂度为O(N),算法稳定。形式上如同一颗二叉树，遍历的次数即为二叉树的深度
     * 归并：将两个有序序列合并为一个有序序列
     * 将数组从中间分为两部分，分别进行排序；再将两个有序序列合并（归并）为全局有序
     *
     * @param nums
     * @return
     */
    public int[] sort_merge(int[] nums) {
        int len = nums.length;
        int[] result = sort_merge_sort(nums, 0, len - 1);
        return result;
    }

    //1、升序排列nums[start……end]
    public int[] sort_merge_sort(int[] nums, int start, int end) {
        //注意：这里一定要写结束条件start==end，否则会堆栈溢出
        if(start == end){
            return null;
        }
        int mid = (start + end) / 2;
        sort_merge_sort(nums, start, mid);
        sort_merge_sort(nums, mid + 1, end);
        return sort_merge_merge(nums, start, mid, end);
    }


    //2、归并：将nums中升序数组[start……mid]与[mid+1……end]归并成一个有序数组
    //TODO 【常考】将两个分别有序的序列合并为一个有序的序列
    public int[] sort_merge_merge(int[] nums, int start, int mid, int end) {
        int[] result = new int[end - start + 1];
        int left = start;
        int right = mid + 1;
        //数组result的索引
        int index = 0;
        while (left <= mid && right <= end) {
            if (nums[left] > nums[right]) {
                result[index++] = nums[right++];
            } else {
                result[index++] = nums[left++];
            }
        }

        //有一个有序序列遍历结束，但另一个还没有，则直接全部插入
        //后边有序序列遍历结束
        for (int i = 0; i <= mid - left; i++) {
            result[index + i] = nums[left + i];
        }
        //前边有序序列遍历结束
        for (int j = 0; j <= end - right; j++) {
            result[index + j] = nums[right + j];
        }

        return result;
    }

    /**
     * 堆排序（小根堆 升序）：
     *
     * @param nums
     * @return
     */
    public int[] sort_heap(int[] nums) {
        int len = nums.length;
        //构建堆并维护堆，这里将数组中所有元素依次插入堆，变插入变维护为小根堆
        for (int i = 0; i < len; i++) {
            heap_insert(nums, i, nums[i]);
        }

//        for(int j = len -1;j > 0;j--){
//            //交换堆顶与堆尾
//            swap(nums,0,j);
//            //调整交换后的堆
//            heapify(nums,0,len);
//        }
        return nums;
    }

    //插入堆,从后插入，故堆从底向顶调整
    public void heap_insert(int[] heap, int index, int value) {
        heap[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (heap[parent] > heap[index]) {
                swap(heap, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    //调整堆,只交换了堆顶，故从顶向底调整
    public void heapify(int[] heap, int index, int heapSize) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;
        while (left < heapSize) {
            if (heap[left] < heap[index]) {
                smallest = left;
            }
            if (right < heapSize && heap[right] < heap[smallest]) {
                smallest = right;
            }
            if (smallest != index) {
                swap(heap, smallest, index);
            } else {
                break;
            }
            index = smallest;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    /**
     * 桶排序（升序）
     *
     * @param nums
     * @return
     */
    public int[] sort_bucket(int[] nums) {

        return nums;
    }

    public void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        for (int t : nums) {
            System.out.print(t + ",");
        }
        System.out.println("\n");

        //{2,4,6,5,3,1};
        CommonSort commonSort = new CommonSort();
        int[] result = new int[nums.length];
        result = commonSort.sort_bubble(nums);
        result = commonSort.sort_chooose(nums);
        result = commonSort.sort_heap(nums);
        result = commonSort.sort_insert(nums);
        result = commonSort.sort_shell(nums);
        result = commonSort.sort_merge(nums);
        result = commonSort.sort_quick(nums);
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println("\n");
    }

}
