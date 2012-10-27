/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class Equation {
    private Root root = new Root();
    private Matrix coefficientMatrix = new Matrix();
    private Matrix augmentedMatrix = new Matrix();
    
    public Equation(){
        
    }
    
    public Equation(Matrix coefficientMatrix,Matrix augmentedMatrix){
        this.coefficientMatrix = Matrix.matrixCopy(coefficientMatrix);
        this.augmentedMatrix = Matrix.matrixCopy(augmentedMatrix);
    }
    
    public Root getRoot(){
        return this.root;
    }
    
    public Matrix getCoefficientMatrix(){
        return this.coefficientMatrix;
    }
    
    public void setCoefficientMatrix(Matrix coefficientMatrix){
        this.coefficientMatrix = Matrix.matrixCopy(coefficientMatrix);
    }
    
    public Matrix getAugmentedMatrix(){
        return this.augmentedMatrix;
    }
    
    public void setAugmentedMatrix(Matrix augmentedMatrix){
        this.augmentedMatrix = Matrix.matrixCopy(augmentedMatrix);
    }
    
    public void printSolution(){
        int i,j;
        
        for(i = 0;i < this.getRoot().getRootNum();i ++){
            for(j = 0;j < this.getRoot().getUnknownNum();j ++){
               System.out.print(this.getRoot().getElement(i, j));
               if(j < this.getRoot().getUnknownNum() - 1)
                   System.out.print(' ');
           }
           System.out.print('\n');
        }
    }
    
    public void homogeneousLinearEquation(Matrix matrix){//
        int i,j,k,p,p1,p2,p3,rank = matrix.matrixRank();
        double temp0 = 0.0;
        int[] array1 = new int[10];
        int[] array2 = new int[10];//自由未知量
        
        this.getRoot().setRootNum(1);
        this.setCoefficientMatrix(matrix.matrixLineSimplify());
        this.getRoot().setRootNum(this.getCoefficientMatrix().getRow() - rank + 1);
        this.getRoot().setUnknownNum(this.getCoefficientMatrix().getRow());
        if(this.getRoot().getRootNum() != 1){
            for(p = i = j = 0;j < this.getCoefficientMatrix().getRow()&&i < this.getCoefficientMatrix().getLine();j ++)
                if(this.getCoefficientMatrix().getElement(i, j) != 0.0){
                    i ++;
                    array1[p ++] = j;
                }
            for(p2 = j = 0;j < this.getCoefficientMatrix().getRow();j ++){
                for(p1 = 0;p1 < p&&j != array1[p1];p1 ++)
                    ;
                if(p1 == p)
                    array2[p2 ++] = j;
            }
            p2 --;
            for(k = 1,j = this.getCoefficientMatrix().getRow() - 1;j >= 0&&p2 >= 0;j --)
                if(j == array2[p2]){
                    this.getRoot().setElement(k, j, 1.0);
                    for(p1 = this.getCoefficientMatrix().matrixRank() - 1;p1 >= 0;p1 --){
                        for(p3 = this.getCoefficientMatrix().matrixRank() - 1;p3 > p1;p3 --)
                            temp0 += this.getCoefficientMatrix().getElement(p1, array1[p3]) * this.getRoot().getElement(k, array1[p3]);
                        this.getRoot().setElement(k, array1[p3], -(temp0 + this.getCoefficientMatrix().getElement(p1, j) / this.getCoefficientMatrix().getElement(p1, array1[p3])));
                    }
                    p2 --;
                    k ++;
                    temp0 = 0.0;
                }
        }
    }
}
