package model.entity.app;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by mi on 7/22/16.
 */
@Entity
@Table(name = "A", schema = "", catalog = "rentguru24")
public class AEntity {
    private int id;
    private Integer aId;
    private int value;
    private Collection<AEntity> aID;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(nullable=true,name = "A_ID")
    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        if(aId==null){
            this.aId = 0;
        }
        this.aId = aId;
    }

    @Basic
    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AEntity aEntity = (AEntity) o;

        if (id != aEntity.id) return false;
        if (aId != aEntity.aId) return false;
        if (value != aEntity.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + aId;
        result = 31 * result + value;
        return result;
    }

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "A_ID",insertable=false, updatable=false)
    public Collection<AEntity> getaID() {
        return aID;
    }

    public void setaID(Collection<AEntity> aID) {
        this.aID = aID;
    }
}
