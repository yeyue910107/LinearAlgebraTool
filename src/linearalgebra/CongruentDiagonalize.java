/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class CongruentDiagonalize {
    private SquareMatrix sourceMatrix = new SquareMatrix();
    private SquareMatrix matrixPofDiagonal = new SquareMatrix();
    private SquareMatrix diagonalMatrix = new SquareMatrix();
    private SquareMatrix matrixPofStandard = new SquareMatrix();
    private SquareMatrix standardMatrix = new SquareMatrix();
    
    public CongruentDiagonalize(){
        
    }
    
    public CongruentDiagonalize(SquareMatrix sourceMatrix, SquareMatrix matrixPofDiagonal, SquareMatrix DiagonalMatrix, SquareMatrix matrixPofStandard, SquareMatrix StandardMatrix){
        this.sourceMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(sourceMatrix));
        this.matrixPofDiagonal = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrixPofDiagonal));
        this.diagonalMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(DiagonalMatrix));
        this.matrixPofStandard = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrixPofDiagonal));
        this.standardMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(DiagonalMatrix));
    }
    
    public SquareMatrix getSourceMatrix(){
        return this.sourceMatrix;
    }    
    
    public void setSourceMatrix(SquareMatrix sourceMatrix){
        this.sourceMatrix = sourceMatrix;
    }
    
    public SquareMatrix getMatrixPofDiagonal(){
        return this.matrixPofDiagonal;
    }
    
    public void setMatrixPofDiagonal(SquareMatrix matrix){
        this.matrixPofDiagonal = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public SquareMatrix getDiagonalMatrix(){
        return this.diagonalMatrix;
    }
    
    public void setDiagonalMatrix(SquareMatrix matrix){
        this.diagonalMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
     public SquareMatrix getMatrixPofStandard(){
        return this.matrixPofStandard;
    }
    
    public void setMatrixPofStandard(SquareMatrix matrix){
        this.matrixPofStandard = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public SquareMatrix getStandardMatrix(){
        return this.standardMatrix;
    }
    
    public void setStandardMatrix(SquareMatrix matrix){
        this.standardMatrix = SquareMatrix.matrixToSquare(Matrix.matrixCopy(matrix));
    }
    
    public void congruent(SquareMatrix matrix){
        int i,j,k,p = 0;
	double change;
        SquareMatrix matrix0 = new SquareMatrix(matrix);

        this.setSourceMatrix(matrix);
        for(i = 0;i < matrix0.getLine();i ++)
		for(j = matrix0.getRow();j < 2 * matrix0.getRow();j ++)
			matrix0.setElement(i, j, (j == matrix0.getRow() + i)?1:0);
	matrix0.setRow(2 * matrix0.getRow());
	for(i = 0;i <matrix0.getLine();i ++){
                for(j = i + 1;j < matrix0.getLine();j ++)
                    for(;p < matrix0.getRow();p ++){
			if(matrix0.getElement(i, p) == 0&&matrix0.getElement(j, p) != 0){
				for(k = 0;k < matrix0.getRow();k ++)
                                    matrix0.setElement(i, k, matrix0.getElement(i, k) + matrix0.getElement(j, k));
                                for(k = 0;k < matrix0.getLine();k ++)
                                    matrix0.setElement(k, i, matrix0.getElement(k, i) + matrix0.getElement(k, j));
                        }
			else if(matrix0.getElement(i, p) == 0&&matrix0.getElement(j, p) == 0){
                                for(;j < matrix0.getLine()&&matrix0.getElement(j, p) == 0;j ++)
                                    ;
                                if(j == matrix0.getLine()){
                                    j = i + 1;
                                    continue;
                                }
                                else{
                                    for(k = p;k < matrix0.getRow();k ++)
                                        matrix0.setElement(i, k, matrix0.getElement(i, k) + matrix0.getElement(j, k));
                                    for(k = p;k < matrix0.getLine();k ++)
                                        matrix0.setElement(k, i, matrix0.getElement(k, i) + matrix0.getElement(k, j));
                                }
			}
			else if(matrix0.getElement(i, p) != 0&&matrix0.getElement(j, p) == 0)
				break;
			change = matrix0.getElement(j, p) / matrix0.getElement(i, p);
			for(k = p;k < matrix0.getRow();k ++)
                            matrix0.setElement(j, k, matrix0.getElement(j, k) - matrix0.getElement(i, k) * change);
                        for(k = p;k < matrix0.getLine();k ++)
                            matrix0.setElement(k, j, matrix0.getElement(k, j) - matrix0.getElement(k, i) * change);
                        break;
		}
                p = i;
        }
        
        this.getDiagonalMatrix().setSize(this.getSourceMatrix().getSize());
        this.getDiagonalMatrix().setAccuracy(this.getSourceMatrix().getAccuracy());
        this.getMatrixPofDiagonal().setSize(this.getSourceMatrix().getSize());
        this.getMatrixPofDiagonal().setAccuracy(this.getSourceMatrix().getAccuracy());
        this.getStandardMatrix().setSize(this.getSourceMatrix().getSize());
        this.getStandardMatrix().setAccuracy(this.getSourceMatrix().getAccuracy());
        this.getMatrixPofStandard().setSize(this.getSourceMatrix().getSize());
        this.getMatrixPofStandard().setAccuracy(this.getSourceMatrix().getAccuracy());
        
        for(i = 0;i < this.getDiagonalMatrix().getLine();i ++)
            this.getDiagonalMatrix().setElement(i, i, matrix0.getElement(i, i));
        
        for(i = 0;i < this.getMatrixPofDiagonal().getLine();i ++)
            for(j = 0;j < this.getMatrixPofDiagonal().getRow();j ++)
                this.getMatrixPofDiagonal().setElement(i, j, matrix0.getElement(j, i + this.getMatrixPofDiagonal().getRow()));
        
        for(i = 0;i < this.getStandardMatrix().getLine();i ++)
            if(matrix0.getElement(i, i) > 0)
                this.getStandardMatrix().setElement(i, i, 1.0);
            else if(matrix0.getElement(i, i) == 0)
                this.getStandardMatrix().setElement(i, i, 0.0);
            else
                this.getStandardMatrix().setElement(i, i, -1.0);
        
        for(i = 0;i < matrix0.getLine();i ++)
            for(j = 0;j < matrix0.getRow();j ++)
                if(matrix0.getElement(i, i) != 0&&Math.abs(matrix0.getElement(i, i)) != 1.0)
                    matrix0.setElement(i, j + this.getMatrixPofStandard().getRow(), matrix0.getElement(i, j + this.getMatrixPofStandard().getRow()) / Math.sqrt(Math.abs(matrix0.getElement(i, i))));
        for(i = 0;i < this.getMatrixPofStandard().getLine();i ++)
            for(j = 0;j < this.getMatrixPofStandard().getRow();j ++)
                this.getMatrixPofStandard().setElement(i, j, matrix0.getElement(j, i + this.getMatrixPofStandard().getRow()));
        
    }
    
}
