package Models;

import java.util.*;

public class Node {
    Random rand = new Random();

    private NodeType _nodeType;
    private HashMap<Node, Float> _linkedNodes = new HashMap<>();
    private float _weightScoreSum;



    public void setNodeType(NodeType nodeType){
        this._nodeType = nodeType;
    }
    public NodeType getNodeType(){
        return _nodeType;
    }

    public HashMap<Node, Float> getLinkedNodes(){
        return _linkedNodes;
    }

    public void setWeightScoreSum(float scoreSum){
        this._weightScoreSum = scoreSum;
    }
    public float getWeightScoreSum(){
        return this._weightScoreSum;
    }




    public Node(){
        this(NodeType.OTHER, null);
    }
    public Node(NodeType type, HashMap<Node, Float> linkedNodes){
        this.setNodeType(type);
        if(this.getNodeType() == NodeType.INPUT){
            this.setWeightScoreSum(1);
        }
        if(linkedNodes != null) {
            for (Map.Entry<Node, Float> linkedNode : linkedNodes.entrySet()) {
                addLinkedNode(linkedNode.getKey(), linkedNode.getValue());
            }
        }

    }

    public void addLinkedNode(Node node, float weight){
        _linkedNodes.put(node, weight);
    }

    // randomizes all weights
    public void mutate(int epoch){
        for(HashMap.Entry<Node, Float> entry: _linkedNodes.entrySet()){
            float newWeight = 1 - rand.nextFloat() * 2;
            _linkedNodes.replace(entry.getKey(), newWeight);
        }
    }
    public void addScore(float weight){
        _weightScoreSum += weight;
    }

    // adds value to all lower nodes
    public boolean addValueToLowerNodes(){
        // break if there are no lower nodes => OUTPUT node
        if(_linkedNodes.isEmpty()){
            return false;
        }
        for(Map.Entry<Node, Float> linkedNode: _linkedNodes.entrySet()){
            linkedNode.getKey().addScore(linkedNode.getValue());
        }
        return true;
    }
    @Override
    public String toString(){
        addValueToLowerNodes();
        return this.getNodeType() + ": " + getWeightScoreSum() + _linkedNodes.values();
    }
}