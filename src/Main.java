import Models.Network;
import Models.NodeType;
import Models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        HashMap<Integer, List<Node>> net = new HashMap<>();

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
        List<Node> outputNodes = new ArrayList<>();
/*
        n1.addWeights((float)0.1);
        n1.addWeights((float)0.3);
        n1.addWeights((float)0.5);
        n1.addWeights((float)0.7);
        n1.addWeights((float)0.9);

        n2.addWeights((float)1);
        n2.addWeights((float)0.8);
        n2.addWeights((float)0.6);
        n2.addWeights((float)0.4);
        n2.addWeights((float)0.2);

        n3.addWeights((float)0.4);
        n3.addWeights((float)0.6);

        n4.addWeights((float)0.4);
        n4.addWeights((float)0.6);

        n5.addWeights((float)0.4);
        n5.addWeights((float)0.6);

        n6.addWeights((float)0.4);
        n6.addWeights((float)0.6);

        n7.addWeights((float)0.4);
        n7.addWeights((float)0.6);
*/
        middleNodes.add(n3);
        middleNodes.add(n4);
        middleNodes.add(n5);
        middleNodes.add(n6);
        middleNodes.add(n7);

        inputNodes.add(n1);
        inputNodes.add(n2);

        outputNodes.add(n8);
        outputNodes.add(n9);

        net.put(1, inputNodes);
        net.put(2, middleNodes);
        net.put(3, outputNodes);

        Network network = new Network(net);
        network.randomizeWeights();

        System.out.println(network);
        System.out.println(network.maxOutput());
    }
}
