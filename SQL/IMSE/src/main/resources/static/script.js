function add(url,commentJson) {
    var xhttp = new XMLHttpRequest();
    /*var schooling=Schooling_Name;
    var termin=Termin;
    var UID=CompanyUIDNumber;*/

    var maskedData = JSON.stringify( commentJson);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            document.getElementById("demo").innerHTML =xhttp.responseText;
        }
    };

    console.log(maskedData);
    xhttp.open("POST", url,true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(maskedData);
}

function deletestuff(url) 
{
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            document.getElementById("demo").innerHTML =xhttp.responseText;
        }
    };
    xhttp.open("DELETE", url,true);
    //xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
	console.log("delete request processed.");
}

function saveSchooling(Schooling_Name,Termin,CompanyUIDNumber)
{
    var commentJson = {"Schooling_Name": Schooling_Name.value, "Termin": Termin.value,"CompanyUIDNumber": CompanyUIDNumber.value};
    add("http://localhost:8080/schooling/add",commentJson);
}

function saveCustomer(customerName, surname, supervisorId)
{
    var commentJson = {"CustomerName": customerName.value, "CustomerSurname": surname.value,"employeeId": supervisorId.value};
    add("http://localhost:8080/customer/add",commentJson);
}

function saveEmployee(employeename, surname, salary ,supervisorId, companyUID, telephone)
{
    var commentJson = {"EmployeeName": employeename.value, "EmployeeSurname": surname.value,"baseSalary": salary.value,"telephoneNumber":telephone.value,
                        "supervisorId":supervisorId.value,"CompanyUIDNumber":companyUID.value};
    add("http://localhost:8080/employee/add",commentJson);
}

function deleteCustomer(customerId)
{
    deletestuff("http://localhost:8080/customer/delete/" + customerId.value);
}

