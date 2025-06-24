/*package org.example.martes;

import java.util.List;
import java.util.Scanner;

public class DiasxMes {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Ingresa el numero del mes");
        int mes = s.nextInt();

        System.out.println("Ingresa el año (yyyy)");
        int anio = s.nextInt();

        int numDias = 0;

        List<Integer> meses31 = List.of(1,3,4,5,7,8,10,12);

        if (mes == 1 || mes == 3 ||mes == 5 ||mes == 7 ||mes == 8 ||mes == 10 ||mes == 12) {
            numDias = 31;
        } else  if (mes == 4 || mes == 6 ||mes == 9  ||mes == 11 ) {
            numDias = 30;
        } else {
            if (anio % 400 == 0 || anio % 4 == 0 || anio % 100 != 0 ){
                numDias = 29;
            }else{
                numDias = 28;
            }
        }


        switch (mes){
            case 1;
            case 3;
            case 5;
            case 7;
            case 8;
            case 10;
            case 12;
            numDias = 31;
            break;
            case 4;
            case 6;
            case 9;
            case 11;
            numDias = 30;



        }

        numDias = switch (mes){
            case 1,3,5,7,8,10,12 -> 31;
            case 4,6,9,11 -> 30;
            case 2
        }

    }

}
*/