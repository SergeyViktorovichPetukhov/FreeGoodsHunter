
alert('script');
function newSharesRequest() {
    var request = new XMLHttpRequest();
    request.setParameter('','time',new Date());
    request.open('GET','/ajax/getNewShares',true);
    request.addEventListener('readystatechange', function() {
        if ((request.readyState==4) && (request.status==200)) {
            var json = request.responseText;
            var data = JSON.parse(json);
            var array = [data[0].login,data[0].productName, data[0].productDescription, data[0].productName ,data[0].linkOnProductUrl,data[0].price,data[0].count,data[0].announcementDuration,data[0].shareDuration,data[0].afterShareDuration,data[0].placeCountry,data[0].placeCity,data[0].placeRegion,data[0].color];
            $('#company_shares').append('<tbody>' +
                '<tr>' +
                '<td>'+array[0]+'</td>' +
                '<td>'+array[1]+'</td>' +
                '<td>'+array[2]+'</td>' +
                '<td>'+array[3]+'</td>' +
                '<td>'+array[4]+'</td>' +
                '<td>'+array[5]+'</td>' +
                '<td>'+array[6]+'</td>' +
                '<td>'+array[7]+'</td>' +
                '<td>'+array[8]+'</td>' +
                '<td>'+array[9]+'</td>' +
                '<td>'+array[10]+'</td>' +
                '<td>'+array[11]+'</td>' +
                '<td>'+array[12]+'</td>' +
                '</tr>' +
                '</tbody>')

        }
        else {
            alert('something wrong');
        }
    });
    request.send();
};
setTimeout(newSharesRequest,10000);
window.addEventListener("load",newSharesRequest);