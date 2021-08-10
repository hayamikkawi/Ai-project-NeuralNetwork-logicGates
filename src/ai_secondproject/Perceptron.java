/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_secondproject;

import static java.lang.Math.min;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author hayamikkawi
 */
public class Perceptron {
    int counter=0;
    String operation = ""; 
//    double w1 ; 
//    double w2 ; 
    double w[]=new double[2];
    double wFinal[]=new double[2];
    double theta1 ; 
    double thetaFinal;
    double alpha; 
    double[][] dataSet;
    int epochs;
    double bestMSE;
    boolean flag=false;
    double MSE;  
    
    public Perceptron(String op,int epochs){
        this.operation = op;
        defineDataSet (op); 
        this.epochs=epochs;
    }
    
    public void initialize(){
        w[0] = Math.random() -.5;
        w[1] = Math.random() -.5; 
        theta1 = Math.random() -.5;
        alpha = .1; 
    }
     
    public void activate (){
        double[] yActual=new double[dataSet.length];

        do{
   
            counter++;
            double error;
            double yDesired;
            double weightCorrection;
            
            
            for(int i=0;i<dataSet.length;i++){
                double X=0;
                int j;
                for( j=0;j<dataSet[0].length-1;j++){
                   X+= w[j]*dataSet[i][j];
                }
                X=X-theta1;
                 BigDecimal bd = BigDecimal.valueOf(X);
//                 bd = bd.setScale(3, RoundingMode.HALF_UP);
//                 X= bd.doubleValue();
                System.out.println("x="+X);
                yActual[i]=step(X);
                //System.out.println("yActual"+yActual[0]+" "+ yActual[1]+" "+ yActual[2]+" "+ yActual[3]);
                System.out.println("j= "+ j);
                yDesired=dataSet[i][j];
                error=yDesired-yActual[i];
                for( int k=0;k<dataSet[0].length-1;k++){
                    
                    weightCorrection=weightCorrection(error,dataSet[i][k]);
                    w[k]=w[k]+weightCorrection;
//                     BigDecimal bd1 = BigDecimal.valueOf(w[k]);
//                     bd1 = bd1.setScale(3, RoundingMode.HALF_UP);
//                     w[k]= bd1.doubleValue();
                    System.out.println("weight"+w[0]+"  "+w[1]);

                }
               double thetaCorrection = weightCorrection(error, -1); 
               theta1 = theta1 + thetaCorrection;
                        System.out.println("theta"+theta1);
                
                
            }
            MSE=measureMSE(yActual);
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

    private double measureMSE(double yActual[]) {
        double summation=0;
        double MSE=0;
        
        for(int i=0;i<dataSet.length;i++){
            summation+=Math.pow((dataSet[i][dataSet[0].length-1]-yActual[i]),2);
        }
        MSE=summation*(1/(float)dataSet.length);
        System.out.println("MSE"+MSE);
//        System.out.println(dataSet[0][2]+" "+ dataSet[1][2]+" "+ dataSet[2][2]+" "+ dataSet[3][2]);
//        System.out.println("yActual"+yActual[0]+" "+ yActual[1]+" "+ yActual[2]+" "+ yActual[3]);

        return MSE; 
    }

    private void defineDataSet(String op) {
        if(op.equals("not")){
            double[][] dataSet = { {1.0,0}, {0,1.0} };
            this.dataSet=dataSet;
//            return dataSet;

        }
        else if(op.equals("and")){
            double[][] dataSet = { {0,0,0}, {0,1.0,0},{1.0,0,0},{1.0,1.0,1.0} };
            this.dataSet=dataSet;
            
        }
        else if(op.equals("nand")){
            double[][] dataSet = { {0,0,1.0}, {0,1.0,1.0},{1.0,0,1.0},{1.0,1.0,0} };
            this.dataSet=dataSet;
        }
        else if(op.equals("or")){
            double[][] dataSet = { {1.0,1.0,1.0}, {0,1.0,1.0},{1.0,0,1.0},{0,0,0} };
            this.dataSet=dataSet;
            
        }
        else if(op.equals("nor")){
            double[][] dataSet = { {1.0,1.0,0}, {0,1.0,0},{1.0,0,0},{0,0,1.0} };
            this.dataSet=dataSet;         
        }
        else if(op.equals("xor")){
            
        }
        else if(op.equals("xnor")){
            
        }
    }
    
    int getNumberOfEpochs(){
        
        return counter;
    }
    
    int step(double x){
        if (x>=0){
            return 1;
        }
        return 0;
    }
    
    int sign(double x){
        if( x>=0){
            return 1;
        }
        return -1;
    }
    
    double sigmoid(double x){
        return 1/(double)(1+(1/Math.exp(x)));
    }
    
    double linear(double x){
        return x;
    }

    private double weightCorrection( double error, double input) {
        double correction = alpha*error*input;
        return correction;
    }

    public double []  getPoints() {
        double points[] = { (thetaFinal/ wFinal[0]*100), (thetaFinal/wFinal[1] *100)};
        return points;
    }
    public int y(int x1, int x2){
      if (x1>1 || x1<0 || x2>1 ||x2<0) return -1; 
      else {
          return step((x1*wFinal[0] + x2*wFinal[1])- thetaFinal);
      }
    }

    private void saveValues() {
                bestMSE=MSE;
                thetaFinal=theta1;
                wFinal[0]=w[0];
                wFinal[1]=w[1];    
    }
    
}