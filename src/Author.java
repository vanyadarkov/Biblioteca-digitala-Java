import java.util.Objects;

public class Author {
    private final int ID;
    private String firstName;
    private String lastName;

    /**
     * Object constructor for Author class
     * @param parameters Array of String which is extracted from input file each field is an parameter for class
     */
    public Author(String[] parameters) {
        this.ID = Integer.parseInt(parameters[0]);
        this.firstName = parameters[1];
        this.lastName = parameters[2];
    }

    public Author(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param o object with which we compare
     * @return true - objects are equals, false - otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return this.ID == author.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * toString for author
     * @return string with Author fields (checks if one of name is empty or not in order to not add whitespaces)
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        if(!firstName.equals("")){
            sb.append(firstName);
        }
        if (!lastName.equals("") && !firstName.equals("")){
            sb.append(" ");
        }
        sb.append(lastName);

        return sb.toString();
    }
}
