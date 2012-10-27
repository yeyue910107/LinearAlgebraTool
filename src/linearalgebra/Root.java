/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class Root {
    private final static int MAX = 10;
    private double[][] rootArray = new double[MAX][MAX];
    private int rootNum;
    private int unknownNum;
    
    public Root(){
        
    }
    
    public Root(double[][] rootArray, int rootNum, int unknownNum){
        this.rootArray = Matrix.matrixArrayCopy(rootArray);
        this.rootNum = rootNum;
        this.unknownNum = unknownNum;
    }
    
    public double[][] getRootArray(){
        return this.rootArray;
    }
    
    public int getRootNum(){
        return this.rootNum;
    }
    
    public void setRootNum(int num){
        this.rootNum = num;
    }
    
    public int getUnknownNum(){
        return this.unknownNum;
    }
    
    public void setUnknownNum(int num){
        this.unknownNum = num;
    }
    
    public double getElement(int i, int j){
        return this.rootArray[i][j];
   }
   
   public void setElement(int i, int j, double n){
        this.rootArray[i][j] = n;
   }
   
}
