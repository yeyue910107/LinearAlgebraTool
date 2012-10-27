/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
public class Eigenvalue {
    private final static int MAX = 10;
    private final static double MIN = 0.00001;
    private EigenvalueData[] eigenvalueArray = new EigenvalueData[MAX];
    private EigenvalueData[] nonRepeatedEigenvalue = new EigenvalueData[MAX];
    private SquareMatrix sourceMatrix = new SquareMatrix();
    private int valueNum;
    private int NRvalueNum;
    private double accuracy = 0.1;
    
    public Eigenvalue(){
        
    }
    
    public Eigenvalue(SquareMatrix sourceMatrix){
        this.sourceMatrix = sourceMatrix;
    }
    
    public EigenvalueData[] getEigenvalueArray(){
        return this.eigenvalueArray;
    }
    
    public EigenvalueData[] getNonRepeatedEigenvalue(){
        return this.nonRepeatedEigenvalue;
    }
    
    public SquareMatrix getSourceMatrix(){
        return this.sourceMatrix;
    }
    
    public void setSourceMatrix(SquareMatrix sourceMatrix){
        this.sourceMatrix = SquareMatrix.matrixToSquare(SquareMatrix.matrixCopy(sourceMatrix));
    }
    
    public int getValueNum(){
        return this.valueNum;
    }
    
    public void setValueNum(int n){
        this.valueNum = n;
    }
    
    public int getNRvalueNum(){
        return this.NRvalueNum;
    }
    
    public void setNRvalueNum(int NRvalueNum){
        this.NRvalueNum = NRvalueNum;
    }
    
    public double getAccuracy(){
        return this.accuracy;
    }
    
    public void setAccuracy(double accuracy){
        this.accuracy = accuracy;
    }
    
    public EigenvalueData getElement(int i){
        return this.eigenvalueArray[i];
    }
   
    public void setElement(int i, EigenvalueData data){
        this.eigenvalueArray[i] = data;
    }
    
    public EigenvalueData getNRElement(int i){
        return this.nonRepeatedEigenvalue[i];
    }
   
    public void setNRElement(int i, EigenvalueData data){
        this.nonRepeatedEigenvalue[i] = data;
    }
    
    public void printEigenvalue(){//输出特征值
        int i;
        for(i = 0;i < this.getValueNum();i ++)
            System.out.println(this.getElement(i).getEigenvalue());
    }
    
    public void printNREigenvalue(){
        int i;
        for(i = 0;i < this.getNRvalueNum();i ++)
            System.out.println(this.getNRElement(i).getEigenvalue());
    }
    
    private static SquareMatrix getQ(SquareMatrix matrix){//QR分解：对矩阵Schmit正交化(schmit_orthogonalization)求Q
        int i,j,k;
	SquareMatrix matrixQ = new SquareMatrix(matrix.getSize());
	double temp0,mod;
        Vector temp1,temp2,temp3,temp;
        
        matrixQ.setAccuracy(matrix.getAccuracy());
        temp = new Vector();
        temp1 = new Vector();
        temp2 = new Vector();
        temp3 = new Vector();
	for(i = 0;i < matrixQ.getSize();i ++)
			matrixQ.setElement(i, 0, matrix.getElement(i, 0) / matrix.matrixRowToVector(0).vectorMod());
	for(j = 1;j < matrixQ.getSize();j ++)
		for(i = 0;i < matrixQ.getSize();i ++)
			matrixQ.setElement(i, j, matrix.getElement(i, j));
	for(j = 1;j < matrixQ.getSize();j ++){
		for(k = 0;k < j;k ++){
			temp3 = Vector.vectorCopy(matrixQ.matrixRowToVector(k));
			temp0 = Vector.vectorMultiply(matrix.matrixRowToVector(j),temp3);
			temp1 = Vector.vectorCopy(temp3.numVectorMultiply(temp0));
			temp2 = Vector.vectorCopy(Vector.vectorPlus(temp1,temp2));
                }
		temp = Vector.vectorCopy(Vector.vectorMinus(matrix.matrixRowToVector(j),temp2));
		mod = temp.vectorMod();
		for(i = 0;i < matrixQ.getSize();i ++)
			matrixQ.setElement(i, j, temp.getElement(i) / mod);
                temp2.vectorInitial();
	}
	return matrixQ;
}
    
   private static SquareMatrix getR(SquareMatrix matrix, SquareMatrix matrixQ){//QR分解：求R
        return SquareMatrix.matrixToSquare(Matrix.matrixMultiply(matrixQ.matrixTranspose(),matrix));
}
   
   private static SquareMatrix getRQ(SquareMatrix matrixR, SquareMatrix matrixQ){//QR分解：求RQ
	return SquareMatrix.matrixToSquare(Matrix.matrixMultiply(matrixR,matrixQ));
}
   
