/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class Jordan {
    private Eigenvalue eigenvalue = new Eigenvalue();
    private SquareMatrix sourceMatrix = new SquareMatrix();
    private SquareMatrix jordanMatrix = new SquareMatrix();
    
    public Eigenvalue getEigenvalue(){
        return this.eigenvalue;
    }
    
    public void setEigenvalue(Eigenvalue value){
        this.eigenvalue = value;
    }
    
    public SquareMatrix getSourceMatrix(){
        return this.sourceMatrix;
    }
    
    public void setSourceMatrix(SquareMatrix matrix){
        this.sourceMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public SquareMatrix getJordanMatrix(){
        return this.jordanMatrix;
    }
    
    public void setJordanMatrix(SquareMatrix matrix){
        this.jordanMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public void jordan(SquareMatrix matrix){
        int i,i1,i2,j,k,k1,k2,k3,p,n = 0;
        int array[][][] = new int[10][10][2];//特征值，不同阶数的jordan块，阶数和jordan块个数
        int array1[] = new int[10];
        int array2[] = new int[10];
        int array3[] = new int[10];
        
        this.setSourceMatrix(SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix)));
        this.getEigenvalue().findEigenvalue(this.getSourceMatrix());
        for(i = 0;i < this.getEigenvalue().getNRvalueNum();i ++){
            array1[0] = this.getSourceMatrix().getSize();
            for(k1 = 1;;k1 ++){
                array1[k1] = this.getEigenvalue().getNRElement(i).getEigenMatrix().matrixPower(k1).matrixRank();
                if(array1[k1] == array1[k1 - 1])
                    break;
            }
            for(k2 = 0;k2 < k1;k2 ++)
                array2[k2] = array1[k2] - array1[k2 + 1];
            for(k3 = 0;k3 < k2;k3 ++)
                array3[k3] = array2[k3] - array2[k3 + 1];
            for(p = j = 0;p < k3;p ++)
                if(array3[p] != 0){
                    array[i][j][0] = p + 1;
                    array[i][j ++][1] = array3[p];
                }
            for(k = 0;k < j;k ++){
                for(i1 = 1;i1 <= array[i][k][1];i1 ++)
                    for(i2 = 1;i2 <= array[i][k][0];i2 ++){
                        this.getJordanMatrix().setElement(n, n, this.getEigenvalue().getElement(i).getEigenvalue());
                        if(i2 < array[i][k][0])
                            this.getJordanMatrix().setElement(n, n + 1, 1.0);
                        n ++;
                    }
            }    
        }
        this.getJordanMatrix().setSize(this.getSourceMatrix().getSize());
        this.getJordanMatrix().setLine(this.getJordanMatrix().getSize());
        this.getJordanMatrix().setRow(this.getJordanMatrix().getSize());
    }
}
