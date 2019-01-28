package dataClasses;

public class Schooling {
    private int Schooling_ID;
    private String Schooling_Name;
    private String Termin;
    private int CompanyUIDNUmber;

    public Schooling(String Schooling,String Termin,int CompanyUIDNUmber)
    {
        this.Schooling_Name=Schooling;
        this.Termin=Termin;
        this.CompanyUIDNUmber=CompanyUIDNUmber;
    }

    public Schooling(int Schooling_ID, String Schooling,String Termin,int CompanyUIDNUmber)
    {
        this.Schooling_ID = Schooling_ID;
        this.Schooling_Name=Schooling;
        this.Termin=Termin;
        this.CompanyUIDNUmber=CompanyUIDNUmber;
    }

    public Schooling() { }

    public int getCompanyUIDNUmber() {
        return CompanyUIDNUmber;
    }

    public int getSchooling_ID() {
        return Schooling_ID;
    }

    public String getSchooling() {
        return Schooling_Name;
    }

    public String getTermin() {
        return Termin;
    }

    public void setCompanyUIDNUmber(int companyUIDNUmber) {
        CompanyUIDNUmber = companyUIDNUmber;
    }

    public void setSchooling(String schooling) {
        Schooling_Name = schooling;
    }

    public void setTermin(String termin) {
        Termin = termin;
    }

    public void setSchooling_ID(int Schooling_ID){this.Schooling_ID=Schooling_ID;}
}