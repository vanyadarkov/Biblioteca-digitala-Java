import java.util.ArrayList;
import java.util.Objects;

public class PublishingRetailer {
    private final int ID;
    private String name;
    private ArrayList<IPublishingArtifact> publishingArtifacts;
    private ArrayList<Countries> countries;

    public PublishingRetailer(int ID) {
        this.ID = ID;
    }

    public PublishingRetailer(String[] parameters) {
        this.ID = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.publishingArtifacts = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    public PublishingRetailer(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.publishingArtifacts = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublishingRetailer)) return false;
        PublishingRetailer that = (PublishingRetailer) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
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

    public ArrayList<IPublishingArtifact> getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public void addPublishingArtifact(IPublishingArtifact publishingArtifact) {
        this.publishingArtifacts.add(publishingArtifact);
    }

    public ArrayList<Countries> getCountries() {
        return countries;
    }

    public void addCountry(Countries country) {
        this.countries.add(country);
    }
}
