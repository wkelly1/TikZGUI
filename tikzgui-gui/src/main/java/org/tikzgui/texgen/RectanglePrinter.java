public class RectanglePrinter {

    public String print(Rectangle rectangle) {
        String out = "\\draw ";
        StrokePrinter strokePrinter = new StrokePrinter(rectangle.getStroke());
        out += strokePrinter.print() + " ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += rectangle.getPointB().toString();

        return out;
    }

}