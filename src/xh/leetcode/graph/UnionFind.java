package xh.leetcode.graph;

/**
 * @Author XH
 * @Description TODO 并查集系列
 * @Date 2019/3/31 22:29
 */
public class UnionFind {
    /**
     * TODO LeetCode 684 在本问题中, 树指的是一个连通且无环的无向图。
     * 【并查集】n个顶点的最小生成树一定有n-1条边，若再加一条树中不存在的边，必定会构成环。从前向后遍历边集合，若两个顶点已经连通，则该边是多余的。====》输入边，构建并查集，找出第一条多余的边
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
     * 示例 1：
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的无向图为:
     *   1
     *  / \
     * 2 - 3
     * 示例 2：
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     *     |   |
     *     4 - 3
     * 注意:
     * 输入的二维数组大小在 3 到 1000。
     * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
     * @param edges
     * @return
     */
    //保存每个节点的跟节点
    int[] parents;
    //保存每个节点的高度
    int[] heights;

    public int[] findRedundantConnection(int[][] edges) {
        //边数
        int len = edges.length;
        //节点数
        parents = new int[len + 1];
        //高度（秩）
        heights = new int[len + 1];
        //初始化并查集
        for(int i = 0;i <= len;i++){
            parents[i] = i;
            heights[i] = 1;
        }
        for(int[] arr : edges){
            int p = arr[0];
            int q = arr[1];
            //若输入边的两个节点已经连通，直接返回当前边
            if(connected(p,q)){
                return arr;
            }else{//否则，将当前边的两个节点连接
                union(p,q);
            }
        }

        return null;
    }


    //找给定节点p的根节点
    public int find(int p){
        while(p != parents[p]){
            p = parents[p];
        }
        return p;
    }

    //判断两个节点是否连接在一起：拥有同一个根节点
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    //将节点p和q合并为同一个根节点:基于树的高度，对两个节点进行合并
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot){
            return;
        }
        int pHeight = heights[pRoot];
        int qHeight = heights[qRoot];
        if(pHeight < qHeight){
            parents[pRoot] = qRoot;
        }else if(pHeight > qHeight){
            parents[qRoot] = pRoot;
        }else{//pHeight == qHeight,此时被选为根节点的节点高度需+1
            parents[qRoot] = pRoot;
            heights[pRoot] += 1;
        }
    }

    public static void main(String[] args) {
        UnionFind solution = new UnionFind();
        int[][] edges = {
                {1,4},
                {3,4},
                {1,3},
                {1,2},
                {4,5}
        };

        int[] res = new int[2];
        res = solution.findRedundantConnection(edges);
        System.out.println(res[0] + "\t" + res[1]);
    }

}
