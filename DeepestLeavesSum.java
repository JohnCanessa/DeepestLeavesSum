import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * LeetCode 1302. Deepest Leaves Sum
 * https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {


    /**
     * Sum values of leaf nodes.
     * Recursive call performs in order traversal.
     * !!!! NOT PART OF THE SOLUTION !!!!
     */
    static public int sumLeafNodes(TreeNode root) {

        // **** sanity checks ****
        if (root == null) return 0;

        // **** traverse left subtree ****
        int sum = sumLeafNodes(root.left);

        // **** determine if this is a leaf node ****
        if (root.left == null && root.right == null)
            sum += root.val;

        // **** traverse right subtree ****
        sum += sumLeafNodes(root.right);

        // **** return sum of leaf nodes ****
        return sum;
    }


    /**
     * Determine the maximum depth of a binary tree.
     * Recursive call.
     */
    static public int maxDepth(TreeNode root) {

        // **** base case ****
        if (root == null) return 0;

        // **** initialization ****
        int depth = 0;

        // **** visit left node ****
        depth = Math.max(depth, maxDepth(root.left) + 1);

        // **** visit right node ****
        return Math.max(depth, maxDepth(root.right) + 1);
    }


    /**
     * Given the root of a binary tree, 
     * return the sum of values of its deepest leaves.
     * 
     * Execution: O(n) - Space: (1)
     * 
     * Runtime: 5 ms, faster than 55.24% of Java online submissions.
     * Memory Usage: 51.6 MB, less than 25.11% of Java online submissions.
     * 
     * 35 / 35 test cases passed.
     * Status: Accepted
     * Runtime: 5 ms
     * Memory Usage: 51.6 MB
     */
    static public int deepestLeavesSum0(TreeNode root) {
        
        // **** initialization *****
        int[] d = new int[1];

        // **** start recursion - O(n) ****
        return deepestLeavesSum(root, maxDepth(root), d);
    }


    /**
     * Auxiliary function.
     * Recursive function.
     * Inorder traversal.
     */
    static private int deepestLeavesSum(TreeNode root, int md, int[] d) {

        // **** base condition ****
        if (root == null) return 0;

        // **** increment depth ****
        d[0]++;

        // **** traverse left subtree ****
        int sum = deepestLeavesSum(root.left, md, d);

        // **** visiting deepest leaf node ****
        if (root.left == null && root.right == null && d[0] == md) {

            // **** increment sum ****
            sum += root.val;

            // ???? ????
            System.out.println("<<< sum: " + sum + " d: " + d[0]);
        }

        // **** traverse right subtree ****
        sum += deepestLeavesSum(root.right, md, d);

        // **** decrement depth ****
        d[0]--;

        // **** return sum ****
        return sum;
    }


    /**
     * Given the root of a binary tree, 
     * return the sum of values of its deepest leaves.
     * 
     * Execution: O(n) - Space: (1)
     * 
     * Runtime: 2 ms, faster than 82.96% of Java online submissions.
     * Memory Usage: 51.8 MB, less than 24.81% of Java online submissions.
     * 
     * 35 / 35 test cases passed.
     * Status: Accepted
     * Runtime: 2 ms
     * Memory Usage: 51.8 MB
     */
    static public int deepestLeavesSum(TreeNode root) {

        // **** initialization ****
        int[] md  = new int[1];
        int[] sum = new int[1];

        // **** start recursion - O(n) ****
        deepestLeavesSum(root, 1, md, sum);

        // **** return sum ****
        return sum[0];
    }


    /**
     * Recursive call
     */
    static public void deepestLeavesSum(TreeNode root, int d, int[] maxDepth, int[] sum) {

        // **** base case ****
        if (root == null) return;

        // ???? ????
        System.out.println("<<< root: " + root.val + " d: " + d + " maxDepth: " + maxDepth[0]);

        // **** update max depth and sum ****
        if (d > maxDepth[0]) {
            maxDepth[0] = d;

            // **** start sum ****
            sum[0]      = root.val;
        } else if (d == maxDepth[0]) {

            // **** add to sum ****
            sum[0]      += root.val;
        }

        // ???? ????
        System.out.println("<<<         d: " + d + " maxDepth: " + maxDepth[0] + " sum: " + sum[0]);

        // **** traverse left subtree ****
        deepestLeavesSum(root.left, d + 1, maxDepth, sum);

        // **** traverse right subtree ****
        deepestLeavesSum(root.right, d + 1, maxDepth, sum);
    }


    /**
     * Test scaffold.
     * !!!! NOT PART OF THE SOLUTION !!!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read and populate string[] for binary tree ****
        String[] strArr = br.readLine().trim().split(",");

        // ***** close the buffered reader ****
        br.close();

        // ????? ????
        System.out.println("main <<<            strArr: " + Arrays.toString(strArr));

        // **** generate Integer[] to create and populate binary tree ****
        int len         = strArr.length;
        Integer[] arr   = new Integer[len];
        for (int i = 0; i < len; i++)
            arr[i] = strArr[i].equals("null") ? null : Integer.parseInt(strArr[i]);

        // ????? ????
        System.out.println("main <<<               arr: " + Arrays.toString(arr));

        // **** create and populate per level the binary tree ****
        BST bt  = new BST();
        bt.root = bt.populate(arr);

        // ???? traverse binary tree ????
        System.out.println("main <<<           inOrder: " + bt.inOrder(bt.root));

        // ???? determine maximum depth of the binary tree ????
        System.out.println("main <<<          maxDepth: " + bt.maxDepth(bt.root));

        // ???? sum of all leave nodes (not looking for this) ????
        System.out.println("main <<<      sumLeafNodes: " + sumLeafNodes(bt.root));

        // **** call function of interest and display result ****
        System.out.println("main <<< deepestLeavesSum0: " + deepestLeavesSum0(bt.root));

        // **** call function of interest and display result ****
        System.out.println("main <<<  deepestLeavesSum: " + deepestLeavesSum(bt.root));
    }

}