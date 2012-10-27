/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

import java.util.*;
import javax.swing.*;
import java.text.*;
/**
 *
 * @author 叶玥
 */
public class Matrix {
   final static int MAX = 10;
   protected double[][] matrixArray = new double[MAX][MAX];
   protected int line = 0, row = 0;
   protected double accuracy = 0.0;
   protected String name = null;
   
   public static Matrix scanMatrix(){//输入矩阵
       int i,j;
       Matrix matrix = new Matrix();
       Scanner scanner = new Scanner(System.in);
       
       matrix.setLine(scanner.nextInt());
       matrix.setRow(scanner.nextInt());
       matrix.setAccuracy(scanner.nextDouble());
       for(i = 0;i < matrix.getLine();i ++){
           for(j = 0;j < matrix.getRow();j ++)
               matrix.setElement(i, j, scanner.nextDouble());
       }
       return matrix;
   }
   
   public void printMatrix(){//输出矩阵
       int i,j;
       
       for(i = 0;i < getLine();i ++){
           for(j = 0;j < getRow();j ++){
               System.out.print(this.getElement(i, j));
               if(j < getRow() - 1)
                   System.out.print(' ');
           }
           System.out.print('\n');
       }
   }
   
   public Matrix(){//构造函数
       
   }
   
   public Matrix(int line, int row){
        this.line = line;
        this.row = row;
   }
   
   public Matrix(int line, int row, double accuracy){
        this.line = line;
        this.row = row;
        this.accuracy = accuracy;
   }
   
   public Matrix(double[][] matrixArray, int line, int row){
        this.matrixArray = Matrix.matrixArrayCopy(matrixArray);
        this.line = line;
        this.row = row;
   }
   
   public Matrix(double[][] matrixArray, int line, int row, double accuracy){
        this.matrixArray = Matrix.matrixArrayCopy(matrixArray);
        this.line = line;
        this.row = row;
        this.accuracy = accuracy;
   }
   
   public Matrix(double[][] matrixArray, int line, int row, double accuracy, String name){
        this.matrixArray = Matrix.matrixArrayCopy(matrixArray);
        this.line = line;
        this.row = row;
        this.accuracy = accuracy;
        this.name = name;
   }
   
    public Matrix(Matrix matrix){
        this.matrixArray = Matrix.matrixArrayCopy(matrix.getMatrixArray());
        this.line = matrix.getLine();
        this.row = matrix.getRow();
        this.accuracy = matrix.getAccuracy();
   }
    
   public static double[][] matrixArrayCopy(double[][] array){
       int i, j;
       double[][] matrixArray = new double[MAX][MAX];
       
       for(i = 0;i < array.length;i++)
           for(j = 0;j < array[0].length;j++)
                matrixArray[i][j] = array[i][j];
       return matrixArray;
   }
   
   public double[][] getMatrixArray(){
       return this.matrixArray;
   }
   
   public void setMatrixArray(double[][] array){
       this.matrixArray = Matrix.matrixArrayCopy(array);
   }
   
   public int getLine(){
       return this.line;
   }
   
   public void setLine(int n){
       this.line = n;
   }
   
   public int getRow(){
       return this.row;
   }
   
   public void setRow(int n){
       this.row = n;
   }
   
   public double getAccuracy(){
        return this.accuracy;
   }
   
   public void setAccuracy(double accuracy){
       this.accuracy = accuracy;
   }
   
   public String getName(){
       return this.name;
   }
   
   public void setName(String name){
       this.name = name;
   }
   
   public double getElement(int i, int j){
        return this.matrixArray[i][j];
   }
   
   public void setElement(int i, int j, double n){
        this.matrixArray[i][j] = n;
   }
   
