package Java6.Controller;

import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class nhap {

	public static void main(String[] args) {
		//tim();
		sapXep();
	}

	static void tim() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap vao 1 xau: ");
		String s = sc.nextLine();
		int[] len;
		String[] ss = s.split(" ");
		len = new int[ss.length];
		
		int max = 0;
		for (int i = 0; i < ss.length; i++) {
			int l = ss[i].length();
			len[i] = l;
			if (l > max) {
				max = l; 
			}
		}
		int viTriMax;
		for (int i = 0; i < ss.length; i++) {
			if (len[i] == max) {
				viTriMax = i;
				System.out.print("Tu Dai Nhat: " + ss[i] + ", vi tri: " + viTriMax +" \n");
			}
		}
		
	}
	
	static void sapXep() {
		//giam dan
		int[] mang = {12,2,8,28,15,24,5,4,10};
		int temp = mang[0];
        for (int i = 0 ; i < mang.length; i++) {
            for (int j = i + 1; j < mang.length; j++) {
                if (mang[i] < mang[j]) {
                    temp = mang[j];
                    mang[j] = mang[i];
                    mang[i] = temp;
                }
            }
            System.out.print(mang[i] + " ");
        }
	}
}
