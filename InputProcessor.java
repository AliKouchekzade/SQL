import java.util.Scanner;

public class InputProcessor {
    public static void main (String[] args) {
        Manager manager = new Manager();
        DataBase dataBase = new DataBase();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("end"))
                break;
            else if (input.contains("CREATE TABLE")) {
                if (dataBase.CreateTable(manager.TableName_CreateTable(input),manager.ColumnName_CreateTable(input)))
                    System.out.println("You have made changes to the database.");
                else
                    System.out.println("ERROR!");
            }
            else if (input.contains("DROP TABLE")) {
                if (dataBase.DropTable(manager.TableName_DropTable(input))) {
                    System.out.println("You have made changes to the database.");
                }
                else
                    System.out.println("ERROR!");
            }
            else if (input.contains("show tables")) {
                dataBase.ShowTable();
            }
            else if (input.contains("INSERT INTO")) {
                int index = dataBase.GetTable(manager.TableName_InsertInto(input));
                if (index==-1) {
                    System.out.println("ERROR!");
                    continue;
                }
                if (!input.contains(") VALUES")) {
                    if (dataBase.tables.get(index).InsertInto(manager.Fields_InsertInto(input)))
                        System.out.println("You have made changes to the database. Rows affected: 1");
                    else
                        System.out.println("ERROR!");
                }
                else {
                    if (dataBase.tables.get(index).InsertInto(manager.Fields_InsertInto2(input),manager.ColumnName_InsertInto(input)))
                        System.out.println("You have made changes to the database. Rows affected: 1");
                    else
                        System.out.println("ERROR!");
                }
            }
            else if (input.contains("SELECT")) {
                int index = dataBase.GetTable(manager.TableName_Select_Delete(input));
                if (index==-1) {
                    System.out.println("ERROR!");
                    continue;
                }
                int result =  dataBase.tables.get(index).Select(manager.ColumnName_Select(input),manager.ColumnName_Where(input),manager.Field_Where(input));
                if (result==-1)
                    System.out.println("ERROR!");
                else if (result==0)
                    System.out.println("No result.");
            }
            else if (input.contains("UPDATE")) {
                int index = dataBase.GetTable(manager.TableName_Update(input));
                if (index==-1) {
                    System.out.println("ERROR!");
                    continue;
                }
                int Effect = dataBase.tables.get(index).Update(manager.ColumnName_Update(input),manager.Field_Update(input),manager.ColumnName_Where(input),manager.Field_Where(input));
                if (Effect==-1)
                    System.out.println("ERROR!");
                else
                    System.out.println("You have made changes to the database. Rows affected: "+Effect);
            }
            else if (input.contains("DELETE")) {
                int index = dataBase.GetTable(manager.TableName_Select_Delete(input));
                if (index==-1) {
                    System.out.println("ERROR!");
                    continue;
                }
                int Effect = dataBase.tables.get(index).Delete(manager.ColumnName_Where(input),manager.Field_Where(input));
                if (Effect==-1)
                    System.out.println("ERROR!");
                else
                    System.out.println("You have made changes to the database. Rows affected: "+Effect);
            }
        }
    }
}
