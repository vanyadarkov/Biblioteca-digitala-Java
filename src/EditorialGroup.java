import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EditorialGroup implements IPublishingArtifact{
    private final int ID;
    private String name;
    private ArrayList<Book> books;


    public EditorialGroup(String[] parameters) {
        this.ID = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.books = new ArrayList(0);
    }

    public EditorialGroup(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EditorialGroup)) return false;
        EditorialGroup that = (EditorialGroup) o;
        return this.ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String Publish() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<xml>\n");
        sb.append(this.toString());
        sb.append("\n</xml>");
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\t<editorialGroup>\n");
        sb.append("\t\t<ID>").append(ID).append("</ID>\n");
        sb.append("\t\t<Name>").append(name).append("</Name>\n");
        sb.append("\t</editorialGroup>\n");
        sb.append("\t<books>\n");
        final StringBuffer allBooks = new StringBuffer();
        Object[] booksArray = books.toArray();
        for(int i = 0; i < books.size(); i++){
            allBooks.append("\t\t<book>\n");
            allBooks.append(((Book)booksArray[i]).toString(3));
            allBooks.append("\t\t</book>\n");
        }
        sb.append(allBooks).append("\t</books>");
        return sb.toString();
    }
}
