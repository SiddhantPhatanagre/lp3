public class Knapsack01 {

    public static int knapSack(int W, int[] wt, int[] val, int n) {

        int[][] dp = new int[n + 1][W + 1];

        // Filling DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Track selected items
        int w = W;
        int[] taken = new int[n];

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                taken[i - 1] = 1;
                w -= wt[i - 1];
            } else {
                taken[i - 1] = 0;
            }
        }

        // Print simple output
        System.out.println("Items chosen:");
        System.out.println("Weight   Value   Taken");

        for (int i = 0; i < n; i++) {
            System.out.printf("%-8d %-8d %s\n",
                    wt[i], val[i], (taken[i] == 1 ? "Yes" : "No"));
        }

        return dp[n][W];
    }

    public static void main(String[] args) {

        int[] val = { 60, 100, 120 };
        int[] wt  = { 10, 20, 30 };
        int W = 50;

        int maxValue = knapSack(W, wt, val, val.length);

        System.out.println("\nMaximum value in 0/1 Knapsack = " + maxValue);
    }
}