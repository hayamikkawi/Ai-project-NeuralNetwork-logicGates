/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_secondproject;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author User
 */
public class HiddenPerceptron {
    double input1;
    double input2;
    double w[]=new double[2];
    double theta1;
  
    
    public HiddenPerceptron(){
    
    }
    
    public void initialize(double w1,double w2,double theta){
        w[0]=w1;
        w[1]=w2;
        theta1=theta;

    }
    
 
    
   public double activate (double input1,double input2){
       this.input1=input1;
       this.input2=input2;
       double output;
       output=tan(input1*w[0]+input2*w[1]-theta1);
       return output;
    }

    
    double sigmoid(double x){
        return 1/(double)(1+(1/Math.exp(x)));
    }
    
    double relU(double x){
        if(x>0){
            return x;
        }
        return 0;
    }
    
    double tan(double x){
        return (2/(double)(1+(1/Math.exp(x*2))))-1;
    }
    
     public double []  getPoints() {
        
            double points[] = { (theta1/ w[0]*20), (theta1/w[1] *20)};
            return points;
      
    }

    double y(double x1, double x2) {
//             if (x1>1 || x1<0 || x2>1 ||x2<0) return -1; 
//      else {
          return tan((x1*w[0] + x2*w[1])- theta1);
      
    }

   
    
}
