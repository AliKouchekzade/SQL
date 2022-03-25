import java.util.ArrayList;

class Table {
    String TableName;
    int ColumnNum;
    ArrayList<String> ColumnName;
    int RecordNum;
    ArrayList<Record> records = new ArrayList<>();

    Table (String TableName,ArrayList<String> ColumnName) {
        this.TableName=TableName;
        this.ColumnName=ColumnName;
        this.ColumnNum=ColumnName.size();
        this.RecordNum=0;
    }

    public boolean InsertInto (ArrayList<String> Fields) {
        if (Fields.size()==ColumnNum) {
            records.add(new Record(Fields));
            this.RecordNum++;
            return true;
        }
        else
            return false;
    }
    public boolean InsertInto (ArrayList<String> SomeFields,ArrayList<String> SomeColumn) {
        ArrayList<String> Fields = new ArrayList<>();
        if (SomeFields.size()!=SomeColumn.size())
            return false;
        for (int i=0;i<ColumnNum;i++) {
            Fields.add("null");
        }
        for (int i=0;i<SomeColumn.size();i++) {
            int index = ColumnName.indexOf(SomeColumn.get(i));
            if (index==-1)
                return false;
            Fields.set(index,SomeFields.get(i));
        }
        records.add(new Record(Fields));
        this.RecordNum++;
        return true;
    }

    public int Select (ArrayList<String> SomeColumn,String ColumnWhere,String FieldsWhere) {
        if (!ColumnName.contains(ColumnWhere) && !ColumnWhere.equals("NOWHERE!"))
            return -1;
        int Where = ColumnName.indexOf(ColumnWhere);
        boolean where = false;
        if (Where==-1) {
            Where=0;
            where=true;
        }
        if (SomeColumn.contains("*")) {
            SomeColumn.remove(0);
            for (int i=0;i<ColumnNum;i++)
                SomeColumn.add(ColumnName.get(i));
        }
        for (String s : SomeColumn) {
            int index = ColumnName.indexOf(s);
            if (index == -1)
                return -1;
        }
        int f = 0;
        for (int j=0;j<RecordNum;j++) {
            if (records.get(j).Fields.get(Where).equals(FieldsWhere) || where)
                f++;
        }
        if (f==0)
            return 0;
        for (int i=0;i< SomeColumn.size();i++) {
            if (i== SomeColumn.size()-1)
                System.out.println(SomeColumn.get(i));
            else
                System.out.print(SomeColumn.get(i)+" | ");
        }
        for (int j=0;j<RecordNum;j++) {
            if (records.get(j).Fields.get(Where).equals(FieldsWhere) || where) {
                for (int i = 0; i < SomeColumn.size(); i++) {
                    int index = ColumnName.indexOf(SomeColumn.get(i));
                    if (i == SomeColumn.size() - 1)
                        System.out.println(records.get(j).Fields.get(index));
                    else
                        System.out.print(records.get(j).Fields.get(index) + " | ");
                }
            }
        }
        return 1;
    }
    public int Update (ArrayList<String> ColumnUpdate,ArrayList<String> FieldUpdate,String ColumnWhere,String FieldsWhere) {
        if (!ColumnName.contains(ColumnWhere) && !ColumnWhere.equals("NOWHERE!"))
            return -1;
        for (String i : ColumnUpdate)
            if (!ColumnName.contains(i))
                return -1;
        int Where = ColumnName.indexOf(ColumnWhere);
        int Effect = 0;
        boolean where = false;
        if (Where==-1) {
            where = true;
            Where=0;
        }
        for (int i=0;i<RecordNum;i++) {
            if (records.get(i).Fields.get(Where).equals(FieldsWhere) || where) {
                for (int j=0;j<ColumnUpdate.size();j++) {
                    int index = ColumnName.indexOf(ColumnUpdate.get(j));
                    records.get(i).Fields.set(index,FieldUpdate.get(j));
                }
                Effect++;
            }
        }
        return Effect;
    }
    public int Delete (String ColumnWhere,String FieldsWhere) {
        if (!ColumnName.contains(ColumnWhere) && !ColumnWhere.equals("NOWHERE!"))
            return -1;
        int Where = ColumnName.indexOf(ColumnWhere);
        int Effect = 0;
        boolean where = false;
        if (Where==-1) {
            Where = 0;
            where = true;
        }
        for (int i=0;i<RecordNum;) {
            if (records.get(i).Fields.get(Where).equals(FieldsWhere) || where) {
                records.remove(i);
                RecordNum--;
                Effect++;
            }
            else
                i++;
        }
        return Effect;
    }
}
