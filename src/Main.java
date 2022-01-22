import Models.Network;
import Models.NodeType;
import Models.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Network network = new Network();
        Random rand = new Random();

        Node n1 = new Node(NodeType.INPUT, null);
        Node n2 = new Node(NodeType.INPUT, null);
        Node n3 = new Node(NodeType.MIDDLE, null);
        Node n4 = new Node(NodeType.MIDDLE, null);
        Node n5 = new Node(NodeType.MIDDLE, null);
        Node n6 = new Node(NodeType.MIDDLE, null);
        Node n7 = new Node(NodeType.MIDDLE, null);
        Node n8 = new Node(NodeType.OUTPUT, null);
        Node n9 = new Node(NodeType.OUTPUT, null);

        List<Node> middleNodes = new ArrayList<>();
        List<Node> inputNodes = new ArrayList<>();

        middleNodes.add(n3);
        middleNodes.add(n4);
        middleNodes.add(n5);
        middleNodes.add(n6);
        middleNodes.add(n7);

        for(Node node: middleNodes){
            node.addLinkedNode(n8, 1- rand.nextFloat() * 2);
            node.addLinkedNode(n9, 1- rand.nextFloat() * 2);

        }

        inputNodes.add(n1);
        inputNodes.add(n2);

        for(Node node: inputNodes){
            node.addLinkedNode(n3, 1- rand.nextFloat() * 2);
            node.addLinkedNode(n4, 1- rand.nextFloat() * 2);
            node.addLinkedNode(n5, 1- rand.nextFloat() * 2);
            node.addLinkedNode(n6, 1- rand.nextFloat() * 2);
            node.addLinkedNode(n7, 1- rand.nextFloat() * 2);
        }

        network.addInputNodes(n1);
        network.addInputNodes(n2);


        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);
        System.out.println(n6);
        System.out.println(n7);
        System.out.println(n8);
        System.out.println(n9);
    }
}
