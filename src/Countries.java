import java.util.Objects;

public class Countries {
    private final int ID;
    private String countryCode;

    public Countries(int ID, String countryCode) {
        this.ID = ID;
        this.countryCode = countryCode;
    }

    public Countries(String[] parameters) {
        this.ID = Integer.parseInt(parameters[0]);
        this.countryCode = parameters[1];
    }

    public Countries(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Countries)) return false;
        Countries countries = (Countries) o;
        return ID == countries.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public int getID() {
        return ID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Countries{");
        sb.append("ID=").append(ID);
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
