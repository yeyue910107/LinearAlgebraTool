/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;
/**
 *
 * @author 叶玥
 */
public class Vector {
    final static int MAX = 10;
    private double[] vectorArray = new double[MAX];
    private int n;
    private String name;
    
    public Vector(){
        
    }
    
    public Vector(double[] vectorArray, int n){
        this.vectorArray = Vector.arrayCopy(vectorArray);
        this.n = n;
    }
    
    public Vector(double[] vectorArray, int n, String name){
        this.vectorArray = Vector.arrayCopy(vectorArray);
        this.n = n;
        this.name = name;
    }
    
    public Vector(Vector p){
        this.vectorArray = Vector.arrayCopy(p.getVectorArray());
        this.n = p.getN();
    }
    
    private static double[] arrayCopy(double[] array){
        int i;
        double[] vectorArray = new double[MAX];
        
        for(i = 0;i < array.length;i++)
            vectorArray[i] = array[i];
        return vectorArray;
    }
    
    public double[] getVectorArray(){
        return this.vectorArray;
    }
    
    public void setVectorArray(double[] vectorArray){
        int i;
        
        for(i = 0;i < vectorArray.length;i++)
            this.vectorArray[i] = vectorArray[i];
    }
    
    public int getN(){
        return this.n;
    }
    
    public void setN(int n){
        this.n = n;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public double getElement(int i){
        return this.vectorArray[i];
    }
    
    public void setElement(int i, double n){
        this.vectorArray[i] = n;
    }
    
    public static Vector vectorCopy(Vector p){//向量复制
	int i;
        Vector vector = new Vector();
        
        vector.setVectorArray(p.getVectorArray());
        vector.setN(p.getN());
        return vector;
    }
    
    public void vectorInitial(){//向量初始化
        int i;
        
        for(i = 0;i < getN();i ++)
            this.setElement(i, 0.0);
    }
    
    public double vectorMod(){//向量的模
        int i;
	double mod = 0.0;

	for(i = 0;i < getN();i ++)
		mod += this.getElement(i) * this.getElement(i);
	mod = Math.sqrt(mod);
	return mod;
}
    
    public static Vector vectorPlus(Vector p1,Vector p2){//向量的和
	int i;
	Vector vector = new Vector();

        vector.setN(p1.getN());
	for(i = 0;i < p1.getN();i ++)
		vector.setElement(i, p1.getElement(i) + p2.getElement(i));
	return vector;
}
    
    public static Vector vectorMinus(Vector p1,Vector p2){//向量的差
	int i;
	Vector vector = new Vector();

        vector.setN(p1.getN());
	for(i = 0;i < p1.getN();i ++)
		vector.setElement(i, p1.getElement(i) - p2.getElement(i));
	return vector;
}
    
    public Vector numVectorMultiply(double x){//一个数乘以向量
        int i;
	Vector vector = new Vector();

        vector.setN(getN());
	for(i = 0;i < getN();i ++)
		vector.setElement(i, x * this.getElement(i));
	return vector;
}
    
    public Vector vectorNumDevide(double x){//向量除以一个数
        int i;
	Vector vector = new Vector();

        vector.setN(getN());
	for(i = 0;i < getN();i ++)
		vector.setElement(i, this.getElement(i) / x);
	return vector;
}
    
    public static double vectorMultiply(Vector p1,Vector p2){//向量的数量积
	int i;
	double value = 0.0;

	for(i = 0;i < p1.getN();i ++)
		value += p1.getElement(i) * p2.getElement(i);
	return value;
}
    
}
