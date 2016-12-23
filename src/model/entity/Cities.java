package model.entity;

import javax.persistence.*;

/**
 * Created by omar on 12/23/16.
 */
@Entity
@Table(name = "cities", schema = "")
public class Cities {
    private int id;
//    private int stateId;
    private String cityName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Id
//    @Column(name = "state_id")
//    public int getStateId() {
//        return stateId;
//    }
//
//    public void setStateId(int stateId) {
//        this.stateId = stateId;
//    }

    @Basic
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cities cities = (Cities) o;

        if (id != cities.id) return false;
//        if (stateId != cities.stateId) return false;
        return !(cityName != null ? !cityName.equals(cities.cityName) : cities.cityName != null);

    }

    @Override
    public int hashCode() {
        int result = id;
//        result = 31 * result + stateId;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id=" + id +
//                ", stateId=" + stateId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
