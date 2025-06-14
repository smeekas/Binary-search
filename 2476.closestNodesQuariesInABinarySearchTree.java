import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * 
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        // return bruteforce(root, queries);
        return optimized(root, queries);
    }

    List<List<Integer>> optimized(TreeNode root, List<Integer> qrs) {
        // inorder traversal to get all nodes in sorted manner!
        /**
         * O(n+logN*n)
         * n for inorder
         * logN for each n query
         * 
         */
        List<Integer> nodes = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        inorder(root, nodes);
        for (Integer q : qrs) {
            List<Integer> local = new ArrayList<>();
            local.add(minBs(nodes, q));
            local.add(maxBs(nodes, q));
            ans.add(local);
        }
        return ans;
    }

    int minBs(List<Integer> nodes, int q) {
        int ans = -1;
        int l = 0, r = nodes.size() - 1;
        // find tightest min
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = nodes.get(mid);
            if (midEle == q) {
                ans = midEle;
                l = mid + 1;
            } else if (midEle < q) {
                ans = midEle;
                l = mid + 1;
            } else {
                // midEle > q
                r = mid - 1;
            }
        }
        return ans;
    }

    int maxBs(List<Integer> nodes, int q) {
        int ans = -1;
        int l = 0, r = nodes.size() - 1;
        // find tightest max
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = nodes.get(mid);
            if (midEle == q) {
                ans = midEle;
                r = mid - 1;
            } else if (midEle < q) {
                l = mid + 1;
            } else {
                // midEle > q
                ans = midEle;
                r = mid - 1;
            }
        }
        return ans;
    }

    void inorder(TreeNode node, List<Integer> ans) {
        if (node.left != null)
            inorder(node.left, ans);
        ans.add(node.val);
        if (node.right != null)
            inorder(node.right, ans);
    }

    // O(N*N) Q=N
    List<List<Integer>> bruteforce(TreeNode root, List<Integer> qrs) {
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer q : qrs) {
            List<Integer> local = new ArrayList<>();
            local.add(getMin(q, root));
            local.add(getMax(q, root));
            ans.add(local);
        }
        return ans;
    }

    // smallest value larger then q
    int getMax(int q, TreeNode node) {
        int ans = -1;
        TreeNode local = node;
        while (local != null) {
            if (local.val == q) {
                ans = q;
                local = local.left;
            } else if (local.val < q) {
                local = local.right;
            } else {
                // local.val > q
                ans = local.val;
                local = local.left;
            }
        }
        return ans;
    }

    // largest value smaller then q
    int getMin(int q, TreeNode node) {
        int ans = -1;
        TreeNode local = node;
        while (local != null) {
            if (local.val == q) {
                // if ans is same as q store as possible answer
                // right side have larger value
                // possibility of duplicate number
                // search on right for tighter bound
                ans = q;
                local = local.right;
            } else if (local.val > q) {
                // number is larger then q
                // we want smallest largest value/
                // go left since this is BST we have smaller value on left
                local = local.left;
            } else {
                // local.val < q
                // value is smaller then q
                // possible answer
                // still search on right for tighter bound
                // right side have larger value
                ans = local.val;
                local = local.right;
            }
        }
        return ans;
    }

}