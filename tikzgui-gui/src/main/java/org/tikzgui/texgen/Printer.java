public class Printer {

    public String print(gObject) {
        switch (gObject) {
            case Rectangle:
                RectanglePrinter r = new RectanglePrinter(gObject); 
                return r.print();
        }
    }

}