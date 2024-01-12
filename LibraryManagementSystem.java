import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class LibraryManagementSystem extends JFrame implements ActionListener {

    private final JTextField bookNameTextField, authorTextField, bookIdTextField, priceTextField;
    private final JTextArea displayTextArea;
    private final Map<String, Book> library;

    public LibraryManagementSystem() {
        library = new HashMap<>();

        JLabel bookNameLabel = new JLabel("Book Name:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel bookIdLabel = new JLabel("Book ID:");
        JLabel priceLabel = new JLabel("Price:");

        bookNameTextField = new JTextField();
        authorTextField = new JTextField();
        bookIdTextField = new JTextField();
        priceTextField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton displayButton = new JButton("Display");
        JButton deleteButton = new JButton("Delete");

        displayTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayTextArea);

        setLayout(new GridLayout(2, 1));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(bookNameLabel);
        inputPanel.add(bookNameTextField);
        inputPanel.add(authorLabel);
        inputPanel.add(authorTextField);
        inputPanel.add(bookIdLabel);
        inputPanel.add(bookIdTextField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceTextField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        displayButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(new JLabel("Library Records:"), BorderLayout.NORTH);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        add(inputPanel);
        add(buttonPanel);
        add(displayPanel);

        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add":
                addBook();
                break;
            case "Update":
                updateBook();
                break;
            case "Display":
                displayBooks();
                break;
            case "Delete":
                deleteBook();
                break;
        }
    }

    private void addBook() {
        String bookName = bookNameTextField.getText();
        String author = authorTextField.getText();
        String bookId = bookIdTextField.getText();
        String price = priceTextField.getText();

        if (bookName.isEmpty() || author.isEmpty() || bookId.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book book = new Book(bookName, author, bookId, price);
        library.put(bookId, book);

        JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    private void updateBook() {
        String bookId = bookIdTextField.getText();

        if (bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!library.containsKey(bookId)) {
            JOptionPane.showMessageDialog(this, "Book with ID " + bookId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book book = library.get(bookId);

        String bookName = bookNameTextField.getText();
        String author = authorTextField.getText();
        String price = priceTextField.getText();

        if (!bookName.isEmpty()) {
            book.setBookName(bookName);
        }

        if (!author.isEmpty()) {
            book.setAuthor(author);
        }

        if (!price.isEmpty()) {
            book.setPrice(price);
        }

        JOptionPane.showMessageDialog(this, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    private void displayBooks() {
        if (library.isEmpty()) {
            displayTextArea.setText("No books in the library.");
        } else {
            StringBuilder displayText = new StringBuilder();
            for (Map.Entry<String, Book> entry : library.entrySet()) {
                displayText.append(entry.getValue()).append("\n");
            }
            displayTextArea.setText(displayText.toString());
        }
    }

    private void deleteBook() {
        String bookId = bookIdTextField.getText();

        if (bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Book ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!library.containsKey(bookId)) {
            JOptionPane.showMessageDialog(this, "Book with ID " + bookId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        library.remove(bookId);
        JOptionPane.showMessageDialog(this, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    private void clearFields() {
        bookNameTextField.setText("");
        authorTextField.setText("");
        bookIdTextField.setText("");
        priceTextField.setText("");
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}

class Book {
    private String bookName;
    private String author;
    private String bookId;
    private String price;

    public Book(String bookName, String author, String bookId, String price) {
        this.bookName = bookName;
        this.author = author;
        this.bookId = bookId;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookId='" + bookId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
