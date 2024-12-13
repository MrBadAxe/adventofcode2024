public class BoundsChecker{
    public static boolean checkBounds(long n, long min, long max){
        return (n >= min && n < max);
    }
    public static boolean checkBounds(long x, long y, long xmin, long xmax, long ymin, long ymax){
        return checkBounds(x,xmin,xmax) && checkBounds(y, ymin, ymax);
    }
    public static boolean checkBounds(Point p, CharGrid grid){
        return checkBounds(p.getX(), 0, grid.getHeight()) && checkBounds(p.getY(), 0, grid.getWidth());
    }
}