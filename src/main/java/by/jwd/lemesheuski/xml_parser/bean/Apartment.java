package by.jwd.lemesheuski.xml_parser.bean;

public class Apartment {
    private int id;
    private int floor;
    private int room_number;
    private boolean balcony;

    public Apartment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (id != apartment.id) return false;
        if (floor != apartment.floor) return false;
        if (room_number != apartment.room_number) return false;
        return balcony == apartment.balcony;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + floor;
        result = 31 * result + room_number;
        result = 31 * result + (balcony ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", floor=" + floor +
                ", room_number=" + room_number +
                ", balcony=" + balcony +
                '}';
    }
}
