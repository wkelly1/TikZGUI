public class RectanglePrinter {

    Rectangle rectangle;

    public RectanglePrinter (Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String print() {
        String out = "\\draw ";
        StrokePrinter strokePrinter = new StrokePrinter(rectangle.getStroke());
        out += strokePrinter.print() + " ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += rectangle.getPointB().toString();

        return out;
    }

}