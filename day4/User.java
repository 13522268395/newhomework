package homework.day4;

/**
 * @Author myy
 * @create 2019/6/26 0:04
 */
public class User {
    private String id;
    private String name;
    private String gender;
    private String score;
    private String address;

    public User() {
    }

    public User(String id, String name, String gender, String score, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getScore() {
        return score;
    }

    public String getAddress() {
        return address;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public User setScore(String score) {
        this.score = score;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", score='" + score + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
