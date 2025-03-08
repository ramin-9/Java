import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private boolean isXTurn = true;
    private Button[][] board = new Button[3][3];

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button(" ");
                button.setMinSize(100, 100);
                button.setStyle("-fx-font-size: 24px;");
                int r = row, c = col;

                button.setOnAction(e -> makeMove(button, r, c));

                board[row][col] = button;
                grid.add(button, col, row);
            }
        }

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeMove(Button button, int row, int col) {
        if (!button.getText().equals(" "))
            return;

        button.setText(isXTurn ? "X" : "O");
        if (checkWinner()) {
            showAlert((isXTurn ? "X" : "O") + " Wins!");
            resetBoard();
        } else if (isBoardFull()) {
            showAlert("It's a Draw!");
            resetBoard();
        }
        isXTurn = !isXTurn;
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (same(board[i][0], board[i][1], board[i][2]))
                return true;
            if (same(board[0][i], board[1][i], board[2][i]))
                return true;
        }
        return same(board[0][0], board[1][1], board[2][2]) ||
                same(board[0][2], board[1][1], board[2][0]);
    }

    private boolean same(Button b1, Button b2, Button b3) {
        return !b1.getText().equals(" ") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (Button[] row : board)
            for (Button btn : row)
                if (btn.getText().equals(" "))
                    return false;
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetBoard() {
        for (Button[] row : board)
            for (Button btn : row)
                btn.setText(" ");
        isXTurn = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
