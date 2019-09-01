package xh.leetcode.graph;

/**
 * @Author XH
 * @Description TODO LeetCode 785 给定一个无向图graph，当这个图为二分图时返回true。
 * 【染色法+DFS】 将无向图中所有节点染成两种颜色，且每条边中两个节点颜色不同 ===》 二分图
 * 0表示未染色，1表示第一种颜色，2表示第二种颜色
 * 初始化所有节点未染色；
 * DFS：包含4个参数，第一个参数是graph的邻接表，第二个参数是每个节点的染色结果数组，第三个参数是的当前节点，第四个参数是上一个节点的颜色（初始化为-1）；
 * 判断当前节点未染色，则将其染色为与上一个节点不同的颜色，且递归dfs判断当前节点的所有邻接节点是否满足；若当前节点已染色，则直接判断其染色是否与上一个节点的染色相同，相同则为false。

 * 原文：https://blog.csdn.net/qq_36272282/article/details/82913056
 * 因为二分图可以划分为两个互不相交的子集，所以可以只用两种颜色染色，使得一条边的两个邻接节点染上不同的颜色。
 * 首先定义颜色，0为未上色，1为第一种颜色，-1为第二种颜色
 * 大致做法：
 * 声明一个数组color，用来保存所有点的颜色，全初始化为未上色，然后定义深度优先搜索函数dfs，这个函数第一个参数是图graph，第二个参数是要遍历的开始点start，第三个参数是遍历时开始点start的上一个点的颜色，第四个参数是所有点的颜色数组color。
 * 在dfs函数中，若此刻遍历的开始点start的颜色为未上色，则染上与上一个邻接点的颜色不同的颜色，然后继续深度优先搜索；若已上色，则判断该点颜色是否与上一个邻接点的颜色不同，若不同则返回true，否则返回false。
 * 在isBipartite函数中，用for循环遍历所有点，若该点是无颜色，则传入深度优先搜索函数dfs函数处理，否则跳过。（因为在一次dfs遍历后，与开始点start连通的点肯定都进行了染色处理，所以已经染色的可以跳过。注意，此时传入的点是第一个遍历的点，不存在与之连接的上一个点的颜色，所以dfs函数的第三个参数我们默认传入-1，目的是让第一个遍历的点染上第一种颜色。）
 * *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 * @Date 2019/4/7 20:49
 */
public class BipartiteGraph {

    //graph是邻接表，即保存的是每个节点的邻接点
    public boolean isBipartite(int[][] graph) {
        //节点数
        int n = graph.length;
        //保存每个节点的染色情况
        int[] colors = new int[n];
        //初始化染色数组
        for(int i = 0;i < n;i++){
            colors[i] = 0;
        }

        for(int i = 0;i < n;i++){
            //当前节点未染色，且不符合dfs
            if(colors[i] == 0 && !dfs(graph,colors,i,-1)){
                return false;
            }
        }
        return true;
    }

    //dfs判断是否符合条件，preColor初始化为-1（第二种颜色），使每个节点的第一个邻接点染第一种颜色
    public boolean dfs(int[][] graph,int[] colors,int node,int preColor){
        //若当前节点已经染色
        if(colors[node] != 0){
            return (preColor == colors[node])?false:true;
        }

        //当前节点未染色：染色为与上一个节点颜色不同的颜色
        colors[node] = (-1) * preColor;
        //dfs递归判断当前节点的所有邻接点是否满足
        for(int nextNode : graph[node]){
            if(!dfs(graph,colors,nextNode,colors[node])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteGraph solution = new BipartiteGraph();
        int[][] graph = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };

        boolean res = false;
        res = solution.isBipartite(graph);
        System.out.println(res);
    }

}
