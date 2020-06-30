package xh.leetcode.string;

/**
 * @Author XH P491
 * @Description TODO 难度系数：5颗星 KMP算法：给定字符串s和m，从s中找出m第一次出现的位置 P491
 * 关键：求出m的next数组
 * next[]:找出模式串m中当前字符m[j]的最长前缀和后缀
 * @Date 2019/3/2 15:59
 */
public class KMP {

    /**
     * 给定字符串s和m，求m在s中第一次出现的位置，已知next[m]
     * 关键点：时间复杂度为T(n)，s不能回退，只能前移；
     * 当s[i]!=m[j]时，已知m中next[j],直接比较s[i]与m[next[j]]是否相等，不相等如上继续回退m，但s保持不变
     * @param s
     * @param m
     * @return
     */
    public int findIndexOf(String s, String m) {
        if( s == null || m == null || s.length() < 1 || s.length() < m.length()){
            return -1;
        }

        int si = 0;
        int mi = 0;
        char[] sc = s.toCharArray();
        char[] mc = m.toCharArray();
        int[] next = findNext(mc);
        while(si < sc.length && mi < mc.length){
            if(sc[si] == mc[mi]){
                si++;
                mi++;
            }else if(next[mi] > -1){
                //递归
                mi = next[mi];
            }else{
                si++;
            }
        }

        //mi滑动到mc的最后，说明从sc中匹配到了mc;否则没有
        return mi == mc.length ? (si - mi) : -1;
    }

    /**
     * 给定字符数组sm，找出每个字符对应的最长前缀串==后缀串的长度。
     * eg:ababa,sm[4]=a,next[4]=2，即其最大匹配串为ab
     * 难点【递归】：已知next[0,……,i-1],求next[i]；next[0]=-1,next[1]=0;
     * 注意:next[i]=sm[0……i-1]中最大前缀串和后缀串，前缀串不包括sm[i-1]，后缀串不包括sm[0]
     * @param sm
     * @return
     */
    public int[] findNext(char[] sm){
        if(sm.length <= 1){
            return new int[]{-1};
        }

        int[] next = new int[sm.length];
        next[0] = -1;
        next[1] = 0;//最大后缀串不能包含第一个字符，此时只有空串，长度为0
        int pos = 2;//从sm中index=2开始求
        int cn = next[pos - 1];//最开始，指向next[pos-1]=next[1]=0
        while(pos < sm.length){
            if(sm[pos - 1] == sm[cn]){
                next[pos] = cn + 1;//next[pos] = next[pos-1]+1
                pos++;
                cn++;
            }else if(cn > 0){
                //递归
                cn = next[cn];
            }else{//cn=-1，无匹配串
                next[pos] = 0;
                pos++;
            }
        }

//        for(int i : next){
//            System.out.println(i);
//        }
        return next;
    }

    public static void main(String[] args) {
        String s = "ababaababb";
        String m = "ababb";
        KMP kmp = new KMP();
        int result = kmp.findIndexOf(s,m);
        System.out.println(result);
    }

}
