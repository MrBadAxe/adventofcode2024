import java.util.List;

public class Day16{
    private static ReindeerMaze generateReindeerMaze(List<String> input){
        int height = input.size();
        int width = input.get(0).length();

        ReindeerMaze z = new ReindeerMaze(height, width, '.');
        for(int row=0;row<height;row++){
            String line = input.get(row);
            for(int col=0;col<width;col++){
                z.set(row,col,line.charAt(col));
            }
        }
        return z;
    }
    public static String getPart01(List<String> input){
        ReindeerMaze maze = generateReindeerMaze(input);
        //System.out.println(maze);
        //System.out.println(maze.getStart());
        //System.out.println(maze.getEnd());
        int[][] scorePaths = maze.scorePaths();
        return Integer.toString(scorePaths[(int)maze.getEnd().getX()][(int)maze.getEnd().getY()]);
    }
    public static String getPart02(List<String> input){
        ReindeerMaze maze = generateReindeerMaze(input);
        //System.out.println(maze);
        //System.out.println(maze.getStart());
        //System.out.println(maze.getEnd());
        while(!maze.equals(maze.pruneDeadEnds())){
            maze = maze.pruneDeadEnds();
        }   
        //System.out.println(maze);     
        int[][] scorePaths = maze.scorePaths();
        return Integer.toString(scorePaths[(int)maze.getEnd().getX()][(int)maze.getEnd().getY()]);
    }

}