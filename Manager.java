import java.util.ArrayList;

class Manager {

    public String TableName_CreateTable (String input) {
        return input.substring(13, input.indexOf("(")-1);
    }

    ArrayList<String> ColumnName_CreateTable (String input) {
        ArrayList<String> ColumnName = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i=input.indexOf("(")+1;i<=input.indexOf(")");i++) {
            if (input.charAt(i)!=',' && input.charAt(i)!=')')
                temp.append(input.charAt(i));
            else {
                if (temp.length()>0)
                    if (! ColumnName.contains(temp.toString()))
                         ColumnName.add(temp.toString());
                    else
                        ColumnName.add("-1ERROR!");
                temp = new StringBuilder();
            }
        }
        return  ColumnName;
    }

    public String TableName_DropTable (String input) {
        return input.substring(11, input.indexOf(";"));
    }

    public String TableName_InsertInto (String input) {
        StringBuilder temp = new StringBuilder();
        for (int i=12;input.charAt(i)!=' ';i++)
            temp.append(input.charAt(i));
        return temp.toString();
    }

    ArrayList<String> Fields_InsertInto (String input) {
        ArrayList<String> Fields = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i=0;i<input.length();i++)
            if ( (int) input.charAt(i)==39)
                input=input.replace("'","");
        for (int i=input.indexOf("(")+1;i<=input.indexOf(")");i++) {
            if (input.charAt(i)!=',' && input.charAt(i)!=')')
                temp.append(input.charAt(i));
            else {
                if (temp.length()>0)
                    Fields.add(temp.toString());

                temp = new StringBuilder();
            }
        }
        return  Fields;
    }

    String TableName_Select_Delete (String input) {
        StringBuilder temp = new StringBuilder();
        for (int i=input.indexOf("FROM")+5;input.charAt(i)!=' ' && input.charAt(i)!=';';i++)
            temp.append(input.charAt(i));
        return temp.toString();
    }

    ArrayList<String> ColumnName_InsertInto (String input) {
        ArrayList<String> ColumnName = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i=input.indexOf("(")+1;i<=input.indexOf(")");i++) {
            if (input.charAt(i)!=',' && input.charAt(i)!=')' && input.charAt(i)!=' ')
                temp.append(input.charAt(i));
            else {
                if (temp.length()>0)
                    if (! ColumnName.contains(temp.toString()))
                        ColumnName.add(temp.toString());

                temp = new StringBuilder();
            }
        }
        return  ColumnName;
    }

    ArrayList<String> Fields_InsertInto2 (String input) {
        ArrayList<String> Fields = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        input = input.substring(input.indexOf("VALUES"),input.length()-1);
        input=input.replaceAll("'","");
        for (int i=input.indexOf("(")+1;i<=input.indexOf(")");i++) {
            if (input.charAt(i)!=',' && input.charAt(i)!=')')
                temp.append(input.charAt(i));
            else {
                if (temp.length()>0)
                    if (! Fields.contains(temp.toString()))
                        Fields.add(temp.toString());

                temp = new StringBuilder();
            }
        }
        return  Fields;
    }

    ArrayList<String> ColumnName_Select (String input) {
        ArrayList<String> ColumnName = new ArrayList<>();
        if (input.contains("*")) {
            ColumnName.add("*");
            return ColumnName;
        }
        StringBuilder temp = new StringBuilder();
        for (int i=input.indexOf(" ")+1;i<=input.indexOf("FROM")-1;i++) {
            if (input.charAt(i)!=',' && input.charAt(i)!=' ')
                temp.append(input.charAt(i));
            else {
                if (temp.length()>0)
                    if (! ColumnName.contains(temp.toString()))
                        ColumnName.add(temp.toString());

                temp = new StringBuilder();
            }
        }
        return  ColumnName;
    }
    public String ColumnName_Where (String input) {
        if (input.contains("WHERE")) {
            input = input.substring(input.indexOf("WHERE"));
            StringBuilder temp = new StringBuilder();
            for (int i=input.indexOf("WHERE") + 6;input.charAt(i)!=' ' && input.charAt(i)!='=';i++)
                temp.append(input.charAt(i));
            return temp.toString();
        }
        else
            return "NOWHERE!";
    }
    public String Field_Where (String input) {
        if (input.contains("WHERE")) {
            input = input.substring(input.indexOf("WHERE"));
            input = input.substring(input.indexOf("'") + 1, input.indexOf(";") - 1);
            return input.trim();
        }
        else
            return "NOWHERE!";
    }
    public String TableName_Update (String input) {
        return input.substring(input.indexOf(" ")+1,input.indexOf("SET")-1);
    }
    public ArrayList<String> ColumnName_Update (String input) {
        ArrayList<String> ColmnName = new ArrayList<>();
        int index = input.indexOf("WHERE")-1;
        if (index==-2)
            index=input.length()-1;
        input = input.substring(input.indexOf("SET")+4,index) + ','+' ';
        while (input.contains("=")) {
            ColmnName.add(input.substring(0,Math.min(input.indexOf("="),input.indexOf(" "))));
            input = input.substring(input.indexOf(",")+1);
            input = input.trim();
        }
        return ColmnName;
    }
    public ArrayList<String> Field_Update (String input) {
        ArrayList<String> Field = new ArrayList<>();
        int index = input.indexOf("WHERE")-1;
        if (index==-2)
            index=input.length()-1;
        input = input.substring(input.indexOf("SET")+4,index) + ',';
        while (input.contains("=")) {
            Field.add(input.substring(input.indexOf("'")+1,input.indexOf("'",input.indexOf("'")+1)));
            input = input.substring(input.indexOf(",")+1);
            input = input.trim();
        }
        return Field;
    }
}
