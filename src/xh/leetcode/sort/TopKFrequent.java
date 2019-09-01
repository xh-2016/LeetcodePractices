package xh.leetcode.sort;

import java.util.*;

/**
 * @Author XH
 * @Description TODO 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。LeetCode 347
 * 【小根堆】一般TOP K题目都可以采用基于priorityQueue构建的大小根堆解决，T(N)=O(NlogK)
 * 【桶排序】：空间换时间，T(N)= O(N)
 * 1、生成HashMap，key为数组中元素，value为其出现的次数;
 * 2、根据HashMap中value值，进行桶排序，桶中放入对应的keySet，则出现频次最高的元素放在最后的桶中；
 * 3、逆序遍历桶，返回出现频次前K高的元素即可。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * @Date 2019/3/8 22:53
 */
public class TopKFrequent {

    /**
     * 【桶排序 经典】基于桶排序获取TOP K
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>();

        HashMap<Integer, Integer> map = new HashMap();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        //桶排序,桶的索引对应元素出现频次,当数组中元素全部相等时，对应频次为nums.length,即对应bucekt的index为nums.length,故bucket数组长度为nums.length+1
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Integer key : map.keySet()) {
            //frequent对应bucket的索引
            int frequent = map.get(key);
            if (bucket[frequent] == null) {
                bucket[frequent] = new ArrayList<>();
            }
            bucket[frequent].add(key);
        }

        //逆序遍历bucket，频次由高到低，获取前K个频率值，即为TOP K frequent
        //注意结束条件res.size()<K，当其=K时，会继续进入循环，res中元素个数会超过K
        for (int i = nums.length; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
            }
        }
        return res;
    }

    //找TOP K by priorityQueue构建的小根堆 LeetCode 347
    //TODO：需要重写比较器修改优先队列默认的比较器：同频次的元素按照map中key出现的频次排序；相同频次的元素按照key的自然顺序排序
    public List<Integer> topKFrequentByMinHeap(int[] nums, int K) {
        List<Integer> res = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 1) + 1);
        }

        //默认是小根堆结构,这里需要重写比较函数：按照map中key对应的value值排序，且保证相同频次下按照字典序排序
        //public int compareTo( NumberSubClass referenceName )
        //参数:referenceName -- 可以是一个 Byte, Double, Integer, Float, Long 或 Short 类型的参数。
        //返回值:
        //如果指定的数与参数相等返回0。
        //如果指定的数小于参数返回 -1。
        //如果指定的数大于参数返回 1。
        //TODO:升序降序记忆方法
        //if(o1.compareTo(o2) < 0 ){
        //    return ?;
        //}
        //这里o1表示位于前面的字符，o2表示后面的字符
        //上面的条件是，o1比o2小，这个时候，我们是否需要调整它们的顺序
        //如果你想升序，那么o1比o2小就是我想要的；所以返回-1，类比成false；表示我不想调整顺序
        //如果你想降序，那么o1比o2小不是我想要的；所以返回1，类比成true；表示我想调整顺序
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                if (map.get(key1).equals(map.get(key2))) {
                    //frequent相等时，按照key字典序排序
                    return key1.compareTo(key2);
                } else {
                    //frequent不相等时，按照frequent降序排序
                    return map.get(key2).compareTo(map.get(key1));
                }
            }
        });
        for (Integer key : map.keySet()) {
            pq.add(key);
        }

        while (res.size() < K) {
            res.add(pq.poll());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        TopKFrequent topKFrequent = new TopKFrequent();
        List<Integer> result = null;
        result = topKFrequent.topKFrequentByMinHeap(nums, k);
        //result = topKFrequent.topKFrequent(nums,k);

        String[] input = {"i", "love", "leetcode", "i", "love", "coding"};
                //{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> res = topKFrequent.topKFrequent(input,k);
        System.out.println(result);
        System.out.println(res);
    }

    //基于小根堆的优先级队列PriorityQueue LeetCode 692
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        //map：key=word,value=frequent of word
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 1) + 1);
        }

        //默认是小根堆结构,这里需要重写比较函数：按照map中key对应的value值降序排序，且保证相同频次下key按照字典序排序
        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String key1, String key2) {
                //frequent不相等时，按照frequent降序排序
                if (map.get(key1) < (map.get(key2))) {
                    return 1;
                } else if (map.get(key1) > map.get(key2)) {
                    return -1;
                } else {
                    //frequent相等时，按照key字典序排列
                    return key1.compareTo(key2);
                }
            }
        });
        for(String word : map.keySet()) {
            pq.add(word);
        }

        List<String> res = new ArrayList<>();
        while (res.size() < k) {
            res.add(pq.remove());
        }

        return res;
    }

}
