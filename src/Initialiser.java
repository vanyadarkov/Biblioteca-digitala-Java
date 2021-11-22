import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Initialiser {
    ArrayList<Author> authors;
    ArrayList<Book> books;
    ArrayList<Countries> countries;
    ArrayList<EditorialGroup> editorialGroups;
    ArrayList<Language> languages;
    ArrayList<PublishingBrand> publishingBrands;
    ArrayList<PublishingRetailer> publishingRetailers;

    public Initialiser() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        countries = new ArrayList<>();
        editorialGroups = new ArrayList<>();
        languages = new ArrayList<>();
        publishingBrands = new ArrayList<>();
        publishingRetailers = new ArrayList<>();
    }

    /**
     * Method to read input from file
     * @param filePath path to file we want to read from
     * @return List of String[]. Every element of list is a line represented as a String[], which each string is a
     * parameter
     * @throws IOException
     */
    public List<String[]> readFile(String filePath) throws IOException {
        List<String[]> listOfFileLines = new LinkedList<>();
        BufferedReader br = null;
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String line;
            String[] parameters;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count = 1;
                    continue;
                }
                parameters = line.split("###");
                listOfFileLines.add(parameters);
            }
            return listOfFileLines;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Method to add authors to book
     * @param parameters parameters[0] - bookId which we add the author, parameters[1] authorId to add
     */
    public void addAuthorsToBook(String[] parameters){
        int bookId = Integer.parseInt(parameters[0]);
        int authorId = Integer.parseInt(parameters[1]);
        Book auxBook = new Book(bookId);
        Author auxAuthor = new Author(authorId);
        auxBook = this.books.get(this.books.indexOf(auxBook));
        auxAuthor = this.authors.get(this.authors.indexOf(auxAuthor));
        auxBook.addAuthor(auxAuthor);
    }

    /**
     * Method to add a book to somewhere.
     * @param parameters parameters[0] - bookId which we want to add, parameters[1] destination
     * @param whereToAdd destination, could be "Editorial Group", "Publishing Brand" or "Publishing Retailer"
     */
    public void addBookToSomewhere(String[] parameters, String whereToAdd){
        int bookId = Integer.parseInt(parameters[1]);
        int destinationId = Integer.parseInt(parameters[0]);
        Book auxBook = new Book(bookId);
        auxBook = this.books.get(this.books.indexOf(auxBook));
        if(whereToAdd.equals("Editorial Group")){
            EditorialGroup auxEditorialGroup = new EditorialGroup(destinationId);
            auxEditorialGroup = this.editorialGroups.get(this.editorialGroups.indexOf(auxEditorialGroup));
            auxEditorialGroup.addBook(auxBook);
            return;
        }
        if(whereToAdd.equals("Publishing Brand")){
            PublishingBrand auxPublishingBrand = new PublishingBrand(destinationId);
            auxPublishingBrand = this.publishingBrands.get(this.publishingBrands.indexOf(auxPublishingBrand));
            auxPublishingBrand.addBook(auxBook);
            return;
        }
        if(whereToAdd.equals("Publishing Retailer")){
            PublishingRetailer auxPublishingRetailer = new PublishingRetailer(destinationId);
            auxPublishingRetailer = this.publishingRetailers.get(this.publishingRetailers.indexOf(auxPublishingRetailer));
            auxPublishingRetailer.addPublishingArtifact(auxBook);
            return;
        }
    }

    /**
     * Method to add a country to a publishing Retailer
     * @param parameters parameters[0] - retailer ID, parameters[1] - country ID
     */
    public void addCountryToPublishingRetailer(String[] parameters){
        int retailerId = Integer.parseInt(parameters[0]);
        int countryId = Integer.parseInt(parameters[1]);
        PublishingRetailer auxRetailer = new PublishingRetailer(retailerId);
        auxRetailer = publishingRetailers.get(publishingRetailers.indexOf(auxRetailer));
        Countries auxCountry = new Countries(countryId);
        auxCountry = countries.get(countries.indexOf(auxCountry));
        auxRetailer.addCountry(auxCountry);
    }
    /**
     * Method to add a artifact to somewhere.
     * @param parameters parameters[0] - bookId which we want to add, parameters[1] destination
     * @param whichArtifactIs destination, could be "Editorial Group" or "Publishing Brand"
     */
    public void addArtifactToPublishingRetailer(String[] parameters, String whichArtifactIs){
        int retailerId = Integer.parseInt(parameters[0]);
        int artifactId = Integer.parseInt(parameters[1]);
        PublishingRetailer auxRetailer = new PublishingRetailer(retailerId);
        auxRetailer = publishingRetailers.get(publishingRetailers.indexOf(auxRetailer));
        if(whichArtifactIs.equals("Editorial Group")){
            EditorialGroup auxEditorial = new EditorialGroup(artifactId);
            auxEditorial = editorialGroups.get(editorialGroups.indexOf(auxEditorial));
            auxRetailer.addPublishingArtifact(auxEditorial);
            return;
        }
        if(whichArtifactIs.equals("Publishing Brand")){
            PublishingBrand auxBrand = new PublishingBrand(artifactId);
            auxBrand = publishingBrands.get(publishingBrands.indexOf(auxBrand));
            auxRetailer.addPublishingArtifact(auxBrand);
        }
    }

}
