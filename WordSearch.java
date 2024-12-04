public class WordSearch extends CharGrid{
    public WordSearch(int x, int y, char init){
        super(x,y,init);
    }

    public int getXmasLineCount(int row, int col){
        if(this.get(row, col) != 'X'){ return 0; }
        int z = 0;
        if(row >= 3){
            if(                                 this.get(row-1,col) == 'M' &&   this.get(row-2,col) == 'A' &&   this.get(row-3,col) == 'S'){ z++; }
            if(col >= 3 &&                      this.get(row-1,col-1) == 'M' && this.get(row-2,col-2) == 'A' && this.get(row-3,col-3) == 'S'){ z++; }
            if(col < this.getWidth() - 3 &&     this.get(row-1,col+1) == 'M' && this.get(row-2,col+2) == 'A' && this.get(row-3,col+3) == 'S'){ z++; }
        }
        if(row < this.getHeight() - 3){
            if(                                 this.get(row+1,col) == 'M' &&   this.get(row+2,col) == 'A' &&   this.get(row+3,col) == 'S'){ z++; }
            if(col >= 3 &&                      this.get(row+1,col-1) == 'M' && this.get(row+2,col-2) == 'A' && this.get(row+3,col-3) == 'S'){ z++; }
            if(col < this.getWidth() - 3 &&     this.get(row+1,col+1) == 'M' && this.get(row+2,col+2) == 'A' && this.get(row+3,col+3) == 'S'){ z++; }
        }
        if(col >= 3 &&                          this.get(row,col-1) == 'M' &&   this.get(row,col-2) == 'A' &&   this.get(row,col-3) == 'S'){ z++; }
        if(col < this.getWidth() - 3 &&         this.get(row,col+1) == 'M' &&   this.get(row,col+2) == 'A' &&   this.get(row,col+3) == 'S'){ z++; }

        return z;
    }

    public int getTotalXmasLineCount(){
        int total = 0;
        for(int row=0; row<this.getHeight(); row++){
            for(int col=0; col<this.getWidth(); col++){
                total += getXmasLineCount(row, col);
            }
        }
        return total;
    }

    public boolean isXmasX(int row, int col){
        if( this.get(row,col) != 'A' || row == 0 || col == 0 || row == this.getHeight() - 1 || col == this.getWidth() - 1){
            return false;
        }
        char nw = this.get(row-1,col-1);
        char ne = this.get(row-1,col+1);
        char sw = this.get(row+1,col-1);
        char se = this.get(row+1,col+1);
        if(nw == 'M'){
            if(ne == 'M'){
                return sw == 'S' && se == 'S';
            }else if(ne == 'S'){
                return sw == 'M' && se == 'S';
            }else return false;
        }else if(nw == 'S'){
            if(ne == 'M'){
                return sw == 'S' && se == 'M';
            }else if(ne == 'S'){
                return sw == 'M' && se == 'M';
            }else return false;
        }else return false;
    }

    public int getTotalXmasXCount(){
        int total = 0;
        for(int row=0; row<this.getHeight(); row++){
            for(int col=0; col<this.getWidth(); col++){
                if(isXmasX(row, col)){ total++; }
            }
        }
        return total;
    }

}