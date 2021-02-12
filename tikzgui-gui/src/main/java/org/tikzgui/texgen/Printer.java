public class Printer {

    public String print(gObject) {
        switch (gObject) {
            case gObject instanceof Rectangle:
                RectanglePrinter r = new RectanglePrinter(gObject); 
                return r.print();
                break;
        }
    }

}