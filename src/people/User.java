package people;

import java.util.UUID;

public abstract class User {

    //attributes
    protected final String name;
    protected final String id;

    //constructor
    public User (String name){
        if (name == null){throw new IllegalArgumentException("Name cannot be null.");}
        this.name= name;
        this.id= generateId();}

    private String generateId() {return "U" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();}

    //getters
    public String getId() {return id;}
    public String getName() {return name;}
}
