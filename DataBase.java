import java.util.ArrayList;

class DataBase {
    int TableNum = 0;
    ArrayList<Table> tables = new ArrayList<>();

    public boolean CreateTable (String TableName,ArrayList<String> ColumnName) {
        for (Table table : tables)
            if (table.TableName.equals(TableName))
                return false;
        if (ColumnName.contains("-1ERROR!"))
            return false;
        tables.add(new Table(TableName,ColumnName));
        this.TableNum++;
        return true;
    }

    public boolean DropTable (String TableName) {
        for (int i=0;i<tables.size();i++) {
            if (tables.get(i).TableName.equals(TableName)) {
                tables.remove(i);
                this.TableNum--;
                return true;
            }
        }
        return false;
    }
    public void ShowTable () {
        if (TableNum==0)
            System.out.println("No result.");
        else {
            System.out.println("Tablename | Records");
            for (Table table : tables) System.out.println(table.TableName + " | " + table.RecordNum);
        }
    }

    public int GetTable (String TableName) {
        for (int i=0;i<tables.size();i++)
            if (tables.get(i).TableName.equals(TableName)) {
                return i;
            }
        return -1;
    }
}
