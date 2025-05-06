package people;

public abstract class User {

    //attributes
    private final String name;
    private final String id;

    //constructor
    public User (String name, String id){
        if (name == null){throw new IllegalArgumentException("Name cannot be null.");}
        if (id==null){throw new IllegalArgumentException("ID cannot be null.");}
        this.name= name;
        this.id= id;
    }

    //getters
    public String getId() {return id;}
    public String getName() {return name;}
}
