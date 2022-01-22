package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {

    private HashMap<Integer, List<Node>> _networkNodes = new HashMap<>();

    public HashMap<Integer, List<Node>> getNetworkNodes(){
        return _networkNodes;
    }

    public Network(HashMap<Integer, List<Node>> allNetworkNodes){
        _networkNodes = allNetworkNodes;
        setNetworkLayers();
    }

    public void addNetworkNodeLayer(int layerPosition, List<Node> layerNodes){
        _networkNodes.put(layerPosition, layerNodes);
    }
    public void updateNetworkNodeLayer(int layerPositon, List<Node> newLayerNodes){
        _networkNodes.replace(layerPositon, newLayerNodes);
    }
    private void calculateWeights(){

    }
    private void setNetworkLayers(){
        for(Map.Entry<Integer, List<Node>> entry: getNetworkNodes().entrySet()){
            for(Node nodeInLayer: entry.getValue()){
                if(entry.getKey() == getNetworkNodes().size()){
                    return;
                }
                int counter = 0;
                for(Node node: getNetworkNodes().get(entry.getKey()+1)){
                    node.addScore(nodeInLayer.getWeights().get(counter) * nodeInLayer.getWeightScoreSum());
                    counter++;
                }
            }
        }
    }

    @Override
    public String toString() {
        String networkNodes = "";
        for(Map.Entry<Integer, List<Node>> entry: _networkNodes.entrySet()){
            networkNodes += "\n"+entry.getKey();
            for(Node node: entry.getValue()){
                networkNodes += node.toString();
            }
        }
        return networkNodes;
    }
}