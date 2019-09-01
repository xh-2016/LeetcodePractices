package xh.leetcode.graph;

import java.util.*;

/**
 * @Author XH
 * @Description TODO 拓扑排序系列
 * @Date 2019/3/31 18:44
 */
public class TopologicalSort {

    /**
     * TODO LeetCode 207 【有向图是否有环】现在你总共有 n 门课需要选，记为 0 到 n-1。在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]。给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     * 【思路】利用图的拓扑排序。
     * 拓扑排序是来检测图中有无环的算法。具体步骤：
     * 1、找到一个入度为0的点，若没有，则返回图中有环。
     * 2、删除1中找到的点及以它为起点的边。
     * 3、重复1、2直到图中所有点都被删除，此时返回图中无环。
     * 示例 1:
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     * 说明:
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 提示:
     * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
     * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
     * 拓扑排序也可以通过 BFS 完成。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //每个课程看做有向图中的一个节点，每个前置条件看做有向图中的一条边
        if(prerequisites.length == 0){
            return true;
        }

        //构建有向图,key时节点，value是key指向的所有节点集
        Map<Integer, List<Integer>> map = new HashMap<>();
        //保存每个节点的入度
        int[] degree = new int[numCourses];
        //构建有向图：p[1] --> p[0] 前置条件是p[1]指向p[0]:完成p[1]，才能完成p[0]
        for(int[] p : prerequisites){
            //包含当前节点，更新当前节点指向的节点集
            if(map.containsKey(p[1])){
                map.get(p[1]).add(p[0]);
            }else{//不包含，新建
                List<Integer> L = new ArrayList<>();
                L.add(p[0]);
                map.put(p[1],L);
            }
            //更新p[0]的入度值
            degree[p[0]]++;
        }

        //保存所有入度为0的节点
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i < numCourses;i++){
            if(degree[i] == 0){
                ((LinkedList<Integer>) q).add(i);
            }
        }

        while (!q.isEmpty()){
            //取出入度为0的一个节点
            int t = q.poll();
            //t节点指向的节点集
            List<Integer> L = map.get(t);
            if(L == null){
                continue;
            }
            //将t有向链接的节点的入度-1
            for(int LL : L){
                degree[LL]--;
                if(degree[LL] == 0){
                    ((LinkedList<Integer>) q).add(LL);
                }
            }
        }

        //遍历完后还有入度不为0的节点，说明有向图中存在环，不满足拓扑排序
        for(int i : degree){
            if(i != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TopologicalSort solution = new TopologicalSort();
        int[][] input = {
                {1,0},
                {0,1}
        };
        int courseNum = 2;

        boolean res = true;
        res = solution.canFinish(courseNum,input);
        System.out.println(res);

    }

}
