import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class brainfuck {

    public static byte[] memory = new byte[6553];
    public static int pointer = memory[0]; 
    public static String history = "";

    public static void main(String[] args) {
        try{
            Path path = Paths.get(args[0]).toAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
            int i = 0;
            int c = 0;
            int temp = 0;

            while((c = reader.read()) != -1){
                char element = (char) c;
                history += element;
            }
            for(i = 0; i< history.length(); i++){
                if(history.charAt(i) == '>') pointer++;
                if(history.charAt(i) == '<') pointer--;
                if(history.charAt(i) == '+') memory[pointer]++;
                if(history.charAt(i) == '-') memory[pointer]--;
                if(history.charAt(i) == '['){
                    if(memory[pointer] > 0){
                        if(memory[pointer] == 0){
                            i++;
                            while (temp > 0 || history.charAt(i) != ']'){
                                if (history.charAt(i) == '[')
                                    temp++;
                                else if (history.charAt(i) == ']')
                                    temp--;
                                i++;
                            }
                        }
                    }
                }
                if(history.charAt(i) == ']'){
                    if(memory[pointer] > 0){
                        i--;
                        while (temp > 0 || history.charAt(i) != '['){
                            if (history.charAt(i) == ']')
                                temp ++;
                            if (history.charAt(i) == '[')
                                temp --;
                            i--;
                        }
                        i--;
                    }
                }
                if(history.charAt(i) == '.'){
                    System.out.print((char) memory[pointer]);
                    break;
                }

                if(history.charAt(i) == ','){
                    Scanner scan = new Scanner(System.in);
                    memory[pointer] = (byte) (scan.next().charAt(0));
                    scan.close();
                }
                if(history.charAt(i) == '#'){
                    Scanner scan = new Scanner(System.in);
                    int range = scan.nextInt();
                    scan.close();
                    if(range<= 6553){
                        for(i = 0; i<range; i++){
                            System.out.print(memory[i]+",");
                        }
                    }else{
                        System.out.println("Memory out of bounds.");
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
