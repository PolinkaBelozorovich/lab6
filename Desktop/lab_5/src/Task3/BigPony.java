package Task3;

import java.util.Comparator;

public abstract class BigPony {

    String Name;
    double Salary;
    int Id;

    BigPony (int Id, String Name, double Salary) {
        this.Name = Name;
        this.Id = Id;
        this.Salary = Salary;
    }

    public String getName() {return Name;}
    public int getId() {return Id;}
    public double getSalary() {return Salary;}

    public void setName(String name) { this.Name = name; }
    public void setSalary(double salary) { this.Salary = salary; }
    public void setId(int id) { this.Id = id;}

    abstract double avarage();

    public static Comparator<BigPony> BySalary = new Comparator<BigPony>() {

        public int compare(BigPony w1, BigPony w2) {
            if (w1.avarage() != w2.avarage()) {
                if (w1.avarage() > w2.avarage()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return w1.Name.compareTo(w2.Name);
            }
        }
    };

    @Override
    public String toString() { return  "ID " + this.Id + "    Name  " + this.Name + "    Salary  " + this.Salary + "\n"; }

}