   private static SquareMatrix decompositionQR(SquareMatrix matrix){//QR分解
	int i = 0;
        double k;
	SquareMatrix matrixQ = new SquareMatrix();

        if(Eigenvalue.isQuaintMatrix(matrix)){
            for(k = 1;;k ++){
                matrix = SquareMatrix.matrixToSquare(Matrix.matrixAdd(matrix, SquareMatrix.creatUnitMatrix(matrix.getSize()).matrixNumMultiply(k)));
                if(!Eigenvalue.isQuaintMatrix(matrix))
                    break;
            }
        }
	do{
		matrixQ = SquareMatrix.matrixToSquare(Eigenvalue.getQ(matrix));
		matrix = SquareMatrix.matrixToSquare(Matrix.matrixMultiply(Eigenvalue.getR(matrix,matrixQ),matrixQ));
		i ++;
	}
	while(Eigenvalue.isTriangularMatrix(matrix));
        matrix = SquareMatrix.matrixToSquare(matrix.matrixAccuralize());
	return matrix;
}
   
   public static SquareMatrix findEigenmatrix(SquareMatrix matrix,double k){//求特征矩阵
       int i;
       SquareMatrix temp_matrix = new SquareMatrix(matrix);
       
       for(i = 0;i < temp_matrix.getSize();i ++)
           temp_matrix.setElement(i, i, temp_matrix.getElement(i, i) - k);
       temp_matrix = SquareMatrix.matrixToSquare(temp_matrix.matrixAccuralize());
       return temp_matrix;
   }
   
   public void findEigenvalue(SquareMatrix sourceMatrix){//求特征值
       int i, j, m, p = 0;
       double k = 0.0;
       SquareMatrix matrixQ = new SquareMatrix();
       SquareMatrix matrix = new SquareMatrix(sourceMatrix);
       EigenvalueData data;
       
       this.setSourceMatrix(sourceMatrix);
       if(Eigenvalue.isQuaintMatrix(matrix)){
            for(k = 1.0;;k ++){
                matrix = SquareMatrix.matrixToSquare(Matrix.matrixAdd(matrix, SquareMatrix.creatUnitMatrix(matrix.getSize()).matrixNumMultiply(1.0)));
                if(!Eigenvalue.isQuaintMatrix(matrix))
                    break;
            }
       }
       do{
		matrixQ = SquareMatrix.matrixToSquare(Eigenvalue.getQ(matrix));
		matrix = SquareMatrix.matrixToSquare(Matrix.matrixMultiply(Eigenvalue.getR(matrix,matrixQ),matrixQ));
		p ++;
       }
       while(Eigenvalue.isTriangularMatrix(matrix));
       matrix = SquareMatrix.matrixToSquare(matrix.matrixAccuralize());
       for(i = 0;i < matrix.getSize();i ++){
           data = new EigenvalueData();
           data.setEigenvalue(matrix.getElement(i, i) - k);
           data.setEigenMatrix(Eigenvalue.findEigenmatrix(this.getSourceMatrix(), data.getEigenvalue()));
           this.setElement(i, data);
       }
       this.setValueNum(matrix.getSize());
       for(i = 0;i < this.getValueNum();i ++)
           if(Math.abs(this.getElement(i).getEigenvalue()) <= this.getAccuracy()){
               data = new EigenvalueData();
               data.setEigenvalue(0.0);
               data.setEigenMatrix(this.getSourceMatrix());
               this.setElement(i, data);    
           }
       this.setNRElement(0, this.getElement(0));
       for(i = 1;i < this.getValueNum();i ++){
           data = new EigenvalueData();
           this.setNRElement(i, data);    
       }
       j = 0;
       for(i = 1;i < this.getValueNum();i ++){
           for(m = 0;m <= j&&Math.abs(this.getNRElement(m).getEigenvalue() - this.getElement(i).getEigenvalue()) >= this.getAccuracy();m ++)
               ;
           if(m == j + 1)
               this.setNRElement(++ j, this.getElement(i));
       }
       this.setNRvalueNum(j + 1);
   }
   
   private static boolean isTriangularMatrix(SquareMatrix matrix){//判断是否为上三角矩阵
	int i,j;
        
	for(i = 1;i < matrix.getSize();i ++)
		for(j = 0;j < i;j ++)
			if(Math.abs(matrix.getElement(i, j)) > MIN)
				return true;
	return false;
}
   
   private static boolean isQuaintMatrix(SquareMatrix matrix){//判断是否为奇异矩阵
       if(matrix.determinantValue() == 0)
           return true;
       else
           return false;
   }
}
