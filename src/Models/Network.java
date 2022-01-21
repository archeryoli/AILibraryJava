package Models;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> _inputNodes = new ArrayList<>();

    private int score;

    public void addInputNodes(Node node){
        _inputNodes.add(node);
    }
    public List<Node> getInputNodes(){
        return _inputNodes;
    }

    @Override
    public String toString(){
        String k = "";
        for(Node node: _inputNodes){
            k += node + "\n";
        }
        return k;
    }

}
