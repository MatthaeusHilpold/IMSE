

function add(url,commentJson) {
    var xhttp = new XMLHttpRequest();

    var maskedData = JSON.stringify( commentJson);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            //document.getElementById("demo").innerHTML = xhttp.responseText;
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
            document.getElementById("demo").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("DELETE", url,true);
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

function deleteCustomer(customerId) {
    deletestuff("http://localhost:8080/customer/delete/" + customerId.value);
    document.getElementById("CustomersTable").innerHTML = sendGetRequest("http://localhost:8080/customer/getAllCustomers");
}

function deleteEmployee(employeeId) {
    deletestuff("http://localhost:8080/employee/delete/" + employeeId.value);
    document.getElementById("EmployeeTable").innerHTML = sendGetRequest("http://localhost:8080/employee/getAllEmployees");
}

function deleteSchooling(scholingId) {
    deletestuff("http://localhost:8080/schooling/delete/" + scholingId.value)
    document.getElementById("SchoolingsTable").innerHTML = sendGetRequest("http://localhost:8080/schooling/getAllSchoolings");
}


function sendGetRequest(url) {
    var request = new XMLHttpRequest();
    var response;
    request.open('GET',url,false);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            response = request.response;
        }
    };

    request.send();
    return response;
}

function getAllEmployeesRequest(){
    document.getElementById("EmployeeTable").innerHTML = sendGetRequest("http://localhost:8080/employee/getAllEmployees");
}

function getAllCustomersRequest(){
    document.getElementById("CustomersTable").innerHTML = sendGetRequest("http://localhost:8080/customer/getAllCustomers");
}

function getAllSchoolingsRequest(){
    document.getElementById("SchoolingsTable").innerHTML = sendGetRequest("http://localhost:8080/schooling/getAllSchoolings");
}

function findEmployeeBySurname(surname){
    document.getElementById("EmployeeTable").innerHTML = sendGetRequest("http://localhost:8080/employee/getEmployee/" + surname.value)
}

function findCustomerBySurname(surname){
    document.getElementById("CustomersTable").innerHTML = sendGetRequest("http://localhost:8080/customer/getCustomer/" + surname.value)
}

function findSchoolingByName(name){
    document.getElementById("SchoolingsTable").innerHTML = sendGetRequest("http://localhost:8080/schooling/getSchooling/" + name.value)
}


function sendPutRequest(url,commentJson) {
    var xhttp = new XMLHttpRequest();

    var maskedData = JSON.stringify( commentJson);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            //document.getElementById("demo").innerHTML = xhttp.responseText;
        }
    };

    console.log(maskedData);
    xhttp.open("PUT", url,true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(maskedData);
}

function changeScholingDate(id, newDate) {
    var payload = {"Schooling_id": id.value, "NewDate": newDate.value};

}




