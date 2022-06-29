import java.util.Scanner;
import java.io.*;

public class Main {
    static void runStackTestCases(){
        try{
            Scanner inps = new Scanner(new FileInputStream("stackTestCases.txt"));
            Scanner outs = new Scanner(new FileInputStream("stackOut.txt"));
            int t = inps.nextInt();
            System.out.printf("** Running %d tests for part A: stack **\n\n", t);
            int passed = 0;
            for(int ti=1;ti<=t;++ti)
            {
                MyStack s = new MyStack(); 
                int n = inps.nextInt();
                boolean status = true;
                while((n--)>0)
                {
                    char op = inps.next().charAt(0);
                    switch(op){
                        case 'I':{
                            int v = inps.nextInt();
                            s.push((Object)v);
                            break;
                        }
                        case 'P': {
                            String ol = "";
                            while(ol.length()==0)
                                ol = outs.nextLine();
                            try{
                                int v = (Integer)s.pop();
                                if(!ol.equals(Integer.toString(v)))
                                {
                                    status = false;
                                    System.out.println("In test case "+ ti);
                                    System.out.println("\tExpected: "+ol);
                                    System.out.println("\tGot: "+v);
                                }
                            }
                            catch(EmptyStackException e){
                                if(!ol.equals("E"))
                                {
                                    status = false;
                                    System.out.println("In test case "+ ti);
                                    System.out.println("\tExpected: "+ol);
                                    System.out.println("\tGot: EmptyStackException");
                                }
                            }
                            break;
                        }
                        case 'T': {
                            String ol = "";
                            while(ol.length()==0)
                                ol = outs.nextLine();
                            try{
                                int v = (Integer)s.top();
                                if(!ol.equals(Integer.toString(v)))
                                {
                                    status = false;
                                    System.out.println("In test case "+ ti);
                                    System.out.println("\tExpected: "+ol);
                                    System.out.println("\tGot: "+v);
                                }
                            }
                            catch(EmptyStackException e){
                                if(!ol.equals("E"))
                                {
                                    status = false;
                                    System.out.println("In test case "+ ti);
                                    System.out.println("\tExpected: "+ol);
                                    System.out.println("\tGot: EmptyStackException");
                                }
                            }
                            break;
                        }
                        case 'E':{
                            String ol = "";
                            while(ol.length()==0)
                                ol = outs.nextLine();
                            boolean res = s.isEmpty();
                            if((res && ol.equals("F")) || (!res && ol.equals("T")))
                            {
                                status = false;
                                System.out.println("In test case "+ ti);
                                System.out.println("\tExpected: "+!res);
                                System.out.println("\tGot: "+res);
                            }
                            break;
                        }
                        case 'S':{
                            String ol = "";
                            while(ol.length()==0)
                                ol = outs.nextLine();
                            String res = s.toString();
                            if(!ol.equals(res))
                            {
                                status = false;
                                System.out.println("In test case "+ ti);
                                System.out.println("\tExpected: "+ol);
                                System.out.println("\tGot: "+res);
                            }
                            break;
                        }
                    }
                }
                if(status){
                    ++passed;
                    System.out.printf("Test case %d passed!\n", ti);
                }
                else 
                    System.out.printf("Test case %d FAILED :/\n", ti);
            }
            System.out.printf("\n[%d/%d] passed\n\n", passed, t);
            inps.close();
            outs.close();
        }
        catch(FileNotFoundException e){
            System.out.println("stackTestCases.txt not found");
        }  
    }

    static void runPostfixTestCases(){
        Calculator calc = new Calculator();
        try{
            Scanner inps = new Scanner(new FileInputStream("postFixTestCases.txt"));
            Scanner outs = new Scanner(new FileInputStream("postFixOut.txt"));
            int t = inps.nextInt();
            System.out.printf("** Running %d tests for part B: postfix evaluator **\n\n", t);
            int passed = 0;
            for(int ti=1;ti<=t;++ti)
            {
                String inp = "";
                while(inp.length()==0)
                    inp = inps.nextLine();
                String gold_out = "";
                while(gold_out.length()==0)
                    gold_out = outs.nextLine();
                boolean status = true;
                try{
                    int ans = calc.evaluatePostFix(inp);
                    if(ans != Integer.parseInt(gold_out)){
                        status = false;
                        System.out.println("In test case "+ ti);
                        System.out.println("\tExpected: "+gold_out);
                        System.out.println("\tGot: "+ans);
                    }
                }
                catch(InvalidPostfixException e){
                    if(!gold_out.equals("E")){
                        status = false;
                        System.out.println("In test case "+ ti);
                        System.out.println("\tExpected: "+gold_out);
                        System.out.println("\tGot: InvalidPostfixException");
                    }
                }
                if(status){
                    ++passed;
                    System.out.printf("Test case %d passed!\n", ti);
                }
                else 
                    System.out.printf("Test case %d FAILED :/\n", ti);
            }
            System.out.printf("\n[%d/%d] passed\n\n", passed, t);
            inps.close();
            outs.close();
        }
        catch(FileNotFoundException e){
            System.out.println("postFixTestCases.txt not found");
        }     
    }

    static void runCalcTestCases(){
        Calculator calc = new Calculator();
        try{
            Scanner inps = new Scanner(new FileInputStream("calcTestCases.txt"));
            Scanner outs = new Scanner(new FileInputStream("calcOut.txt"));
            int t = inps.nextInt();
            System.out.printf("** Running %d tests for part B: calculator **\n\n", t);
            int passed = 0;
            for(int ti=1;ti<=t;++ti)
            {
                String inp = "";
                while(inp.length()==0)
                    inp = inps.nextLine();
                String gold_out = "";
                while(gold_out.length()==0)
                    gold_out = outs.nextLine();
                boolean status = true;
                try{
                    String ans = calc.convertExpression(inp);
                    if(!ans.equals(gold_out))
                    {
                        status = false;
                        System.out.println("In test case "+ ti);
                        System.out.println("\tExpected: "+gold_out);
                        System.out.println("\tGot: "+ans);
                    }
                }
                catch(InvalidExprException e){
                    if(!gold_out.equals("E")){
                        status = false;
                        System.out.println("In test case "+ ti);
                        System.out.println("\tExpected: "+gold_out);
                        System.out.println("\tGot: InvalidExprException");
                    }
                }
                if(status){
                    ++passed;
                    System.out.printf("Test case %d passed!\n", ti);
                }
                else {
                    System.out.printf("Test case %d FAILED :/\n", ti);
                }
            }
            System.out.printf("\n[%d/%d] passed\n\n", passed, t);
            inps.close();
            outs.close();
        }
        catch(FileNotFoundException e){
            System.out.println("calcTestCases.txt not found");
        }     
    }

    public static void main(String[] args){
        runStackTestCases();
        runPostfixTestCases();
        runCalcTestCases();
    }
}
