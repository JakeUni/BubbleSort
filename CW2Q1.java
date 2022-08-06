import java.io.*;

/**This is my implementation of a BubbleSort in java, I take the value of names.txt into
 * a string array and sort it. This is then ouputted to a new file.
 */
public class CW2Q1 {
    /**
     * This is the main class which simple  reads names.txt and sorts it using a bubble sort
     * @param args
     * @throws IOException
     */
    public static void main(String[] args   ) throws IOException {
        String[] names = new String[6000];
        //file reader to get the names from the file character by character
        FileReader fr;
        fr = new FileReader("names.txt");
        int i;
        int k = 0;
        char c;
        //currentName will store the current name
        String currName = "";
        //while the reader isnt -1
        while ((i = fr.read()) != -1) {
            //if the character is a " than do nothing as we want to get rid of them
            //else if the character is a , than we have finished reading a word and we want to add it
            //to our word array
            //and if it doesnt meet either of these than add it to the current name
            c = (char) i;
            if(c == '"'){
            }else if(c == ','){
                names[k] = currName;
                k++;
                currName = "";
            }else{
                currName = currName + c;
            }
        }
        //getting the last name
        names[k] = currName;

        //Bubble sort which will loop through  all the values that are in the word array,
        //it will do this twice as we need to compare every letter to each word
        for(int x=0;x<k;x++) {
            for(int y=0;y<k-x;y++) {
                if(compare(names[y],names[y+1])) {
                    //if the letters compared reutrns true then swap them
                    currName=names[y];
                    names[y]=names[y+1];
                    names[y+1]=currName;
                }
            }
        }
        //write the now sorted file to the array
        writeFile(names,"bubbleSorted.txt",true);

    }

    /**
     * this is a function to return is the two strings given are the same
     * @param a the first string to be compared
     * @param b the second string to be comopared
     * @return this will return true if the string a is alphabetically after B,
     * And this will return false if the string b is alphabetically before A
     * if the two atrings are identical false is also returned as it is irrelevant
     */
    private static boolean compare(String a, String b)
    {
        int i = 0;
        //while we arent at the end of either names
        while (i < a.length() && i < b.length()) {
            //compare and decide
            if (Character.getNumericValue(a.charAt(i)) > Character.getNumericValue(b.charAt(i))) {
                return true;
            }
            else if (Character.getNumericValue(a.charAt(i)) < Character.getNumericValue(b.charAt(i))) {
                return false;
            }
            i++;
        }
        if (b.length() < a.length()) {
            return true;
        }
        return false;
    }

    /**
     * This function is used to re write names to the text file which is how we show it is sorted
     * @param words the array of names to be sorted
     * @param output the name of the file that they will be written to
     * @param adding this decies if the names are just printed or if they are printed with "",
     */
    private static void writeFile(String[] words, String output , boolean adding) {
        try {
            File file = new File(output);
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < words.length; i++) {
                if(adding){

                    out.append("\""  + words[i] + "\",");

                }else{
                    out.append(words[i]);
                }
            }
            out.close();
        }catch(IOException e) {
            //if the file is not found tell the user this
            System.err.print("File " + output + " not found, please attempt again");
        }
    }

}
