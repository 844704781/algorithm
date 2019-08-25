package com.watermelon.num001sparseArray;


import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 二维数组转稀疏数组，稀疏数组再转二维数组
 */
public class SparseArray {

    private static final String path = "C:\\Users\\84470\\Desktop\\map.data";

    public static void write(int sparseArray[][]) throws IOException {
        StringBuilder sb =new StringBuilder();
        for (int[] row : sparseArray) {
            for(int data : row){
                sb.append(data);
                sb.append("\t");
            }
            sb.append("\n");
        }
        InputStream inputStream =new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
        OutputStream outputStream = new FileOutputStream(new File(path));
        byte[] buffer =new byte[1];
        while(inputStream.read(buffer)!=-1){
            outputStream.write(buffer);
        }
    }

    public static int[][] read() throws IOException {
        InputStream inputStream =new FileInputStream(new File(path));
        InputStreamReader inputStreamReader =new InputStreamReader(inputStream,StandardCharsets.UTF_8);
        StringBuilder sb =new StringBuilder();
        char[] temp =new char[1];

        while (inputStreamReader.read(temp)!=-1){
            sb.append(temp);
        }

        int x =0;
        int y =0;
        char[] data=sb.toString().toCharArray();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '\n') {
                x = x + 1;
            }

        }

        for (int i = 0; i < data.length; i++) {
            if(data[i]=='\n'){
                break;
            }
            if (data[i] == '\t') {

                y = y + 1;
            }
        }

        int sparseArray[][] = new int[x][y];
        String data1=sb.toString().replaceAll("\n","");
        String[]values=data1.split("\t");
        int k =0;
        for(int i = 0 ;i<sparseArray.length;i++){
            for(int j =0;j<sparseArray[i].length;j++){
                sparseArray[i][j]=Integer.parseInt(values[k]);
                k= k+1;
            }
        }
        return sparseArray;
    }


    public static void main(String[] args) throws IOException {
        /**
         * 1.创建原始二维数组
         */

        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始二维数组数据为:");
        for (int[] row : chessArr1) {
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


        /**
         * 2.获取二维数组里的非零值个数
         */
        int sum = 0;
        for (int[] row : chessArr1) {
            for(int data : row){
                if(data!=0){
                    sum = sum +1;
                }
            }
        }

        System.out.printf("二维数组中非零值个数为:%d\n",sum);

        /**
         * 3.创建稀疏数组的第一行
         */

        int[][] sparesArr = new int[sum + 1][3];
        sparesArr[0][0] = chessArr1.length;
        sparesArr[0][1] = chessArr1[0].length;
        sparesArr[0][2] = sum;

        System.out.println("稀疏数组第一行为:");

        for(int[]raw:sparesArr){
            for(int data :raw){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        /**
         * 4.將二维数组里的非零值在稀疏数组中表示
         */
        int k = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparesArr[k][0] = i;
                    sparesArr[k][1] = j;
                    sparesArr[k][2] = chessArr1[i][j];
                    k = k +1;
                }
            }
        }

        System.out.println("稀疏数组为:");
        for(int[]raw:sparesArr){
            for(int data :raw){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



        /**
         * 5.根据稀疏数组的第一行创建二维数组
         */

        int[][] chessArr2 = new int[sparesArr[0][0]][sparesArr[0][1]];

        /**
         * 6.根据稀疏数组的表示，将非零值填入二维数组
         */
        for(int i =1;i<sparesArr.length;i++){
            int x = sparesArr[i][0];
            int y = sparesArr[i][1];
            int val = sparesArr[i][2];
            chessArr2[x][y] =val;
        }

        System.out.println("根据稀疏数组转换为二维数组，二维数组为:");
        for (int[] row : chessArr2) {
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        System.out.println("将稀疏数组存入文件");
        write(sparesArr);
        System.out.println("稀疏数组存入成功");
        System.out.println("从文件中读取稀疏数组");
        int[][] sparseArr1=read();
        System.out.println("稀疏数组为:");
        for(int[]raw:sparseArr1){
            for(int data :raw){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
