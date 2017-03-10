function fetchPeople() {

    var url = "http://localhost:8084/Rest_Jax-RS_Ex2/api/person/";
    var conf = {method: 'get'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {

        document.getElementById("bodyt").innerHTML = listMaker(text);

    });

}
function fetchPerson(id) {

    var url = "http://localhost:8084/Rest_Jax-RS_Ex2/api/person/" + id;
    var conf = {method: 'get'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {

        var person = JSON.parse(text);

        console.log(person.id);

        document.getElementById("eid").value = person.id;
        document.getElementById("efName").value = person.fName;
        document.getElementById("elName").value = person.lName;
        document.getElementById("ephone").value = person.phone;

    });

}

function editPerson() {


    var id = document.getElementById("eid");
    var fName = document.getElementById("efName");
    var lName = document.getElementById("elName");
    var phone = document.getElementById("ephone");

    var url = "http://localhost:8084/Rest_Jax-RS_Ex2/api/person";
    var conf = {method: 'PUT',
        headers:
                {
                    'Accept': 'application/JSON',
                    'Content-Type': 'application/JSON'
                },

        body: JSON.stringify({
            id: id.value.toString(),
            fName: fName.value.toString(),
            lName: lName.value.toString(),
            phone: phone.value.toString()
        })
    };
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();
    }).then(function (text) {

        console.log(text);
        fetchPeople();

    });



}


function removePerson(id) {

    var url = "http://localhost:8084/Rest_Jax-RS_Ex2/api/person/" + id;
    var conf = {method: 'delete'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {

        console.log(text);
        fetchPeople();

    });


}

function listMaker(arr) {

    var parsed = JSON.parse(arr);

    var lis = parsed.map(function (people) {

        return  "<tr>" +
                "<td>" + people.id + "</td>" +
                "<td>" + people.fName + "</td>" +
                "<td>" + people.lName + "</td>" +
                "<td>" + people.phone + "</td>" +
                "<td>" + "<a href= \"\" onclick=\"removePerson(" + people.id + ");return false;\">delete</a>" + " / " + "<a href= \"\" data-toggle=\"modal\" data-target=\"#myEditModal\" onclick=\"fetchPerson(" + people.id + ");return false;\">edit</a>" + "</td>" +
                "</tr>";

    });

    return lis.join("");

}
;

function addPerson() {

    var fName = document.getElementById("fName");
    var lName = document.getElementById("lName");
    var phone = document.getElementById("phone");

    var url = "http://localhost:8084/Rest_Jax-RS_Ex2/api/person";
    var conf = {method: 'POST',
        headers:
                {
                    'Accept': 'application/JSON',
                    'Content-Type': 'application/JSON'
                },

        body: JSON.stringify({
            fName: fName.value.toString(),
            lName: lName.value.toString(),
            phone: phone.value.toString()
        })
    };
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();
    }).then(function (text) {

        console.log(text);
        fetchPeople();

    });
}
;


fetchPeople();

