/**
 * Created by alisher on 6/7/17.
 */
var dictName = '';
var userName = '';

loadDictionaries();
loadUsers();

// Table of stars
var table = $('#starsTable').DataTable({
    "sAjaxSource": "/list",
    "sAjaxDataProp": "",
    "paging": false,
    "ordering": false,
    "info": false,
    "searching": false,
    "aoColumns": [
        {"mData": "id"},
        {"mData": "name"},
        {"mData": "coordX"},
        {"mData": "coordY"},
        {"mData": "dictionary.name"},
        {"mData": "user.name"},
        {"render": function (data, type, full, meta) {
                var urlEdit = "/edit/" + full.id;
                return '<button id="delete" class="btn btn-danger"  onclick="deleteStar(' + full.id + ')" >Delete</button>&nbsp;&nbsp;<a class="btn btn-success" href="' + urlEdit + '">Edit</a>';
            }
        }
    ]
});

$('#editForm').submit(function (event) {
    var id = $('#starId').attr("value");
    var name = $('#name').val();
    var coordX = $('#coordX').val();
    var coordY = $('#coordY').val();
    var dict = $('#dict').val();
    var user = $('#users').val();
    var json = {
        "id": id,
        "name": name,
        "coordX": coordX,
        "coordY": coordY,
        "dictionary": {"id": dict},
        "user": {"id": user}
    };

    $.ajax({
        url: $("#editForm").attr("action"),
        data: JSON.stringify(json),
        type: "PUT",
        dataType: 'text',

        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            $(".error").remove();
        },
        success: function (data) {
            window.location.href = "/main";
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
    event.preventDefault();
});

$("#addUser").on('click', function () {
    var addName = $('#addName').val();
    var json = {"name": addName};

    $.ajax({
        url: getHostname(document.URL) + "/user/save",
        data: JSON.stringify(json),
        type: "POST",
        dataType: 'text',

        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            $('#userModal').modal('hide');
            $('#users').empty();
            loadUsers();
        }
    });
});

$('#editUserModal').on('show.bs.modal', function (event) {
    var nameUser = $("#users option:selected").text();
    var modal = $(this);
    modal.find('.modal-body input').val(nameUser)
});

$("#saveEditUser").on('click', function () {
    var editName = $('#editName').val();
    var id = $('#users').val();
    var json = {"id": id, "name": editName};

    $.ajax({
        url: getHostname(document.URL) + "/user/edit",
        data: JSON.stringify(json),
        type: "POST",
        dataType: 'text',

        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            $('#editUserModal').modal('hide');
            $('#users').empty();
            loadUsers();
        }
    });
});


// Send post request to create star
$('#createForm').submit(function (event) {
    var name = $('#name').val();
    var coordX = $('#coordX').val();
    var coordY = $('#coordY').val();
    var dict = $('#dict').val();
    var user = $('#users').val();
    var json = {"name": name, "coordX": coordX, "coordY": coordY, "dictionary": {"id": dict}, "user": {"id": user}};

    $.ajax({
        url: $("#createForm").attr("action"),
        data: JSON.stringify(json),
        type: "POST",
        dataType: 'text',

        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            $('#createForm')[0].reset();
            window.location.href = "/main";
        }
    });
    event.preventDefault();
});

function deleteStar(id) {
    var conBox = confirm("Are you sure ?");
    if (conBox) {
        $.ajax({
            url: "/delete/" + id,
            type: "GET",
            dataType: "text",

            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },

            success: function () {
                var tr = $(event.target).closest("tr");
                tr.css("background-color", "#000000");
                tr.fadeIn(1000).fadeOut(200, function () {
                    tr.remove();
                });
                table.ajax.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#deleteAlert').css('visibility', 'visible');
                $('#deleteAlert').text(jqXHR.responseText);
            }
        });
    } else {
        event.preventDefault();
    }
}

//List of dictionaries
function loadDictionaries() {
    $.get("/dictionaries", function (data) {
        $.each(data, function (key, value) {
            var option = $("<option></option>");
            if (value.name === dictName) {
                option.attr("selected", "selected");
            }
            $('#dict').append(option.attr("value", value.id).text(value.name));
        });
    });
}

//List of available users
function loadUsers() {
    $.get("/users", function (data) {
        $.each(data, function (key, value) {
            var option = $("<option></option>");
            if (value.name === userName) {
                option.attr("selected", "selected");
            }
            $('#users').append(option.attr("value", value.id).text(value.name));
        });
    });
}

function getHostname(url) {
    var m = url.match(/^http:\/\/[^/]+/);
    return m ? m[0] : null;
}