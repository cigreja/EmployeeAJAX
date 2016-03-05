/**
 * Created by Carlos on 3/3/2016.
 */

function addFormReady() {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var address = $('#address').val();
    if (firstName === "" || lastName === "" || address === "") {
        return false;
    }
    else {
        return true;
    }
}

$(document).ready(function () {
    
    $('#addBtn').click(function () {
        if (addFormReady()) {
            var firstName = $('#addFirstName').val();
            var lastName = $('#addLastName').val();
            var address = $('#address').val();
            $.ajax({
                type: 'POST',
                data: {
                    firstName: firstName,
                    lastName: lastName,
                    address: address
                },
                url: 'Add',
                success: function (result) {
                    $('#addMsg').html(result);
                }
            })
        }
    })
    
    $('#getBtn').click(function(){
        if(addFormReady()){
            // information to post with ajax
            var firstName = $('#getFirstName').val();
            var lastName = $('#getLastName').val();
            var details = $('#getEmployeeForm').serialize();

            $.post('Get', details)
                .done(function(data){
                    alert("data = " + data);
                })
                .fail(function(){
                    alert("There was an error!");
                });

            //$.ajax({
            //    type: 'POST',
            //    url: 'Get',
            //    success: function (data) {
            //        alert("id = " + data.addressID + " address = " + data.address);
            //    }
            //});
            //var addressID;
            //var address;
        }
    })
});