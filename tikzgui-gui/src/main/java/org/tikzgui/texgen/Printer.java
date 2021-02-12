public class Printer {

    public String print(gObject) {
        switch (gObject) {
            case gObject.getClass() == Rectangle.class:
                RectanglePrinter r = new RectanglePrinter(gObject); 
                return r.print();
                break;
        }
    }

}