   public Matrix matrixAccuralize(){//精确化矩阵
       int i, j;
       Matrix matrix = new Matrix(this);
       
       for(i = 0;i < matrix.getLine();i ++)
           for(j = 0;j < matrix.getRow();j ++)
               if(Math.abs(matrix.getElement(i, j)) <= matrix.getAccuracy())
                   matrix.setElement(i, j, 0.0);
       return matrix;
   }
   
   /*public boolean equals(Matrix matrix){
       
   }*/
   
   /*public String toString(double x, double accuracy){
       String element, temp;
       int n = (int)(1 / accuracy);
       
       temp = String.valueOf(x);
       element = 
   }*/
   
   public static Matrix matrixCopy(Matrix matrix){//矩阵复制
       int i, j;
       Matrix matrix_copy = new Matrix();
       
       matrix_copy.setLine(matrix.getLine());
       matrix_copy.setRow(matrix.getRow());
       matrix_copy.setAccuracy(matrix.getAccuracy());
       matrix_copy.setName(matrix.getName());
       for(i = 0;i < matrix.getLine();i ++)
           for(j = 0;j < matrix.getRow();j ++)
               matrix_copy.setMatrixArray(matrix.getMatrixArray());
       return matrix_copy;
   }
   
   public Matrix matrixTranspose(){//矩阵转置
	int i, j;
	Matrix trans_matrix = new Matrix();

	for(i = 0;i < getLine();i ++)
		for(j = 0;j < getRow();j ++)
			trans_matrix.setElement(j, i, this.getElement(i, j));
	trans_matrix.setLine(getLine());
	trans_matrix.setRow(getRow());
        trans_matrix.setAccuracy(getAccuracy());
	return trans_matrix;
   }
    
   public static Matrix matrixAdd(Matrix matrix1,Matrix matrix2){//矩阵加法
       int i, j;
       Matrix matrix = new Matrix();
       
       for (i = 0;i< matrix1.getLine();i ++)
           for (j = 0;j < matrix1.getRow();j ++)
               matrix.setElement(i, j, matrix1.getElement(i, j) + matrix2.getElement(i, j));
       matrix.setLine(matrix1.getLine());
       matrix.setRow(matrix1.getRow());
       matrix.setAccuracy(matrix1.getAccuracy());
       return matrix;
   } 
   
   public static Matrix matrixMinus(Matrix matrix1,Matrix matrix2){//矩阵减法
       int i, j;
       Matrix matrix = new Matrix();
       
       for (i = 0;i< matrix1.getLine();i ++)
           for (j = 0;j < matrix1.getRow();j ++)
               matrix.setElement(i, j, matrix1.getElement(i, j) - matrix2.getElement(i, j));
       matrix.setLine(matrix1.getLine());
       matrix.setRow(matrix1.getRow());
       matrix.setAccuracy(matrix1.getAccuracy());
       return matrix;
   } 
   
   public static Matrix matrixMultiply(Matrix matrix1,Matrix matrix2){//矩阵乘法
        int i, j, k;
        Matrix matrix = new Matrix();

	matrix.setLine(matrix1.getLine());
	matrix.setRow(matrix2.getRow());
        matrix.setAccuracy(matrix1.getAccuracy());
	for(i = 0;i < matrix.getLine();i++)
		for(j = 0;j < matrix.getRow();j ++)
			matrix.setElement(i, j, 0.0);
	for(i = 0;i < matrix.getLine();i ++)
		for(j = 0;j < matrix.getRow();j ++)
			for(k = 0;k < matrix1.getRow();k ++)
				matrix.setElement(i, j, matrix.getElement(i, j) + matrix1.getElement(i, k) * matrix2.getElement(k, j));
	return matrix;
    }
   
   public Matrix matrixPower(int n){//矩阵的幂
       int i;
       Matrix matrix = new Matrix(this);
       
       for(i = 2;i <= n;i ++)
           matrix = Matrix.matrixMultiply(matrix, this);
       return matrix;
   }
   
