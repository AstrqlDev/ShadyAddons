// 
// Decompiled by Procyon v0.5.36
// 

package cheaters.get.banned.features.connectfoursolver;

public class ConnectFourAlgorithm
{
    public Board board;
    private int nextMoveLocation;
    private int MAX_DEPTH;
    
    public ConnectFourAlgorithm(final Board board) {
        this.nextMoveLocation = -1;
        this.MAX_DEPTH = 9;
        this.board = board;
    }
    
    public int gameResult(final Board b) {
        int aiScore = 0;
        int humanScore = 0;
        for (int i = 5; i >= 0; --i) {
            for (int j = 0; j <= 6; ++j) {
                if (b.board[i][j] != 0) {
                    if (j <= 3) {
                        for (int k = 0; k < 4; ++k) {
                            if (b.board[i][j + k] == 1) {
                                ++aiScore;
                            }
                            else {
                                if (b.board[i][j + k] != 2) {
                                    break;
                                }
                                ++humanScore;
                            }
                        }
                        if (aiScore == 4) {
                            return 1;
                        }
                        if (humanScore == 4) {
                            return 2;
                        }
                        aiScore = 0;
                        humanScore = 0;
                    }
                    if (i >= 3) {
                        for (int k = 0; k < 4; ++k) {
                            if (b.board[i - k][j] == 1) {
                                ++aiScore;
                            }
                            else {
                                if (b.board[i - k][j] != 2) {
                                    break;
                                }
                                ++humanScore;
                            }
                        }
                        if (aiScore == 4) {
                            return 1;
                        }
                        if (humanScore == 4) {
                            return 2;
                        }
                        aiScore = 0;
                        humanScore = 0;
                    }
                    if (j <= 3 && i >= 3) {
                        for (int k = 0; k < 4; ++k) {
                            if (b.board[i - k][j + k] == 1) {
                                ++aiScore;
                            }
                            else {
                                if (b.board[i - k][j + k] != 2) {
                                    break;
                                }
                                ++humanScore;
                            }
                        }
                        if (aiScore == 4) {
                            return 1;
                        }
                        if (humanScore == 4) {
                            return 2;
                        }
                        aiScore = 0;
                        humanScore = 0;
                    }
                    if (j >= 3 && i >= 3) {
                        for (int k = 0; k < 4; ++k) {
                            if (b.board[i - k][j - k] == 1) {
                                ++aiScore;
                            }
                            else {
                                if (b.board[i - k][j - k] != 2) {
                                    break;
                                }
                                ++humanScore;
                            }
                        }
                        if (aiScore == 4) {
                            return 1;
                        }
                        if (humanScore == 4) {
                            return 2;
                        }
                        aiScore = 0;
                        humanScore = 0;
                    }
                }
            }
        }
        for (int l = 0; l < 7; ++l) {
            if (b.board[0][l] == 0) {
                return -1;
            }
        }
        return 0;
    }
    
    int calculateScore(final int aiScore, final int moreMoves) {
        final int moveScore = 4 - moreMoves;
        if (aiScore == 0) {
            return 0;
        }
        if (aiScore == 1) {
            return 1 * moveScore;
        }
        if (aiScore == 2) {
            return 10 * moveScore;
        }
        if (aiScore == 3) {
            return 100 * moveScore;
        }
        return 1000;
    }
    
