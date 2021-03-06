package Models;

import java.util.*;

public class Node {
    Random rand = new Random();

    private int _id;
    private NodeType _nodeType;
    private List<Float> _weights = new ArrayList<>();
    private float _weightScoreSum;

    public void setId(int id){
        if(id >= 0){
            this._id = id;
        }
    }
    public int getId(){
        return _id;
    }

    public void setNodeType(NodeType nodeType){
        this._nodeType = nodeType;
    }
    public NodeType getNodeType(){
        return _nodeType;
    }

    public List<Float> getWeights(){
        return _weights;
    }
    public void setWeights(List<Float> weights){
        if(weights == null){
            _weights.clear();
            return;
        }
        for(Float f: weights){
            _weights.add(f);
        }
    }

    public void setWeightScoreSum(float scoreSum){
        this._weightScoreSum = scoreSum;
    }
    public float getWeightScoreSum(){
        return this._weightScoreSum;
    }



    public Node(){
        this(0, NodeType.OTHER, null);
    }
    public Node(int id, NodeType type, List<Float> weights){
        this.setNodeType(type);
        this.setId(id);
        if(this.getNodeType() == NodeType.INPUT){
            this.setWeightScoreSum(1);
        }
        setWeights(weights);

    }

    public void addWeights(Float weightToAdd){
        _weights.add(weightToAdd);
    }

    // randomizes all weights
    public void mutate(int epoch){
        List<Float> newWeights = new ArrayList<>();
        for(Float f: _weights){
            float newWeight = 1 - rand.nextFloat() * 2;
            newWeights.add(newWeight);
        }
        _weights.clear();
        setWeights(newWeights);
    }
    protected void addScore(float weight){
        _weightScoreSum += weight;
    }

    // adds value to all lower nodes

    @Override
    public String toString(){
        return this.getId() + " " +this.getNodeType() + ": " + getWeightScoreSum() + " " + this.getWeights();
    }
}