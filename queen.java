import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean isSafe(int row, int col, List<String> board, int n) {
        // Check upper diagonal
        int dupRow = row;
        int dupCol = col;

        while (row >= 0 && col >= 0) {
            if (board.get(row).charAt(col) == 'Q') {
                return false;
            }
            row--;
            col--;
        }

        col = dupCol;
        row = dupRow;
        // Check the left side
        while (col >= 0) {
            if (board.get(row).charAt(col) == 'Q') {
                return false;
            }
            col--;
        }

        row = dupRow;
        col = dupCol;
        // Check lower diagonal
        while (row < n && col >= 0) {
            if (board.get(row).charAt(col) == 'Q') {
                return false;
            }
            row++;
            col--;
        }
        return true;
    }

    public void solve(int col, List<String> board, List<List<String>> ans, int n) {
        if (col == n) {
            ans.add(new ArrayList<>(board));
            return;
        }
        for (int row = 0; row < n; row++) {
            if (isSafe(row, col, board, n)) {
                char[] rowArr = board.get(row).toCharArray();
                rowArr[col] = 'Q';
                board.set(row, new String(rowArr));
                solve(col + 1, board, ans, n);
                rowArr[col] = '.'; // backtrack
                board.set(row, new String(rowArr));
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        List<String> board = new ArrayList<>();
        StringBuilder s = new StringBuilder(".".repeat(n));
        for (int i = 0; i < n; i++) {
            board.add(s.toString());
        }
        solve(0, board, ans, n);
        return ans;
    }

    public static void main(String[] args) {
        int n = 4; // we are taking 4x4 grid and 4 queens
        Solution obj = new Solution();
        List<List<String>> ans = obj.solveNQueens(n);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println("Arrangement " + (i + 1));
            for (String row : ans.get(i)) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
