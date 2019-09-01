package xh.leetcode.trie;

/**
 * @Author XH
 * @Description TODO 前缀树节点
 * @Date 2019/3/30 23:57
 */
public class TrieNode {
    //当前节点是否是一个单词的结尾
    boolean isWord;
    //当前节点指向的下一个节点集合，共26个字母，最大长度为26
    TrieNode[] next;

    public TrieNode() {
        next = new TrieNode[26];
    }
}
