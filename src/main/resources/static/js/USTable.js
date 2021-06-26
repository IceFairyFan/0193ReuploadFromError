$.getJSON('...', function (data_us) {
    console.log(data_us);
    let us = data_us;
    $.each(us, function (i, data_us) {
        $('#list_us').append('<td>'+ data_us.user_story+'</td><td>'+ data_us.task_type +'</td><td>'+ data_us.us_priority+'</td>')
    });
});
