package com.imse.imse.DataInsert;

import com.imse.imse.config.DbConnection;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public final class DataInsert {



    public static void addCompany() {
            // Add 1 Company instance
        StringBuilder query1 = new StringBuilder();
        query1.append("INSERT INTO Company (CompanyName, City, Street) VALUES (");
        query1.append("'ElvisCompany',");
        query1.append("'Vienna',");
        query1.append("'WahringerStrasse')");
        System.out.println(query1);
        try {
            DbConnection.executeQuery(query1.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillEmployeeTable() {
            Random generator = new Random();
            StringBuilder employeeQuery = new StringBuilder();
            employeeQuery.append("INSERT INTO Employee( EmployeeName, EmployeeSurname, BaseSalary," +
                    " TelephoneNumber, SupervisorId, CompanyUIDNumber) VALUES( ");

            employeeQuery.append("'");
            employeeQuery.append(Names.getRandomName().toString()); //Random name
            employeeQuery.append("',");
            employeeQuery.append("'");
            employeeQuery.append(Surnames.getRandomSurname().toString());//  Random Surnamne
            employeeQuery.append("',");
            employeeQuery.append("'");
            employeeQuery.append(Integer.toString(generator.nextInt(3000 - 1000) + 1000));// Random base salary
            employeeQuery.append("',");
            employeeQuery.append("'");
            employeeQuery.append(generator.nextInt(10000000)); //Random tel
            employeeQuery.append("',");
            employeeQuery.append("NULL,"); // SupervisorId for now NULL because there is no other employees yet
            employeeQuery.append("1) ");
            System.out.println(employeeQuery);

            try {
             DbConnection.executeQuery(employeeQuery.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }


            int employeeCounter = 0;
            while (employeeCounter++ < 9) {
                Random generator2 = new Random();
                StringBuilder employeeQuery2 = new StringBuilder();
                employeeQuery2.append("INSERT INTO Employee( EmployeeName, EmployeeSurname, BaseSalary," +
                        " TelephoneNumber, SupervisorId, CompanyUIDNumber) VALUES( ");

                employeeQuery2.append("'");
                employeeQuery2.append(Names.getRandomName().toString()); // Random name
                employeeQuery2.append("',");
                employeeQuery2.append("'");
                employeeQuery2.append(Surnames.getRandomSurname().toString()); // Random Surnamne
                employeeQuery2.append("',");
                employeeQuery2.append("'");
                employeeQuery2.append(Integer.toString(generator2.nextInt(3000 - 1000) + 1000)); // Random base salary
                employeeQuery2.append("',");
                employeeQuery2.append("'");
                employeeQuery2.append(generator2.nextInt(10000000)); //Random tel
                employeeQuery2.append("',");
                employeeQuery2.append("1,");
                employeeQuery2.append("1) ");
                System.out.println(employeeQuery2);

                try {
                  DbConnection.executeQuery(employeeQuery2.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        public static void fillCustomerTable () {
            int customerCounter = 0;

            while (customerCounter++ <= 20) {
                Random generator3 = new Random();
                StringBuilder customerQuery = new StringBuilder();
                customerQuery.append("INSERT INTO Customer(CustomerName, CustomerSurname, CustomerSince,EmployeeID) VALUES (");
                customerQuery.append("'");
                customerQuery.append(Names.getRandomName().toString()); //Random name
                customerQuery.append("',");
                customerQuery.append("'");
                customerQuery.append(Surnames.getRandomSurname().toString()); // Random Surnamne
                customerQuery.append("',");
                customerQuery.append("'2017-12-03',");
                customerQuery.append("'");
                customerQuery.append(1); //Employee id
                customerQuery.append("')");
                System.out.println(customerQuery);

                try {
                    DbConnection.executeQuery(customerQuery.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            //spouse

            int spouseCounter = 0;
            while (spouseCounter++ < 10) {
                StringBuilder spouseQuery = new StringBuilder();
                spouseQuery.append("INSERT INTO Spouse(CustomerId, SpouseName, HaveChildren, SpouseSince) VALUES (");
                spouseQuery.append("'");
                spouseQuery.append(spouseCounter);
                spouseQuery.append("',");
                spouseQuery.append("'");
                spouseQuery.append(Names.getRandomName().toString());
                //Random name
                spouseQuery.append("',");
                spouseQuery.append("'1',");
                spouseQuery.append("'1994-12-03')");
                System.out.println(spouseQuery);

                try {
                    DbConnection.executeQuery(spouseQuery.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
         //Schooling


    public static void fillSchoolingTable(){
        int schoolingCounter = 1;
        while (schoolingCounter++ < 10) {

            StringBuilder schoolingQuery = new StringBuilder();
            schoolingQuery.append("INSERT INTO Schooling(SchoolingName,Termin, CompanyUIDNUmber) VALUES( ");
            schoolingQuery.append("'schooling name'");
            schoolingQuery.append(" ,'2019-12-03',");
            schoolingQuery.append("'1') ");
            System.out.println(schoolingQuery);

            try {
              DbConnection.executeQuery(schoolingQuery.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}


