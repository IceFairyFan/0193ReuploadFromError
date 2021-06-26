$.getJSON('...', function (data_project) {
    console.log(data_project);
    let project = data.project;
    $.each(project, function (i, data_project) {
       $('#list_project').append('<td>'+ data_project.project_name+'</td><td>'+ data_project.company_name +'</td><td>'+ data_project.project_date+'</td>')
    });
});
