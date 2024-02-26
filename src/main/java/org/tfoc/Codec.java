package org.tfoc;

import java.util.LinkedList;
import java.util.List;

/**
 * Your Codec object will be instantiated and called as such:
 * Codec serializer = new Codec();
 * Codec deserializer = new Codec();
 * TreeNode ans = deserializer.deserialize( serializer.serialize(root) );
 */
public class Codec {

    /**
     * Encodes a tree to a single string.
     */
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder("[");
        if(root != null) {
            sb.append(root.val);
            LinkedList<TreeNode> nextNodes = new LinkedList<>();
            if(root.left != null)
                nextNodes.add(root.left);
            if(root.right!=null)
                nextNodes.add(root.right);
            {
                LinkedList<TreeNode> nextLevelNodes = new LinkedList<>();
                while(!nextNodes.isEmpty()) {
                    TreeNode node = nextNodes.pop();
                    if(node!=null) {
                        sb.append(node.val).append(",");
                        nextLevelNodes.add(node.left);
                        nextLevelNodes.add(node.right);
                    } else {
                        sb.append("null,");
                    }
                }
                if(!nextLevelNodes.isEmpty())
                    nextNodes.addAll(nextLevelNodes);
                nextLevelNodes.clear();
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        String[] values = data.replace("[","").replace("]","").split(",");
        TreeNode root = null;
        int i = 0;
        if(values.length > 0) {
            root = new TreeNode(Integer.valueOf(values[i]));
        }
        return root;
    }

}