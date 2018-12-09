package Task3;
//worker with salary per hour

public class LittlePony1 extends BigPony {

    LittlePony1 (int Id, String Name, double Salary) {
        super (Id, Name, Salary);
        this.Salary = Salary*8*20.8;
    }

    @Override
    double avarage() { return  20.8 * 8 * Salary; }

    //public String toString() { return  "ID " + this.Id + "    Name  " + this.Name + "    Salary Per Month  " + this.SalaryPerHour + "\n"; }

}
