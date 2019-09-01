package xh.leetcode.string;

/**
 * @Author XH
 * @Description TODO 给定两个字符串X，Y，求：1、最长公共子序列 P210
 * 【动态规划】状态转移方程
 * 1、最长公共子序列：若xm = yn,LCS[Xm,Yn]=LCS(Xm-1,Yn-1)+xm;
 * 否则，LCS[Xm,Yn]=max{LCS[Xm,Yn-1],LCS[Xm+1,Yn]}
 * C[i,j]记录Xi，Yj的最长公共子序列的长度，则：
 * 当i=0 或 j=0时，C[i,j]=0;
 * 当xi=yi时，C[i,j]=C[i-1,j-1]+1;
 * 当xi！=yi是，C[i,j]=max{C[i-1,j],C[i,j-1]};
 *
 * TODO 给定两个字符串X，Y，求：2、最长公共子串（要求必须连续） P213S
 * 【动态规划】状态转移方程
 *  2、最长公共子串：若xm = yn,LCS[Xm,Yn]=LCS(Xm-1,Yn-1)+xm;
 * 否则，LCS[Xm,Yn]=空，即最长公共子串不存在（分别以xm和yn为串尾）
 * C[i,j]记录以xi，yj结尾的最长公共子串的长度，则：
 * 当i=0 或 j=0时，C[i,j]=0;
 * 当xi=yi时，C[i,j]=C[i-1,j-1]+1;
 * 当xi！=yi是，C[i,j]=0;
 * @Date 2019/3/3 17:26
 */
public class LCS {
    //最长公共子序列
    public String longestCommonSequence(String s1,String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        //注意：此处创建的二维数组的长度是（len1+1）*（len2+1）
        int[][] c = new int[len1+1][len2+1];
        //j=0,c[i,j]=0
        for(int i = 0;i <= len1;i++){
            c[i][0] = 0;
        }
        //i=0,c[i,j]=0
        for(int j = 0;j <= len2;j++){
            c[0][j] = 0;
        }

        //i>0,j>0
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for(int i = 1;i <= len1;i++){
            for(int j = 1;j <= len2;j++){
                if(c1[i-1] == c2[j-1]){//对于c1，c2，i、j的索引范围为：i-1，j-1
                    c[i][j] = c[i-1][j-1] + 1;
                }else{
                    c[i][j] = Math.max(c[i-1][j],c[i][j-1]);
                }
            }
        }

        //从c[i,j]中反向得出最长公共子序列,c[len1][len2]表示s1和s2最长公共子序列的长度
        char[] result = new char[c[len1][len2]];
        System.out.println(c[len1][len2]);
        int i = len1-1;
        int j = len2-1;
        int index = result.length - 1;
        while(i >= 0 && j >= 0){
            //移动方向：左上
            if(c1[i] == c2[j]){
                result[index--]=c1[i];
                i--;
                j--;
            }else{
                //移动方向:向上
                //注意c与c1、c2中索引的对于关系，c[i][j]对应c1[i-1]、c2[j-1]
                if(c[i][j+1] > c[i+1][j]){
                   i--;
                }else{
                    //移动方向：向左
                    j--;
                }
            }
        }

        return String.valueOf(result);
    }

    /**
     * 最长公共子串，子串（必须连续）是子序列的特殊情况
     * @param s1
     * @param s2
     * @return
     */
    public String longetsCommonSubstring(String s1,String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        //注意：此处创建的二维数组的长度是（len1+1）*（len2+1）
        int[][] c = new int[len1+1][len2+1];
        //j=0,c[i,j]=0
        for(int i = 0;i <= len1;i++){
            c[i][0] = 0;
        }
        //i=0,c[i,j]=0
        for(int j = 0;j <= len2;j++){
            c[0][j] = 0;
        }

        //i>0,j>0
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for(int i = 1;i <= len1;i++){
            for(int j = 1;j <= len2;j++){
                if(c1[i-1] == c2[j-1]){//对于c1，c2，i、j的索引范围为：i-1，j-1
                    c[i][j] = c[i-1][j-1] + 1;
                }else{
                    c[i][j] = 0;//注意：最长公共子序列和最长公共子串，只有这里不同！！
                }
            }
        }

        //遍历c[][],得出最长公共子串的长度，同时得知了最长公共子串的尾字符i
        int end = 0;
        int max = 0;
        for(int i = 0;i <= len1;i++){
            for(int j = 0;j <= len2;j++){
                if(c[i][j] > max){
                    max = c[i][j];
                    end = i;
                }
            }
        }

        return s1.substring(end-max,end);//substringf(i,j)=>[i,j-1]
        //这里s1中索引比c中索引小1，所以从c中遍历获得的索引end不用+1
    }

    public static void main(String[] args) {
        String s1 = "1AB2345CD";
        String s2 = "12345EF";
        LCS lcs = new LCS();
        //String result = lcs.longestCommonSequence(s1,s2);
        String result = lcs.longetsCommonSubstring(s1,s2);
        System.out.println(result);
    }
}
