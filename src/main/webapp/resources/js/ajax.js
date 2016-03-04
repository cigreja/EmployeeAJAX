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
            var firstName = $('#firstName').val();
            var lastName = $('#lastName').val();
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
});