    public int evaluateBoard(final Board b) {
        int aiScore = 1;
        int score = 0;
        int blanks = 0;
        int k = 0;
        int moreMoves = 0;
        for (int i = 5; i >= 0; --i) {
            for (int j = 0; j <= 6; ++j) {
                if (b.board[i][j] != 0) {
                    if (b.board[i][j] != 2) {
                        if (j <= 3) {
                            for (k = 1; k < 4; ++k) {
                                if (b.board[i][j + k] == 1) {
                                    ++aiScore;
                                }
                                else {
                                    if (b.board[i][j + k] == 2) {
                                        aiScore = 0;
                                        blanks = 0;
                                        break;
                                    }
                                    ++blanks;
                                }
                            }
                            moreMoves = 0;
                            if (blanks > 0) {
                                for (int c = 1; c < 4; ++c) {
                                    for (int column = j + c, m = i; m <= 5 && b.board[m][column] == 0; ++m) {
                                        ++moreMoves;
                                    }
                                }
                            }
                            if (moreMoves != 0) {
                                score += this.calculateScore(aiScore, moreMoves);
                            }
                            aiScore = 1;
                            blanks = 0;
                        }
                        if (i >= 3) {
                            for (k = 1; k < 4; ++k) {
                                if (b.board[i - k][j] == 1) {
                                    ++aiScore;
                                }
                                else if (b.board[i - k][j] == 2) {
                                    aiScore = 0;
                                    break;
                                }
                            }
                            moreMoves = 0;
                            if (aiScore > 0) {
                                for (int column2 = j, l = i - k + 1; l <= i - 1 && b.board[l][column2] == 0; ++l) {
                                    ++moreMoves;
                                }
                            }
                            if (moreMoves != 0) {
                                score += this.calculateScore(aiScore, moreMoves);
                            }
                            aiScore = 1;
                            blanks = 0;
                        }
                        if (j >= 3) {
                            for (k = 1; k < 4; ++k) {
                                if (b.board[i][j - k] == 1) {
                                    ++aiScore;
                                }
                                else {
                                    if (b.board[i][j - k] == 2) {
                                        aiScore = 0;
                                        blanks = 0;
                                        break;
                                    }
                                    ++blanks;
                                }
                            }
                            moreMoves = 0;
                            if (blanks > 0) {
                                for (int c = 1; c < 4; ++c) {
                                    for (int column = j - c, m = i; m <= 5 && b.board[m][column] == 0; ++m) {
                                        ++moreMoves;
                                    }
                                }
                            }
                            if (moreMoves != 0) {
                                score += this.calculateScore(aiScore, moreMoves);
                            }
                            aiScore = 1;
                            blanks = 0;
                        }
                        if (j <= 3 && i >= 3) {
                            for (k = 1; k < 4; ++k) {
                                if (b.board[i - k][j + k] == 1) {
                                    ++aiScore;
                                }
                                else {
                                    if (b.board[i - k][j + k] == 2) {
                                        aiScore = 0;
                                        blanks = 0;
                                        break;
                                    }
                                    ++blanks;
                                }
                            }
                            moreMoves = 0;
                            if (blanks > 0) {
                                for (int c = 1; c < 4; ++c) {
                                    final int column = j + c;
                                    int m2;
                                    for (int row = m2 = i - c; m2 <= 5; ++m2) {
                                        if (b.board[m2][column] == 0) {
                                            ++moreMoves;
                                        }
                                        else if (b.board[m2][column] != 1) {
                                            break;
                                        }
                                    }
                                }
                                if (moreMoves != 0) {
                                    score += this.calculateScore(aiScore, moreMoves);
                                }
                                aiScore = 1;
                                blanks = 0;
                            }
                        }
                        if (i >= 3 && j >= 3) {
                            for (k = 1; k < 4; ++k) {
                                if (b.board[i - k][j - k] == 1) {
                                    ++aiScore;
                                }
                                else {
                                    if (b.board[i - k][j - k] == 2) {
                                        aiScore = 0;
                                        blanks = 0;
                                        break;
                                    }
                                    ++blanks;
                                }
                            }
                            moreMoves = 0;
                            if (blanks > 0) {
                                for (int c = 1; c < 4; ++c) {
                                    final int column = j - c;
                                    int m2;
                                    for (int row = m2 = i - c; m2 <= 5; ++m2) {
                                        if (b.board[m2][column] == 0) {
                                            ++moreMoves;
                                        }
                                        else if (b.board[m2][column] != 1) {
                                            break;
                                        }
                                    }
                                }
                                if (moreMoves != 0) {
                                    score += this.calculateScore(aiScore, moreMoves);
                                }
                                aiScore = 1;
                                blanks = 0;
                            }
                        }
                    }
                }
            }
        }
        return score;
    }
    
    public int minimax(final int depth, final int turn, int alpha, int beta) {
        if (beta <= alpha) {
            if (turn == 1) {
                return Integer.MAX_VALUE;
            }
            return Integer.MIN_VALUE;
        }
        else {
            final int gameResult = this.gameResult(this.board);
            if (gameResult == 1) {
                return 1073741823;
            }
            if (gameResult == 2) {
                return -1073741824;
            }
            if (gameResult == 0) {
                return 0;
            }
            if (depth == this.MAX_DEPTH) {
                return this.evaluateBoard(this.board);
            }
            int maxScore = Integer.MIN_VALUE;
            int minScore = Integer.MAX_VALUE;
            for (int j = 0; j <= 6; ++j) {
                int currentScore = 0;
                if (this.board.isLegalMove(j)) {
                    if (turn == 1) {
                        this.board.placeMove(j, 1);
                        currentScore = this.minimax(depth + 1, 2, alpha, beta);
                        if (depth == 0) {
                            if (currentScore > maxScore) {
                                this.nextMoveLocation = j;
                            }
                            if (currentScore == 1073741823) {
                                this.board.undoMove(j);
                                break;
                            }
                        }
                        maxScore = Math.max(currentScore, maxScore);
                        alpha = Math.max(currentScore, alpha);
                    }
                    else if (turn == 2) {
                        this.board.placeMove(j, 2);
                        currentScore = this.minimax(depth + 1, 1, alpha, beta);
                        minScore = Math.min(currentScore, minScore);
                        beta = Math.min(currentScore, beta);
                    }
                    this.board.undoMove(j);
                    if (currentScore == Integer.MAX_VALUE) {
                        break;
                    }
                    if (currentScore == Integer.MIN_VALUE) {
                        break;
                    }
                }
            }
            return (turn == 1) ? maxScore : minScore;
        }
    }
    
    public int getAIMove() {
        this.nextMoveLocation = -1;
        this.minimax(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.nextMoveLocation;
    }
    
    public static class Board
    {
        byte[][] board;
        
        public Board(final byte[][] board) {
            this.board = new byte[6][7];
            this.board = board;
        }
        
        public Board() {
            this.board = new byte[6][7];
            this.board = new byte[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
        }
        
        public boolean isLegalMove(final int column) {
            return column >= 0 && column <= this.board[0].length && this.board[0][column] == 0;
        }
        
        public boolean placeMove(final int column, final int player) {
            if (!this.isLegalMove(column)) {
                return false;
            }
            for (int i = 5; i >= 0; --i) {
                if (this.board[i][column] == 0) {
                    this.board[i][column] = (byte)player;
                    return true;
                }
            }
            return false;
        }
        
        public void undoMove(final int column) {
            for (int i = 0; i <= 5; ++i) {
                if (this.board[i][column] != 0) {
                    this.board[i][column] = 0;
                    break;
                }
            }
        }
    }
}
