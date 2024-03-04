package org.tfoc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (root != null) {
            sb.append(root.val);
            LinkedList<TreeNode> nextNodes = new LinkedList<>();
            nextNodes.add(root.left);
            nextNodes.add(root.right);
            LinkedList<TreeNode> nextLevelNodes = new LinkedList<>();
            while (!nextNodes.isEmpty()) {
                TreeNode node = nextNodes.pop();
                if (node != null) {
                    sb.append(",").append(node.val);
                    nextLevelNodes.add(node.left);
                    nextLevelNodes.add(node.right);
                } else {
                    sb.append(",null");
                }
                if (nextNodes.isEmpty() && !nextLevelNodes.stream().allMatch(n -> n==null)) {
                    nextNodes.addAll(nextLevelNodes.stream().collect(Collectors.toList()));
                    nextLevelNodes.clear();
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        data = data.replace("[", "").replace("]", "");
        String[] values = data.isBlank()? new String[0] : data.split(",");
        TreeNode root = null;
        int i = 0;
        if (values.length > 0) {
            root = new TreeNode(Integer.valueOf(values[i]));
            i++;
            List<TreeNode> currentLevelNodes = new ArrayList<>();
            List<TreeNode> nextLevelNodes = new ArrayList<>();
            currentLevelNodes.add(root);
            while (i < values.length) {
                if (currentLevelNodes.isEmpty()) {
                    currentLevelNodes = nextLevelNodes.stream().collect(Collectors.toList());
                    nextLevelNodes.clear();
                }
                TreeNode currentNode = currentLevelNodes.remove(0);
                if (values[i].equals("null"))
                    currentNode.left = null;
                else {
                    currentNode.left = new TreeNode(Integer.parseInt(values[i]));
                    nextLevelNodes.add(currentNode.left);
                }
                i++;
                if (values[i].equals("null"))
                    currentNode.right = null;
                else {
                    currentNode.right = new TreeNode(Integer.parseInt(values[i]));
                    nextLevelNodes.add(currentNode.right);
                }
                i++;
            }
        }
        return root;
    }

}