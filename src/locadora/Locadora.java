/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Adriana
 */
public class Locadora {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner inFile;
        try {
            inFile = new Scanner(new FileReader("C:\\Locadora\\teste.txt"));
            readFile(inFile);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }
    
    private static void readFile(Scanner file){
        int qPessoas;
        boolean fidelidade = false;
        if(!file.hasNext()){
            System.out.println("Arquivo em branco!");
            return;
        }
        int count = 1;
        while(file.hasNext()){
            String text = file.next();
            text = text.toUpperCase();
            String[] ops = text.split(":");

            if(ops.length < 3){
                System.out.println("Formato dos parametros não confere com o padrão! favor seguir ");
                System.out.println("Favor seguir o exemplo: <TIPO_CLIENTE>:<QUANTIDADE_PASSAGEIROS>:<DATA1>,<DATA2>,<DATA3>... ");
                return;
            }
            if((!ops[0].equals("NORMAL")) && (!ops[0].equals("PREMIUM"))){
                System.out.println("O parametro <TIPO_CLIENTE>, não pode ser reconhecido!");
                System.out.println("Favor utilizar a nomeclatura (normal) para cliente comum e (premium) para cliente fidelidade.");
                return;
            }
            fidelidade = ops[0].equals("PREMIUM");

            try {
                qPessoas = Integer.parseInt(ops[1]);
                if((qPessoas < 1) || (qPessoas > 7)){
                    System.out.println("O parametro <QUANTIDADE_PASSAGEIROS>, não pode ser reconhecido!");
                    System.out.println("Favor fornecer um numero inteiro de 1 a 7.");
                    return;
                }
            } catch (NumberFormatException e) {	  
                System.out.println("O parametro <QUANTIDADE_PASSAGEIROS>, não pode ser reconhecido!");
                System.out.println("Favor fornecer um numero inteiro de 1 a 7.");
                return;
            }

            String[] datas = ops[2].split(",");
            if(datas.length < 1){
                System.out.println("O parametro <DATA>, não pode ser reconhecido!");
                System.out.println("Favor fornecer uma data no formato 01Fev2018.");
                return;
            }

            if(qPessoas > 4){
                System.out.println("O carro mais barato na consulta " + count +" é: Navigator (NorthCar)");
            }else{
                double SouthCar = 0;
                double WestCar = 0;
                for (String data : datas) {
                    Date date = getDate(data);
                    if(date == null){
                        return; 
                    }
                    boolean fimSemana = (date.getDay() == 0 || date.getDay() == 6); 
                    if(fimSemana){
                        if(fidelidade){
                            SouthCar += 90;
                            WestCar += 90;
                        }else{
                            SouthCar += 200;
                            WestCar += 200;
                        }
                    }else{
                       if(fidelidade){
                            SouthCar += 150;
                            WestCar += 150;
                        }else{
                            SouthCar += 210;
                            WestCar += 530;
                        } 
                    }
                }

                if(SouthCar > WestCar){
                    System.out.println("O carro mais barato na consulta " + count +" é: Ferrari (WestCar)");
                    return;
                }
                System.out.println("O carro mais barato na consulta " + count +" é: GOL (SouthCar)");
            }
            
            count++;
        }
    }
    
    
    private static Date getDate(String date){
        int dia;
        int mes;
        int ano;
        try {
            dia = Integer.parseInt(date.substring(0,2));
            ano = Integer.parseInt(date.substring(5,9));
            mes = getMes(date.substring(2,5));
            
        } catch (NumberFormatException e) {
            System.out.println("O parametro <DATA>, não pode ser reconhecido!");
            System.out.println("Favor fornecer uma data no formato 01Fev2018.");
            return null;
        }
        
        if((dia < 1) || (mes < 1) || (ano < 1)){
            System.out.println("O parametro <DATA>, não pode ser reconhecido!");
            System.out.println("Favor fornecer uma data no formato 01Fev2018.");
            return null;
        }
         
        return new Date(mes + "/" + dia + "/" + ano);
    }
    
    private static int getMes(String mes){
        switch(mes){
            case "JAN" :
                return 1;
            case "FEV" :
                return 2;
            case "MAR" :
                return 3;
            case "ABR" :
                return 4;
            case "MAI" :
                return 5;
            case "JUN" :
                return 6;
            case "JUL" :
                return 7;
            case "AGO" :
                return 8;
            case "SET" :
                return 9;
            case "OUT" :
                return 10;
            case "NOV" :
                return 11;
            case "DEZ" :
                return 12;
            default:
                return 0;
        }
    }
    
}
