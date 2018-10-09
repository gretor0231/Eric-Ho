import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Main {

    // Method to sort a string alphabetically
    public static int compareString(String inputString1, String inputString2,int j,int index) {
        Character tempArray[] = new Character[inputString1.length()];
        for (int i = 0; i < inputString1.length(); i++) {
            tempArray[i] = inputString1.charAt(i);
        }
        Character tempArray2[] = new Character[inputString2.length()];
        for (int i = 0; i < inputString2.length(); i++) {
            tempArray2[i] = inputString2.charAt(i);
        }
        // Sort, ignoring case during sorting
        // return new sorted string
        if (tempArray[0] >= tempArray2[0]) {
            return j;
        } else
            return index;
        }

    public static void main(String[] args) throws IOException{
//        FileInputStream in = null;
//        FileOutputStream out = null;

        try {
            File file = new File("input.txt");
            File fileout = new File("output.txt");
            FileWriter fw = new FileWriter(fileout);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            String line;


            ArrayList<String> l = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {

                l.add(line);

            }
//            System.out.println(compareString(l.get(2),l.get(1),2,1));
            while (l.size() != 0) {
                int shorterString = l.get(0).length();
                int index = 0;
                for (int i = 0; i < l.size(); i++) {
                    if (l.get(i).length() < shorterString) {
//                        System.out.println("yoyo");
                        shorterString = l.get(i).length();
                        index = i;

//                        if (l.get(i).length() == shorterString) {
////                        System.out.println("hihi");
//                            shorterString = compareString(l.get(i),l.get(index),i,index);
//                        }
                    }
                }

                System.out.println(l.get(index));
//                out = l.get(index);
                fw.write(l.get(index));
                fw.write("\n");
                l.remove(l.get(index));
            }


//            stringBuffer.append(l.toString());
//            System.out.println(stringBuffer);
//                String outputString = sortString(inputString);

//            stringBuffer.append(inputString);


            fileReader.close();
            fw.close();
//            System.out.println(stringBuffer.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
