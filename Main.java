import java.util.*;

public class Main {
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameboard =
                {{' ','|',' ','|',' '},
                {'+','-','+','-','+'},
                {' ','|',' ','|',' '},
                {'+','-','+','-','+'},
                {' ','|',' ','|',' '}};

        printGameboard(gameboard);

        while(true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter a number: (1-9)");
            int playerPos = scan.nextInt();

            while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)){
                System.out.println("Position taken! Enter a new one!");
                playerPos = scan.nextInt();
            }
            placePiece(gameboard, playerPos, "Player");
            String result = checkWinner();

            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)){
                cpuPos = random.nextInt(9)+1;
            }
            placePiece(gameboard, cpuPos, "cpu");

            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

        }

    }
    public static void printGameboard(char[][] gameboard){
        for(char[] row:gameboard){
            for(char col:row){
                System.out.print(col);
            }
            System.out.println();
        }
        /*for(int row=0;row< gameboard.length;row++){
            for(int col=0;col<row;col++){
                System.out.print(gameboard[row][col]);
            }
            System.out.println();
        }*/
    }
    public static void placePiece(char[][] gameboard, int pos, String user){
        char symbol = ' ';
        if(user.equals("Player")){
            symbol = 'X';
            playerPosition.add(pos);
        }
        else if(user.equals("cpu")){
            symbol = 'O';
            cpuPosition.add(pos);
        }

        switch (pos){
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
            default:
                break;
        }
        printGameboard(gameboard);
    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning){
            if(playerPosition.containsAll(l)){
                System.out.println("Congratulations you have won!");
            }
            else if(cpuPosition.containsAll(l)){
                System.out.println("You have lost! The computer won!");
            }
            else if(playerPosition.size() + cpuPosition.size() == 9){
                System.out.println("It's a tie! No one won!");
                break;
            }

        }

        return "";
    }
}
