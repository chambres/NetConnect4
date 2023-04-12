import java.util.Random;

public class GameData
{
    private char[][] grid = new char[6][7];

    public GameData() {
        reset();
    }

    public char[][] getGrid()
    {
        return grid;
    }

    public void reset()
    {
        Random rd = new Random();
        for(int r=0; r<grid.length; r++) {
            for(int c=0; c<grid[0].length; c++) {
                
                grid[r][c]= rd.nextBoolean() ? 'x' : 'o';
                

                
            }
        }
    }

    public boolean isCat()
    {
        for(int r=0; r<grid.length; r++) {
            for(int c=0; c<grid[0].length; c++) {
                if(grid[r][c] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner(char letter)
    {
        // Check for horizontal wins
        for(int r=0; r<grid.length; r++) {
            for(int c=0; c<=grid[0].length-4; c++) {
                if(grid[r][c] == letter && grid[r][c+1] == letter && grid[r][c+2] == letter && grid[r][c+3] == letter) {
                    return true;
                }
            }
        }

        // Check for vertical wins
        for(int r=0; r<=grid.length-4; r++) {
            for(int c=0; c<grid[0].length; c++) {
                if(grid[r][c] == letter && grid[r+1][c] == letter && grid[r+2][c] == letter && grid[r+3][c] == letter) {
                    return true;
                }
            }
        }

        // Check for diagonal wins (up and to the right)
        for(int r=3; r<grid.length; r++) {
            for(int c=0; c<=grid[0].length-4; c++) {
                if(grid[r][c] == letter && grid[r-1][c+1] == letter && grid[r-2][c+2] == letter && grid[r-3][c+3] == letter) {
                    return true;
                }
            }
        }

        // Check for diagonal wins (up and to the left)
        for(int r=3; r<grid.length; r++) {
            for(int c=3; c<grid[0].length; c++) {
                if(grid[r][c] == letter && grid[r-1][c-1] == letter && grid[r-2][c-2] == letter && grid[r-3][c-3] == letter) {
                    return true;
                }
            }
        }

        return false;
    }
}
