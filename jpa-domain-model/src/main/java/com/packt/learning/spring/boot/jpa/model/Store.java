package com.packt.learning.spring.boot.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import java.util.Objects;
import java.util.Set;

@Entity
/*
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "storeTypeId",
        discriminatorType = DiscriminatorType.INTEGER
)
*/
public class Store extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "store_sequence_generator")
    @SequenceGenerator(name = "store_sequence_generator",
            sequenceName="store_sequence", allocationSize = 1)
    private int id;

    @Column(nullable = false, length = 50, insertable = true)
    private String name;

    @Column(nullable = false, length = 50, insertable = true)
    private String location;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Section> sections;

    @PrePersist
    public void beforeSave() {
        System.out.println("Saving the Store " + getId());
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "StoreManager",
            joinColumns = {
                // navigating from the 'StoreManager' to the 'Store'
                @JoinColumn(name = "store_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                // navigating from the 'StoreManager' to the 'Manager'
                @JoinColumn(name = "manager_id", referencedColumnName = "id")
            }
    )
    private Set<Manager> storeManagers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public Set<Manager> getStoreManagers() {
        return storeManagers;
    }

    public void setStoreManagers(Set<Manager> storeManagers) {
        this.storeManagers = storeManagers;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return id == store.id &&
                Objects.equals(name, store.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
