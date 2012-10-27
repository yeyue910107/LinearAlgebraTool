/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class SimilarDiagonalize {
    private SquareMatrix sourceMatrix;
    private SquareMatrix matrixP;
    private SquareMatrix diagonalMatrix;
    
    public SimilarDiagonalize(){
        
    }
    
    public SimilarDiagonalize(SquareMatrix sourceMatrix, SquareMatrix matrixP, SquareMatrix DiagonalMatrix){
        this.sourceMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(sourceMatrix));
        this.matrixP = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrixP));
        this.diagonalMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(DiagonalMatrix));
    }
    
    public SquareMatrix getSourceMatrix(){
        return this.sourceMatrix;
    }    
    
    public void setSourceMatrix(SquareMatrix sourceMatrix){
        this.sourceMatrix = sourceMatrix;
    }
    
    public SquareMatrix getMatrixP(){
        return this.matrixP;
    }
    
    public void setMatrixP(SquareMatrix matrix){
        this.matrixP = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public SquareMatrix getDiagonalMatrix(){
        return this.diagonalMatrix;
    }
    
    public void setDiagonalMatrix(SquareMatrix matrix){
        this.diagonalMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public void findMatrixP(SquareMatrix matrix){
        int i,j = 0,k1,k2,k;
        Equation solution = new Equation();
        Eigenvalue value = new Eigenvalue();
        
        this.setMatrixP(matrix);
        value.findEigenvalue(matrix);
        for(k = 0;k < value.getNRvalueNum();k ++){
            solution.homogeneousLinearEquation(value.getNRElement(k).getEigenMatrix());
            for(k1 = 1;k1 < solution.getRoot().getRootNum()&&j < this.getMatrixP().getSize();j ++,k1 ++)
                for(i = k2 = 0;i < this.getMatrixP().getSize();i ++,k2 ++)
                    this.getMatrixP().setElement(i, j, solution.getRoot().getElement(k1, k2));
        }
    }
    
    public void findDiagonalMatrix(SquareMatrix matrix){
        int i;
        Eigenvalue value = new Eigenvalue();
        
        this.setDiagonalMatrix(new SquareMatrix(matrix.getSize(), matrix.getAccuracy()));
        value.findEigenvalue(matrix);
        for(i = 0;i < value.getValueNum();i ++)
            this.getDiagonalMatrix().setElement(i, i, value.getElement(i).getEigenvalue());
    }
}