   public Matrix matrixNumMultiply(double x){//矩阵乘以一个数
       int i, j;
       Matrix matrix = new Matrix(this);
       
       for(i = 0;i < matrix.getLine();i ++)
           for(j = 0;j < matrix.getRow();j ++)
               matrix.setElement(i, j, x * matrix.getElement(i, j));
       return matrix;
   }
   
   public Matrix matrixLineSimplify(){//矩阵的行简化
        int i, j, k, p = 0;
	double change,first_of_line;
        Matrix matrix0 = new Matrix(this);

	for(i = 0;i <matrix0.getLine();i ++){
                for(j = i + 1;j < matrix0.getLine();j ++)
                    for(;p < matrix0.getRow();p ++){
			if(matrix0.getElement(i, p) == 0&&matrix0.getElement(j, p) != 0)
				for(k = 0;k < matrix0.getRow();k ++)
					matrix0.setElement(i, k, matrix0.getElement(i, k) + matrix0.getElement(j, k));
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
                                }
			}
			else if(matrix0.getElement(i, p) != 0&&matrix0.getElement(j, p) == 0)
				break;
			if(matrix0.getElement(i, p) < 0)
				for(k = p;k < matrix0.getRow();k ++)
					matrix0.setElement(i, k, -matrix0.getElement(i, k));
			if(matrix0.getElement(j, p) < 0)
				for(k = p;k < matrix0.getRow();k ++)
					matrix0.setElement(j, k, -matrix0.getElement(j, k));
			change = matrix0.getElement(j, p) / matrix0.getElement(i, p);
			for(k = p;k < matrix0.getRow();k ++)
				matrix0.setElement(j, k, matrix0.getElement(j, k) - matrix0.getElement(i, k) * change);
                        break;
		}
                p = i;
        }
	//将每一行最简化
        matrix0 = matrix0.matrixAccuralize();
	for(i = 0;i < matrix0.getLine();i ++){
		for(j = 0;j < matrix0.getRow()&&matrix0.getElement(i, j) == 0;j ++)
			;
		first_of_line = matrix0.getElement(i, j);
		if(j == matrix0.getRow())
			continue;
		for(;j < matrix0.getRow();j ++)
			matrix0.setElement(i, j, matrix0.getElement(i, j) / first_of_line);
	}
	return matrix0;
}
   
   public Matrix matrixReverse(){//求矩阵的逆
        int i, j, k;
	double change;
        Matrix matrix_reverse = new Matrix(this);

	for(i = 0;i < matrix_reverse.getLine();i ++)
		for(j = matrix_reverse.getRow();j < 2 * matrix_reverse.getRow();j ++)
			matrix_reverse.setElement(i, j, (j == matrix_reverse.getRow() + i)?1:0);
	matrix_reverse.setRow(2 * matrix_reverse.getRow());
	matrix_reverse = matrix_reverse.matrixLineSimplify();
	/*将矩阵的左半边化为单位阵*/
	for(i = 0;i < matrix_reverse.getLine() - 1;i ++)
		for(j = i + 1;j < matrix_reverse.getRow() / 2;j ++){
			if(matrix_reverse.getElement(i, j) == 0)
				continue;
			if(matrix_reverse.getElement(i, j) < 0)
				for(k = 0;k < matrix_reverse.getRow();k ++)
					matrix_reverse.setElement(i, k, -matrix_reverse.getElement(i, k));
			if(matrix_reverse.getElement(j, j) < 0)
				for(k = 0;k < matrix_reverse.getRow();k ++)
					matrix_reverse.setElement(j, k, -matrix_reverse.getElement(j, k));
			change = matrix_reverse.getElement(i, j) / matrix_reverse.getElement(j, j);
			for(k = 0;k < matrix_reverse.getRow();k ++)
				matrix_reverse.setElement(i, k, matrix_reverse.getElement(i, k) - matrix_reverse.getElement(j, k) * change);
		}
        matrix_reverse.setRow(matrix_reverse.getRow() / 2);
        for(i = 0;i < matrix_reverse.getLine();i ++)
            for(j = 0;j < matrix_reverse.getRow();j ++)
                matrix_reverse.setElement(i, j, matrix_reverse.getElement(i, j + matrix_reverse.getRow()));
        return matrix_reverse;
}
   
   public Matrix matrixAbjoint(){//求伴随矩阵
        int i, j;
        Matrix matrix = new Matrix();
        
        matrix = Matrix.matrixCopy(this);
        matrix = matrix.matrixReverse().matrixNumMultiply(matrix.determinantValue());
        return matrix;
   }
   
   public int matrixRank()//矩阵的秩
{
	int rank = 0, i, j;
        Matrix matrix = new Matrix();

	matrix = matrixLineSimplify();
	for(i = 0;i < matrix.getLine();i ++)
		for(j = 0;j < matrix.getRow();j ++)
			if(Math.abs(matrix.getElement(i, j)) > matrix.getAccuracy()){
				rank ++;
				break;
			}
	return rank;
}
   
   public double determinantValue()//计算行列式
{
	int i,j,k,p = 0;
	double change,value = 1.0;
        Matrix matrix = new Matrix(this);

	for(i = 0;i < matrix.getLine();i ++){
		for(j = i + 1;j < matrix.getLine();j ++)
                    for(;p < matrix.getLine();p ++){
			if(matrix.getElement(i, p) == 0&&matrix.getElement(j, p) != 0)
				for(k  = p;k < matrix.getRow();k ++)
					matrix.setElement(i, k, matrix.getElement(i, k) + matrix.getElement(j, k));
			else if(matrix.getElement(i, p) == 0&&matrix.getElement(j, p) == 0){
                            for(;j < matrix.getLine()&&matrix.getElement(j, p) == 0;j ++)
                                    ;
                                if(j == matrix.getLine()){
                                    j = i + 1;
                                    continue;
                                }
                                else{
                                    for(k = p;k < matrix.getRow();k ++)
					matrix.setElement(i, k, matrix.getElement(i, k) + matrix.getElement(j, k));
                                }
			}
			else if(matrix.getElement(i, p) != 0&&matrix.getElement(j, p) == 0)
				break;
			change = matrix.getElement(j, p) / matrix.getElement(i, p);
			for(k = p;k < matrix.getRow();k ++)
				matrix.setElement(j, k, matrix.getElement(j, k) - matrix.getElement(i, k) * change);
                        break;
		}
                p = i;
        }
	for(i = 0;i < matrix.getLine();i ++)
		value *= matrix.getElement(i, i);
	return value;
}
   
   public Vector matrixRowToVector(int n){//矩阵化为列向量组
	int i;
	Vector vector = new Vector();

        vector.setN(getLine());
	for(i = 0;i < getLine();i ++)
		vector.setElement(i, this.getElement(i, n));
	return vector;
}
   
   public static Matrix panelToMatrix(JTextField[] jtfMatrix, int line, int row, double accuracy){
        int i, j;
        Matrix matrix = new Matrix(line, row, accuracy);
        for(i = 0;i < line;i++)
            for(j = 0;j < row;j++){
                if(jtfMatrix[i * row + j].getText().equals(""))
                    return null;
                matrix.setElement(i, j, Double.parseDouble(jtfMatrix[i * row + j].getText()));
            }
        return matrix;
    }
    
    public static void matrixToPanel(JTextField[] jtfMatrix, Matrix matrix){
        int i, j;
        DecimalFormat df = new DecimalFormat(String.valueOf(matrix.getAccuracy()).replace('1', '0'));
        
        for(i = 0;i < matrix.getLine();i++)
            for(j = 0;j < matrix.getRow();j++){
                jtfMatrix[i * matrix.getRow() + j].setText(String.valueOf(df.format(matrix.getElement(i, j))));
            }
    }
}
