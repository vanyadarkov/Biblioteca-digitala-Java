import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Book implements IPublishingArtifact{
    private final int ID;
    private String name;
    private String subtitle;
    public final String ISBN;
    private int pageCount;
    private String keywords;
    private int languageID;
    private ArrayList<Author> authors;
    private Calendar createdOn;

    public Book(int ID) {
        this.ID = ID;
        this.ISBN = null;
    }

    public Book(String[] parameters) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        Date date = sdf.parse(parameters[7]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        this.ID = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.subtitle = parameters[2];
        this.ISBN = parameters[3];
        this.pageCount = Integer.parseInt(parameters[4]);
        this.keywords = parameters[5];
        this.languageID = Integer.parseInt(parameters[6]);
        this.createdOn = calendar;
        this.authors = new ArrayList<>();
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public ArrayList getAuthors() {
        return this.authors;
    }

    /**
     * Add a author to book's authors list
     * @param author author to add
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getCreatedOn() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
        Date date = this.createdOn.getTime();
        String formatted = format.format(date);
        return formatted;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return this.ID == book.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }


    /**\
     *
     * @return Publish string with specified in PDF format
     */
    @Override
    public String Publish() {
        final StringBuffer sb = new StringBuffer("<xml>\n");
        sb.append(this.toString(1));
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     *
     * @param nrOfTabs number of tabs for each member variable of book
     * @return string with the characteristics of the book
     */
    public String toString(int nrOfTabs) {
        final StringBuffer sb = new StringBuffer();
        String tab = "\t";
        sb.append(tab.repeat(nrOfTabs)).append("<title>").append(name)
                            .append("</title>\n");
        sb.append(tab.repeat(nrOfTabs)).append("<subtitle>").append(subtitle)
                               .append("</subtitle\n");
        sb.append(tab.repeat(nrOfTabs)).append("<isbn>").append(ISBN)
                           .append("</isbn>\n");
        sb.append(tab.repeat(nrOfTabs)).append("<pageCount>").append(pageCount)
                                .append("</pageCount>\n");
        sb.append(tab.repeat(nrOfTabs)).append("<keywords>").append(keywords)
                               .append("</keywords>\n");
        sb.append(tab.repeat(nrOfTabs)).append("<languageID>").append(languageID)
                                 .append("</languageID>\n");
        sb.append(tab.repeat(nrOfTabs)).append("<createdOn>").append(this.getCreatedOn())
                                .append("</createdOn>\n");
        sb.append(tab.repeat(nrOfTabs)).append("<authors>").append(authors == null ? "null" : getAuthors())
                              .append("</authors>\n");
        return (sb.toString()).replace("[", "").replace("]", "");
    }
}
