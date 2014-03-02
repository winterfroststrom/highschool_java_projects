/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix;

/**
 *
 * @author Scalene
 */
public class Net {
    double learningRateOriginal = .1;
    double learningRate = learningRateOriginal;
    int trials = 10000;
    int precision = 100;
    
    public static class Node{
        double weight;

        public Node(double weight){
            this.weight = weight;
        }
        
        public void reweight(double delta){
            this.weight += delta;
            if(this.weight > 1)
                this.weight = 1;
            else if(this.weight < -1)
                this.weight = -1;
        }
    }

    public static double sigmoid(double input){
        return 1 / (1+Math.pow(Math.E,-input));
    }

    public static double sum(Node[] layer,double[] input){
         double sum = 0;
         for(int i = 0;i<layer.length;i++){    
             sum += layer[i].weight*input[i];
        }
        return sum;
    }

    public static Node[][] forwardprop(Node[][] nodes,double[][] inputs, double delta){
        for(int l = 0;l<nodes.length;l++){
            for(int n = 0;n<nodes[l].length;n++){
                nodes[l][n].reweight(delta*inputs[l][n]);
            }
        }
        return nodes;
    }

    public static Node[][] forwardprop2(Node[][] nodes,double[][] inputs, double delta){
        for(int l = 0;l<nodes.length;l++){
            double sum = 0;
            for(int i = 0;i<inputs[l].length;i++){
                sum += inputs[l][i];
            }
            for(int n = 0;n<nodes[l].length;n++){
                nodes[l][n].reweight(delta*inputs[l][n]/sum);
            }
        }
        return nodes;
    }

    public void trials(){
        Node[][] nodes = new Node[3][];
        nodes[0] = new Node[2+1];
        nodes[1] = new Node[3];
        nodes[2] = new Node[1];
        for(int i = 0;i<nodes.length;i++){
            for(int j = 0;j<nodes[i].length;j++){
                nodes[i][j] = new Node((j == nodes[i].length-1)?1:0.5);
            }
        }
        double previousSumError = 0;
        double sumError = 0;
        int times = 0;
        int it = 0;
        while(times < precision && it < trials){
        //for(int it=0;it < trials;it++){
            if(Math.abs(sumError - previousSumError)/Math.pow(learningRate,2) < 1){
                times++;
                learningRate -= (learningRate/10);
            }
            previousSumError = sumError;
            sumError = 0;
            double maxDelta = 0;
            double[][] maxErrorInputs = new double[nodes.length][];
            for(int l = 0; l<nodes.length;l++){
                maxErrorInputs[l] = new double[nodes[l].length];
            }
            for(int q = 0;q<2;q++){
                for(int e = 0;e<2;e++){
                    double output = 0;
                    double[][] inputs = new double[nodes.length][];
                    inputs[0] = new double[nodes[0].length];
                    for(int l = 0;l<nodes.length;l++){
			if(l >= nodes.length - 1){
                            output = inputs[l][0];
			} else {
                            inputs[l+1] = new double[nodes[l+1].length];
                            if(l == 0){
                                inputs[l][0] = q;
				inputs[l][1] = e;
				inputs[l][2] = 1;
                            }
                            for(int n = 0;n<nodes[l+1].length;n++){
				//inputs[l+1][n] = sigmoid(sum(nodes[l],inputs[l]));
                                inputs[l+1][n] = sum(nodes[l],inputs[l]);
                            }
			}
                    }
                    double result = (q & e)/4.;
                    double error = result - output;
                    if(it == trials-1 || times == precision){
                        System.out.print("\nResult:" + result + " Out:" + output + " Er:" + error);
                    }
                    double delta = (error) * learningRate;
                    if(Math.abs(delta) > Math.abs(maxDelta)){
                        maxDelta = delta;
                        maxErrorInputs = inputs;
                    }
                    sumError += Math.pow(error,2);
                    //forwardprop(nodes,inputs,delta);// 0.250057912328961
                    //forwardprop2(nodes,inputs,delta);// 0.28345727269215637
		}
            }
            forwardprop(nodes,maxErrorInputs,maxDelta);//0.250000001342221
            //forwardprop2(nodes,maxErrorInputs,maxDelta);
            if(it % (trials/500) == 0){
                if(it% (trials/100) == 0){
                    System.out.println();
                }
                System.out.print(" error: " + sumError);
            }
            it++;
        }
        System.out.println();
        System.out.println(it);
        for(int i =0;i<nodes.length;i++){
            for(int j = 0;j<nodes[i].length;j++){
                System.out.print("\nnode " + i + " " + j + ": " + nodes[i][j].weight);
            }
        }
        System.out.println();
    }

    public static void main(String[]args){
        Net net = new Net();
        net.trials();
    }
}
