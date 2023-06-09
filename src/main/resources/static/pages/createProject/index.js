onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建项目')
}

const handleCreateProject = () => {
  let params = {
    userId: $util.getItem('userInfo').id,
    createdBy: $util.getItem('userInfo').username,
    lastUpdatedBy: $util.getItem('userInfo').username,
    projectName: $('#projectName').val(),
    projectContent: $('#projectDescribe').val()
  }
  if (!params.projectName) return alert('项目名称不能为空！')
  if (!params.projectContent) return alert('项目描述不能为空！')
  $.ajax({
    url: API_BASE_URL + '/project/addProject',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if(res.code === '200') {
        alert(res.message)
        location.href = "/pages/questionnaire/index.html"
      }else{
        alert(res.message)
      }
    }
  })
}
