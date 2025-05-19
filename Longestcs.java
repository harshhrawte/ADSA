public class Longestcs {

    public static String[] lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        printDPTable(dp, X, Y);
        int lcsLength = dp[m][n];
        StringBuilder lcsString = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcsString.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        lcsString.reverse();
        return new String[]{String.valueOf(lcsLength), lcsString.toString()};
    }

    public static void printDPTable(int[][] dp, String X, String Y) {
        System.out.println("DP Table:");
        System.out.print("    ");
        for (char c : Y.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < dp.length; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(X.charAt(i - 1) + " ");
            }
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) {
                    System.out.print(dp[i][j] + " ");
                } else {
                    String arrow = "";
                    if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                        arrow = "\\ ";
                    } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                        arrow = "^ ";
                    } else {
                        arrow = "< ";
                    }
                    System.out.print(dp[i][j] + arrow);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String X = "AGGTAB";
        String Y = "GXTXAYB";
        String[] result = lcs(X, Y);
        System.out.println("Length of LCS: " + result[0]);
        System.out.println("LCS: " + result[1]);
    }
}
