package Models;

import java.util.*;

public class Nodes {
    Random rand = new Random();

    private String _name;
    private HashMap<Nodes, Float> _linkedNodes = new HashMap<>();
    private float _weightScoreSum;


    public void setName(String name){
        if(name != "" && name != null){
            this._name = name;
        }
    }
    public String getName(){
        return _name;
    }

    public HashMap<Nodes, Float> getLinkedNodes(){
        return _linkedNodes;
    }

    public void setWeightScoreSum(float scoreSum){
        this._weightScoreSum = scoreSum;
    }
    public float getWeightScoreSum(){
        return this._weightScoreSum;
    }




    public Nodes(){
        this("", null);
    }
    public Nodes(String name, HashMap<Nodes, Float> linkedNodes){
        this.setName(name);
        for(Map.Entry<Nodes, Float> linkedNode: linkedNodes.entrySet()){
            addLinkedNode(linkedNode);
        }
    }

    private void addLinkedNode(HashMap.Entry<Nodes, Float> linkedNode){
        _linkedNodes.put(linkedNode.getKey(), linkedNode.getValue());
    }

    public void mutate(int epoch){
        for(HashMap.Entry<Nodes, Float> entry: _linkedNodes.entrySet()){
            float newWeight = 1 - rand.nextFloat() * 2;
            _linkedNodes.replace(entry.getKey(), newWeight);
        }
    }
    public boolean addValueToLowerNodes(){
        if(_linkedNodes.isEmpty()){
            return false;
        }
        for(Map.Entry<Nodes, Float> linkedNode: _linkedNodes.entrySet()){
            Nodes newNode = linkedNode.getKey();
            newNode.setWeightScoreSum(getWeightScoreSum() * linkedNode.getValue());
            _linkedNodes.remove(linkedNode.getKey());
            _linkedNodes.put(newNode, linkedNode.getValue());

        }
        return true;
    }
    @Override
    public String toString(){
        return this.getName() + ":\n" + getLinkedNodes();
    }
}