package Models;

import java.util.*;

public class Network {

    private List<List<Node>> _networkNodes = new ArrayList<>();
    Random rand = new Random();

    public List<List<Node>> getNetworkNodes(){
        return _networkNodes;
    }

    public Network(List<List<Node>> allNetworkNodes){
        for(List<Node> networkLayer: allNetworkNodes){
            addNetworkNodeLayer(networkLayer);
        }
    }

    public void addNetworkNodeLayer(List<Node> layerNodes){
        _networkNodes.add(layerNodes);
    }

    // TODO find a way to print network twice with same result
    private void calculateWeights(){
        int layer = 0;
        clearNodeWeights();
        for(List<Node> networkLayer: getNetworkNodes()){
            for(Node nodeInLayer: networkLayer){

                if(layer == getNetworkNodes().size() || nodeInLayer.getWeights().size() == 0){
                    return;
                }

                int counter = 0;
                for(Node node: getNetworkNodes().get(layer+1)){

                    node.addScore(nodeInLayer.getWeights().get(counter) * nodeInLayer.getWeightScoreSum());
                    counter++;
                }
            }
            layer++;
        }
    }
    private void clearNodeWeights(){
        int layer = 0;
        for(List<Node> networkLayer: getNetworkNodes()){
            for(Node nodeInLayer: networkLayer){
                if(nodeInLayer.getNodeType() != NodeType.INPUT){
                    nodeInLayer.setWeightScoreSum(0);
                }
            }
            layer++;
        }
    }


    //randomizes all weights at the beginning of the network
    public void randomizeWeights(){
        int layer = 0;
        for(List<Node> networkLayer: _networkNodes){
            for(Node nodeInLayer: networkLayer){
                nodeInLayer.setWeights(null);
                if(layer == getNetworkNodes().size() - 1){
                    return;
                }
                for(Node nodeInNextLayer: _networkNodes.get(layer + 1)){
                    nodeInLayer.addWeights(1 - (2 * rand.nextFloat()));
                }
            }
            layer++;
        }
    }
    // randomizes all weights except the weights that led to the best output

    public void randomizeWeights2(){
        List<Node> maxOutputPath = getMaxOutputPath();
        // nodes that have already been passed through
        int passedNodes = 0;
        int layer = 0;
        for(List<Node> networkLayer: getNetworkNodes()){

            for(Node nodeInLayer: networkLayer){
                nodeInLayer.setWeights(null);
                if(layer + 1 == getNetworkNodes().size()){
                    return;
                }
                for(Node nodeInNextLayer: getNetworkNodes().get(layer + 1)){
                    if(nodeInNextLayer.getId() != maxOutputPath.get(layer + 1).getId()){
                        nodeInLayer.addWeights(1 - (2 * rand.nextFloat()));
                    }else{
                        nodeInLayer.addWeights(maxOutputPath.get(layer + 1).getWeights().get(maxOutputPath.get(layer + 1).getId() - passedNodes));
                    }
                }
                passedNodes++;
            }
            layer++;
        }
    }


    public int maxOutput(){
        int maxID = 0;
        int counter = 0;
        float max = 0;
        for(Node outputNode: getNetworkNodes().get(getNetworkNodes().size()-1)){
            if(max < outputNode.getWeightScoreSum() || counter == 0){
                max = outputNode.getWeightScoreSum();
                maxID = counter;
            }
            counter++;
        }
        return maxID;
    }
    // TODO change function to output hashmap to also output the max weight so it´s easier to implement randomizeWeights 2
    public List<Node> getMaxOutputPath(){
        int idOfMaxOutput = maxOutput();
        List<Node> path = new ArrayList<>();
        path.add(_networkNodes.get(getNetworkNodes().size() - 1).get(idOfMaxOutput));
        int oldMaxId = idOfMaxOutput;
        for(int i = getNetworkNodes().size()-2; i >= 0; i--){
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
        Collections.reverse(path);
        return path;
    }

    @Override
    public String toString() {
        calculateWeights();

        String networkNodes = "";
        int layer = 0;

        for(List<Node> networkLayer: _networkNodes){
            networkNodes += "\n"+ layer + " ";
            for(Node node: networkLayer){
                networkNodes += node.toString();
            }
            layer++;
        }
        return networkNodes;
    }
}