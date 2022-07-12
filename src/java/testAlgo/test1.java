/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testAlgo;

/**
 *
 * @author dinhd
 */
public class test1 {
    public static void main(String[] args) {
        String txt = "1,1";
        String ids[] = txt.split(",");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals("1")&&check==0) {
                    check++;
                    System.out.println(txtOutPut);
                    continue;
            }
            System.out.println(txtOutPut);
            if (txtOutPut.equals("")) {
                txtOutPut = ids[i];
            } else {
                txtOutPut = txtOutPut + "," + ids[i];
            }
             System.out.println(txtOutPut);
        }System.out.println(txtOutPut);
    }
}
