package people;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable
{
    private static final long serialVersionUID = -4537788144857287537L;

    //attributes
    protected transient String name;
    protected transient String id;

    //constructor
    public User (){
        name = "example";
        id = "999";
    }

    public User (String name){
        if (name == null){throw new IllegalArgumentException("Name cannot be null.");}
        this.name= name;
        this.id= generateId();}


    private String generateId() {return "U" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();}

    //getters
    public String getId() {return id;}
    public String getName() {return name;}
}
