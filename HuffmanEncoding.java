import java.util.PriorityQueue;

class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char c, int f) {
        ch = c;
        freq = f;
        left = right = null;
    }
}

class Huffman {
    // Print Huffman codes (tree traversal)
    static void printCodes(Node root, String code) {
        if (root == null) return;

        // If leaf node, print character + code
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {

        // Example characters and frequencies
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freq   = {  5,   9,  12,  13,  16,  45 };

        // Min-heap (priority queue)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        // Step 1: Create leaf nodes
        for (int i = 0; i < chars.length; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }

        // Step 2: Build Huffman Tree (Greedy)
        while (pq.size() > 1) {
            Node x = pq.poll();  // smallest
            Node y = pq.poll();  // second smallest

            Node sum = new Node('-', x.freq + y.freq);
            sum.left = x;
            sum.right = y;

            pq.add(sum);
        }

        // Root of Huffman Tree
        Node root = pq.poll();

        System.out.println("Huffman Codes:");
        printCodes(root, "");
    }
}