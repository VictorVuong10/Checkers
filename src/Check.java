import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

/**
 * 
 * Checkers.
 * 
 * @author Victor A01021299
 * @version 1.0
 */
public class Check extends GridPane {
    private Boolean turnW = true; 
    private Boolean move = false;
    private CheckerSquares currentSquare;
    private CheckerSquares leftSquare;
    private CheckerSquares rightSquare;
    private CheckerSquares eatSquareL;
    private CheckerSquares eatSquareR;
    private CheckerSquares kingUpLeft;
    private CheckerSquares kingDownLeft;
    private CheckerSquares kingUpRight;
    private CheckerSquares kingDownRight;
    private CheckerSquares eatKDL;
    private CheckerSquares eatKDR;
    private CheckerSquares eatKUL;
    private CheckerSquares eatKUR;
    private int deadW;
    private int deadB;
    private Main main;
    
    public Check(Main main) {
        this.main = main;
        
        
        int color = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                CheckerSquares square;
                if (color % 2 == 0) {
                   square = new CheckerSquares(Color.YELLOW);
                } else {
                    if(row <= 2) {
                        CheckerPieces pieceB = new CheckerPieces(Color.BLACK);

                        square = new CheckerSquares(Color.PURPLE, pieceB);
                        square.setOnMouseClicked(this::move);
                    } else if (row >=5 ) {
                        CheckerPieces pieceW = new CheckerPieces(Color.WHITE);
                        square = new CheckerSquares(Color.PURPLE, pieceW);
                        square.setOnMouseClicked(this::move);
                    } else {
                   square = new CheckerSquares(Color.PURPLE);
                   square.setOnMouseClicked(this::move);
                    }
                }
                add(square, col, row);

                color++;
            }
            color++;
        }
        }
        
        
        
        
        
        
    
    public void move(MouseEvent event) {
        if(!move) {
            if (currentSquare != null) {
                currentSquare.deselect();
            }
            CheckerSquares square = (CheckerSquares) event.getSource();
            currentSquare = square;
            if(square.hasPiece()) {
                if (!square.getPiece().isKing()) {
                    if(square.getPiece().getTeam().equals("black")) {
                        canMoveB();
                    } else {
                        canMoveW();
                    }
                } else {
                    canMoveK();
                }
            }
        } else if (!currentSquare.getPiece().isKing()) {
            if(leftSquare == (CheckerSquares) event.getSource()) {
                if (!leftSquare.hasPiece()) {
                        CheckerPieces piece = currentSquare.getPiece();
                        currentSquare.removePiece();
                        if (eatSquareL != null) {
                            if (!piece.getTeam().equals(eatSquareL.getPiece().getTeam())) {
                                eatSquareL.removePiece();
                                if(turnW) {
                                    deadB++;
                                } else {
                                    deadW++;
                                }
                                
                            }
                        }
                        main.getWinner();
                        leftSquare.addPiece(piece);
                        if (GridPane.getRowIndex(leftSquare) == 7 && piece.getTeam().equals("black") && !piece.isKing()) {
                            piece.kinged();
                        } else if (GridPane.getRowIndex(leftSquare) == 0 && piece.getTeam().equals("white") && !piece.isKing()) {
                            piece.kinged();
                        }
                        
                        
                        move = false;
                        if (rightSquare != null) {
                            rightSquare.deselect();
                        }
                        if (leftSquare != null) {
                            leftSquare.deselect();
                        }
                        currentSquare.deselect();
                        currentSquare = null;
                        rightSquare = null;
                        leftSquare = null;
                        if (piece.getTeam().equals("black")) {
                            turnW = true;
                        } else {
                            turnW = false;
                        }
                        
                }
            } else if (rightSquare == (CheckerSquares) event.getSource()) {
                    if (!rightSquare.hasPiece()) {
                        CheckerPieces piece = currentSquare.getPiece();
                        currentSquare.removePiece();
                        
                        if (eatSquareR != null) {
                            if (!piece.getTeam().equals(eatSquareR.getPiece().getTeam())) {
                                System.out.println("hello");
                                eatSquareR.removePiece();
                                if(turnW) {
                                    deadB++;
                                } else {
                                    deadW++;
                                }
                            }
                        }
                        main.getWinner();
                        rightSquare.addPiece(piece);
                        if (GridPane.getRowIndex(rightSquare) == 7 && piece.getTeam().equals("black") && !piece.isKing()) {
                            piece.kinged();
                        } else if (GridPane.getRowIndex(rightSquare) == 0 && piece.getTeam().equals("white") && !piece.isKing()) {
                            piece.kinged();
                        }
                        
                        move = false;
                        if (rightSquare != null) {
                            rightSquare.deselect();
                        }
                        if (leftSquare != null) {
                            leftSquare.deselect();
                        }
                        currentSquare.deselect();
                        currentSquare = null;
                        rightSquare = null;
                        leftSquare = null;
                                
                        if (piece.getTeam().equals("black")) {
                            turnW = true;
                        } else {
                            turnW = false;
                        }
                        
                    }
            } else {
                if (rightSquare != null) {
                    rightSquare.deselect();
                }
                if (leftSquare != null) {
                    leftSquare.deselect();
                }
                move = false;
                currentSquare.deselect();
                currentSquare = null;
                rightSquare = null;
                leftSquare = null;
                eatSquareR = null;
                eatSquareL = null;
            }
        } else if ((currentSquare.getPiece().isKing() && currentSquare.getPiece().getTeam().equals("black") && turnW == false) || (currentSquare.getPiece().isKing() && currentSquare.getPiece().getTeam().equals("white") && turnW == true)) {
            if (kingUpLeft == (CheckerSquares) event.getSource()) {
                if (!kingUpLeft.hasPiece()) {
                    CheckerPieces piece = currentSquare.getPiece();
                    currentSquare.removePiece();
                    
                    if (eatKUL != null) {
                        if (!piece.getTeam().equals(eatKUL.getPiece().getTeam())) {
                            if(turnW) {
                                deadB++;
                            } else {
                                deadW++;
                            }
                            main.getWinner();
                            eatKUL.removePiece();
                            eatKUL = null;
                            eatKUR = null;
                            eatKDL = null;
                            eatKDR = null;
                        }
                    }
                    kingUpLeft.addPiece(piece);
                    move = false;
                    if (kingUpLeft != null) {
                        kingUpLeft.deselect();
                    }
                    if (kingUpRight != null) {
                        kingUpRight.deselect();
                    }
                    if (kingDownLeft != null) {
                        kingDownLeft.deselect();
                    }
                    if (kingDownRight != null) {
                        kingDownRight.deselect();
                    }
                    currentSquare.deselect();
                    currentSquare = null;
                    kingUpLeft = null;
                    kingUpRight = null;
                    kingDownLeft = null;
                    kingDownRight = null;
                            
                    if (piece.getTeam().equals("black")) {
                        turnW = true;
                    } else {
                        turnW = false;
                    }
                }
            } else if (kingUpRight == (CheckerSquares) event.getSource()) {
                if (!kingUpRight.hasPiece()) {
                    CheckerPieces piece = currentSquare.getPiece();
                    currentSquare.removePiece();
                    
                    if (eatKUR != null) {
                        if (!piece.getTeam().equals(eatKUR.getPiece().getTeam())) {
                            if(turnW) {
                                deadB++;
                            } else {
                                deadW++;
                            }
                            main.getWinner();
                            eatKUR.removePiece();
                            eatKUL = null;
                            eatKUR = null;
                            eatKDL = null;
                            eatKDR = null;
                        }
                    }
                    kingUpRight.addPiece(piece);
                    move = false;
                    if (kingUpLeft != null) {
                        kingUpLeft.deselect();
                    }
                    if (kingUpRight != null) {
                        kingUpRight.deselect();
                    }
                    if (kingDownLeft != null) {
                        kingDownLeft.deselect();
                    }
                    if (kingDownRight != null) {
                        kingDownRight.deselect();
                    }
                    currentSquare.deselect();
                    currentSquare = null;
                    kingUpLeft = null;
                    kingUpRight = null;
                    kingDownLeft = null;
                    kingDownRight = null;
                            
                    if (piece.getTeam().equals("black")) {
                        turnW = true;
                    } else {
                        turnW = false;
                    }
                }
            } else if (kingDownLeft == (CheckerSquares) event.getSource()) {
                if (!kingDownLeft.hasPiece()) {
                    CheckerPieces piece = currentSquare.getPiece();
                    currentSquare.removePiece();
                    
                    if (eatKDL != null) {
                        if (!piece.getTeam().equals(eatKDL.getPiece().getTeam())) {
                            if(turnW) {
                                deadB++;
                            } else {
                                deadW++;
                            }
                            main.getWinner();
                            eatKDL.removePiece();
                            eatKUL = null;
                            eatKUR = null;
                            eatKDL = null;
                            eatKDR = null;
                        }
                    }
                    kingDownLeft.addPiece(piece);
                    move = false;
                    if (kingUpLeft != null) {
                        kingUpLeft.deselect();
                    }
                    if (kingUpRight != null) {
                        kingUpRight.deselect();
                    }
                    if (kingDownLeft != null) {
                        kingDownLeft.deselect();
                    }
                    if (kingDownRight != null) {
                        kingDownRight.deselect();
                    }
                    currentSquare.deselect();
                    currentSquare = null;
                    kingUpLeft = null;
                    kingUpRight = null;
                    kingDownLeft = null;
                    kingDownRight = null;
                            
                    if (piece.getTeam().equals("black")) {
                        turnW = true;
                    } else {
                        turnW = false;
                    }
                }
            } else if (kingDownRight == (CheckerSquares) event.getSource()) {
                if(!kingDownRight.hasPiece()) {
                    CheckerPieces piece = currentSquare.getPiece();
                    currentSquare.removePiece();
                    
                    if (eatKDR != null) {
                        if (!piece.getTeam().equals(eatKDR.getPiece().getTeam())) {
                            if(turnW) {
                                deadB++;
                            } else {
                                deadW++;
                            }
                            main.getWinner();
                            eatKDR.removePiece();
                            eatKUL = null;
                            eatKUR = null;
                            eatKDL = null;
                            eatKDR = null;
                        }
                    }
                    kingDownRight.addPiece(piece);
                    move = false;
                    if (kingUpLeft != null) {
                        kingUpLeft.deselect();
                    }
                    if (kingUpRight != null) {
                        kingUpRight.deselect();
                    }
                    if (kingDownLeft != null) {
                        kingDownLeft.deselect();
                    }
                    if (kingDownRight != null) {
                        kingDownRight.deselect();
                    }
                    currentSquare.deselect();
                    currentSquare = null;
                    kingUpLeft = null;
                    kingUpRight = null;
                    kingDownLeft = null;
                    kingDownRight = null;
                            
                    if (piece.getTeam().equals("black")) {
                        turnW = true;
                    } else {
                        turnW = false;
                    }
                }
            } else {
                move = false;
                if (kingUpLeft != null) {
                    kingUpLeft.deselect();
                }
                if (kingUpRight != null) {
                    kingUpRight.deselect();
                }
                if (kingDownLeft != null) {
                    kingDownLeft.deselect();
                }
                if (kingDownRight != null) {
                    kingDownRight.deselect();
                }
                currentSquare.deselect();
                currentSquare = null;
//                if (rightSquare != null) {
//                    rightSquare.deselect();
//                }
//                if (leftSquare != null) {
//                    leftSquare.deselect();
//                }
//                rightSquare = null;
//                leftSquare = null;
//                eatSquareR = null;
//                eatSquareL = null;
                kingUpLeft = null;
                kingUpRight = null;
                kingDownLeft = null;
                kingDownRight = null;
            }
        }   else {
            System.out.println("asd");
            move = false;
            if (kingUpLeft != null) {
                kingUpLeft.deselect();
            }
            if (kingUpRight != null) {
                kingUpRight.deselect();
            }
            if (kingDownLeft != null) {
                kingDownLeft.deselect();
            }
            if (kingDownRight != null) {
                kingDownRight.deselect();
            }
            currentSquare.deselect();
            currentSquare = null;
//            if (rightSquare != null) {
//                rightSquare.deselect();
//            }
//            if (leftSquare != null) {
//                leftSquare.deselect();
//            }
//            rightSquare = null;
//            leftSquare = null;
//            eatSquareR = null;
//            eatSquareL = null;
            kingUpLeft = null;
            kingUpRight = null;
            kingDownLeft = null;
            kingDownRight = null;


        }
        
        
    }
    
    public void canMoveK() {
        if ((currentSquare.getPiece().getTeam().equals("black") && turnW == false) || (currentSquare.getPiece().getTeam().equals("white") && turnW == true)) {
            currentSquare.select();
            kingDownLeft = null;
            kingUpLeft = null;
            kingDownRight = null;
            kingUpRight = null;
            
            
            int currentCol = GridPane.getColumnIndex(currentSquare);
            int currentRow = GridPane.getRowIndex(currentSquare);
            
            int downRow = currentRow + 1;
            int upRow = currentRow - 1;
            int leftCol = currentCol - 1;
            int rightCol = currentCol + 1;
            
            if (leftCol >= 0 && downRow < 8) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == leftCol && GridPane.getRowIndex(node) == downRow) {
                        kingDownLeft = (CheckerSquares) node;
                    }
                }
                
                if (!kingDownLeft.hasPiece()) {
                    move = true;
                    kingDownLeft.posMove();
                }
                
            }
            
            if (rightCol < 8 && downRow < 8) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == rightCol && GridPane.getRowIndex(node) == downRow) {
                        kingDownRight = (CheckerSquares) node;
                    }
                }
                
                if (!kingDownRight.hasPiece()) {
                    move = true;
                    kingDownRight.posMove();
                }
                
            }
            
            if (leftCol >= 0 && upRow >= 0) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == leftCol && GridPane.getRowIndex(node) == upRow) {
                        kingUpLeft = (CheckerSquares) node;
                    }
                }
                if (!kingUpLeft.hasPiece()) {
                    move = true;
                    kingUpLeft.posMove();
                }
            }
            
            if (rightCol < 8 && upRow >= 0) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == rightCol && GridPane.getRowIndex(node) == upRow) {
                        kingUpRight = (CheckerSquares) node;
                    }
                }
                
                if (!kingUpRight.hasPiece()) {
                    move = true;
                    kingUpRight.posMove();
                }
            }
            
            if (kingDownLeft != null) {
                if (kingDownLeft.hasPiece()) {
                    if (!kingDownLeft.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                        canEatK();
                    }
                }
            }
            
            if (kingDownRight != null) {
                if (kingDownRight.hasPiece()) {
                    if (!kingDownRight.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                        canEatK();
                    }
                }
            }
            
            if (kingUpLeft != null) {
                if (kingUpLeft.hasPiece()) {
                    if (!kingUpLeft.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                        canEatK();
                    }
                }
            }
            
            if (kingUpRight != null) {
                if (kingUpRight.hasPiece()) {
                    if (!kingUpRight.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                        canEatK();
                    }
                }
            }
        }
    }
    
    public void canEatK() {
        
        int currentCol;
        int currentRow;
        int jumpCol;
        int jumpRow;
        
        if (kingDownLeft != null) {
            if (kingDownLeft.hasPiece()) {
                if (!kingDownLeft.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                    currentCol = GridPane.getColumnIndex(kingDownLeft);
                    currentRow = GridPane.getRowIndex(kingDownLeft);
                    
                    jumpCol = currentCol - 1;
                    jumpRow = currentRow + 1;
                    
                    if (jumpCol >= 0 && jumpRow < 8) {
                        
                        eatKDL = kingDownLeft;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                kingDownLeft = (CheckerSquares) node;
                            }
                        }
                        
                        if(!kingDownLeft.hasPiece()) {
                            move = true;
                            kingDownLeft.posMove();
                        } else {
                            eatKDL = null;
                        }
                    }
                }
            }
        }
        
        if (kingDownRight != null) {
            if (kingDownRight.hasPiece()) {
                if (!kingDownRight.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                    currentCol = GridPane.getColumnIndex(kingDownRight);
                    currentRow = GridPane.getRowIndex(kingDownRight);
                    
                    jumpCol = currentCol + 1;
                    jumpRow = currentRow + 1;
                    
                    if (jumpCol < 8 && jumpRow < 8) {
                        
                        eatKDR = kingDownRight;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                kingDownRight = (CheckerSquares) node;
                            }
                        }
                        
                        if(!kingDownRight.hasPiece()) {
                            move = true;
                            kingDownRight.posMove();
                        } else {
                            eatKDR = null;
                        }
                    }
                }
            }
        }
        
        if (kingUpLeft != null) {
            if (kingUpLeft.hasPiece()) {
                if (!kingUpLeft.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                    currentCol = GridPane.getColumnIndex(kingUpLeft);
                    currentRow = GridPane.getRowIndex(kingUpLeft);
                    
                    jumpCol = currentCol - 1;
                    jumpRow = currentRow - 1;
                    
                    if (jumpCol >= 0 && jumpRow >= 0) {
                        
                        eatKUL = kingUpLeft;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                kingUpLeft = (CheckerSquares) node;
                            }
                        }
                        
                        if(!kingUpLeft.hasPiece()) {
                            move = true;
                            kingUpLeft.posMove();
                        } else {
                            eatKUL = null;
                        }
                    }
                }
            }
        }
        
        if (kingUpRight != null) {
            if (kingUpRight.hasPiece()) {
                if (!kingUpRight.getPiece().getTeam().equals(currentSquare.getPiece().getTeam())) {
                    currentCol = GridPane.getColumnIndex(kingUpRight);
                    currentRow = GridPane.getRowIndex(kingUpRight);
                    
                    jumpCol = currentCol + 1;
                    jumpRow = currentRow - 1;
                    
                    if (jumpCol < 8 && jumpRow >= 0) {
                        
                        eatKUR = kingUpRight;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                kingUpRight = (CheckerSquares) node;
                            }
                        }
                        
                        if(!kingUpRight.hasPiece()) {
                            move = true;
                            kingUpRight.posMove();
                        } else {
                            eatKUR = null;
                        }
                    }
                }
            }
        }
        
    }
    
    
    public void canMoveB () {
        
        if(!turnW) {
            currentSquare.select();
            int currentCol = GridPane.getColumnIndex(currentSquare);
            int currentRow = GridPane.getRowIndex(currentSquare);
            
            
            int leftCol = currentCol - 1;
            int leftRow = currentRow + 1;
            int rightCol = currentCol + 1;
            int rightRow = currentRow + 1;
            
            leftSquare = null;
            rightSquare = null;
            eatSquareR = null;
            eatSquareL = null;
            
            if (leftCol >= 0 && leftRow < 8) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == leftCol && GridPane.getRowIndex(node) == leftRow) {
                        leftSquare = (CheckerSquares) node;
                    }
                }
                
                if (!leftSquare.hasPiece()) {
                    move = true;
    //                leftSquare.setOnMouseClicked(this::move);
                    leftSquare.posMove();
                    
                }
            }
            
            if (rightCol < 8 && rightRow < 8) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == rightCol && GridPane.getRowIndex(node) == rightRow) {
                        rightSquare = (CheckerSquares) node;
                    }
                }
                
                if (!rightSquare.hasPiece()) {
                    move = true;
    //                rightSquare.setOnMouseClicked(this::move);
                    rightSquare.posMove();
                }
            }
            
            if (rightSquare != null) {
                if (rightSquare.hasPiece()) {
                    if(rightSquare.getPiece().getTeam().equals("white")) {
                        canEatB();
                    }
                }
            }
            
            if (leftSquare != null) {
                if (leftSquare.hasPiece()) {
                    if(leftSquare.getPiece().getTeam().equals("white")) {
                        canEatB();
                    }
                }
            }
        }
    }
    
    public void canEatB() {
        int currentCol;
        int currentRow;
        int jumpCol;
        int jumpRow;
        
        if (leftSquare != null) {
            if (leftSquare.hasPiece()) {
                if (leftSquare.getPiece().getTeam().equals("white")) {
                    currentCol = GridPane.getColumnIndex(leftSquare);
                    currentRow = GridPane.getRowIndex(leftSquare);
                    
                    jumpCol = currentCol - 1;
                    jumpRow = currentRow + 1;
                    
                    if (jumpCol >= 0 && jumpRow < 8) {
                        
                        eatSquareL = leftSquare;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                leftSquare = (CheckerSquares) node;
                            }
                        }
                        
                        if(!leftSquare.hasPiece()) {
                            move = true;
                            leftSquare.posMove();
                        } else {
                            eatSquareL = null;
                        }
                        
                    
                    }
                }
            }
        }
        
        if (rightSquare != null) {
            if (rightSquare.hasPiece()) {
                if (rightSquare.getPiece().getTeam().equals("white")) {
                    currentCol = GridPane.getColumnIndex(rightSquare);
                    currentRow = GridPane.getRowIndex(rightSquare);
                    
                    
                    jumpCol = currentCol + 1;
                    jumpRow = currentRow + 1;
                    
                    if (jumpCol < 8 && jumpRow < 8) {
                        
                        eatSquareR = rightSquare;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                rightSquare = (CheckerSquares) node;
                            }
                        }
                        
                        if(!rightSquare.hasPiece()) {
                            move = true;
                            rightSquare.posMove();
                        } else {
                            eatSquareR = null;
                        }
                    }
                }
            }
        }
    }
    
    public void canMoveW () {
        
        if(turnW) {
            currentSquare.select();
            int currentCol = getColumnIndex(currentSquare);
            int currentRow = getRowIndex(currentSquare);
            
            
            int leftCol = currentCol - 1;
            int leftRow = currentRow - 1;
            int rightCol = currentCol + 1;
            int rightRow = currentRow - 1;
            
            leftSquare = null;
            rightSquare = null;
            eatSquareR = null;
            eatSquareL = null;
            
            if (leftCol >= 0 && leftRow >= 0) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == leftCol && GridPane.getRowIndex(node) == leftRow) {
                        leftSquare = (CheckerSquares) node;
                    }
                }
                
                if (!leftSquare.hasPiece()) {
                    move = true;
    //                leftSquare.setOnMouseClicked(this::move);
                    
                    leftSquare.posMove();
                }
            }
            
            if (rightCol < 8 && rightRow >= 0) {
                for (Node node : getChildren()) {
                    if (GridPane.getColumnIndex(node) == rightCol && GridPane.getRowIndex(node) == rightRow) {
                        rightSquare = (CheckerSquares) node;
                    }
                }
                
                if (!rightSquare.hasPiece()) {
                    rightSquare.posMove();
                    move = true;
    //                rightSquare.setOnMouseClicked(this::move);
                    
                    
                }
            }
            
            if (rightSquare != null) {
                if (rightSquare.hasPiece()) {
                    if(rightSquare.getPiece().getTeam().equals("black")) {
                        canEatW();
                    }
                }
            }
            
            if (leftSquare != null) {
                if (leftSquare.hasPiece()) {
                    if(leftSquare.getPiece().getTeam().equals("black")) {
                        canEatW();
                    }
                }
            }
            
        }
    }
    
    public void canEatW() {
        int currentCol;
        int currentRow;
        int jumpCol;
        int jumpRow;
        
        if (leftSquare != null) {
            if (leftSquare.hasPiece()) {
                if (leftSquare.getPiece().getTeam().equals("black")) {
                    currentCol = GridPane.getColumnIndex(leftSquare);
                    currentRow = GridPane.getRowIndex(leftSquare);
                    
                    jumpCol = currentCol - 1;
                    jumpRow = currentRow - 1;
                    
                    if (jumpCol >= 0 && jumpRow >= 0) {
                        
                        eatSquareL = leftSquare;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                leftSquare = (CheckerSquares) node;
                            }
                        }
                        
                        if(!leftSquare.hasPiece()) {
                            
                            move = true;
                            
                            leftSquare.posMove();
                        } else {
                            eatSquareL = null;
                        }
                    }
                }
            }
        }
        
        if (rightSquare != null) {
            if (rightSquare.hasPiece()) {
                if (rightSquare.getPiece().getTeam().equals("black")) {
                    currentCol = GridPane.getColumnIndex(rightSquare);
                    currentRow = GridPane.getRowIndex(rightSquare);
                    
                    
                    jumpCol = currentCol + 1;
                    jumpRow = currentRow - 1;
                    
                    if (jumpCol < 8 && jumpRow >= 0) {
                        
                        eatSquareR = rightSquare;
                        
                        for (Node node : getChildren()) {
                            if (GridPane.getColumnIndex(node) == jumpCol && GridPane.getRowIndex(node) == jumpRow) {
                                rightSquare = (CheckerSquares) node;
                            }
                        }
                        
                        if(!rightSquare.hasPiece()) {

                            if (eatSquareR == null) {
                                System.out.println("EatR null @ canEatW");
                            }
                            rightSquare.posMove();
                            move = true;
                        } else {
                            System.out.println("asd");
                           eatSquareR = null;
                        }
                    }
                }
            }
        }
    }

    public void reset() {
        getChildren().clear();
        int color = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 9; col++) {
                CheckerSquares square;
                if (color % 2 == 0) {
                   square = new CheckerSquares(Color.YELLOW);
                } else {
                    if(row <= 2) {
                        CheckerPieces pieceB = new CheckerPieces(Color.BLACK);

                        square = new CheckerSquares(Color.PURPLE, pieceB);
                        square.setOnMouseClicked(this::move);
                    } else if (row >=5 ) {
                        CheckerPieces pieceW = new CheckerPieces(Color.WHITE);
                        square = new CheckerSquares(Color.PURPLE, pieceW);
                        square.setOnMouseClicked(this::move);
                    } else {
                   square = new CheckerSquares(Color.PURPLE);
                   square.setOnMouseClicked(this::move);
                    }
                }
                add(square, col, row);

                color++;
            }
        }
        
    }
    
    public String winner() {
        if (deadW == 12) {
            return "black";
        } else if (deadB == 12) {
            return "white";
        } return "none";
    }
    
}
