import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Administration {

    private ArrayList<PublishingRetailer> publishingRetailers;
    private ArrayList<Language> languages;

    /**
     * Constructor for Administration object
     * @param publishingRetailers - ArrayList of publishingRetailers
     * @param languages - ArrayList of all languages
     */
    public Administration(ArrayList<PublishingRetailer> publishingRetailers, ArrayList<Language> languages) {
        this.publishingRetailers = publishingRetailers;
        this.languages = languages;
    }

    public ArrayList<PublishingRetailer> getPublishingRetailers() {
        return publishingRetailers;
    }

    public void setPublishingRetailers(ArrayList<PublishingRetailer> publishingRetailers) {
        this.publishingRetailers = publishingRetailers;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    /**
     * Get all unique books from a retailer. Looking in entire array of publishingRetailers. Checks if its artifact is
     * book, pubBrand or editGroup in order to extract a book or more(in case if it is PubBrand or editGroup)
     * @param publishingRetailerID id of retailer from where we are going to get all books
     * @return a list of all books we found
     */
    public ArrayList<Book> getBooksForPublishingRetailerID(int publishingRetailerID) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> auxList = null;
        PublishingRetailer retailer = publishingRetailers.get(publishingRetailers.indexOf(new PublishingRetailer(publishingRetailerID)));
        ArrayList<IPublishingArtifact> artifacts = retailer.getPublishingArtifacts();

        for (IPublishingArtifact artifact : artifacts) {
            if (artifact instanceof Book book) {
                if (!books.contains(book)) books.add(book);
                continue;
            }
            if (artifact instanceof EditorialGroup editorialGroup) {
                auxList = editorialGroup.getBooks();
            }
            if (artifact instanceof PublishingBrand publishingBrand) {
                auxList = publishingBrand.getBooks();
            }

            for (Book auxBook : auxList) {
                if (!books.contains(auxBook)) books.add(auxBook);
            }
        }
        return books;
    }

    /**
     * Method to get all the languages in which the retailer publishes its books
     * @param publishingRetailerID id of retailer we are looking for
     * @return list with all languages
     */
    public ArrayList<Language> getLanguagesForPublishingRetailerID(int publishingRetailerID) {
        ArrayList<Language> languagesList = new ArrayList<>();
        ArrayList<Book> booksList = null;
        Language auxLanguage;
        int languageId;
        PublishingRetailer retailer = publishingRetailers.get(publishingRetailers.indexOf(new PublishingRetailer(publishingRetailerID)));
        ArrayList<IPublishingArtifact> artifacts = retailer.getPublishingArtifacts();
        for (IPublishingArtifact artifact : artifacts) {
            if (artifact instanceof Book book) {
                auxLanguage = languages.get(languages.indexOf(new Language(book.getLanguageID())));
                if (!languagesList.contains(auxLanguage)) languagesList.add(auxLanguage);
                continue;
            }
            if (artifact instanceof EditorialGroup editorialGroup) {
                booksList = editorialGroup.getBooks();
            }
            if (artifact instanceof PublishingBrand publishingBrand) {
                booksList = publishingBrand.getBooks();
                continue;
            }

            for (Book auxBook : booksList) {
                languageId = auxBook.getLanguageID();
                auxLanguage = languages.get(languages.indexOf(new Language(auxBook.getLanguageID())));
                if (!languagesList.contains(auxLanguage)) languagesList.add(auxLanguage);
            }

        }

        return languagesList;
    }

    /**
     * Method that returns a list of countries the bookId book has reached. This method is searching bookID in
     * entire publishingRetailers array in order to found all countries.
     * @param bookID
     * @return
     */
    public ArrayList<Countries> getCountriesForBookID(int bookID) {
        ArrayList<Countries> countriesList = new ArrayList<>();
        ArrayList<Book> books = null;
        for (PublishingRetailer publishingRetailer : publishingRetailers) {
            books = getBooksForPublishingRetailerID(publishingRetailer.getID());
            if (books.contains(new Book(bookID))) {
                countriesList.addAll(publishingRetailer.getCountries());
            }
        }
        return countriesList;
    }

    /**
     * Common books between retailers -
     * method that returns a list of common cards between retailers and receives as parameter two IDs
     * of retailers
     * @param retailerID1
     * @param retailerID2
     * @return array of common books
     */
    public ArrayList<Book> getCommonBooksForRetailerIDs(int retailerID1, int retailerID2) {
        ArrayList<Book> finalList = new ArrayList<>();
        ArrayList<Book> retailer1Books = getBooksForPublishingRetailerID(retailerID1);
        ArrayList<Book> retailer2Books = getBooksForPublishingRetailerID(retailerID2);
        HashSet<Book> retailerSetBooks = new HashSet<>(retailer2Books);
        for (Book book : retailer1Books) {
            if (retailerSetBooks.contains(book)) finalList.add(book);
        }
        return finalList;
    }

    /**
     * Books of retailers (union) -
     * method that returns a list of books between retailers (the union of the two lists) and receives as
     * parameter two retailer IDs
     * @param retailerID1
     * @param retailerID2
     * @return array of books union between these 2 retailers
     */
    public ArrayList<Book> getAllBooksForRetailerIDs(int retailerID1, int retailerID2) {
        ArrayList<Book> retailer1Books = getBooksForPublishingRetailerID(retailerID1);
        ArrayList<Book> retailer2Books = getBooksForPublishingRetailerID(retailerID2);
        HashSet<Book> retailerSetBooks = new HashSet<>(retailer2Books);
        retailerSetBooks.addAll(retailer1Books);
        ArrayList<Book> finalList = new ArrayList<Book>(retailerSetBooks.stream().toList());
        return finalList;
    }

    /**
     * Write a received array List to outputFile,
     * @param outputFileName path to file we want to write in
     * @param WhichMethod 1 - to write an array of books to output, 2 - language array, 3 - countries array
     * @param arrayList list which contains objects resulting from the execution of the methods within the theme.
     *                  They can be Book, Countries or Language
     * @throws IOException
     */
    public void writeToOutputFile(String outputFileName, int WhichMethod, ArrayList arrayList) throws IOException {
        File outputFile = new File("tests-outputs/" + outputFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        final StringBuffer sb = new StringBuffer();

        if(WhichMethod == 1){
            ArrayList<Book> books = arrayList;
            for(Book b : books){
                sb.append(b.Publish()).append("\n");
            }
        }
        if(WhichMethod == 2){
            ArrayList<Language> langList = arrayList;
            for(Language l : langList){
                sb.append(l.getName()).append("\n");
            }
        }
        if(WhichMethod == 3){
            ArrayList<Countries> countriesList = arrayList;
            for(Countries c : countriesList){
                sb.append(c.getCountryCode()).append("\n");
            }
        }

        writer.write(sb.toString());
        writer.close();
    }
}





