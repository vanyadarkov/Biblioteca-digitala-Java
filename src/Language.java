import java.util.Objects;

public class Language {
    private final int ID;
    private String code;
    private String name;

    public Language(int ID) {
        this.ID = ID;
    }

    public Language(String[] parameters) {
        this.ID = Integer.parseInt(parameters[0]);
        this.code = parameters[1];
        this.name = parameters[2];
    }


    public int getID() {
        return ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;
        Language language = (Language) o;
        return ID == language.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Language{");
        sb.append("ID=").append(ID);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
