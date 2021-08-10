/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_secondproject;

/**
 *
 * @author User
 */
public class Multilayer {
       double[][] dataset;
       
       double w13 = Math.random()*2.4 -1.2;
       double deltaW13=0;
       double w23 = Math.random()*2.4 -1.2;
       double deltaW23=0;
       double w14 = Math.random()*2.4 -1.2;
       double deltaW14=0;
       double w24 = Math.random()*2.4 -1.2;
       double deltaW24=0;
       double w35 = Math.random()*2.4 -1.2;
       double deltaW35=0;
       double w45 = Math.random()*2.4 -1.2;
       double deltaW45=0;
       
       double theta1 = Math.random()*2.4 -1.2;
       double deltaT1;
       double theta2 = Math.random()*2.4 -1.2;
       double deltaT2;
       double theta3 =Math.random()*2.4 -1.2;
       double deltaT3;


       
       double alpha= .2;
       
       double s5;
       double s3;
       double s4;
       
       double hidden1output;
       double hidden2output;
       double out;
       
       double error;

       
       HiddenPerceptron hidden1;
       HiddenPerceptron hidden2;
       HiddenPerceptron output;
       
       double[] weights;
       double[] finalWeights;
       double[] delta;
       
       double[] thetas;
       double[] finalThetas;
       double[] deltaTheta;
       int counter=0;
       
       int epochs;
       double bestMSE;
       boolean flag=false;
       double MSE;
       
        public Multilayer(double[][] dataSet,int epochs){
            this.epochs=epochs;
            this.dataset=dataSet;
            
            double[] weights={w13,w23,w14,w24,w35,w45};
            this.weights= weights;
            
            double[] delta={deltaW13,deltaW23,deltaW14,deltaW24,deltaW35,deltaW45};
            this.delta=delta;
            
            double[] thetas={theta1,theta2,theta3};
            this.thetas=thetas;
            
            double[] deltaTheta={deltaT1,deltaT2,deltaT3};
            this.deltaTheta=deltaTheta;
            
            finalWeights=new double[6];
            finalThetas=new double[3];

            
            hidden1 = new HiddenPerceptron();
            hidden2 = new HiddenPerceptron();
            output = new HiddenPerceptron();
         
       }
       
       void initialize() {
        hidden1.initialize(weights[0], weights[1], thetas[0]);
        hidden2.initialize(weights[2], weights[3], thetas[1]);
        output.initialize(weights[4], weights[5], thetas[2]);    
//        System.out.print("in initialization");
       }
       
      void activate(){
          double o[]=new double[4];
          do{
              counter++;
              
              
       for(int j=0;j<this.dataset.length;j++){
            initialize();
//           System.out.print("in activation function");
            hidden1output=hidden1.activate(dataset[j][0], dataset[j][1]);
            hidden2output=hidden2.activate(dataset[j][0], dataset[j][1]);
            out=output.activate(hidden1output, hidden2output);
            o[j]=out;
       
            error=dataset[j][2]-out;
       
            //training
            s5=tanPrime(out)*error;
            
            delta[4]=alpha*hidden1output*s5;
            delta[5]=alpha*hidden2output*s5;
            deltaTheta[2]=alpha*-1*s5;
            
       
            s3=tanPrime(hidden1output)*s5*weights[4];
            s4=tanPrime(hidden2output)*s5*weights[5];
            
       
            delta[0]=alpha*dataset[j][0]*s3;
            delta[1]=alpha*dataset[j][1]*s3;
            deltaTheta[0]=alpha*-1*s3;

       
            delta[2]=alpha*dataset[j][0]*s4;
            delta[3]=alpha*dataset[j][1]*s4;
            deltaTheta[1]=alpha*-1*s4;

       
            for(int i=0;i<weights.length;i++){
                weights[i]=weights[i]+delta[i];
               // thetas[i]=thetas[i]+deltaTheta[i];
               System.out.println("weight "+i+" "+weights[i]);
            }
            
            for(int i=0;i<thetas.length;i++){
                //weights[i]=weights[i]+delta[i];
               thetas[i]=thetas[i]+deltaTheta[i];
            }
            //System.out.println(error);
            
       } 
          
        MSE=measureMSE(o);
            if(!flag){
                flag=true;
                saveValues();
               

            }
            else{
                if(MSE<bestMSE){
                    saveValues();
                }
        }
            
    }
                while (counter<epochs&&MSE>.01);
       }

    
    
     private double measureMSE(double o[]) {
        double summation=0;
        double MSE=0;
        
        for(int i=0;i<dataset.length;i++){
            summation+=Math.pow((dataset[i][2]-o[i]),2);
            System.out.println("sum"+i+" "+o[i]);
        }
        MSE=summation*(1/(float)dataset.length);
        System.out.println("MSE"+MSE);
        //System.out.println(dataset[0][2]+" "+ dataSet[1][2]+" "+ dataSet[2][2]+" "+ dataSet[3][2]);
//        System.out.println("yActual"+yActual[0]+" "+ yActual[1]+" "+ yActual[2]+" "+ yActual[3]);

        return MSE; 
    }
    
    double tanPrime(double x){
        //double tan=2/(double)(1+(1/Math.exp(2*x)))-1;
        return 1-Math.pow(x, 2);
    }
    
    int reluPrime(double x){
        if( x>=0){
            return 1;
        }
        return -1;
    }
    
   
    
    double sigmoidPrime(double x){
        //double sigmoid=1/(double)(1+(1/Math.exp(x)));
        return x*(1-x);
    }
    
    double linearPrime(){
        return 1;
    }
    
    
    public double []  getPoints(int n) {
        if(n==1){
            double points[] = { (finalThetas[0]/ finalWeights[0]*20), (finalThetas[0]/finalWeights[1] *20)};
            return points;
        }
            double points[] = { (finalThetas[1]/ finalWeights[2]*20), (finalThetas[1]/finalWeights[3] *20)};
            return points;
        
    }
//   
    
    int getNumberOfEpochs(){
        
        return counter;
    }

    double y(double x1, double x2) {
        double out1 = this.hidden1.y(x1 , x2); 
        double out2 = this.hidden2.y(x1, x2); 
        double out = this.output.y(out1, out2);
        return out;
    }

    private void saveValues() {
        bestMSE=MSE;
        for(int i=0;i<3;i++){
            finalThetas[i]=thetas[i];
        }
        for(int i=0;i<6;i++){
            finalWeights[i]=weights[i];
        }
                 
    }
    
}
