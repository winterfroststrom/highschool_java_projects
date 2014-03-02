/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix;

/**
 *
 * @author Scalene
 */
public class Matrix {

    public static void main(String[] args){
        int terms = 30;
        double[][] a = new double[20][terms];
        for(int i = 0;i<a.length;i++){
            int x = (int)Math.floor(i/5)-1;
            for(int j = 0;j<a[0].length;j++){
                if(j == terms-1){
                    if(i < 5){
                        if(i%5 == 2)
                            a[i][j] = 1;
                    }else if(i < 10){
                        if(i%5 == 0)
                            a[i][j] = 0;
                        else if(i%5 == 4){
                            a[i][j] = 3;
                        } else
                            a[i][j] = 1;
                    }else if(i < 15){
                        if(i%5 == 0 || i%5 == 1)
                            a[i][j] = 2;
                        else if(i%5 == 3 || i%5 == 4){
                            a[i][j] = 3;
                        } else
                            a[i][j] = 1;
                    } else {
                        if(i%5 == 3 || i%5 == 4){
                            a[i][j] = 3;
                        } else
                            a[i][j] = 2;
                    }

                    continue;
                }
                int y = (i%5)-2;
                int power = (a[0].length - j -1)/2;
                a[i][j] = (int) (j%2==0?Math.pow(x,power):Math.pow(y,power));
            }
        }
        //displaymatrix(a);
        //rowEchelonReduction(a);
        //matrixRound(a,3);
        displaymatrix(a);
    }

    public static double[][] rowEchelonReduction(double[][] a){
        for(int i = 0;i<a.length;i++){
            a:
            for(int j = i;j<a[i].length;j++){
                if(j > a[i].length - 2) return a;
                for(int k = i;k<a.length;k++){
                    if(a[k][j] != 0 ){
                        rowSwap(a,k,i);
                        rowReduce(a,i,j);
                        break a;
                    }
                }
            }
        }
        return a;
    }

    public static double[][] rowSwap(double[][] a, int row1, int row2){
        double[] b = a[row1];
        a[row1] = a[row2];
        a[row2] = b;
        return a;
    }

    public static double[][] rowReduce(double[][] a,int row, int column){
        double[] b = new double[a[row].length];
        rowScale(a, row, 1/a[row][column]);
        for(int i = 0; i<a[row].length;i++){
            b[i] = a[row][i];
        }
        for(int i = 0; i<a.length;i++){
            double factor = -a[i][column];
            if(i == row)
                continue;
            for(int j =0;j<a[i].length;j++){
                a[i][j] += (b[j]*factor);
            }
        }
        return a;
    }

    public static double[][] matrixRound(double[][] a,int degree){
        for(int i = 0; i<a.length;i++){
            for(int j = 0; j<a[0].length;j++){
                a[i][j] = Math.round(a[i][j]*Math.pow(10,degree))/Math.pow(10,degree);
            }
        }
        return a;
    }

    public static double[][] rowAdd(double[][] a, int rowFrom, int rowTo){
        for(int i = 0; i< a[rowFrom].length;i++){
            a[rowTo][i] += a[rowFrom][i];
        }
        return a;
    }

    public static double[][] rowScale(double[][] a, int row, double factor){
        for(int i = 0; i< a[row].length;i++){
            a[row][i] = a[row][i]*factor;
        }
        return a;
    }

    public static void displaymatrix(double[][] a){
        for(int i = 0;i<a.length;i++){
            System.out.print("Row "+ i +": [");
            for(int j = 0;j<a[0].length;j++){
                if(j + 1 < a[0].length)
                    System.out.print(a[i][j] + ",\t");
                else
                    System.out.print(a[i][j]);
            }
            if(i + 1 < a.length)
                System.out.println();
            else
                System.out.println("]");
        }
        System.out.println();
    }

    public static double[][] transpose(double[][] a){
        double[][] b = new double[a[0].length][a.length];
        for(int i = 0;i<b.length;i++){
            for(int j = 0;j<b[0].length;j++){
                b[i][j] = a[j][i];
            }
        }
        return b;
    }

    public static double[][] matrixproduct(double[][] a, double[][] b){
        if(a[0].length != b.length)
            return new double[0][];
        double[][] c = new double[a.length][b[0].length];
        double[][] d = transpose(b);
        for(int i = 0;i<c.length;i++){
            for(int j = 0;j<c[0].length;j++){
                c[i][j] = dotproduct(a[i],d[j]);
            }
        }
        return c;
    }

    public static double[][] scalarproduct(int scalar, double[][] a){
        double[][] b = new double[a.length][];
        for(int i = 0; i<a.length;i++){
            b[i] = new double[a[0].length];
            for(int j = 0;j<a[i].length;j++){
                b[i][j] = scalar*a[i][j];
            }
        }
        return b;
    }

    public static double dotproduct(double[] a, double[] b ){
        if(a.length != b.length)
            return 0;
        int res = 0;
        for(int i = 0;i<a.length;i++){
            res += (a[i]*b[i]);
        }
        return res;
    }

    public static double determinate2x2(double[][] a){
        if(a.length != 2 && a[0].length != 2)
            return 0;
        return a[0][0]*a[1][1] - a[1][0]*a[0][1];
    }
}
