import java.util.ArrayList;

class Record {
    int RecordLength;
    ArrayList<String> Fields;

    Record (ArrayList<String> Fields) {
        this.Fields=Fields;
        RecordLength=Fields.size();
    }
}
