package xh.leetcode.trie;

/**
 * @Author XH
 * @Description TODO LeetCode 208 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * @Date 2019/3/28 14:34
 */
public class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word == null){
            return;
        }

        char[] chs = word.toCharArray();
        TrieNode cur = root;
        for(char c : chs){
            int index = c - 'a';
            //当前字母不存在===》新建前缀节点，加入前缀树
            if(cur.next[index] == null){
                cur.next[index] = new TrieNode();
            }
            //移动当前节点
            cur = cur.next[index];
        }
        //字符串遍历结束时，最后一个前缀节点处，标记该节点为一个单词的结尾
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word == null){
            return false;
        }

        char[] chs = word.toCharArray();
        TrieNode cur = root;
        for(char c : chs){
            int index = c - 'a';
            //当前字符不在前缀树中，直接返回false
            if(cur.next[index] == null){
                return false;
            }
            //移动前缀节点，继续判断下一个字符是否存在
            cur = cur.next[index];
        }
        //遍历完给定字符串后，定位到的前缀节点处标记为某个单词的结尾，则返回true；否则，返回false
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix == null){
            return false;
        }

        char[] chs = prefix.toCharArray();
        TrieNode cur = root;
        for(char c : chs){
            int index = c - 'a';
            if(cur.next[index] == null){
                return false;
            }
            cur = cur.next[index];
        }
        return true;
    }

}
