import java.io.PrintWriter;
import java.io.*;
import java.io.File;
import java.util.*;

public class ChemDecoder
{
  /*Write a program that accepts data from one column and returns the corresponding data from the other columns in the stored file.
   * If input data matches different rows, then return all matching row data. For example, a bond length input of 0.12 should return triple
   * bond C|||C and bond energy 890kj/mol and single bond N|O and bond energy 250kj/mol
   * 
   * */
 
  public static void main(String[] args) throws IOException
  {
    String filename = "output.txt";
    PrintWriter output = null;
    try {
    output = new PrintWriter(filename);
  }
  catch (FileNotFoundException e){
    e.printStackTrace();
  }
    
    
    
    Scanner input = new Scanner(System.in);
    String strResponse="";
    ArrayList<String> ChemData = readdata("data.txt");// create this method returns array
    System.out.println("Chapter 7");
      
      System.out.println("L - Bond Length");
      System.out.println("E - Bond Energy");
      System.out.println("N - Number of Bonds\n");
      System.out.println("Q - quit program.");
     
    
    do
    {
      strResponse = input.nextLine();
      strResponse = strResponse.toUpperCase();
      
      
      
      //process strResponse with a switch() statement
      // if "L" then show all bonds with that value
      //  if "N" show all chemicals with that number
      //  of bonds.
      if (strResponse.equals("L")) 
        { 
        System.out.println("Please enter the length of the bond");
        double bondlength = input.nextDouble();
        ArrayList<String> lengthlist = findlength(bondlength,ChemData);
        for (int x=0; x<lengthlist.size(); x++) {output.print(lengthlist.get(x));}
      }
      else if (strResponse.equals("E"))
      {
        System.out.println("Please enter the energy of the bond");
        int bondnumber = input.nextInt();
        ArrayList<String> energylist = findenergy(bondnumber,ChemData);
        for (int x=0; x<energylist.size(); x++) {output.print(energylist.get(x));}
      }
      else if (strResponse.equals("N"))
      {
        System.out.println("Please enter the amount of bonds");
        int bondcount = input.nextInt();
        ArrayList<String> numberlist = findbondnumber(bondcount, ChemData);
        for (int x=0; x<numberlist.size(); x++) {output.print(numberlist.get(x));}
        
      }
      /*System.out.println("L - Bond Length\n");
      System.out.println("E - Bond Energy\n");
      System.out.println("N - Number of Bonds\n");
      System.out.println("Q - quit program.");**/
        
      
    }
    while (!strResponse.equals("Q"));
    System.out.println("\n\nThank you and have a nice day.");
    

    
  }
  public static ArrayList<String> readdata(String f) throws IOException
  {
    File inputFile = new File(f);
      Scanner file = new Scanner(inputFile);
      ArrayList<String> list = new ArrayList<String>();
      while (file.hasNext())//each line of the file
      {
        list.add(file.nextLine());
      }
      return list;
  }
  public static ArrayList<String> findlength(double a, ArrayList<String> b) throws IOException//gets the bond length 
  {
    ArrayList<String> outputlist = new ArrayList<String>();
    String currentline = "";
    CharSequence ch = Double.toString(a);

    for (int j=0; j< b.size(); j++)//for every line in the array
       {
         currentline = b.get(j);
         if (currentline.contains(ch))
         {
          outputlist.add(currentline);
         }
       }
    return outputlist;

  }
  public static ArrayList<String> findenergy(int a, ArrayList<String> b) throws IOException//gets the bond length 
  {
    ArrayList<String> outputlist = new ArrayList<String>();
    String currentline = "";
    CharSequence ch = Integer.toString(a);

    for (int j=0; j< b.size(); j++)//for every line in the array
       {
         currentline = b.get(j);
         if (currentline.contains(ch))
         {
          outputlist.add(currentline);
         }
       }
    return outputlist;
 
  }
  public static ArrayList<String> findbondnumber(int a, ArrayList<String> c)throws IOException
  {
    ArrayList<String> outputlist = new ArrayList<String>();
    ArrayList<String> singlebond = new ArrayList<String>();
    ArrayList<String> doublebond = new ArrayList<String>();
    ArrayList<String> triplebond = new ArrayList<String>();
    
    String currentline = "";
    for (int j=0; j<c.size(); j++)
    {
      currentline = c.get(j);
      int i = 0;
      while(!Character.isDigit(currentline.charAt(i))) {i++;}
      String energystr = currentline.substring(i,i+3);
      double energynumber = Double.parseDouble(energystr.trim());
      if (energynumber < 600){singlebond.add(currentline);}
      if (energynumber > 600 && energynumber < 800) {doublebond.add(currentline);}
      if (energynumber > 800){triplebond.add(currentline);}
    }
    switch (a)
    {
      case 1: 
      for (String x : singlebond){outputlist.add(x);} break;
      case 2:
      for (String x: doublebond) {outputlist.add(x);} break;
      case 3: 
      for (String x: triplebond) {outputlist.add(x);} break;
      default: outputlist.add("Not a correct bond");
    }
    return outputlist;
  }
}
  
  
  
  
  
  
  
  
  
  
  
  
  

  
