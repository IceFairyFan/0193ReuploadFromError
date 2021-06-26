$.getJSON('...', function (data_meeting) {
    console.log(data_meeting);
    let meeting = data_meeting;
    $.each(meeting, function (i, data_meeting) {
        $('#list_meeting').append('<td>'+ data_meeting.meeting_date+'</td><td>'+ data_meeting.meeting_name+'</td>')
    });
});
