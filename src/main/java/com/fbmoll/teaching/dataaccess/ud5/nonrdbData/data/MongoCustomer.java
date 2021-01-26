package com.fbmoll.teaching.dataaccess.ud5.nonrdbData.data;
import org.springframework.data.annotation.Id;
public class MongoCustomer {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public MongoCustomer() {}

    public MongoCustomer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
