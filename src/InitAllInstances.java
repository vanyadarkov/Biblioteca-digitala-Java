import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class InitAllInstances {
    public static void main(String[] args) {
        Initialiser init = new Initialiser();

        try{
            List<String[]> authorsLines= init.readFile("init/authors.in");
            List<String[]> booksLines = init.readFile("init/books.in");
            List<String[]> booksAuthorsLines = init.readFile("init/books-authors.in");
            List<String[]> countriesLines = init.readFile("init/countries.in");
            List<String[]> editorialGroupLines = init.readFile("init/editorial-groups.in");
            List<String[]> editorialGroupBooksLines = init.readFile("init/editorial-groups-books.in");
            List<String[]> languagesLines = init.readFile("init/languages.in");
            List<String[]> publishingBrandsLines = init.readFile("init/publishing-brands.in");
            List<String[]> publishingBrandsBooksLines = init.readFile("init/publishing-brands-books.in");
            List<String[]> publishingRetailersLines = init.readFile("init/publishing-retailers.in");
            List<String[]> publishingRetailerBooksLines = init.readFile("init/publishing-retailers-books.in");
            List<String[]> publishingRetailerCountriesLines = init.readFile("init/publishing-retailers-countries.in");
            List<String[]> publishingRetailerEdiGroupsLines = init.readFile("init/publishing-retailers-editorial-groups.in");
            List<String[]> publishingRetailerPubBrandsLines = init.readFile("init/publishing-retailers-publishing-brands.in");

            for (String[] parametersFromLine: authorsLines) {
                init.authors.add(new Author(parametersFromLine));
            }
            for(String[] parametersFromLine : booksLines){
                init.books.add(new Book(parametersFromLine));
            }
            for(String[] parametersFromLine : booksAuthorsLines){
                init.addAuthorsToBook(parametersFromLine);
            }
            for(String[] parametersFromLine : countriesLines){
                init.countries.add(new Countries(parametersFromLine));
            }
            for(String[] parametersFromLine : editorialGroupLines){
                init.editorialGroups.add(new EditorialGroup(parametersFromLine));
            }
            for(String[] parametersFromLine : editorialGroupBooksLines){
                init.addBookToSomewhere(parametersFromLine, "Editorial Group");
            }
            for(String[] parametersFromLine : languagesLines){
                init.languages.add(new Language(parametersFromLine));
            }
            for(String[] parametersFromLine : publishingBrandsLines){
                init.publishingBrands.add(new PublishingBrand(parametersFromLine));
            }
            for(String[] parametersFromLine : publishingBrandsBooksLines){
                init.addBookToSomewhere(parametersFromLine,"Publishing Brand");
            }
            for(String[] parametersFromLine : publishingRetailersLines){
                init.publishingRetailers.add(new PublishingRetailer(parametersFromLine));
            }
            for(String[] parametersFromLine : publishingRetailerBooksLines){
                init.addBookToSomewhere(parametersFromLine, "Publishing Retailer");
            }
            for(String[] parametersFromLine : publishingRetailerCountriesLines){
                init.addCountryToPublishingRetailer(parametersFromLine);
            }
            for(String[] parametersFromLine : publishingRetailerEdiGroupsLines){
                init.addArtifactToPublishingRetailer(parametersFromLine, "Editorial Group");
            }
            for(String[] parametersFromLine : publishingRetailerPubBrandsLines){
                init.addArtifactToPublishingRetailer(parametersFromLine, "Publishing Brand");
            }
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }

        Administration admin = new Administration(init.publishingRetailers, init.languages);

        ArrayList test = admin.getBooksForPublishingRetailerID(14);
        ArrayList test1 = admin.getBooksForPublishingRetailerID(2);
        ArrayList test2 = admin.getBooksForPublishingRetailerID(19);
        ArrayList test3 = admin.getBooksForPublishingRetailerID(23);
        ArrayList test4 = admin.getBooksForPublishingRetailerID(20);




        try{
            admin.writeToOutputFile("get-books-for-publishing-retailer-id-test1.out", 1, test);
            admin.writeToOutputFile("get-books-for-publishing-retailer-id-test2.out", 1, test1);
            admin.writeToOutputFile("get-books-for-publishing-retailer-id-test3.out", 1, test2);
            admin.writeToOutputFile("get-books-for-publishing-retailer-id-test4.out", 1, test3);
            admin.writeToOutputFile("get-books-for-publishing-retailer-id-test5.out", 1, test4);

            test = admin.getCommonBooksForRetailerIDs(29,10);
            test1 = admin.getCommonBooksForRetailerIDs(27,29);
            test2 = admin.getCommonBooksForRetailerIDs(12,19);
            test3 = admin.getCommonBooksForRetailerIDs(8,11);
            test4 = admin.getCommonBooksForRetailerIDs(27,23);

            admin.writeToOutputFile("get-common-books-for-retailer-ids-test1.out", 1, test);
            admin.writeToOutputFile("get-common-books-for-retailer-ids-test2.out", 1, test1);
            admin.writeToOutputFile("get-common-books-for-retailer-ids-test3.out", 1, test2);
            admin.writeToOutputFile("get-common-books-for-retailer-ids-test4.out", 1, test3);
            admin.writeToOutputFile("get-common-books-for-retailer-ids-test5.out", 1, test4);

            test = admin.getAllBooksForRetailerIDs(28,32);
            test1 = admin.getAllBooksForRetailerIDs(14,16);
            test2 = admin.getAllBooksForRetailerIDs(7,19);
            test3 = admin.getAllBooksForRetailerIDs(3,3);
            test4 = admin.getAllBooksForRetailerIDs(5,13);

            admin.writeToOutputFile("get-all-books-for-retailer-ids-test1.out", 1, test);
            admin.writeToOutputFile("get-all-books-for-retailer-ids-test2.out", 1, test1);
            admin.writeToOutputFile("get-all-books-for-retailer-ids-test3.out", 1, test2);
            admin.writeToOutputFile("get-all-books-for-retailer-ids-test4.out", 1, test3);
            admin.writeToOutputFile("get-all-books-for-retailer-ids-test5.out", 1, test4);

            test = admin.getLanguagesForPublishingRetailerID(6);
            test1 = admin.getLanguagesForPublishingRetailerID(28);
            test2 = admin.getLanguagesForPublishingRetailerID(11);
            test3 = admin.getLanguagesForPublishingRetailerID(30);
            test4 = admin.getLanguagesForPublishingRetailerID(13);

            admin.writeToOutputFile("get-languages-for-publishing-retailer-id-test1.out", 2, test);
            admin.writeToOutputFile("get-languages-for-publishing-retailer-id-test2.out", 2, test1);
            admin.writeToOutputFile("get-languages-for-publishing-retailer-id-test3.out", 2, test2);
            admin.writeToOutputFile("get-languages-for-publishing-retailer-id-test4.out", 2, test3);
            admin.writeToOutputFile("get-languages-for-publishing-retailer-id-test5.out", 2, test4);

            test = admin.getCountriesForBookID(2832);
            test1 = admin.getCountriesForBookID(5598);
            test2 = admin.getCountriesForBookID(5856);
            test3 = admin.getCountriesForBookID(10995);
            test4 = admin.getCountriesForBookID(1000);

            admin.writeToOutputFile("get-countries-for-book-id-test1.out", 3, test);
            admin.writeToOutputFile("get-countries-for-book-id-test2.out", 3, test1);
            admin.writeToOutputFile("get-countries-for-book-id-test3.out", 3, test2);
            admin.writeToOutputFile("get-countries-for-book-id-test4.out", 3, test3);
            admin.writeToOutputFile("get-countries-for-book-id-test5.out", 3, test4);

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
