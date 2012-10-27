/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class EigenvalueData {
    private double eigenvalue;
    private SquareMatrix eigenMatrix = new SquareMatrix();
    
    public EigenvalueData(){
        
    }
    
    public EigenvalueData(double eigenvalue, SquareMatrix eigenMatrix){
        this.eigenvalue = eigenvalue;
        this.eigenMatrix = (SquareMatrix)SquareMatrix.matrixCopy(eigenMatrix);
    }
    
    public double getEigenvalue(){
        return this.eigenvalue;
    }
    
    public void setEigenvalue(double eigenvalue){
        this.eigenvalue = eigenvalue;
    }
    
    public SquareMatrix getEigenMatrix(){
        return this.eigenMatrix;
    }
    
    public void setEigenMatrix(SquareMatrix eigenMatrix){
        this.eigenMatrix = SquareMatrix.matrixToSquare(SquareMatrix.matrixCopy(eigenMatrix));
    }
       
}
