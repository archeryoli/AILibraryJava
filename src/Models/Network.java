package Models;

import java.util.*;

public class Network {

    private HashMap<Integer, List<Node>> _networkNodes = new HashMap<>();
    Random rand = new Random();

    public HashMap<Integer, List<Node>> getNetworkNodes(){
        return _networkNodes;
    }

    public Network(HashMap<Integer, List<Node>> allNetworkNodes){
        for(Map.Entry<Integer, List<Node>> networkLayer: allNetworkNodes.entrySet()){
            addNetworkNodeLayer(networkLayer.getKey(), networkLayer.getValue());
        }
    }

    public void addNetworkNodeLayer(int layerPosition, List<Node> layerNodes){
        _networkNodes.put(layerPosition, layerNodes);
    }
    public void updateNetworkNodeLayer(int layerPosition, List<Node> newLayerNodes){
        _networkNodes.replace(layerPosition, newLayerNodes);
    }
    private void calculateWeights(){
        for(Map.Entry<Integer, List<Node>> entry: getNetworkNodes().entrySet()){
            for(Node nodeInLayer: entry.getValue()){
                if(entry.getKey() == getNetworkNodes().size() || nodeInLayer.getWeights().size() == 0){
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

    public void randomizeWeights(){
        for(Map.Entry<Integer, List<Node>> entry: _networkNodes.entrySet()){
            for(Node node: entry.getValue()){
                node.setWeights(null);
                if(entry.getKey() == getNetworkNodes().size()){
                    return;
                }
                for(Node nodeInNextLayer: _networkNodes.get(entry.getKey() +1)){
                    node.addWeights(1 - (2 * rand.nextFloat()));
                }
            }

        }
    }
    public int maxOutput(){
        int maxID = 0;
        int counter = 0;
        float max = 0;
        for(Node outputNode: getNetworkNodes().get(getNetworkNodes().size())){
            if(max < outputNode.getWeightScoreSum() || counter == 0){
                max = outputNode.getWeightScoreSum();
                maxID = counter;
            }
            counter++;
        }
        return maxID;
    }
    public List<Node> getMaxOutputPath(){
        int idOfMaxOutput = maxOutput();
        List<Node> path = new ArrayList<>();
        path.add(_networkNodes.get(getNetworkNodes().size()).get(idOfMaxOutput));
        int oldMaxId = idOfMaxOutput;
        for(int i = getNetworkNodes().size()-1; i >= 1; i--){
            float max = 0;
            int maxId = 0;
            int counter = 0;
            for(Node node: _networkNodes.get(i)){
                if(counter == 0 || max < node.getWeights().get(oldMaxId)){
                    max = node.getWeights().get(oldMaxId);
                    maxId = counter;
                }
                counter++;
            }
            oldMaxId = maxId;
            path.add(_networkNodes.get(i).get(maxId));
        }
        return path;
    }

    @Override
    public String toString() {
        calculateWeights();

        String networkNodes = "";
        for(Map.Entry<Integer, List<Node>> entry: _networkNodes.entrySet()){
            networkNodes += "\n"+entry.getKey();
            for(Node node: entry.getValue()){
                networkNodes += node.toString() + node.getWeights();
            }
        }
        return networkNodes;
    }
}